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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wistronits.tms.entity.GroupBean;
import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.entity.RsResponse;
import com.wistronits.tms.entity.UserBean;
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
		resource.setLastMTime(new Date());
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
//			importBeans = resumeService.getAllImportBeans(offSet,pageSize);
//			count = resumeService.getAllImportBeansCount();
			importBeans = resumeService.getAllBeans(offSet,pageSize);
			count = resumeService.getAllBeansCount();
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
			@RequestParam(value="gender") boolean gender,
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
		rBean.setLastMTime(new Date());
		boolean ret = resumeService.addResume(rBean);
		if(!ret)
			return RsResponse.getErrorInstance("add to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
			
	}
	@RequestMapping(value="/toEditImport", method=RequestMethod.POST)
	public ModelAndView toEditImportView(@RequestParam(value="resourceId") int resourceId){
		ModelAndView mv = new ModelAndView();
		ImportResourceBean bean = resumeService.getImportResourceById(resourceId);
		mv.getModel().put("bean",bean);
		mv.setViewName("/resource/resource_edit_importrecource");
		return mv;
	}
	@RequestMapping(value="/toEditAdd", method=RequestMethod.POST)
	public ModelAndView toEditAddView(@RequestParam(value="resourceId") int resourceId){
		ModelAndView mv = new ModelAndView();
		ResumeBean bean = resumeService.getResumeById(resourceId);
		mv.getModel().put("bean",bean);
		mv.setViewName("/resource/resource_add_edit");
		return mv;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/toDeleteImport", method=RequestMethod.POST)
	public @ResponseBody RsResponse toDeleteImport (@RequestParam(value="resourceId") int resourceId){
		if(resourceId<=0){
			return RsResponse.getErrorInstance("resourceId invalid: "+resourceId);
		}
		boolean ret = resumeService.deleteImportResource(resourceId);
		if(!ret){
			return RsResponse.getErrorInstance("delete import resource failed!");
		}else{
			return RsResponse.BLANKSUCCESS;
		}
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/toDeleteAdd", method=RequestMethod.POST)
	public @ResponseBody RsResponse toDeleteAdd (@RequestParam(value="resourceId") int resourceId){
		if(resourceId<=0){
			return RsResponse.getErrorInstance("resourceId invalid: "+resourceId);
		}
		boolean ret = resumeService.deleteResume(resourceId);
		if(!ret){
			return RsResponse.getErrorInstance("delete Resume resource failed!");
		}else{
			return RsResponse.BLANKSUCCESS;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateImportResource", method = RequestMethod.POST)
	public @ResponseBody RsResponse updateImportResource(
			@RequestParam(value="id") int id,
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
		resource.setId(id);
		resource.setFirstName(firstName);
		resource.setLastName(lastName);
		resource.setBirth(birthDate);
		resource.setGender(gender);
		resource.setLastMTime(new Date());
		boolean ret = resumeService.editImportResource(resource, file);
		if(!ret)
			return RsResponse.getErrorInstance("edit to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateResume", method = RequestMethod.POST)
	public @ResponseBody RsResponse updateResume(
			@RequestParam(value="id") int id,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="birth") String birthYYYY_MM_DD,
			@RequestParam(value="gender") boolean gender,
			@RequestParam(value="mobile") String mobile,
			@RequestParam(value="starts") String startsYYYY_MM_DD,
			@RequestParam(value="email") String email,
			@RequestParam(value="residency") String residency,
			@RequestParam(value="education") String education,
			@RequestParam(value="workExp") String workExp,
			@RequestParam(value="projectExp") String projectExp) {
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
		rBean.setId(id);
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
		rBean.setLastMTime(new Date());
		boolean ret = resumeService.editResume(rBean);
		if(!ret)
			return RsResponse.getErrorInstance("edit to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
	}
	
	@RequestMapping(value = "/getCanJoinResources", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCanJoinResources(@RequestParam(value = "no") int no) {
		 
		return resumeService.getCanJoinResources(no);

	}
	
	@RequestMapping(value = "/getTheBelongResources", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getTheBelongResources(@RequestParam(value = "no") int no) {
		return resumeService.getTheBelongResources(no);

	}
/*	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/editTheBelongResource", method = RequestMethod.POST)
	public @ResponseBody RsResponse editTheBelongGroup(
			@RequestParam(value = "jdId") int no,
			@RequestParam(value = "resourceId") int rid,
			@RequestParam(value="resourceName")String rName) {
		String type=rName.substring(rName.indexOf("(")+1, rName.indexOf(")"));
		
		int i=resumeService.editTheBelongResource(no, rid,type);
		if(i==0 ){
			int j= resumeService.addResourceToJD(no, rid,type);
			if(j==0){
				return RsResponse.getErrorInstance("edit to database failed!");
			}
		}
		// ModelAndView view = new ModelAndView("jd/jd_add_resource");
		// view.addObject("jdNo", no);
		//return view;
		 return RsResponse.BLANKSUCCESS;
	}*/
	
	@RequestMapping(value = "/addResourceToJD", method = RequestMethod.POST)
	public ModelAndView addResourceToJD(	
			@RequestParam(value = "jdId") int no,
			@RequestParam(value="jdTitle") String title,
			@RequestParam(value = "resourceId") int rid,
			@RequestParam(value="resourceName")String rName) {
		String type=rName.substring(rName.indexOf("(")+1, rName.indexOf(")"));
		ModelAndView view = new ModelAndView("jd/jd_add_resource");
		int resultCount=resumeService.addResourceToJD(no, rid, type);
		if(resultCount!=0){
			 view.addObject("jdNo", no);
			 view.addObject("jdTitle", title);
				return view;
		}
		return view;

	}
	@RequestMapping(value = "/deleteResourceFromJD", method = RequestMethod.POST)
	public ModelAndView deleteResourceFromJD(
			@RequestParam(value = "jdId") int no,
			@RequestParam(value="jdTitle") String title,
			@RequestParam(value = "resourceId") int rid,
			@RequestParam(value="resourceName")String rName) {
		String type=rName.substring(rName.indexOf("(")+1, rName.indexOf(")"));
		 ModelAndView view = new ModelAndView("jd/jd_add_resource");
		 int resultCount=resumeService.deleteResourceFromJD(no, rid, type);
		 if(resultCount!=0){
			 view.addObject("jdNo", no);
			 view.addObject("jdTitle", title);
				return view;
		}
		return view;
	}
		
}
