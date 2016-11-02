package com.wistronits.tms.service;

import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;

public interface IResumeService {

	public void saveResume(ResumeBean rDto);
	public Boolean importResource(ImportResourceBean resource, MultipartFile file);
}
