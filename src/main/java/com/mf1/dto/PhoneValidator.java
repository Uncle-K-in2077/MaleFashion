package com.mf1.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PhoneValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String phone = (String) target;
        // Kiểm tra điều kiện của trường phone và thêm lỗi nếu có
        if (!phone.matches("[0-9]{10}")) {
            errors.rejectValue("phone", "Invalid phone number");
        }
    }
}