package com.sise.cwh.estate.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.IntrateMag;
import com.sise.cwh.estate.service.IntrateMagService;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;

@Controller
@RequestMapping("/intrateMag")
public class IntrateMagAction {
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="intrateMagService")
	private IntrateMagService intrateMagService;
	

	@RequestMapping("/queryAllIntrateMag")
	public @ResponseBody List<IntrateMag>queryAllIntrateMag(HttpServletResponse response,HttpServletRequest request){ 
		return intrateMagService.queryAllIntrateMag();
		
	}
	
	@RequestMapping("/saveIntrateMag")
	public @ResponseBody Map<String, Object>saveIntrate(HttpServletRequest request)throws Exception{
		IntrateMag intrateMag = (IntrateMag) RequestUtil.requstToBean(IntrateMag.class, request);
		IntrateMag saveIntrateMag = new IntrateMag();
		String intrateId = request.getParameter("intrateId");
		if(StringUtils.isNotEmpty(intrateId)){
			saveIntrateMag = queryFactory.findByKey(IntrateMag.class, intrateMag.getIntrateId());
			saveIntrateMag.setBystgSum(intrateMag.getBystgSum());
			saveIntrateMag.setIntrate(intrateMag.getIntrate());
		}else{
			saveIntrateMag = intrateMag;
		}
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			intrateMagService.save(saveIntrateMag);
		} catch (Exception e) {
			map.put("success", "false");
		}
		return map;
		
	}
	
	@RequestMapping("/deleteIntrateMag")
	public @ResponseBody Map<String, Object>deleteIntrateMag(HttpServletRequest request)throws Exception{
		String intrateId = request.getParameter("intrateId");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success","true");
		try {
			intrateMagService.delete(intrateId);
		} catch (Exception e) {
			map.put("success","false");
		}
		
		return map;
		
	}
}
