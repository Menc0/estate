package com.sise.cwh.estate.service;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.util.Pagination;

public interface HouseMagService {
	
	/**
	 * 按条件查询出房屋信息并分页显示
	 * @param hosNo
	 * @param bldNo
	 * @param hosAreaMin
	 * @param hosAreaMax
	 * @param hosTp
	 * @param sellPceMin
	 * @param sellPceMax
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryHouseInfoBy(String hosNo, 
													String bldNo,
													String hosAreaMin,
													String hosAreaMax,
													String hosTp,
													String sellPceMin,
													String sellPceMax,
													Pagination pg);
	
	/**
	 * 按条件查询出房屋信息并分页显示用于信息查询模块高级查询
	 * @param hosNo
	 * @param bldNo
	 * @param hosAreaMin
	 * @param hosAreaMax
	 * @param hosTp
	 * @param sellPceMin
	 * @param sellPceMax
	 * @param costPceMin
	 * @param costPceMax
	 * @param dscntRate
	 * @param houseSell
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryHouseInfoByBig(String hosNo, 
													String bldNo,
													String hosAreaMin,
													String hosAreaMax,
													String hosTp,
													String sellPceMin,
													String sellPceMax,
													String costPceMin,
													String costPceMax,
													String dscntRate,
													String houseSell,
													Pagination pg);
	
	/**
	 * 保存房屋信息
	 * @param housemag
	 */
	public void saveHouseInfo(Housemag housemag);
	
	/**
	 * 删除多条房屋信息
	 * @param hosNos
	 */
	public void deleteHouseInfo(String hosNos);
	
	/**
	 * 获取所有楼房信息
	 * @return
	 */
	public List<Map<String, Object>>queryAllBuild();
	
	/**
	 * 根据房屋编号查询出简介用于信息查询模块
	 * @param hosNo
	 * @return
	 */
	public Map<String,Object>queryHosIntroByHosNo(String hosNo);
	
	/**
	 * 查询销售详情
	 * @param hosNo
	 * @return
	 */
	public Map<String, Object>queryHosSellByHosNo(String hosNo);
	
	
	/**
	 * 获取所有房间
	 * @return
	 */
	public List<Housemag>getAllHousemags();
}
