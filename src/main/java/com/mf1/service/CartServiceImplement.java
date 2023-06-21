package com.mf1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mf1.dto.CartElement;
import com.mf1.entities.Product;
import com.mf1.repository.ProductRepository;

@Service
public class CartServiceImplement implements CartService{
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductRepository productRepository;
	
	public CartElement toCartElement(Product product) {
		CartElement element = new CartElement();
		element.setId(product.getId());
		element.setImage(product.getImage());
		element.setName(product.getName());
		element.setPrice(product.getPrice());
		return element;
	}

	public CartElement getCartElement(int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return toCartElement(product.get());
		}
		return null;
	}

	@Override
	public void add(int id) {
		List<CartElement> list = getList();

		for (CartElement CartElement : list) {
			if (CartElement.getId() == id) {
				CartElement.setQuantity(CartElement.getQuantity() + 1);
				return;
			}
		}
		list.add(getCartElement(id));
		sessionService.set("ShoppingCart", list);
	}

	@Override
	public void remove(int id) {
		List<CartElement> list = sessionService.get("ShoppingCart");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.get(i).setQuantity(1);
				list.remove(i);
				return;
			}
		}
	}

	@Override
	public void update(int id, int quantity) {
		List<CartElement> list = sessionService.get("ShoppingCart");
		if (quantity < 1) {
			remove(id);
			return;
		}
		for (CartElement CartElement : list) {
			if (CartElement.getId() == id) {
				CartElement.setQuantity(quantity);
			}
		}

	}

	@Override
	public void clear() {
		List<CartElement> list = sessionService.get("ShoppingCart");
		for (CartElement CartElement : list) {
			CartElement.setQuantity(1);
		}
		list.clear();
		sessionService.set("ShoppingCart", list);
	}

	@Override
	public int getCount() {
		List<CartElement> list = sessionService.get("ShoppingCart");
		int count = 0;
		if (list != null) {
			for (CartElement CartElement : list) {
				count += CartElement.getQuantity();
			}
		}

		return count;
	}

	@Override
	public double getAmount() {
		List<CartElement> list = sessionService.get("ShoppingCart");
		double amount = 0;
		if (list != null) {
			for (CartElement CartElement : list) {
				amount += CartElement.getQuantity() * CartElement.getPrice();
			}
		}
		return amount;
	}

	@Override
	public List<CartElement> getList() {
		List<CartElement> list = sessionService.get("ShoppingCart");
		if (list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
}
