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
<title>2016成都年会</title>
<link type="text/css" rel="stylesheet" href="../conference/css/bm2016.css"/>
<link type="text/css" rel="stylesheet" media="all" href="../conference/css/style.css" />
<link type="text/css" rel="stylesheet" href="../conference/css/datePicker.css"  />
<link type="text/css" rel="stylesheet" media="all" href="../conference/css/jquery-ui.min.css" />
<link type="text/css" rel="stylesheet" media="all" href="../conference/css/jquery-ui-timepicker-addon.css" />
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

        </ul>
    </div>
</div>
<div class="main">
    <div class="wraper">
    	<h3 class="green">关于召开2016年会日程的通知</h3>
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

<script type="text/javascript" src="../conference/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../conference/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="../conference/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../conference/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="../conference/js/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="../conference/js/bm2016.js"></script>

<!-- ver:.1121 -->
</body>
</html>