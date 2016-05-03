package com.sise.cwh.estate.service;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.IntrateMag;

public interface IntrateMagService {

	/**
	 * 查询所有
	 * @return
	 */
	public List<IntrateMag>queryAllIntrateMag();
	
	public void save(IntrateMag intrateMag);
	
	public void delete(String intrateIds);
}
