package com.spring.config.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.google.gson.Gson;
import com.spring.config.AbsWebAppContextSetupTest;
import com.spring.config.model.UserInfo;

public class UserInfoControllerTest extends AbsWebAppContextSetupTest {

	@Test
	public void getByIdTest() throws Exception {
		// 执行请求
		mockMvc.perform(get("/getUserInfoById/{id}", 1).accept(MediaType.ALL)).andExpect(status().isOk())
		        .andExpect(model().attributeExists(new String[] { "userInfo" }));

	}

	@Test
	public void addUserInfoTest() throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("Mike");
		userInfo.setPassword("Mike");
		Integer[] roleids = new Integer[] { 1 };

		List<Object> list = new ArrayList<Object>();
		list.add(userInfo);
		list.add(roleids);
		// 执行请求
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);

		mockMvc.perform(post("/addUserInfo"));

	}

}
