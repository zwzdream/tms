package com.wistronits.tms.service;

import java.util.ArrayList;

import com.wistronits.tms.entity.UserBean;

public interface IUserService {
	public UserBean getUserById(int id);
	public void addUser(UserBean userBean);
	public void editUser(UserBean userBean);
	public void deleteUser(int id);
	public ArrayList<UserBean> listByName(String username);

}
