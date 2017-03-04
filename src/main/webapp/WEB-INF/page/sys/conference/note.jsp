<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>2016年会</title>
<link type="text/css" rel="stylesheet" href="../conference/css/bm2016.css" />
<style>
.pdfobject-container {
	max-width: 100%;
	width:720px;
	height:600px;
}
.pdfobject { border: 1px solid #666; }
</style>

</head>
<body>  
<div id="pm">
<div class="header">
    <div class="wraper">
    	<h1><a href="#">融合.创新.实践</a></h1>
        <ul class="nav">
           
            <li><a href="note" >会议通知</a></li>
            <li><a href="register" >会议报名</a></li>
            <li><a href="schedule">会议日程</a></li>
            <li><a href="document">会议资料</a></li>
             <li><a href="mylist">我的信息</a></li>

        </ul>
    </div>
</div>
<div class="main">
    <div class="wraper">
    	<h3 class="green">关于召开2016年会的通知</h3>
		<div id="my-pdf">
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
<script src="../conference/js/pdfobject.min.js"></script>
<script>PDFObject.embed("./pdf/2016nianhui.pdf", "#my-pdf");</script>

<!-- ver:2016-11-08 14:26:16 -->
</body>
</html>