package com.wistronits.tms.service.impl;

import java.util.ArrayList;

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
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
	}

	@Override
	public ArrayList<UserBean> listByName(String username) {
		return this.userDao.listByName(username);
	}

}
