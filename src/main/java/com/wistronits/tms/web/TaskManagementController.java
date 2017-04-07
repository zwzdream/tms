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

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wistronits.tms.entity.JDBean;
import com.wistronits.tms.entity.TaskBean;
import com.wistronits.tms.entity.TaskNotes;
import com.wistronits.tms.service.IJDBeanService;
import com.wistronits.tms.service.ITaskService;
import com.wistronits.tms.service.IUserService;


@Controller
@RequestMapping("/Task")
public class TaskManagementController {
	@Resource
	private ITaskService taskService;
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	public String toAdd() {

		return "/task/task_add";
	}


	@RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	public ModelAndView toEdit(@RequestParam(value="id") int id) {
		ModelAndView view = new ModelAndView("/task/task_edit");
		TaskBean taskBean=taskService.getTaskById(id);
		view.addObject("task", taskBean);
		
		
		return view;
	}
	
	@RequestMapping(value = "/toAssign", method = RequestMethod.POST)
	public ModelAndView toAssign(@RequestParam(value="id") int id) {
		ModelAndView view = new ModelAndView("/task/assign_task");
		TaskBean taskBean=taskService.getTaskById(id);
		view.addObject("task", taskBean);
		return view;
	}

	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("taskForm") TaskBean form) {
		form.setCreate_time(new Date());
		form.setAssigneeName(userService.getUserById(form.getLast_assignee()).getUsername());
		taskService.saveTask(form);
		ModelAndView view = new ModelAndView("/task/task_management");
		return view;
	}

	@RequestMapping(value = "/assignTask/save", method = RequestMethod.POST)
	public ModelAndView assignTask(@ModelAttribute("noteForm") TaskNotes note) {
		note.setUpdate_time(new Date());
		note.setAssignee(userService.getUserById(note.getAssigneeId()).getUsername());
		taskService.assignTaskTo(note);
		ModelAndView view = new ModelAndView("/task/task_management");
		return view;
	}
	
	@RequestMapping(value = "/completeTask", method = RequestMethod.POST)
	public @ResponseBody Map<Object,Object> completeTask(@ModelAttribute("id") int id) {
		Map<Object,Object> returnData=new HashMap<Object,Object>();
		returnData.put("message",taskService.completeTask(id, new Date()));
		return returnData;
	}
	
	@RequestMapping(value = "/startTask", method = RequestMethod.POST)
	public @ResponseBody Map<Object,Object> startTask(@ModelAttribute("id") int id) {
		Map<Object,Object> returnData=new HashMap<Object,Object>();
		returnData.put("message",taskService.startTask(id, new Date()));
		return returnData;
	}
	
	@RequestMapping(value = "/edit/save", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("taskForm") TaskBean form) {
		form.setLast_update(new Date());
		form.setAssigneeName(userService.getUserById(form.getLast_assignee()).getUsername());
		taskService.editTask(form);
		ModelAndView view = new ModelAndView("/task/task_management");
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
		List<TaskBean> jdBean = taskService.listByKeyword(keyword);
		List<TaskBean> jdBean1 = taskService.listByKeyword(keyword);
	  Map<Object,Object> map =new HashMap<Object,Object>();
	  map.put("aaData", jdBean);
      map.put("iTotalDisplayRecords", jdBean1.size());
      map.put("iTotalRecords", jdBean1.size());
      map.put("sEcho", sEcho);
	   return map;	
	}

	@RequestMapping(value = "/edit/complete", method = RequestMethod.POST)
	public ModelAndView close(int id) {
		ModelAndView view = new ModelAndView("/jd/jd_management");
		taskService.completeTask(id, new Date());
		return view;

	}

}
