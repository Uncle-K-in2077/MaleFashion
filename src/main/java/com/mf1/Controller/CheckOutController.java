package com.mf1.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.dto.CartElement;
import com.mf1.dto.PhoneValidator;
import com.mf1.entities.Account;
import com.mf1.entities.Order;
import com.mf1.entities.OrderDetail;
import com.mf1.entities.Product;
import com.mf1.repository.OrderRepository;
import com.mf1.repository.ProductRepository;
import com.mf1.repository.orderDetailRepository;
import com.mf1.service.AccountService;
import com.mf1.service.CartService;
import com.mf1.service.MailerService;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/checkOut")
public class CheckOutController {
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
	
	@Autowired
	AccountService accountService;
	
	private final PhoneValidator phoneValidator;

    @Autowired
    public CheckOutController(PhoneValidator phoneValidator) {
        this.phoneValidator = phoneValidator;
    }
	
	
	@GetMapping()
	public String checkOutView(Model model) {
		List<CartElement> cartItems = cartService.getList();
		int itemCount = cartService.getCount();
		double totalAmount = cartService.getAmount();
		Account account = sessionService.get("account");
		sessionService.set("account", account);
		sessionService.set("cartItems", cartItems);
		sessionService.set("itemCount", itemCount);
		sessionService.set("totalAmount", totalAmount);
		model.addAttribute("currentPage", "shop");
		
		Order order = new Order();
		model.addAttribute("order", order);
		return "shoppingCart-checkOut";
	}
	
	@PostMapping()
	public String create(Model model,@RequestParam(value = "order_note", defaultValue = "") String note,
			@Valid @ModelAttribute("order") Order order,
            BindingResult bindingResult) {
		
        // Kiểm tra lỗi
        if (bindingResult.hasErrors()) {
            sessionService.set("CheckOutMessage", "Invalid phone number");
            System.out.println("Lỗi Validate SDT");
            model.addAttribute("order", order);
            return "shoppingCart-checkOut";
        }
	    
	   
		try {
			Account account = sessionService.get("account");
//			Order order = new Order();
			order.setAccount(account);
			order.setCreatedAt(new Date());
			order.setNote(note);
			order.setAmount(cartService.getAmount());
			order.setStatus(0);
			order.setPhone(order.getPhone());
			order.setAddress(order.getAddress());
			Order newOrder = orderRepository.save(order);

			List<CartElement> listCartElement = cartService.getList();
			List<OrderDetail> listOrderDetail = new ArrayList<>();
			if (listCartElement.size() > 0) {
				for (CartElement item : listCartElement) {
					OrderDetail o = new OrderDetail();
					Product product = productRepository.findById(item.getId()).get();

					o.setProduct(product);
					o.setOrder(newOrder);
					o.setQuantity(item.getQuantity());
					o.setPrice(product.getPrice());
					o.setStatus(1);
					listOrderDetail.add(o);
				}
				orderDetailRepository.saveAll(listOrderDetail);
			}
			mailerService.send(account.getEmail(), "Male-Fashion" , "Thank for your Order: " + newOrder.getAmount());
			cartService.clear();
		} catch (Exception e) {
			System.out.println("Lỗi tạo đơn hàng :" + e.getMessage());
		}

		return "redirect:/home";
		
	}
	
	@GetMapping("/{userId}")
	public String getOrdersByUserId(@PathVariable int userId, Model model) {
	    Account account = accountService.getAccountById(userId);
	    if (account == null) {
	        // Xử lý khi người dùng không tồn tại
	        return "error-page"; // Điều hướng đến trang lỗi hoặc trang thông báo không tìm thấy người dùng
	    }
	    
	    List<Order> orders = orderRepository.findByAccount(account, null);
	    
	    model.addAttribute("account", account);
	    model.addAttribute("orders", orders);
	    
	    return "user-order"; // Điều hướng đến trang hiển thị danh sách đơn hàng của người dùng
	}
	
	@GetMapping("/detail/{orderId}")
	public String getOrderById(@PathVariable int orderId, Model model) {
	    Order order = orderRepository.findById(orderId);
	    if (order == null) {
	        // Xử lý khi không tìm thấy đơn hàng
	        return "error-page"; // Điều hướng đến trang lỗi hoặc trang thông báo không tìm thấy đơn hàng
	    }
	    
	    List<OrderDetail> orderDetails = order.getOrderDetails(); // Lấy danh sách OrderDetail từ Order
	    
	    model.addAttribute("order", order);
	    model.addAttribute("orderDetails", orderDetails);
	    
	    return "user-order-detail"; // Điều hướng đến trang hiển thị chi tiết đơn hàng
	}
	
}
