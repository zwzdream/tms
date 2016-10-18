package com.wistronits.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wistronits.tms.entity.JDBean;

@Controller
@RequestMapping("/JD")
public class JDManagementController {


/*  @RequestMapping(value = "/toEdit", method = RequestMethod.POST)
  public String toEdit(Model model) {
	  
   
    return "/jd/jd_edit";
  }*/

  @RequestMapping(value = "/toAdd", method = RequestMethod.POST)
  public String toAdd(Model model) {

    return "/jd/jd_add";
  }
  /*
  @RequestMapping(value = "/add/save", method = RequestMethod.POST)
  public ModelAndView save(@ModelAttribute("editForm") JDBean form, Model model, ModelMap map) {

    // return new ModelAndView(new RedirectView("/home/JD.html", true, false, false), map);
    // GET JD Management List
	  System.out.println(form.toString());
    ModelAndView view = new ModelAndView("/jd/jd_management");
    view.addObject(form);
    return view;
  }*/

}
