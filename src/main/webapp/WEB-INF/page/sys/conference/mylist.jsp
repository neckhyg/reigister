<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>查询</title>
<link type="text/css" rel="stylesheet" href="../conference/css/bm2016.css"/>
<link type="text/css" rel="stylesheet" media="screen" href="../conference/css/tablecloth.css"/>
<script type="text/javascript" src="../conference/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../conference/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../conference/js/tablecloth.js"></script>

<script type="text/javascript">
$(document).ready(function(){
		$.post("displayUser",
			{selectId:0},
			function(data){
				$("#tab_list").empty();
				var str="";  
				if( data != null){  
				var  str =		"<h3>尊敬的"+data.userName  +"：</h3>"　+　"您的部门信息如下：<br>" +
"<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"white-space:normal;\">" +
"<tr><th>id</th><th>单位</th><th>部门</th><th>姓名</th><th>职务职称</th><th>手机</th><th>email</th><th>性别</th><th>房间</th><th>航班</th><th>抵达日期</th><th>发票单位</th><th>发票项目</th><th>地址</th><th>邮编</th><th>备注</th><th>date1</th></tr>" +
"<tr><td>"+data.userId +"</td><td>"+data.unit+"</td><td>"+data.department+"</td><td>"+data.userName+"</td><td>"+"</td><td>"+"</td><td>"+data.email+"</td><td>"+"</td><td>"+"</td><td>-</td><td>"+"</td><td>"+
"</td><td>"+"</td><td>"+"</td><td>"+"</td><td>"+"</td><td>"+"</td></tr></table>";
 						
					//str+="<h3>尊敬的"+data.userName  +"：</h3>"　+　"您的部门信息如下："+data.department+"<br>"
					//"<option value='"+data[i].id+"'>"+data[i].name+"</option>"  
					//$("#tab_list").html(str);  
				}else{
				str ="没有找到您需要的信息！"
				}
				$("#tab_list").html(str);  
				//$("#energyItemName").val(2);  
		},
		"json");
}); 
//		$(document).ready(function () {  
		
		             
         //	 var  dataStr = "<p><label id = \"disp_total_energy\"><span class=\"STYLE4\">本月总能耗：&nbsp&nbsp</span></label>"+
	       //              "<span class=\"STYLE4\"> <label></label></span><label id = \"disp_compare_energy\" ><span class=\"STYLE4\">&nbsp&nbsp&nbsp&nbsp同比上月：&nbsp&nbsp</span></label>"+
	   		//			 "<span class=\"STYLE3\"><label id = \"disp_percent\" style = \" font-size: 16px\" ><span class=\"STYLE4\">下降 15%</span></label></span></p>"; 
          
// 			var  dataStr =		"	<h3>尊敬的何英高：</h3>　　您的参会信息如下：<br>" +
//"<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"white-space:normal;\">" +
//"<tr><th>id</th><th>单位</th><th>部门</th><th>姓名</th><th>职务职称</th><th>手机</th><th>email</th><th>性别</th><th>房间</th><th>航班</th><th>抵达日期</th><th>发票单位</th><th>发票项目</th><th>地址</th><th>邮编</th><th>备注</th><th>date1</th></tr>" +
//"<tr><td>264</td><td>无锡工艺职业技术学院</td><td>信息中心</td><td>何英高</td><td>高级工程师</td><td>15961500998</td><td>497273569@qq.com</td><td>男</td><td>标准间-合住-1床位</td><td>-</td><td>2016-12-07 00:00</td><td>无锡工艺职业技术学院</td><td>会议费</td><td>江苏省宜兴市宜城街道荆邑南路99号</td><td>214221</td><td>-</td><td>2016-11-24 08:32:56</td></tr></table>";
 						
 //			$("#tab_list").html(dataStr); 
//	alert("mylist")	;		
//		}); 		
</script>
</head>

<body>  
<div id="pm">
<div class="header">
    <div class="wraper">
    	<h1><a href="#">融合.创新.实践</a></h1>
        <ul class="nav">
            <li><a href="#">首页</a></li>
            <li><a href="note" >会议通知</a></li>
            <li><a href="register" >会议报名</a></li>
            <li><a href="schedule">会议日程</a></li>
            <li><a href="document">会议资料</a></li>

        </ul>
    </div>
</div>

<div class="main">
    <div class="wraper">

<div id='tab_list' style='overflow-x:auto;width:100%;'>

</div>

<div class='msg01'>　
　成都欢迎您！期待您的光临！<br><br>　　　　　　　　　　　　　　　
　秘书处、成职院会务组　2016年11月24日 08:32:58<br><br>
</div>
    </div>
</div>

<div class="footer">
    <div class="wraper">
        <p class="fl">Copyright &copy; 2016</p>
        <p class="fr"><a href="http://www.cdp.edu.cn" target="_blank">cdp.edu.cn</a></p>
    </div>
</div>

</div>
<!-- pm end -->

<!-- ver:2016-11-18 -->
</body>
</html>

