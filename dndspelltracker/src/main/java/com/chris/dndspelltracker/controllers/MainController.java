package com.chris.dndspelltracker.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chris.dndspelltracker.models.LoginUser;
import com.chris.dndspelltracker.models.User;
import com.chris.dndspelltracker.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "loginandregister.jsp";
	}
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
    	
    	User registeredUser = userServ.register(newUser, result);
        
    	if(result.hasErrors()) {
    		model.addAttribute("newLogin", new LoginUser());
    		return "loginandregister.jsp";
    	}
        
        
    	session.setAttribute("userId", registeredUser.getId());
    
        return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
            
    	User loginUser = userServ.login(newLogin, result);
    	
        if(result.hasErrors()) {
        	System.out.println(result.getAllErrors());
            model.addAttribute("newUser", new User());
            return "loginandregister.jsp";
        }
            
        
        session.setAttribute("userId", loginUser.getId());
    
        return "redirect:/dashboard";
    
    }   
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	User user = userServ.findById((Long) session.getAttribute("userId"));
    	model.addAttribute("user", user);
    	model.addAttribute("characters", user.getCharacters());
    	
    	return "dashboard.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
