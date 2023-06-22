package com.mf1.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mf1.entities.Order;
import com.mf1.entities.OrderDetail;
import com.mf1.repository.AccountRepository;
import com.mf1.repository.OrderRepository;

@Controller
@RequestMapping("admin/order")
public class Admin_OrderController {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping()
	public String index(Model model) {
		List<Order> allOrderList = orderRepository.findAll();
		model.addAttribute("allOrderList", allOrderList);
		model.addAttribute("currentLine", "order");
		return "admin-order";
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
		return "admin-order-detail";
	}
}
