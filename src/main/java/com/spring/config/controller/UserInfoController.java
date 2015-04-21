package com.spring.config.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	// @RequestMapping(value = "/getUserInfoById/{id}", method =
	// RequestMethod.GET)
	// public String getUserInfoById(@PathVariable Integer id, Model model) {
	// return "forward:toEditUserPage/" + id;
	// }

	@RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	public String addUserInfo(UserInfo userInfo/* , Model model */) {
		userInfo.setDeleteFlag(1);
		String salt = UUID.randomUUID().toString();
		userInfo.setSalt(salt);
		List<Role> roles = new ArrayList<Role>();
		for (Integer id : userInfo.getRoleIds()) {
			roles.add(roleService.get(id));
		}
		userInfo.setRoles(roles);
		userInfo.setPassword(MD5Util.MD5(userInfo.getUserName() + "{" + salt + "}"));
		userInfoService.save(userInfo);
		// model.addAttribute("userInfo", userInfo);
		return "forward:toUserListPage";
	}

	@RequestMapping(value = "/toAddUserPage")
	public String toAddUserPage(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		List<Role> roles = roleService.list();
		model.addAttribute("roles", roles);
		return "toAddUserPage";
	}

	@RequestMapping(value = "/toEditUserPage/{id}")
	public String toEditUserPage(@PathVariable Integer id, Model model) {
		UserInfo userInfo = userInfoService.get(id);
		model.addAttribute("userInfo", userInfo);
		
		List<Role> roles = roleService.getRoleSetByUserId(id);
		List<Role> allRoles = roleService.list();
		Map<Role, Integer> rolesMap = new HashMap<Role, Integer>();
		for (Role role : allRoles) {
			for (Role r : roles) {
				if (r.getId() == role.getId()) {
					rolesMap.put(role, 1);
					break;
				}
			}
			if (!rolesMap.containsKey(role)) {
				rolesMap.put(role, 0);
			}
		}
		model.addAttribute("rolesMap", rolesMap);
		return "toEditUserPage";
	}

	@RequestMapping(value = "/deleteUser/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletResponse res) {
		userInfoService.deleteLogic(id);
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.print("{\"result\" : \"success\"}");
		pw.flush();
		pw.close();
	}

	@RequestMapping(value = "/toUserListPage")
	public String toUserListPage(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		model.addAttribute("pageResultSet", new PageResultSet<UserInfo>());
		return "userList";
	}

	@RequestMapping(value = "/editUserInfo", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String editUser(UserInfo userInfo) throws UnsupportedEncodingException {
		List<Role> roleList = roleService.getRoleListByRoleIds(userInfo.getRoleIds());
		userInfo.setRoles(roleList);
		userInfoService.updateUserInfo(userInfo);
		return "forward:toUserListPage";
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
