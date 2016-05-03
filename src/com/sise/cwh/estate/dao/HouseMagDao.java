package com.sise.cwh.estate.dao;
import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.entity.HousemagId;
import com.sise.cwh.estate.util.Pagination;

public interface HouseMagDao extends BaseDao<Housemag>{
	
	
	/**
	 * 按条件查询房屋信息并分页显示
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
	 * 按条件查询房屋信息并分页显示用于信息查询高级查询
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
	 * @param houseSell 判断是否由销售管理，如果为0 则是，为空则不用改条件
	 * @param pg
	 * @return
	 */
	public List<Map<String, Object>>queryInfoBy(String hosNo, 
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
	 * 删除房屋信息
	 * @param housemagId
	 */
	public void deleteHouseInfo(HousemagId housemagId);
	
	/**
	 * 获取所有楼房信息
	 * @return
	 */
	public List<Map<String, Object>> queryAllBuild();
	
	/**
	 * 根据房屋编号查询出简介用于信息查询模块
	 * @param hosNo
	 * @return
	 */
	public List<Map<String, Object>> queryHosIntroByHosNo(String hosNo);
	
	/**
	 * 查询出销售详情
	 * @param hosNo
	 * @return
	 */
	public List<Map<String, Object>>queryHosSellByHosNo(String hosNo);
	
	
	/**
	 * 根据楼房编号删除房屋信息
	 * @param BldNo
	 * @return
	 */
	public void deleteHosInfoByBldNo(String BldNo);
}
