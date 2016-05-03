package com.sise.cwh.estate.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.Menu;
import com.sise.cwh.estate.entity.Rolemenu;
import com.sise.cwh.estate.entity.RolemenuId;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.util.Constants;
import com.sise.cwh.estate.util.QueryFactory;


@Controller
@RequestMapping("/menu")
public class MenuAction {
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	
	@RequestMapping(value="/queryMenuAjax",method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> queryMenuAjax(String id,String roleId,HttpServletResponse response) throws Exception{
		List<Rolemenu> rolemenus = queryFactory.queryRolemenusByRoleId(roleId);
		String sprrMenuCode = null;
		if(StringUtils.isNotEmpty(id)){
			sprrMenuCode = new String(id.getBytes("iso8859-1"),"utf-8");
		}else{
			sprrMenuCode = Constants.ESTATE;
		}
		List<Menu>leftMenus = queryFactory.findMenus(null, sprrMenuCode, null);
		List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
		for(Menu menu :leftMenus){
			if(rolemenus!=null){
				for(Rolemenu rolemenu: rolemenus){
					//判断该员工是否有此权限菜单，有则列出
					if(rolemenu.getId().getMenuCode().equals(menu.getMenuCode())){
						Map<String, Object>map = new HashMap<String, Object>();
						map.put("id",menu.getMenuCode());
						map.put("text", menu.getMenuNm());
						map.put("state", menu.getState());
						map.put("url", menu.getUrl());
						map.put("iconCls", menu.getIconcls());
						list.add(map);
					}
				}
			}
			
		}
		return list; 
		
	}

}
