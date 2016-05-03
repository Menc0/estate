package com.sise.cwh.estate.service.impl;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.dao.CusInfoDao;
import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.service.CusInfoService;
import com.sise.cwh.estate.util.Pagination;

public class CusInfoServiceImpl implements CusInfoService {
	
	private CusInfoDao cusInfoDao;

	public void setCusInfoDao(CusInfoDao cusInfoDao) {
		this.cusInfoDao = cusInfoDao;
	}

	@Override
	public Cusinfo getCusinfoByCusIcd(String cusIcd) {
		return cusInfoDao.getCusinfoByCusIcd(cusIcd);
	}

	@Override
	public List<Map<String, Object>> queryWrkrInfoBy(String cusIcd,
			String cusNm, String cusTel, Pagination pg) {
		// TODO Auto-generated method stub
		return cusInfoDao.queryCusInfoBy(cusIcd, cusNm, cusTel, pg);
	}

	@Override
	public void saveCusInfo(Cusinfo cusinfo) {
		 cusInfoDao.save(cusinfo);
	}
	
	@Override
	public void deleteCusInfo(String cusNos) {
		String []cusNo = cusNos.split(",");
		for(int i=0;i<cusNo.length;i++){
		cusInfoDao.delete(Integer.valueOf(cusNo[i]));
		}
		
	}

}
