<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="YES">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control"
          content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/address.css" />
    <title>选择地址</title>
    <style>
        .top_aearch input[type=text]:focus{
            text-align: left;
        }
    </style>
</head>
<body>
<div class="add_top">
    <a href="../auth/go?uri=/wechat/home/home" external><img src="../img/regist/cha.png" class="cha"></a>
    <p class="search_add">选择地址</p>
    <div class="top_aearch">
        <img src="../img/home/search.png" class="search_pic">
        <input type="text" class="city_search" placeholder="输入城市名或拼音查询" />
    </div>
</div>

<div class="city_list" id="city_list">
    <div class="cur_city_list">
        <p class="cur_city" v-cloak>当前：<span class="local_city">{{city}}</span></p>
        <img src="../img/address/dingwei.png" class="dingwei">
        <p class="again_add" v-on:click="reSetCity">重新定位</p>
    </div>
    <div class="city_infor">
        <div class="alphabet" v-for="citys in cityList" v-cloak>
            <div class="letter">{{citys[0].letter}}</div>
            <div class="zone_name" v-for="city in citys" v-on:click="setCity($event)" :data-name="city.name" :data-state="city.fast_state">{{city.name}}</div>
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="//cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.2.1/vue-resource.min.js"></script>
<script src="//webapi.amap.com/maps?v=1.3&key=420ea50f88365ed4c968a99629aeb08f"></script>
<script src="//webapi.amap.com/ui/1.0/main.js"></script>
<script>
    $('.city_search').focus(function () {
        $('.search_pic').css('left','20px')
    }).blur(function () {
        $('.search_pic').css('left','27%')
    });

    new Vue({
        el: '#city_list',
        data: {
            apiUrl: '/system/cityList',
            cityList: '', city: '拱墅区'
        },
        mounted: function(e) {
            this.city = getItem('service_city').length ? getItem('service_city') : getItem('user_city');
            this.getCity();
        },
        methods: {
            getCity: function () {
                this.$http.get(createUrl(this.apiUrl, {userid: userId}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.cityList = jsonData;
                        } else {
                            console.log(response.data.m)
                        }
                    })
            },
            setCity: function (e) {
                setItem('service_city', $(e.target).data('name'));
                setItem('fast_state', $(e.target).data('state'));
                this.city = $(e.target).data('name');
                window.location.href = '../auth/go?uri=/wechat/home/home'
            },
            reSetCity: function () {
                var map, geolocation;
                //加载地图，调用浏览器定位服务
                map = new AMap.Map('container', {
                    resizeEnable: true
                });
//                map.plugin('AMap.CitySearch', function() {
//                    var citysearch = new AMap.CitySearch();
//                    citysearch.getLocalCity(function(status, result) {
//                        if (status === 'complete' && result.info === 'OK') {
//                            if (result && result.city && result.bounds) {
//                                this.city = result.city;
//                                setItem('service_city', result.city);
//                                $(".local_city").text(result.city)
//                            }
//                        }
//                    });
//                });
                map.plugin('AMap.Geolocation', function() {
                    geolocation = new AMap.Geolocation({
                        enableHighAccuracy: true,//是否使用高精度定位，默认:true
                        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                    });
                    map.addControl(geolocation);
                    geolocation.getCurrentPosition();
                    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
                    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
                });
                //解析定位结果
                function onComplete(data) {
                    console.log(data);
                    if (data.addressComponent != null) {
                        this.city = data.addressComponent.district;
                        setItem('service_city', this.city);
                        $(".local_city").text(this.city)
                    }

                }
                //解析定位错误信息
                function onError(data) { console.log(data); }
            }
        }
    });
    //本地地址搜索
    $('.city_search').bind('input propertychange', function() {
        var value=$('.city_search').val();
        if( value!==''){
            $('.letter , .zone_name').hide();
            $('.zone_name').each(function () {
                if($(this).html().indexOf(value) !== -1){
                    $(this).show();
                }
            })
        }else {
            $('.letter , .zone_name').show();
        }
    });
</script>
</body>
</html>
<script>



</script>
