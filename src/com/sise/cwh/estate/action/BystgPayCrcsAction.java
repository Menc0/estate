package com.sise.cwh.estate.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sise.cwh.estate.entity.BystgPayCrcs;
import com.sise.cwh.estate.entity.PerPrdPayed;
import com.sise.cwh.estate.service.BystgPayCrcsService;
import com.sise.cwh.estate.util.Pagination;
import com.sise.cwh.estate.util.QueryFactory;
import com.sise.cwh.estate.util.PopupAuthenticator;

@Controller
@RequestMapping("/payCrcs")
public class BystgPayCrcsAction {
	@Resource(name="queryFactory")
	private QueryFactory queryFactory;
	@Resource(name="bystgPayCrcsService")
	private BystgPayCrcsService bystgPayCrcsService;
	
	@RequestMapping("/queryPayCrcs")
	public @ResponseBody Map<String, Object>queryPayCrcs(HttpServletRequest request){
		String cusIcd = request.getParameter("cusIcd");
		String hosNo = request.getParameter("hosNo");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String sec = request.getParameter("order");
		String sort = request.getParameter("sort");
		Pagination pg = new Pagination(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sec, sort);
		List<Map<String, Object>>list = bystgPayCrcsService.queryPayCrcs(cusIcd, hosNo, pg);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", list.get(list.size()-1).get("total"));
		list.remove(list.size()-1);//删除掉带过来的total数据总量
		map.put("rows",list);
		
		return map;
	}
	
	@RequestMapping("/paying")
	public @ResponseBody Map<String, Object>paying(HttpServletRequest request){
		String selNo = request.getParameter("selNo");
		PerPrdPayed perPrdPayed = new PerPrdPayed();
		//perPrdPayed.setPayDt(new Timestamp(System.currentTimeMillis()));
		perPrdPayed.setSelNo(Integer.valueOf(selNo));
		BystgPayCrcs bystgPayCrcs = queryFactory.findByKey(BystgPayCrcs.class, Integer.valueOf(selNo));
		if(bystgPayCrcs!=null){
			bystgPayCrcs.setPayedSum(bystgPayCrcs.getPayedSum()+1);//分期付款情况中的已付款期数加1
			bystgPayCrcs.setUnpaySum(bystgPayCrcs.getUnpaySum()-1);//所缺期数减1
		}
		HashMap<String, Object>map = new HashMap<String, Object>();
		try {
			map.put("success", "true");
			bystgPayCrcsService.savePaying(perPrdPayed, bystgPayCrcs);
		} catch (Exception e) {
			map.put("success", "false");
		}
		return map;
		
	}
	
	/**
	 * 发送邮件
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendEmail")
	public @ResponseBody Map<String, Object>sendEmail(HttpServletRequest request){
		String title = request.getParameter("emailTitle");
		String content = request.getParameter("emailContent");
		String toEmailAddress = request.getParameter("toEmailAddress");
		HashMap<String, Object>map = new HashMap<String, Object>();
		
		try{
			map.put("success", "true");
			String userName="cwh1010@yeah.net";
			String password="cwh2766119059";
			String smtp_server="smtp.yeah.net";//smtpcom.263xmail.com
			String from_mail_address=userName;
			String to_mail_address=toEmailAddress;
			Authenticator auth=new PopupAuthenticator(userName,password);
			Properties mailProps=new Properties();
			mailProps.put("mail.smtp.host", smtp_server);
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("username", userName);
			mailProps.put("password", password);

			Session mailSession=Session.getDefaultInstance(mailProps, auth);
			mailSession.setDebug(true);
			MimeMessage message=new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from_mail_address));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));
			message.setSubject(title);

			MimeMultipart multi=new MimeMultipart();
			BodyPart textBodyPart=new MimeBodyPart();
			textBodyPart.setText(content);
			//textBodyPart.setFileName("37af4739a11fc9d6b311c712.jpg");
			multi.addBodyPart(textBodyPart);
			message.setContent(multi);
			message.saveChanges();
			Transport.send(message);
			}catch(Exception ex){
				if(StringUtils.isEmpty(title)){
					map.put("success", "请输入标题");
				}else if(StringUtils.isEmpty(content)){
					map.put("success", "请输入内容");
				}else if(StringUtils.isEmpty(toEmailAddress)){
					map.put("success", "请输入发送邮箱");
				}else{
					map.put("success", "false");
				}
			
			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);
			}
		
		return map;
		
	}

}
