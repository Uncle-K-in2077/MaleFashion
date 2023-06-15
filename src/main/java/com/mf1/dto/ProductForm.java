package com.mf1.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
	private String name;
    private MultipartFile  image;
    private double price;
    private int categoryId;
    private int availble;
    private String descrip;
    private int hiddenStatus;
    private int hiddenIsSale;

    public int getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(int hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }
    
    public int getHiddenIsSale() {
    	return hiddenIsSale;
    }
    
    public void setHiddenIsSale(int hiddenIsSale) {
    	this.hiddenIsSale = hiddenIsSale;
    }

    // Các getter và setter cho các thuộc tính
    
    public int getAvailble() {
		return availble;
	}
    
    public void setAvailble(int availble) {
    	this.availble = availble;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile  getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
