package com.sise.cwh.estate.dao;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.util.Pagination;

public interface WrkrInfoDao extends BaseDao<Wrkrinfo> {

	/**
	 * 
	 * 工具条件查询员工信息
	 * @param wrkrNo
	 * @param wrkrNm
	 * @param wrkrPst
	 * @param roleId
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryWrkrInfoBy(String wrkrNo, 
													String wrkrNm, 
													String wrkrPst,
													String roleId,
													Pagination pg);
	/**
	 * 获取一个员工号
	 * @return
	 */
	public String getWrkrNo();
}
