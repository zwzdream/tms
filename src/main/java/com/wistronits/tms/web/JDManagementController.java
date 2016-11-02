package com.wistronits.tms.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	public ModelAndView toEdit(int no) {
		ModelAndView view = new ModelAndView("/jd/jd_edit");
		PageHelper.startPage(1, 5);
		List<JDBean> jdBeanList = jdBeanService.listAll();
		PageInfo<JDBean> page = new PageInfo<>(jdBeanList);
		view.addObject("jd", jdBeanService.getJD(no));
		System.out.println(jdBeanService.getJD(no).toString());
		view.addObject("page", page);
		return view;
	}

	@RequestMapping(value = "/all/list", method = RequestMethod.POST)
	public ModelAndView listAll(int pageNum, int pageSize) {
		ModelAndView view = new ModelAndView("/jd/jd_edit");
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

	// mysql缓存，上一页，下一页或更改每页记录数时，keyword为空
	@RequestMapping(value = "/keyword/listPage")
	public ModelAndView listPage(String keyword, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JDBean> jdBean = jdBeanService.listByKeyword(keyword);
		PageInfo<JDBean> page = new PageInfo<>(jdBean);
		ModelAndView view = new ModelAndView("/jd/jd_management");
		view.addObject("keyword", keyword);
		view.addObject("page", page);
		return view;
	}

	@RequestMapping(value = "/edit/close", method = RequestMethod.POST)
	public ModelAndView close(int no) {
		ModelAndView view = new ModelAndView("/jd/jd_management");
		jdBeanService.closeJD(no, new Date());
		return view;

	}

}
