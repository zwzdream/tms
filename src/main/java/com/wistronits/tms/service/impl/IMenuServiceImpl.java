package com.wistronits.tms.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wistronits.tms.dao.IMenuDao;
import com.wistronits.tms.dao.IUserDao;
import com.wistronits.tms.entity.MenuBean;
import com.wistronits.tms.entity.UserBean;
import com.wistronits.tms.service.IMenuService;
import com.wistronits.tms.service.IUserService;

@Service("menuService")
public class IMenuServiceImpl implements IMenuService {

	@Resource
	private IMenuDao menuDao;
	@Override
	public ArrayList<MenuBean> listByGroupId(int gid) {
		return this.menuDao.listByGroupId(gid);
	}

	@Override
	public ArrayList<MenuBean> listByParentId(int pid) {
		return this.menuDao.listByParentId(pid);
	}

	@Override
	public MenuBean getMenuById(int id) {
		return this.menuDao.getMenuById(id);
	}
	
}
