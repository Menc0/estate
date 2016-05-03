package com.sise.cwh.estate.dao;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Buildmag;
import com.sise.cwh.estate.util.Pagination;

public interface BuildMagDao extends BaseDao<Buildmag> {

	/**
	 * 按条件查询楼房信息并分页显示
	 * @param bldNo
	 * @param bldNm
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryBuildInfoBy(String bldNo, 
			String bldNm, 
			Pagination pg);
}
