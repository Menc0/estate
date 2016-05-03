package com.sise.cwh.estate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sise.cwh.estate.dao.RoleMenuDao;
import com.sise.cwh.estate.entity.Rolemenu;
import com.sise.cwh.estate.entity.RolemenuId;
import com.sise.cwh.estate.service.RoleMenuService;

public class RoleMenuServiceImpl implements RoleMenuService {

	private RoleMenuDao roleMenuDao;
	public void setRoleMenuDao(RoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}


	@Override
	public void saveRoleMenu(String[] menuCode, String roleId) {

		for(int i=0;i<menuCode.length;i++){
			Rolemenu rolemenu = new Rolemenu();
			RolemenuId rolemenuId = new RolemenuId(roleId,menuCode[i]);
			rolemenu.setId(rolemenuId);
			roleMenuDao.save(rolemenu);
		}
	}


	@Override
	public List<Rolemenu> queryRolemenusByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return roleMenuDao.queryRolemenusByRoleId(roleId);
	}


	@Override
	public void deleteRoleMenu(String roleIds) {
		// TODO Auto-generated method stub
		String []roleId = roleIds.split(",");
		for(int i=0;i<roleId.length;i++){
		roleMenuDao.deleteRolemenus(roleId[i]);
		}
	}


	@Override
	public void updateRoleMenu(String[] menuCode, String roleId) {
		//roleMenuDao.deleteRolemenus(roleId);
		List<Rolemenu>rolemenus = roleMenuDao.queryRolemenusByRoleId(roleId);
		List<String>menuList = new ArrayList<String>();
		List<String>menuList2 = new ArrayList<String>();
		for(Rolemenu rolemenu :rolemenus){
			menuList.add(rolemenu.getId().getMenuCode());
		}
		//如果数据表中没有该数据则添加
		for(int i=0;i<menuCode.length;i++){
			    menuList2.add(menuCode[i]);
			if(!(menuList.contains(menuCode[i]))){
				Rolemenu rolemenu = new Rolemenu();
				RolemenuId rolemenuId = new RolemenuId(roleId,menuCode[i]);
				rolemenu.setId(rolemenuId);
				roleMenuDao.save(rolemenu);
			}
			
		}
		//如果被勾选掉，则从数据库中删除点该条数据
		for(int i=0;i<menuList.size();i++){
			if(!(menuList2.contains(menuList.get(i)))){
				RolemenuId rolemenuId = new RolemenuId(roleId,menuList.get(i));
				Rolemenu rolemenu = roleMenuDao.findByKey(Rolemenu.class,rolemenuId);
				roleMenuDao.deleteRolemenu(rolemenu);
			}
		}
		
	}

}
