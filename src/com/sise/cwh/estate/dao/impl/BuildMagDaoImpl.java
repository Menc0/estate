package com.sise.cwh.estate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.sise.cwh.estate.dao.BuildMagDao;
import com.sise.cwh.estate.entity.Buildmag;
import com.sise.cwh.estate.util.Pagination;

public class BuildMagDaoImpl extends BaseDaoImpl<Buildmag> implements
		BuildMagDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryBuildInfoBy(String bldNo,
			String bldNm, Pagination pg) {
		StringBuffer sql = new StringBuffer();
		StringBuffer wheresql = new StringBuffer();
		StringBuffer countsql = new StringBuffer();
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" SELECT 							");
		sql.append("  	 BLD_NO bldNo					");
		sql.append(" 	,BLD_NO bldNo1					");
		sql.append(" 	,BLD_NM bldNm					");
		sql.append(" 	,BLD_SITE bldSite				");
		sql.append(" 	,BLD_AREA bldArea				");
		sql.append(" 	,FLO_SUM floSum					");
		sql.append(" 	,HOUSE_SUM houseSum				");
		sql.append(" 	,BLD_INVEST_MON bldInvestMon	");
		sql.append(" 	,BLD_INTRO bldIntro				");
		sql.append(" 	,IS_RECMD isRecmd				");
		sql.append(" FROM BUILDMAG 						");
		sql.append(" WHERE 1 = 1 						");
		if(StringUtils.isNotEmpty(bldNo)){
			wheresql.append(" and BLD_NO = :bldNo 	");
			param.put("bldNo", bldNo);
		}
		if(StringUtils.isNotEmpty(bldNm)){
			wheresql.append(" and BLD_NM = :bldNm 	");
			param.put("bldNm", bldNm);
		}
		if(pg!=null){//有物理分页
		if(StringUtils.isNotEmpty(pg.getSec())){
			wheresql.append(" ORDER BY  "+pg.getSort()+"  "+pg.getSec());
		}
		
		sql.append(wheresql);
		countsql.append(" select  				");
		countsql.append(" 	BLD_NO bldNo		");
		countsql.append(" 	,BLD_AREA bldArea	");
		countsql.append(" 	,FLO_SUM floSum		");
		countsql.append(" 	,HOUSE_SUM houseSum	");
		countsql.append(" 	,BLD_INVEST_MON bldInvestMon	");
		countsql.append(" 	,IS_RECMD isRecmd				");
		countsql.append(" 	, count(*)total		");
		countsql.append(" FROM BUILDMAG 		");
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
