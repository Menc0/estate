package com.sise.cwh.estate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;




















import com.sise.cwh.estate.dao.HouseMagDao;
import com.sise.cwh.estate.entity.Housemag;
import com.sise.cwh.estate.entity.HousemagId;
import com.sise.cwh.estate.util.Constants;
import com.sise.cwh.estate.util.Pagination;

public class HouseMagDaoImpl extends BaseDaoImpl<Housemag> implements HouseMagDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryHouseInfoBy(String hosNo,
														String bldNo,
														String hosAreaMin,
														String hosAreaMax,
														String hosTp,
														String sellPceMin,
														String sellPceMax,
														Pagination pg){
		StringBuffer sql = new StringBuffer();
		StringBuffer wheresql = new StringBuffer();
		StringBuffer countsql = new StringBuffer();
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" SELECT 							");
		sql.append("  	 HOS_NO hosNo					");
		sql.append(" 	,HOS_NO hosNo1					");
		sql.append(" 	,BLD_NO bldNo					");
		sql.append(" 	,HOS_AREA hosArea				");
		sql.append(" 	,HOS_TP hosTp					");
		sql.append(" 	,COST_PCE costPce				");
		sql.append(" 	,SELL_PCE sellPce				");
		sql.append(" 	,DSCNT_RATE dscntRate			");
		sql.append(" 	,SELL_ST sellSt					");
		sql.append(" 	,HOS_INTRO hosIntro				");
		sql.append(" 	,IS_RECMD isRecmd				");
		sql.append(" FROM HOUSEMAG 						");
		sql.append(" WHERE 1 = 1 						");
		
		if(StringUtils.isNotEmpty(hosNo)){
			wheresql.append(" and HOS_NO = :hosNo 	");
			param.put("hosNo", hosNo);
		}
		if(StringUtils.isNotEmpty(bldNo)){
			wheresql.append(" and BLD_NO = :bldNo 	");
			param.put("bldNo", bldNo);
		}
		if(StringUtils.isNotEmpty(hosAreaMin)&&StringUtils.isNotEmpty(hosAreaMax)){
			wheresql.append(" and HOS_AREA BETWEEN "+hosAreaMin+" AND "+hosAreaMax);
		}
		if(StringUtils.isNotEmpty(hosAreaMin)&&StringUtils.isEmpty(hosAreaMax)){
			wheresql.append(" and HOS_AREA >= :hosAreaMin");
			param.put("hosAreaMin", hosAreaMin);
		}
		if(StringUtils.isEmpty(hosAreaMin)&&StringUtils.isNotEmpty(hosAreaMax)){
			wheresql.append(" and HOS_AREA <= :hosAreaMax");
			param.put("hosAreaMax", hosAreaMax);
		}
		if(StringUtils.isNotEmpty(hosTp)){
			wheresql.append(" and HOS_TP = :hosTp 	");
			param.put("hosTp", hosTp);
		}
		if(StringUtils.isNotEmpty(sellPceMin)&&StringUtils.isNotEmpty(sellPceMax)){
			wheresql.append(" and SELL_PCE BETWEEN "+sellPceMin+" AND "+sellPceMax);
		}
		if(StringUtils.isNotEmpty(sellPceMin)&&StringUtils.isEmpty(sellPceMax)){
			wheresql.append(" and SELL_PCE >= :sellPceMin");
			param.put("sellPceMin", sellPceMin);
		}
		if(StringUtils.isEmpty(sellPceMin)&&StringUtils.isNotEmpty(sellPceMax)){
			wheresql.append(" and SELL_PCE <= :sellPceMax");
			param.put("sellPceMax", sellPceMax);
		}
		if(pg!=null){//有物理分页
		if(StringUtils.isNotEmpty(pg.getSec())){
			wheresql.append(" ORDER BY  "+pg.getSort()+"  "+pg.getSec());
		}
		sql.append(" AND SELL_ST = 0 					");
		sql.append(wheresql);
		countsql.append(" select  				");
		countsql.append("  	 HOS_NO hosNo					");
		countsql.append(" 	,HOS_NO hosNo1					");
		countsql.append(" 	,BLD_NO bldNo					");
		countsql.append(" 	,HOS_AREA hosArea				");
		countsql.append(" 	,HOS_TP hosTp					");
		countsql.append(" 	,COST_PCE costPce				");
		countsql.append(" 	,SELL_PCE sellPce				");
		countsql.append(" 	,DSCNT_RATE dscntRate			");
		countsql.append(" 	,SELL_ST sellSt					");
		countsql.append(" 	,HOS_INTRO hosIntro				");
		countsql.append(" 	,IS_RECMD isRecmd				");
		countsql.append(" 	, count(*)total		");
		countsql.append(" FROM HOUSEMAG 		");
		countsql.append("  where 1 = 1			");
		countsql.append(wheresql);
		Query querycount = this.getSession().createSQLQuery(countsql.toString()).setProperties(param);
		List<Map<String, Object>>countList = querycount.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setProperties(param); 
		resultList = query.setFirstResult(pg.getFirstResult()).setMaxResults(pg.getMaxResults()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total",countList.get(0).get("total"));
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
	public void deleteHouseInfo(HousemagId housemagId) {
		Object object = (Object) getSession().get(Housemag.class, housemagId);
        this.getSession().delete(object);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryAllBuild() {
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append("	SELECT						");
		sql.append("		bld.BLD_NO	bldNo		");
		sql.append("		,bld.BLD_NM	bldNm		");
		sql.append("	FROM BUILDMAG	bld			");
		sql.append("	LEFT JOIN (					");
		sql.append("		SELECT					");
		sql.append("			COUNT(*) hosSum,	");
		sql.append("			BLD_NO				");
		sql.append("		FROM					");
		sql.append("			HOUSEMAG			");
		sql.append("		GROUP BY				");
		sql.append("			BLD_NO				");
		sql.append("	) hos on hos.BLD_NO=bld.BLD_NO		");
		sql.append("	WHERE 1 = 1							");
		sql.append("	AND bld.HOUSE_SUM>hos.hosSum		");
		sql.append("	OR( bld.HOUSE_SUM>0 AND hos.hosSum is  null)		");
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setProperties(param); 
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryInfoBy(String hosNo, String bldNo,
			String hosAreaMin, String hosAreaMax, String hosTp,
			String sellPceMin, String sellPceMax, String costPceMin,
			String costPceMax, String dscntRate, String houseSell,
			 Pagination pg) {
		StringBuffer sql = new StringBuffer();
		StringBuffer wheresql = new StringBuffer();
		StringBuffer countsql = new StringBuffer();
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" SELECT 							");
		sql.append("  	 HOS_NO hosNo					");
		sql.append(" 	,BLD_NO bldNo					");
		sql.append(" 	,HOS_AREA hosArea				");
		sql.append(" 	,HOS_TP hosTp					");
		sql.append(" 	,COST_PCE costPce				");
		sql.append(" 	,SELL_PCE sellPce				");
		sql.append(" 	,DSCNT_RATE dscntRate			");
		sql.append(" 	,SELL_ST sellSt					");
		sql.append(" 	,HOS_INTRO hosIntro				");
		sql.append(" 	,IS_RECMD isRecmd				");
		sql.append(" FROM HOUSEMAG 						");
		sql.append(" WHERE 1 = 1 						");
		if(StringUtils.isNotEmpty(houseSell)){
			wheresql.append(" and SELL_ST = :houseSell 	");
			param.put("houseSell", houseSell);
		}
		if(StringUtils.isNotEmpty(hosNo)){
			wheresql.append(" and HOS_NO = :hosNo 	");
			param.put("hosNo", hosNo);
		}
		if(StringUtils.isNotEmpty(bldNo)){
			wheresql.append(" and BLD_NO = :bldNo 	");
			param.put("bldNo", bldNo);
		}
		if(StringUtils.isNotEmpty(hosAreaMin)&&StringUtils.isNotEmpty(hosAreaMax)){
			wheresql.append(" and HOS_AREA BETWEEN "+hosAreaMin+" AND "+hosAreaMax);
		}
		if(StringUtils.isNotEmpty(hosAreaMin)&&StringUtils.isEmpty(hosAreaMax)){
			wheresql.append(" and HOS_AREA >= :hosAreaMin");
			param.put("hosAreaMin", hosAreaMin);
		}
		if(StringUtils.isEmpty(hosAreaMin)&&StringUtils.isNotEmpty(hosAreaMax)){
			wheresql.append(" and HOS_AREA <= :hosAreaMax");
			param.put("hosAreaMax", hosAreaMax);
		}
		if(StringUtils.isNotEmpty(costPceMin)&&StringUtils.isNotEmpty(costPceMax)){
			wheresql.append(" and COST_PCE BETWEEN "+costPceMin+" AND "+costPceMax);
		}
		if(StringUtils.isNotEmpty(costPceMin)&&StringUtils.isEmpty(costPceMax)){
			wheresql.append(" and COST_PCE >= :costPceMin");
			param.put("costPceMin", costPceMin);
		}
		if(StringUtils.isEmpty(costPceMin)&&StringUtils.isNotEmpty(costPceMax)){
			wheresql.append(" and COST_PCE <= :costPceMax");
			param.put("costPceMax", costPceMax);
		}
		if(StringUtils.isNotEmpty(hosTp)){
			wheresql.append(" and HOS_TP = :hosTp 	");
			param.put("hosTp", hosTp);
		}
		if(Constants.HASDSCNTRATE.equals(dscntRate)){
			wheresql.append(" and DSCNT_RATE >0 	");
		}
		if(Constants.NODSCNTRATE.equals(dscntRate)){
			wheresql.append(" and DSCNT_RATE =0 	");
		}
		if(StringUtils.isNotEmpty(sellPceMin)&&StringUtils.isNotEmpty(sellPceMax)){
			wheresql.append(" and SELL_PCE BETWEEN "+sellPceMin+" AND "+sellPceMax);
		}
		if(StringUtils.isNotEmpty(sellPceMin)&&StringUtils.isEmpty(sellPceMax)){
			wheresql.append(" and SELL_PCE >= :sellPceMin");
			param.put("sellPceMin", sellPceMin);
		}
		if(StringUtils.isEmpty(sellPceMin)&&StringUtils.isNotEmpty(sellPceMax)){
			wheresql.append(" and SELL_PCE <= :sellPceMax");
			param.put("sellPceMax", sellPceMax);
		}
		if(pg!=null){//有物理分页
		if(StringUtils.isNotEmpty(pg.getSec())){
			wheresql.append(" ORDER BY  "+pg.getSort()+"  "+pg.getSec());
		}
		
		sql.append(wheresql);
		countsql.append(" select  				");
		countsql.append("  	 HOS_NO hosNo					");
		countsql.append(" 	,HOS_NO hosNo1					");
		countsql.append(" 	,BLD_NO bldNo					");
		countsql.append(" 	,HOS_AREA hosArea				");
		countsql.append(" 	,HOS_TP hosTp					");
		countsql.append(" 	,COST_PCE costPce				");
		countsql.append(" 	,SELL_PCE sellPce				");
		countsql.append(" 	,DSCNT_RATE dscntRate			");
		countsql.append(" 	,SELL_ST sellSt					");
		countsql.append(" 	,HOS_INTRO hosIntro				");
		countsql.append(" 	,IS_RECMD isRecmd				");
		countsql.append(" 	, count(*)total		");
		countsql.append(" FROM HOUSEMAG 		");
		countsql.append("  where 1 = 1			");
		countsql.append(wheresql);
		Query querycount = this.getSession().createSQLQuery(countsql.toString()).setProperties(param);
		List<Map<String, Object>>countList = querycount.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setProperties(param); 
		resultList = query.setFirstResult(pg.getFirstResult()).setMaxResults(pg.getMaxResults()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total",countList.get(0).get("total"));
		resultList.add(map);
		}else{//无物理分页
			Query query = this.getSession().createSQLQuery(sql.toString());
			query.setProperties(param); 
			resultList = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryHosIntroByHosNo(String hosNo) {
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append("	SELECT					");
		sql.append("		HOS_INTRO hosIntro	");
		sql.append("	FROM HOUSEMAG	");
		sql.append("	WHERE 1 = 1				");
		if(StringUtils.isNotEmpty(hosNo)){
			sql.append("	AND HOS_NO = '"+hosNo+"'");
		}else{
			return new ArrayList<Map<String,Object>>();
		}
		Query query = this.getSession().createSQLQuery(sql.toString());
		
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>queryHosSellByHosNo(String hosNo){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append("	SELECT					");
		sql.append("		hs.HOS_NO,			");
		sql.append("		hs.TXN_DT,			");
		sql.append("		hs.HOS_PFT,			");
		sql.append("		wi.WRKR_NO,			");
		sql.append("		wi.WRKR_NM,			");
		sql.append("		ci.CUS_ICD,			");
		sql.append("		ci.CUS_NM			");
		sql.append("	FROM HOSSELLCRCS hs		");
		sql.append("	LEFT JOIN wrkrinfo wi ON wi.WRKR_NO = hs.WRKR_NO		");
		sql.append("	LEFT JOIN cusinfo ci ON ci.CUS_NO = hs.CUS_NO			");
		sql.append("	WHERE 1 = 1				");
		if(StringUtils.isNotEmpty(hosNo)){
			sql.append("	AND HOS_NO = '"+hosNo+"'");
		}else{
			return new ArrayList<Map<String,Object>>();
		}
		Query query = this.getSession().createSQLQuery(sql.toString());
		
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public void deleteHosInfoByBldNo(String BldNo) {
		StringBuffer sql = new StringBuffer();
		sql.append("	DELETE					");
		sql.append("	FROM HOUSEMAG			");
		sql.append("	WHERE					");
		sql.append("		BLD_NO = '"+BldNo+"'");
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.executeUpdate();
	}

	
	

}
