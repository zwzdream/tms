package com.wistronits.tms.web;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.entity.RsResponse;
import com.wistronits.tms.service.IResumeService;

@Controller
@RequestMapping("/Resource")
public class ResourceRepositoryController {
	
	@Resource
	private IResumeService resumeService;

	@RequestMapping(value = "/toRepository", method = RequestMethod.GET)
	public ModelAndView getAllRepository(){
		ModelAndView mv = new ModelAndView();
//		List<ImportResourceBean> importBeans = resumeService.getAllImportBeans();
//		mv.getModel().put("importBeans", importBeans);
		mv.setViewName("/resource/resource_repository");
		return mv;
	}
	
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	  public String toAdd(Model model) {

	    return "/resource/resource_add";
	  }
	@RequestMapping(value = "/toimport", method = RequestMethod.POST)
	public String toImportPage(Model model) {
		return "/resource/resource_import";
	}
	
	
	@SuppressWarnings("rawtypes")
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
		boolean ret = resumeService.importResource(resource,file);
		if(!ret)
			return RsResponse.getErrorInstance("import to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchresource", method = RequestMethod.GET)
	public @ResponseBody RsResponse<ImportResourceBean> searchResource(
			@RequestParam(value="keyWord") String keyWord,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize){
		RsResponse<ImportResourceBean> rs = new RsResponse<ImportResourceBean>();
		List<ImportResourceBean> importBeans = new ArrayList<ImportResourceBean>();
		int count = 0;
		if(keyWord.isEmpty()){
			importBeans = resumeService.getAllImportBeans(offSet,pageSize);
			count = resumeService.getAllImportBeansCount();
		}else{
			Map<String,Object> map = resumeService.searchResource(keyWord,offSet,pageSize);
			importBeans = (List<ImportResourceBean>) map.get("list");
			count = (int) map.get("count");
		}
		rs.setAaData(importBeans);
		rs.setiTotalDisplayRecords(count);  
		rs.setiTotalRecords(count);
		rs.setsEcho(sEcho);
		return rs;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addResume", method = RequestMethod.POST)
	public @ResponseBody RsResponse addResume(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="birth") String birthYYYY_MM_DD,
			@RequestParam(value="inlineRadio") String gender,
			@RequestParam(value="mobile") String mobile,
			@RequestParam(value="starts") String startsYYYY_MM_DD,
			@RequestParam(value="email") String email,
			@RequestParam(value="residency") String residency,
			@RequestParam(value="education") String education,
			@RequestParam(value="workExp") String workExp,
			@RequestParam(value="projectExp") String projectExp
			) {	
		Date birthDate = null;
		Date startsDate=null;
		if(!birthYYYY_MM_DD.isEmpty()) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				birthDate = df.parse(birthYYYY_MM_DD);
			} catch (ParseException e) {
				return RsResponse.getErrorInstance("Birth date invalid:"+birthYYYY_MM_DD);
			}
		}
		if(birthDate == null){
			return RsResponse.getErrorInstance("Birth date must exist");
		}
		if(!startsYYYY_MM_DD.isEmpty()) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startsDate = df.parse(startsYYYY_MM_DD);
			} catch (ParseException e) {
				return RsResponse.getErrorInstance("Work starts date invalid:"+birthYYYY_MM_DD);
			}
		}
		if(startsDate == null){
			return RsResponse.getErrorInstance("Work starts date must exist");
		}
		ResumeBean rBean = new ResumeBean();
		rBean.setBirth(birthDate);
		rBean.setFirstName(firstName);
		rBean.setLastName(lastName);
		rBean.setGender(gender);
		rBean.setMobile(mobile);
		rBean.setStarts(startsDate);
		rBean.setEmail(email);
		rBean.setResidency(residency);
		rBean.setEducation(education);
		rBean.setWorkExp(workExp);
		rBean.setProjectExp(projectExp);
		boolean ret = resumeService.addResume(rBean);
		if(!ret)
			return RsResponse.getErrorInstance("add to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
			
	}
	@RequestMapping(value="/toEditImport", method=RequestMethod.POST)
	public ModelAndView toEditImportView(@RequestParam(value="resourceId") int resourceId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/resource/resource_edit_importrecource");
		return mv;
	}
}
