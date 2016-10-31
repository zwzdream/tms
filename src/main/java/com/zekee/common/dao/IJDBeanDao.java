package com.zekee.common.dao;

import java.util.ArrayList;
import java.util.Date;

import com.zekee.common.persistence.JDBean;

public interface IJDBeanDao {
	public void saveJDBean(JDBean jdBean);
	public void editJDBean(JDBean jdBean);
	public ArrayList<JDBean> listByKeyword(String keyword);
	public JDBean getJD(int no);
	public void closeJD(int no,Date date);
	public ArrayList<JDBean> listAll();

}
