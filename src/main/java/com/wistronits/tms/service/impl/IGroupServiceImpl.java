package com.wistronits.tms.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wistronits.tms.dao.IGroupDao;
import com.wistronits.tms.entity.GroupBean;
import com.wistronits.tms.service.IGroupService;

@Service("groupService")
public class IGroupServiceImpl implements IGroupService {
 @Resource
 private IGroupDao groupDao;

@Override
public GroupBean getGroupById(int id) {
	return this.groupDao.getGroupById(id);
}

@Override
public void addGroup(GroupBean groupBean) {
 this.groupDao.addGroup(groupBean);	
}

@Override
public void editGroup(GroupBean groupBean) {
	this.groupDao.editGroup(groupBean);
}

@Override
public void deleteGroup(int id) {
this.groupDao.deleteGroup(id);	
}

@Override
public ArrayList<GroupBean> listByName(String username) {
	return this.groupDao.listByName(username);
}

@Override
public ArrayList<GroupBean> getCanJoinGroups(int uid) {
	return this.groupDao.getCanJoinGroups(uid);
}

@Override
public GroupBean getTheBelongGroup(int uid) {
	return this.groupDao.getTheBelongGroup(uid);
}

@Override
public int editTheBelongGroup(int uid, int gid) {
	 return this.groupDao.editTheBelongGroup(uid, gid);
}
}
