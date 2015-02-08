package com.spring.config.initializer;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 所有实现了WebApplicationInitializer接口的类都会在容器启动时自动被加载运行，用@Order注解设定加载顺序
 * 这是servlet3.0+后加入的特性，web.xml中可以不需要配置内容，都硬编码到WebApplicationInitializer的实现类中
 */
@Order(3)
// spring DispatcherServlet的配置,其它servlet和监听器等需要额外声明，用@Order注解设定启动顺序
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 应用上下文，除web部分
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class, AppConfig.class };
	}

	/**
	 * DispatcherServlet的映射路径
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
