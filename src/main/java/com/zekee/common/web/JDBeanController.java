package com.zekee.common.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zekee.common.persistence.JDBean;
import com.zekee.common.service.IJDBeanService;

@Controller
@RequestMapping("/JD")
public class JDBeanController {
	  @Resource
	  private IJDBeanService jdBeanService; 

	  @RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	  public ModelAndView toEdit(int no) {
		  ModelAndView view = new ModelAndView("/jd/jd_edit");
		  view.addObject("jd",jdBeanService.getJD(no));
		  return view;
	  }

  @RequestMapping(value = "/add/save", method = RequestMethod.POST)
  public ModelAndView save(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {
	  jdBeanService.saveJDBean(form);
    ModelAndView view = new ModelAndView("/jd/jd_management");
    view.addObject(form);
    return view;
  }
  
  @RequestMapping(value = "/edit/save", method = RequestMethod.POST)
  public ModelAndView edit(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {
		  jdBeanService.editJDBean(form);
	    ModelAndView view = new ModelAndView("/jd/jd_management");
	    view.addObject(form);
	    return view;
	  }
  
  @RequestMapping(value = "/keyword/list")
  public ModelAndView list(String keyword) {
	  List<JDBean> jdBean=jdBeanService.listByKeyword(keyword);
       
	  ModelAndView view = new ModelAndView("/jd/jd_management");
	  view.addObject("jdBean",jdBean);
	  return view;
  }
  //mysql缓存，上一页，下一页或更改每页记录数时，keyword为空
  @RequestMapping(value = "/keyword/listPage")
  public ModelAndView listPage(String keyword,int pageNum,int pageSize,HttpServletResponse response ) {

	 // Cookie cookie = new Cookie(Cookiename, null); 
	  /*cookie.setMaxAge(-1);
	  response.addCookie(cookie); */
	  
	  PageHelper.startPage(pageNum, pageSize);  
	  List<JDBean> jdBean=jdBeanService.listByKeyword(keyword);
	  PageInfo<JDBean> page=new PageInfo<>(jdBean);
	  ModelAndView view = new ModelAndView("/jd/jd_management");
	  view.addObject("keyword",keyword);
	  view.addObject("page",page);
	  return view;
  }

}
