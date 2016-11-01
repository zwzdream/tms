package com.wistronits.tms.web;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.entity.ImportResourceBean;

@Controller
@RequestMapping("/Resource")
public class ResourceRepositoryController {

	
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	  public String toAdd(Model model) {

	    return "/resource/resource_add";
	  }
	@RequestMapping(value = "/toimport", method = RequestMethod.POST)
	public String toImportPage(Model model) {
		return "/resource/resource_import";
	}
	
	@RequestMapping(value = "/importResource", method = RequestMethod.POST)
//	public String importResource(@RequestParam Map<String, Object> params) {
	public String importResource(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="birth") String birthYYYY_MM_DD,
			@RequestParam(value="gender") Boolean gender,
			@RequestParam(value="inputFile") MultipartFile file) {
		
		return "/resource/resource_import";
	}
}
