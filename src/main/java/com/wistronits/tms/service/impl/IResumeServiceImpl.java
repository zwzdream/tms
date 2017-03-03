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
import com.wistronits.tms.entity.JDBean;
import com.wistronits.tms.entity.ResumeBean;
import com.wistronits.tms.service.IResumeService;
import com.wistronits.tms.util.LuceneIndexer;
import com.wistronits.tms.util.ViewOfficeOnline;
@Service("resumeService")
public class IResumeServiceImpl implements IResumeService {

	@Autowired
	private IResumeDao iResumeDao;
	@Resource
	private IJDBeanDao jdBeanDao;

	@Override
	public Boolean addResource(ResumeBean resource, MultipartFile file) {
		try {
			File localFile = uploadFile(file);
			resource.setFilePath(localFile.getAbsolutePath());
			System.out.println(localFile.getAbsolutePath());
			int cnt =  iResumeDao.addResource(resource);
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
	public List<ResumeBean> getAllResources(int offSet, int pageSize) {
		PageHelper.startPage(getCurrentPageNum(offSet,pageSize), pageSize);
		List<ResumeBean> resourceBeans = iResumeDao.getAllResources();
		return resourceBeans;
	}
	
	@Override
	public int getAllResourcesCount() {
		int count = iResumeDao.getAllResourcesCount();
		return count;
	}
	
	@Override
	public Map<String,Object> searchResource(String keyWord, int offSet, int pageSize) {
		List<ResumeBean> beans = new ArrayList<ResumeBean>();
		Map<String,Object> resultMap= LuceneIndexer.seacher(keyWord,getCurrentPageNum(offSet,pageSize),pageSize);
		@SuppressWarnings("unchecked")
		List<Integer> RListIds = (List<Integer>) resultMap.get("resourceList");
	
		int count = (int) resultMap.get("count");
		if(!RListIds.isEmpty()){
			beans.addAll(iResumeDao.getResourcesByIds(RListIds));
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("list", beans);
		ret.put("count", count);
		return ret;
	}

	@Override
	public Map<String,Object> searchCanJoinResource(String keyWord,int no) {
		List<ResumeBean> beans = new ArrayList<ResumeBean>();
		Map<String,Object> resultMap= LuceneIndexer.seacherAll(keyWord);
		@SuppressWarnings("unchecked")
		List<Integer> addListIds = (List<Integer>) resultMap.get("addList");
		int count = (int) resultMap.get("count");
		List<Integer> haveAddIds=iResumeDao.getAddIdsByNo(no);
		addListIds.removeAll(haveAddIds);
		if(!addListIds.isEmpty()){
			beans=iResumeDao.getResourcesByIds(addListIds);
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("list", beans);
		ret.put("count", count);
		return ret;
	}
	

	 public int getCurrentWeekCount() {
		return  this.iResumeDao.getCurrentWeekCount();
	
	}
	
	public int getCurrentPageNum(int offSet, int pageSize){
		return offSet/pageSize+1;
	}
	
	public boolean deleteResource(int resourceId) {
		int count = iResumeDao.deleteResource(resourceId);
		if(count!=1){
			return false;
		}
		LuceneIndexer.deleteIndex(resourceId);
		return true;
	}

	
	@Override
	public ResumeBean getResourceById(int resourceId) {
		
		return iResumeDao.getResourceById(resourceId);
	}
	
	@Override
	public Boolean editResource(ResumeBean resource,MultipartFile file) {
		try {
			File localFile = uploadFile(file);
			resource.setFilePath(localFile.getAbsolutePath());
			int cnt =  iResumeDao.editResource(resource);
			if(cnt<=0){
				return false;
			}
			//String filePath=ViewOfficeOnline.toTransferString(resource.getFilePath());
			LuceneIndexer.deleteIndex(resource.getId());
			LuceneIndexer.createIndexer(localFile, resource.getId());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> getCanJoinResources(int offSet, int pageSize,int no) {
		List<ResumeBean> resources = new ArrayList<ResumeBean>();
		PageHelper.startPage(getCurrentPageNum(offSet,pageSize), pageSize);
		List<ResumeBean> returnResources = iResumeDao.haveNotBeans(no);
		resources= iResumeDao.haveNotBeans(no);
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("resources", returnResources);
		resRe.put("count", resources.size());
		
		return resRe;
	}
	@Override
	public Map<String, Object> getTheBelongResources(int offSet,int pageSize,int no) {
		List<ResumeBean> resources = new ArrayList<ResumeBean>();
		PageHelper.startPage(getCurrentPageNum(offSet,pageSize), pageSize);
		List<ResumeBean> returnResources = iResumeDao.haveBeans(no);
		resources = iResumeDao.haveBeans(no);
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("resources", returnResources);
		resRe.put("count", resources.size());
		return resRe;
	}

	@Override
	public int addResourceToJD(int no, int rid) {
		int resultCount=0;
		resultCount=iResumeDao.addResourceToJD(no, rid);
		
		return resultCount;
	}
    @Override
	public int deleteResourceFromJD(int no, int rid) {
		        int resultCount=0;
				resultCount=	iResumeDao.deleteResourceFromJD(no, rid);
				return resultCount;
	}
	@Override
	public Map<String, Object> getCanJoinJDs(int rid) {
		List<JDBean> jdList=new ArrayList<JDBean>();
		jdList=jdBeanDao.getJDsNotInAdd(rid);
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("jdList", jdList);
		return resRe;
	}
	@Override
	public Map<String, Object> getTheBelongJDs(int rid) {
		List<JDBean> jdList=new ArrayList<JDBean>();
		jdList=jdBeanDao.getJDsInAdd(rid);
		Map<String,Object> resRe = new HashMap<String,Object>();
		resRe.put("jdList", jdList);
		return resRe;
	}
	@Override
	public String transferToswf(String filePath) {
		String fileString=ViewOfficeOnline.toTransferString(filePath);
		String fileName=fileString.substring(0, fileString.lastIndexOf("."));
		String casefile1=fileName+".swf";
		String casefile2=fileName+".pdf";
		File file1=new File(casefile1);
		File file2=new File(casefile2);
		if(!file1.exists()){
			if(!file2.exists()){
				ViewOfficeOnline.setFileString(fileString,fileName);
				ViewOfficeOnline.office2pdf();
				 ViewOfficeOnline.pdf2swf();
			}else{
			ViewOfficeOnline.setFileString(fileName);
			 ViewOfficeOnline.pdf2swf();
			}
		}
	
		 return fileName.substring(fileName.lastIndexOf("/")+1);
	}

	

	

	

	
	
	
	
	

}
