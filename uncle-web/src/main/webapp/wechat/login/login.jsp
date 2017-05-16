<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="YES">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control"
          content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/register.css" />
    <script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
    <title>登录</title>
</head>
<body>
     <div class="login">
         <div class="top_pic">
             <img src="../img/regist/cha.png" class="cha">
         </div>
         <p class="top_login">账号登录</p>
     </div>
     <div class="shuru shu_pho">
         <img class="input_phone phone1" src="../img/regist/phone.png">
         <img class="input_phone phone2" src="../img/regist/phone2.png">
         <input id="phone" class="ipt" placeholder="输入手机号" oninput="changcolor()">
     </div>
     <div class="shuru input1">
         <img class="input_key key1" src="../img/regist/key.png">
         <img class="input_key key2" src="../img/regist/key2.png">
         <input class="ipt" type="password" placeholder="输入密码" id="password" oninput="changcolor()">
         <input class="ipt" type="text" placeholder="输入密码" id="passwords" style="display: none;" oninput="changcolor()">
         <img class="eye e_close" src="../img/regist/eye_close.png" onclick="toPic1()">
         <img class="eye e_open" src="../img/regist/eye_open.png" onclick="toPic1()" style="display: none;">
     </div>
　　 <div id="login" class="butn">登录</div>
     <div class="bottom">
         <p class="bot forget_pwd">忘记密码 ？</p>
         <p class="bot mid">|</p>
         <p class="bot reg_now">现在注册 》</p>
     </div>


</body>
</html>
<script>
    $(function(){
        changcolor();
    })

    function changcolor() {
        var phoneNum = $('#phone').val();
        var pwd = $('#password').val();
        var pwds = $('#passwords').val();
        if(phoneNum != ""){
            $('.phone1').hide();
            $(".phone2").show();
            $(".shu_pho").addClass("shuru2");
        }else{
            $('.phone2').hide();
            $(".phone1").show();
            $(".shu_pho").removeClass("shuru2");
        }

        if(pwd != "" || pwds != ""){
            $('.input1 .key1').hide();
            $(".input1 .key2").show();
            $(".input1").addClass("shuru2");
        }else{
            $('.input1 .key2').hide();
            $(".input1 .key1").show();
            $(".input1").removeClass("shuru2");
        }
        if((pwd != ""||pwds != "") && phoneNum != ""){
            $("#login").css("backgroundImage","linear-gradient(to bottom, #78c6ff, #5d8ff9)");
        }else{
            $("#login").css("background","#adadad");
        }
    }

    /*  密码的显示与隐藏  */
    var index = true;
    function toPic1(){
        if(index){
            /* 显示密码 */
            $('.input1 .e_open').hide();
            $('.input1 .e_open').show();
            $('#password').hide();
            $('#passwords').show();
            $('#passwords').val($('#password').val());
            index = false;
        }else{
            /* 隐藏密码 */
            $('.input1 .e_open').hide();
            $('.input1 .e_close').show();
            $('#password').show();
            $('#passwords').hide();
            $('#password').val($('#passwords').val());
            index = true;
        }
    }
</script>
