package com.mf1.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.entities.Account;
import com.mf1.service.AccountService;

@Controller
@RequestMapping("admin/account")
public class Admin_AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping
	public String index(@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		
		List<Account> accountList;

	    if (keyword != null && !keyword.isEmpty()) {
	        accountList = accountService.searchAccount(keyword);
	        model.addAttribute("keyword", keyword);
	        System.out.println("Tìm theo keyword " + keyword);
	    } else {
	        accountList = accountService.getAllAccount();
	        System.out.println("không tìm nè");
	    }

	    model.addAttribute("accountList", accountList);
	    model.addAttribute("currentLine", "account");

	    return "admin-account";
		
	}
		
}
