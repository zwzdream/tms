package com.wistronits.tms.service;

import java.util.ArrayList;
import java.util.Date;

import com.wistronits.tms.entity.JDBean;



public interface IJDBeanService {
	public void saveJDBean(JDBean jdBean);
	public void editJDBean(JDBean jdBean);
	public ArrayList<JDBean> listByKeyword(String keyword);
	public JDBean getJD(int no);
	public void closeJD(int no,Date date);
	public ArrayList<JDBean> listAll();
	public int listCurrentWeekCount();

}
