<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>家政公司</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="../js/mui/css/mui.min.css">
       <style>
           p{
               margin: 0;
           }
           .my_head{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);}
           .list-block .item-title{white-space: normal}
           .uncle_img{border-radius: 50%;height: 50px}
           .item-after{padding-right: 20px}
           .list_search ul:after{
               content:".";
               display:block;
               height:0;
               clear:both;
               visibility:hidden;
           }
           .list_search ul{padding: 0;margin: 0;padding: 15px 0;}
           .list_search ul li{list-style: none;float:left;width: 33%;font-size: 14px;text-align: center;border-right: 1px solid #e6e6e6}
           .list_search ul li img{margin-left: 5px}
           .list_search ul li:nth-child(3){
               border-right: none;
           }
           /*列表样式*/
           .my_list:after{
               content:".";
               display:block;
               height:0;
               clear:both;
               visibility:hidden;
           }
           .list_box{padding: 15px;padding-top: 0}
           .my_list{padding-bottom: 15px;font-size: 14px;padding-top: 15px}
           .my_list div{float: left;}
           .my_list .list_title{width: 40%;color: #959495}
           .my_list .list_text{width: 60%;padding-left: 20px}
           .list_title img{width: 100%}
           .list_text_title{font-size: 15px;font-weight: 700}
           .list_text_sub{font-size: 13px;color: #959495}
           .my_list{border-bottom: 1px solid #e6e6e6}
           .detail_btn_box div{float: left;width: 50%;padding-right: 15px;padding-top: 15px;}
           .detail_btn_box div a{height: 30px ;line-height: 30px;width: 90px;margin: 0 auto}
           .btn_left{color: #5d92f9}
           .btn_right{background-image:linear-gradient(to right, #78c6ff, #5d8ff9);color: white }
           .head_slide_main:after{
               content:".";
               display:block;
               height:0;
               clear:both;
               visibility:hidden;
           }
           .detail_btn_box:after{
               content:".";
               display:block;
               height:0;
               clear:both;
               visibility:hidden;
           }
           .head_slide{height: 380px;overflow: scroll}
           .l-3{float: left;width: 33%;text-align: center;padding: 0 10px;margin-top: 15px}
           .mui-bar .mui-btn{top: 0;width: 100%;background-color: #f6f9f8;border: 1px solid #f6f9f8}
           .btn_active{border: 1px solid #5e93f9!important;box-sizing: border-box !important;background-color: white !important;color: #5e93f9}
           .head_slide_title{margin-top: 15px;padding-left: 15px}
           .head_slide_main{padding-bottom: 15px}
           .btn_left,.btn_right{width: 100px}
           .up_arr{font-size: 13px;text-align: right;padding-right: 15px;border-bottom: 1px solid #eee}
           .up_arr .mui-icon{font-size: 15px !important;}
           .address_btn{max-width: 100%;font-size: 12px;overflow: hidden;text-overflow: ellipsis;}
           .mui-pull-top-pocket{ padding-top: 90px}
       </style>
</head>
<body>
<header class="mui-bar mui-bar-nav my_head money_head" id="header">
    <!-- <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" style="color: white"></a>
    <a class="button button-link button-nav mui-pull-right">
        <span class="mui-icon mui-icon-search" style="color: white"></span>
    </a> -->
    <h1 class="mui-title" style="color: white">家政公司</h1>
</header>
<!--下拉刷新容器-->
<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="padding-top: 96px">
    <div class="mui-scroll" >
        <!--数据列表-->
        <ul class="mui-table-view">
            <li class="mui-table-view-cell mui-media" style="" v-for="company in companyList" v-cloak :data-id="company.companyid">
                <a href="javascript:;">
                    <img class=" mui-pull-left" style="height: 110px;width: 120px" src="../img/home/list1.png" :src="company.picurl">
                    <div class="mui-media-body" style="padding-left: 15px" v-cloak>
                        {{company.name}}
                        <p class='mui-ellipsis' v-cloak>{{company.profile}}</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
</div>
<nav class="mui-bar mui-bar-tab" style="top: 45px;;background-color: white" id="search">
    <div class="list_search">
        <ul>
            <li class="slide_1">附近<img src="../img/serve/treasure_filter_normal.png" height="5px"/> </li>
            <li class="slide_2">名称<img src="../img/serve/treasure_filter_normal.png" height="5px"/> </li>
            <li class="slide_3">筛选<img src="../img/serve/treasure_filter_normal.png" height="5px"/> </li>
        </ul>
    </div>
    <div class="slide_distance head_slide" style="display: none">
        <p class="head_slide_title">按距离筛选</p>
        <div class="head_slide_main">
            <div class="head_slide_btn l-3">
                <button type="button" class="mui-btn distance_btn btn_active" v-on:click="setDistance(0, 10, $event)">默认</button>
            </div>
            <div class="head_slide_btn l-3">
                <button type="button" class="mui-btn distance_btn" v-on:click="setDistance(0, 10, $event)">0-10km</button>
            </div>
            <div class="head_slide_btn l-3">
                <button type="button" class="mui-btn distance_btn" v-on:click="setDistance(10, 20, $event)">10km-20km</button>
            </div>
            <div class="head_slide_btn l-3">
                <button type="button" class="mui-btn distance_btn" v-on:click="setDistance(20, 30, $event)">20km-30km</button>
            </div>
            <div class="head_slide_btn l-3">
                <button type="button" class="mui-btn distance_btn" v-on:click="setDistance(30, 40, $event)">30km-40km</button>
            </div>
            <div class="head_slide_btn l-3">
                <button type="button" class="mui-btn distance_btn" v-on:click="setDistance(50, 0, $event)">50km以上</button>
            </div>
        </div>
        <p class="head_slide_title">按服务地址筛选</p>
        <div class="head_slide_main slide_add">
            <div style="padding: 15px;padding-bottom: 0">
                <button class="button address_btn btn_active" v-on:click="set_add_curr($event)"><span>当前地址</span></button>
            </div>
            <div style="padding: 15px;padding-bottom: 0;" v-if="addressList.length != 0" v-for="add in addressList">
                <button class="button address_btn" v-on:click="set_add(add.latitude,add.longitude,$event)">{{add.addressname}}</button>
            </div>
        </div>
    </div>
    <div class="slide_name head_slide" style="display: none">
        <div class="head_slide_main s_name">
            <div class="head_slide_btn l-3 " v-for="(char,index) in chars">
                <button class="button mui-btn name_btn btn_active" v-if="index==0" v-on:click="setName('', $event)">{{char}}</button>
                <button class="button mui-btn name_btn" v-else="index==0" v-on:click="setName(char, $event)">{{char}}</button>
            </div>
        </div>
    </div>
    <div class="slide_chose head_slide" style="display: none;">
        <p class="head_slide_title">按评价筛选</p>
        <div class="head_slide_main ">
            <div class="head_slide_btn l-3 ">
                <button class="button mui-btn comment_btn btn_active" v-on:click="setComment(0, $event)">默认</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn comment_btn" v-on:click="setComment(1, $event)">好评</button>
            </div>
        </div>
        <p class="head_slide_title">按服务类型筛选</p>
        <div class="head_slide_main " style="padding-bottom: 0">
            <div class="head_slide_btn l-3 my_chose" style="display: none"></div>
            <div class="head_slide_btn l-3 ">
                <button class="button mui-btn server_btn btn_active" v-on:click="setServer(0, $event)">默认</button>
            </div>
            <div class="head_slide_btn l-3" v-for="(server,i) in serverList" v-if="i<=3">
                <button class="button mui-btn server_btn" v-on:click="setServer(server.id, $event)">{{server.name}}</button>
            </div>
        </div>
        <div class="head_slide_main hide_serve" style="display: none">
            <div class="head_slide_btn l-3" v-for="(server,i) in serverList" v-if="i>3">
                <button class="button mui-btn server_btn" v-on:click="setServer(server.id, $event)">{{server.name}}</button>
            </div>
        </div>
        <p class="up_arr">
            <span class="up_arr_span" style="display: none  " v-on:click="serverMore()">收起<span class="mui-icon mui-icon-arrowup"></span></span>
            <span class="down_arr_span" v-on:click="serverMore()">展开<span class="mui-icon mui-icon-arrowdown"></span></span>
        </p>
        <p class="head_slide_title">按单位头衔筛选</p>
        <div class="head_slide_main ">
            <div class="head_slide_btn l-3 ">
                <button class="button mui-btn title_btn btn_active" v-on:click="setTitle(0, $event)">默认</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn title_btn" v-on:click="setTitle(1, $event)">会长</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn title_btn" v-on:click="setTitle(2, $event)">常务副会长</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn title_btn" v-on:click="setTitle(3, $event)">副会长</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn title_btn" v-on:click="setTitle(4, $event)">理事</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn title_btn" v-on:click="setTitle(5, $event)">会员</button>
            </div>
        </div>
        <p class="head_slide_title">按星级筛选</p>
        <div class="head_slide_main ">
            <div class="head_slide_btn l-3 ">
                <button class="button mui-btn start_btn btn_active" v-on:click="setStart(0, $event)">默认</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn start_btn" v-on:click="setStart(1, $event)">1星</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn start_btn" v-on:click="setStart(2, $event)">2星</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn start_btn" v-on:click="setStart(3, $event)">3星</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn start_btn" v-on:click="setStart(4, $event)">4星</button>
            </div>
            <div class="head_slide_btn l-3">
                <button class="button mui-btn start_btn" v-on:click="setStart(5, $event)">5星</button>
            </div>
        </div>
        <div class="detail_btn_box">
            <div style="text-align: center;padding-bottom: 15px">
                <button class="button btn_left">
                    取消
                </button>
            </div>
            <div style="text-align: center;padding-bottom: 15px">
                <button class="button btn_right" v-on:click="search">确定</button>
            </div>
        </div>
    </div>

</nav>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="../js/mui/js/mui.min.js"></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
    var lat=getItem('user_lat');
    var lng=getItem('user_lng');
    var list = new Vue({
        el: '#pullrefresh',
        data: {
            apiUrl: '/home/companyList',
            companyList:[], havemore:0,
            parameter: {
                companytype:0, screentype:0, longitude:getItem('user_lng'), latitude:getItem('user_lat'), distance_from:0, distance_to:10, name_letter:'',
                comment_type:0, servertype:0, titletype:0, startype:0, page:1
            }
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers();
        },
        methods: {
            getCustomers: function() {
                this.parameter.page = 1;
                this.$http.get(createUrl(this.apiUrl, this.parameter, false))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.companyList = jsonData.companyList;
//                            console.log(jsonData)
                            this.havemore = jsonData.havemore;
                            this.parameter.page += 1;
                        } else {
//                            $.toast(response.data.m);
                        }
                    })
            },

        }
    });
    var search = new Vue({
        el: '#search',
        data: {
            apiGetAddressUrl: '/user/getAddressList',
            apiGetServerCategoryUrl: '/order/getServerCategory',
            addressList:[], serverList:[],
            address: getItem('user_address'),
            chars: ['默认','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
        },
        emulateJSON: true,
        mounted: function() {
            this.$http.get(createUrl(this.apiGetAddressUrl, {userid: userId, categoryid: 0}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        this.addressList = jsonData.addressList;
                        console.log(this.addressList)
                    } else {
                        console.log(response.data.m)
                    }
                });
            this.$http.get(createUrl(this.apiGetServerCategoryUrl, {}, false))
                .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        for(var i=0; i<jsonData.firstList.length; i++) {
                            for(var x=0; x<jsonData.firstList[i].secondList.length; x++) {
                                this.serverList.push(jsonData.firstList[i].secondList[x])
                            }
                        }
                    } else {
                        console.log(response.data.m)
                    }
                })
        },
        methods: {
            setDistance: function (from, to, e) {
                list.distance_from = from;
                list.distance_to = to;
                list.screentype = 0;
                $('.distance_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active')

                list.getCustomers();
            },
            setName: function (char, e) {
                list.name_letter = char;
                list.screentype = 1;
                $('.name_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active')

                list.getCustomers();
            },
            setComment: function (type, e) {
                list.comment_type = type;
                $('.comment_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active')
            },
            setServer: function (type, e) {
                list.servertype = type;
                $('.server_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active');
            },
            set_add_curr:function (e) {
                $('.address_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active');
                list.getCustomers();
            },
            set_add:function (lat,lng,e) {
                $('.address_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active');
                list.parameter.latitude=lat;
                list.parameter.longitude=lng;
                list.getCustomers();
            },
            setTitle: function (type, e) {
                list.titletype = type;
                $('.title_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active')
            },
            setStart: function (type, e) {
                list.startype = type;
                $('.start_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active')
            },
            serverMore:function () {
                $('.hide_serve').slideToggle();
                $('.down_arr_span').toggle();
                $('.up_arr_span').toggle();

            },
            search: function () {
                list.screentype = 2;
                list.getCustomers();
            }
        }
    });
    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            down: {
                callback: pulldownRefresh
            },
            up: {
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });
    mui('.mui-scroll').on('tap','li',function(){
        setItem('company_id', $(this).data('id'));
        console.log($(this).data('id'));
        mui.openWindow({
            url:'../auth/go?uri=/wechat/company/detail',
            id:'detail.jsp'
        });
    });
    /**
     * 下拉刷新具体业务实现
     */
    function pulldownRefresh() {
        list.parameter.page = 1;
        list.$http.get(createUrl(list.apiUrl, list.parameter, false))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    list.companyList = jsonData.companyList;
                    list.havemore = jsonData.havemore;
                    list.parameter.page += 1;
                } else {

                }
                mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
            });
    }
    var count = 0;
    /**
     * 上拉加载具体业务实现
     */
    function pullupRefresh() {
        list.$http.get(createUrl(list.apiUrl, list.parameter, false))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    list.companyList = jsonData.companyList;
                    list.havemore = jsonData.havemore;
                    list.parameter.page += 1;
                } else {

                }
                mui('#pullrefresh').pullRefresh().endPullupToRefresh(!list.havemore);
            });
    }

    $('.slide_1').click(function () {
        $('.slide_name ,.slide_chose').hide()
        $('.slide_distance').slideToggle();

    });
    $('.slide_2').click(function () {
        $('.slide_distance ,.slide_chose').hide()
        $('.slide_name').slideToggle();

    });
    $('.slide_3').click(function () {
        $('.slide_distance ,.slide_name').hide()
        $('.slide_chose').slideToggle();

    });
</script>

</body>
</html>