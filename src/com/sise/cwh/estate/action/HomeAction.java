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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sise.cwh.estate.entity.Cuslikehos;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.service.CusLikeHosService;
import com.sise.cwh.estate.service.HouseMagService;
import com.sise.cwh.estate.util.Constants;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;
@Controller
@RequestMapping("/homeAction")
public class HomeAction {

	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="houseMagService")
	private HouseMagService houseMagService;
	@Resource(name="cusLikeHosService")
	private CusLikeHosService cusLikeHosService;
	/**
	 * 查询推荐楼房用于前台主页显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/home")
	public  ModelAndView queryRecmd(HttpServletRequest request){
		String ip = request.getRemoteAddr();//获取IP
		List<Map<String, Object>>homeList = new ArrayList<Map<String,Object>>();
		Map<String, Object>map = new HashMap<String, Object>();
		List<Map<String, Object>>recmdHouse = queryFactory.queryRecmdHouse(null,Constants.RECMD,ip);
		List<Map<String, Object>>recmdBuild = queryFactory.getRecmdBuild();
		map.put("recmdHouse", recmdHouse);
		map.put("recmdBuild", recmdBuild);
		homeList.add(map);
		
		return new ModelAndView("home/index","home",map);
	}
	
	/**
	 * 获取房间简介
	 * @param hosNo
	 * @return
	 */
	@RequestMapping("/getHouseIntro")
	public ModelAndView getHouseIntro(@RequestParam("hosNo") String hosNo,@RequestParam("bldNo") String bldNo,HttpServletRequest request){
		String ip = request.getRemoteAddr();//获取IP
		List<Map<String, Object>>houseIntro = queryFactory.getHouseIntro(hosNo);
		List<Map<String, Object>>recmdHouse = queryFactory.queryRecmdHouse(bldNo,Constants.RECMD,ip);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("houseIntro", houseIntro.get(0));
		map.put("recmdHouse", recmdHouse);
		return new ModelAndView("home/houseIntro","houseIntro",map);
		
	}
	
	/**
	 * 获取所有房间
	 * @return
	 */
	@RequestMapping("/getAllHouse")
	public ModelAndView getAllHouse(){
		List<Map<String, Object>>houseList = queryFactory.getAllHouse();
		return new ModelAndView("home/allHouse","houseList",houseList);
		
	}
	
	/**
	 * 
	 * 查询出所有楼房
	 * @return
	 */
	@RequestMapping("/getAllBuild")
	public ModelAndView getAllBuild(){
		List<Map<String, Object>>bldList = queryFactory.getAllBuild(null);
		return new ModelAndView("home/allBuild","bldList",bldList);
		
	}
	
	/**
	 * 获取楼房介绍及该楼房下的推荐房间/或用于楼房查询子下的房间
	 * @param bldNo
	 * @return
	 */
	@RequestMapping("/getBuildIntro")
	public ModelAndView getBuildIntro(@RequestParam("bldNo") String bldNo,HttpServletRequest request){
		String ip = request.getRemoteAddr();//获取IP
		List<Map<String, Object>>buildIntro = queryFactory.getAllBuild(bldNo);//楼房简介
		List<Map<String, Object>>recmdHouse = queryFactory.queryRecmdHouse(bldNo,Constants.RECMD,ip);//带出子下推荐房间
		List<Map<String, Object>>houseList = queryFactory.queryRecmdHouse(bldNo,null,ip);//带出子下房间
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buildIntro", buildIntro.get(0));
		map.put("recmdHouse", recmdHouse);
		map.put("houseList", houseList);
		return new ModelAndView("home/buildIntro","buildIntro",map);
		
	}
	
	/**
	 * 查询楼房子下房间
	 * @param bldNo
	 * @return
	 */
	@RequestMapping("/searchHouse")
	public ModelAndView searchHouse(@RequestParam("bldNo") String bldNo,HttpServletRequest request){
		String ip = request.getRemoteAddr();//获取IP
		List<Map<String, Object>>houseList = queryFactory.queryRecmdHouse(bldNo,null,ip);
		return new ModelAndView("home/searchHouse","houseList",houseList);
		
	}
	
	/**
	 * 按条件查询房间
	 * @param hosTp
	 * @return
	 */
	@RequestMapping("/searchHouseByHosTp")
	public ModelAndView searchHouseByHosTp(@RequestParam("hosTp") String hosTp){
		List<Map<String, Object>>houseList = queryFactory.getHousebyHosTp(hosTp);
		return new ModelAndView("home/searchHouse","houseList",houseList);
		
	}
	
	@RequestMapping("/saveCusLikeHos")
	public @ResponseBody  void saveCusLikeHos(HttpServletRequest request){
		Cuslikehos cuslikehos = (Cuslikehos)RequestUtil.requstToBean(Cuslikehos.class, request);
		cusLikeHosService.saveCusLikeHos(cuslikehos);
	}
}
