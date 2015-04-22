package com.spring.config.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.config.model.Role;
import com.spring.config.model.UserInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.RoleService;
import com.spring.config.service.UserInfoService;
import com.spring.config.util.MD5Util;

@Controller
@Scope("prototype")
@RequestMapping(value = "/user")
public class UserInfoController extends MultiActionController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RoleService roleService;

	@RequestMapping
	public String search(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		model.addAttribute("pageResultSet", new PageResultSet<UserInfo>());
		return "userList";
	}

	@RequestMapping(value = "/list",method=RequestMethod.POST)
	public String list(UserInfo userInfo, PageResultSet<UserInfo> pageResultSet, Model model) {
		if (pageResultSet == null) {
			pageResultSet = new PageResultSet<UserInfo>();
		}
		model.addAttribute("userInfo", userInfo);
		pageResultSet = userInfoService.queryForPage(userInfo, pageResultSet.getPageInfo().getPageSize(), pageResultSet
		        .getPageInfo().getCurrentPage());
		model.addAttribute("pageResultSet", pageResultSet);
		return "userList";
	}

	/**
	 * add这个方法可能会被maxthon浏览器当做广告链接过滤掉,因为包含ad字符
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public String add(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		List<Role> roles = roleService.list();
		model.addAttribute("roles", roles);
		return "toAddUserPage";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(UserInfo userInfo) {
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
		return "redirect:/user";
	}

	@RequestMapping(value = "/{id}/edit")
	public String edit(@PathVariable Integer id, Model model) {
		UserInfo userInfo = userInfoService.getUserInfoById(id);
		model.addAttribute("userInfo", userInfo);

		List<Role> roles = roleService.getRoleListByUserId(id);
		List<Role> allRoles = roleService.list();
		Map<Role, Integer> rolesMap = new LinkedHashMap<Role, Integer>();
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

	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable Integer id,HttpServletRequest request) throws Exception {
		UserInfo userInfo = userInfoService.get(id);
		bind(request, userInfo);
		List<Role> roleList = roleService.getRoleListByRoleIds(userInfo.getRoleIds());
		userInfo.setRoles(roleList);
		userInfoService.update(userInfo);
		return "redirect:/user";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id, HttpServletResponse res) {
		userInfoService.deleteLogic(id);
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.print("{\"result\" : \"delete success.\"}");
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, Model model) {
		UserInfo userInfo = userInfoService.getUserInfoById(id);
		List<Role> roleList = roleService.getRoleListByUserId(userInfo.getId());
		userInfo.setRoles(roleList);
		model.addAttribute("userInfo", userInfo);
		return "detail";
	}

}
