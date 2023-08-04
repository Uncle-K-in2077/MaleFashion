package com.mf1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class apiProductDTO {
	private int id;
    private String name;
    private String image;
    private double price;
    private int available;
    private String description;
    private String categoryName;
    private int categoryId;
    private String createdAt;
    private int status;
    private int isSale;
}
