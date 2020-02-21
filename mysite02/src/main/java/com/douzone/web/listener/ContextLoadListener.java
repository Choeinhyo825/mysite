package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// context(문맥, 어떤 정보를 가지고있는 컨데이너)
// context = Application에대한 정보, 컨테이너(tomcat)의 클래스(servlet)들이 Application이 어떻게 실행되어야 하는가에 대한 정보
// Tomcat에서 context : 웹 어플리케이션은 서블릿들이 구성하고 있다.
// spring에서 context : 비즈니스 클래스(Controller, Service, Repository)

public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String contextConfigLocation = context.getInitParameter("contextConfigLocation");
//		System.out.println("Application Starts..." + contextConfigLocation);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

}
