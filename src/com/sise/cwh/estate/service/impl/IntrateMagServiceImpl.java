package com.sise.cwh.estate.service.impl;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.dao.IntrateMagDao;
import com.sise.cwh.estate.entity.IntrateMag;
import com.sise.cwh.estate.service.IntrateMagService;

public class IntrateMagServiceImpl implements IntrateMagService{

	private IntrateMagDao intrateMagDao;
	
	public void setIntrateMagDao(IntrateMagDao intrateMagDao) {
		this.intrateMagDao = intrateMagDao;
	}

	@Override
	public List<IntrateMag> queryAllIntrateMag() {
		return intrateMagDao.getAll();
	}

	@Override
	public void save(IntrateMag intrateMag) {
		intrateMagDao.save(intrateMag);
	}

	@Override
	public void delete(String intrateIds) {
		String []intrateId = intrateIds.split(",");
		for(int i=0;i<intrateId.length;i++){
			intrateMagDao.delete(Integer.valueOf(intrateId[i]));
		}
	}

}
