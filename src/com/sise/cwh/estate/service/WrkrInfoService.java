package com.sise.cwh.estate.service;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.util.Pagination;


public interface WrkrInfoService {

	/**
	 * 查询所有员工信息
	 * @return
	 */
	public List<Wrkrinfo>queryAllWrkrinfos();
	
	/**
	 * 按条件查询出员工信息并分页显示
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
	
	/**
	 * 保存员工信息
	 * @param wrkrinfo
	 */
	public void saveWrkrInfo(Wrkrinfo wrkrinfo);
	
	/**
	 * 删除多条员工信息
	 * @param wrkrinfo
	 */
	public void deleteWrkrInfo(String wrkrNos);
}
