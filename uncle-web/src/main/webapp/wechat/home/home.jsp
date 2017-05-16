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
    <link href="http://www.jq22.com/jquery/bootstrap-3.3.4.css" rel="stylesheet">
    <link href="../css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/bottom.css" />
    <link rel="stylesheet" href="../css/home.css" />

    <!--sui-ui-->
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/??sm.min.css,sm-extend.min.css">
    <style>
        p {
            margin: 0;
        }
        .col-50 {height: 100%}
        .my_head {background-image: linear-gradient(to right, #78c6ff, #5d8ff9);}
        .my_ad {color: white}
        .address_list {line-height: 2.2rem}
        .bar-tab {background-color: #ffffff}
        .swiper-container {height: 9rem}
        .second_icon_box:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .home_picture {background-color: white;padding:15px;}
        .home_picture img{width: 100%}
        .first{padding-bottom: 0!important;}
        .second{padding-top: 0!important;}
        .cont_container{margin-top: 15px; margin-bottom: 15px;}
        /*弹窗*/
        .my_alert{position: absolute;z-index: 9999;width: 100%;height: 100%;background: rgba(0,0,0,0.5)}
        .alert_box{
            border-radius: 10px;
            width: 215px;
            height: 280px;
            position: absolute;
            top:50%;
            left: 50%;
            -webkit-transform: translate(-50%,-50%);
            background-image: url(../img/home/home_alert.png);
            background-size: 100% 100%;
            /*overflow: hidden;*/
        }
        .alert_head{width: 100%;height: 65px;padding: 0 15px;padding-top: 20px}
        .company_btn_box :after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .sign_alert_head_text{font-size: 13px}
        .sign_alert_head_title{font-size: 16px}
        .close_alert{width: 35px;position: absolute;bottom: -60px;left: 89px}
        .alert_money{margin-top: 24px;height: 60px;padding: 0 15px}
        .alert_money div{float: left;padding-top: 5px}
        .alert_left{width: 100px;height: 100%;}
        .alert_right{width: 80px;height: 100%}
        .alert_time{font-size: 13px;color: #959495}
        .alert_t{color: #ff4c65;font-size: 13px}
        .alert_m{color: #ff4c65;font-size: 30px}
        .home_alert_btn{background-color: white;border: none;height: 40px;width: 145px;position: absolute;bottom: 45px;left: 50%;margin-left: -73px;line-height: 40px}
        .home_alert_tips{position: absolute;width: 100%;bottom: 5px;font-size: 15px;color: white}
        .star{float: left;width: 15px;height: 15px;background-image: url(../img/serve/star.png);background-size: 100% 100%}
        .star_s{background-image: url("../img/serve/star_s.png") !important;}
        .star_half{background-image: url('../img/serve/h_star.png')}
    </style>
</head>
<body>

<div class="page-group" id="page-group">
    <div class="page page-current" id="home">
        <header class="bar bar-nav my_head">
            <div class="row">
                <div class="col-50 address_list">
                    <a href="../auth/go?uri=/wechat/address/address" class="my_ad" external>诸暨市</a>
                    <img src="../img/home/down_arr.png" class="downArr">
                </div>
                <div class="col-50" style="margin-left: 0;position: relative">
                    <img src="../img/home/cloud.png" class="cloud">
                    <a href="../auth/go?uri=/wechat/message/new_list" external><img src="../img/home/bell.png" class="bell"></a>
                </div>
            </div>
        </header>
        <div class="my_alert" style="display: none">
            <div class="alert_box">
                <div class="alert_head ">
                    <p class="sign_alert_head_title text-center" style="color: white;">新手红包</p>
                    <p class="sign_alert_head_text text-center" style="color: white;margin-top: 0;">现代都市生活新方式</p>
                </div>
                <div class="alert_money">
                    <div class="alert_left">
                        <p>满20元使用</p>
                        <p class="alert_time">12.23-12.26有效</p>
                    </div>
                    <div class="alert_right">
                        <span class="alert_t">￥</span><span class="alert_m">10.0</span>
                    </div>
                </div>
                <img class="close_alert" onclick="closeSign()" src="../img/user/close.png" />
                <a class="button home_alert_btn">去使用</a>
                <p class="home_alert_tips text-center">表叔云服全平台通用</p>
            </div>
        </div>
        <nav class="bar bar-tab">
            <a class="tab-item external active" href="../auth/go?uri=/wechat/home/home">
                <span class="icon">
                    <img src="../img/home/home_select_icon.png" class="bar_icon">
                </span>
                <span class="tab-label">首页</span>
            </a>
            <a class="tab-item external" href="../auth/go?uri=/wechat/serves/home" v-on:click="goFast()">
                <span class="icon">
                    <img src="../img/home/sort_unselect_icon.png" class="bar_icon">
                </span>
                <span class="tab-label">快速下单</span>
            </a>
            <a class="tab-item external" href="../auth/go?uri=/wechat/order/home">
                <span class="icon">
                    <img src="../img/home/order_unselect.png" class="bar_icon">
                </span>
                <span class="tab-label">订单</span>
            </a>
            <a class="tab-item external" href="../auth/go?uri=/wechat/user/home">
                <span class="icon">
                    <img src="../img/home/mine_unselect.png" class="bar_icon">
                </span>
                <span class="tab-label">我的</span>
            </a>
        </nav>
        <div class="content">
            <!-- 这里是页面内容区 -->
            <div class="page-index" >
                <div class="swiper-container" data-autoplay="2000">
                    <div class="swiper-wrapper" id="swiper-wrapper">
                        <div v-for="banner in bannerList" class="swiper-slide"><img :src="banner.picurl" alt="" width="100%"/></div>
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
                <div class="first_icon" id="abd" id="iconList">
                    <div class="childicon" v-for="(icon,i) in iconList" v-on:click="goUrl(icon.content)">
                        <img src="../img/home/more_home.png" class="shelf_more" v-if="i==7">
                        <img src="../img/home/shelf.png" :src="icon.picurl" class="shelf" v-else="i==7">
                        <div class="my_title">{{icon.name}}</div>
                    </div>
                </div>
                <div class="home_picture first" v-if="adList[0]">
                    <img :src="adList[0].picurl" width="100%"/>
                </div>
                <div class="second_icon" >
                    <div class="second_icon_box">
                        <div class="sec_part" >
                            <a style="display: block;height:100%;color: #333" href="../auth/go?uri=/wechat/company/list" external>
                                <div class="sev_text_cont">
                                    <p class="sev_title">家电维修</p>
                                    <p class="sev_intro">服务到家最专业</p>
                                    <img src="../img/home/rightarr_gray.png" class="rightarr_gray">
                                </div>
                                <div class="sev_pic_cont">
                                    <img src="../img/home/service1.png" class="service1">
                                </div>
                            </a>
                        </div>
                        <div class="sec_part" >
                            <a style="display: block;height:100%;color: #333" href="../auth/go?uri=/wechat/company/list" external>
                                <div class="sev_text_cont">
                                    <p class="sev_title">家政公司</p>
                                    <p class="sev_intro">服务到家最专业</p>
                                    <img src="../img/home/rightarr_gray.png" class="rightarr_gray">
                                </div>
                                <div class="sev_pic_cont">
                                    <img src="../img/home/service2.png" class="service2">
                                </div>
                            </a>
                        </div>
                        <div class="sec_part" >
                            <a style="display: block;height:100%;color: #333" href="../auth/go?uri=/wechat/company/list">
                                <div class="sev_text_cont">
                                    <p class="sev_title">中介公司</p>
                                    <p class="sev_intro">贴心有保障</p>
                                    <img src="../img/home/rightarr_gray.png" class="rightarr_gray">
                                </div>
                                <div class="sev_pic_cont">
                                    <img src="../img/home/service4.png" class="service4">
                                </div>
                            </a>
                        </div>
                        <div class="sec_part" >
                            <a style="display: block;height:100%;color: #333" href="../auth/go?uri=/wechat/company/uncle_list" external>
                                <div class="sev_text_cont">
                                    <p class="sev_title">个体阿姨</p>
                                    <p class="sev_intro">预约上门很方便</p>
                                    <img src="../img/home/rightarr_gray.png" class="rightarr_gray">
                                </div>
                                <div class="sev_pic_cont">
                                    <img src="../img/home/service3.png" class="service3">
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="home_ad second" id="abde" v-if="adList[1]">
                    <img  :src="adList[1].picurl" width="100%"/>
                </div>
                <div class="cont_container">
                    <ul class="third_part">
                        <li id="housekeeping" class="third_list">家政</li>
                        <li id="inter_agent" class="third_list">中介</li>
                        <li id="person" class="third_list">个人</li>
                        <li id="repair" class="third_list">维修</li>
                    </ul>
                    <div class="list1_container">
                        <div class="list" v-for="home in homeList">
                            <div class="list_piccont">
                                <img src="../img/home/list1.png" :src="home.picurl" class="list1_pic">
                            </div>
                            <div class="list_content">
                                <p class="list_title">{{home.name}}</p>
                                <p class="sev_intro">{{home.profile}}</p>
                            </div>
                        </div>
                    </div>
                    <div class="list2_container">
                        <div class="list" v-for="medium in mediumList">
                            <div class="list_piccont">
                                <img src="../img/home/list2.png" :src="medium.picurl" class="list1_pic">
                            </div>
                            <div class="list_content">
                                <p class="list_title">{{medium.name}}</p>
                                <p class="sev_intro">{{medium.profile}}</p>
                            </div>
                        </div>
                    </div>
                    <div class="list3_container">
                        <div class="list_spe" v-for="aunt in auntList">
                            <div class="list2_one">
                                <img src="../img/home/aunt1.png" :src="aunt.picurl" class="aunt_pic">
                            </div>
                            <div class="list2_two">
                                <p class="list2_name">{{aunt.name}}</p>
                                <p>
                                    <div class="star star_s" v-for="i in Math.floor(aunt.score)"></div>
                                    <div class="star star_half" v-if="aunt.score != Math.floor(aunt.score)"></div>
                                    <div class="star" v-for="i in (5-Math.ceil(aunt.score))"></div>
                                </p><br>
                                <p class="list2_fron">籍贯{{aunt.origin_place}}</p>
                            </div>
                            <div class="list2_three">
                                <p class="list2_distance"></p>
                                <p class="list2_age">{{aunt.age}}岁</p>
                            </div>
                        </div>
                    </div>
                    <div class="list4_container">
                        <div class="list" v-for="repair in repairList">
                            <div class="list_piccont">
                                <img src="../img/home/list3.png" :src="repair.picurl" class="list1_pic">
                            </div>
                            <div class="list_content">
                                <p class="list_title">{{repair.name}}</p>
                                <p class="sev_intro">{{repair.profile}}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <b/>
            </div>
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
<script src="//webapi.amap.com/maps?v=1.3&key=420ea50f88365ed4c968a99629aeb08f"></script>
<script src="//webapi.amap.com/ui/1.0/main.js"></script>
<!--<script type='text/javascript' src='../js/plugins/amap.js' charset='utf-8'></script>-->
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script src="../js/plugins/swiper.min.js"></script>
<script>
    /**
     * 微信认证数据保存在此
     * */
    var wxuserid = "<%=session.getAttribute("wxuserid")%>";
    var accesstoken = "<%=session.getAttribute("wxaccesstoken")%>";
    var openid = "<%=session.getAttribute("wxopenid")%>";
    setItem('user_id', wxuserid);
    setItem('openid', openid);
    setItem('user_auth', accesstoken);
    refreshLocal();
    setItem('server_address_name', '');
    setItem('server_address_address', '');
    setItem('server_address_id', '');
    //快速下单判断值
    var judge=0;

    var demo = new Vue({
        el: '#page-group',
        data: {
            apiUrl: '/home/homeUser', city: '诸暨市', area: '',
            apiCityListUrl: '/system/cityList', cityList:[],fast_state:1,
            adList: [], bannerList: [], iconList: [], homeList: [], mediumList: [], auntList: [], repairList: [],
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
                18:'../auth/go?uri=/wechat/serves/bingren'
            }
        },
        emulateJSON: true,
        mounted: function() {
            this.city = '诸暨市';
            if (getItem('service_city') != '') {
                $('.my_ad').text(getItem('service_city'));
                this.city = getItem('service_city');
            } else {
                setItem('service_city', this.city)
            }
            var state = getItem('fast_state').length != 0 ? getItem('fast_state') : this.fast_state;
            setItem('fast_state', state); //0.支持  1.不支持

            this.getCustomers();
            this.getCity();

        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {city: this.city}, false))
                    .then(function(response) {
                        var jsonData = JSON.parse(response.data.r);
                        this.bannerList = jsonData.bannerList;
                        this.adList = jsonData.adList;
                        this.iconList = jsonData.iconList;
                        this.homeList = jsonData.homeList;
                        this.mediumList = jsonData.mediumList;
                        this.auntList = jsonData.auntList;
                        this.repairList = jsonData.repairList;
                    })
            },
            goUrl:function (id) {
                console.log(this.urlList[id]);
                setItem('judge',0);
                window.location.href=this.urlList[id];
            },
            goFast:function () {
                setItem('judge',1);
            },
            getCity: function () {
                this.$http.get(createUrl(this.apiCityListUrl, {userid: userId}, true))
                    .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        this.cityList = jsonData;
                    } else {
                        console.log(response.data.m)
                    }
                })
            },
        }
    });

    $(function() {
        var mySwiper = new Swiper ('.swiper-container', {
            autoplay: 5000,
            loop: true,
            pagination: '.swiper-pagination',
            observer:true,
        })
    });

    var map, geolocation;
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
        resizeEnable: true
    });
    map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition:'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });
