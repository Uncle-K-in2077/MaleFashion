package com.mf1.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class I18NConfig implements WebMvcConfigurer {
	@Bean("MessageSource")
	public MessageSource getI18nMessageSource() {
	    ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
	    ms.setBasenames("classpath:i18n/lang");
	    ms.setDefaultEncoding("utf-8");
	    return ms;
	}

}
