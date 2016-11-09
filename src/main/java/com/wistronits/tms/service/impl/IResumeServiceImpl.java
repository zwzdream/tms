package com.wistronits.tms.service.impl;

import java.io.File;
import java.io.IOException;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.wistronits.tms.dao.IResumeDao;
import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.service.IResumeService;
import com.wistronits.tms.util.LuceneIndexer;
@Service("resumeService")
public class IResumeServiceImpl implements IResumeService {

	@Autowired
	private IResumeDao iResumeDao;
	@Override
	public void saveResume(ResumeBean rDto) {
		// TODO Auto-generated method stub
		this.iResumeDao.saveResume(rDto);
	}
	@Override
	public Boolean importResource(ImportResourceBean resource, MultipartFile file) {
		try {
			File localFile = uploadFile(file);
			resource.setFilePath(localFile.getAbsolutePath());
			int cnt =  iResumeDao.insertResource(resource);
			if(cnt<=0){
				return false;
			}
			LuceneIndexer.createIndexer(localFile, resource.getId());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public File uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		String path = LuceneIndexer.UPLOAD_FOLDER_PATH + "/" + fileName;
		File fileDir = new File(LuceneIndexer.UPLOAD_FOLDER_PATH);
		if(!fileDir.exists() && !fileDir.isDirectory()){
			fileDir.mkdir();
		}
		File localFile = new File(path);
		file.transferTo(localFile);
		return localFile;
	}
	@Override
	public List<ImportResourceBean> getAllImportBeans(int offSet,int pageSize) {
		PageHelper.startPage(getCurrentPageNum(offSet,pageSize), pageSize);
		List<ImportResourceBean> importBeans = iResumeDao.getAllImportBeans();
		return importBeans;
	}
	@Override
	public Map<String,Object> searchResource(String keyWord, int offSet, int pageSize) {
		List<ImportResourceBean> beans = new ArrayList<ImportResourceBean>();
		Map<String,Object> resultMap= LuceneIndexer.seacher(keyWord,getCurrentPageNum(offSet,pageSize),pageSize);
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) resultMap.get("list");
		int count = (int) resultMap.get("count");
		if(!ids.isEmpty()){
			beans = iResumeDao.getResourceByIds(ids);
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("list", beans);
		ret.put("count", count);
		return ret;
	}
	@Override
	public int getAllImportBeansCount() {
		int count = iResumeDao.getAllImportBeansCount();
		return count;
	}
	
	public int getCurrentPageNum(int offSet, int pageSize){
		return offSet/pageSize+1;
	}
}