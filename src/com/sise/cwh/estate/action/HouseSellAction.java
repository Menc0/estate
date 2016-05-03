package com.sise.cwh.estate.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.entity.Hossellcrcs;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.entity.HousemagId;
import com.sise.cwh.estate.entity.IntrateMag;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.service.CusInfoService;
import com.sise.cwh.estate.service.HouseSellService;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.RequestUtil;

@Controller
@RequestMapping("/houseSell")
public class HouseSellAction {

	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="houseSellService")
	private HouseSellService houseSellService;
	@Resource(name="cusInfoService")
	private CusInfoService cusInfoService;
	
	/**
	 * 保存销售记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveHouseSell")
	public @ResponseBody Map<String, Object>saveHouseSell(HttpServletRequest request,HttpServletResponse response){
		Cusinfo cusinfo = (Cusinfo) RequestUtil.requstToBean(Cusinfo.class, request);
		Hossellcrcs hossellcrcs = (Hossellcrcs)RequestUtil.requstToBean(Hossellcrcs.class, request);
		String cusIcd = request.getParameter("cusIcd");
		HousemagId housemagId = (HousemagId)RequestUtil.requstToBean(HousemagId.class,request);
		Housemag housemag = queryFactory.findByKey(Housemag.class, housemagId);
		String wrkrNo = request.getParameter("wrkrNo");
		Wrkrinfo wrkrinfo = queryFactory.findByKey(Wrkrinfo.class, wrkrNo);
		IntrateMag intrateMag = queryFactory.findByKey(IntrateMag.class, hossellcrcs.getPrdSumId());
		
		
		//double fsdpdPay = intrateMag.getIntrate()*//首付
		/*if(housemag.getDscntRate()==0){
			hosPft = housemag.getSellPce()-housemag.getCostPce();
		}else{*/
			double dscntRate = housemag.getDscntRate()/100;//折扣率
			double amount = housemag.getSellPce()-housemag.getSellPce()*dscntRate;//需付总额
			
			double fsdpdPay = Double.valueOf(request.getParameter("fsdpdPayMon"));//首付金额
			
		//}
		//每期付款金额=（（总额-首付）/期数）*（1+利率）
			double perPrdMon=0;
			if(intrateMag.getBystgSum()!=0)
		perPrdMon = ((amount-fsdpdPay)/intrateMag.getBystgSum())*(1+intrateMag.getIntrate()/100);
		double hosPft  = perPrdMon*intrateMag.getBystgSum()+fsdpdPay-housemag.getCostPce();//利润=每期金额*期数+首付-成本价
		BystgPayCrcs bystgPayCrcs = new BystgPayCrcs();
		bystgPayCrcs.setPayedSum(0);
		bystgPayCrcs.setUnpaySum(intrateMag.getBystgSum());
		bystgPayCrcs.setPerPrdMon(perPrdMon);
		bystgPayCrcs.setSellNo(queryFactory.getAutoIncrement("hossellcrcs"));
		bystgPayCrcs.setFsdpdPay(fsdpdPay);
		
		
		hossellcrcs.setHousemag(housemag);
		hossellcrcs.setWrkrinfo(wrkrinfo);
		hossellcrcs.setHosPft(hosPft);
		Cusinfo hasCusinfo = cusInfoService.getCusinfoByCusIcd(cusIcd);
		if(hasCusinfo==null){//判断输入的身份证是否有该客户，无则存存入客户表
			//hossellcrcs.setCusinfo(hasCusinfo);
			//cusinfo = null;
			cusinfo.setCusNo(null);
			cusInfoService.saveCusInfo(cusinfo);
		}
		Cusinfo q_cusinfo = cusInfoService.getCusinfoByCusIcd(cusIcd);
		hossellcrcs.setCusinfo(q_cusinfo);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			houseSellService.saveHouseSell(hossellcrcs, bystgPayCrcs);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("success", "false");
		}
		
		return map;
	}
}
