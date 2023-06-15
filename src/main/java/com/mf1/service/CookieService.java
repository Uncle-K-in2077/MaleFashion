package com.mf1.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	//Get cookie từ Request
	public Cookie get(String name) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals(name)) {
	                return cookie;
	            }
	        }
	    }
	    return null;
	}
	
	//Get giá trị Cookie từ Request
	public String getValue(String name) {
	    Cookie cookie = get(name);
	    if (cookie != null) {
	        return cookie.getValue();
	    }
	    return "";
	}
	
	//Tạo và gửi Cookie về client
	public Cookie add(String name, String value, int hours) {
	    Cookie cookie = new Cookie(name, value);
	    cookie.setMaxAge(hours * 60 * 60); // số giờ tính bằng giây
	    cookie.setPath("/");
	    response.addCookie(cookie);
	    return cookie;
	}
	
	//Phương thức remove(): Xóa cookie khỏi client
	public void remove(String name) {
	    Cookie cookie = new Cookie(name, "");
	    cookie.setMaxAge(0); 
	    //Thiết lập thời gian sống của cookie thành 0, đồng nghĩa với việc yêu cầu trình duyệt xóa cookie đó ngay lập tức.
	    cookie.setPath("/");
	    //Thiết lập path cho cookie, đảm bảo rằng cookie sẽ bị xóa ở tất cả các đường dẫn bao gồm cả root path (/).
	    response.addCookie(cookie);
	}
	
}
