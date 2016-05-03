<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'statcAnaly.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
<script type="text/javascript">
   $(document).ready(function() {  

           $.ajax({  
               url: "statcAnaly/sellSumMonth.do",  
               type: "GET",  
               success: function(data){  
                  var chart = new Highcharts.Chart({  
                      chart: {  
                          renderTo: 'sellSumMonth'  
                      },  
                     xAxis: {
	                   categories: ['一月', '二月', '三月', '四月', '五月', '六月',
	                    '七月', '八月', '九月', '十月', '十一月', '十二月']
			            },
		            yAxis: {
		                title: {
		                    text: '销售量'
		                },
		                plotLines: [{
		                    value: 0,
		                    width: 1,
		                    color: '#808080'
		                }]
		            },
		           title : {  
                      text : "每月销售量"  
                      },  

                     tooltip: {
              				  valueSuffix: '户'
           				 },

                      plotOptions: {  
                      },  
                      series: [{ 
                      	name: '销售量', 
                          data: data.total
                      }]  
                  });  
               }  
           });  
           
           //每月销售最受欢迎楼房
            $.ajax({  
               url: "statcAnaly/queryBigSellBldPreThrMonth.do",  
               type: "GET",  
               success: function(data){  
                  var chart = new Highcharts.Chart({  
	                  
	                  chart: {  
                          renderTo: 'bigSellBldMonth' ,
                          type: 'column' 
                      },  
                     xAxis: {
	                   categories: ['近三个月']
			            },
		            yAxis: {
		                title: {
		                    text: '销售量'
		                },
		                plotLines: [{
		                    value: 0,
		                    width: 1,
		                    color: '#808080'
		                }]
		            },
		           title : {  
                      text : "最近三月热销前五楼房"  
                      },  

                     tooltip: {
              				  valueSuffix: '户'
           				 },

                      plotOptions: {  
                      },  
                      series: [{ 
                      	name: data.bldNo[0], 
                          data: [data.total[0]]
                      },
                      { 
                      	name: data.bldNo[1], 
                          data: [data.total[1]]
                      },
                      { 
                      	name: data.bldNo[2], 
                          data: [data.total[2]]
                      },
                      { 
                      	name: data.bldNo[3], 
                          data: [data.total[3]]
                      },
                      { 
                      	name: data.bldNo[4], 
                          data: [data.total[4]]
                      }
                      
                      ]  
                  });  
               }  
           });  
           
       });  

</script>  
    <div id="sellSumMonth" style="width: 500px; height: 300px; margin: 0 auto;float:left"></div> 
    <div id="bigSellBldMonth" style="width: 500px; height: 300px; margin: 0 auto;float:left"></div> 
  	<div style="margin-left:20px;float:left">
  		<table class="easyui-datagrid" style="width:500px;"   
        data-options="url:'statcAnaly/querySellSrcTop10.do',striped:true,title:'近一月销售前十员工'">   
  		   
		    <thead>   
		        <tr>   
		            <th data-options="field:'WRKR_NO',width:170">工号</th>   
		            <th data-options="field:'WRKR_NM',width:160">名字</th>   
		            <th data-options="field:'score',width:160">业绩</th>   
		        </tr>   
		    </thead>   
		</table>  


  	
  	</div>
  
  </body>
</html>
