package com.wistronits.tms.web;

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
import com.wistronits.tms.entity.GroupBean;
import com.wistronits.tms.service.IGroupService;



@Controller
@RequestMapping("/Group")
public class GroupManagementController {
	@Resource
	private IGroupService groupService;

	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	public String toAdd(Model model) {

		return "/group/group_add";
	}

	@RequestMapping(value = "/toEdit/{id}", method = RequestMethod.POST)
	public ModelAndView toEdit(@PathVariable int id) {
		ModelAndView view = new ModelAndView("/group/group_edit");	
		view.addObject("group", groupService.getGroupById(id));
		return view;
	}



	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("editForm") GroupBean form, Model model, ModelMap map) {
		groupService.addGroup(form);
		ModelAndView view = new ModelAndView("/group/group_management");
		view.addObject(form);
		return view;
	}


	@RequestMapping(value = "/group/list", method = RequestMethod.GET)
	@ResponseBody
	public  Map<Object,Object> list(@RequestParam(value="name") String name,
			@RequestParam(value="sEcho") String sEcho,
			@RequestParam(value="iDisplayStart") int offSet,
			@RequestParam(value="iDisplayLength") int pageSize) {
		int pageNum=offSet/pageSize+1;
		PageHelper.startPage(pageNum, pageSize);
		List<GroupBean> groupBean = groupService.listByName(name);
		List<GroupBean> groupBean1 = groupService.listByName(name);
        Map<Object,Object> map=new HashMap<>();
        map.put("aaData", groupBean);
        map.put("iTotalDisplayRecords", groupBean1.size());
        map.put("iTotalRecords", groupBean1.size());
        map.put("sEcho", sEcho);
		return map;
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable int id) {
		//ModelAndView view = new ModelAndView("/user/user_management");
		ModelAndView view = new ModelAndView("/group/group_management");
		groupService.deleteGroup(id);
		return view;
		
	}
	
	
	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("editForm") GroupBean form, Model model, ModelMap map) {
		groupService.editGroup(form);
		ModelAndView view = new ModelAndView("/group/group_management");
		//view.addObject(form);
		return view;
	}
	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
	public ModelAndView editUser(@PathVariable int id) {
		ModelAndView view = new ModelAndView("/group/group_user");
		view.addObject("groupId", groupService.getGroupById(id).getId());
		view.addObject("groupName", groupService.getGroupById(id).getName());
		return view;
	}

}
