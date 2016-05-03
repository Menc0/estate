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

import com.sise.cwh.estate.entity.Role;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.service.WrkrInfoService;
import com.sise.cwh.estate.util.ExportExcel;
import com.sise.cwh.estate.util.Pagination;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;

@Controller
@RequestMapping("/wrkrInfo")
public class WrkrInfoAction {

	@Resource(name="wrkrInfoService")
	private WrkrInfoService wrkrInfoService;
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	
	/**
	 * 查询出所有员工信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllWrkrInfo")
	public @ResponseBody Map<String, Object>queryAllWrkrInfo(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String wrkrNo = request.getParameter("wrkrNo");
		String wrkrNm = request.getParameter("wrkrNm");
		String wrkrPst = request.getParameter("wrkrPst");
		String roleId = request.getParameter("roleId");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String sec = request.getParameter("order");
		String sort = request.getParameter("sort");
		Pagination pg = new Pagination(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sec, sort);
		List<Map<String, Object>>list = wrkrInfoService.queryWrkrInfoBy(wrkrNo, wrkrNm, wrkrPst, roleId,pg);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", list.get(list.size()-1).get("total"));
		list.remove(list.size()-1);//删除掉带过来的total数据总量
		map.put("rows",list);
		
		return map;
	} 
	
	/**
	 * 导出员工信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("/exportExcel")
	public @ResponseBody Map<String, Object> exportExcel(HttpServletResponse response,HttpServletRequest request){
		String wrkrNo = request.getParameter("wrkrNo");
		String wrkrNm = request.getParameter("wrkrNm");
		String wrkrPst = request.getParameter("wrkrPst");
		String roleId = request.getParameter("roleId");
		List<Map<String, Object>>exportlist = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>>list = wrkrInfoService.queryWrkrInfoBy(wrkrNo, wrkrNm, wrkrPst, roleId,null);
		Map<String, Object>resultmap = new HashMap<String, Object>();
		resultmap.put("success", "true");
		for(Map<String, Object>map :list){
			@SuppressWarnings("unchecked")
			Map<String, Object>exportMap = (Map<String, Object>)new ListOrderedMap();//listOrderMap是一个排序的map
			exportMap.put("wrkrNo", map.get("wrkrNo").toString());
			exportMap.put("wrkrNm", map.get("wrkrNm").toString());
			exportMap.put("wrkrIcd", map.get("wrkrIcd").toString());
			exportMap.put("wrkrTell", map.get("wrkrTell").toString());
			exportMap.put("wrkrPst", map.get("wrkrPst").toString());
			exportlist.add(exportMap);
		}
		String[] titles = {"工号","名字","身份证","电话","职位"};
		try {
			ExportExcel.doExportExcel("员工信息",titles, exportlist);
		} catch (Exception e) {
			resultmap.clear();
			resultmap.put("success", "false");
		}
		return resultmap;
	}
	
	/**
	 * 获取角色生成下拉框
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllRole")
	public @ResponseBody List<Role>queryAllRole(HttpServletResponse response,HttpServletRequest request) throws Exception{
		return queryFactory.queryRoleByRoleId(null);
	}
	
	/**
	 * 自动获取一个员工号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getWrkrNo")
	public @ResponseBody Map<String, Object> getWrkrNo(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("wrkrNo", wrkrInfoService.getWrkrNo());
		return map;
	}
	
	/**
	 * 保存员工信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveWrkrInfo")
	public @ResponseBody Map<String, Object>saveWrkrInfo(HttpServletRequest request,HttpServletResponse response){
		/*String wrkrNo = request.getParameter("wrkrNo");
		String wrkrNm = request.getParameter("wrkrNm");
		String wrkrIcd = request.getParameter("wrkrIcd");
		String wrkrTell = request.getParameter("wrkrTell");
		String wrkrPst = request.getParameter("wrkrPst");
		String loginPsw = request.getParameter("loginPsw");
		String roleId = request.getParameter("roleId");
		Wrkrinfo wrkrinfo = new Wrkrinfo(wrkrNm, roleId, wrkrIcd, wrkrTell, loginPsw, wrkrPst, 0);
		wrkrinfo.setWrkrNo(wrkrNo);*/
		Wrkrinfo wrkrinfo = (Wrkrinfo) RequestUtil.requstToBean(Wrkrinfo.class, request);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			wrkrInfoService.saveWrkrInfo(wrkrinfo);
		} catch (Exception e) {
			map.clear();
			map.put("success", "false");
		}
		
		return map;
	}
	
	/**
	 * 根据员工号查询出该条数据用于修改是显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryWrkrInfoByWrkrNo")
	public @ResponseBody List<Wrkrinfo>queryWrkrInfoByWrkrNo(HttpServletRequest request,HttpServletResponse response){
		String wrkrNo = request.getParameter("wrkrNo");
		Wrkrinfo wrkrinfo = queryFactory.findByKey(Wrkrinfo.class, wrkrNo);
		List<Wrkrinfo> list = new ArrayList<Wrkrinfo>();
		list.add(wrkrinfo);
		return list;
	}
	
	/**
	 * 删除多条员工信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteWrkrInfo")
	public @ResponseBody Map<String, Object> deleteWrkrInfo(HttpServletRequest request,HttpServletResponse response){
		String wrkrNo = request.getParameter("wrkrNo");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("sucess","true");
		try {
			wrkrInfoService.deleteWrkrInfo(wrkrNo);

		} catch (Exception e) {
			map.clear();
			map.put("sucess", "false");
		}
		return map;
	}
}
