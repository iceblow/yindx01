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
        .user_info{padding: 1rem 1rem ;position: absolute;top: 5rem;width: 100%}
        .sign_btn{height: 22px;padding-left: 0;padding-right:0;position: absolute;border: none;bottom:2px;right: 1rem}
        .sign_btn img{height: 100%}
        .chose_text{font-size: 0.6rem;color: #D5D7E2}
        .chose_href{display: block}
        .back_btn{background-image: url("../img/user/back_background.png");border: none;border-radius: 0;margin: 0 auto;height: 40px;line-height: 40px;color: white}
        .about_text{margin-top: 45%}
        .about_footer{font-size: 14px;position: absolute;width: 100%;text-align: center;bottom: 0.5rem}
        .phone_number{color: #ff4c65}
        .phone_title{padding-top: 30px;text-align: center;width: 100%;}
        .phone_input{padding-top: 30px;position: relative}
        .phone_input a{height: 40px;position: absolute;right: 15%;top: 30px;border-radius: 0;line-height: 40px;border: none;color: #333}
        .btn_disable{background-color: #d5d7e3}
        .btn_send{background-color: #69a8fb;color: white !important}
        .phone_input input{width: 70%;border: 1px solid #d5d7e3;height: 40px;margin-left: 15%;}
        .phone_btn{width: 70%;border:none;border-radius: 0;height: 35px;
            background-image:url(../img/user/next_shape_select.png);background-size: 100% 100%;
            margin-left: 15%;margin-top: 30px;line-height: 35px;color: white;
        }
        input{padding-left: 10px!important;font-size: 16px}
        .money_box{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);border-top: none}
        .money_head:after{background-color: transparent !important}
        .money_number h1{margin: 0;color: white}
        .money_number{color: white;vertical-align: middle;padding-left: 15px;padding-bottom: 25px}
        .add_money{float: right;bottom:25px;width: 45px;height: 30px;border: none;background-color: white;line-height: 30px;right: 15px;color: #ff4c65; }
        .detail_title{line-height: 20px;font-size: 14px;color: #a6a6a6;padding: 15px}
        .detail_text{margin-top: 0}
        .balance_img img{height: 35px}
        .balance_title{font-size: 16px;color: #333}
        .balance_time{font-size: 14px;color: #959495}
        .add_text{font-size: 16px;color: #5e93f9;font-weight: 700}
        .reduce_text{font-size: 16px;color: #333;font-weight: 700}
        .card_tips{font-size: 16px;text-align: center;margin: 15px}
        .card_number{color: #ff4c65;}
        .my_card{height: 105px;background-size: 100% 100%;margin: 0 auto;padding: 5px ;margin-bottom: 15px}
        .card_li .item-inner:after{background-color: white !important;}
        .detail_text ul{background-color: white !important;}
        .my_card .detail_text{margin-bottom: 0}
        .my_card .detail_text {height: 60px !important}
        .card_foot{height: 30px;padding: 0 15px;vertical-align: middle;position: relative;margin-top: 10px;line-height: 30px;color: white;font-size: 14px}
        .detail_text ul:before{background-color: white}
        .detail_text ul:after{background-color: white}
        /*官方优惠券*/
        .cash_car_1{background-image:url("../img/user/cash_card.png")}
        /*官方现金券*/
        .cash_card_2{background-image:url("../img/user/cash_c.png")}
        /*商户优惠券*/
        .company_card{background-image:url("../img/user/company_card.png")}
    </style>
</head>
<body>

<div class="page-group" >
    <div class="page page-current" id="user_home">
        <header class="bar bar-nav my_head money_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">我的优惠券</h1>
        </header>
        <div class="content" style="background-color: #ffffff;padding: 15px">
            <!-- 这里是页面内容区 -->
            <p class="card_tips">您有<span class="card_number" v-cloak>{{count}}</span>张优惠券即将过期</p>
            <div class="my_card cash_car_1" v-for="coupon in couponList" v-cloak>
                <div class="list-block detail_text">
                    <ul>
                        <li class="item-content" >
                            <div class="item-media balance_img">
                                <img src="../img/user/balance_default.png" :src="coupon.logourl"/>
                            </div>
                            <div class="item-inner">
                                <div class="item-title">
                                    <p class="balance_title" v-cloak>{{coupon.title}}</p>
                                    <p class="balance_time" v-cloak>{{coupon.discount}}</p>
                                </div>
                                <div class="item-after">
                                    <span class="add_text">
                                        <input name="radio" type="checkbox">
                                    </span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="card_foot">
                    <span class="card_name pull-left">{{coupon.condition}}</span>
                    <span class="card_time pull-right">{{coupon.timestr}}</span>
                </div>
            </div>
            <!--<div class="my_card cash_card_2">-->
                <!--<div class="list-block detail_text">-->
                    <!--<ul>-->
                        <!--<li class="item-content" v-for="coupon in couponList">-->
                            <!--<div class="item-media balance_img">-->
                                <!--<img src="../img/user/balance_default.png" :src="coupon.logourl"/>-->
                            <!--</div>-->
                            <!--<div class="item-inner">-->
                                <!--<div class="item-title">-->
                                    <!--<p class="balance_title" v-cloak>{{coupon.title}}</p>-->
                                    <!--<p class="balance_time" v-cloak>{{coupon.discount}}</p>-->
                                <!--</div>-->
                                <!--<div class="item-after">-->
                                    <!--<span class="add_text">+ 500.0</span>-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</li>-->
                        <!--<li class="item-content">-->
                            <!--<div class="item-media balance_img">-->
                                <!--<img src="../img/user/balance_default.png" />-->
                            <!--</div>-->
                            <!--<div class="item-inner">-->
                                <!--<div class="item-title">-->
                                    <!--<p class="balance_title">标题</p>-->
                                    <!--<p class="balance_time">标题</p>-->
                                <!--</div>-->
                                <!--<div class="item-after">-->
                                    <!--<span class="add_text">+ 500.0</span>-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</li>-->
                    <!--</ul>-->
                <!--</div>-->
                <!--<div class="card_foot">-->
                    <!--<span class="card_name pull-left">1111</span>-->
                    <!--<span class="card_time pull-right">1111</span>-->
                <!--</div>-->
            <!--</div>-->
            <!--<div class="my_card company_card">-->
                <!--<div class="list-block detail_text">-->
                    <!--<ul>-->
                        <!--<li class="item-content" v-for="coupon in couponList">-->
                            <!--<div class="item-media balance_img">-->
                                <!--<img src="../img/user/balance_default.png" :src="coupon.logourl"/>-->
                            <!--</div>-->
                            <!--<div class="item-inner">-->
                                <!--<div class="item-title">-->
                                    <!--<p class="balance_title" v-cloak>{{coupon.title}}</p>-->
                                    <!--<p class="balance_time" v-cloak>{{coupon.discount}}</p>-->
                                <!--</div>-->
                                <!--<div class="item-after">-->
                                    <!--<span class="add_text">+ 500.0</span>-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</li>-->
                        <!--<li class="item-content">-->
                            <!--<div class="item-media balance_img">-->
                                <!--<img src="../img/user/balance_default.png" />-->
                            <!--</div>-->
                            <!--<div class="item-inner">-->
                                <!--<div class="item-title">-->
                                    <!--<p class="balance_title">标题</p>-->
                                    <!--<p class="balance_time">标题</p>-->
                                <!--</div>-->
                                <!--<div class="item-after">-->
                                    <!--<span class="add_text">+ 500.0</span>-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</li>-->
                    <!--</ul>-->
                <!--</div>-->
                <!--<div class="card_foot">-->
                    <!--<span class="card_name pull-left">1111</span>-->
                    <!--<span class="card_time pull-right">1111</span>-->
                <!--</div>-->
            <!--</div>-->
        </div>

    </div>
</div>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>var Zepto = jQuery</script>
<!--sui-ui-->
<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
    $.init();
    new Vue({
        el: '#user_home',
        data: {
            apiUrl: '/user/getMyCoupon',
            couponList: [],
            count: 0
        },
        mounted: function() {
            this.getCustomers();
        },
        methods: {
            getCustomers: function () {
                this.$http.get(createUrl(this.apiUrl, {userid: userId}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.count = jsonData.count;
                            this.couponList = jsonData.couponList;

                            console.log(jsonData)
                        } else {
                            console.log(response.data.m)
                        }
                    })
            },
        }
    });
</script>
</body>
</html>
