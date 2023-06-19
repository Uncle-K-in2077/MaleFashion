package com.mf1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class homeController {
	
	
	@GetMapping()
	public String home(Model model) {
		model.addAttribute("currentPage", "home");
		return "pages/home";
	}
	
	@GetMapping("/1")
	public String testView() {
		return "/home";
	}
	
}