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
		this.jdBeanDao.saveJDBean(jdBean);
		
	}
	@Override
	public void editJDBean(JDBean jdBean) {
		this.jdBeanDao.editJDBean(jdBean);
		
	}
	@Override
	public ArrayList<JDBean> listByKeyword(String keyword) {
		return 	this.jdBeanDao.listByKeyword(keyword);
	}
	@Override
	public JDBean getJD(int no) {
		return this.jdBeanDao.getJD(no);
	}
	@Override
	public void closeJD(int no,Date date) {
		this.jdBeanDao.closeJD(no,date);
		
	}
	@Override
	public void deleteJD(int no) {
		this.jdBeanDao.deleteJD(no);
	}
	@Override
	public ArrayList<JDBean> listAll() {
		return this.jdBeanDao.listAll();
	}
	@Override
	public int listCurrentWeekCount() {
		return this.jdBeanDao.listCurrentWeekCount();
	}
	

}
