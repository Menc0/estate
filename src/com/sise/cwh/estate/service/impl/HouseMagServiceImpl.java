package com.sise.cwh.estate.service.impl;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.dao.HouseMagDao;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.entity.HousemagId;
import com.sise.cwh.estate.service.HouseMagService;
import com.sise.cwh.estate.util.Pagination;

public class HouseMagServiceImpl implements HouseMagService{
	private HouseMagDao houseMagDao;
	
	public void setHouseMagDao(HouseMagDao houseMagDao) {
		this.houseMagDao = houseMagDao;
	}


	@Override
	public List<Map<String, Object>> queryHouseInfoBy(String hosNo,
														String bldNo,
														String hosAreaMin,
														String hosAreaMax,
														String hosTp,
														String sellPceMin,
														String sellPceMax,
														Pagination pg){
		// TODO Auto-generated method stub
		return houseMagDao.queryHouseInfoBy(hosNo,bldNo,hosAreaMin,hosAreaMax,hosTp,sellPceMin,sellPceMax,pg);
	}

	@Override
	public void saveHouseInfo(Housemag housemag) {
		this.houseMagDao.save(housemag);
		
	}

	@Override
	public void deleteHouseInfo(String hosNos) {
		String []hosNos1 = hosNos.split(",");
		for(int i=0;i<hosNos1.length;i++){
			String []houseMagId = hosNos1[i].toString().split(";");
			HousemagId housemagId2 =new HousemagId(houseMagId[1],houseMagId[0]);
			houseMagDao.deleteHouseInfo(housemagId2);
		}
		
	}


	@Override
	public List<Map<String, Object>> queryAllBuild() {
		
		return houseMagDao.queryAllBuild();
	}


	@Override
	public List<Map<String, Object>> queryHouseInfoByBig(String hosNo, String bldNo,
			String hosAreaMin, String hosAreaMax, String hosTp,
			String sellPceMin, String sellPceMax, String costPceMin,
			String costPceMax, String dscntRate, String houseSell,
			Pagination pg) {
		// TODO Auto-generated method stub
		return houseMagDao.queryInfoBy(hosNo, bldNo, hosAreaMin, hosAreaMax, hosTp, sellPceMin, sellPceMax, costPceMin, costPceMax, dscntRate,houseSell, pg);
	}


	@Override
	public Map<String, Object> queryHosIntroByHosNo(String hosNo) {
		return houseMagDao.queryHosIntroByHosNo(hosNo).get(0);
	}


	public Map<String, Object>queryHosSellByHosNo(String hosNo){
		return houseMagDao.queryHosSellByHosNo(hosNo).get(0);
	}
	
	@Override
	public List<Housemag> getAllHousemags() {
		return houseMagDao.getAll();
	}
	
	

}
