package com.mf1.Controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mf1.entities.Category;
import com.mf1.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class Admin_CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping()
	public String index(Model model) {
		List<Category> categoryList = categoryService.getAllCategories();
		request.getSession().setAttribute("categoryList", categoryList);
		model.addAttribute("currentLine", "category");
		return "admin-category";
	}
}
