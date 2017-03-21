package com.wistronits.tms.web;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public @ResponseBody RsResponse addResource(@ModelAttribute("addForm") ResumeBean rBean,
			@RequestParam(value="inputFile")  MultipartFile file) {	

		String regex = "^[a-zA-Z].*[xX]$";
		if(file.getOriginalFilename().matches(regex)){
			return RsResponse.getErrorInstance("Please upload an office file of under the 2003 version !");
		}
		rBean.setAddTime(new Date());
		rBean.setLastMTime(new Date());
		boolean ret = resumeService.addResource(rBean,file);
		if(!ret){
			return RsResponse.getErrorInstance("add to database failed!");
		}else{
			return RsResponse.BLANKSUCCESS;
			}
			
	}
		

	
	@RequestMapping(value="/toEditResource", method=RequestMethod.POST)
	public ModelAndView toEditResource(@RequestParam(value="resourceId") int resourceId){
		ModelAndView mv = new ModelAndView();
		String filePath="error";
		String fileError="success";
		ArrayList<String> returnList=null;
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String rfilePath= bean.getFilePath();
		if(rfilePath!=null){
		returnList=resumeService.transferToswf(rfilePath);
		filePath=returnList.get(0);
		fileError=returnList.get(1);
		}
		
		mv.addObject("bean",bean);
		mv.addObject("resourceId", resourceId);
		mv.addObject("filePath",filePath);
		mv.addObject("fileError",fileError);
		mv.setViewName("/resource/resource_add_edit");
		return mv;
	}
	@RequestMapping(value="/toScanResource", method=RequestMethod.POST)
	public ModelAndView toScanResource(@RequestParam(value="resourceId") int resourceId){
		ModelAndView mv = new ModelAndView("/resource/resource_scan");
		String filePath="error";
		String fileError="success";
		ArrayList<String> returnList=null;
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String rfilePath= bean.getFilePath();
		if(rfilePath!=null){
			returnList=resumeService.transferToswf(rfilePath);
			filePath=returnList.get(0);
			fileError=returnList.get(1);
		}
		
		mv.addObject("filePath",filePath);
		mv.addObject("fileError",fileError);

		return mv;
	}
	
	@RequestMapping(value="/toEditFromJD", method=RequestMethod.POST)
	public ModelAndView toEditAddFromJD(@RequestParam(value="resourceId") int resourceId,@RequestParam(value="no") int no){
		ModelAndView mv = new ModelAndView();
		String filePath="error";
		String fileError="success";
		ArrayList<String> returnList=null;
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String rfilePath= bean.getFilePath();
		if(rfilePath!=null){
			returnList=resumeService.transferToswf(rfilePath);
			filePath=returnList.get(0);
			fileError=returnList.get(1);
		}
		mv.addObject("bean",bean);
		mv.addObject("jdNo",no);
		mv.addObject("filePath",filePath);
		mv.addObject("fileError",fileError);
		mv.setViewName("/resource/resource_add_edit");
		return mv;
	}
	
	@RequestMapping(value="/toEditFromJD2", method=RequestMethod.POST)
	public ModelAndView toEditAddFromJD2(@RequestParam(value="resourceId") int resourceId,@RequestParam(value="no") int no){
		ModelAndView mv = new ModelAndView();
		String filePath="error";
		String fileError="";
		ArrayList<String> returnList=null;
		ResumeBean bean = resumeService.getResourceById(resourceId);
		String rfilePath= bean.getFilePath();
		if(rfilePath!=null){
			returnList=resumeService.transferToswf(rfilePath);
			filePath=returnList.get(0);
			fileError=returnList.get(1);
		}
		mv.addObject("bean",bean);
		mv.addObject("jdNo2",no);
		mv.addObject("filePath",filePath);
		mv.addObject("fileError",fileError);
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
	public @ResponseBody RsResponse updateResource(@ModelAttribute("editForm") ResumeBean rBean,
			@RequestParam(value="inputFile") MultipartFile file) {
	
		String regex = "^[a-zA-Z].*[xX]$";
		if(file.getOriginalFilename().matches(regex)){
			return RsResponse.getErrorInstance("Please upload an office file of under the 2003 version !");
		}
		rBean.setLastMTime(new Date());
		boolean ret = resumeService.editResource(rBean, file);
		if(!ret){
			return RsResponse.getErrorInstance("edit to database failed!");
		}else{
			return RsResponse.BLANKSUCCESS;
		}
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
