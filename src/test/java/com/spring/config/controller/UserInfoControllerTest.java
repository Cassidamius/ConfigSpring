package com.spring.config.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.spring.config.AbsWebAppContextSetupTest;

public class UserInfoControllerTest extends AbsWebAppContextSetupTest {

	@Test
	public void getByIdTest() throws Exception {
		// 执行请求
		mockMvc.perform(get("/getUserInfoById/{id}", 1).accept(MediaType.ALL)).andExpect(status().isOk())
		        .andExpect(model().attributeExists(new String[] { "userInfo" }));
	}

	@Test
	public void addUserInfoTest() throws Exception {
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(1);
		ResultActions result = mockMvc.perform(
		        post("/addUserInfo").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("userName", "Mike006")
		                .param("password", "111").param("roleIds", new String[] { "1" })).andExpect(status().isOk());
		MvcResult mvcResult = result.andReturn();
		boolean b = mvcResult.getModelAndView().getViewName().contains("forward:toUserListPage");
		Assert.assertTrue(b);
	}

}
