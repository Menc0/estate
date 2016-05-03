package com.sise.cwh.estate.dao;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.util.Pagination;

public interface  CusInfoDao extends BaseDao<Cusinfo> {
	
	/**
	 * 获取客户信息
	 * @param cusIcd
	 * @return
	 */
	public Cusinfo getCusinfoByCusIcd(String cusIcd);
	
	/**
	 * 查询客户信息
	 * @param cusIcd
	 * @param cusNm
	 * @param cusTel
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryCusInfoBy(String cusIcd, 
			String cusNm, 
			String cusTel,
			Pagination pg);
}
