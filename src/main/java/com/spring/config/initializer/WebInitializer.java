package com.spring.config.initializer;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spring.config.filter.ContextFilter;

/**
 * 所有实现了WebApplicationInitializer接口的类都会在容器启动时自动被加载运行，用@Order注解设定加载顺序
 * 这是servlet3.0+后加入的特性，web.xml中可以不需要配置内容，都硬编码到WebApplicationInitializer的实现类中
 */
@Order(2)
// spring DispatcherServlet的配置,其它servlet和监听器等需要额外声明，用@Order注解设定启动顺序
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 应用上下文，除web部分
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, SpringSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class};
	}

	/**
	 * DispatcherServlet的映射路径
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		
//		CharacterEncodingFilter cef = new CharacterEncodingFilter();
//		cef.setEncoding("UTF-8");
//		cef.setForceEncoding(true);
		// sessionStore
		ContextFilter cf = new ContextFilter();
		// OpenSessionInViewFilter
//		OpenSessionInViewFilter osivf = new OpenSessionInViewFilter();
//		osivf.setSessionFactoryBeanName("sessionFactory");
		Filter[] filters = new Filter[] { /*cef,*/ cf/*, osivf*/ };
		return filters;
	}

}
