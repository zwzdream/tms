package com.wistronits.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Resource")
public class ResourceRepositoryControlle {

	
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	  public String toAdd(Model model) {

	    return "/resource/resource_add";
	  }

}
