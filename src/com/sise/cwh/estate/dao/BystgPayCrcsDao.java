package com.sise.cwh.estate.dao;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.PerPrdPayed;
import com.sise.cwh.estate.util.Pagination;

public interface BystgPayCrcsDao extends BaseDao<BystgPayCrcs>{
	
	/**
	 * 查询分期付款情况
	 * @param cusIcd
	 * @param hosNo
	 * @return
	 */
	public List<Map<String, Object>>queryPayCrcs(String cusIcd, String hosNo, Pagination pg);
	
	/**
	 * 保存已付款
	 * @param perPrdPayed
	 */
	public void savePaying(PerPrdPayed perPrdPayed,BystgPayCrcs bystgPayCrcs); 

}
