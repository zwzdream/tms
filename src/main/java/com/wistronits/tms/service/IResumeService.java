package com.wistronits.tms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.entity.ResumeBean;

public interface IResumeService {

	public Boolean addResource(ResumeBean resource, MultipartFile file);
	public Map<String,Object> searchResource(String keyWord, int offSet, int pageSize);
	public List<ResumeBean> getAllResources(int offSet, int pageSize);
	public int getAllResourcesCount();
	public int getCurrentWeekCount();
	public boolean deleteResource(int resourceId);
	public ResumeBean getResourceById(int resourceId);
	public Boolean editResource(ResumeBean resource, MultipartFile file);
	public Map<String,Object> getCanJoinResources(int offSet, int pageSize,int no);
	public Map<String,Object> getTheBelongResources(int offSet, int pageSize,int no);
	public Map<String,Object> getCanJoinJDs(int rid);
	public Map<String,Object> getTheBelongJDs(int rid);
	public int addResourceToJD(int no,int rid);
	public int deleteResourceFromJD(int no,int rid);
	public ArrayList<String> transferToswf(String filePath);
	public Map<String, Object> searchCanJoinResource(String keyWord,int no);
	

	
}
