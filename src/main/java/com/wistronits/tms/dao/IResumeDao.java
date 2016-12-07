package com.wistronits.tms.dao;

import java.util.List;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;

public interface IResumeDao {

	public int addResume(ResumeBean rDto);
	public int insertResource(ImportResourceBean resource);
	public List<ResumeBean> getAllResume();
	public List<ImportResourceBean> getAllImportBeans();
	public List<ImportResourceBean> getImportResourceByIds(List<Integer> ids);
	public int getAllImportBeansCount();
	public List<ImportResourceBean> getAddResourceByIds(List<Integer> ids);
	public List<ImportResourceBean> getAllBeans();
	public int getAllBeansCount();
	public int deleteImportResource(int resourceId);
	public int deleteResume(int resourceId);
	public ImportResourceBean getImportResourceById(int resourceId);
	public ResumeBean getResumeById(int resourceId);
	public int editImportResource(ImportResourceBean resource);
	public int editResume(ResumeBean rDto);

}
