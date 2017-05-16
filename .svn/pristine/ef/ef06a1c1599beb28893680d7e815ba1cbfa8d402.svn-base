<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>表叔云服</title>
</head>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-1.11.1.min.js"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        body,html{
            width: 100%;
            height: 100%;
        }
        .bgpic_box{
            width: 100%;
            height: 85%;
            box-sizing: border-box;
            background-image: url(img/bg.png);
            background-repeat:no-repeat;
            background-position:100%;
        }
        .phone_app{
            width: 670px;
            margin: 0 auto;
            position: relative;
        }
        .phone_pic{
            position: absolute;
            left: 0;
            top: 0;
        }
        .qrcode{
            width: 140px;
            margin-bottom: 10px;
        }
        .qrCode_box{
            text-align: center;
            color: white;
            font-size: 21px;
            position: absolute;
            right: 0;
            top: 320px;
        }
    </style>
</head>
<body>
<div class="bgpic_box">
    <div class="phone_app">
        <img src="img/phone.png" class="phone_pic">
        <div class="qrCode_box">
            <img src="img/QR_user_android.png" class="qrcode">
            <div>扫码下载APP</div>
        </div>
    </div>
</div>

</body>
</html>
<script>
        var pho_h = $('.phone_pic').height()/2;
        console.log(pho_h);
        var body_h = $('body').height()/2;
        console.log(body_h);
        if (body_h >= pho_h){
            $('.bgpic_box').css({ "padding-top": (body_h-pho_h)});
        }
</script>