package com.wistronits.tms.dao;

import java.util.ArrayList;

import com.wistronits.tms.entity.MenuBean;

public interface IMenuDao {
	public ArrayList<MenuBean> listByGroupId(int gid);
	public ArrayList<MenuBean> listByParentId(int pid);
	public MenuBean getMenuById(int id);


}
