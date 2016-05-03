package com.sise.cwh.estate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.sise.cwh.estate.dao.CusInfoDao;
import com.sise.cwh.estate.entity.Cusinfo;
import com.sise.cwh.estate.util.Pagination;

public class CusInfoDaoImpl extends BaseDaoImpl<Cusinfo> implements CusInfoDao{


	@Override
	public Cusinfo getCusinfoByCusIcd(String cusIcd) {
		StringBuffer hql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		hql.append("	from		");
		hql.append("		Cusinfo	");
		hql.append("	where 1 = 1	");
		if(StringUtils.isNotEmpty(cusIcd)){
		hql.append("	and cusIcd =:cusIcd	");
		param.put("cusIcd", cusIcd);
		}else{
			return new Cusinfo();
		}
		Query query = this.getSession().createQuery(hql.toString());
		query.setProperties(param);
		return (Cusinfo) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryCusInfoBy(String cusIcd,
			String cusNm, String cusTel, Pagination pg) {
		StringBuffer sql = new StringBuffer();
		StringBuffer wheresql = new StringBuffer();
		StringBuffer countsql = new StringBuffer();
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" SELECT 							");
		sql.append("  	 CUS_NO cusNo					");
		sql.append(" 	,CUS_ICD cusIcd					");
		sql.append(" 	,CUS_NM cusNm					");
		sql.append(" 	,CUS_TEL cusTel					");
		sql.append(" 	,CUS_EMAIL cusEmail				");
		sql.append(" FROM CUSINFO c						");
		sql.append(" where 1 = 1 						");
		if(StringUtils.isNotEmpty(cusIcd)){
			wheresql.append(" and CUS_ICD = :cusIcd 	");
			param.put("cusIcd", cusIcd);
		}
		if(StringUtils.isNotEmpty(cusNm)){
			wheresql.append(" and CUS_NM = :cusNm 	");
			param.put("cusNm", cusNm);
		}
		if(StringUtils.isNotEmpty(cusTel)){
			wheresql.append(" and CUS_TEL = :cusTel 	");
			param.put("cusTel", cusTel);
		}
		if(pg!=null){//有物理分页
		if(StringUtils.isNotEmpty(pg.getSec())){
			wheresql.append(" ORDER BY  "+pg.getSort()+"  "+pg.getSec());
		}
		
		sql.append(wheresql);
		countsql.append(" select  				");
		countsql.append("  	 CUS_NO cusNo					");
		countsql.append(" 	, count(*)total		");
		countsql.append(" from CUSINFO c		");
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
	
}
