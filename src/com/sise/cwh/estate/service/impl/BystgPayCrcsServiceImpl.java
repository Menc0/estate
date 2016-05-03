package com.sise.cwh.estate.service.impl;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.dao.BystgPayCrcsDao;
import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.PerPrdPayed;
import com.sise.cwh.estate.service.BystgPayCrcsService;
import com.sise.cwh.estate.util.Pagination;

public class BystgPayCrcsServiceImpl implements BystgPayCrcsService{

	private BystgPayCrcsDao bystgPayCrcsDao;

	public void setBystgPayCrcsDao(BystgPayCrcsDao bystgPayCrcsDao) {
		this.bystgPayCrcsDao = bystgPayCrcsDao;
	}

	@Override
	public List<Map<String, Object>> queryPayCrcs(String cusIcd, String hosNo, Pagination pg) {
		
		return bystgPayCrcsDao.queryPayCrcs(cusIcd, hosNo, pg);
	}

	@Override
	public void savePaying(PerPrdPayed perPrdPayed,BystgPayCrcs bystgPayCrcs) {
		bystgPayCrcsDao.savePaying(perPrdPayed, bystgPayCrcs);
		
	}
	
}
