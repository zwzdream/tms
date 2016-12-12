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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.wistronits.tms.entity.UserBean;
import com.wistronits.tms.service.IUserService;


@Controller
@RequestMapping("/User")
public class UserManagementController {
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	public String toAdd(Model model) {

		return "/user/user_add";
	}

	@RequestMapping(value = "/toEdit/{id}", method = RequestMethod.POST)
	public ModelAndView toEdit(@PathVariable int id) {
		ModelAndView view = new ModelAndView("/user/user_edit");	
		view.addObject("user", userService.getUserById(id));
		return view;
	}
	
	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("editForm") UserBean form, Model model, ModelMap map) {
		form.setDate(new Date());
		userService.editUser(form);
		ModelAndView view = new ModelAndView("/user/user_management");
		view.addObject(form);
		return view;
	}



	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("editForm") UserBean form, Model model, ModelMap map) {
		form.setDate(new Date());
		userService.addUser(form);
		ModelAndView view = new ModelAndView("/user/user_management");
		view.addObject(form);
		return view;
	}

	
	@RequestMapping(value = "/username/list", method = RequestMethod.GET)
	@ResponseBody
	public  Map<Object,Object> list(
			@RequestParam(value="username") String username,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize) {
		int pageNum=offSet/pageSize+1;
		PageHelper.startPage(pageNum, pageSize);
		List<UserBean> userBean = userService.listByName(username);
		List<UserBean> userBean1 = userService.listByName(username);
        Map<Object,Object> map=new HashMap<>();
        map.put("aaData", userBean);//数据
        map.put("iTotalDisplayRecords", userBean1.size());//过滤后总记录数
        map.put("iTotalRecords", userBean1.size());// 过滤前总记录数
        map.put("sEcho", sEcho);
		return map;
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable int id) {
		//ModelAndView view = new ModelAndView("/user/user_management");
		ModelAndView view = new ModelAndView("/user/user_management");
		userService.deleteUser(id);
		return view;
		
	}
	
	@RequestMapping(value = "/editGroup/{id}", method = RequestMethod.POST)
	public ModelAndView editGroup(@PathVariable int id) {
		ModelAndView view = new ModelAndView("/user/user_group");
		view.addObject("userId", userService.getUserById(id).getId());
		view.addObject("userName", userService.getUserById(id).getUsername());
		return view;
	}

}
