package com.spring.config.initializer;

import java.util.EnumSet;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.Log4jConfigListener;

/**
 * Description: <web.xml通用设置>. <br>
 * <p>
 * <配置相关Listener，servlet，filter等等>
 * </p>
 * Makedate:2014年8月18日 下午5:34:08
 * 
 * @author Administrator
 * @version V1.0
 */
@Order(1)
public class CommonInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// Log4jConfigListener
		// servletContext.setInitParameter("log4jConfigLocation",
		// "classpath:config/properties/log4j.properties");
		servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.xml");
		servletContext.addListener(Log4jConfigListener.class);

		Dynamic dynamic = servletContext.addFilter("encoding", CharacterEncodingFilter.class);
		dynamic.setInitParameter("encoding", "UTF-8");
		dynamic.setInitParameter("forceEncoding", "true");
		dynamic.addMappingForUrlPatterns(
		        EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
		
	}

}
