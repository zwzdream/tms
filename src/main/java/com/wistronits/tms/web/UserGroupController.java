package com.wistronits.tms.web;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wistronits.tms.entity.GroupBean;
import com.wistronits.tms.entity.UserBean;
import com.wistronits.tms.service.IGroupService;
import com.wistronits.tms.service.IUserService;

    @Controller
    @RequestMapping("/UserGroup")
    public class UserGroupController {
  	@Resource
	private IUserService userService;
  	@Resource
  	private IGroupService groupService;

	@RequestMapping(value = "/getAllNotIncludedUser", method = RequestMethod.POST)
	public @ResponseBody ArrayList<UserBean> getAllNotIncludedUser(@RequestParam(value = "groupId") int groupId) {
		return userService.getAllNotIncludedUser(groupId);

	}
	@RequestMapping(value = "/getAllIncludedUser", method = RequestMethod.POST)
	public @ResponseBody ArrayList<UserBean> getAllIncludedUser(@RequestParam(value = "groupId") int groupId) {
		return userService.getAllIncludedUser(groupId);
		
	}
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public ModelAndView addRole(@RequestParam(value = "userId") int uid,@RequestParam(value = "groupId") int gid) {
		userService.addUserToGroup(uid, gid);
		ModelAndView view = new ModelAndView("/group/group_user");
		 view.addObject("groupId", gid);
		return view;
	}
	@RequestMapping(value = "/removeRole", method = RequestMethod.POST)
	public ModelAndView removeRole(@RequestParam(value = "userId") int uid,@RequestParam(value = "groupId") int gid) {
		 userService.removeUserToGroup(uid, gid);
		 ModelAndView view = new ModelAndView("/group/group_user");
		 view.addObject("groupId", gid);
		return view;
	}
	
	@RequestMapping(value = "/getCanJoinGroups", method = RequestMethod.POST)
	public @ResponseBody ArrayList<GroupBean> getCanJoinGroups(@RequestParam(value = "userId") int userId) {
		return groupService.getCanJoinGroups(userId);

	}
	
	@RequestMapping(value = "/getTheBelongGroup", method = RequestMethod.POST)
	public @ResponseBody GroupBean getTheBelongGroup(@RequestParam(value = "userId") int userId) {
		return groupService.getTheBelongGroup(userId);

	}
	@RequestMapping(value = "/editTheBelongGroup", method = RequestMethod.POST)
	public ModelAndView editTheBelongGroup(@RequestParam(value = "userId") int uid,@RequestParam(value = "groupId") int gid) {
		int i=groupService.editTheBelongGroup(uid, gid);
		if(i==0 ){
			 userService.addUserToGroup(uid, gid);
		}
		 ModelAndView view = new ModelAndView("/user/user_group");
		 view.addObject("userId", uid);
		return view;
	}
	

}
