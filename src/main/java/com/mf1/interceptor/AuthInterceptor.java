package com.mf1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mf1.entities.Account;
import com.mf1.service.SessionService;

@Service
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	SessionService session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		Account user = session.get("account"); // lấy từ session

		if (user == null) { // chưa đăng nhập
			session.set("security-uri", uri);
			response.sendRedirect("/login?error=Please-login!");
			return false;
		}
		if (user != null) {
			if (user.getAdmin() != 1) {
				response.sendRedirect("/login?error=Access-denied!");
				return false;
			}
		}

		return true;

	}
}
