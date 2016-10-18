package com.zekee.common.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zekee.common.dao.IJDBeanDao;
import com.zekee.common.persistence.JDBean;
import com.zekee.common.service.IJDBeanService;

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
	

}
