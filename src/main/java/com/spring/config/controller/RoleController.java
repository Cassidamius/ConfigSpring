package com.spring.config.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.config.model.Resource;
import com.spring.config.model.Role;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.ResourceService;
import com.spring.config.service.RoleService;

@Controller
@Scope("prototype")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "/toRoleListPage")
	public String toRoleListPage(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("pageResultSet", new PageResultSet<Role>());
		return "roleList";
	}
	
	@RequestMapping(value = "/findRoleList")
	public String findUserList(Role role, PageResultSet<Role> pageResultSet, Model model) {
		if (pageResultSet == null) {
			pageResultSet = new PageResultSet<Role>();
		}
		model.addAttribute("role", role);
		pageResultSet = roleService.queryForPage(role, pageResultSet.getPageInfo().getPageSize(), pageResultSet
		        .getPageInfo().getCurrentPage());
		model.addAttribute("pageResultSet", pageResultSet);
		return "roleList";
	}
	
	@RequestMapping(value = "/toAddRolePage")
	public String toAddRolePage(Model model) {
		model.addAttribute("role", new Role());
		List<Resource> resources = resourceService.list();
		model.addAttribute("resources", resources);
		return "toAddRolePage";
	}
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public String addRole(Role role, Integer[] resourceids, Model model) {
		role.setDeleteFlag(1);
		List<Resource> resources = new ArrayList<Resource>();
		for (Integer id : resourceids) {
			resources.add(resourceService.get(id));
		}
		role.setResources(resources);
		roleService.save(role);
		model.addAttribute("role", role);
		return "redirect:/toRoleListPage";
	}
	
}
