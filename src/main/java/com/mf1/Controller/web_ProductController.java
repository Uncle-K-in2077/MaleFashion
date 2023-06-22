package com.mf1.Controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.entities.Category;
import com.mf1.entities.Product;
import com.mf1.repository.CategoryRepository;
import com.mf1.repository.ProductRepository;
import com.mf1.service.CategoryService;
import com.mf1.service.ProductService;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/shop")
public class web_ProductController {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	SessionService sessionService;

	@GetMapping()
	public String getAllProduct(Model model,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "6") int limit,
			@RequestParam(value = "category_id", defaultValue = "0") int categoryID,
			@RequestParam(value = "order_by", defaultValue = "DESC") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "price") String sortBy,
			@RequestParam(value = "range", defaultValue = "dưới 5 triệu") String rangeOption) {
		
		if (page < 0) {
			page = 0;
		}
		
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;
		
		switch (rangeOption) {
		case "0-5":
			// Dưới 5 triệu
			min = 0;
			max = 4999999;
			break;
		case "5-10":
			// 5 - 10 triệu
			min = 5000000;
			max = 10000000;
			break;
		case "10-15":
			// 10 - 15 triệu
			min = 10000000;
			max = 15000000;
			break;
		case "15-20":
			// 15 - 20 triệu
			min = 15000000;
			max = 20000000;
			break;
		case "20-25":
			// 20tr - 25tr
			min = 20000000;
			max = 25000000;
			break;
		case "25+":
			// over 25tr
			min = 25000000;
			max = Double.MAX_VALUE;
			break;
		case "0":
			// All range
			min = Double.MIN_VALUE;
			max = Double.MAX_VALUE;
			break;
		default:
			break;
		}
		
		// Lấy Category
		Optional<Category> category = categoryRepository.findById(categoryID);

		Pageable pageable = PageRequest.of(page, limit,
				Sort.by(orderBy.equals("DESC") ? Direction.DESC : Direction.ASC, sortBy));

		Page<Product> pageProduct = null;
		if (category.isEmpty()) {
			// Lấy tất cả các product theo tất cả category
			pageProduct = productRepository.findByNameContainingAndStatusAndPriceBetween(
					keyword, 1, min, max, pageable);
		}else {
			// Lấy product theo riêng category
			pageProduct = productRepository.findByCategoryAndNameContainingAndStatusAndPriceBetween(
					category.get(), keyword, 1, min, max, pageable);
		}
		
		List<Category> listCategory = categoryRepository.findAll();
		sessionService.set("listCategory", listCategory);
		sessionService.set("pageProduct", pageProduct);
		model.addAttribute("currentPage", "shop");
		
		return "shoppage";
	}
	
	@GetMapping("/{id}")
	public String getProduct(@PathVariable("id") Optional<Integer> id) {
		if (id.isEmpty()) {
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Optional<Product> product = productRepository.findById(id.get());
		if (product.isEmpty()) {
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		sessionService.set("product", product.get());
		return "shoppage-detail";
	}
	
}
