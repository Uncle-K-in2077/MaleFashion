package com.mf1.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.mf1.dto.apiProductDTO;
import com.mf1.entities.Category;
import com.mf1.entities.Product;
import com.mf1.repository.CategoryRepository;
import com.mf1.repository.ProductRepository;

@Component
public class ProductConverter {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	public Product toEntity(apiProductDTO DTO) {
		
		Optional<Product> product = productRepository.findById(DTO.getId());
		Product item = new Product();
		item.setId(DTO.getId());
		if (product.isPresent()) {
			item = product.get();
		}

		Optional<Category> category = categoryRepository.findById(DTO.getCategoryId());

		if (category.isPresent()) {
			item.setCategory(category.get());
		}

		item.setName(DTO.getName());
		item.setAvailable(DTO.getAvailable());
		item.setCreatedAt(parseDate(DTO.getCreatedAt()));
		item.setDescrip(DTO.getDescription());
		item.setImage(DTO.getImage());
		item.setIssale(DTO.getIsSale());
		item.setPrice(DTO.getPrice());
		item.setStatus(DTO.getStatus());
		return item;
	
		
		
//		System.out.println(dto.getId());
//		Optional<Product> product = productRepository.findById(dto.getId());
//		Product entity = new Product();
//		
//		if (product.isPresent()) {
//			entity = product.get();
//		}
//
//		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
//
//		if (category.isPresent()) {
//			entity.setCategory(category.get());
//		}
//		
//        // Chuyển các trường từ dto sang entity
//        entity.setAvailable(dto.getAvailable());
//        entity.setCreatedAt(parseDate(dto.getCreatedAt()));
//        entity.setDescrip(dto.getDescription());
//        entity.setImage(dto.getImage());
//        entity.setIssale(dto.getIsSale());
//        entity.setName(dto.getName());
//        entity.setPrice(dto.getPrice());
//        entity.setStatus(dto.getStatus());
//        // Không chuyển đổi trường categoryName và categoryId vì chúng thuộc về Category, không thuộc về Product
//        return entity;
//		Product entity = new Product();
//		
//		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
//
//		if (category.isPresent()) {
//			entity.setCategory(category.get());
//		}
//
//        entity.setAvailable(dto.getAvailable());
//        entity.setCreatedAt(parseDate(dto.getCreatedAt()));
//        entity.setDescrip(dto.getDescription());
//        entity.setImage(dto.getImage());
//        entity.setIssale(dto.getIsSale());
//        entity.setName(dto.getName());
//        entity.setPrice(dto.getPrice());
//        entity.setStatus(dto.getStatus());
//        System.out.println(entity.getId());
//        return entity;
	}
	
	public apiProductDTO toDTO(Product entity) {
		apiProductDTO dto = new apiProductDTO();
        // Chuyển các trường từ entity sang dto
		
        dto.setId(entity.getId());
        System.out.println(dto.getId());
        
        dto.setAvailable(entity.getAvailable());
        dto.setCreatedAt(formatDate(entity.getCreatedAt()));
        dto.setDescription(entity.getDescrip());
        dto.setImage(entity.getImage());
        dto.setIsSale(entity.getIssale());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStatus(entity.getStatus());
        dto.setCategoryId(entity.getCategory().getId());
        // Không chuyển đổi trường category thuộc về Category, không thuộc về apiProductDTO
        return dto;
	}

	
	// Chuyển đổi kiểu Date thành String
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    // Chuyển đổi kiểu String thành Date
    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Xử lý ngoại lệ khi không thể chuyển đổi kiểu Date
            e.printStackTrace();
        }
        return null;
    }
}
