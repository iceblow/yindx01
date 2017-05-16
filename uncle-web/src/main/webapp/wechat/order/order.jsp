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
        .order_tab a{color: #cfe2ff !important}
        .order_tab{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);padding: 5px !important}
        .order_tab .active{color: #fff !important;border-color: #ffffff !important;}
        .card{border-top: 2px solid #eee}
        .order_status{color: #959495;font-size: 13px;}
        .order_status_img{height: 12px;position: absolute;top: 18px;right: 5px}
        .order-header{padding: 15px}
        .order_name{font-size: 16px}
        .order-content{padding: 0 15px 15px 15px ;}
        .order_text{border-bottom: 1px solid #eee;padding-bottom: 10px}
        .order_btn{display: inline-block;margin-left: 10px}
        .order-footer{padding:0 15px 15px 15px ;vertical-align: middle}
        .order_pos{margin-bottom: 10px}
    </style>
</head>
<body>

<div class="page-group" id="order_page">
    <!-- 你的html代码 -->
    <div id="page-tab" class="page">
        <div class="bar bar-standard bar-footer">
            <a class="icon icon-edit pull-left"></a>
        </div>
        <div class="content" style="background-color: #ffffff">
            <div class="buttons-tab order_tab">
                <a href="#tab1" class="tab-link active button">未发单</a>
                <a href="#tab2" class="tab-link button">未接单</a>
                <a href="#tab3" class="tab-link button">未出发</a>
                <a href="#tab4" class="tab-link button">服务中</a>
                <a href="#tab5" class="tab-link button">已完结</a>
            </div>

            <div class="tabs">
                <div id="tab1" class="tab active">
                    <div class="card">
                        <div class="order-header">
                            <span class="order_name">居家保洁</span>
                            <label class="pull-right">
                                <span class="order_status">待付款</span>
                                <img class="order_status_img" src="../img/serve/right_go.png" />
                            </label>
                        </div>
                        <div class="order-content">
                            <div class="order_text">
                                <div class="order_pos order_status">
                                    <img src="../img/serve/positioning.png" height="12" />
                                    上门时间地点地点
                                </div>
                                <div class="order_time order_status">
                                    <img src="../img/serve/positioning.png" height="12" />
                                    上门时间地地点上门时间地点地点上点
                                </div>
                            </div>
                        </div>
                        <div class="order-footer">
                            <span>2015/01/15</span>
                            <a class="button order_btn pull-right" >支付</a>
                            <a class="button order_btn pull-right" >退单</a>
                        </div>
                    </div>
                </div>
                <div id="tab2" class="tab">
                    <div class="content-block">
                        <p>This is tab 2 content</p>
                    </div>
                </div>
                <div id="tab3" class="tab">
                    <div class="content-block">
                        <p>This is tab 3 content</p>
                    </div>
                </div>
                <div id="tab4" class="tab">
                    <div class="content-block">
                        <p>This is tab 3 content</p>
                    </div>
                </div>
                <div id="tab5" class="tab">
                    <div class="content-block">
                        <p>This is tab 3 content</p>
                    </div>
                </div>
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
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
    $(document).on("pageInit", "#user_info", function(e, pageId, $page) {
        console.log(111)
    });
    var demo = new Vue({
        el: '#order_page',
        data: {
            apiUrl: '/order/orderList',
            adList: [{name:'测试循环'}],
            auntList: [{name:'测试循环'}],
            bannerList: [{name:'测试循环'}],
            homeList: [{name:'测试循环'}],
            iconList: [{name:'居家保洁'}],
            mediumList: [{name:'测试循环'}],
            repairList: [{name:'测试循环'}]
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers()
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {userid:userId,type:1,page:1}, false))
                        .then(function(response) {
                            console.log(response.data);
//                        var jsonData = JSON.parse(response.data.r);
//                        console.log(jsonData);
                            this.bannerList = [{url:'../img/home/pic_home_slider_4.jpg'}, {url:'../img/home/pic_home_slider_4.jpg'},{url:'../img/home/pic_home_slider_1.jpg'}];
                            this.adList = [{'url':'../img/home/pic_home_slider_4.jpg'}, {'url':'../img/home/pic_home_slider_1.jpg'}, {url:'../img/home/pic_home_slider_1.jpg'}];
//                        console.log(this.bannerList)

                        })

            }
        }
    });
    $.init();
</script>
</body>
</html>
