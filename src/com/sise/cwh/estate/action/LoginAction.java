package com.sise.cwh.estate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.Role;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.util.QueryFactory;

@Controller
@RequestMapping("/login")
public class LoginAction {
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	
	@RequestMapping("/toLogin")
	 public String login(HttpServletRequest request,HttpServletResponse response){
		if(request.getSession().getAttribute("wrkrNm")!=null){
			//request.getRequestDispatcher("/login/toLogin.do").forward(request, response); 
			return "manager/home";
		}
	 	 return "manager/login";
	  }
	
	@RequestMapping("/toLogout")
	 public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("wrkrNm");
	 	 return "manager/login";
	  }
	
	@RequestMapping("/toHome")
	 public String toHome(){
	 	 return "manager/home";
	  }
	
	@RequestMapping(value="/loginAjax",method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> loginAjax(String wrkrNo,String psw,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		Wrkrinfo wrkrinfos = queryFactory.findByKey(Wrkrinfo.class, wrkrNo);
		List<Map<String, Object>>wrList = new ArrayList<Map<String,Object>>();
		Map<String, Object>map = new HashMap<String, Object>();
		Map<String, Object>map2 = new HashMap<String, Object>();
		if(wrkrinfos!=null){
			map.put("isWrkNo", "true");
			if(wrkrinfos.getLoginPsw().equals(psw)){
				request.getSession().setAttribute("wrkrNm", wrkrinfos.getWrkrNm());
				map.put("isPsw", "true");
				map2.put("wrkrNo", wrkrinfos.getWrkrNo());
				map2.put("wrkrNm", wrkrinfos.getWrkrNm());
				map2.put("roleId", wrkrinfos.getRoleId());
				map.put("roleId", wrkrinfos.getRoleId());
				Role role = queryFactory.findByKey(Role.class, wrkrinfos.getRoleId());
				if(role!=null){
				map2.put("roleNm", role.getRoleNm());
				}
				
				request.getSession().setAttribute("wrkrInfo", map2);
			}
			else{
				map.put("isPsw", "false");
			}
		}else{
			map.put("isWrkNo", "false");
		}
		wrList.add(map);
		return wrList;
	}

}
