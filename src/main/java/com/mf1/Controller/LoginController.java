package com.mf1.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.entities.Account;
import com.mf1.repository.AccountRepository;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	HttpServletResponse response;
	
	@GetMapping()
	public String index() {
		return "../layout/guestLogin";
	}
	
	@PostMapping()
	public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
		
		Optional<Account> user = Optional.ofNullable(accountRepository.findByEmail(email));
		if (user.isEmpty()) {
			sessionService.set("LoginMessage", "Tài khoản không tồn tại !");
		} else {
			if (user.get().getPassword().equals(password)) {
				sessionService.set("account", user.get());
				sessionService.set("LoginMessage", "");
				try {
					return "redirect:/home";
				} catch (Exception e) {
					System.out.println("Lỗi đăng nhập");
					return "../layout/guestLogin";
				}
			} else {
				sessionService.set("LoginMessage", "Mật khẩu không chính xác !");
				return "../layout/guestLogin";
			}
		}
		return "../layout/guestLogin";
	}
	
	@GetMapping("/logout")
	public String logOut() {
		if(sessionService.get("account") != null) {
			sessionService.remove("account");
		}
		return "redirect:/login";
	}
	
	
}
