package com.sise.cwh.estate.service;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.PerPrdPayed;
import com.sise.cwh.estate.util.Pagination;


public interface BystgPayCrcsService {
	
	/**
	 * 查询付款统计并分页显示
	 * @param cusIcd
	 * @param hosNo
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryPayCrcs(String cusIcd, String hosNo, Pagination pg);
	
	/**
	 * 存入已付款表并更新分期付款情况表
	 * @param perPrdPayed
	 * @param bystgPayCrcs
	 */
	public void savePaying(PerPrdPayed perPrdPayed,BystgPayCrcs bystgPayCrcs);

}
