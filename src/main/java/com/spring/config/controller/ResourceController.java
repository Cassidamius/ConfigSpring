package com.spring.config.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.config.model.Resource;
import com.spring.config.model.UserInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.ResourceService;
import com.spring.config.util.MD5Util;

@Controller
@Scope("prototype")
public class ResourceController {

	@Autowired
	private ResourceService resourcesService;

	@RequestMapping(value = "/toResourceListPage")
	public String toResourceListPage(Model model) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("pageResultSet", new PageResultSet<Resource>());
		return "resourceList";
	}

	@RequestMapping(value = "/findResourceList")
	public String findUserList(Resource resource, PageResultSet<Resource> pageResultSet, Model model) {
		if (pageResultSet == null) {
			pageResultSet = new PageResultSet<Resource>();
		}
		model.addAttribute("resource", resource);
		pageResultSet = resourcesService.queryForPage(resource, pageResultSet.getPageInfo().getPageSize(),
		        pageResultSet.getPageInfo().getCurrentPage());
		model.addAttribute("pageResultSet", pageResultSet);
		return "resourceList";
	}

	@RequestMapping(value = "/toAddResourcePage")
	public String toAddUserPage(Model model) {
		model.addAttribute("resource", new Resource());
		return "toAddResourcePage";
	}
	
	@RequestMapping(value = "/addResource", method = RequestMethod.POST)
	public String addResource(Resource resource, Model model) {
		resource.setDeleteFlag(1);
		resourcesService.save(resource);
		model.addAttribute("resource", resource);
		return "userList";
	}

}
