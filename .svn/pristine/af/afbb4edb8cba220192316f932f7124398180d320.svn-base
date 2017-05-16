<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
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
        .user_top{width: 100%}
        .data_box{position: absolute;width: 100%;top: 4rem;height: 500px;padding: 15px;}
        .data_container{height: 100%;border: 1px solid #eee;padding-top: 1rem}
        .data_head{width: 70%;text-align: center;margin: 0 auto;height: 60px;line-height: 60px}
        .data_toady{color: white;margin: 0 30px}
        .data_arr{color: white;margin-top: 0!important;}
        .sign_btn{width: 120px;height: 30px;border-radius: 15px;background-image: url(../img/user/1111.png);border: none;background-size: 100% 100%;line-height: 30px;color: white}
        .sing_btn_box{position: absolute;bottom: 40px;left: 50%;margin-left: -60px}
        .data_calendar{ height: 180px;padding: 15px; }
        .data_tips{padding: 15px;text-align: center;color: #999999;font-size: 14px;}

        .day_title ul li {display:inline; text-align: center;width: 14%;float: left;}
        .data_calendar ul li {display:inline; text-align: center}
        .circle_solid {
            width: 22px;
            height: 22px;
            background-color: #ff566d;
            -webkit-border-radius: 11px;
            text-align: center;
            color: #fff
        }
        .circle_hollow {
            width: 19px;
            height: 19px;
            background-color: #fff;
            border: 1px #ff566d solid;
            -webkit-border-radius: 12px;
            text-align: center;
            line-height:20px;
            overflow:hidden;
            color: #ff566d
        }
        .data_calendar li div{width: 22px;font-size: 13px;margin: 0 auto}
        .data_calendar li{width: 14% !important;float: left;padding: 5px;  text-align:center}
        .day_title{padding: 0 15px}
        .day_title ul{margin: 0;padding: 0;width: 100%;}
        .data_calendar li div{height:22px;line-height: 22px}
        .data_calendar ul{padding: 0 ; margin: 0  }
        /*弹窗*/
        .my_alert{position: absolute;z-index: 9999;width: 100%;height: 100%;background: rgba(0,0,0,0.5)}
        .alert_box{
            border-radius: 10px;
            width: 214px;
            position: absolute;
            background-color: white;
            top:50%;
            left: 50%;
            -webkit-transform: translate(-50%,-50%);
            /*overflow: hidden;*/
        }
        .alert_head{width: 100%;height: 65px;padding: 0 15px;background-image: url(../img/user/sing.png);background-size: 100% 100%;padding-top: 10px}
        .alert_text{background-color: white;height: 120px;position: relative;border-bottom-right-radius:10px;border-bottom-left-radius: 10px }
        .company_btn_box :after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .sign_alert_head_title,.sign_alert_head_text{padding-left: 40px}
        .sign_alert_head_text{font-size: 13px}
        .sign_alert_head_title{font-size: 16px}
        .close_alert{width: 35px;position: absolute;bottom: -60px;left: 89px}

    </style>
</head>
<body>

<div class="page-group" id="user_home">
    <!-- 你的html代码 -->
    <!--弹窗-->
    <div class="my_alert" v-if="alert" v-cloak>
        <div class="alert_box">
            <div class="alert_head ">
                <p class="sign_alert_head_title" style="color: white;">HELLO ~</p>
                <p class="sign_alert_head_text" style="color: white;margin-top: 0;">今天也要好心情哦 ！ </p>
            </div>
            <div class="alert_text">
                <h3 class="text-center" style="margin: 10px 0">签到成功</h3>
                <p class="text-center" style="color: #ff4c65">获得10个积分</p>
                <p class="text-center" style="color: #959495;font-size: 12px;margin-top: 30px">5s后自动关闭</p>
                <img class="close_alert" src="../img/user/close.png" v-on:click="close"/>
            </div>
        </div>
    </div>
    <div class="page page-current" >
        <div class="content" style="background-color: #ffffff">
            <!-- 这里是页面内容区 -->
            <div class="page-index" style="position: relative">
                <div class="user_top">
                    <img class="back_img_btn"  onclick="javascript:history.back(-1);" src="../img/user/serve_back.png" />
                    <img src="../img/user/top_bg.png" width="100%" />
                </div>
            </div>
            <div class="data_box">
                <div class="data_container">
                    <div class="data_head">
                        <span class="icon icon-left data_arr" v-on:click="preMonth"></span>
                        <span class="data_toady">2017 年 03 月</span>
                        <span class="icon icon-right data_arr" v-on:click="nextMonth"></span>
                    </div>
                    <div class="data_tips">已经连续签到<span v-cloak>{{signDays}}</span>天啦~</div>
                    <div class="day_title">
                        <ul><li>六</li><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li></ul>
                        <div style="clear: both" ></div>
                    </div>
                    <div class="data_calendar"><ul id="data_calendar"></ul></div>
                    <div class="sing_btn_box" v-cloak>
                        <span class="sign_btn button" v-if="isSign">今日已签到</span>
                        <span class="sign_btn button" v-on:click="sign" v-else="isSign">签到</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>var Zepto = jQuery</script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script type='text/javascript' src='../js/plugins/qiandao.js' charset='utf-8'></script>
<script>

    myDate = new Date();
    var currentDay = myDate.getDate();

    var home = new Vue({
        el: '#user_home',
        data: {
            apiGetSignUrl: '/user/getSignMonth',
            apiSignUrl: '/user/sign',
            currentDate: myDate.getFullYear()+'-'+(myDate.getMonth() + 1),
            signDays: 0,
            isSign : false, alert: false
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers();
            signFun(this.currentDate);
//            this.sign(); //自动签到
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiGetSignUrl, {userid: userId, time: this.currentDate}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.signDays = jsonData.signdays;
                            this.isSign = check(jsonData.dayList);
                            signFun(this.currentDate, jsonData.dayList);
                        }
                    })
            },
            preMonth: function () {
                this.currentDate = getPreMonth(this.currentDate);
                this.$http.get(createUrl(this.apiGetSignUrl, {userid: userId, time: this.currentDate}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            signFun(this.currentDate, jsonData.dayList);
                        }
                    });
                signFun(this.currentDate);
            },
            nextMonth: function () {
                this.currentDate = getNextMonth(this.currentDate);
                this.$http.get(createUrl(this.apiGetSignUrl, {userid: userId, time: this.currentDate}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            signFun(this.currentDate, jsonData.dayList);
                        }
                    });
                signFun(this.currentDate);
            },
            sign: function () {
                this.$http.get(createUrl(this.apiSignUrl, {userid: userId}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.signDays = jsonData.days;
                            this.isSign = true;
                            this.alert = true;

                            setTimeout(function() { home.alert = false; }, 5000)
                        } else {
                            // 签到失败,您今天已经签过到了
                            console.log(response.data.m)
                        }
                    })
            },
            close: function () {
                this.alert = false;
            }
        }
    });
    $.init();
</script>
</body>
</html>
