package com.sise.cwh.estate.service;

import java.util.List;

import com.sise.cwh.estate.entity.Rolemenu;

public interface RoleMenuService {

	/**
	 * 保存角色菜单
	 * @param menuCode
	 * @param roleId
	 */
	public void saveRoleMenu(String[] menuCode,String roleId);
	
	/**
	 * 根据角色ID查询角色菜单
	 * @param roleId
	 * @return
	 */
	public List<Rolemenu>queryRolemenusByRoleId(String roleId);
	
	/**
	 * 删除角色菜单
	 * @param roleId
	 */
	public void deleteRoleMenu(String roleId);
	
	/**
	 * 更新角色菜单
	 * @param menuCode
	 * @param roleId
	 */
	public void updateRoleMenu(String[] menuCode,String roleId);
}
