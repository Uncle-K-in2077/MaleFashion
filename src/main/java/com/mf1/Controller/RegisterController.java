package com.mf1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mf1.entities.Account;
import com.mf1.repository.AccountRepository;
import com.mf1.service.MailerService;
import com.mf1.service.SessionService;
import com.mf1.dto.CodeOTP;

@Controller
@RequestMapping("/signup")
public class RegisterController {
	
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
	MessageSource messageSource;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MailerService mailerService;
	
	@Autowired
	SessionService sessionService;
	
	@GetMapping("")
	public String signupview(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		return "../layout/guestSignUp";
	}
	
	@PostMapping("")
	public String save(Model model, @RequestParam("rePassword") String rePassword, @Valid @ModelAttribute("account") Account account, BindingResult result ) {
		if (result.hasErrors()) {
			try {
//				String errorMessage = messageSource.getMessage("error.save", null, Locale.getDefault());
//		        model.addAttribute("errorMessage", errorMessage);
//		        System.out.println("Error messages..............: " + errorMessage);
				
//				Nhiều khi code đéo hiểu nó chạy kiểu gì, không cài errorMessage nhưng phía jsp vẫn nhận. Ảo ma Canada
				
				model.addAttribute("account", account);
		        return "../layout/guestSignUp";
			} catch (Exception e) {
				System.out.println("Lỗi đăng ký");
			}
		}
//		Check mật khẩu
		if(!account.getPassword().equals(rePassword)) {
			model.addAttribute("errorMessage", "Mật khẩu xác nhận không khớp!");
			System.out.println("lỗi---------------------");
			return "../layout/guestSignUp";
		}
		
//		Check email
		Optional<Account> acc = Optional.ofNullable(accountRepository.findByEmail(account.getEmail()));
		if(acc.isPresent()) {
			model.addAttribute("errorMessage", "Email đã được sử dụng");
			System.out.println("Email already been used!");
			return "../layout/guestSignUp";
		}
		
//		GenerateCodeOtp
		String guestEmail = account.getEmail();
		CodeOTP newCode = new CodeOTP();
		newCode.setEmail(guestEmail);
		newCode.setCode(generateRandomString());
		listOTP.add(newCode);
		
		
		try {
			mailerService.send(guestEmail, "Wellcome MaleFashion", "Hi " + account.getFullName() + "\nYour OTP: " + newCode.getCode() + "");
		}catch (Exception e) {
			System.out.println("Email gửi không thành công");
		}
		sessionService.saveAccount(account);
		return "redirect:/signup/confirm";
		
	}
	
	
	@GetMapping("/confirm")
	public String showConfirmOTP(Model model) {
		Account account = sessionService.get("account");
	    System.out.println("Email lấy về ở UI là: " + account.getEmail());
		return "../layout/guestAfterSignUp";
	}
	
	@PostMapping("/confirm-otp")
	public String confirmOTP(Model model, @RequestParam("otp") String otp) {
	    // Kiểm tra mã OTP nhập vào có khớp với mã OTP đã gửi đi không
		
		Account account = sessionService.get("account");
		
		String confirmEmail = account.getEmail();
		System.out.println(otp);
		System.out.println(confirmEmail);
	    CodeOTP matchedOTP = null;
	    for (CodeOTP codeOTP : listOTP) {
	        if (codeOTP.getEmail().equals(confirmEmail) && codeOTP.getCode().equals(otp)) {
	            matchedOTP = codeOTP;
	            System.out.println("Đã xác nhận được email");
	            break;
	        }
	    }
	    
	    if (matchedOTP != null) {
			account.setUsername(account.getUsername());
			account.setEmail(account.getEmail());
			account.setPassword(account.getPassword());
			
			accountRepository.save(account);
			sessionService.set("account", account);
			System.out.println("SignUp succsess!");
	    	listOTP.remove(matchedOTP);
	    	
	        // Chuyển hướng đến trang xác nhận thành công
	    	return "redirect:/home";
	    } else {
	        // Mã OTP không khớp, hiển thị thông báo lỗi
	        model.addAttribute("errorMessage", "Mã OTP không chính xác!");
	        return "../layout/guestAfterSignUp";
	    }
	}

}
