package com.wistronits.tms.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wistronits.tms.dao.IUserDao;
import com.wistronits.tms.entity.UserBean;
import com.wistronits.tms.service.IUserService;

@Service("userService")
public class IUserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Override
	public UserBean getUserById(int id){
		return this.userDao.getUserById(id);
	}

	@Override
	public void addUser(UserBean userBean) {
		this.userDao.addUser(userBean);
	}

	@Override
	public void editUser(UserBean userBean) {
		this.userDao.editUser(userBean);
	}
	
	@Override
	public void updateRoot(int uid,int gid,Date date){
		this.userDao.updateRoot(uid, gid, date);
	}

	@Override
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
	}

	@Override
	public ArrayList<UserBean> listByName(String username) {
		return this.userDao.listByName(username);
	}
	
	@Override
	public ArrayList<UserBean> listAll() {
	
		return this.userDao.listAll();
	}

	@Override
	public ArrayList<UserBean> getAllNotIncludedUser(int gid) {
		return this.userDao.getAllNotIncludedUser(gid);
	}
	@Override
	public ArrayList<UserBean> getAllIncludedUser(int gid) {
		return this.userDao.getAllIncludedUser(gid);
	}

	@Override
	public void addUserToGroup(int uid, int gid) {
    this.userDao.addUserToGroup(uid, gid);		
	}

	@Override
	public void removeUserToGroup(int uid, int gid) {
      this.userDao.removeUserToGroup(uid, gid);		
	}

	@Override
	public int getHighestRoot(int id) {
		return this.userDao.getHighestRoot(id);
	}

	

}
