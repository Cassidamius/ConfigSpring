package com.spring.config.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String findResourceList(Resource resource, PageResultSet<Resource> pageResultSet, Model model) {
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
	public String addResource(Resource resource) {
		resource.setDeleteFlag(1);
		resourcesService.save(resource);
		return "forward:toResourceListPage";
	}

	@RequestMapping(value = "/toEditResourcePage")
	public String toEditResourcePage(@RequestParam("id") Integer id, Model model) {
		Resource resource = resourcesService.get(id);
		model.addAttribute("resource", resource);
		return "toEditResourcePage";
	}

	@RequestMapping(value = "/editResource", method = RequestMethod.POST)
	public String editResource(Resource resource) throws UnsupportedEncodingException {
		resourcesService.updateResource(resource);
		return "forward:toResourceListPage";
	}

	@RequestMapping(value = "/deleteResource")
	public @ResponseBody void deleteResource(Integer id, HttpServletResponse res) {
		resourcesService.deleteLogic(id);
		String jsonStr = "{\"result\" : \"success\"}";

		System.out.println("jsonStr:" + jsonStr);
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.print(jsonStr);
		pw.flush();
		pw.close();
	}

}
