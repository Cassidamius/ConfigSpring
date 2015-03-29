package com.spring.config.initializer;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(3)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

//	public SecurityWebApplicationInitializer() {
//		super(SpringSecurityConfig.class);
//	}
	
	@Override
	protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
	
}
