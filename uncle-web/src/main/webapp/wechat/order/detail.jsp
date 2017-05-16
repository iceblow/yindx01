<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单详情</title>
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
        .money_number h4{margin: 0;color: white;text-align: center}
        .money_number{color: white;vertical-align: middle;padding-left: 15px;padding-bottom: 25px}
        .add_money{float: right;bottom:25px;width: 45px;height: 30px;border: none;background-color: white;line-height: 30px;right: 15px;color: #ff4c65; }
        .detail_title{line-height: 20px;font-size: 14px;color: #a6a6a6;padding: 15px}
        .detail_text{margin-top: 0}
        .balance_img img{height: 30px}
        .balance_title{font-size: 16px;color: #333}
        .balance_time{font-size: 14px;color: #959495}
        .add_text{font-size: 16px;color: #ff4c65;font-weight: 700}
        .reduce_text{font-size: 16px;color: #333;font-weight: 700}
        .detail_btn_box div{float: left;width: 50%;padding-right: 15px;padding-top: 15px}
        .detail_btn_box:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .detail_btn_box div a{height: 30px ;border: none;background-color: #fff;line-height: 30px}
        .btn_left{color: #333}
        .btn_right{color: #ff4c65}
        .btn_coment {color: #5d92f9}
        .uncle_detail img{border-radius: 50%}
        .uncle_age{font-size: 14px;color: #959495;margin-left: 10px}
        /*列表样式*/
        .my_list:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .list_box{padding: 15px;border-bottom: 1px solid #e8e8e8}
        .my_list{padding-bottom: 15px;font-size: 14px}
        .my_list div{float: left;}
        .my_list .list_title{width: 20%;color: #959495}
        .my_list .list_text{width: 80%;padding-left: 20px}
        .money_number {padding-bottom: 15px}
        
        .pop_box{
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            z-index: 9990;
            display: none;
        }
         .pay_pop{
            width: 100%;
            height: auto;
            padding: 0 15px;
            background-color: white;
            position: fixed;
            bottom: 0;
            z-index: 9999;
            display: none;
        }
        .pay_way_box {
            width: 100%;
            height: 54px;
            line-height: 54px;
            font-size: 14px;
            position: relative;      
       }
       .pay_logo_pic{
            width: 20px;
            margin-right: 7px;
            vertical-align: text-bottom;
       }
       .right_arrow{
            width: 6px;
            position: absolute;
            right: 0;
            top: 21px;
       }
       .right_span{
            position: absolute;
            right: 20px;
       }
    </style>
</head>
<body>

<div class="page-group" >
    <div class="page page-current" id="user_home">
        <header class="bar bar-nav my_head money_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left" style="margin-top: 10px"></span>
            </a> -->
        </header>
        <div class="content" style="background-color: #ffffff">
            <!-- 这里是页面内容区 -->
            <div class="money_box">
                <div class="money_number">
                    <!-- 状态文案  -->
                    <h4 class="oder_detail" v-if="orderInfo.state == 0">待支付定金</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 1">等待接单</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 2">未出发</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 3">已出发</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 4">服务中</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 5">待支付</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 6">已完成</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 7">已退单</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 8">被拒单</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 9">预约失败</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 10">待确认</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 11">待支付</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 12">服务完成</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 13">退单申请中</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 14">待支付</h4>
                    <h4 class="oder_detail" v-if="orderInfo.state == 20">投诉中</h4>
                    <!-- 计时文案剩余支付时间 -->
                    <p class="text-center" style="font-size: 13px;margin-top: 20px" v-if="orderInfo.state == 0"><span class="icon icon-clock"> 剩余</span> {{orderInfo.paylasttime}}</p>
                    <!-- 计时文案已经服务时间 -->
                    
                    <!-- 待支付定金 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 0">
                        <div><a class="button btn_left" v-on:click="cancel">取消订单</a></div>
                        <div><a class="button btn_right" v-on:click="payDeposit">支付定金</a></div>
                    </div>
                    
                    <!-- 待接单 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 1">
                        <div><a class="button btn_left" v-on:click="cancelOrder">退单</a></div>
                        <div><a class="button btn_right" v-on:click="addTip">加小费</a></div>
                    </div>
                    <!-- 未出发、已出发 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 2 || orderInfo.state == 3">
                        <div style="float: none;margin: 0 auto"><a class="button btn_left" v-on:click="cancelOrder">退单</a></div>
                    </div>
                    <!-- 服务中 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 4">
                        <div style="width: 30%;margin-left: 5%"><a class="button btn_left" v-on:click="cancelOrder">退单</a></div>
                        <div style="width: 30%"><a class="button btn_left" v-on:click="complainOrder">投诉</a></div>
                        <div style="width: 30%" v-if="(orderInfo.categoryid == 14 || orderInfo.categoryid == 15 || orderInfo.categoryid == 16 || orderInfo.categoryid == 17 || orderInfo.categoryid == 18) && orderInfo.canpay == 1"><a class="button btn_right" v-on:click="payMoneyMonth">支付月工资</a></div>
                    </div>
                    <!-- 服务完成,待支付 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 5">
                        <div style="width: 30%"><a class="button btn_left" v-on:click="complainOrder">投诉</a></div>
                        <div style="width: 30%;float: none;margin: 10 auto"><a class="button btn_right" v-on:click="payMoney">支付</a></div>
                    </div>
                    <!-- 已完成 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 6 &&　order.comment_state == 0">
                        <div style="float: none;margin: 0 auto"><a class="button btn_left" v-on:click="commentOrder">评论</a></div>
                    </div>
                    <!-- 多人订单确认 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 10">
                        <div><a class="button btn_left" v-on:click="cancelOrder">退单</a></div>
                        <div><a class="button btn_right" v-on:click="comfirmMultiOrder">继续预约</a></div>
                    </div>
                    <!-- 支付第一次维修金,确认长期单工资 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 11">
                        <div style="width: 30%;margin-left: 5%"><a class="button btn_left" v-on:click="cancelOrder">退单</a></div>
                        <div style="width: 30%;margin-left: 5%" v-if="orderInfo.categoryid == 14 || orderInfo.categoryid == 15 || orderInfo.categoryid == 16 || orderInfo.categoryid == 17 || orderInfo.categoryid == 18">
                        <a class="button btn_left" v-on:click="comfirmWages">确认工资</a></div>
                        <div style="width: 30%;margin-left: 5%" v-if="orderInfo.categoryid == 3 || orderInfo.categoryid == 4 || orderInfo.categoryid == 7 || orderInfo.categoryid == 9 || orderInfo.categoryid == 10 || orderInfo.categoryid == 11 || orderInfo.categoryid == 12 || orderInfo.categoryid == 13">
                        <a class="button btn_left" v-on:click="payMoney">支付费用</a></div>
                    </div>
                    <!-- 维修类订单确认服务完成 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 12">
                        <div style="float: none;margin: 0 auto"><a class="button btn_left" v-on:click="comfirmOrder">确认完成</a></div>
                    </div>
                    <!-- 支付退单费用 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 14">
                        <div style="float: none;margin: 0 auto"><a class="button btn_left" v-on:click="">支付退单费</a></div>
                    </div>
                    <!-- 投诉中订单 -->
                    <div class="detail_btn_box" style="text-align:center" v-if="orderInfo.state == 20">
                        <div style="float: none;margin: 0 auto"><a class="button btn_left" v-on:click="goServer">联系客服</a></div>
                    </div>
                </div>
            </div>
            <!-- 阿姨或者公司列表  -->
            <div class="list-block detail_text" style="margin-bottom: 0">
                <ul>
                    <li class="item-content uncle_detail" style="padding: 15px" v-for="aunt in auntList" v-cloak>
                        <div class="item-media balance_img">
                            <img src="../img/user/default_head_pic.png" :src="aunt.picurl"/>
                        </div>
                        <div class="item-inner">
                            <div class="item-title">
                                <p class="balance_title" v-cloak>{{aunt.name}}<span class="uncle_age" v-cloak>{{aunt.age}}岁</span><span class="uncle_age uncle_star" v-cloak>{{aunt.phone}}</span></p>
                            </div>
                            <div class="item-after">
                                <span class="add_text">
                                    <img src="../img/user/balance_default.png" height="30"/>
                                </span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- 订单的详细信息  -->
            <div class="list_box">
                <div class="my_list">
                    <div class="list_title">服务类型</div>
                    <div class="list_text" v-cloak>{{orderInfo.servername}}</div>
                </div>
                <div class="my_list">
                    <div class="list_title">服务地址</div>
                    <div class="list_text" v-cloak>{{orderInfo.address}}</div>
                </div>
                <div class="my_list">
                    <div class="list_title">阿姨数量</div>
                    <div class="list_text" v-cloak>{{auntcount}}</div>
                </div>
                <div class="my_list">
                    <div class="list_title">服务时长</div>
                    <div class="list_text" v-cloak>{{expect_time}}小时</div>
                </div>
                <div class="my_list">
                    <div class="list_title">上门时间</div>
                    <div class="list_text" v-cloak>{{orderInfo.timetitle}}</div>
                </div>
                <div class="my_list">
                    <div class="list_title">费用明细</div>
                    <div class="list_text" v-cloak>费用明细</div>
                </div>
            </div>
            <div class="list-block detail_text">
                <ul>
                    <li class="item-content">
                        <div class="item-media balance_img">
                            <img src="../img/home/p_m.png" />
                        </div>
                        <div class="item-inner">
                            <div class="item-title">
                                <p class="balance_title">预计全款</p>
                                <p class="balance_time">包含定金30元</p>
                            </div>
                            <div class="item-after">
                                <span class="add_text">+ 500.0</span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- 支付弹出框 -->
<div class="pop_box"></div>
<div class="pay_pop">
      <div class="pay_way_box" style="border-bottom: 1px solid #ededed;" onclick="payClick(1)">
          <img src="../img/account/logo_account.png" class="pay_logo_pic">
          <span>账户余额支付</span>
          <!-- <span class="right_span">余额不足</span> -->
          <img src="../img/account/right_arrow.png" class="right_arrow">
      </div>
      <div class="pay_way_box" onclick="payClick(2)">
          <img src="../img/account/logo_wechat.png" class="pay_logo_pic">
          <span>微信支付</span>
          <!-- <span class="right_span">需要安装微信客户端</span> -->
          <img src="../img/account/right_arrow.png" class="right_arrow">
      </div>
</div>
<input type="hidden"  id="tip_money" value="" />
<input type="hidden"  id="pay_type" value="" />
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>var Zepto = jQuery</script>
<!--sui-ui-->
<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type='text/javascript' src='http://res.wx.qq.com/open/js/jweixin-1.2.0.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
var orderDetail = new Vue({
    el: '#user_home',
    data: {
        apiUrl: '/order/orderDetail',
        apiCalcel: '/order/calcelBook',//取消订单
        apiCancelOrder: '/order/cancel',//退单确认
        apiComfirmWages:'/order/comfirmWages',//继续预约
        apiComfirmOrder:'/order/comfirmOrder',//确认
        apiPay: '/order/payDeposit',//支付定金
        apiPayDepositUrl: '/order/payDeposit',
        apiPayOrderUrl: '/order/payOrder',
        apiPayUrl: 'order/pay',
        apiAddTipUrl: '/order/addTip',
        apicomfirmMultiOrder: '/order/comfirmMultiOrder',
        orderId: getItem('order_detail_id'), userId: userId,
        orderInfo: {}, auntList:[], auntcount:0, day_time:'', server_time:0, expect_time:0
    },
    emulateJSON: true,
    mounted: function() {
        this.$http.get(createUrl(this.apiUrl, {userid: userId, orderid: this.orderId}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    this.orderInfo = jsonData.orderInfo;
                    this.auntList = jsonData.auntList;
                    this.auntcount = jsonData.auntcount;
                    this.day_time = jsonData.day_time;
                    this.server_time = jsonData.server_time;
                    this.expect_time = jsonData.expect_time;
                } else {
                    $.toast(response.data.m);
                }
            })
    },
    methods: {
        //取消订单
        cancel: function () {
            this.$http.get(createUrl(this.apiCalcel, {userid: userId, orderid:this.orderId,}, true))
                .then(function(response) {
                    $.alert(response.data.m, function () {
                        window.history.back();
                    });
                });
        },
        // 添加小费
        addTip: function () {
            $.prompt('请输入打赏金额', function (value) {
            	var reg = /^\d+(?=\.{0,1}\d+$|$)/
                if (!reg.test(value)) {
                    $.alert('输入正确的金额');
                    return;
                }
                if(parseFloat(value) == 0){
                	$.alert('打赏金额不能为零');
                    return;
                }
                //弹出支付选择框
                $('.pop_box').show();
                $('.pay_pop').show();
                $('#pay_type').val(2);
                $('#tip_money').val(value);
            });
        },
        addTipBalance:function () {
        	var tip_money = $('#tip_money').val();
        	this.$http.get(createUrl(this.apiAddTipUrl, {userid: list1.userId, pay_type:3, orderid:this.orderId, count:tip_money}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    mui.toast(response.data.m);
                }else{
                	mui.toast(response.data.m);
                }
            })
        },
        addTipWx:function () {
        	var tip_money = $('#tip_money').val();
        	var openId = getItem('openid');
        	this.$http.get(createUrl(this.apiAddTipUrl, {userid: list1.userId, pay_type:4, orderid:this.orderId, count:tip_money,openid:openId}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                	 var jsonData = JSON.parse(response.data.r);
                     // 生成订单后回调该方法发起支付功能
                     wx.chooseWXPay({
                         timestamp: jsonData.pay.timeStamp,
                         nonceStr: jsonData.pay.noncestr,
                         package: jsonData.pay.packagename,
                         signType: 'MD5',
                         paySign: jsonData.pay.sign, // 支付签名
                         success: function (res) {
                             // 支付成功后的回调函数
                         	alert("支付成功");
                         }
                     });
                }else{
                	mui.toast(response.data.m);
                }
            })
        },
        //继续预约
        comfirmWages:function () {
        	this.$http.get(createUrl(this.apiComfirmWages, {userid: userId, orderid:this.orderId}, true))
            .then(function(response) {
            	if (response.data.c == 1) {
                    mui.toast(response.data.m);
                    window.location.reload();
                } else {
                	mui.toast(response.data.m);
                }
            });
        },
        comfirmMultiOrder:function () {
        	this.$http.get(createUrl(this.apicomfirmMultiOrder, {userid: userId,orderid:this.orderId}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    mui.toast(response.data.m);
                    window.location.reload();
                } else {
                	mui.toast(response.data.m);
                }
            })
        },
        //联系客服
        goServer:function () {
            window.location.href='https://ikefu.baidu.com/wise/zk';
        },
        //确认完成
        comfirmOrder:function () {
        	this.$http.get(createUrl(this.apiComfirmOrder, {userid: userId, orderid:this.orderId}, true))
            .then(function(response) {
            	mui.toast(response.data.m);
            	if(response.data.c == 1){
            		mui.toast(response.data.m);
            		window.location.reload();
                }else {
                    mui.toast(response.data.m);
                }
            });
        },
        complainOrder: function () {
            setItem('order_complain_id', this.orderId);
            window.location.href = '../auth/go?uri=/wechat/order/complain'
        },
        // 支付定金方法
        payDeposit: function () {
        	//选择支付类型的弹出
        	$('.pop_box').show();
            $('.pay_pop').show();
            $('#pay_type').val(1);
        },
        payDepositBalance: function () {
            this.$http.get(createUrl(this.apiPayDepositUrl, {userid: userId, pay_type: 3,orderid:this.orderId}, true))
                .then(function(response) {
                	console.log(response);
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        // 生成订单后回调该方法发起支付功能
                        alert("支付成功");
                        window.location.reload();
                    } else {
                    	alert(response.data.m);
                    }
                })
        },
        payDepositWx: function () {  
        	var openId = getItem('openid');
            this.$http.get(createUrl(this.apiPayDepositUrl, {userid: userId, pay_type: 4, openid:openId,orderid:this.orderId}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        // 生成订单后回调该方法发起支付功能
                        wx.chooseWXPay({
                            timestamp: jsonData.pay.timeStamp,
                            nonceStr: jsonData.pay.noncestr,
                            package: jsonData.pay.packagename,
                            signType: 'MD5',
                            paySign: jsonData.pay.sign, // 支付签名
                            success: function (res) {
                                // 支付成功后的回调函数
                            	alert("支付成功");
                            	window.location.reload();
                            }
                        });
                    } else {
                    	alert(response.data.m);
                    }
                })
        },
        // 支付剩余金额
        payOrder: function () {
            this.$http.get(createUrl(this.apiPayOrderUrl, {userid: this.userId, pay_type:2, orderid:this.orderId}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        
                        console.log(this.jsonData)
                    }
                })
        },
        payMoney:function () {
            setItem('order_pay_id', this.orderId);
            setItem('order_pay_sid', this.orderInfo.categoryid);
            window.location.href = '../auth/go?uri=/wechat/pay/pay_total'
        },
        payMoneyMonth:function () {
            setItem('order_pay_id', this.orderId);
            setItem('order_pay_sid', this.orderInfo.categoryid);
            window.location.href = '../auth/go?uri=/wechat/pay/pay_total'
        },
        //评论订单
        commentOrder: function () {
        	setItem('order_comment_id', this.orderId);
            window.location.href='../auth/go?uri=/wechat/order/handle';
        },
        cancelOrder: function (id) {
            setItem('order_cancel_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/back'
        }
    }
});


$('.pop_box').on("click",function(){
    $('.pop_box').hide();
    $('.pay_pop').hide();
});

$.init();    
var ua = navigator.userAgent.toLowerCase();
if (ua.match(/MicroMessenger/i) == "micromessenger") {
	var backurl = window.location.href;
	var wxjssign=new Vue({
        el: '#wx_jssign',
        data: {
            apiSignUrl: '/system/getWxPreperSign',
        },
        emulateJSON: true,
        methods: {
            wxjssign: function () {
                this.$http.get(createUrl(this.apiSignUrl, {sign_backurl:backurl}, false))
                    .then(function(response) {
                    	if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
        					var nonce_str = jsonData.nonce_str;
        				    var appid = jsonData.appid;
        				    var sign = jsonData.sign;
        				    var timestamp = jsonData.timestamp;
        				    wx.config({
        				    	debug: false,
        				        appId: appid, 
        				        timestamp:timestamp , 
        				        nonceStr: nonce_str, 
        				        signature: sign,
        				        jsApiList: ['checkJsApi', 'chooseWXPay']
        				    });
                        } 
                    })
            }
        }
    });
	wxjssign.wxjssign();
}

wx.ready(function(){

});
wx.error(function(res){

});
wx.checkJsApi({
    jsApiList: ['chooseWXPay'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
    success: function(res) {
        // 以键值对的形式返回，可用的api值true，不可用为false
        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
    }
});

function payClick(type){
	$('.pop_box').hide();
	$('.pay_pop').hide();
	var paytype =  $('#pay_type').val();
	if(paytype == 1){//定金
		if(type == 1){
			orderDetail. payDepositBalance();
		}else{
			orderDetail. payDepositWx();
		}
	}else{
		if(type == 1){
			orderDetail. addTipBalance();
		}else{
			orderDetail. addTipWx();
		}
	}
}

</script>
</body>
</html>
