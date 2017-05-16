<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>我的订单</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="../mui/mui.min.css">
    <link rel="stylesheet" href="../css/common.css">
    <style>
        html,
        body {
            background-color: #efeff4;
        }
        .mui-bar~.mui-content .mui-fullscreen {
            top: 44px;
            height: auto;
        }
        .mui-pull-top-tips {
            position: absolute;
            top: -20px;
            left: 50%;
            margin-left: -25px;
            width: 40px;
            height: 40px;
            border-radius: 100%;
            z-index: 1;
        }
        .mui-bar~.mui-pull-top-tips {
            top: 24px;
        }
        .mui-pull-top-wrapper {
            width: 42px;
            height: 42px;
            display: block;
            text-align: center;
            background-color: #efeff4;
            border: 1px solid #ddd;
            border-radius: 25px;
            background-clip: padding-box;
            box-shadow: 0 4px 10px #bbb;
            overflow: hidden;
        }
        .mui-pull-top-tips.mui-transitioning {
            -webkit-transition-duration: 200ms;
            transition-duration: 200ms;
        }
        .mui-pull-top-tips .mui-pull-loading {
            /*-webkit-backface-visibility: hidden;
            -webkit-transition-duration: 400ms;
            transition-duration: 400ms;*/

            margin: 0;
        }
        .mui-pull-top-wrapper .mui-icon,
        .mui-pull-top-wrapper .mui-spinner {
            margin-top: 7px;
        }
        .mui-pull-top-wrapper .mui-icon.mui-reverse {
            /*-webkit-transform: rotate(180deg) translateZ(0);*/
        }
        .mui-pull-bottom-tips {
            text-align: center;
            background-color: #efeff4;
            font-size: 15px;
            line-height: 40px;
            color: #777;
        }
        .mui-pull-top-canvas {
            overflow: hidden;
            background-color: #fafafa;
            border-radius: 40px;
            box-shadow: 0 4px 10px #bbb;
            width: 40px;
            height: 40px;
            margin: 0 auto;
        }
        .mui-pull-top-canvas canvas {
            width: 40px;
        }
        .mui-slider-indicator.mui-segmented-control {
            background-color: #efeff4;
        }
        .order_name{font-size: 16px;font-weight: 700}
        .order_tab{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);padding: 5px !important;height: 44px}
        .mui-scroll-wrapper .mui-scroll .mui-control-item{color: #cfe2ff;line-height: 30px}
        /*.mui-scroll-wrapper .mui-scroll .mui-active{color: white !important;border-bottom: 1px solid white !important;}*/
        .order_status_img{position: absolute;top: 18px;right: 10px;width: 7px !important;}
        .order_status img{width: 10px !important;}
        .card{
            background: #fff;
            box-shadow: 0 0.05rem 0.1rem rgba(0,0,0,.3);
            position: relative;
            padding: 15px;
        }
        .order-header{margin-bottom: 15px}
        .order_text{border-bottom: 1px solid #eee;color: #bbbbbb}
        .order_time{margin: 15px 0}
        .order-footer{padding-top:15px;vertical-align: middle;height: 48px;line-height: 35px}
        .order_left_time{color: #ff4c65}
        .order_status{margin-right: 10px}
        .order_btn{margin-left: 10px}
        .mui-table-view-cell:after { background-color: #ffffff; }
        .red_btn {border: 1px solid #ff4c65; color: #ff4c65}
        .blue_btn {border: 1px solid #5d8ff9; color: #5d8ff9}
        .mui-slider-group a {color: #bbbbbb}
        .mui-bar {-webkit-box-shadow: 0 0 1px #bbbbbb; box-shadow: 0 0 1px #bbbbbb;}
        .mui-table-view { margin-bottom: 10px;}
        .mui-active{color: white !important;border-bottom: 1px solid white !important;}
        
        .pop_box{
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            z-index: 13;
            display: none;
        }
         .pay_pop{
            width: 100%;
            height: auto;
            padding: 0 15px;
            background-color: white;
            position: fixed;
            bottom: 0;
            z-index: 99;
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
<div class="mui-content">
    <div id="slider" class="mui-slider mui-fullscreen">
       <!-- 总订单分类 -->
       <div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted" style="height: 40px;width: 100%">
            <div class="mui-scroll order_tab" style="width: 100%" id="title">
                <a class="mui-control-item mui-active" href="#item1mobile">未服务</a>
                <a class="mui-control-item" href="#item2mobile">服务中</a>
                <a class="mui-control-item" href="#item3mobile">已完成</a>
            </div>
        </div>
        <!-- 未服务订单列表 -->
        <div class="mui-slider-group">
            <div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="mui-scroll" data-id="1">
                        <ul class="mui-table-view" id="list1">
                            <li class="mui-table-view-cell" v-for="order in orderlists">
                                <div class="card">
                                    <div class="click_detail" :id="order.orderid">
                                        <div class="order-header">
                                            <span class="order_name"  style="color: #333;font-size: 14px "    v-cloak>{{order.servername}}</span>
                                            <a class="mui-pull-right">
                                                <span class="order_status" v-if="order.state == 0">待支付定金</span>
                                                <span class="order_status" v-if="order.state == 1">等待接单</span>
                                                <span class="order_status" v-if="order.state == 2">未出发</span>
                                                <span class="order_status" v-if="order.state == 3">已出发</span>
                                                <span class="order_status" v-if="order.state == 10">待确认</span>
                                                <span class="order_status" v-if="order.state == 11">待支付</span>
                                                <span class="order_status" v-if="order.state == 20">投诉中</span>
                                                <img class="order_status_img" src="../img/serve/right_go.png" />
                                            </a>
                                        </div>
                                        <div class="order-content">
                                            <div class="order_text">
                                                <div class="order_pos order_status" v-cloak>
                                                    <img src="../img/serve/order_t.png" />
                                                    {{order.timetitle}}
                                                </div>
                                                <div class="order_time order_status" v-cloak>
                                                    <img src="../img/serve/order_pos.png" />
                                                    {{order.address}}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="order-footer">
                                        <!-- 订单支付倒计时 -->
                                        <span class="order_left_time" v-if="order.paylasttime > 0" ><font :data-id="order.orderid" class="rlasttime"></font></span>
                                        <input type="hidden" v-if="order.paylasttime > 0" :id="order.orderid" :value="order.paylasttime"/>
                                        <!-- 操作按钮  -->
                                        <!-- 投诉 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="goServer(order.orderid)" v-if="order.state == 20">联系客服</button>
                                        <!-- 未支付定金 -->
                                        <button class="button order_btn mui-pull-right red_btn" v-if="order.state == 0" v-on:click="payOrder(order.orderid)">付定金</button>
                                        <button class="button order_btn mui-pull-right" v-on:click="cancel(order.orderid)" v-if="order.state == 0">取消订单</button>
                                        <!-- 等待接单-->
                                        <button class="button order_btn mui-pull-right red_btn" v-if="order.state == 1" v-on:click="addTip(order.orderid)">加小费</button>
                                        <button class="button order_btn mui-pull-right" v-on:click="cancelOrder(order.orderid)" v-if="order.state == 1">退单</button>
                                        
                                        <button class="button order_btn mui-pull-right" v-on:click="cancelOrder(order.orderid)" v-if="order.state == 2">退单</button>
                                        <button class="button order_btn mui-pull-right" v-on:click="cancelOrder(order.orderid)" v-if="order.state == 3">退单</button>
                                        <!-- 多人代确认 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="cancelOrder(order.orderid)" v-if="order.state == 10">退单</button>
                                        <button class="button order_btn mui-pull-right" v-on:click="comfirmMultiOrder(order.orderid)" v-if="order.state == 10">继续预约</button>
                                        <!-- 维修类支付维修费用 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="cancelOrder(order.orderid)" v-if="order.state == 11">退单</button>
                                        <button class="button order_btn mui-pull-right" v-on:click="comfirmWages(order.orderid)" v-if="order.state == 11 && 
                                        (order.categoryid == 14 || order.categoryid == 15 || order.categoryid == 16 || order.categoryid == 17 || order.categoryid == 18)">确认工资</button>
                                        <button class="button order_btn mui-pull-right red_btn" v-on:click="payMoney(order.orderid,order.categoryid)" v-if="order.state == 11 && 
                                        (order.categoryid == 3 || order.categoryid == 4 || order.categoryid == 7 || order.categoryid == 9 || order.categoryid == 10 || order.categoryid == 11 || order.categoryid == 12 || order.categoryid == 13)">支付费用</button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- 服务中订单列表 -->
            <div id="item2mobile" class="mui-slider-item mui-control-content">
                <div class="mui-scroll-wrapper">
                    <div class="mui-scroll" data-id="2">
                        <ul class="mui-table-view" id="list2">
                            <li class="mui-table-view-cell" v-for="order in orderlists">
                                <div class="card">
                                    <div class="click_detail" :id="order.orderid">
                                        <div class="order-header">
                                            <span class="order_name"  style="color: #333;font-size: 14px "    v-cloak>{{order.servername}}</span>
                                            <a class="mui-pull-right" :id="order.orderid">
                                                <span class="order_status" v-if="order.state == 4">服务中</span>
                                                <span class="order_status" v-if="order.state == 5">待支付</span>
                                                <span class="order_status" v-if="order.state == 20">投诉中</span>
                                                <img class="order_status_img" src="../img/serve/right_go.png" />
                                            </a>
                                        </div>
                                        <div class="order-content">
                                            <div class="order_text">
                                                <div class="order_pos order_status" v-cloak>
                                                    <img src="../img/serve/order_t.png" />
                                                    {{order.timetitle}}
                                                </div>
                                                <div class="order_time order_status" v-cloak>
                                                    <img src="../img/serve/order_pos.png" />
                                                    {{order.address}}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="order-footer">
                                        <!-- 操作按钮  -->
                                        <!-- 投诉 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="goServer(order.orderid)" v-if="order.state == 20">联系客服</button>
                                        <!-- 服务中 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="cancelOrder(order.orderid)" v-if="order.state == 4">退单</button>
                                        <button class="button order_btn mui-pull-right" v-on:click="complainOrder(order.orderid)" v-if="order.state == 4">投诉</button>
                                        <!-- 长期工且可以支付的情况下 -->
                                        <button class="button order_btn mui-pull-right red_btn" v-on:click="payMoneyMonth(order.orderid,order.categoryid)" v-if="order.state == 4 &&
                                        (order.categoryid == 14 || order.categoryid == 15 || order.categoryid == 16 || order.categoryid == 17 || order.categoryid == 18) && order.canpay == 1">支付月工资</button>
                                        <!-- 待支付 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="complainOrder(order.orderid)" v-if="order.state == 5">投诉</button>
                                        <!-- 分为长期单支付或者普通单支付 -->
                                        <button class="button order_btn mui-pull-right red_btn" v-on:click="payMoney(order.orderid,order.categoryid)" v-if="order.state == 5">支付</button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- 已完成订单列表 -->
            <div id="item3mobile" class="mui-slider-item mui-control-content">
                <div class="mui-scroll-wrapper">
                    <div class="mui-scroll" data-id="3">
                        <ul class="mui-table-view" id="list3">
                            <li class="mui-table-view-cell" v-for="order in orderlists">
                                <div class="card">
                                    <div class="click_detail" :id="order.orderid">
                                        <div class="order-header">
                                            <span class="order_name"  style="color: #333;font-size: 14px "    v-cloak>{{order.servername}}</span>
                                            <a class="mui-pull-right" :id="order.orderid">
                                                <span class="order_status" v-if="order.state == 6">已完成</span>
                                                <span class="order_status" v-if="order.state == 7">已退单</span>
                                                <span class="order_status" v-if="order.state == 8">被拒单</span>
                                                <span class="order_status" v-if="order.state == 9">预约失败</span> 
                                                <span class="order_status" v-if="order.state == 12">服务完成</span>
                                                <span class="order_status" v-if="order.state == 13">退单申请中</span>
                                                <span class="order_status" v-if="order.state == 14">待支付</span>
                                                <span class="order_status" v-if="order.state == 20">投诉中</span>
                                                <img class="order_status_img" src="../img/serve/right_go.png" />
                                            </a>
                                        </div>
                                        <div class="order-content">
                                            <div class="order_text">
                                                <div class="order_pos order_status" v-cloak>
                                                    <img src="../img/serve/order_t.png" />
                                                    {{order.timetitle}}
                                                </div>
                                                <div class="order_time order_status" v-cloak>
                                                    <img src="../img/serve/order_pos.png" />
                                                    {{order.address}}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="order-footer">
                                        <!-- 操作按钮  -->
                                        <button class="button order_btn mui-pull-right" v-on:click="goServer(order.orderid)" v-if="order.state == 20">联系客服</button>
                                        
                                        <button class="button order_btn mui-pull-right" v-on:click="commentOrder(order.orderid)" v-if="order.state == 6 && order.comment_state == 0">评论</button>
                                        
                                        <button class="button order_btn mui-pull-right" v-on:click="comfirmOrder(order.orderid)" v-if="order.state == 12">确认完成</button>
                                        <!-- 退单待支付 -->
                                        <button class="button order_btn mui-pull-right" v-on:click="test(order.orderid)" v-if="order.state == 14">支付退单费</button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<nav class="mui-bar mui-bar-tab" style="background-color: #fff;">
    <a class="mui-tab-item" href="../auth/go?uri=/wechat/home/home">
        <span class="mui-icon">
            <img src="../img/home/home_unselect_icon.png" class="bar_icon">
        </span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item" href="../auth/go?uri=/wechat/serves/home">
        <span class="mui-icon">
            <img src="../img/home/sort_unselect_icon.png" class="bar_icon">
        </span>
        <span class="mui-tab-label">快速下单</span>
    </a>
    <a class="mui-tab-item mui-active" href="../auth/go?uri=/wechat/order/home">
        <span class="mui-icon">
            <img src="../img/home/order_select.png" class="bar_icon">
        </span>
        <span class="mui-tab-label" style="color: #5094ed">订单</span>
    </a>
    <a class="mui-tab-item" href="../auth/go?uri=/wechat/user/home">
        <span class="mui-icon">
            <img src="../img/home/mine_unselect.png" class="bar_icon">
        </span>
        <span class="mui-tab-label">我的</span>
    </a>
</nav>

<!-- 支付弹出框 -->
<div class="pop_box"></div>
<div class="pay_pop">
      <div class="pay_way_box" style="border-bottom: 1px solid #ededed;" onclick="payDeposit(1)">
          <img src="../img/account/logo_account.png" class="pay_logo_pic">
          <span>账户余额支付</span>
          <!-- <span class="right_span">余额不足</span> -->
          <img src="../img/account/right_arrow.png" class="right_arrow">
      </div>
      <div class="pay_way_box" onclick="payDeposit(2)">
          <img src="../img/account/logo_wechat.png" class="pay_logo_pic">
          <span>微信支付</span>
          <!-- <span class="right_span">需要安装微信客户端</span> -->
          <img src="../img/account/right_arrow.png" class="right_arrow">
      </div>
</div>
<input type="hidden"  id="select_orderid" value="" />
<input type="hidden"  id="tip_money" value="" />
<input type="hidden"  id="pay_type" value="" />
<script src="../mui/mui.min.js"></script>
<script src="../mui/mui.pullToRefresh.js"></script>
<script src="../mui/mui.pullToRefresh.material.js"></script>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type='text/javascript' src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js' charset='utf-8'></script>
<script src='../js/plugins/md5.js'></script>
<script src='../js/van-common.js'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
<!--支付剩余时间的倒计时-->
function refreshPayTime(){
	
	$("font.rlasttime").each(function(){
		var id = $(this).attr("data-id");
		var re_input = $("input[id='" + id + "']");
		var text =re_input.val();
		if(parseInt(text) > 0){
			var miute = parseInt(text / 60);
			var second = text % 60;
			if(parseInt(miute) < 10){
				miute = "0" + miute;
			}
			if(parseInt(second) < 10){
				second = "0" + second;
			}
			$(this).text('剩余:' + miute + ':' + second);
			var newtext = text - 1;
			re_input.val(newtext);
		}
	})

}

setInterval("refreshPayTime()",1000);

$('.pop_box').on("click",function(){
    $('.pop_box').hide();
    $('.pay_pop').hide();
});
mui.init();
(function($) {
    //阻尼系数
    var deceleration = mui.os.ios?0.003:0.0009;
    $('.mui-scroll-wrapper').scroll({
        bounce: false,
        indicators: true, //是否显示滚动条
        deceleration:deceleration
    });
    $.ready(function() {
        //循环初始化所有下拉刷新，上拉加载。
        $.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
            $(pullRefreshEl).pullToRefresh({
                down: {
                    callback: function() {
                        var self = this;
                        var itemId = jQuery(self.element).data('id');
                        if (itemId == 1) {
                            list1.$http.get(createUrl(list1.apiUrl, {userid: userId, type:1, page: 1}, true))
                                .then(function(response) {
                                    if (response.data.c == 1) {
                                        var jsonData = JSON.parse(response.data.r);
                                        list1.orderlists = jsonData.orderlists;
                                        list1.havemore = jsonData.havemore;
                                        list1.page += 1;
                                    }
                                    self.endPullDownToRefresh();
                                })
                        }
                        if (itemId == 2) {
                            list2.$http.get(createUrl(list2.apiUrl, {userid: userId, type:2, page: 1}, true))
                                .then(function(response) {
                                    if (response.data.c == 1) {
                                        var jsonData = JSON.parse(response.data.r);
                                        list2.orderlists = jsonData.orderlists;
                                        list2.havemore = jsonData.havemore;
                                        list2.page += 1;
                                    }
                                    self.endPullDownToRefresh();
                                })
                        }
                        if (itemId == 3) {
                            list3.$http.get(createUrl(list3.apiUrl, {userid: userId, type:3, page: 1}, true))
                                .then(function(response) {
                                    if (response.data.c == 1) {
                                        var jsonData = JSON.parse(response.data.r);
                                        list3.orderlists = jsonData.orderlists;
                                        list3.havemore = jsonData.havemore;
                                        list3.page += 1;
                                    }
                                    self.endPullDownToRefresh();
                                })
                        }


                    }
                },
                up: {
                    callback: function() {
                        var self = this;
                        var itemId = jQuery(self.element).data('id');
                        if (itemId == 1) {
                            list1.$http.get(createUrl(list1.apiUrl, {userid: userId, type:1, page: list1.page}, true))
                                .then(function(response) {
                                    if (response.data.c == 1) {
                                        var jsonData = JSON.parse(response.data.r);
                                        if(jsonData.orderlists.length) {
                                            list1.orderlists.push(jsonData.orderlists);
                                        }
                                        list1.havemore = jsonData.havemore;
                                        list1.page += 1;
                                    }
                                    self.endPullUpToRefresh(!list1.havemore);
                                })
                        }
                        if (itemId == 2) {
                            list2.$http.get(createUrl(list2.apiUrl, {userid: userId, type:2, page: list2.page}, true))
                                .then(function(response) {
                                    if (response.data.c == 1) {
                                        var jsonData = JSON.parse(response.data.r);
                                        if(jsonData.orderlists.length) {
                                            list2.orderlists.push(jsonData.orderlists);
                                        }
                                        list2.havemore = jsonData.havemore;
                                        list2.page += 1;
                                    }
                                    self.endPullUpToRefresh(!list2.havemore);
                                })
                        }
                        if (itemId == 3) {
                            list3.$http.get(createUrl(list3.apiUrl, {userid: userId, type:3, page: list3.page}, true))
                                .then(function(response) {
                                    if (response.data.c == 1) {
                                        var jsonData = JSON.parse(response.data.r);
                                        if(jsonData.orderlists.length) {
                                            list3.orderlists.push(jsonData.orderlists);
                                        }
                                        list3.havemore = jsonData.havemore;
                                        list3.page += 1;
                                    }
                                    self.endPullUpToRefresh(!list3.havemore);
                                })
                        }
                    }
                }
            });
        });
    });
})(mui);

mui('.mui-slider-group').on('tap', '.click_detail', function(e) {
    setItem('order_detail_id', this.getAttribute('id'));
    window.location.href = '../auth/go?uri=/wechat/order/detail'
});
mui('.mui-bar').on('tap', 'a', function(e) {
    window.location.href = this.getAttribute('href')
});

var list1 = new Vue({
    el: '#list1',
    data: {
        apiUrl: '/order/orderList',
        apiCalcel: '/order/calcelBook',//取消订单
        apiCancelOrder: '/order/cancel',//退单确认
        payDeposit:'/order/payDeposit',
        apiAddTipUrl: '/order/addTip',
        apicomfirmMultiOrder: '/order/comfirmMultiOrder',
        apiComfirmWages:'/order/comfirmWages',//继续预约
        orderlists:[], page:1, havemove:0, userId: userId
    },
    emulateJSON: true,
    mounted: function() {
        this.$http.get(createUrl(this.apiUrl, {userid: this.userId, type:1, page: this.page}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    this.orderlists = jsonData.orderlists;
                    this.havemore = jsonData.havemore;
                    this.page += 1;
                    console.log(this.orderlists)
                }
            })
    },
    methods: {
    	//支付订单费用(服务费、维修费等)
    	payMoney: function (id,serid) {
    		setItem('order_pay_id', id);
    		setItem('order_pay_sid', serid);
            window.location.href = '../auth/go?uri=/wechat/pay/pay_total'
        },
        //取消订单
        cancel: function (id) {
            this.$http.get(createUrl(this.apiCalcel, {userid: userId, orderid:id,}, true))
                .then(function(response) {
                	if(response.data.c == 1){
                		 location.reload();
                	}else{
                		mui.toast(response.data.m);
                	}
                });
        },
        //退单
        cancelOrder:function (id) {
        	setItem('order_cancel_id', id);
            window.location.href='../auth/go?uri=/wechat/order/back';
        },
        //弹出支付定金弹框
        payOrder:function (id) {
        	//选择支付类型的弹出
        	$('.pop_box').show();
            $('.pay_pop').show();
            $('#select_orderid').val(id);
            $('#pay_type').val(1);
        },
        // 余额支付订单定金
        payOrderBalance:function () {
            var orderid = $('#select_orderid').val();
            this.$http.get(createUrl(this.payDeposit, {userid: userId, pay_type: 3,orderid:orderid}, true))
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
        // 微信支付订单定金
        payOrderWx:function () {
        	var orderid = $('#select_orderid').val();
        	var openId = getItem('openid');
            this.$http.get(createUrl(this.payDeposit, {userid: userId, pay_type: 4, openid:openId,orderid:orderid}, true))
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
        
        // 添加小费
        addTip: function (id) {
            var btnArray = ['取消', '确定'];
            mui.prompt('', '', '请输入打赏金额', btnArray, function(e) {
                if (e.index == 1) {
                	var reg = /^\d+(?=\.{0,1}\d+$|$)/
                    if (!reg.test(e.value)) {
                        $.alert('输入正确的金额');
                        return;
                    }
                    if(parseFloat(e.value) == 0){
                    	$.alert('打赏金额不能为零');
                        return;
                    }
                    //弹出支付选择框
                    $('.pop_box').show();
                    $('.pay_pop').show();
                    $('#pay_type').val(2);
                    //保存订单号和输入的金额
                    $('#select_orderid').val(id);
                    $('#tip_money').val(e.value);
                }
            })
        },
        addTipBalance: function () {
        	var orderId = $('#select_orderid').val();
        	var tip_money = $('#tip_money').val();
        	this.$http.get(createUrl(this.apiAddTipUrl, {userid: list1.userId, pay_type:3, orderid:orderId, count:tip_money}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    mui.toast(response.data.m);
                }else{
                	mui.toast(response.data.m);
                }
            })
        },
        addTipWx: function () {
        	var orderId = $('#select_orderid').val();
        	var tip_money = $('#tip_money').val();
        	var openId = getItem('openid');
        	this.$http.get(createUrl(this.apiAddTipUrl, {userid: list1.userId, pay_type:4, orderid:orderId, count:tip_money,openid:openId}, true))
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
        //多人预约的时候接单人数不足继续预约
        comfirmMultiOrder: function (id) {
            this.$http.get(createUrl(this.apicomfirmMultiOrder, {userid: userId,orderid:id}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        mui.toast(response.data.m);
                        window.location.reload();
                    } else {
                    	mui.toast(response.data.m);
                    }
                })
        },
        //确认长期工工资和预约时间
        comfirmWages:function (id) {
            this.$http.get(createUrl(this.apiComfirmWages, {userid: userId, orderid:id}, true))
                .then(function(response) {
                	if (response.data.c == 1) {
                        mui.toast(response.data.m);
                        window.location.reload();
                    } else {
                    	mui.toast(response.data.m);
                    }
                });
        },
        //联系客服
        goServer:function (id) {
            window.location.href='https://ikefu.baidu.com/wise/zk';
        }
    }
});
var list2 = new Vue({
    el: '#list2',
    data: {
        apiUrl: '/order/orderList',
        orderlists:[], page:1, havemove:0
    },
    emulateJSON: true,
    mounted: function() {
        this.$http.get(createUrl(this.apiUrl, {userid: userId, type:2, page: this.page}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    this.orderlists = jsonData.orderlists;
                    this.havemore = jsonData.havemore;
                    this.page += 1;
                }
            })
    },
    methods: {
        cancelOrder: function (id) {
            setItem('order_cancel_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/back'
        },
        detailOrder: function (id) {
            setItem('order_detail_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/detail'
        },
        //联系客服
        goServer:function (id) {
            window.location.href='https://ikefu.baidu.com/wise/zk';
        },
        //投诉订单
        complainOrder: function (id) {
            setItem('order_complain_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/complain'
        },
        payMoney:function (id,serid) {
            setItem('order_pay_id', id);
            setItem('order_pay_sid', serid);
            window.location.href = '../auth/go?uri=/wechat/pay/pay_total'
        },
        payMoneyMonth:function (id,serid) {
            setItem('order_pay_id', id);
            setItem('order_pay_sid', serid);
            window.location.href = '../auth/go?uri=/wechat/pay/pay_total'
        }
    }
});
var list3 = new Vue({
    el: '#list3',
    data: {
        apiUrl: '/order/orderList',
        orderlists:[], page:1, havemove:0,
        apiCancelOrder: '/order/cancel',//退单确认
        apiComfirmOrder:'/order/comfirmOrder',//确认
        apiComfirmWages:'/order/comfirmWages',//继续预约
    },
    emulateJSON: true,
    mounted: function() {
        this.$http.get(createUrl(this.apiUrl, {userid: userId, type:3, page: this.page}, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    this.orderlists = jsonData.orderlists;
                    this.havemore = jsonData.havemore;
                    this.page += 1;

                }
            })
    },
    methods: {
        //退单
        cancelOrder:function (id) {
            this.$http.get(createUrl(this.apiCancelOrder, {userid: userId, orderid:id}, true))
                .then(function(response) {
                    if(response.data.m == 1){
                        setItem('order_cancel_id', id);
                        window.location.href='./back.html'
                    }else {
                        mui.toast(response.data.m);
                    }
                });
        },
        //确认订单(维修类)
        comfirmOrder:function (id) {
            this.$http.get(createUrl(this.apiComfirmOrder, {userid: userId, orderid:id}, true))
                .then(function(response) {
                	mui.toast(response.data.m);
                	if(response.data.c == 1){
                		window.location.reload();
                    }else {
                        mui.toast(response.data.m);
                    }
                });
        },
        //确认长期工工资和预约时间
        comfirmWages:function (id) {
            this.$http.get(createUrl(this.apiComfirmWages, {userid: userId, orderid:id}, true))
                .then(function(response) {
                    mui.toast(response.data.m);
                });
        },
        //联系客服
        goServer:function (id) {
            window.location.href='https://ikefu.baidu.com/wise/zk';
        },
        handleOrder: function (id) {
            setItem('order_handle_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/handle'
        },
        complainOrder: function (id) {
            setItem('order_complain_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/complain'
        },
        detailOrder: function (id) {
            setItem('order_detail_id', id);
            window.location.href = '../auth/go?uri=/wechat/order/detail'
        },
        //评论订单
        commentOrder: function (id) {
        	setItem('order_comment_id', id);
            window.location.href='../auth/go?uri=/wechat/order/handle';
        }
    }
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
        				    
                           /*  this.canBookCompanyList = addShow(jsonData.companyList);
                            console.log(888)
                            console.log(this.canBookCompanyList) */
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

function payDeposit(type){
	$('.pop_box').hide();
	$('.pay_pop').hide();
	var paytype =  $('#pay_type').val();
	if(paytype == 1){//定金
		if(type == 1){
			list1. payOrderBalance();
		}else{
			list1. payOrderWx();
		}
	}else{
		if(type == 1){
			list1. addTipBalance();
		}else{
			list1. addTipWx();
		}
	}
}
</script>
</body>
</html>