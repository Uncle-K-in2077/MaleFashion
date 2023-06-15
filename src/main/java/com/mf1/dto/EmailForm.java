package com.mf1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailForm {
	private String to;
    private String subject;
    private String body;
}
