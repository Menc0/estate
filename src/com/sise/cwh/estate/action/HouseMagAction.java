package com.sise.cwh.estate.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.tags.EditorAwareTag;

import com.sise.cwh.estate.entity.Buildmag;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.entity.HousemagId;
import com.sise.cwh.estate.service.HouseMagService;
import com.sise.cwh.estate.util.Constants;
import com.sise.cwh.estate.util.Pagination;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;

@Controller
@RequestMapping("/houseMag")
public class HouseMagAction{
	@Resource(name="houseMagService")
	private HouseMagService houseMagService;
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;

	
	/**
	 * 按条件查询出所有房屋信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllHouseMag")
	public @ResponseBody Map<String, Object>queryAllHouseMag(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String hosNo = request.getParameter("hosNo");
		String bldNo = request.getParameter("bldNo");
		String hosAreaMax = request.getParameter("hosAreaMax");
		String hosAreaMin = request.getParameter("hosAreaMin");
		String hosTp = request.getParameter("hosTp");
		String sellPceMin = request.getParameter("sellPceMin");
		String sellPceMax = request.getParameter("sellPceMax");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String sec = request.getParameter("order");
		String sort = request.getParameter("sort");
		Pagination pg = new Pagination(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sec, sort);
		List<Map<String, Object>>list = houseMagService.queryHouseInfoBy(hosNo,bldNo,hosAreaMin,hosAreaMax,hosTp,sellPceMin,sellPceMax,pg);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", list.get(list.size()-1).get("total"));
		list.remove(list.size()-1);//删除掉带过来的total数据总量
		map.put("rows",list);
		
		return map;
	} 
	
	/**
	 * 按条件查询出所有房屋信息用于信息查询模块高级查询
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryHouseInfoByBig")
	public @ResponseBody Map<String, Object>queryHouseInfoByBig(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String hosNo = request.getParameter("hosNo");
		String bldNo = request.getParameter("bldNo");
		String hosAreaMax = request.getParameter("hosAreaMax");
		String hosAreaMin = request.getParameter("hosAreaMin");
		String hosTp = request.getParameter("hosTp");
		String dscntRate = request.getParameter("dscntRate");
		String hasDscntRate = null;
		String sellPceMin = request.getParameter("sellPceMin");
		String sellPceMax = request.getParameter("sellPceMax");
		String costPceMin = request.getParameter("costPceMin");
		String costPceMax = request.getParameter("costPceMax");
		String houseSell = request.getParameter("sell");//如果不为空为0则用于销售管理查询出未销售的信息
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String sec = request.getParameter("order");
		String sort = request.getParameter("sort");
		if(Constants.HASDSCNTRATE.equals(dscntRate)){
			hasDscntRate = Constants.HASDSCNTRATE;
		}else if(Constants.NODSCNTRATE.equals(dscntRate)){
			hasDscntRate = Constants.NODSCNTRATE;
		}
		Pagination pg = new Pagination(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sec, sort);
		List<Map<String, Object>>list = houseMagService.queryHouseInfoByBig(hosNo, bldNo, hosAreaMin, hosAreaMax, hosTp, sellPceMin, sellPceMax, costPceMin, costPceMax, hasDscntRate,houseSell, pg);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", list.get(list.size()-1).get("total"));
		list.remove(list.size()-1);//删除掉带过来的total数据总量
		map.put("rows",list);
		
		return map;
	} 
	
	
	/**
	 * 保存房屋信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("saveHouseMag")
	public @ResponseBody Map<String, Object>saveHouseMag(HttpServletRequest request,HttpServletResponse response){
		String bldNo = request.getParameter("bldNo");
		String hosNo = null;
		String hosNo1 = request.getParameter("hosNo");
		String method = request.getParameter("method");
		Housemag hasHousemag = new Housemag();
		HousemagId housemagId = null;
		List<Map<String, Object>>hosList = new ArrayList<Map<String,Object>>();
		Buildmag buildmag = new Buildmag();
		if("edit".equals(method)){
			hosNo = hosNo1;
			housemagId = new HousemagId(hosNo,bldNo);
			hasHousemag = null;
		}else{
			hosNo = bldNo+request.getParameter("hosNo");
			housemagId = new HousemagId(hosNo,bldNo);
			hasHousemag = queryFactory.findByKey(Housemag.class, housemagId);
			hosList = houseMagService.queryHouseInfoBy(null,bldNo,null,null,null,null,null,null);
			buildmag = queryFactory.findByKey(Buildmag.class, bldNo);
		}
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		if(hasHousemag==null){
			Housemag housemag = (Housemag) RequestUtil.requstToBean(Housemag.class, request);
			housemag.setId(housemagId);
			try {
				if("edit".equals(method)){
				houseMagService.saveHouseInfo(housemag);
				}else{
					if(hosList.size()<buildmag.getHouseSum()){
					houseMagService.saveHouseInfo(housemag);
					}else{
						map.put("success", "gtHosSum");
					}
				}
			} catch (Exception e) {
				map.clear();
				map.put("success", "false");
			}
		}else{
			map.clear();
			map.put("success", "hasHosNo");
		}
		return map;
		
	}
	
	/**
	 * 根据房屋编号查询出该条数据用于修改是显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryHouseMagByhosNo")
	public @ResponseBody List<Housemag> queryHouseMagByhosNo(HttpServletRequest request,HttpServletResponse response){
		String hosNo = request.getParameter("hosNo");
		String bldNo = request.getParameter("bldNo");
		HousemagId housemagId = new HousemagId(hosNo,bldNo);
		Housemag Housemag = queryFactory.findByKey(Housemag.class, housemagId);
		List<Housemag> list = new ArrayList<Housemag>();
		list.add(Housemag);
		return list;
	}
 
	/**
	 * 删除多条房屋信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteHouseMag")
	public @ResponseBody Map<String, Object> deleteHouseMag(HttpServletRequest request,HttpServletResponse response){
		String hosNo = request.getParameter("hosNo");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("sucess","true");
		try {
			houseMagService.deleteHouseInfo(hosNo);

		} catch (Exception e) {
			map.clear();
			map.put("sucess", "false");
		}
		return map;
	}
	
	/**
	 * 查询出所有楼房供选择
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryAllBuild")
	public @ResponseBody List<Map<String, Object>>queryAllBuild(HttpServletResponse response,HttpServletRequest request){
		
		return houseMagService.queryAllBuild();
		
	}
	
	/**
	 * 查看显示房屋简介用于信息查询
	 * @param hosNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/toHosIntro")
	public String toHosIntro(String hosNo,HttpServletRequest request){
		String hosIntro = houseMagService.queryHosIntroByHosNo(hosNo).get("hosIntro").toString();
		request.setAttribute("hosIntro", hosIntro);
		return "/manager/hosIntro";
		
	}
	
	/**
	 * 查看销售详情
	 * @param hosNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/toViewHosSellCrcs")
	public @ResponseBody Map<String, Object>toViewHosSellCrcs(String hosNo,HttpServletRequest request){
		Map<String, Object>map = houseMagService.queryHosSellByHosNo(hosNo);
		String txnDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(map.get("TXN_DT"));
		map.put("TXN_DT", txnDt);
		return map;
	}
	
}
