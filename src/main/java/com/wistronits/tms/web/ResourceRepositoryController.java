package com.wistronits.tms.web;




import java.io.IOException;
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
		mv.setViewName("/resource/resource_repository");
		return mv;
	}
	
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	  public String toAdd(Model model) {
	    return "/resource/resource_add";
	  }
	
	@RequestMapping(value = "/toEditJD", method = RequestMethod.POST)
	public ModelAndView toEditJD(
			@RequestParam(value="resourceId") int resourceId,
			@RequestParam(value="resourceType") String resourceType) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/resource/resource_edit_jd");
		mv.addObject("resourceId", resourceId);
		mv.addObject("resourceType", resourceType);
		return mv;
		
	}

	@RequestMapping(value = "/toScan")
	public String toScan(
			@RequestParam(value="resourceId") int resourceId,
			@RequestParam(value="resourceType") String resourceType) throws IOException {
        String retrurnString="";
		if("import".equals(resourceType)){
			retrurnString="forward:/Resource/toScanImport";
		}else if("add".equals(resourceType)){
          		
			retrurnString="forward:/Resource/toEditAdd";
           }
		return retrurnString;

		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchresource", method = RequestMethod.GET)
	public @ResponseBody RsResponse<ResumeBean> searchResource(
			@RequestParam(value="keyWord") String keyWord,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize){
		RsResponse<ResumeBean> rs = new RsResponse<ResumeBean>();
		List<ResumeBean> resourceBeans = new ArrayList<ResumeBean>();
		int count = 0;
		if(keyWord.isEmpty()){
			resourceBeans = resumeService.getAllResources(offSet,pageSize);
			count = resumeService.getAllResourcesCount();
		}else{
			Map<String,Object> map = resumeService.searchResource(keyWord,offSet,pageSize);
			resourceBeans = (List<ResumeBean>) map.get("list");
			count = (int) map.get("count");
		}
		rs.setAaData(resourceBeans);
		rs.setiTotalDisplayRecords(count);  
		rs.setiTotalRecords(count);
		rs.setsEcho(sEcho);
		return rs;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addResource", method = RequestMethod.POST)
	public @ResponseBody RsResponse addResource(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="birth") String birthYYYY_MM_DD,
			@RequestParam(value="gender") boolean gender,
			@RequestParam(value="mobile") String mobile,
			@RequestParam(value="starts") String startsYYYY_MM_DD,
			@RequestParam(value="email") String email,
			@RequestParam(value="inputFile") MultipartFile file,
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
		boolean ret = resumeService.addResource(rBean,file);
		if(!ret)
			return RsResponse.getErrorInstance("add to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
			
	}
		

	
	@RequestMapping(value="/toEditResource", method=RequestMethod.POST)
	public ModelAndView toEditResource(@RequestParam(value="resourceId") int resourceId){
		ModelAndView mv = new ModelAndView();
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String filePath=resumeService.transferToswf(bean.getFilePath());
		mv.addObject("bean",bean);
		mv.addObject("resourceId", resourceId);
		mv.addObject("filePath",filePath);
		mv.setViewName("/resource/resource_add_edit");
		return mv;
	}
	
	@RequestMapping(value="/toEditFromJD", method=RequestMethod.POST)
	public ModelAndView toEditAddFromJD(@RequestParam(value="resourceId") int resourceId,@RequestParam(value="no") int no){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/resource/resource_add_edit");
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String filePath=resumeService.transferToswf(bean.getFilePath());
		mv.addObject("bean",bean);
		mv.addObject("jdNo",no);
		mv.addObject("filePath",filePath);
		mv.setViewName("/resource/resource_add_edit");
		return mv;
	}
	
	@RequestMapping(value="/toEditFromJD2", method=RequestMethod.POST)
	public ModelAndView toEditAddFromJD2(@RequestParam(value="resourceId") int resourceId,@RequestParam(value="no") int no){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/resource/resource_add_edit");
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String filePath=resumeService.transferToswf(bean.getFilePath());
		mv.addObject("bean",bean);
		mv.addObject("jdNo2",no);
		mv.addObject("filePath",filePath);
		mv.setViewName("/resource/resource_add_edit");
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/toDeleteResource", method=RequestMethod.POST)
	public @ResponseBody RsResponse toDeleteResource (@RequestParam(value="resourceId") int resourceId){
		if(resourceId<=0){
			return RsResponse.getErrorInstance("resourceId invalid: "+resourceId);
		}
		boolean ret = resumeService.deleteResource(resourceId);
		if(!ret){
			return RsResponse.getErrorInstance("delete  resource failed!");
		}else{
			return RsResponse.BLANKSUCCESS;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateResource", method = RequestMethod.POST)
	public @ResponseBody RsResponse updateResource(
			@RequestParam(value="id") int id,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="birth") String birthYYYY_MM_DD,
			@RequestParam(value="gender") boolean gender,
			@RequestParam(value="mobile") String mobile,
			@RequestParam(value="starts") String startsYYYY_MM_DD,
			@RequestParam(value="email") String email,
			@RequestParam(value="inputFile") MultipartFile file,
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
		boolean ret = resumeService.editResource(rBean, file);
		if(!ret)
			return RsResponse.getErrorInstance("edit to database failed!");
		else
			return RsResponse.BLANKSUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listCanJoinResources", method = RequestMethod.POST)
	public @ResponseBody RsResponse<ResumeBean> listCanJoinResources(
			@RequestParam(value="no") int no,
			@RequestParam(value="keyWord") String keyWord,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize){
		RsResponse<ResumeBean> rs = new RsResponse<ResumeBean>();
		List<ResumeBean> importBeans = new ArrayList<ResumeBean>();
		int count = 0;
		if(keyWord.isEmpty()){
			Map<String,Object> map =resumeService.getCanJoinResources(offSet,pageSize,no);
			importBeans = (List<ResumeBean>) map.get("resources");
			count = (int) map.get("count");
		}else{
			Map<String,Object> map = resumeService.searchCanJoinResource(keyWord,no);
			importBeans = (List<ResumeBean>) map.get("list");
			count = (int) map.get("count");
		}
		rs.setAaData(importBeans);
		rs.setiTotalDisplayRecords(count);  
		rs.setiTotalRecords(count);
		rs.setsEcho(sEcho);
		return rs;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTheBelongResources", method = RequestMethod.POST)
	public @ResponseBody RsResponse<ResumeBean> listTheBelongResources(
			@RequestParam(value="no") int no,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize){
		RsResponse<ResumeBean> rs = new RsResponse<ResumeBean>();
		List<ResumeBean> importBeans = new ArrayList<ResumeBean>();
		int count = 0;
		
		Map<String,Object> map =resumeService.getTheBelongResources(offSet,pageSize,no);
		importBeans = (List<ResumeBean>) map.get("resources");
		count = (int) map.get("count");

		rs.setAaData(importBeans);
		rs.setiTotalDisplayRecords(count);  
		rs.setiTotalRecords(count);
		rs.setsEcho(sEcho);
		return rs;
	}
	
	@RequestMapping(value = "/getCanJoinJDs", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCanJoinJDs(@RequestParam(value="resourceId") int resourceId) {
		return resumeService.getCanJoinJDs(resourceId);
	}
	

	
	@RequestMapping(value = "/getTheBelongJDs", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getTheBelongJDs(@RequestParam(value="resourceId") int resourceId) {
		return resumeService.getTheBelongJDs(resourceId);
	}
	
	
	@RequestMapping(value = "/addTheResourceToJD", method = RequestMethod.POST)
	public ModelAndView addTheResourceToJD(	
			@RequestParam(value = "jdId") int no,
			@RequestParam(value = "rId") int rid) {
		ModelAndView view = new ModelAndView("jd/jd_edit");
		int resultCount=resumeService.addResourceToJD(no, rid);
		if(resultCount!=0){
			view.addObject("jdNo", no);
		}
		return view;
	}
	
	@RequestMapping(value = "/deleteTheResourceFromJD", method = RequestMethod.POST)
	public ModelAndView deleteTheResourceFromJD(
			@RequestParam(value = "jdId") int no,
			@RequestParam(value = "rId") int rid) {
		ModelAndView view = new ModelAndView("jd/jd_edit");
		int resultCount=resumeService.deleteResourceFromJD(no, rid);
		if(resultCount!=0){
			view.addObject("jdNo", no);
		}
		return view;
	}
	

	@RequestMapping(value = "/addJDToResource", method = RequestMethod.POST)
	public ModelAndView addJDToResource(	
			@RequestParam(value = "jdId") int no,
			@RequestParam(value = "resourceId") int rid) {
		ModelAndView view = new ModelAndView("resource/resource_edit_jd");
		int resultCount=resumeService.addResourceToJD(no, rid);
		if(resultCount!=0){
			view.addObject("resourceId", rid);
		}
		return view;
	}

	@RequestMapping(value = "/deleteJDFromResource", method = RequestMethod.POST)
	public ModelAndView deleteJDFromResource(
			@RequestParam(value = "jdId") int no,
			@RequestParam(value = "resourceId") int rid) {
		ModelAndView view = new ModelAndView("resource/resource_edit_jd");
		int resultCount=resumeService.deleteResourceFromJD(no, rid);
		if(resultCount!=0){
			view.addObject("resourceId", rid);
		}
		return view;
	}
}
