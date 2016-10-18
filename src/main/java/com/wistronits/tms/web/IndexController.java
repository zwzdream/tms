package com.wistronits.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Index")
public class IndexController {

  /**
   * login jsp
   * 
   * @param model
   * @return
   * 
   * @return String
   * @exception throws
   * @see
   */
  @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
  public String toLogin(Model model) {
    return "/login/login";
  }

  /**
   * Menu link load Module
   * 
   * @param model
   * @param targetAction
   * @return
   * 
   * @return String
   * @exception throws
   * @see
   */
  @RequestMapping(value = "/home/{targetAction}", method = RequestMethod.GET)
  public String home(Model model, @PathVariable("targetAction") String targetAction) {

    if (!StringUtils.isEmpty(targetAction)) {
      if ("dashboard".equals(targetAction)) {
        return "/content";
      } else if ("JD".equals(targetAction)) {
        return "/jd/jd_management";
      } else if ("resource".equals(targetAction)) {
        return "/resource/resource_repository";
      } else {
        return "/home";
      }
    } else {
      return "/home";
    }
  }

  /**
   * Ajax load dashboard
   * 
   * @param model
   * @return
   * 
   * @return String
   * @exception throws
   * @see
   */
  @RequestMapping(value = "/dashboard/init", method = RequestMethod.POST)
  public String dashboard(Model model) {
    return "/content";
  }

  /**
   * Ajax load JD Management List
   * 
   * @param model
   * @return
   * 
   * @return String
   * @exception throws
   * @see
   */
  @RequestMapping(value = "/JD/init", method = RequestMethod.POST)
  public String JD(Model model) {
    return "/jd/jd_management";
  }

  /**
   * Ajax load Resource Repository
   * 
   * @param model
   * @return
   * 
   * @return String
   * @exception throws
   * @see
   */
  @RequestMapping(value = "/resource/init", method = RequestMethod.POST)
  public String resource(Model model) {
    return "/resource/resource_repository";
  }

}
