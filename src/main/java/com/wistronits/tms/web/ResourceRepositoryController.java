package com.wistronits.tms.web;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.RsResponse;
import com.wistronits.tms.service.IResumeService;

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
	
	@Resource
	private IResumeService resumeService;
	
	@RequestMapping(value = "/importResource", method = RequestMethod.POST)
	public @ResponseBody RsResponse importResource(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="birth") String birthYYYY_MM_DD,
			@RequestParam(value="gender") Boolean gender,
			@RequestParam(value="inputFile") MultipartFile file) {
		Date birthDate = null;
		if(!birthYYYY_MM_DD.isEmpty()) {
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
			try {
				birthDate = df.parse(birthYYYY_MM_DD);
			} catch (ParseException e) {
				return RsResponse.getErrorInstance("Birth date invalid:"+birthYYYY_MM_DD);
			}
		}
		if(birthDate == null){
			return RsResponse.getErrorInstance("Birth date must exist");
		}
		ImportResourceBean resource = new ImportResourceBean();
		resource.setFirstName(firstName);
		resource.setLastName(lastName);
		resource.setBirth(birthDate);
		resource.setGender(gender);
//		resource.setInputFile(file);
		boolean ret = resumeService.importResource(resource,file);
		if(!ret)
			return RsResponse.getErrorInstance("import to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
			
	}
}
