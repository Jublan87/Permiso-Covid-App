package com.unla.grupo21.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo21.helpers.ViewRouteHelper;

//LOGIN
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error", required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.LOGGING_LOGIN;
	}
	
	@GetMapping("/logoutsuccess")
	public String logout(Model model) {
		return ViewRouteHelper.LOGGING_LOGOUT;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}
}
