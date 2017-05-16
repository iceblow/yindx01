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
    <!--<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">-->
    <!--<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/??sm.min.css,sm-extend.min.css">-->

    <link rel="stylesheet" href="../js/mui/css/mui.min.css">
    <style>
        p{
            margin: 0;
        }
        .my_head{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);height: 130px}
        .sign_btn img{height: 100%}
        .phone_input a{height: 40px;position: absolute;right: 15%;top: 30px;border-radius: 0;line-height: 40px;border: none;color: #333}
        .phone_input input{width: 70%;border: 1px solid #d5d7e3;height: 40px;margin-left: 15%;}
        input{padding-left: 10px!important;font-size: 16px}
        /*.money_box{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);border-top: none}*/
        .money_box{width: 100%;margin-top: 45px}
        .money_head:after{background-color: transparent !important}
        .money_number h1{margin: 0;color: white}
        .money_number{color: white;vertical-align: middle;padding-bottom: 25px;height: 90px}
        .detail_title{line-height: 20px;font-size: 14px;color: #a6a6a6;padding: 15px}
        .detail_text{margin-top: 0}
        .balance_img img{height: 30px}
        .balance_title{font-size: 16px;color: #333}
        .balance_time{font-size: 14px;color: #959495}
        .add_text{font-size: 16px;color: #5e93f9;font-weight: 700;display: block;position: absolute;top: 21px;right: 15px}
        .reduce_text{font-size: 16px;color: #333;font-weight: 700}
    </style>
</head>
<body>

<header class="mui-bar mui-bar-nav my_head money_head" id="my_point">
    <!-- <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
    <h1 class="mui-title" style="color: white">我的积分</h1>
    <div class="money_box">
        <div class="money_number">
            <!--<h1 class="money_detail">10000</h1>-->
            <h1 class="money_detail" v-cloak>{{point}}</h1>
            <span style="font-size: 14px;">当前积分</span>
        </div>
    </div>
</header>
<div class="mui-content mui-scroll-wrapper" style="background-color: #ffffff;padding-top: 134px; " id="pullrefresh">
    <div class="mui-scroll" id="list" >
        <div class="detail_title">积分记录</div>
        <ul class="mui-table-view">
            <li class="mui-table-view-cell" v-for="point in pointList">
                <p class="balance_title" v-cloak>{{point.title}}</p>
                <p class="balance_time" v-cloak>{{point.time}}</p>
                <span class="add_text mui-pull-right" v-cloak>
                    <span v-if="point.type == 0 || point.type == 1 || point.type == 2 || point.type == 3 || point.type == 4 || point.type == 5 || point.type == 6 ||  || point.type == 8">
                    +</span>
                    <span v-else="point.type == 7">
                    -</span> {{point.count}}
                </span>
            </li>
        </ul>
    </div>
</div>

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>var Zepto = jQuery</script>
<!--sui-ui-->
<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
<!--<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>-->
<!--<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>-->

<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>

<script src="../js/mui/js/mui.min.js"></script>
<script>
    var list = new Vue({
        el: '#list',
        data: {
            pointList: [],point: 0,
        }
    });

    var point = new Vue({
        el: '#my_point',
        data: {
            apiUrl: '/user/getPointList',
            pointList: [], point: 0, havemore: 0,
            page: 1
        },
        mounted: function() {
            this.getCustomers();
        },
        methods: {
            getCustomers: function () {
                this.$http.get(createUrl(this.apiUrl, {userid: userId, page: this.page}, true))
                        .then(function(response) {
                            if (response.data.c == 1) {
                                var jsonData = JSON.parse(response.data.r);
                                if (this.page == 1) {
                                    this.point = jsonData.point;

                                }
                                list.pointList = jsonData.pointList;
                                list.havemore = 1;
                            } else {
                            	$.toast(response.data.m);
                            }
                        })
            },
        }
    });

    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            up: {
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });

    /**
     * 上拉加载具体业务实现
     */
    function pullupRefresh() {
        setTimeout(function() {
            mui('#pullrefresh').pullRefresh().endPullupToRefresh(!point.havemore); //参数为true代表没有更多数据了。
        }, 1500);
    }
</script>
</body>
</html>
