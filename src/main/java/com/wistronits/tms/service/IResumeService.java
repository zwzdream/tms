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
	public boolean deleteImportResource(int resourceId);
	public boolean deleteResume(int resourceId);
	public ImportResourceBean getImportResourceById(int resourceId);
	public ResumeBean getResumeById(int resourceId);
	public Boolean editImportResource(ImportResourceBean resource,MultipartFile file);
	public Boolean editResume(ResumeBean rDto);
}
