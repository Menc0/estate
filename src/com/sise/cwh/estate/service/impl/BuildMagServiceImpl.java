package com.sise.cwh.estate.service.impl;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.dao.BuildMagDao;
import com.sise.cwh.estate.dao.HouseMagDao;
import com.sise.cwh.estate.entity.Buildmag;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.service.BuildMagService;
import com.sise.cwh.estate.util.Pagination;

public class BuildMagServiceImpl implements BuildMagService{

	private BuildMagDao buildMagDao;
	private HouseMagDao houseMagDao;
	
	public void setBuildMagDao(BuildMagDao buildMagDao) {
		this.buildMagDao = buildMagDao;
	}
	
	

	public void setHouseMagDao(HouseMagDao houseMagDao) {
		this.houseMagDao = houseMagDao;
	}



	public HouseMagDao getHouseMagDao() {
		return houseMagDao;
	}



	@Override
	public void saveBuildInfo(Buildmag buildmag) {
		this.buildMagDao.save(buildmag);
		
	}

	@Override
	public void deleteBuildInfo(String bldNos) {
		String []bldNo = bldNos.split(",");
		for(int i=0;i<bldNo.length;i++){
		buildMagDao.delete(bldNo[i]);
		houseMagDao.deleteHosInfoByBldNo(bldNo[i]);
		}
		
	}

	@Override
	public List<Map<String, Object>> queryBuildInfoBy(String bldNo,
			String bldNm, Pagination pg) {
		// TODO Auto-generated method stub
		return buildMagDao.queryBuildInfoBy(bldNo, bldNm, pg);
	}

}
