package com.sise.cwh.estate.service;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.util.Pagination;

public interface CusInfoService {
	
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
	public List<Map<String, Object>>queryWrkrInfoBy(String cusIcd, 
			String cusNm, 
			String cusTel,
			Pagination pg);

	/**
	 * 保存信息
	 * @param cusinfo
	 */
	public void saveCusInfo(Cusinfo cusinfo);
	
	/**
	 * 删除客户信息
	 * @param cusNos
	 */
	public void deleteCusInfo(String cusNos);
}
