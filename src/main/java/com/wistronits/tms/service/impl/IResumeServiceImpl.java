package com.wistronits.tms.service.impl;

import java.io.File;
import java.io.IOException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wistronits.tms.dao.IResumeDao;
import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.service.IResumeService;
import com.wistronits.tms.util.LuceneIndexer;
@Service("resumeService")
public class IResumeServiceImpl implements IResumeService {

	@Autowired
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
			int resourceId =  rDao.insertResource(resource);
			if(resourceId<=0){
				return false;
			}
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
