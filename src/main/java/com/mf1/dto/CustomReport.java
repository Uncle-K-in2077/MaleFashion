package com.mf1.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomReport {
	@Id
	Serializable group;

	String name;

	String image;

	Double price;

	Long count;

	Double amount;
}
