package com.wistronits.tms.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wistronits.tms.dao.IJDBeanDao;
import com.wistronits.tms.entity.JDBean;
import com.wistronits.tms.service.IJDBeanService;



@Service("jdBeanService")
public class IJDBeanServiceImpl implements IJDBeanService{
	
	@Resource
	private IJDBeanDao jdBeanDao;
	@Override	
	public void saveJDBean(JDBean jdBean) {
		// TODO Auto-generated method stub
		this.jdBeanDao.saveJDBean(jdBean);
		
	}
	@Override
	public void editJDBean(JDBean jdBean) {
		// TODO Auto-generated method stub
		this.jdBeanDao.editJDBean(jdBean);
		
	}
	@Override
	public ArrayList<JDBean> listByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return 	this.jdBeanDao.listByKeyword(keyword);
	}
	@Override
	public JDBean getJD(int no) {
		// TODO Auto-generated method stub
		
		return this.jdBeanDao.getJD(no);
	}
	@Override
	public void closeJD(int no,Date date) {
		// TODO Auto-generated method stub
		this.jdBeanDao.closeJD(no,date);
		
	}
	@Override
	public ArrayList<JDBean> listAll() {
		// TODO Auto-generated method stub
		return this.jdBeanDao.listAll();
	}
	@Override
	public int listCurrentWeekCount() {
		// TODO Auto-generated method stub
		return this.jdBeanDao.listCurrentWeekCount();
	}
	

}
