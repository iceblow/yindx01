<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>退单</title>
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
        .pay_title{color: #959495;font-size: 14px;margin-top: 30px;text-align: center}
        .pay_money{font-size: 1.5rem;color: #ff5266}
        .detail_title{float: left;font-size: 14px;color: #959495}
        .detail_text{float: right;font-size: 14px;}
        .pay_item{margin-bottom: 15px}
        .img_box:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .confirm_item:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .item_list{float: left;width: 50%;margin: 0.8rem 0;height:40px ;border-right: 1px solid #eee}
        .item_list a{display: block}
        .item_text_box{width: 110px;position: relative;margin: 0 auto}
        .item_text_box img {height: 100%;}
        .serve_title{margin-bottom: 0;margin-top: 0;font-size: 12px;color: #333}
        .serve_text{font-size: 12px;color: #959495}
        .item_text{height: 100%;float: right;top: 0;padding-top: 5px}
        .line_title{line-height: 20px;width: 126px;margin: 0 auto;font-size: 12px;text-align: center;position: relative;margin-top: 20px}
        .line_title  img{width: 100%;position: absolute;left: 0;height: 4px;top: 50%;margin-top: -2px}
        .line_title_name{font-size: 14px;margin: 0}
        .item_box{border-bottom: 12px solid #ededed}
        .item_box:after {
            content: ".";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }
        .pay_detail{width: 70%;margin: 0 auto;border-top: 1px solid #e6e6e6;margin-top: 30px;padding-top: 20px;padding-bottom: 5px;}
        .pay_chose_title{font-size: 16px;}
        .pay_button_box{padding: 10px 15px 15px 15px ;}
        .pay_button{width: 100%;height: 45px;line-height: 45px;background-image: url(../img/user/back_background.png);border: none;color: white}
        .pay_chose_after{position: absolute;right: 30px;color: #959495}
        .confirm_item{width: 100%;padding: 15px}
        .confirm_title{width: 20%;float: left;font-size: 14px;color: #959495}
        .confirm_text{float: left;width: 80%;font-size: 14px;padding-left: 20px}
        .pay_way_number{font-size: 16px;text-align: center;margin-top: 30px;margin-bottom: 30px}
        .way_money{color: #ff5266}
        .pay_line{height: 15px;background-color: #ededed}
        .img_box{padding: 15px 30px;border-bottom: 1px solid #eaf1f9;padding-bottom: 0}
        .uncle_img{width: 33%;text-align: center;float: left;margin-bottom: 15px}
        .uncle_img img{height: 35px;border-radius: 50%}
        .uncle_img p{font-size: 14px;color: #959495;}
        .uncle_img a{height: 50px;line-height: 50px;width: 95%;}
        .uncle_img .my_handle{height: 30px;line-height: 30px;}
        .handle_text{width: 100%;height: 65px;border: 1px solid #eaf1f9;margin-bottom: 30px;padding: 10px;font-size: 14px}
        .handle_tip{color: #959495}


    </style>
</head>
<body>
<div class="page-group">
    <div class="page  page-current" >
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">退单</h1>
        </header>
        <div class="content" style="background-color: white" id="back">
            <div class="item_box">
                <div class="line_title">
                    <img src="../img/serve/t_line.png" />
                    <h4 class="line_title_name">退单内容</h4>
                </div>
                <div class="img_box">
                    <div class="pay_confirm">
                        <div class="confirm_item">
                            <div class="confirm_title">订单状态</div>
                            <div class="confirm_text" v-cloak>{{state}}</div>
                        </div>
                        <div class="confirm_item">
                            <div class="confirm_title">退款金额</div>
                            <div class="confirm_text" v-cloak>{{money}}元</div>
                        </div>
                        <div class="confirm_item">
                            <div class="confirm_title">退款路径</div>
                            <div class="confirm_text">
                                <p v-cloak>{{moneystr}}</p>
                                <p class="handle_tip">1-7个工作日退回你的原支付方</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="img_box" style="border-bottom: none">
                <div class="line_title">
                    <img src="../img/serve/t_line.png" />
                    <h4 class="line_title_name">退单内容</h4>
                </div>
                <div class="pay_confirm" style="margin-top: 30px" >
                    <div class="uncle_img">
                        <a class="button my_handle serve_btn btn_active" v-on:click="change('不想预约了',$event)">不想预约了</a>
                    </div>
                    <div class="uncle_img">
                        <a class="button my_handle serve_btn" v-on:click="change('信息填错了',$event)">信息填错了</a>
                    </div>
                    <div class="uncle_img">
                        <a class="button my_handle serve_btn" v-on:click="change('没责任心',$event)">没责任心</a>
                    </div>
                    <div class="uncle_img">
                        <a class="button my_handle serve_btn" v-on:click="change('未按时到岗',$event)">未按时到岗</a>
                    </div>
                    <div class="uncle_img">
                        <a class="button my_handle serve_btn" v-on:click="change('不注重卫生',$event)">不注重卫生</a>
                    </div>
                    <div class="uncle_img">
                        <a class="button my_handle serve_btn" v-on:click="change('道德败坏',$event)">道德败坏</a>
                    </div>
                    <textarea class="handle_text" placeholder="其他退订理由" v-model="content"></textarea>
                </div>
            </div>
            <div class="pay_button_box">
                <a class="button pay_button" href="#" v-on:click="submit">提交</a>
            </div>
        </div>
    </div>
</div>

<!--<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>-->

<!--sui-ui-->
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script src='../js/plugins/md5.js'></script>
<script src='../js/van-common.js'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
$.init();
new Vue({
    el: '#back',
    data: {
        apiUrl: '/order/cancelOrder',
        apiCancelUrl: '/order/cancel',
        orderid: getItem('order_cancel_id'), reason:'不想预约了', reasonList:[], content:'', pay_type:1,
        state: '', money: 0, moneystr: '', price: 0, pricestr: ''
    },
    emulateJSON: true,
    mounted: function() {
        this.$http.get(createUrl(this.apiCancelUrl, {userid: userId, orderid: this.orderid}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    this.state = jsonData.state,
                    this.money = jsonData.money,
                    this.moneystr = jsonData.moneystr,
                    this.price = jsonData.price,
                    this.pricestr = jsonData.pricestr
                    console.log(jsonData)
                } else {
                    $.toast(response.data.m);
                }
            })
    },
    methods: {
        submit: function () {
            if (this.reason == "") {
                $.toast("请选择退单内容！");
                return false;
            }
            this.$http.get(createUrl(this.apiUrl, {userid: userId, orderid:this.orderid, reason:this.reason, content:this.content, pay_type:this.pay_type}, true))
                .then(function(response) {
                    $.toast(response.data.m);
                })
        },
        change: function (a,e) {
            //按钮改变
            $('.serve_btn').removeClass('btn_active');
            $(e.currentTarget).addClass('btn_active');
            this.reason = a;
        }
    }
});
</script>
</body>
</html>

