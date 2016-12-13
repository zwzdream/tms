package com.wistronits.tms.service.impl;

import java.io.File;
import java.io.IOException;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.wistronits.tms.dao.IJDBeanDao;
import com.wistronits.tms.dao.IResumeDao;
import com.wistronits.tms.entity.ImportResourceBean;
import com.wistronits.tms.entity.JDBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.entity.RsResponse;
import com.wistronits.tms.service.IResumeService;
import com.wistronits.tms.util.LuceneIndexer;
@Service("resumeService")
public class IResumeServiceImpl implements IResumeService {

	@Autowired
	private IResumeDao iResumeDao;
	@Resource
	private IJDBeanDao jdBeanDao;
	@Override
	public Boolean addResume(ResumeBean rDto) {
		int cnt = iResumeDao.addResume(rDto);
		if(cnt<=0){
			return false;
		}
		LuceneIndexer.createIndexer(rDto.toString(), rDto.getId());
		return true;
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
		List<Integer> addListIds = (List<Integer>) resultMap.get("addList");
		@SuppressWarnings("unchecked")
		List<Integer> importListIds = (List<Integer>) resultMap.get("importList");
		int count = (int) resultMap.get("count");
		if(!importListIds.isEmpty()){
			beans = iResumeDao.getImportResourceByIds(importListIds);
		}
		if(!addListIds.isEmpty()){
			beans.addAll(iResumeDao.getAddResourceByIds(addListIds));
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
	 public int getCurrentWeekCount() {
		return  this.iResumeDao.getCurrentWeekCount();
	
	}
	
	public int getCurrentPageNum(int offSet, int pageSize){
		return offSet/pageSize+1;
	}
	@Override
	public List<ImportResourceBean> getAllBeans(int offSet, int pageSize) {
		PageHelper.startPage(getCurrentPageNum(offSet,pageSize), pageSize);
		List<ImportResourceBean> importBeans = iResumeDao.getAllBeans();
		return importBeans;
	}
	@Override
	public int getAllBeansCount() {
		int count = iResumeDao.getAllBeansCount();
		return count;
	}
	@Override
	public boolean deleteImportResource(int resourceId) {
		int count = iResumeDao.deleteImportResource(resourceId);
		if(count!=1){
			return false;
		}
		LuceneIndexer.deleteIndex(resourceId);
		return true;
	}
	@Override
	public boolean deleteResume(int resourceId) {
		int count = iResumeDao.deleteResume(resourceId);
		if(count!=1){
			return false;
		}
		LuceneIndexer.deleteIndex(resourceId);
		return true;
	}
	@Override
	public ImportResourceBean getImportResourceById(int resourceId) {
		return iResumeDao.getImportResourceById(resourceId);
	}
	@Override
	public ResumeBean getResumeById(int resourceId) {
		return iResumeDao.getResumeById(resourceId);
	}
	@Override
	public Boolean editImportResource(ImportResourceBean resource,MultipartFile file) {
		try {
			File localFile = uploadFile(file);
			resource.setFilePath(localFile.getAbsolutePath());
			int cnt =  iResumeDao.editImportResource(resource);
			if(cnt<=0){
				return false;
			}
			LuceneIndexer.deleteIndex(resource.getId());
			LuceneIndexer.createIndexer(localFile, resource.getId());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public Boolean editResume(ResumeBean rDto) {
		int cnt = iResumeDao.editResume(rDto);
		if(cnt<=0){
			return false;
		}
		LuceneIndexer.deleteIndex(rDto.getId());
		LuceneIndexer.createIndexer(rDto.toString(), rDto.getId());
		return true;
	}
	@Override
	public Map<String, Object> getCanJoinResources(int no) {
		List<ImportResourceBean> resources = new ArrayList<ImportResourceBean>();
		resources = iResumeDao.haveNotResumes(no);
		resources.addAll(iResumeDao.haveNotImportBeans(no));
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("resources", resources);
		return resRe;
	}
	@Override
	public Map<String, Object> getTheBelongResources(int no) {
		List<ImportResourceBean> resources = new ArrayList<ImportResourceBean>();
		resources = iResumeDao.haveResumes(no);
		resources.addAll(iResumeDao.haveImportBeans(no));
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("resources", resources);
		return resRe;
	}
	@Override
	public int editTheBelongResource(int no, int rid, String type) {
		int resultCount=0;
  if(type.equals("add")){
			resultCount=iResumeDao.editBelongResumeResource(no, rid);
		} else if(type.equals("import")){
			resultCount=iResumeDao.editBelongImportResource(no, rid);
		}
		return resultCount;
	}
	@Override
	public int addResourceToJD(int no, int rid, String type) {
		int resultCount=0;
   if(type.equals("add")){
		resultCount=iResumeDao.addResumeResourceToJD(no, rid);
		} else if(type.equals("import")){
			resultCount=iResumeDao.addImportResourceToJD(no, rid);
		}
		return resultCount;
	}
	@Override
	public int deleteResourceFromJD(int no, int rid, String type) {
		int resultCount=0;
		   if(type.equals("add")){
				resultCount=	iResumeDao.deleteResumeResourceFromJD(no, rid);
				} else if(type.equals("import")){
				resultCount=	iResumeDao.deleteImportResourceFromJD(no, rid);
				}
				return resultCount;
	}
	@Override
	public Map<String, Object> getCanJoinJDs(int rid, String type) {
		List<JDBean> jdList=new ArrayList<JDBean>();
		if(type.equals("add")){
			jdList=jdBeanDao.getJDsNotInAdd(rid);
		} else if(type.equals("import")){
			jdList=jdBeanDao.getJDsNotInImport(rid);
		}
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("jdList", jdList);
		return resRe;
	}
	@Override
	public Map<String, Object> getTheBelongJDs(int rid, String type) {
		List<JDBean> jdList=new ArrayList<JDBean>();
		if(type.equals("add")){
			jdList=jdBeanDao.getJDsInAdd(rid);
		} else if(type.equals("import")){
			jdList=jdBeanDao.getJDsInImport(rid);
		}
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("jdList", jdList);
		return resRe;
	}
	
	
	
	
	

}