//    map.plugin('AMap.CitySearch', function() {
//        var citysearch = new AMap.CitySearch();
//        citysearch.getLocalCity(function(status, result) {
//            if (status === 'complete' && result.info === 'OK') {
//                if (result && result.city && result.bounds) {
//                    console.log(result)
//                    $('.my_ad').text(result.city);
//                    setItem('user_city', result.city);
//                    if (!getItem('service_city').length) {
//                        setItem('service_city', result.city);
//                    }
//                    $('.my_ad').text(getItem('service_city'));
//                }
//            }
//        });
//    });

    //解析定位结果
    function onComplete(data) {
        setItem('user_address', data.formattedAddress);
        setItem('user_lng', data.position.getLng());
        setItem('user_lat', data.position.getLat());
        if (data.addressComponent != null) {
            setItem('user_citycode', data.addressComponent.citycode);
            // 城市选择给为区域选择
//            setItem('user_city', data.addressComponent.city);
            var result = isCityScope(data.addressComponent.district, demo.cityList);
            if (result.result) {
                setItem('user_city', data.addressComponent.district);
                if (!getItem('service_city').length) {
                    setItem('service_city', data.addressComponent.district);
                    setItem('fast_state', result.data.fast_state);
                    console.log(result)
                }
            }
        }
        $('.my_ad').text(getItem('service_city'));
    }
    //解析定位错误信息
    function onError(data) {
        $('.my_ad').text('诸暨市');
        console.log(data)
    }

    function isCityScope(city, list) {
        for (var i in list) {
            for(var j=0; j<list[i].length; j++) {
                if (city == list[i][j]['name']) {
                    return { result:true, data:list[i][j] }
                }
            }
        }
        return { result:false }
    }

    $('.third_part').on('click','li',function () {
        switch(this.id) {
            case "housekeeping":
                $('.third_list:not(#housekeeping)').css({ "color": "#292929", "border-bottom": "1px solid #e6e6e6" });
                $("#housekeeping").css({ "color": "#5e93f9", "border-bottom": "1px solid #5e93f9" });
                $('.list1_container').show();
                $('.list2_container').hide();
                $('.list3_container').hide();
                $('.list4_container').hide();
                break;
            case "inter_agent":
                $('.third_list:not(#inter_agent)').css({ "color": "#292929", "border-bottom": "1px solid #e6e6e6" });
                $("#inter_agent").css({ "color": "#5e93f9", "border-bottom": "1px solid #5e93f9" });
                $('.list1_container').hide();
                $('.list2_container').show();
                $('.list3_container').hide();
                $('.list4_container').hide();
                break;
            case "person":
                $('.third_list:not(#person)').css({ "color": "#292929", "border-bottom": "1px solid #e6e6e6" });
                $("#person").css({ "color": "#5e93f9", "border-bottom": "1px solid #5e93f9" });
                $('.list1_container').hide();
                $('.list2_container').hide();
                $('.list3_container').show();
                $('.list4_container').hide();
                break;
            case "repair":
                $('.third_list:not(#repair)').css({ "color": "#292929", "border-bottom": "1px solid #e6e6e6" });
                $("#repair").css({ "color": "#5e93f9", "border-bottom": "1px solid #5e93f9" });
                $('.list1_container').hide();
                $('.list2_container').hide();
                $('.list3_container').hide();
                $('.list4_container').show();
                break;
        }
    });
    //关闭弹窗
    function closeSign(){
        $('.my_alert').hide();
    }
    $.init();
</script>
</body>
</html>
