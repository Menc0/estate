package com.sise.cwh.estate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.sise.cwh.estate.dao.WrkrInfoDao;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.util.Pagination;

public class WrkrInfoDaoImpl extends BaseDaoImpl<Wrkrinfo> implements WrkrInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 按条件查询出员工信息并分页显示
	 * @param wrkrNo 工号
	 * @param wrkrNm 名字
	 * @param wrkrPst 职位
	 * @param roleId 角色
	 * 
	 */
	public List<Map<String, Object>> queryWrkrInfoBy(String wrkrNo,
													String wrkrNm, 
													String wrkrPst, 
													String roleId,
													Pagination pg) {
		
		StringBuffer sql = new StringBuffer();
		StringBuffer wheresql = new StringBuffer();
		StringBuffer countsql = new StringBuffer();
		List<Map<String, Object>>resultList = new ArrayList<Map<String,Object>>();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" SELECT 							");
		sql.append("  	 WRKR_NO wrkrNo					");
		sql.append(" 	,WRKR_NO wrkrNo1				");
		sql.append(" 	,WRKR_NM wrkrNm					");
		sql.append(" 	,WRKR_ICD wrkrIcd				");
		sql.append(" 	,WRKR_PST wrkrPst				");
		sql.append(" 	,WRKR_TELL wrkrTell				");
		sql.append(" 	,LOGIN_PSW loginPsw				");
		sql.append(" 	,SELL_SCR sellScr				");
		sql.append(" 	,ROLE_NM roleNm					");
		sql.append(" FROM WRKRINFO w					");
		sql.append(" LEFT JOIN role r					");
		sql.append(" 	on r.ROLE_ID = w.ROLE_ID		");
		sql.append(" where 1 = 1 						");
		if(StringUtils.isNotEmpty(wrkrNo)){
			wheresql.append(" and WRKR_NO = :wrkrNo 	");
			param.put("wrkrNo", wrkrNo);
		}
		if(StringUtils.isNotEmpty(wrkrNm)){
			wheresql.append(" and WRKR_NM = :wrkrNm 	");
			param.put("wrkrNm", wrkrNm);
		}
		if(StringUtils.isNotEmpty(wrkrPst)){
			wheresql.append(" and WRKR_PST = :wrkrPst 	");
			param.put("wrkrPst", wrkrPst);
		}
		if(StringUtils.isNotEmpty(roleId)){
			wheresql.append(" and w.ROLE_ID = :roleId 	");
			param.put("roleId", roleId);
		}
		if(pg!=null){//有物理分页
		if(StringUtils.isNotEmpty(pg.getSec())){
			wheresql.append(" ORDER BY  "+pg.getSort()+"  "+pg.getSec());
		}
		
		sql.append(wheresql);
		countsql.append(" select  				");
		countsql.append(" 	WRKR_NO wrkrNo		");
		countsql.append(" 	, SELL_SCR sellScr	");
		countsql.append(" 	, count(*)total		");
		countsql.append(" from WRKRINFO w		");
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

	@SuppressWarnings("unchecked")
	@Override
	public String getWrkrNo() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT 							");
		sql.append(" TRIM(MAX(WRKR_NO)+1) wrkrNo		");
		sql.append(" FROM WRKRINFO						");
		sql.append(" WHERE 1 = 1						");
		Query query = this.getSession().createSQLQuery(sql.toString());
		List<Map<String, Object>>list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list.get(0).get("wrkrNo").toString();
	}


}
