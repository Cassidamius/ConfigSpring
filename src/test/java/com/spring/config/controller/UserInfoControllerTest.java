package com.spring.config.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.spring.config.AbsWebAppContextSetupTest;

public class UserInfoControllerTest extends AbsWebAppContextSetupTest {

	@Test
	public void getByIdTest() throws Exception {

		// 执行请求
		mockMvc.perform(get("/getUserInfo/1").accept(MediaType.ALL)).andExpect(status().isOk())
		        .andExpect(model().attributeExists(new String[] { "userInfo" }));

	}

}
