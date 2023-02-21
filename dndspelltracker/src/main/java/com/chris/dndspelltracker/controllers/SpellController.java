package com.chris.dndspelltracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chris.dndspelltracker.models.DNDChar;
import com.chris.dndspelltracker.models.Spell;
import com.chris.dndspelltracker.services.DNDCharService;
import com.chris.dndspelltracker.services.SpellService;
import com.chris.dndspelltracker.services.UserService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class SpellController extends HttpServlet {
	
	@Autowired
	private DNDCharService dndCharServ;
	
	@Autowired 
	private UserService userServ;
	
	@Autowired
	private SpellService spellServ;
	
	@GetMapping("/spells/new")
	public String newSpells(HttpSession session, Model model) throws IOException, UnirestException {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
		DNDChar newChar = dndCharServ.findById((Long) session.getAttribute("dndCharId"));
		List<Spell> charSpells = getClassSpellList(newChar);
		Map<String, Boolean> displayedLevels = newChar.displaySpellLevels(charSpells);
    	
    	
		model.addAttribute("classSpellList", charSpells);
    	model.addAttribute("dndChar", newChar);
		model.addAttribute("displayedLevels" , displayedLevels);
    	
		return "newSpells.jsp";
	}
	
	@PostMapping("/spells/add")
    public String createSpells(HttpSession session, Model model, HttpServletRequest request) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	List<Spell> classSpells = new ArrayList<>();
    	String[] spells = request.getParameterValues("spells[]");
    	DNDChar newChar = dndCharServ.findById((Long) session.getAttribute("dndCharId"));
    	
    	if (spells != null) {
    	    for (String spell : spells) {
    	        Spell sqlSpell = spellServ.findSpellByName(spell).get();
    	        classSpells.add(sqlSpell);
    	        
    	        List<DNDChar> spellChar = sqlSpell.getCharacters();
    	        spellChar.add(newChar);
    	        sqlSpell.setCharacters(spellChar);
    	        spellServ.updateSpell(sqlSpell);
    	    }
    	}
    	else {
    		return "redirect:/spells/new";
    	}
    	
    	newChar.setSpells(classSpells);
    	dndCharServ.updateChar(newChar);
    
    	session.removeAttribute("dndCharId");
    	    	
    	return "redirect:/dashboard";
    }
	
	@GetMapping("/spells/edit")
	public String editSpells(HttpSession session, Model model) throws IOException, UnirestException {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
		DNDChar editChar = dndCharServ.findById((Long) session.getAttribute("dndCharId"));
		List<Spell> charSpells = getClassSpellList(editChar);
    	Map<String, Boolean> displayedLevels = editChar.displaySpellLevels(charSpells);
    	
    	model.addAttribute("classSpellList", charSpells);
    	model.addAttribute("dndChar", editChar);
		model.addAttribute("displayedLevels" , displayedLevels);
    	
		return "editSpells.jsp";
	}
	
	@PostMapping("/spells/update")
    public String updateSpells(HttpSession session, Model model, HttpServletRequest request) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	List<Spell> classSpells = new ArrayList<>();
    	String[] spells = request.getParameterValues("spells[]");
    	DNDChar newChar = dndCharServ.findById((Long) session.getAttribute("dndCharId"));
    	
    	if (spells != null) {
    	    for (String spell : spells) {
    	        Spell sqlSpell = spellServ.findSpellByName(spell).get();
    	        classSpells.add(sqlSpell);
    	        List<DNDChar> spellChar = sqlSpell.getCharacters();
    	        spellChar.add(newChar);
    	        sqlSpell.setCharacters(spellChar);
    	        spellServ.updateSpell(sqlSpell);
    	    }
    	}
    	else {
    		return "redirect:/spells/edit";
    	}
    	
    	newChar.setSpells(classSpells);
    	dndCharServ.updateChar(newChar);
    
    	session.removeAttribute("dndCharId");
    	    	
    	return "redirect:/dashboard";
    }
	
	public List<Spell> getClassSpellList(DNDChar dndChar) throws IOException, UnirestException {
		List<Spell> spellList = new ArrayList<Spell>();
		
		HttpResponse<JsonNode> response = Unirest.get("https://www.dnd5eapi.co/api/classes/" + dndChar.getDndclass().toLowerCase() + "/spells")
				.header("accept", "application/json")
				.asJson();
		
		JSONArray classSpellList = (JSONArray) response.getBody().getObject().get("results");
		
		for (int i=0; i<classSpellList.length(); i++) {
			
			JSONObject objectSpell = (JSONObject) classSpellList.get(i);
			String stringSpell = (String) objectSpell.get("name");
			String oneSpell = stringSpell.replace(" ", "-").replace("/", "-").replace("'", "").toLowerCase();
			HttpResponse<JsonNode> res = Unirest.get("https://www.dnd5eapi.co/api/spells/" + oneSpell)
					.header("accept", "application/json")
					.asJson();
			
			Spell spell = new Spell();
			spell.setDesc((String) ((JSONArray) res.getBody().getObject().get("desc")).get(0));
			spell.setName((String) res.getBody().getObject().get("name"));
			spell.setLevel((Integer) res.getBody().getObject().get("level"));
			
			spellList.add(spell);
			
			
			if (spellServ.findSpellByName(spell.getName()).isPresent() == false) {
				System.out.println("new spell");
				spellServ.saveSpell(spell);
			}
		}
		
		return spellList;
	}
	
	
	
}
