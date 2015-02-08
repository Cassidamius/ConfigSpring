package com.spring.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.config.model.Role;
import com.spring.config.model.UserInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.RoleService;
import com.spring.config.service.UserInfoService;

@Controller
@Scope("prototype")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public String getUserInfo(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("userInfo", userInfoService.get(id));
		return "toEditUserPage";
	}

	@RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	public String addUserInfo(UserInfo userInfo, Model model) {
		userInfo.setDeleteFlag(1);
		userInfoService.save(userInfo);
		model.addAttribute("userInfo", userInfo);
		return "toAddUserPage";
	}

	@RequestMapping(value = "/toAddUserPage")
	public String toAddUserPage(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "toAddUserPage";
	}

	@RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
	public String editUser(UserInfo userInfo, Model model) {
		List<Role> roleList = roleService.getRoleSetByUserId(userInfo.getId());
		userInfo.setRoles(roleList);
		userInfoService.update(userInfo);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("pageResultSet", new PageResultSet<UserInfo>());
		return "userList";
	}

	@RequestMapping(value = "/findUserList", method = RequestMethod.POST)
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
