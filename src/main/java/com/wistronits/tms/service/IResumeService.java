package com.wistronits.tms.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;

public interface IResumeService {

	public Boolean addResume(ResumeBean rDto);
	public Boolean importResource(ImportResourceBean resource, MultipartFile file);
	public List<ImportResourceBean> getAllImportBeans(int offSet, int pageSize);
	public Map<String,Object> searchResource(String keyWord, int offSet, int pageSize);
	public int getAllImportBeansCount();
	public List<ImportResourceBean> getAllBeans(int offSet, int pageSize);
	public int getAllBeansCount();
	public int getCurrentWeekCount();
	public boolean deleteImportResource(int resourceId);
	public boolean deleteResume(int resourceId);
	public ImportResourceBean getImportResourceById(int resourceId);
	public ResumeBean getResumeById(int resourceId);
	public Boolean editImportResource(ImportResourceBean resource,MultipartFile file);
	public Boolean editResume(ResumeBean rDto);
	public Map<String,Object> getCanJoinResources(int offSet, int pageSize,int no);
	public Map<String,Object> getTheBelongResources(int offSet, int pageSize,int no);
	public Map<String,Object> getCanJoinJDs(int rid,String type);
	public Map<String,Object> getTheBelongJDs(int rid,String type);
	public int editTheBelongResource(int no,int rid,String type);
	public int addResourceToJD(int no,int rid,String type);
	public int deleteResourceFromJD(int no,int rid,String type);
	public String transferToswf(String filePath);
	public Map<String, Object> searchCanJoinResource(String keyWord,int no);

	
}
