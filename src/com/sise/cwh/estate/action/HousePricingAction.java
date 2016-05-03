package com.sise.cwh.estate.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.entity.HousemagId;
import com.sise.cwh.estate.service.HouseMagService;
import com.sise.cwh.estate.util.QueryFactory;
@Controller
@RequestMapping("/housePricing")
public class HousePricingAction {

	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="houseMagService")
	private HouseMagService houseMagService;
	
	@RequestMapping("/saveHousePricing")
	public @ResponseBody Map<String, Object>hosPricing(HttpServletRequest request){
		String hosNo = request.getParameter("hosNo");
		String sellPce = request.getParameter("sellPce");
		String dscntRate = request.getParameter("dscntRate");
		String bldNo = request.getParameter("bldNo");
		HousemagId housemagId = new HousemagId(hosNo,bldNo);
		Housemag housemag = queryFactory.findByKey(Housemag.class, housemagId);
		housemag.setSellPce(Double.valueOf(sellPce));
		housemag.setDscntRate(Double.valueOf(dscntRate));
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			houseMagService.saveHouseInfo(housemag);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("success", "false");
		}
		
		return map;
		
	}
}
