package com.wistronits.tms.service.impl;

import javax.annotation.Resource;

import com.wistronits.tms.dao.IResumeDao;
import com.wistronits.tms.entity.ResumeDto;
import com.wistronits.tms.service.IResumeService;

public class IResumeServiceImpl implements IResumeService {

	@Resource
	private IResumeDao rDao;
	@Override
	public void saveResume(ResumeDto rDto) {
		// TODO Auto-generated method stub
		this.rDao.saveResume(rDto);
	}

}
