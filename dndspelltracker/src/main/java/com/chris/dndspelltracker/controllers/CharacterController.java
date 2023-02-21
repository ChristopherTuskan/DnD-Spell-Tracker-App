package com.chris.dndspelltracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chris.dndspelltracker.models.DNDChar;
import com.chris.dndspelltracker.models.Spell;
import com.chris.dndspelltracker.models.User;
import com.chris.dndspelltracker.services.DNDCharService;
import com.chris.dndspelltracker.services.SpellService;
import com.chris.dndspelltracker.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class CharacterController {
	
	@Autowired
	private DNDCharService dndCharServ;
	
	@Autowired 
	private UserService userServ;
	
	@Autowired
	private SpellService spellServ;
	
	@GetMapping("/character/new/")
	public String newCharacter(HttpSession session, Model model) throws IOException, UnirestException {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	model.addAttribute("dndchar", new DNDChar());
    	model.addAttribute("levelList", getLevelList());
    	model.addAttribute("classList", getClassList());
    	
		return "newCharacter.jsp";
	}
	
	@PostMapping("/character/create/")
    public String createCharacter(@Valid @ModelAttribute("dndchar") DNDChar dndchar, BindingResult result, HttpSession session, Model model) throws IOException, UnirestException {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	if(result.hasErrors()) {
    		System.out.println(result.getAllErrors());
    		model.addAttribute("dndchar", dndchar);
    		model.addAttribute("levelList", getLevelList());
        	model.addAttribute("classList", getClassList());
    		return "newCharacter.jsp";
    	}
    	
    	User user = userServ.findById((Long) session.getAttribute("userId"));
    	dndchar.setUser(user);
    	DNDChar newChar = dndCharServ.saveChar(dndchar);
    	session.setAttribute("dndCharId", newChar.getId());
    	
    	return "redirect:/spells/new";
    }
	
	@GetMapping("/character/edit/{id}")
	public String editCharacter(@PathVariable("id") Long id, HttpSession session, Model model) throws IOException, UnirestException {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		
		DNDChar dndChar = dndCharServ.findById(id);
		model.addAttribute("dndcharacter", dndChar);
		model.addAttribute("classList", getClassList());
		model.addAttribute("levelList", getLevelList());
		session.setAttribute("dndCharId", dndChar.getId());
		
		return "editCharacter.jsp";
	}
	
	@PostMapping("/character/update/")
	public String updateCharacter(@Valid @ModelAttribute("dndcharacter") DNDChar dndchar, BindingResult result, HttpSession session, Model model) throws IOException, UnirestException {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("dndcharacter", dndchar);
			model.addAttribute("classList", getClassList());
			model.addAttribute("levelList", getLevelList());
    		return "editCharacter.jsp";
    	}
    	
		Long dndId = (Long) session.getAttribute("dndCharId");
    	DNDChar findChar = dndCharServ.findById(dndId);
    	List<Spell> emptySpellList = new ArrayList<>();
    	
    	List <Spell> characterSpells = findChar.getSpells();
//		for (int i=0; i<characterSpells.size(); i++) {
////			Spell oneSpell = characterSpells.get(i);
////			List <DNDChar> spellCharacters = oneSpell.getCharacters();
////			spellCharacters.remove(findChar);
////			oneSpell.setCharacters(spellCharacters);
////			
////			spellServ.updateSpell(oneSpell);
//		}
    	
    	if (findChar.getDndclass().equals(dndchar.getDndclass()) == false) {
    		findChar.setSpells(emptySpellList);
    	}
    	else {
    		findChar.setSpells(characterSpells);
    	}
    	
    	findChar.setDndclass(dndchar.getDndclass());
    	findChar.setFirstName(dndchar.getFirstName());
    	findChar.setLastName(dndchar.getLastName());
    	findChar.setLevel(dndchar.getLevel());
    	
    	DNDChar updatedChar = dndCharServ.updateChar(findChar);
    	session.setAttribute("dndCharId", updatedChar.getId());
    	
    	return "redirect:/spells/edit";
		
	}
	
	@GetMapping("/character/view/{id}")
	public String viewCharacter(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		
		DNDChar dndChar = dndCharServ.findById(id);
		
		List<Spell> charSpells = dndChar.getSpells();
		Map<String, Boolean> displayedLevels = dndChar.displaySpellLevels(charSpells);
		
		model.addAttribute("character", dndChar);
		model.addAttribute("displayedLevels" , displayedLevels);
		
		return "viewCharacter.jsp";
	}
	
	@GetMapping("/character/destroy/{id}")
    public String destroyCharacter(@PathVariable("id") Long id, HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	DNDChar dndChar = dndCharServ.findById(id);
    	dndCharServ.deleteDNDChar(dndChar);
    	
    	return "redirect:/dashboard";
    }
	
	public List<Integer> getLevelList() {
		List<Integer> levelList = new ArrayList<Integer>();
		for (int i=1; i<=20; i++) {
			levelList.add(i);
		}
		return levelList;
	}
	
	public List<String> getClassList() throws IOException, UnirestException {
		
		List<String> dndClassList = new ArrayList<>();
		HttpResponse<JsonNode> response = Unirest.get("https://www.dnd5eapi.co/api/classes")
				.header("accept", "application/json")
				.asJson();
		
		JSONObject dndClasses = new ObjectMapper().readValue(response.getBody().toString(), JSONObject.class);
		ArrayList dndClassResults = (ArrayList) dndClasses.get("results");
		
		for (int i=0; i<dndClassResults.size(); i++) {
			LinkedHashMap dndClassIndex = (LinkedHashMap) dndClassResults.get(i);
			dndClassList.add((String) dndClassIndex.get("name"));
		}
		
		return dndClassList;
	}
	
	
	
}
