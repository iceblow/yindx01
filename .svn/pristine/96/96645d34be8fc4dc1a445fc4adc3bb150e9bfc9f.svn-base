<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约服务</title>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<style  type="text/css">
.content{
	width:1100px;
	height:auto;
	overflow: hidden;
	margin:0 auto;
	margin-top:30px;
}

img{
    vertical-align: sub;
  }

.uad{
	width:100%;
	position:absolute;
	height:100%;
	top:0px;
}

.uadc{
	width:1110px;
	margin:0 auto;
	margin-top:80px;
	position:relative;
}

.topava{
	width:120px;
	top:70px;
	left:20px;
	position:absolute;
	height:120px;
	border-radius:50%;
}

.topphone{
	position:absolute;
	top:100px;
	left:170px;
	color:#fff;
	font-size:30px;
}

.topaddress{
	font-size:18px;
	position:absolute;
	top:150px;
	left:170px;
	text-indent: 2px;
	color:#fff;
}

.lc{
	float:left;
	height:460px;
	width:260px;
	background-color: #fff;
	border-radius:4px;
}

.orderlabel{
	width:180px;
	height:60px;
	background-color: #6fb5fd;
	color:#fff;
	border-radius:4px;
	margin:0 auto;
	margin-top:40px;
	line-height:60px;
	text-align: center;
	font-size:26px;
}

.accountlabel{
	line-height:30px;
	text-align: center;
	margin-top:30px;
	font-size:20px;
	color:#414141;
}

.accountlabel img,.servicelabel img{
	position:relative;
	top:5px;
}

.servicelabel{
	line-height:30px;
	text-align: center;
	font-size:20px;
	color:#414141;
	margin-top:20px;
}

.rc{
	float:right;
	width:780px;
	height:auto;
	background-color:#fff;
	margin-bottom:100px;
}

.rclb{
	margin-left:20px;
	margin-right:25px;
	display: block;
	box-sizing:border-box;
	line-height:30px;
	font-size:30px;
	height:85px;
	border-bottom:1px solid #c9c9c9;
	color:#303234;
	line-height: 100px;
	text-indent: 10px;
}
.addresslb{
	margin-left:20px;
	margin-right:25px;
	display: block;
	box-sizing:border-box;
	line-height:60px;
	font-size:20px;
	height:60px;
	text-indent: 15px;
	color:#303336;
}

.addresschoose{
	width:560px;
	height:70px;
	margin-left:30px;
	border:1px solid #dee8ef;
	margin-bottom:70px;
	text-align: left;
	text-indent: 20px;
	color:#303336;
	line-height:70px;
	font-size:20px;
	cursor:pointer;
}

.addresschoose .chooseic{
	position:relative;
	top:10px;
	margin-right:20px;
}

.serviceselect{
	width:560px;
	height:45px;
	line-height:45px;
	cursor:pointer;
	margin:10px;
	margin-left:30px;
	font-size:18px;
	color:#63686f;
}

.otlb{
	margin-left:30px;
	height:170px;
	lien-height:100px;
	
}

.otlb div{
	margin-left:20px;
	margin-right:40px;
	float:left;
	text-align: center;
	cursor:pointer;
}

.bk{
	width:540px;
	height:140px;
	border:1px solid #dcdcdc;
	font-size:18px;
	margin-left:35px;
	resize: none;
	padding:10px;
}

.form_datetime{
	cursor:pointer;
	margin-left:35px;
}

.btline{
	border-bottom:1px solid #c9c9c9;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div style="overflow: hidden;height:auto;position:relative;">
<img src="img/top_bg.png" style="margin-top: 80px;">
<div class="uad">
<div class="uadc">
	<c:if test="${empty suser.thirdAvatar }">
		<img src="img/avator.png" class="topava">
		</c:if>
		<c:if test="${not empty suser.thirdAvatar }">
		<img src="${suser.thirdAvatar }" class="topava">
		</c:if>
		<span class="topphone">${suser.phone }</span>
		<span class="topaddress"><font style="color:#3975b7">所在城市</font>&ensp;[杭州]</span>
</div>
</div>
</div>
<div class="content">
	<div class="lc">
		<div class="orderlabel">快速预约</div>
		<div class="accountlabel"><img src="img/setting_ic.png">&emsp;账号设置</div>
		<div class="servicelabel"><img src="img/address_ic.png">&emsp;服务地址</div>
	</div>
	<div class="rc">
		<label class="rclb">快速预约</label>
		<label class="addresslb">服务地址:</label>
		<div class="addresschoose">
			<img src="img/adchoose_ic.png" class="chooseic">
			选择您的服务地址
			
			<img src="img/rarrow.png" style="float:right;position:relative;right:25px;top:25px;">
		</div>
		
		<label class="addresslb">服务内容:</label>
		<select class="serviceselect">
		<option>家电清理</option>
		</select>
		
		<label class="addresslb">预约方式:</label>
		<div class="otlb">
			<div>
				<img src="img/zhiding.png">
				<br>
				指定
			</div>
			<div>
				<img src="img/qiangdao.png">
				<br>
				抢单
			</div>
		</div>
		<label class="addresslb">选择技师:</label>
		<div class="otlb" style="height:100px">
			<div>
				<img src="img/addjishi.png">
			</div>
		</div>
		<label class="addresslb">上门时间:</label>
		<input type="text" class="form_datetime" placeholder="请选择上门时间">
		<label class="addresslb">备注:</label>
		<textarea rows="" cols="" class="bk" placeholder="如有特殊要求请在此输入"></textarea>
		<label class="addresslb">照片上传:</label>
		<div class="otlb btline" style="height:120px;">
			<div>
				<img src="img/imgupload.png">
			</div>
		</div>
	</div>
</div>
</body>
<style  type="text/css">
body{
	background-color:#f3f6fa;
}
</style>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	forceParse: 0,
    showMeridian: 1
});
</script>
</html>