<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style  type="text/css">
		 html,body{height:100%}
			    html{overflow-y:auto}
			    body{ font:18px arial; text-align:left;background:#fff;;line-height: 1.5;min-width: 1000px;color: #817872;}
			    body,p,form,ul,li{margin:0;padding:0;list-style:none}
 		 a{text-decoration: none;color: #817872;}
	    a:visited{ text-decoration: none;}
	    #header{height:80px;width:100%;    background: hsla(0,0%,100%,.8);line-height: 80px;position: fixed;min-width: 1000px;z-index: 997}
	    .head-left{width:40%;float:left;text-align:center;padding: 10px 0;}
	    .head-left img{height:60px;}
	    .head-center{width:40%;height:100%;float:left;font-size:16px;}
	    .head-right{width:20%;float:left;font-size:12px;}
	    .head-center a{padding:0 15px;}
	    .head-right a{padding:0 10px;}
	    .avator{
		    width:30px;
			position:relative;
			top:10px;
		}
</style>
</head>
<body>
<div id="header" >
				<div >
					<div class="head-left">
						<img src="img/bsh_logo.png" />
						<div style="display: inline-block;line-height: 30px;margin-left: 15px;">
						     <div style="font-size: 32px;color:#659efb;">表叔云服</div>
						     <div style="font-size: 12px;background: #6eafff;line-height: 14px;margin-top: 7px;border-radius: 5px;color: white;">www.uncleserv.com</div>
						</div>
					</div>
					<div class="head-center">
						<a href="homepage.jsp" class="homepage">首页</a>
						<a href="serverIntroduce.jsp" class="serverIntroduce" >服务介绍</a>
						<a href="serverTeam.jsp">服务团队</a>
						<a href="AboutUs.jsp" class="AboutUs">关于我们</a>
					</div> 
					<div class="head-right">
						<c:if test="${empty suser }">
							<a href="#" onclick="showlogin()">登录</a>|
							<a href="#" onclick="showregist()">注册</a>
						</c:if>
						<c:if test="${not empty suser }">
							<c:if test="${empty suser.thirdAvatar }">
							<img src="img/avator.png" class="avator">
							</c:if>
							<c:if test="${not empty suser.thirdAvatar }">
							<img src="${suser.thirdAvatar }" class="avator">
							</c:if>
							<font class="exit">&nbsp;退出</font>
						</c:if>
					</div>
							
				</div>
				<div style="clear:both"></div>
			</div>
			<jsp:include page="login.jsp"></jsp:include>
</body>
</html>