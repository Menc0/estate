package com.sise.cwh.estate.dao.impl;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.sise.cwh.estate.dao.RoleMenuDao;
import com.sise.cwh.estate.entity.Rolemenu;
public class RoleMenuDaoImpl extends BaseDaoImpl<Rolemenu> implements RoleMenuDao {

	/**
	 * 工具角色ID查询出该角色对应的菜单编码
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rolemenu>queryRolemenusByRoleId(String roleId){
		StringBuffer hql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		hql.append(" 	from Rolemenu rm					");
		hql.append(" 	where 1=1							");
		if(StringUtils.isNotEmpty(roleId)){
			hql.append(" and rm.id.roleId = :roleId 		");
			param.put("roleId", roleId);
		}
		Query query = this.getSession().createQuery(hql.toString());
		query.setProperties(param);
		return query.list();
	}
	
	public void deleteRolemenus(String roleId){
		StringBuffer hql = new StringBuffer();
		HashMap<String, Object>param = new HashMap<String, Object>();
		hql.append(" 	delete Rolemenu rm					");
		hql.append(" where rm.id.roleId = :roleId 			");
		param.put("roleId", roleId);
		Query query = this.getSession().createQuery(hql.toString());
		query.setProperties(param).executeUpdate();
		//return query.list();
	}
	
	public void deleteRolemenu(Rolemenu rolemenu){
		 this.getSession().delete(rolemenu);
	}
	

}
