package com.wistronits.tms.dao;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;

public interface IResumeDao {

	 public void saveResume(ResumeBean rDto);
	 public int insertResource(ImportResourceBean resource);
}
