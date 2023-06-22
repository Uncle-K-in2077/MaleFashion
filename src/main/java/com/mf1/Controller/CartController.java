package com.mf1.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.dto.CartElement;
import com.mf1.entities.Account;
import com.mf1.entities.Order;
import com.mf1.entities.OrderDetail;
import com.mf1.entities.Product;
import com.mf1.repository.OrderRepository;
import com.mf1.repository.ProductRepository;
import com.mf1.repository.orderDetailRepository;
import com.mf1.service.CartService;
import com.mf1.service.MailerService;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	orderDetailRepository orderDetailRepository;
	
	@Autowired
	MailerService mailerService;
	
	@GetMapping()
	public String index(Model model) {
		
		List<CartElement> cartItems = cartService.getList();
		int itemCount = cartService.getCount();
		double totalAmount = cartService.getAmount();
		    
		sessionService.set("cartItems", cartItems);
		sessionService.set("itemCount", itemCount);
		sessionService.set("totalAmount", totalAmount);
		
		model.addAttribute("currentPage", "shop");
		return "shoppingCart";
	}
	
	@GetMapping("/add")
	public String addToCart(@RequestParam("productId") int productId) {
		
	    cartService.add(productId);
	    
	    List<CartElement> cartItems = cartService.getList();
	    sessionService.set("cartItems", cartItems);
	    return "redirect:/cart";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, @RequestParam int quantity) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			cartService.update(id, quantity);
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			cartService.remove(id);
		}
		return "redirect:/cart";
	}
}
