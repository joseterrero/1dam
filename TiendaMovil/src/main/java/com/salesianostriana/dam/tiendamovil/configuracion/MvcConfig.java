package com.salesianostriana.dam.tiendamovil.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login");
		registry.addViewController("/acceso-denegado");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:files/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:static/img/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/");
	}

}