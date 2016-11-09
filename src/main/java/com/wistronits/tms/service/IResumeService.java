package com.wistronits.tms.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;

public interface IResumeService {

	public void saveResume(ResumeBean rDto);
	public Boolean importResource(ImportResourceBean resource, MultipartFile file);
	public List<ImportResourceBean> getAllImportBeans(int offSet, int pageSize);
	public Map<String,Object> searchResource(String keyWord, int offSet, int pageSize);
	public int getAllImportBeansCount();
}