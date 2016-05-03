package com.sise.cwh.estate.dao;

import java.util.List;

import com.sise.cwh.estate.entity.Rolemenu;

public interface RoleMenuDao extends BaseDao<Rolemenu>{
	/**
	 * 根据角色ID查询角色菜单
	 * @param roleId
	 * @return
	 */
	public List<Rolemenu>queryRolemenusByRoleId(String roleId);
	
	/**
	 * 指定角色ID删除多个角色菜单
	 * @param roleId
	 */
	public void deleteRolemenus(String roleId);
	
	/**
	 * 指定复合主键删除一条角色菜单
	 * @param rolemenu
	 */
	public void deleteRolemenu(Rolemenu rolemenu);
}
