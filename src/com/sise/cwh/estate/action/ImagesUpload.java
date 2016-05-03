package com.sise.cwh.estate.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sise.cwh.estate.util.Constants;

@Controller
@RequestMapping("/upload")
public class ImagesUpload{


	@RequestMapping("/ImagesUpload.do")
	public @ResponseBody Map<String, Object>ImagesUploadAjax(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "false");
		if (!file.isEmpty()) {
			   String path = request.getSession().getServletContext().getRealPath("/upload/");  //获取本地存储路径
			   String fileName = file.getOriginalFilename();
			   String fileType = fileName.substring(fileName.lastIndexOf("."));
			   if(Constants.JPG.equals(fileType)||Constants.PNG.equals(fileType)||Constants.GIF.equals(fileType)){
				   long newfileName = new Date().getTime();
				   File file2 = new File(path,newfileName + fileType); //新建一个文件
				   try {
					   file.transferTo(file2);//将上传的文件写入新建的文件中
					    map.clear();
						map.put("success", "true");
						map.put("fileName", newfileName+fileType);
				   } catch (Exception e) {
					   map.clear();
					   map.put("success", "false");
					   e.printStackTrace();
				   }
			   }else{
				   map.clear();
				   map.put("fileName", "false");
			   }
			}
		return map;
	}
	
	
	/*@RequestMapping("/ImagesUploadEdit.do")
	public @ResponseBody Map<String, Object>ImagesUploadEditAjax(String name,@RequestParam("bldImg_edit") CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("success", "false");
		if (!file.isEmpty()) {
			   String path = this.servletContext.getRealPath("/upload/");  //获取本地存储路径
			   String fileName = file.getOriginalFilename();
			   String fileType = fileName.substring(fileName.lastIndexOf("."));
			   if(Constants.JPG.equals(fileType)||Constants.PNG.equals(fileType)||Constants.GIF.equals(fileType)){
				   long newfileName = new Date().getTime();
				   File file2 = new File(path,newfileName + fileType); //新建一个文件
				   try {
					    file.getFileItem().write(file2); //将上传的文件写入新建的文件中
					    map.clear();
						map.put("success", "true");
						map.put("fileName", newfileName+fileType);
				   } catch (Exception e) {
					   map.clear();
					   map.put("success", "false");
					   e.printStackTrace();
				   }
			   }else{
				   map.clear();
				   map.put("fileName", "false");
			   }
			}
		return map;
	}*/
}
