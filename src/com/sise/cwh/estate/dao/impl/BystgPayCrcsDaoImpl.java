package com.sise.cwh.estate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.sise.cwh.estate.dao.BystgPayCrcsDao;
import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.PerPrdPayed;
import com.sise.cwh.estate.util.Pagination;

public class BystgPayCrcsDaoImpl extends BaseDaoImpl<BystgPayCrcs> implements BystgPayCrcsDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryPayCrcs(String cusIcd, String hosNo, Pagination pg) {
		StringBuffer sql = new StringBuffer();
		StringBuffer wheresql = new StringBuffer();
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object>param = new HashMap<String, Object>();
				sql.append("SELECT								");
				sql.append("	bpc.SELL_NO selNo,				");
				sql.append("	hscs.HOS_NO hosNo,				");
				sql.append("	bpc.PAYED_SUM payedSum,			");
				sql.append("	bpc.UNPAY_SUM unPaySum,			");
				sql.append("	bpc.PER_PRD_MON perPrdMon,		");
				sql.append("	ci.CUS_ICD cusIcd,				");
				sql.append("	ci.CUS_NM cusNm,				");
				sql.append("	ci.CUS_TEL cusTel,				");
				sql.append("	ci.CUS_EMAIL cusEmail,			");
				sql.append("	bpc.UNPAY_SUM+bpc.PAYED_SUM allPrdSum,				");
				sql.append("	ppp.SEL_NO payed				");
				sql.append("FROM								");
				sql.append("	bystg_pay_crcs bpc				");
				sql.append("LEFT JOIN hossellcrcs hscs ON bpc.SELL_NO = hscs.SEL_NO	");
				sql.append("LEFT JOIN cusinfo ci ON ci.CUS_NO = hscs.CUS_NO			");
				sql.append("LEFT JOIN (												");
				sql.append("	SELECT												");
				sql.append("		SEL_NO											");
				sql.append("	FROM												");
				sql.append("		per_prd_payed									");
				sql.append("	WHERE												");
				sql.append("		DATE_FORMAT(PAY_DT, '%m') = DATE_FORMAT(CURRENT_TIMESTAMP(), '%m')");
				sql.append(") ppp ON ppp.SEL_NO = bpc.SELL_NO						");
				sql.append("WHERE								");
				sql.append("	1 = 1							");
				sql.append("AND bpc.UNPAY_SUM!=0				");
				sql.append("AND PER_PRD_MON!=0					");
				if(StringUtils.isNotEmpty(hosNo)){
					wheresql.append("AND hscs.HOS_NO = :hosNo		");
					param.put("hosNo", hosNo);
				}else if(StringUtils.isNotEmpty(cusIcd)){
					wheresql.append("AND ci.CUS_ICD = :cusIcd		");
					param.put("cusIcd", cusIcd);
				}
				if(pg!=null){//有物理分页
					if(StringUtils.isNotEmpty(pg.getSec())){
						wheresql.append(" ORDER BY  "+pg.getSort()+"  "+pg.getSec());
					}
					
					sql.append(wheresql);
					Query querycount = this.getSession().createSQLQuery(sql.toString()).setProperties(param);
					List<Map<String, Object>>countList = querycount.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
					
					Query query = this.getSession().createSQLQuery(sql.toString());
					query.setProperties(param); 
					resultList = query.setFirstResult(pg.getFirstResult()).setMaxResults(pg.getMaxResults()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
					Map<String, Object>map = new HashMap<String, Object>();
					map.put("total",countList.size());
					resultList.add(map);
					}else{//无物理分页
						sql.append(wheresql);
						Query query = this.getSession().createSQLQuery(sql.toString());
						query.setProperties(param); 
						resultList = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
					}
		return resultList;
	}

	@Override
	public void savePaying(PerPrdPayed perPrdPayed,BystgPayCrcs bystgPayCrcs) {
		 this.getSession().saveOrUpdate(perPrdPayed);
		 this.getSession().saveOrUpdate(bystgPayCrcs);
	}
	
	

}
