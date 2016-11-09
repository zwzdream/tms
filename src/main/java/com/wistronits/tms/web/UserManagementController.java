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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

	@RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	public ModelAndView toEdit(int id) {
		ModelAndView view = new ModelAndView("/user/user_edit");	
		view.addObject("user", userService.getUserById(id));
		return view;
	}

/*	@RequestMapping(value = "/all/list", method = RequestMethod.POST)
	public ModelAndView listAll(int pageNum, int pageSize) {
		ModelAndView view = new ModelAndView("/jd/jd_grid");
		PageHelper.startPage(pageNum, pageSize);
		List<UserBean> userList = userService.listByName("");
		PageInfo<UserBean> page = new PageInfo<>(userList);
		view.addObject("page", page);
		return view;
	}*/

	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("editForm") UserBean form, Model model, ModelMap map) {
		form.setDate(new Date());
		userService.addUser(form);
		ModelAndView view = new ModelAndView("/user/user_management");
		view.addObject(form);
		return view;
	}

	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("editForm") UserBean form, Model model, ModelMap map) {
		form.setDate(new Date());
		userService.editUser(form);
		ModelAndView view = new ModelAndView("/jd/jd_management");
		view.addObject(form);
		return view;
	}

	// mysql缓存，上一页，下一页或更改每页记录数时，keyword为空
	@RequestMapping(value = "/username/listPage", method = RequestMethod.POST)
	public  ModelAndView listPage(String username,int pageNum,@RequestParam(value="pageSize",defaultValue="5")int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserBean> userBean = userService.listByName(username);
		PageInfo<UserBean> page = new PageInfo<>(userBean);
		ModelAndView view = new ModelAndView("/user/user_management");
		view.addObject("name", username);
		view.addObject("page", page);
		return view;

	}
/*	@RequestMapping(value = "/username/listPage", method = RequestMethod.POST)
	public  ModelAndView listPage(String username,int pageNum,@RequestParam(value="pageSize",defaultValue="5")int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserBean> userBean = userService.listByName(username);
		PageInfo<UserBean> page = new PageInfo<>(userBean);
		ModelAndView view = new ModelAndView("/jd/jd_management");
		view.addObject("username", username);
		view.addObject("page", page);
		return view;
	}*/

	@RequestMapping(value = "/edit/delete", method = RequestMethod.POST)
	public ModelAndView close(int id) {
		ModelAndView view = new ModelAndView("/user/user_management");
		userService.deleteUser(id);
		return view;

	}

}
