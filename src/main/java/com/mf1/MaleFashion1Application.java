package com.mf1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class MaleFashion1Application {

	public static void main(String[] args) {
		SpringApplication.run(MaleFashion1Application.class, args);
	}

	
	@GetMapping
	public String index() {
		return "redirect:/home";
	}
}
