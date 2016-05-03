package com.sise.cwh.estate.action;

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

import com.sise.cwh.estate.entity.Buildmag;
import com.sise.cwh.estate.service.BuildMagService;
import com.sise.cwh.estate.util.Pagination;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;

@Controller
@RequestMapping("/buildMag")
public class BuildMagAction {

	@Resource(name="buildMagService")
	private BuildMagService buildMagService;
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	
	/**
	 * 查询出所有楼房信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllBuildMag")
	public @ResponseBody Map<String, Object>queryAllBuildMag(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String bldNo = request.getParameter("bldNo");
		String bldNm = request.getParameter("bldNm");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String sec = request.getParameter("order");
		String sort = request.getParameter("sort");
		Pagination pg = new Pagination(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sec, sort);
		List<Map<String, Object>>list = buildMagService.queryBuildInfoBy(bldNo, bldNm, pg);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", list.get(list.size()-1).get("total"));
		list.remove(list.size()-1);//删除掉带过来的total数据总量
		map.put("rows",list);
		
		return map;
	} 
	
	/**
	 * 保存楼房信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("saveBuildMag")
	public @ResponseBody Map<String, Object>saveBuildMag(HttpServletRequest request,HttpServletResponse response){
		String bldNo = request.getParameter("bldNo");
		String method = request.getParameter("method");
		Buildmag hasBuildmag = new Buildmag();
		if("edit".equals(method)){
			hasBuildmag = null;
		}else{
			hasBuildmag = queryFactory.findByKey(Buildmag.class, bldNo);
		}
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		if(hasBuildmag==null){
		Buildmag buildmag = (Buildmag) RequestUtil.requstToBean(Buildmag.class, request);
		
			try {
				buildMagService.saveBuildInfo(buildmag);
			} catch (Exception e) {
				map.clear();
				map.put("success", "false");
			}
		}else{
			map.clear();
			map.put("success", "hasBldNo");
		}
		return map;
		
	}
	
	/**
	 * 根据楼房编号查询出该条数据用于修改是显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryBuildMagByBldNo")
	public @ResponseBody List<Buildmag> queryBuildMagByBldNo(HttpServletRequest request,HttpServletResponse response){
		String bldNo = request.getParameter("bldNo");
		Buildmag buildmag = queryFactory.findByKey(Buildmag.class, bldNo);
		List<Buildmag> list = new ArrayList<Buildmag>();
		list.add(buildmag);
		return list;
	}
 
	/**
	 * 删除多条楼房信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteBuildMag")
	public @ResponseBody Map<String, Object> deleteBuildMag(HttpServletRequest request,HttpServletResponse response){
		String bldNo = request.getParameter("bldNo");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("sucess","true");
		try {
			buildMagService.deleteBuildInfo(bldNo);

		} catch (Exception e) {
			map.clear();
			map.put("sucess", "false");
		}
		return map;
	}
}
