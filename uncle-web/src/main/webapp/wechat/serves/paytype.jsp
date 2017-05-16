<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>支付方式选择</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    
    <link rel="stylesheet" href="../css/bottom.css" />
    
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/??sm.min.css,sm-extend.min.css">
    
    <link rel="stylesheet" href="../css/server.css" />
    
</head>
<style>
	body{font-family: "微软雅黑"}
	.pay_count{height: 71px; width: 100%; background-color: #fff; text-align: center;padding-top: 23px; margin-top: 44px;}
	.pay_type{margin-top: 12px; width: 100%; background-color: #fff; height: 100%;}
	.type_logo{width: 20px; height: 20px;}
	.choose_type{height: 54px; line-height: 54px; width: 90%; margin: auto;}
	.choose_type span{margin-left: 8px;}
	.go_pic{float: right; margin: 16px;}
</style>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script type='text/javascript' src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js' charset='utf-8'></script>
<script type='text/javascript' >
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
	
	var openId = getItem('openid');
	var tempId = ${tempid};
    var conInfo=new Vue({
        el: '#con_info',
        data: {
            apiOrderUrl: '/order/order'
        },
        emulateJSON: true,
        methods: {
            sendOrderWx: function () {
                this.$http.get(createUrl(this.apiOrderUrl, {userid: userId, pay_type:4, openid:openId,tempid:tempId}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            var orderid = jsonData.orderid;
                            // 生成订单后回调该方法发起支付功能
                            wx.chooseWXPay({
                                timestamp: jsonData.pay.timeStamp,
                                nonceStr: jsonData.pay.noncestr,
                                package: jsonData.pay.packagename,
                                signType: 'MD5',
                                paySign: jsonData.pay.sign, // 支付签名
                                success: function (res) {
                                    // 支付成功后的回调函数
                                }
                            });
                        }else{
                        	alert(response.data.m);
                        }
                    })
            },
            sendOrderBalance: function () {
                this.$http.get(createUrl(this.apiOrderUrl, {userid: userId, pay_type:3,tempid:tempId}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        var orderid = jsonData.orderid;
                        //跳转至订单详情页面
                        
                        
                        setItem('order_detail_id', orderid);
                        window.location.href = '../auth/go?uri=/wechat/order/detail';
                    }else{
                    	alert(response.data.m);
                    }
                })
        }
        }
    });
    
	function payBalance(){
		conInfo.sendOrderBalance();
	}
	
    function payWx(){
    	conInfo.sendOrderWx();
	}
</script>
<body>
	<header class="bar bar-nav my_head">
<!--         <a class="button button-link button-nav pull-left close-popup"> -->
<!--             <span class="icon icon-left" style="margin-top: 10px"></span> -->
<!--         </a> -->
        <h1 class="title">支付中心</h1>
    </header>

	<div></div>
	<div class="pay_count">支付金额<font color="red">￥${money}</font></div>
	<div class="pay_type">
		<div class="choose_type" style="border-bottom: 1px solid #ededed;" onclick="payBalance()">
			<img class="type_logo" src="../img/serve/balance.png">
			<span>账户余额支付</span>
			<img class="go_pic" src="../img/serve/go.png">
		</div>
		<div class="choose_type" onclick="payWx()">
			<img class="type_logo" src="../img/serve/wechat.png">
			<span>微信支付</span>
			<img class="go_pic" src="../img/serve/go.png">
		</div>
	</div>
</body>
</html>