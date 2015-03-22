package com.spring.config.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
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

	@RequestMapping(value = "/toLoginPage", method = RequestMethod.GET)
	public String toLoginPage(Model model) {
		UserInfo userInfo = new UserInfo();
		model.addAttribute("userInfo", userInfo);
		return "login";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(HttpServletRequest request, UserInfo userInfo, Model model) {
		HttpSession session = request.getSession(true);
		AuthenticationException exception = (AuthenticationException) session
		        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (exception != null) {
			model.addAttribute("error", exception.getMessage());
		}
		return "login";
	}

	@RequestMapping("/welcome")
	public String welcome(HttpSession session, Model model) {
		SecurityContext sc = SecurityContextHolder.getContext();
		UserInfo user = (UserInfo) sc.getAuthentication().getPrincipal();
		List<Role> roles = user.getRoles();
		List<Role> temp = new ArrayList<Role>();
		for (Role role : roles) {
			temp.add(roleService.getRole(role.getId()));
		}
		user.setRoles(temp);
		session.setAttribute("sessionUserInfo", user);
		return "welcome";
	}

}
