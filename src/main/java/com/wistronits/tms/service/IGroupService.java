package com.wistronits.tms.service;

import java.util.ArrayList;

import com.wistronits.tms.entity.GroupBean;

public interface IGroupService {
	public GroupBean getGroupById(int id);
	public void addGroup(GroupBean groupBean);
	public void editGroup(GroupBean groupBean);
	public void deleteGroup(int id);
	public ArrayList<GroupBean> listByName(String name);
	public ArrayList<GroupBean> getCanJoinGroups(int uid);
	public GroupBean getTheBelongGroup(int uid);
	public int editTheBelongGroup(int uid,int gid);

}
