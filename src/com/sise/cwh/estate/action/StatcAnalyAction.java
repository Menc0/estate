package com.sise.cwh.estate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.util.QueryFactory;

@Controller
@RequestMapping("/statcAnaly")
public class StatcAnalyAction {

	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	
	/**
	 * 每月销量
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/sellSumMonth")
	public @ResponseBody Map<String, Object>sellSumMonth(HttpServletResponse response,HttpServletRequest request) throws Exception{

	List<Map<String, Object>>sellSumMonthDataList = queryFactory.querySellSumMonth();
	List mothList1 = new ArrayList();
	for(Map<String, Object> moth : sellSumMonthDataList){
		mothList1.add(moth.get("Moth").toString());
	}
	List mothList2 = new ArrayList();
	for(int i= 1;i<=12;i++){//1到12
		if(i<=9){
			mothList2.add("0"+i);
		}else{
		mothList2.add(i);
		}
	}
	int[] data = new int[12] ;
	int hasData=0;
	for(int i= 0;i<12;i++){
	if(mothList1.contains(mothList2.get(i).toString())){
		data[i]=Integer.valueOf(sellSumMonthDataList.get(hasData).get("total").toString());
		hasData++;
	}else{
		data[i]=0;
	}
	}
	Map<String, Object>map = new HashMap<String, Object>();
	map.put("total",data);
	return map;
	}
	
	/**
	 * 查询近三个月热销前五楼房
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryBigSellBldPreThrMonth")
	public @ResponseBody Map<String, Object>queryBigSellBldPreThrMonth(HttpServletResponse response,HttpServletRequest request) throws Exception{

	List<Map<String, Object>>bigSellBldPreThrMonthDataList = queryFactory.queryBigSellBldPreThrMonth();
	int[] total = new int[5];
	String[] bldNo = new String[]{"无更多","无更多","无更多","无更多","无更多"};
	int i=0;
	for(Map<String, Object>map:bigSellBldPreThrMonthDataList){
		bldNo[i] = map.get("BLD_NO").toString();
		total[i]=Integer.valueOf(map.get("total").toString());
		i++;
		
	}
	Map<String, Object>map = new HashMap<String, Object>();
	map.put("total",total);
	map.put("bldNo",bldNo);
	return map;
	}
	
	/**
	 *查询出近一月前十员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/querySellSrcTop10")
	public @ResponseBody List<Map<String, Object>>querySellSrcTop10(HttpServletRequest request,HttpServletResponse response){
		return queryFactory.querySellScrTop10();
	}
}
