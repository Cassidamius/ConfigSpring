package com.spring.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.config.model.Resource;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.ResourceService;

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

}
