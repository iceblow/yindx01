<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./css/regist.css">
    <title>注册-表叔云服企业管理后台</title>
   	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<link rel="stylesheet" href="js/common.js" />
	<script type="text/javascript" src="js/regist/regist.js"></script>
</head>
<body>
<div class="header_reg_log">
    <img src="./img/reg1.png" class="head_pic">
    <div class="head_name">表叔云服企业管理后台</div>
</div>
<div class="section_reg_log">
    <div class="phone">
        <input type="text" class="pho_input" id="phoneVal" placeholder="请输入手机号">
        <img src="./img/reg2.png" class="pho_pic">
    </div>
    <div class="vcode">
        <input type="text" class="vcode_input" placeholder="验证码" id="vcode_input">
        <div class="vcode_text" id="sendVCode" >发送验证码</div>
         <div class="vcode_text" id="checkVCode" style="display:none;">已发送<span id="jishi">(60秒)</span></div>
    </div>
    <div class="pwd">
        <input type="text" class="pwd_input" id="password" placeholder="请输入密码">
        <img src="./img/reg3.png" class="pwd_pic">
    </div>
    <div class="company_type">
        <div class="com_title">公司类型</div>
        <div class="com_sort" data-type="0">
            <img src="./img/reg4.png">
            维修
        </div>
        <div class="com_sort" data-type="1">
            <img src="./img/reg5.png">
            家政
        </div>
        <div class="com_sort" data-type="2">
            <img src="./img/reg5.png">
            中介
        </div>
        <div class="com_box">
            <input type="text" class="com_input"  id="com_name" placeholder="请输入公司名称">
        </div>
        <div class="com_box">
            <input type="text" class="com_input" id="com_ads" placeholder="请输入公司地址">
        </div>
        <div class="reg reglog_btn" onclick="doRegist()">注册</div>
        <div class="login reglog_btn">登录</div>

    </div>

</div>


</body>

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
</html>