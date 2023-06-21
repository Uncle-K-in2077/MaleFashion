package com.mf1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mf1.entities.Account;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/home")
public class homeController {
	@Autowired
	SessionService sessionService;
	
	@GetMapping()
	public String home(Model model) {
		model.addAttribute("currentPage", "home");
		Account account = (Account) sessionService.get("account");
	    model.addAttribute("account", account);
		return "pages/home";
	}
	
	@GetMapping("/1")
	public String testView() {
		return "/home";
	}
	
}