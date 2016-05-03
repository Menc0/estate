package com.sise.cwh.estate.service;

import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.entity.Hossellcrcs;


public interface HouseSellService {
	
	/**
	 * 保存销售记录
	 * @param hossellcrcs
	 */
	public void saveHouseSell(Hossellcrcs hossellcrcs,BystgPayCrcs bystgPayCrcs);

}
