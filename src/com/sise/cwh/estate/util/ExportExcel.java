package com.sise.cwh.estate.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

	/**
	*  Excel关系：
	*  Excel文件下包含工作表(Sheet1...) ， 工作表下包含了行Row，Row下包含了Cell单元格
	*  Excel文件：			HSSFWorkbook
	*  Excel工作薄：		HSSFSheet
	*  Excel的行：			HSSFRow
	*  Excel行中的单元格：	HSSFCell
	*/
	// 定义HSSFWorkbook对象，代表excel工作表
	@SuppressWarnings("unused")
	private HSSFWorkbook workbook = null;

	@SuppressWarnings({ "rawtypes", "deprecation", "unused" })
	public static void doExportExcel(String fileName,String[] titles,List<Map<String, Object>> list) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFCell cell = row.createCell((short) 0); 
        for (int i = 0; i < titles.length; i++) {
        	 cell = row.createCell((short) i);  
             cell.setCellValue(titles[i]);  
             cell.setCellStyle(style);  
        }
  
        // 第五步，写入实体数据，  
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1); 
            List<String> valList=new ArrayList<String>();
            Iterator iterator = list.get(i).entrySet().iterator();
    		while (iterator.hasNext()) {
    			Entry entry = (Entry) iterator.next();
    			Object key = entry.getKey();
    			Object val = entry.getValue();
    			valList.add(val.toString());
    		}
            for(int j = 0; j<list.get(i).size(); j++){
            	
            // 第四步，创建单元格，并设置值  
            row.createCell((short) j).setCellValue(valList.get(j));  
            }
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
        	
            FileOutputStream fout = new FileOutputStream("E:/"+fileName+".xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
	
}
