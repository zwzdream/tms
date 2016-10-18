package com.zekee.common.dao;

import com.zekee.common.persistence.JDBean;

public interface IJDBeanDao {
	public void saveJDBean(JDBean jdBean);
	
	public void editJDBean(JDBean jdBean);

}
