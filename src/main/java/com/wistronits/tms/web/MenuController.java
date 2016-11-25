package com.wistronits.tms.web;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wistronits.tms.entity.GroupBean;
import com.wistronits.tms.entity.MenuBean;
import com.wistronits.tms.service.IGroupService;
import com.wistronits.tms.service.IMenuService;

    @Controller
    @RequestMapping("/Menu")
    public class MenuController {
  	@Resource
  	private IGroupService groupService;
  	@Resource
  	private IMenuService menuService;

	
	
	@RequestMapping(value = "/getMenuByGroupName", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MenuBean> getMenuByGroupName(@RequestParam(value = "id",defaultValue="-1",required=false)int id,
			@RequestParam(value = "name",required=false)String name,
			@RequestParam(value = "level",defaultValue="-1",required=false)int level,
			@RequestParam(value = "groupName")String gName){

		
		  ArrayList<MenuBean> menuList=new ArrayList<MenuBean>();
    if(id==-1&&level==-1&&gName!=null){
	  ArrayList<GroupBean> groupList=	groupService.listByName(gName);
	  for(GroupBean groupBean : groupList){	  
	  ArrayList<MenuBean> menu=	menuService.listByGroupId(groupBean.getId());
	     menuList.addAll(menu);
	    }
  }else if(id!=-1&&level!=-1&&gName!=null){
	  ArrayList<MenuBean> menu=menuService.listByParentId(id);
	  menuList.addAll(menu);
  }
	  return menuList;
		
		
	}

}
