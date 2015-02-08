package com.spring.config.service.impl;

import com.spring.config.service.MyService;

public class MyServiceImpl implements MyService {
	
	public void doStuff() {
		System.out.println(" do stuff..." + this);
	}
	
	public void init() {
		System.out.println("call init method...");
	}
	
	public void cleanUp() {
		System.out.println("call destroy method...");
	}

}
