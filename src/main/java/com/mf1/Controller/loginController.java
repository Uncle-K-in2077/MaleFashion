package com.mf1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class loginController {
	
	@GetMapping("")
	public String login() {
		return "../layout/guestLogin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "../layout/guestSignUp";
	}
}
