package com.wistronits.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Resource")
public class ResourceRepositoryController {

	
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	  public String toAdd(Model model) {

	    return "/resource/resource_add";
	  }
	@RequestMapping(value = "/toimport", method = RequestMethod.POST)
	public String toImportPage(Model model) {
		return "/resource/resource_importFile";
	}
	
	@RequestMapping(value = "/importResource", method = RequestMethod.POST)
	public String importResource(Model model) {
		return "/resource/resource_importFile";
	}
}
