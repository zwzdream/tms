package com.wistronits.tms.dao;

import java.util.List;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;

public interface IResumeDao {

	 public Boolean addResume(ResumeBean rDto);
	 public int insertResource(ImportResourceBean resource);
	 public List<ResumeBean> getAllResume();
	 public List<ImportResourceBean> getAllImportBeans();
	 public List<ImportResourceBean> getResourceByIds(List<Integer> ids);
	 public int getAllImportBeansCount();

}
