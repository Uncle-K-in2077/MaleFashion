package com.mf1.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mf1.dto.ProductForm;
import com.mf1.entities.Category;
import com.mf1.entities.Product;
import com.mf1.repository.ProductRepository;
import com.mf1.service.CategoryService;
import com.mf1.service.ProductService;
import com.mf1.service.UploadService;

@Controller
@RequestMapping("/admin/product")
public class Admin_ProductController {
	
	@Autowired
	ProductRepository productReponsitory;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UploadService uploadService;
	
	@GetMapping()
	public String getAll(Model model) {
		List<Product> productList = productService.getAllProducts();
		List<Category> categoryList = categoryService.getAllCategories();
		request.getSession().setAttribute("productList", productList);
		request.getSession().setAttribute("categoryList", categoryList);
		return "admin-index";
	}
	
	@PostMapping("add")
	public String AddProduct(@ModelAttribute("productForm") ProductForm productForm,
			@RequestParam("categoryId") int categoryId, @RequestParam("imageFile") MultipartFile imageFile) {
		Product newProduct = new Product();
		newProduct.setName(productForm.getName());
		newProduct.setPrice(productForm.getPrice());
		newProduct.setCreatedAt(new Date());
		newProduct.setAvailable(productForm.getAvailble());
		newProduct.setStatus(1);

		try {
			String filename = uploadService.save(imageFile);
			newProduct.setImage(filename);
			 System.out.println("Link ảnh: " + newProduct.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Lấy danh mục (category) từ productForm hoặc từ CSDL (nếu category được chọn
		// từ dropdown)
		Category category = categoryService.getCategoryById(categoryId);
		newProduct.setCategory(category);

		// Lưu sản phẩm mới vào CSDL
		productService.saveProduct(newProduct);

		// Redirect hoặc trả về trang thành công
		return "redirect:/admin/product";

	}
	
	@GetMapping("getByID")
	public String GetByID(@RequestParam("id") int id) {
		Product findProd = productService.getProductById(id);
		request.getSession().setAttribute("findProd", findProd);
		return "admin-product-detail";
	}
	
	@PostMapping("update")
	public String UpdateProduct(RedirectAttributes redirectAttributes, @ModelAttribute("productForm") ProductForm productForm,
			@RequestParam("categoryId") int categoryId, @RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("idProduct") int idProduct, @RequestParam("oldImage") String oldImage) {
		System.out.println("update OK");

		Product UdProduct = productService.getProductById(idProduct);

		String newName = productForm.getName();
		int newAvailble = productForm.getAvailble();
		double newPrice = productForm.getPrice();
		int hiddenStatus = productForm.getHiddenStatus();
		int hiddenIsSale = productForm.getHiddenIsSale();
//		System.err.println(hiddenIsSale);
        String descrip = productForm.getDescrip();

		if (newName == null || newName.trim().isEmpty() || newAvailble < 0 || newPrice <= 0) {
			String errorMessage = "Invalid Input";
			redirectAttributes.addFlashAttribute("errorMsg", errorMessage);
			System.out.println(errorMessage);
			return "redirect:getByID?id=" + idProduct;
		}
		try {
			String filename = uploadService.save(imageFile);
			UdProduct.setImage(filename);
		} catch (Exception e) {
			UdProduct.setImage(oldImage);
		}

		UdProduct.setName(newName);
//		System.out.println(UdProduct.getName());
//		System.out.println(UdProduct.getImage());
		UdProduct.setAvailable(newAvailble);
		UdProduct.setPrice(newPrice);
		UdProduct.setStatus(hiddenStatus);
		UdProduct.setIssale(hiddenIsSale);
		UdProduct.setDescrip(descrip);
		Category category = categoryService.getCategoryById(categoryId);
		UdProduct.setCategory(category);

		productService.saveProduct(UdProduct);

		return "redirect:getByID?id=" + idProduct;
	}
}
