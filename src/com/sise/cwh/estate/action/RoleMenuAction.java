package com.sise.cwh.estate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.Menu;
import com.sise.cwh.estate.entity.Role;
import com.sise.cwh.estate.entity.Rolemenu;
import com.sise.cwh.estate.service.RoleMenuService;
import com.sise.cwh.estate.util.Constants;
import com.sise.cwh.estate.util.QueryFactory;

@Controller
@RequestMapping("/roleMenu")
public class RoleMenuAction {
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="roleMenuService")
	private RoleMenuService roleMenuService;
	
	
	/**
	 * 角色下拉框
	 * @param roleId
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryRole")
	public @ResponseBody List<Map<String, Object>>getRoleByRoleIdAjax(String roleId,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Role>roles = queryFactory.queryRoleByRoleId(roleId);
		List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
		List<String>list2 = new ArrayList<String>();
		List<Rolemenu>roleMenus = roleMenuService.queryRolemenusByRoleId(roleId);
		for(Rolemenu rolemenu:roleMenus){
			String string = new String();
			for(int i=0;i<list2.size();i++){
			string = (String) list2.get(i);
			}
			if(!(string.contains(rolemenu.getId().getRoleId()))){
				list2.add(rolemenu.getId().getRoleId());
			}
		}
		if(roles.size()>0){
			for(Role role :roles){
				//判断该角色是否已经存在角色菜单，没有则允许生成下拉框添加
				if(!(list2.contains(role.getRoleId()))){
				Map<String, Object>maps = new HashMap<String, Object>();
				maps.put("id", role.getRoleId());
				maps.put("text", role.getRoleNm());
				list.add(maps);
				}
				
			}
		}
		
		return list;
	}
	
	/**
	 * 查询出所有菜单
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllMenu")
	public @ResponseBody List<Map<String, Object>>queryMenuAjax(String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
		String sprrMenuCode = null;
		if(StringUtils.isNotEmpty(id)){
			sprrMenuCode = new String(id.getBytes("iso8859-1"),"utf-8");
		}else{
			sprrMenuCode = Constants.ESTATE;
		}
		List<Menu>menus = queryFactory.findMenus(null,sprrMenuCode, null);
		for(Menu menu:menus){
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("id",menu.getMenuCode());
			map.put("text", menu.getMenuNm());
			map.put("state", menu.getState());
			map.put("iconCls", menu.getIconcls());
			list.add(map);
		}
		return list;
	} 
	
	
	/**
	 * 保存角色菜单
	 * @param roleId
	 * @param menuCode
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveRoleMenu")
	public @ResponseBody List<Map<String, Object>>saveRoleMenuAjax(String roleId,String menuCode,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String[] menus=menuCode.split(",");
		
		List<Map<String, Object>>list =new ArrayList<Map<String,Object>>();
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			roleMenuService.saveRoleMenu(menus, roleId);
		} catch (Exception e) {
			map.clear();
			map.put("success", "false");
		}
		list.add(map);
		return list;
	} 
	
	/**
	 * 更新权限菜单
	 * @param roleId
	 * @param menuCode
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRoleMenu")
	public @ResponseBody List<Map<String, Object>>updateRoleMenuAjax(String roleId,String menuCode,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String[] menus=menuCode.split(",");
		List<Map<String, Object>>list =new ArrayList<Map<String,Object>>();
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "true");
		try {
			roleMenuService.updateRoleMenu(menus, roleId);
		} catch (Exception e) {
			map.clear();
			map.put("success", "false");
		}
		list.add(map);
		return list;
	} 
	
	/**
	 * 查询出拥有权限菜单的角色
	 * @param roleId
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryRoleMenu")
	public @ResponseBody List<Map<String, Object>>queryRoleMenuAjax(String roleId,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Rolemenu>rolemenus = roleMenuService.queryRolemenusByRoleId(roleId);
		List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
		for(Rolemenu rolemenu:rolemenus){
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("roleId", rolemenu.getId().getRoleId());
			Role role = queryFactory.findByKey(Role.class, rolemenu.getId().getRoleId());
			map.put("roleNm", role.getRoleNm());
			//map.put("menuUrl", "<a href='' id='"+rolemenu.getId().getRoleId()+"' name='menuUrl' style='color:red'>点击查看</a>");
			String string = new String();
			for(int i=0;i<list.size();i++){
				string=(String) list.get(i).get("roleId");
			}
			if(string.contains(map.get("roleId").toString())){
				}else{
				list.add(map);
				
				}
		}
		return list;
	} 
	
	/**
	 * 查询出该角色所拥有的菜单权限
	 * @param roleId
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryRoleMenuByRoleId")
	public @ResponseBody List<Map<String, Object>>queryRoleMenuByRoleIdAjax(String roleId,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Rolemenu>rolemenus = roleMenuService.queryRolemenusByRoleId(roleId);
		List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
		Map<String, Object>map = new HashMap<String, Object>();
		List<String>menulist = new ArrayList<String>();
		for(Rolemenu rolemenu:rolemenus){
		//Menu menu = queryFactory.findByKey(Menu.class, rolemenu.getId().getMenuCode());
		menulist.add(rolemenu.getId().getMenuCode());
		}
		map.put("roleId", rolemenus.get(0).getId().getRoleId());
		Role role = queryFactory.findByKey(Role.class, rolemenus.get(0).getId().getRoleId());
		map.put("roleNm", role.getRoleNm());
		map.put("menuCode", menulist);
		list.add(map);
		return list;
	} 
	
	
	/**
	 * 删除该角色菜单关系
	 * @param roleId
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteRoleMenu")
	public @ResponseBody List<Map<String, Object>>deleteRoleMenuByRoleIdAjax(String roleId,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
		Map<String, Object>map = new HashMap<String, Object>();
			map.put("isSuccess", "true");
			try {
				roleMenuService.deleteRoleMenu(roleId);
			} catch (Exception e) {
				map.clear();
				map.put("isSuccess", "false");
			}
			list.add(map);
		return list;
		
	} 
	
	
}
