package com.sise.cwh.estate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.service.CusInfoService;
import com.sise.cwh.estate.util.ExportExcel;
import com.sise.cwh.estate.util.Pagination;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;

@Controller
@RequestMapping("/cusInfo")
public class CusInfoAction {

	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="cusInfoService")
	private CusInfoService cusInfoService;
	
	@RequestMapping("/getCusinfoByCusIcd")
	public @ResponseBody Cusinfo getCusinfoByCusIcd(String cusIcd,HttpServletRequest request) throws Exception{
		return cusInfoService.getCusinfoByCusIcd(cusIcd);
		
	}
	
	/**
	 * 查询出所有客户信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllCusInfo")
	public @ResponseBody Map<String, Object>queryAllCusInfo(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String cusIcd = request.getParameter("cusIcd");
		String cusNm = request.getParameter("cusNm");
		String cusTel = request.getParameter("cusTel");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String sec = request.getParameter("order");
		String sort = request.getParameter("sort");
		Pagination pg = new Pagination(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sec, sort);
		List<Map<String, Object>>list = cusInfoService.queryWrkrInfoBy(cusIcd, cusNm, cusTel, pg);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", list.get(list.size()-1).get("total"));
		list.remove(list.size()-1);//删除掉带过来的total数据总量
		map.put("rows",list);
		
		return map;
	} 
	
	
	
	/**
	 * 保存客户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveCusInfo")
	public @ResponseBody Map<String, Object>saveCusInfo(HttpServletRequest request,HttpServletResponse response){
		Cusinfo cusinfo = (Cusinfo) RequestUtil.requstToBean(Cusinfo.class, request);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			cusInfoService.saveCusInfo(cusinfo);
		} catch (Exception e) {
			map.clear();
			map.put("success", "false");
		}
		
		return map;
	}
	
	
	/**
	 * 根据客户号查询出该条数据用于修改是显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryCusInfoByCusNo")
	public @ResponseBody List<Cusinfo>queryCusInfoByWrkrNo(HttpServletRequest request,HttpServletResponse response){
		String cusNo = request.getParameter("cusNo");
		Cusinfo cusinfo = queryFactory.findByKey(Cusinfo.class, Integer.valueOf(cusNo));
		List<Cusinfo> list = new ArrayList<Cusinfo>();
		list.add(cusinfo);
		return list;
	}
	
	
	/**
	 * 删除多条客户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteCusInfo")
	public @ResponseBody Map<String, Object> deleteCusInfo(HttpServletRequest request,HttpServletResponse response){
		String cusNo = request.getParameter("cusNo");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success","true");
		try {
			cusInfoService.deleteCusInfo(cusNo);

		} catch (Exception e) {
			map.clear();
			map.put("success", "false");
		}
		return map;
	}
	
	
	@RequestMapping("/exportExcel")
	public @ResponseBody Map<String, Object>exportExcel(HttpServletRequest request){
		String cusIcd = request.getParameter("cusIcd");
		String cusNm = request.getParameter("cusNm");
		String cusTel = request.getParameter("cusTel");
		List<Map<String, Object>>exportlist = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>>list =  cusInfoService.queryWrkrInfoBy(cusIcd, cusNm, cusTel, null);
		Map<String, Object>resultmap = new HashMap<String, Object>();
		resultmap.put("success", "true");
		for(Map<String, Object>map :list){
			@SuppressWarnings("unchecked")
			Map<String, Object>exportMap = (Map<String, Object>)new ListOrderedMap();//listOrderMap是一个排序的map
			exportMap.put("cusIcd", map.get("cusIcd").toString());
			exportMap.put("cusNm", map.get("cusNm").toString());
			exportMap.put("cusTel", map.get("cusTel").toString());
			exportMap.put("cusIncm", map.get("cusIncm").toString());
			exportlist.add(exportMap);
		}
		String[] titles = {"身份证","名字","电话","收入"};
		try {
			ExportExcel.doExportExcel("客户信息",titles, exportlist);
		} catch (Exception e) {
			resultmap.clear();
			resultmap.put("success", "false");
		}
		return resultmap;
	}
}
