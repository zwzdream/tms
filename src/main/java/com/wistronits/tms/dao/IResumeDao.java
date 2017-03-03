package com.wistronits.tms.dao;

import java.util.List;
import java.util.Map;

import com.wistronits.tms.entity.ResumeBean;

public interface IResumeDao {

	//public int addResume(ResumeBean rDto);
	public int addResource(ResumeBean resource);
	//public int insertResource(ImportResourceBean resource);
	//public List<ResumeBean> getAllResume();
	//public List<ImportResourceBean> getAllImportBeans();
	public List<ResumeBean> getAllResources();
	//public List<ImportResourceBean> getImportResourceByIds(List<Integer> ids);
	//public List<ResumeBean> getAddResourceByIds(List<Integer> ids);
	public List<ResumeBean> getResourcesByIds(List<Integer> ids);

	public int getAllResourcesCount();
	public List<ResumeBean> getBeansByIds(Map<String, Object> mapList);
	public int getCurrentWeekCount();
	public int deleteResource(int resourceId);
	public ResumeBean getResourceById(int resourceId);
	public int editResource(ResumeBean resource);
	public List<ResumeBean> haveBeans(int no);
	public List<ResumeBean> haveNotBeans(int no);
	public int addResourceToJD(int no,int rid);
	public int deleteResourceFromJD(int no,int rid);
	public List<Integer>  getAddIdsByNo(int no);
	
	

}
