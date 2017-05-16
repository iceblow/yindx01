<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!--星星评价-->
    <link rel="stylesheet" href="../css/bottom.css" />
    <link rel="stylesheet" href="../css/common.css" />
    <!--sui-ui-->
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/??sm.min.css,sm-extend.min.css">
    <style>
        p{
            margin: 0;
        }
        .my_head{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);}
        .user_top{width: 100%}
        .user_head{width: 100%;top: 4rem;background-color: #ffffff ;box-shadow:2px 2px 3px #aaaaaa;border: 1px solid #E3E5E4}
        .text_size{text-align: center}
        .info_number{font-size: 1rem;color: #5d92f9}
        .info_text{color: #5d92f9}
        .info_row{border-right: 1px solid #E3E5E4}
        .user_img{width: 4rem;border-radius: 50%;}
        .img_box{position: absolute;top: -2rem;left: 50%;margin-left: -2rem}
        .user_info_head{height: 5rem}
        .user_name{margin-top: 0.5rem}
        .user_info{padding: 0rem 1rem ;position: absolute;bottom: 0rem;width: 100%}
        .sign_btn{height: 2rem;padding-left: 0;padding-right:0;position: absolute;border: none;bottom: -0.5rem;right: 1rem}
        .chose_text{font-size: 0.6rem;color: #D5D7E2}
        .chose_href{display: block}
        .back_btn{background-image: url("../img/user/back_background.png");border: none;border-radius: 0;margin: 0 auto;height: 40px;line-height: 40px;color: white}
        .about_text{margin-top: 45%}
        .about_footer{font-size: 14px;position: absolute;width: 100%;text-align: center;bottom: 0.5rem}
    </style>
</head>
<body>

<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page page-current" id="user_home">
        <div class="content" style="background-color: #ffffff">
            <!-- 这里是页面内容区 -->
            <div class="page-index" style="position: relative">
                <div class="user_top">
                    <img src="../img/user/top_bg.png" width="100%" />
                </div>
                <div class="user_info">
                    <div class="user_head">
                        <div class="user_info_head" style="position: relative;height: auto">
                            <div class="list-block media-list inset" style="margin: 0">
                                <ul>
                                    <li>
                                        <a href="#" class="item-link item-content">
                                            <div class="item-media"><img src="../img/serve/place_logo.png" width="30"></div>
                                            <div class="item-inner">
                                                <div class="item-title-row">
                                                    <div class="item-title">标题</div>
                                                </div>
                                                <div class="item-subtitle">子标题</div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page" id="user_info">
        <header class="bar bar-nav my_head">
           <!--  <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">个人资料</h1>
        </header>
        <div class="content" style="background-color: white">
            <!-- 这里是页面内容区 -->
            <div class="list-block" style="margin-top: 0">
                <ul>
                    <li>
                        <a href="/demos/list/view-list" class="item-link item-content">
                            <div class="item-inner" style="height: 4rem">
                                <div class="item-title">头像</div>
                                <div class="item-after" style="max-height: 4rem">
                                    <img style="height: 3rem;border-radius: 50%" src="../img/user/default_head_pic.png" />
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="/demos/list/contacts-list" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">积分等级</div>
                                <div class="item-after">
                                    <img style="height: 1rem;" src="../img/user/vip_four.png" />
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="list-block">
                <ul>
                    <li>
                        <a href="#name_edit" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">姓名</div>
                                <div class="item-after">ss</div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#birthday_edit" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">生日</div>
                                <div class="item-after">ss</div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="/demos/list/contacts-list" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">手机号码</div>
                                <div class="item-after">ss</div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#sing_edit" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">个性签名</div>
                                <div class="item-after">ss</div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>-->
<!--<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>-->
<!--轮播图-->
<!--<script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>-->
<!--<script type="text/javascript" src="../js/jquery.flexslider.js"></script>-->
<!--sui-ui-->
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script>
    $(document).on("pageInit", "#user_info", function(e, pageId, $page) {
        console.log(111)
    });
    $.init();
</script>
</body>
</html>