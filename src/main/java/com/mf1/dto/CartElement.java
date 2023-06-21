package com.mf1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartElement {
	int id;
	String name;
	String image;
	double price;
	int quantity = 1;
}
