package com.sise.cwh.estate.dao;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.IntrateMag;

public interface IntrateMagDao extends BaseDao<IntrateMag>{
	
	/**
	 * 查询利率期数
	 * @param prdSum
	 * @param intrate
	 * @return
	 */
	public List<Map<String, Object>>queryIntrateMag(String prdSum, String intrate);

}
