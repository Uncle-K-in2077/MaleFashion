package com.mf1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.dto.CodeOTP;
import com.mf1.entities.Account;
import com.mf1.repository.AccountRepository;
import com.mf1.service.MailerService;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public String getURL() {
		StringBuilder url = new StringBuilder();
		String scheme = request.getScheme();
		int port = request.getServerPort();

		url.append(scheme).append("://").append(request.getServerName());

		if (port != 80 && port != 443) {
			url.append(":").append(port);
		}

		return url.toString();
	}

	public String generateRandomString() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(10);

		for (int i = 0; i < 10; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	private List<CodeOTP> listOTP = new ArrayList<>();
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MailerService mailerService;
	
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
	
	@GetMapping("/forgotPassword")
	public String forgotPasswrod() {
		
		return "../layout/forgotPassword";
	}
	
	@PostMapping("/forgot-password")
	public String fogotPasswordVerify(Model model, @RequestParam("verifyEmail") String verifyEmail) {
		if(verifyEmail.equals("") || verifyEmail.isEmpty()) {
			model.addAttribute("errorMessage", "Complete your EMAIL..!");
			System.out.println("Complete your EMAIL..!");
			return "../layout/forgotPassword";
		}
		
		Optional<Account> verifyAccount = Optional.ofNullable(accountRepository.findByEmail(verifyEmail));
		if(!verifyAccount.isPresent()) {
			model.addAttribute("errorMessage", "No account match with your Email!");
			System.out.println("No account match with your Email!");
			return "../layout/forgotPassword";
		}
		
//		GenerateCodeOtp
		
		CodeOTP newCode = new CodeOTP();
		newCode.setEmail(verifyEmail);
		newCode.setCode(generateRandomString());
		listOTP.add(newCode);
		
		try {
			mailerService.send(verifyEmail, "MaleFashion - Verify Account", "Your OTP to reset password is:  " + newCode.getCode() + "");
		}catch (Exception e) {
			System.out.println("Email gửi không thành công");
		}
		sessionService.set("verifyEmail", verifyEmail);
		return "redirect:/login/confirmOTP";
		
		
	}
	
	@GetMapping("/confirmOTP")
	public String viewConfirmOTP(Model model) {
		String verifyEmail = sessionService.get("verifyEmail");
		model.addAttribute("verifyEmail", verifyEmail);
		System.out.println("Email lấy về là " + verifyEmail);
		return "../layout/confirmOTP";
	}
	
	@PostMapping("/confirmOTP")
	public String confirmOTP(Model model, @RequestParam("otp") String otp) {
		String verifyEmail = sessionService.get("verifyEmail");
		
		 CodeOTP matchedOTP = null;
		    for (CodeOTP codeOTP : listOTP) {
		        if (codeOTP.getEmail().equals(verifyEmail) && codeOTP.getCode().equals(otp)) {
		            matchedOTP = codeOTP;
		            System.out.println("Đã xác nhận được email");
		            break;
		        }
		    }
		    
		    if (matchedOTP != null) {
				System.out.println("SignUp succsess!");
		    	listOTP.remove(matchedOTP);
		        // Chuyển hướng đến trang xác nhận thành công
		    	System.out.println("Verify success");
		    	sessionService.set("finalEmail", verifyEmail);
		    	return "redirect:/login/resetPassword";
		    } else {
		        // Mã OTP không khớp, hiển thị thông báo lỗi
		        model.addAttribute("errorMessage2", "Mã OTP không chính xác!");
		        System.out.println("Mã OTP không chính xác!");
		        return "../layout/confirmOTP";
		    }
	}
	
	@GetMapping("/resetPassword")
	public String viewResetPassword(Model model) {
		String finalEmail = sessionService.get("finalEmail");
		return "../layout/resetPassword";
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(Model model,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("reNewPassword") String reNewPassword) {
		if(newPassword.equals("") || newPassword.isEmpty() || reNewPassword.equals("") || reNewPassword.isEmpty() ) {
			 model.addAttribute("errorMessage3", "Complete your new password...!");
		        return "../layout/resetPassword";
		}
		if(!newPassword.equals(reNewPassword)) {
			model.addAttribute("errorMessage3", "New password NOT match");
	        return "../layout/resetPassword";
		}
		String finalEmail = sessionService.get("finalEmail");
		Account account = accountRepository.findByEmail(finalEmail);
		account.setPassword(newPassword);
		accountRepository.save(account);
		sessionService.set("account", account);
		return "redirect:/home";
	}
	
}
