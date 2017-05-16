<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jQuery.md5.js"></script>
<style  type="text/css">
.mask{
	position: fixed;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 0px;
	opacity: 0.5;
	background: #10090d;
	z-index:998;
	display:none;
}

.login,.regist{
	width:550px;
	height:595px;
	position:fixed;
	left:50%;
	margin-left:-275px;
	margin-top:-297.5px;
	top:50%;
	background-image: url('img/loginbg.jpg');
	background-size: 100%;
	z-index:999;
	box-sizing: border-box;
	display:none;
}

.login input,.regist input{
	margin-left:50px;
	margin-right:75px;
	width:425px;
	border:none;
	-webkit-appearance:none;
	border-bottom:1px solid #e2e2e2;
	font-size:15px;	
	left: 0px;
}

.loginname{
	position:absolute;
	top:200px;
	box-sizing: border-box;
	line-height:35px;
	text-indent: 39px;
}

.loginimg{
	position:absolute;
	top:205px;
	left:60px;
	z-index:99;
}

.login .pwdimg{
	position:absolute;
	top:285px;
	left:60px;
	z-index:99;
}

.login .loginpassword{
	position:absolute;
	top:280px;
	box-sizing: border-box;
	line-height:35px;
	text-indent: 39px;
}

.regist .vcodeimg{
	position:absolute;
	top:255px;
	left:60px;
	z-index:99;
}

.regist .gvcdoe{
	position:absolute;
	top:260px;
	right:85px;
	z-index:99;
	text-align: right;
	cursor:pointer;
	color:#5aa4fd;
}

.regist .gvcdoe.disable{
	color:#c5c2c5;
}

.regist .pdimg{
	position:absolute;
	top:305px;
	left:60px;
	z-index:99;
}


.regist .pd{
	position:absolute;
	top:300px;
	box-sizing: border-box;
	line-height:35px;
	text-indent: 39px;
}

.regist .vcode{
	position:absolute;
	top:250px;
	box-sizing: border-box;
	line-height:35px;
	text-indent: 39px;
}

.login input:focus{
	outline: none;
}

.login .sure,.regist .registbtn{
	margin-left:50px;
	margin-right:75px;
	text-align: center;
	background-color:#77c4ff;
	position:absolute;
	bottom:145px;
	width:425px;
	box-sizing: border-box;
	line-height:35px;
	color:#fff;
	cursor:pointer;
}

.login .toregist,.regist .tologin{
	margin-left:50px;
	margin-right:75px;
	text-align: center;
	box-sizing: border-box;
	background-color:#c7d1d9;
	position:absolute;
	bottom:85px;
	width:425px;
	box-sizing: border-box;
	line-height:35px;
	color:#6f6f6f;
	cursor:pointer;
}

.rephonesp,.lophonesp{
	position:absolute;
	top:210px;
	right:80px;
	color:red;
}

</style>
<body>
<div class="mask"></div>
<div class="login">
<img src="img/phone-icon.png" class="loginimg">
<input type="text" class="loginname" id="loginphone" placeholder="输入手机号"> <span class="lophonesp"></span>
<img src="img/pwd-icon.png" class="pwdimg">
<input type="text" class="loginpassword" placeholder="输入密码">

<div class="sure">登录</div>

<div class="toregist">注册</div>
</div>

<div class="regist">
<img src="img/phone-icon.png" class="loginimg">
<input type="text" class="loginname" placeholder="输入手机号" id="registphone"> <span class="rephonesp"></span>
<img src="img/pwd-icon.png" class="vcodeimg">
<input type="text" class="vcode" placeholder="输入验证码">  <span class="vcodesp"></span>

<img src="img/pd_icon.png" class="pdimg">
<input type="text" class="pd" placeholder="输入密码">  <span class="repdsp"></span>

<span class="gvcdoe" onclick="sendVcode()">获取验证码</span>

<div class="registbtn">注册</div>

<div class="tologin">登录</div>
</div>
</body>
<script>
function showlogin(){
	$('.mask').show();
	$(".login").show();
	$(".regist").hide();
}

function changelogin(){
	$(".login").show();
	$(".regist").hide();
}

function showregist(){
	$('.mask').show();
	$(".regist").show();
	$(".login").hide();
}

function changeregist(){
	$(".login").hide();
	$(".regist").show();
}

$(".tologin").on("click",function(){
	changelogin();
})

$(".toregist").on("click",function(){
	changeregist();
})

$(".mask").on("click",function(){
	$(this).hide();
	$(".login").hide();
	$(".regist").hide();
})

// $(".gvcdoe").on("click",sendVcode());


$(".registbtn").on("click",function(){
	var phone = $("#registphone").val();
	if(!phone){
		$(".rephonesp").text("输入手机号!");
		return;
	}
	var vcode = $(".vcode").val();
	if(!vcode){
		$(".rephonesp").text("输入验证码!");
		return;
	}
	
	var pd = $(".pd").val();
	if(!pd){
		$(".rephonesp").text("输入密码!");
		return;
	}
	pd = $.md5(pd);
	pd = $.md5(pd);
	pd = $.md5(pd);
	$.ajax({
		url:'./website/login/regist',
		data:{phone:phone,vcode:vcode,password:pd},
		dataType:'json',
		success:function(data){
			var c = data.c;
			if(c!=1){
				$(".rephonesp").text(data.m);
			}else{
				$(".rephonesp").text("注册成功,自动登录中.");
				login(phone,pd)
			}
		}
	})
})

$(".sure").on("click",function(){
	var loginphone = $("#loginphone").val();
	if(!loginphone){
		$(".lophonesp").text("输入手机号!");
		return;
	}
	var loginpassword = $(".loginpassword").val();
	if(!loginpassword){
		$(".lophonesp").text("输入验证码!");
		return;
	}
	loginpassword = $.md5(loginpassword);
	loginpassword = $.md5(loginpassword);
	loginpassword = $.md5(loginpassword);
	login(loginphone,loginpassword);
})


function login(phone,password){
	$.ajax({
		url:'./website/login/login',
		data:{phone:phone,password:password},
		dataType:'json',
		success:function(data){
			var c = data.c;
			if(c!=1){
				$(".lophonesp").text(data.m);
			}else{
				window.location.reload();
			}
		}
	})
}

function sendVcode(){
	var phone = $("#registphone").val();
	if(!phone){
		$(".rephonesp").text("输入手机号!");
		return;
	}else{
		$(".rephonesp").text("");
	}
	$.ajax({
		url:'./website/login/getVcode',
		data:{phone:phone},
		dataType:"json",
		success:function(data){
				console.log(data);
				var c = data.c;
				if(c!=1){
					$(".rephonesp").text(data.m);
				}else{
					vcodeInterval();
				}
		}
	})
}

function vcodeInterval(){
	var time = 60;
	$('.gvcdoe').addClass("disable");
	$('.gvcdoe').text(time+"s后再次获取");
	$(".gvcdoe").removeAttr("onclick");
	var timeInterval = setInterval(function(){
		if(time>0){
			time--;
			$('.gvcdoe').text(time+"s后再次获取");
		}else{
			$('.gvcdoe').removeClass("disable");
			$('.gvcdoe').text("获取验证码");
			$(".gvcdoe").attr("onclick","sendVcode()");
			clearInterval(timeInterval);
		}
	},1000);
}

$(document).on("click",".exit",function(){
	$.ajax({
		url:'./website/login/logout',
		dataType:"json",
		success:function(data){
				console.log(data);
				var c = data.c;
				if(c==1){
					window.location.reload();
				}
		}
	})
})

</script>
</html>
