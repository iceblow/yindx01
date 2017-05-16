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
    </style>
</head>
<body>
<div class="page-group">
    <div class="page" id="add_address">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">服务分类</h1>
        </header>
        <div class="content" style="background-color: white">
            <div class="item_box" v-for="service in list">
                <div class="line_title">
                    <img src="../img/serve/t_line.png" />
                    <h4 class="line_title_name" v-cloak>{{service.name}}</h4>
                </div>
                <div class="item_list" v-for="sub in service.secondList">
                    <a :id="sub.id" :href="urlList[sub.id]" external>
                        <div class="item_text_box">
                            <img src="../img/serve/s_1.png" :src="sub.picurl"/>
                            <div class="item_text">
                                <h4 class="serve_title">{{sub.name}}</h4>
                                <p class="serve_text">{{sub.profile}}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<!--sui-ui-->
<script>var Zepto = jQuery</script>
<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.2.1/vue-resource.min.js"></script>
<script>
    setItem('server_address_name', '');
    setItem('server_address_address', '');
    setItem('server_address_id', '');
    //    setItem('fast_state',1);
    //判断是否为快速下单
    var judge=getItem('judge');

    var fast;
    if(judge == 0){
        fast=0;
    }else if(judge == 1){
        fast=getItem('fast_state');
    }
    $.init();
    var server = new Vue({
        el: '#add_address',
        data: {
            apiUrl: '/order/getServerCategory',
            list: [],
            urlList: {
                1:'../auth/go?uri=/wechat/serves/daily',
                2:'../auth/go?uri=/wechat/serves/cachuang',
                3:'../auth/go?uri=/wechat/serves/qingxi',
                4:'../auth/go?uri=/wechat/serves/zuofan',
                5:'../auth/go?uri=/wechat/serves/yanhui',
                6:'../auth/go?uri=/wechat/serves/jiuxi',
                7:'../auth/go?uri=/wechat/serves/piju',
                8:'../auth/go?uri=/wechat/serves/jiachang',
                9:'../auth/go?uri=/wechat/serves/xielei',
                10:'../auth/go?uri=/wechat/serves/jiadian',
                11:'../auth/go?uri=/wechat/serves/guandao',
                12:'../auth/go?uri=/wechat/serves/shuidian',
                13:'../auth/go?uri=/wechat/serves/kaisuo',
                14:'../auth/go?uri=/wechat/serves/jujia',
                15:'../auth/go?uri=/wechat/serves/yuesao',
                16:'../auth/go?uri=/wechat/serves/yuersao',
                17:'../auth/go?uri=/wechat/serves/peihu',
                18:'../auth/go?uri=/wechat/serves/bingren',
            }
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers()
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {city:getItem('service_city')}, false))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.list = jsonData.firstList;
                            console.log(this.list)
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            goPage:function (a) {
                console.log(this.list)
                var url= {
                    17:'../serves/peihu.jsp'
                };
                window.location.href=url[a];
            }
        }
    });
    if(fast == 1){
        $.alert('该地区不支持快速下单', function () {
            window.location.href='../auth/go?uri=/wechat/home/home';
        });
    }
</script>
</body>
</html>

