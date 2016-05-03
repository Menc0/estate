package com.sise.cwh.estate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.sise.cwh.estate.dao.IntrateMagDao;
import com.sise.cwh.estate.entity.IntrateMag;

public class IntrateMagDaoImpl extends BaseDaoImpl<IntrateMag> implements IntrateMagDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryIntrateMag(String prdSum,
			String intrate) {
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" 	SELECT							");
		sql.append(" 		BYSTG_SUM prdSum			");
		sql.append(" 		,INTRATE intrate			");
		sql.append(" 	WHERE 1 = 1						");
		if(StringUtils.isNotEmpty(prdSum)){
		sql.append("	AND BYSTG_SUM =:prdSum			");
		}
		if(StringUtils.isNotEmpty(intrate)){
		sql.append("	AND INTRATE =:intrate			");
		}
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setProperties(param); 
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		resultList = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return resultList;
	}

}
