package com.sise.cwh.estate.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;

import com.sise.cwh.estate.entity.Menu;
import com.sise.cwh.estate.entity.Role;
import com.sise.cwh.estate.entity.Rolemenu;

public class QueryFactory {
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public <T> T findByKey(Class<T> obj, Serializable key) {
		if(key==null){
			return null;
		}
		try {
			return(T)sessionFactory.getCurrentSession().get(obj, key);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 查询自增长下一个ID
	 * @param table
	 * @return
	 */
	public Integer getAutoIncrement(String table){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT auto_increment FROM information_schema.TABLES WHERE  TABLE_NAME='"+table+"'");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.valueOf(query.list().get(0).toString());
		
	}
	
/**
 * 获取菜单
 * @param menuCode
 * @param sprrMenuCode
 * @param seqLength
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<Menu>findMenus(String menuCode, String sprrMenuCode, Integer seqLength){
		StringBuffer hql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		hql.append(" from Menu ");
		hql.append(" where 1=1");
		if(StringUtils.isNotEmpty(menuCode)){
			hql.append(" and menuCode = :menuCode ");
			param.put("menuCode", menuCode);
		}
		if(StringUtils.isNotEmpty(sprrMenuCode)){
			hql.append(" and sprrMenuCode = :sprrMenuCode ");
			param.put("sprrMenuCode", sprrMenuCode);
		}
		if(seqLength!=null&&seqLength!=0){
			hql.append(" and length(seq) = :seqLength ");
			param.put("seqLength", seqLength);
		}
		hql.append(" order by seq asc");
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setProperties(param);
		return query.list();
	}
	
	/**
	 * 根据角色ID查询出该角色对应的菜单编码
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rolemenu>queryRolemenusByRoleId(String roleId){
		StringBuffer hql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		hql.append(" from Rolemenu rm");
		hql.append(" where 1=1");
		if(StringUtils.isNotEmpty(roleId)){
			hql.append(" and rm.id.roleId = :roleId ");
			param.put("roleId", roleId);
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setProperties(param);
		return query.list();
	}
	
	
	/**
	 * 根据角色ID查询角色
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role>queryRoleByRoleId(String roleId){
		StringBuffer hql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		hql.append(" from Role ");
		hql.append(" where 1=1");
		if(StringUtils.isNotEmpty(roleId)){
			hql.append(" and roleId = :roleId ");
			param.put("roleId", roleId);
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setProperties(param);
		return query.list();
	}
	
	/**
	 * 查询每月销量
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public List<Map<String, Object>>querySellSumMonth(){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" select 								");
		sql.append(" 	COUNT(*) as total 					");
		sql.append(" 	, TXN_DT 							");
		sql.append(" 	,DATE_FORMAT( TXN_DT,'%m' )AS Moth 	");//获取月份
		sql.append(" 	from hossellcrcs					");
		sql.append("  GROUP BY  						");
		sql.append("  	DATE_FORMAT( TXN_DT, '%m' )			");//按月份
		sql.append("  HAVING DATE_FORMAT( TXN_DT,'%Y' ) = DATE_FORMAT( current_timestamp(),'%Y' ) 		");//获取当年
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 
	 * 查询近三个月热销前五楼房
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Map<String, Object>>queryBigSellBldPreThrMonth(){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" select 								");
		sql.append(" 	COUNT(*) as total 					");
		sql.append(" 	, TXN_DT 							");
		sql.append(" 	, BLD_NO						 	");
		sql.append(" 	from hossellcrcs					");
		sql.append(" 	where								");
		sql.append(" 	 TXN_DT<= current_timestamp()		");
		sql.append(" 	and								 	");
		sql.append(" 	  TXN_DT>date_sub(current_timestamp(), interval 3 month)	");
		sql.append("  GROUP BY  							");
		sql.append("  	BLD_NO								");
		sql.append("  ORDER BY								");
		sql.append("  	total DESC							");
		sql.append("  LIMIT 5								");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 查询出近一月销售成绩前十员工
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>querySellScrTop10(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select 																	");
		sql.append(" 	wrk.WRKR_NO, 															");
		sql.append(" 	wrk.WRKR_NM, 															");
		sql.append(" 	COUNT(*) score															");
		sql.append(" from wrkrinfo wrk															");
		sql.append(" INNER JOIN hossellcrcs hsc ON wrk.WRKR_NO = hsc.WRKR_NO					");
		sql.append(" WHERE																		");
		sql.append(" 	DATE_FORMAT(TXN_DT, '%m') = DATE_FORMAT(CURRENT_TIMESTAMP(), '%m')		");
		sql.append(" GROUP BY wrk.WRKR_NO ORDER BY COUNT(*) desc LIMIT 10						");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	
	/**
	 * 查询推荐/或用于楼房查询子下房间
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Map<String, Object>>queryRecmdHouse(String bldNo,String recmd,String ip){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" select 								");
		sql.append(" 	  hos.HOS_NO 	HOS_NO				");
		sql.append(" 	, HOS_IMG 							");
		sql.append(" 	, BLD_NO						 	");
		sql.append(" 	, HOS_TP						 	");
		sql.append(" 	, SELL_PCE						 	");
		sql.append(" 	, HOS_AREA						 	");
		sql.append(" 	, clh.likeTotal		likeTotal		");
		if(StringUtils.isNotEmpty(ip)){
		sql.append(" 	, liked.liked 						");
		}
		sql.append(" 	from housemag	hos					");
		sql.append(" LEFT JOIN (							");
		sql.append(" 	SELECT								");
		sql.append(" 		HOS_NO,							");
		sql.append(" 		COUNT(*) likeTotal				");
		sql.append(" 	FROM								");
		sql.append(" 		cuslikehos						");
		sql.append(" 	GROUP BY							");
		sql.append(" 		HOS_NO							");
		sql.append(" 		) clh ON clh.HOS_NO = hos.HOS_NO");
		if(StringUtils.isNotEmpty(ip)){
		sql.append(" 	LEFT JOIN(										");
		sql.append(" 		SELECT	DISTINCT							");
		sql.append(" 			HOS_NO,									");
		sql.append(" 			HOS_NO liked							");
		sql.append(" 		FROM										");
		sql.append(" 			cuslikehos								");
		sql.append(" 		where ip=:ip								");
		sql.append(" 			) liked ON liked.hos_no = hos.hos_no	");
		param.put("ip", ip);
		}
		sql.append(" 	where	1=1							");
		if(StringUtils.isNotEmpty(recmd)){
		sql.append(" 	and 								");
		sql.append(" 	 IS_RECMD = 1						");
		}
		sql.append(" 	and 								");
		sql.append(" 	 SELL_ST = 0						");
		if (StringUtils.isNotEmpty(bldNo)) {
		sql.append(" 	and 								");
		sql.append(" 	 BLD_NO = :bldNo					");
		param.put("bldNo", bldNo);
		}
		sql.append("  ORDER BY								");
		sql.append("  	CREATE_DATE DESC					");
		if(StringUtils.isNotEmpty(recmd)){
		sql.append("  LIMIT 9								");
		}
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.setProperties(param);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 查询房间简介等信息
	 * @param hosNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>getHouseIntro(String hosNo){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" select 								");
		sql.append(" 	  HOS_INTRO 						");
		sql.append(" 	, HOS_NO 							");
		sql.append(" 	, HOS_IMG 							");
		sql.append(" 	, BLD_NO						 	");
		sql.append(" 	, HOS_TP						 	");
		sql.append(" 	, SELL_PCE						 	");
		sql.append(" 	, HOS_AREA						 	");
		sql.append(" 	from housemag						");
		sql.append(" 	where								");
		sql.append(" 	 HOS_NO = :hosNo					");
		param.put("hosNo", hosNo);
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.setProperties(param);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 查询出所有未销售房间
	 * @param hosNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>getAllHouse(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select 								");
		sql.append(" 	  HOS_INTRO 						");
		sql.append(" 	, HOS_NO 							");
		sql.append(" 	, HOS_IMG 							");
		sql.append(" 	, BLD_NO						 	");
		sql.append(" 	, HOS_TP						 	");
		sql.append(" 	, SELL_PCE						 	");
		sql.append(" 	, HOS_AREA						 	");
		sql.append(" 	from housemag						");
		sql.append(" 	where								");
		sql.append(" 	 SELL_ST = 0						");
		sql.append(" 	order by							");
		sql.append(" 	 CREATE_DATE desc					");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 查询出所有楼房，推荐优先/或条件用于查询简介
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>getAllBuild(String bldNo){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" select 								");
		sql.append(" 	 BLD_NO, 							");
		sql.append(" 	 BLD_NM, 							");
		sql.append(" 	 BLD_AREA, 							");
		sql.append(" 	 BLD_SITE,						 	");
		sql.append(" 	 BLD_IMG,						 	");
		sql.append(" 	 BLD_INVEST_MON,					");
		sql.append(" 	 BLD_INTRO							");
		sql.append(" 	from buildmag						");
		if(StringUtils.isNotEmpty(bldNo)){
		sql.append(" 	 where								");
		sql.append(" 	 BLD_NO =:bldNo						");
		param.put("bldNo", bldNo);
		}
		sql.append(" 	order by							");
		sql.append(" 	 IS_RECMD DESC  					");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.setProperties(param);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	
	/**
	 * 查询出推荐楼房
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>getRecmdBuild(){
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		sql.append(" select 								");
		sql.append(" 	 BLD_NO, 							");
		sql.append(" 	 BLD_NM, 							");
		sql.append(" 	 BLD_AREA, 							");
		sql.append(" 	 BLD_SITE,						 	");
		sql.append(" 	 BLD_IMG,						 	");
		sql.append(" 	 BLD_INVEST_MON,					");
		sql.append(" 	 BLD_INTRO							");
		sql.append(" 	from buildmag						");
		sql.append(" 	where								");
		sql.append(" 	 IS_RECMD = 1						");
		sql.append(" 	order by							");
		sql.append(" 	 CREATE_DATE DESC  					");
		sql.append("  LIMIT 9								");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.setProperties(param);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	
	/**
	 * 按条件查询出所有未销售房间
	 * @param hosTp 户型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>getHousebyHosTp(String hosTp){
		StringBuffer sql = new StringBuffer();
		sql.append(" select 								");
		sql.append(" 	  HOS_INTRO 						");
		sql.append(" 	, HOS_NO 							");
		sql.append(" 	, HOS_IMG 							");
		sql.append(" 	, BLD_NO						 	");
		sql.append(" 	, HOS_TP						 	");
		sql.append(" 	, SELL_PCE						 	");
		sql.append(" 	, HOS_AREA						 	");
		sql.append(" 	from housemag						");
		sql.append(" 	where								");
		sql.append(" 	 SELL_ST = 0						");
		if(StringUtils.isNotEmpty(hosTp)){
		sql.append(" 	and									");
		sql.append(" 	 HOS_TP ='"+hosTp+"'				");
		}
		sql.append(" 	order by							");
		sql.append(" 	 CREATE_DATE desc					");
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
}
