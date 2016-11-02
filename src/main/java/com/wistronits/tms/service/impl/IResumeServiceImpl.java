package com.wistronits.tms.service.impl;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.dao.IResumeDao;
import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.service.IResumeService;
import com.wistronits.tms.util.LuceneIndexer;
@Service("resumeService")
public class IResumeServiceImpl implements IResumeService {

	@Resource
	private IResumeDao rDao;
	@Override
	public void saveResume(ResumeBean rDto) {
		// TODO Auto-generated method stub
		this.rDao.saveResume(rDto);
	}
	@Override
	public Boolean importResource(ImportResourceBean resource) {
		try {
			String filePath = uploadFile(resource.getInputFile());
			resource.setFilePath(filePath);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException{
		String fileName = file.getOriginalFilename();
		String path = "D:/uploadFiles/" + fileName;
		File fileDir = new File("D:/uploadFiles");
		if(!fileDir.exists() && !fileDir.isDirectory()){
			fileDir.mkdir();
		}
		File localFile = new File(path);
		file.transferTo(localFile);
		LuceneIndexer.createIndexer(localFile);
		return path;
	}
}
