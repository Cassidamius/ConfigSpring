package com.spring.config.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.config.model.Role;
import com.spring.config.model.UserInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.RoleService;
import com.spring.config.service.UserInfoService;
import com.spring.config.util.MD5Util;

@Controller
@Scope("prototype")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/getUserInfoById/{id}", method = RequestMethod.GET)
	public String getUserInfoById(@PathVariable Integer id, Model model) {
		model.addAttribute("userInfo", userInfoService.get(id));
		return "toEditUserPage";
	}

	@RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	public String addUserInfo(@RequestBody UserInfo userInfo, Model model) {
		userInfo.setDeleteFlag(1);
		String salt = UUID.randomUUID().toString();
		userInfo.setSalt(salt);
		userInfo.setPassword(MD5Util.MD5(userInfo.getPassword() + "{" + salt + "}"));
		
		userInfoService.save(userInfo);
		model.addAttribute("userInfo", userInfo);
		return "userList";
	}

	@RequestMapping(value = "/toAddUserPage")
	public String toAddUserPage(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		List<Role> roles = roleService.list();
		model.addAttribute("roles", roles);
		return "toAddUserPage";
	}
	
	@RequestMapping(value = "/toEditUserPage")
	public String toEditUserPage( @RequestParam("id") Integer id, Model model) {
		UserInfo userInfo = userInfoService.get(id);
		model.addAttribute("userInfo", userInfo);
		return "toEditUserPage";
	}
	
	@RequestMapping(value = "/deleteUser")
	public String deleteUser(@RequestParam("id") Integer id, Model model) {
		userInfoService.delete(id);
		model.addAttribute("pageResultSet", new PageResultSet<UserInfo>());
		return "userList";
	}
	
	@RequestMapping(value = "/toUserListPage")
	public String toUserListPage(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		model.addAttribute("pageResultSet", new PageResultSet<UserInfo>());
		return "userList";
	}

	@RequestMapping(value = "/editUserInfo", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String editUser(@RequestHeader("Accept-Encoding") String encoding, HttpServletRequest request, UserInfo userInfo, Model model) throws UnsupportedEncodingException {
		List<Role> roleList = roleService.getRoleSetByUserId(userInfo.getId());
		userInfo.setRoles(roleList);
		userInfoService.updateUserInfo(userInfo);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("pageResultSet", new PageResultSet<UserInfo>());
		return "userList";
	}
	
	@RequestMapping(value = "/findUserList")
	public String findUserList(UserInfo userInfo, PageResultSet<UserInfo> pageResultSet, Model model) {
		if (pageResultSet == null) {
			pageResultSet = new PageResultSet<UserInfo>();
		}
		model.addAttribute("userInfo", userInfo);
		pageResultSet = userInfoService.queryForPage(userInfo, pageResultSet.getPageInfo().getPageSize(), pageResultSet
		        .getPageInfo().getCurrentPage());
		model.addAttribute("pageResultSet", pageResultSet);
		return "userList";
	}

}
