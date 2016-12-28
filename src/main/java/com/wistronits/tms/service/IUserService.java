package com.wistronits.tms.service;

import java.util.ArrayList;
import java.util.Date;

import com.wistronits.tms.entity.UserBean;

public interface IUserService {
	public UserBean getUserById(int id);
	public void addUser(UserBean userBean);
	public void editUser(UserBean userBean);
	public void updateRoot(int uid,int gid,Date date);
	public void deleteUser(int id);
	public ArrayList<UserBean> listByName(String username);
	public ArrayList<UserBean> getAllNotIncludedUser(int gid);
	public ArrayList<UserBean> getAllIncludedUser(int gid);
	public void addUserToGroup(int uid,int gid);
	public void removeUserToGroup(int uid,int gid);
	public int getHighestRoot(int id);
}
