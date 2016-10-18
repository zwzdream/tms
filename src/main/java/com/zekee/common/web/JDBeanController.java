package com.zekee.common.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zekee.common.persistence.JDBean;
import com.zekee.common.service.IJDBeanService;

@Controller
@RequestMapping("/JD")
public class JDBeanController {
	  @Resource
	  private IJDBeanService jdBeanService;

  @RequestMapping(value = "/add/save", method = RequestMethod.POST)
  public ModelAndView save(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {
    // return new ModelAndView(new RedirectView("/home/JD.html", true, false, false), map);
    // GET JD Management List
	  jdBeanService.saveJDBean(form);
    ModelAndView view = new ModelAndView("/jd/jd_management");
    view.addObject(form);
    return view;
  }
  
  @RequestMapping(value = "/edit/save", method = RequestMethod.POST)
  public ModelAndView edit(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {
	    // return new ModelAndView(new RedirectView("/home/JD.html", true, false, false), map);
	    // GET JD Management List
		  jdBeanService.saveJDBean(form);
	    ModelAndView view = new ModelAndView("/jd/jd_management");
	    view.addObject(form);
	    return view;
	  }

}
