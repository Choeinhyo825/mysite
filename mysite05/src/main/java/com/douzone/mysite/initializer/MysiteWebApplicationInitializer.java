package com.douzone.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// Servlet Mapping
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// Filters
		return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// Create DispztcherServlet
		DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
		// Exception Handler가 없으면 exception을 던짐
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
	}
	//에러페이지도 추가

}
