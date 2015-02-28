package com.spring.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.spring.config.common.SessionStore;

public class ContextFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
		SessionStore.setWebSession(((HttpServletRequest)request).getSession());
		chain.doFilter(request, response);
    }

	@Override
    public void destroy() {
	    // TODO Auto-generated method stub
	    
    }

}
