package com.wistronits.tms.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wistronits.tms.entity.JDBean;
import com.wistronits.tms.service.IJDBeanService;


@Controller
@RequestMapping("/JD")
public class JDManagementController {
	@Resource
	private IJDBeanService jdBeanService;

	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	public String toAdd(Model model) {

		return "/jd/jd_add";
	}
	@RequestMapping(value = "/toAddResource", method = RequestMethod.POST)
	public ModelAndView toAddResource(int no) {	
		ModelAndView view = new ModelAndView("/jd/jd_add_resource");
		JDBean jd=jdBeanService.getJD(no);
		view.addObject("jdNo", jd.getNo());
		view.addObject("jdTitle", jd.getTitle());
		return view;
	}

	@RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	public ModelAndView toEdit(int no) {
		ModelAndView view = new ModelAndView("/jd/jd_edit");
/*		PageHelper.startPage(1, 5);
		List<JDBean> jdBeanList = jdBeanService.listAll();
		PageInfo<JDBean> page = new PageInfo<>(jdBeanList);
		view.addObject("page", page);*/
		view.addObject("jd", jdBeanService.getJD(no));
		view.addObject("jdNo", no);
		return view;
	}

	@RequestMapping(value = "/all/list", method = RequestMethod.POST)
	public ModelAndView listAll(int pageNum, int pageSize) {
		ModelAndView view = new ModelAndView("/jd/jd_grid");
		PageHelper.startPage(pageNum, pageSize);
		List<JDBean> jdBeanList = jdBeanService.listAll();
		PageInfo<JDBean> page = new PageInfo<>(jdBeanList);
		view.addObject("page", page);
		return view;
	}

	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {
		form.setModifydate(new Date());
		jdBeanService.saveJDBean(form);
		ModelAndView view = new ModelAndView("/jd/jd_management");
		view.addObject(form);
		return view;
	}

	@RequestMapping(value = "/edit/save", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {
		form.setModifydate(new Date());
		jdBeanService.editJDBean(form);
		ModelAndView view = new ModelAndView("/jd/jd_management");
		view.addObject(form);
		return view;
	}


	@RequestMapping(value = "/keyword/listPage", method = RequestMethod.POST)
	public  @ResponseBody  Map<Object,Object> listPage(
			@RequestParam(value="keyword")String keyword,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize) {
		int pageNum=offSet/pageSize+1;
		PageHelper.startPage(pageNum, pageSize);
		List<JDBean> jdBean = jdBeanService.listByKeyword(keyword);
		List<JDBean> jdBean1 = jdBeanService.listByKeyword(keyword);
	  Map<Object,Object> map =new HashMap<Object,Object>();
	  map.put("aaData", jdBean);
      map.put("iTotalDisplayRecords", jdBean1.size());
      map.put("iTotalRecords", jdBean1.size());
      map.put("sEcho", sEcho);
	   return map;	
	}

	@RequestMapping(value = "/edit/close", method = RequestMethod.POST)
	public ModelAndView close(int no) {
		ModelAndView view = new ModelAndView("/jd/jd_management");
		jdBeanService.closeJD(no, new Date());
		return view;

	}

}
