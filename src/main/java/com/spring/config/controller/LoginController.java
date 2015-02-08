package com.spring.config.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.config.model.Role;
import com.spring.config.model.UserInfo;
import com.spring.config.service.RoleService;
import com.spring.config.service.UserInfoService;

@Controller
public class LoginController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoleService roleService;
		
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLoginPage(Model model) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("user");
		userInfo.setPassword("user");
		model.addAttribute("userInfo", userInfo);
		return "login";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpSession session, Model model) {
		SecurityContext sc = SecurityContextHolder.getContext();
		User user = (User)sc.getAuthentication().getPrincipal();
		UserInfo ui = userInfoService.getUserInfo(user.getUsername());
		List<Role> roles = ui.getRoles();
		List<Role> temp = new ArrayList<Role>();
		for (Role role : roles) {
			temp.add(roleService.getRole(role.getId()));
		}
		ui.setRoles(temp);
//		model.addAttribute("userInfo", ui);
		session.setAttribute("sessionUserInfo", ui);
		return "welcome";
	}
	
//	@RequestMapping("/logout")
//	public String logout() {
//		return "forward:login";
//    }

}
