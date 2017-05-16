<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh">

	<head>
		<meta charset="utf-8" />
		<title>表叔云服</title>
		<meta name="keywords" content="Bootstrapæ¨¡ç,Bootstrapæ¨¡çä¸è½½,Bootstrapæç¨,Bootstrapä¸­æ" />
		<meta name="description" content="ç«é¿ç´ ææä¾Bootstrapæ¨¡ç,Bootstrapæç¨,Bootstrapä¸­æç¿»è¯ç­ç¸å³Bootstrapæä»¶ä¸è½½" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

<!-- 		<link href="assets/css/bootstrap.min.css" rel="stylesheet" /> -->
		<link rel="stylesheet" href="./css/regist.css">
<!-- 		<link rel="stylesheet" href="assets/css/font-awesome.min.css" /> -->
		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="js/jquery.cookie.js"></script>
		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->


		<!-- ace styles -->

<!-- 		<link rel="stylesheet" href="assets/css/ace.min.css" /> -->
<!-- 		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" /> -->
		<link rel="stylesheet" href="js/common.js" />
		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>


		<!-- basic scripts -->


		<!--[if !IE]> -->
<body>
<div class="header_reg_log">
    <img src="./img/reg1.png" class="head_pic">
    <div class="head_name">表叔云服企业管理后台</div>
</div>
<form action="./toLogin" id="loginForm" method="post">
<div class="section_reg_log">
    <div class="log_phone">
        <input type="text" class="pho_input" placeholder="请输入手机号" id="username" value="${username}" name="username">
        <img src="./img/reg2_0.png" class="pho_pic">
    </div>
    <div class="log_pwd">
        <input type="text" class="pwd_input" placeholder="请输入密码"  id="password" name="password">
        <img src="./img/reg3.png" class="pwd_pic">
    </div>
    <div class="log_vcode">
        <input type="text" class="vcode_input" placeholder="验证码" name="logincode" >
        <img style="cursor:pointer;" class="log_vcode_pic" src="createImg" onclick="this.setAttribute('src','createImg?x='+Math.random());" alt="验证码" title="点击更换">
    </div>
    <div class="company_type">
    <div style="line-height: 50px;color: red;height: 50px;">${message }</div>
        <div class="log_login reglog_btn" onclick="login()">登录</div>
        <div class="log_reg reglog_btn" onclick="regist()">注册</div>
    </div>

</div>
</form>

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
		</script>
<!-- <script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + " type='text/javascript'%3E%3C/script%3E"));
</script> -->

<script type="text/javascript">
$(function(){
	var cookieusername = $.cookie('lvguoguoUsername') ? $.cookie('lvguoguoUsername') : "";
	var cookiepassword = $.cookie('lvguoguoPassword') ? $.cookie('lvguoguoPassword') : "";
	var rememberInfo = $.cookie('lvguoguoInfo') ? $.cookie('lvguoguoInfo') : "";
	if (cookieusername && cookiepassword && rememberInfo) {
		$("#username").val(cookieusername);
		$("#password").val(cookiepassword);
		$("#reflag").val(1);
		$("#ace").attr("checked","checked");
	}

	var login_account = $("input[name=account]").val();
	var login_password = $("input[name=password]").val();
	if(login_account != "" && login_password  != "" ){
		$("#logintable_submit").css("background","rgb(1,138,254)");
		$("#logintable_submit").attr("onclick","login()");
	}else{
		$("#logintable_submit").css("background","#c9d2da");
		$("#logintable_submit").removeAttr("onclick");
	}
	
});

function remember(){
	var reflag = $("#reflag").val();
	var username = $('#username').val();
	var password = $('#password').val();
	if(reflag == 0){//设置为记住我
		$("#reflag").val(1);
		$.cookie('lvguoguoInfo', '1', {
			expires : 30,
			path : '/',
		});
		$.cookie('lvguoguoUsername', username, {
			expires : 30,
			path : '/',
		});
		$.cookie('lvguoguoPassword', password, {
			expires : 30,
			path : '/',
		});
	}else{//设置为不要记住
		$("#reflag").val(0);
		$.cookie('lvguoguoUsername', '', {
			expires : 30,//有效时间，单位：天
			path : '/',
		//	domain : 'www.inredata.com'
		});
		$.cookie('lvguoguoPassword', '', {
			expires : 30,
			path : '/',
	//		domain : 'www.inredata.com'
		});
		$.cookie('lvguoguoInfo', '', {
			expires : 30,
			path : '/',
	//		domain : 'www.inredata.com'
		});
	}
}

	function login(){
		$('#loginForm').submit();
	}
	function regist(){
		window.location.href="./toRegist";
	}
</script>

</body>
</html>
