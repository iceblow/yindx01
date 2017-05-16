<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的余额</title>
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
    <link rel="stylesheet" href="../js/dropload/dropload.css" />
    <style>
        p{
            margin: 0;
        }
        .my_head{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);}

        .sign_btn img{height: 100%}

        .phone_input a{height: 40px;position: absolute;right: 15%;top: 30px;border-radius: 0;line-height: 40px;border: none;color: #333}

        .phone_input input{width: 70%;border: 1px solid #d5d7e3;height: 40px;margin-left: 15%;
            background-image:url(../img/user/next_shape_select.png);background-size: 100% 100%;
            margin-left: 15%;margin-top: 30px;line-height: 35px;color: white; }
        input{padding-left: 10px!important;font-size: 16px}
        .money_box{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);border-top: none}
        .money_head:after{background-color: transparent !important}
        .money_number h1{margin: 0;color: white}
        .money_number{color: white;vertical-align: middle;padding-left: 15px;padding-bottom: 25px}
        .add_money{float: right;bottom:25px;height: 30px;border: none;background-color: white;line-height: 30px;right: 15px;color: #ff4c65; }
        .detail_title{line-height: 20px;font-size: 14px;color: #a6a6a6;padding: 15px}
        .detail_text{margin-top: 0}
        .balance_img img{height: 30px}
        .balance_title{font-size: 16px;color: #333}
        .balance_time{font-size: 14px;color: #959495}
        .add_text{font-size: 16px;color: #5e93f9;font-weight: 700}
        .reduce_text{font-size: 16px;color: #333;font-weight: 700}
    </style>
</head>
<body>

<div class="page-group" >
    <div class="page page-current" id="user_home">
        <header class="bar bar-nav my_head money_head">
            <h1 class="title">账户余额</h1>
        </header>
        <div class="content" style="background-color: #ffffff" data-distance="100">
            <!-- 这里是页面内容区 -->
            <div class="money_box">
                <div class="money_number">
                    <h1 class="money_detail" v-cloak>{{balance}}</h1>
                    <span style="font-size: 14px;" >可用余额</span>
                    <a class="button add_money prompt-ok">充值</a>
                </div>
            </div>
            <div class="detail_title">
                账单明细
            </div>
            <div class="list-block detail_text">
                <ul>
                    <li class="item-content" v-for="item in balanceList">
                        <div class="item-media balance_img">
                            <img src="../img/user/balance_default.png" :src="item.logourl" v-cloak/>
                        </div>
                        <div class="item-inner">
                            <div class="item-title">
                                <p class="balance_title" v-cloak>{{item.title}}</p>
                                <p class="balance_time" v-cloak>{{item.time}}</p>
                            </div>
                            <div class="item-after">
                                <span class="add_text" v-cloak>
                                <span v-if="item.type == 3">
                                +</span>
                                <span v-else="item.type == 1 || item.type == 2 || item.type == 4">
                                -</span> {{item.count}}</span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type='text/javascript' src='http://res.wx.qq.com/open/js/jweixin-1.2.0.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script src="../js/dropload/dropload.min.js"></script>
<script>

   var openId = getItem('openid');
    var home = new Vue({
        el: '#user_home',
        data: {
            apiUrl: '/user/getBalanceList',
            apiRechargeUrl: '/user/recharge',
            balanceList: [],
            balance: 0,
            page: 1,
            items: [],
            havemore:0
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers();
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {userid: userId, page: this.page}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                        	var jsonData = JSON.parse(response.data.r);
                            if (this.page == 1) {
                                this.balance = jsonData.balance;

                            }
                            this.balanceList = jsonData.balanceList;
                            this.havemore = jsonData.havemore;
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            hello: function (me) {
                // 锁定
                me.lock();
                // 无数据
                me.noData();
                me.resetload();
            },
            recharge: function(value) {
                this.$http.get(createUrl(this.apiRechargeUrl, {userid: userId, count: value,openid:openId,pay_type:3}, true))
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
                                }
                            });
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            }

        }
    });

    $(document).on('click','.prompt-ok', function () {
        $.prompt('输入你要充值的金额', function (value) {
            var reg = /^\d+(?=\.{0,1}\d+$|$)/
            if (!reg.test(value)) {
                $.alert('输入正确的金额');
                return;
            }
            if(parseFloat(value) == 0){
            	$.alert('充值金额不能为零');
                return;
            }
            home.recharge(value);
        });
    });
    
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

</script>
</body>
</html>
