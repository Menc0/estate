package com.sise.cwh.estate.service;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Buildmag;
import com.sise.cwh.estate.util.Pagination;

public interface BuildMagService {

	/**
	 * 按条件查询出楼房信息并分页显示
	 * @param bldNo
	 * @param bldNm
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryBuildInfoBy(String bldNo, 
													String bldNm, 
													Pagination pg);
	
	/**
	 * 保存楼房信息
	 * @param buildmag
	 */
	public void saveBuildInfo(Buildmag buildmag);
	
	/**
	 * 删除多条楼房信息信息
	 * @param bldNos
	 */
	public void deleteBuildInfo(String bldNos);
}
