package com.sise.cwh.estate.service.impl;

import javax.annotation.Resource;

import com.sise.cwh.estate.dao.BystgPayCrcsDao;
import com.sise.cwh.estate.dao.CusInfoDao;
import com.sise.cwh.estate.dao.HouseMagDao;
import com.sise.cwh.estate.dao.HouseSellDao;
import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.entity.Hossellcrcs;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.service.HouseSellService;
import com.sise.cwh.estate.util.QueryFactory;

public class HouseSellServiceImpl  implements HouseSellService {

	private HouseSellDao houseSellDao;
	private CusInfoDao cusInfoDao;
	private HouseMagDao houseMagDao;
	private BystgPayCrcsDao bystgPayCrcsDao;
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	public void setHouseSellDao(HouseSellDao houseSellDao) {
		this.houseSellDao = houseSellDao;
	}
	public void setCusInfoDao(CusInfoDao cusInfoDao) {
		this.cusInfoDao = cusInfoDao;
	}
	
	public void setHouseMagDao(HouseMagDao houseMagDao) {
		this.houseMagDao = houseMagDao;
	}
	
	
	public void setBystgPayCrcsDao(BystgPayCrcsDao bystgPayCrcsDao) {
		this.bystgPayCrcsDao = bystgPayCrcsDao;
	}
	@Override
	public void saveHouseSell(Hossellcrcs hossellcrcs, BystgPayCrcs bystgPayCrcs) {
		
		houseSellDao.save(hossellcrcs);
		bystgPayCrcsDao.save(bystgPayCrcs);
		Housemag housemag = queryFactory.findByKey(Housemag.class,hossellcrcs.getHousemag().getId());
		housemag.setSellSt(1);
		//houseMagDao.save(housemag);
	}
	
	

}
