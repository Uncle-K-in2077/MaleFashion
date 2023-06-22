package com.mf1.interceptor;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class InterConfig implements WebMvcConfigurer {
	@Autowired
	GlobalInterceptor global;

	@Autowired
	AuthInterceptor auth;
	
	@Autowired
	CheckAccountInterceptor check;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(global).addPathPatterns("/**").excludePathPatterns("/assets/**");
		
		registry.addInterceptor(check).addPathPatterns("/cart/**", "/checkOut/**").excludePathPatterns();

		registry.addInterceptor(auth).addPathPatterns("/account/edit", "/account/chgpwd", "/order/**", "/admin/**")
				.excludePathPatterns("/assets/**", "/admin/home/index");

		LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
		locale.setParamName("lang");
		registry.addInterceptor(locale).addPathPatterns("/**");
	}


//	@Bean("localeResolver")
//	public LocaleResolver getLocaleResolver() {
//		CookieLocaleResolver resolver = new CookieLocaleResolver();
//		resolver.setDefaultLocale(new Locale("vi"));
//		resolver.setCookieMaxAge(10 * 24 * 60 * 60); // 10 ngày
//		resolver.setCookiePath("/");
//		return  resolver;
//	}

}
