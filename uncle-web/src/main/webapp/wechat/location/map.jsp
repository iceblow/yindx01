<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <title>地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../js/mui/css/mui.min.css">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/AMap.CloudDataSearchRender1120.css"/>
    <style>
        .mui-input-row { overflow: hidden; }
        #mapContainer { width: 100%;}
        #panel { background-color: #1E90FF}
        .mui-input-row { clear: right;}
        .mui-input-clear{background-color: white !important;color: #adadad !important;}
        .result_info{margin-left: 40px;}
        .result_img{position: absolute;height: 30px;top: 50%;margin-top: -15px;}
        .poi-title{font-size: 16px}
        .my_poi_title{color: #5d8ff9;}
        .result1_div{padding: 10px;background-color: white;border-bottom: 1px solid #efeff4}
        #result1{position: fixed;top: 44px;width: 100%;z-index: 99999}
        .search_result_title{font-size: 16px;margin-bottom: 10px}
        .search_result_2{padding: 10px;position: relative;background-color: white;border-bottom: 1px solid #efeff4}
        .search_result_info{margin-left: 40px;}
        .search_result_3{position: absolute;top: 50%;margin-top: -15px}
        .search_result_3 img{height: 30px}
        .amap-logo,.amap-copyright { display: none!important; }
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav my_head money_head" id="header">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <div class="mui-input-row mui-search">
        <input type="search" id="keyword" name="keyword" class="mui-input-clear" placeholder="小区/学校/写字楼等" >
    </div>
</header> 
<div class="mui-content" id="address">
    <div id="result1" class="autobox" name="result1"></div>
    <div id="mapContainer"></div>
    <div id="result" class="amap-pl-pc">
        <div class="amap_lib_placeSearch mobile">
            <div class="amap_lib_placeSearch_list">
                <ul class="mui-table-view mui-table-view-chevron amap_lib_placeSearch_ul">
                    <li class="mui-table-view-cell poibox" v-for="(address,index) in addressList" v-cloak
                        v-on:click="setAddress(address.province, address.city, address.district, address.lng, address.lat, address.formattedAddress)">
                        <img class="result_img" src="../img/location/place_logo.png" v-if="index == 0"/>
                        <img class="result_img" src="../img/location/positioning.png" v-else="index == 0"/>
                        <div class="result_info">
                            <h3 class="poi-title my_poi_title"><span class="poi-name" v-cloak><span v-if="index==0 && !is_search">[当前]</span>{{address.neighborhood}}</span></h3>
                            <div class="poi-info"><p class="poi-addr" v-cloak>{{address.formattedAddress}}</p></div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="../js/mui/js/mui.min.js"></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script src="//webapi.amap.com/maps?v=1.3&key=420ea50f88365ed4c968a99629aeb08f&plugin=AMap.PlaceSearch"></script>
<script src="//webapi.amap.com/ui/1.0/main.js"></script>
<script>
var mapObj;
var marker = new Array();
var windowsArr = new Array();
var cloudDataLayer;
var MSearch;
var keyword;
var lng = getItem('user_lng'), lat = getItem('user_lat'), city = getItem('user_city'), citycode = getItem('user_citycode'), province='浙江省';;
$(function(){
    //浏览器当前窗口文档body的高度
    var height = $(document.body).height();
    $("#mapContainer").css("height", (height/2 -5)+"px");

    mapInit();

//    autoSearch();
});
var address = new Vue({
    el: '#address',
    data: {
        addressList: [], selectList: [], is_search: false,
        provience: '', city:'', area:'', lng:lng, lat:lat, address: "", neighborhood: ""
    },
    mounted: function() {

    },
    methods: {
        setAddress: function (province, city, district, lng, lat, address) {
            setItem('add-address', address);
            setItem('add-lng', lng);
            setItem('add-lat', lat);
            setItem('add-provience', province);
            setItem('add-city', city);
            setItem('add-area', district);
            // 返回到添加页面
            window.location.href = '../auth/go?uri=/wechat/location/add_address'
        }
    }
});

//初始化地图对象，加载地图
function mapInit(){
    mapObj = new AMap.Map("mapContainer",{
        center:new AMap.LngLat(lng, lat), //地图中心点
        level:17  //地图显示的比例尺级别
    });
    // 启动查找附近地址
    AMap.service(["AMap.PlaceSearch"], function() {
        var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize: 5,
//            type: '商务住宅',
            pageIndex: 1,
            city: citycode, //城市
//            map: mapObj,
//            panel: "result"
        });

        var cpoint = [lng, lat]; //中心点坐标
        placeSearch.searchNearBy('', cpoint, 160, function(status, result) {
            var pois = result.poiList.pois
//            console.log(result.poiList.pois.length)
            console.log(result.poiList.pois);
            for(var key=0; key<result.poiList.pois.length; key++) {
//                address.addressList.push({
//                    formattedAddress: pois[key].address,
//                    neighborhood: pois[key].name,
//                    lat:pois[key].location.lat, lng:pois[key].location.lng,
////                    province: pois[key].pname,
////                    city:pois[key].cityname,
////                    district: pois[key].adname
//                });
                data = {
                    formattedAddress: pois[key].address,
                    neighborhood: pois[key].name,
                    lat:pois[key].location.lat, lng:pois[key].location.lng
                };
                console.log(getPovince([pois[key].location.lng, pois[key].location.lat], data));
            }
        });
    });

//    AMap.event.addListener(mapObj,'click',getLnglat); //点击事件
    // 定位自己
//    circle = new AMap.Circle({   //圆形编辑器的样式
//        map: mapObj,
//        center:new AMap.LngLat("120.14837","30.290422"),
//        radius:600,
//        strokeColor: "#C0D7F5",
//        strokeOpacity: 1,
//        strokeWeight: 1,
//        fillColor: "EDF0F3",
//        fillOpacity: 0.1
//    });
//    mapObj.plugin(["AMap.CircleEditor"],function(){
//        circleEditor = new AMap.CircleEditor(mapObj, circle);   //创建圆形编辑器对象
//        circleEditor.open();    //打开圆形编辑器
//    });
    // 替换图标
    myMarker = new AMap.Marker({
        icon: new AMap.Icon({  //复杂图标
            size: new AMap.Size(52, 52),//图标大小
            imageSize: new AMap.Size(26,26),
            image: "../img/location/minemap.png", //大图地址
            imageOffset: new AMap.Pixel(0, 13)//相对于大图的取图位置
        }),
        position: new AMap.LngLat(lng, lat)
    });
    myMarker.setMap(mapObj);  //在地图上添加点
    getAddress([lng, lat]);


    newMarker = new AMap.Marker({
        icon: new AMap.Icon({  //复杂图标
            size: new AMap.Size(43, 52),//图标大小
            imageSize: new AMap.Size(21,26),
            image: "../img/location/place_logo.png", //大图地址
//            imageOffset: new AMap.Pixel(0, 0)//相对于大图的取图位置
        }),
        position: new AMap.LngLat(lng, lat),
    });


    //加载输入提示插件
    mapObj.plugin(["AMap.Autocomplete"], function() {
        //判断是否IE浏览器
        if (navigator.userAgent.indexOf("MSIE") > 0) {
            document.getElementById("keyword").onpropertychange = autoSearch;
        }
        else {
            document.getElementById("keyword").oninput = autoSearch;
        }
    });

    var dragstartEventListener = mapObj.on('dragstart', function(e) {
//        console.log(e);
        newMarker.setMap(mapObj);
    });
    var draggingEventListener = mapObj.on('dragging', function(e) {
//        console.log(e);
        newMarker.setPosition(mapObj.getCenter());
    });
    var dragendEventListener = mapObj.on('dragend', function(e) {
        newMarker.setPosition(mapObj.getCenter());
//        console.log(newMarker.getPosition());

        //逆地理编码
        getAddress([newMarker.getPosition().lng, newMarker.getPosition().lat])
    });

//    AMap.event.addListener(newMarker, 'dragstart', function(e){
//        newMarker.setMap(mapObj);
//        console.log(e)
//    });
    AMap.event.addListener(newMarker, 'touchmove', function(e){
        var lat = e.lnglat.lat,
            lng = e.lnglat.lng;
        newMarker.setPosition(new AMap.LngLat(lng,lat));
        console.log(e)

    });
}
function getPovince(lnglatXY, data) {
    AMap.plugin('AMap.Geocoder',function(){
        var geocoder = new AMap.Geocoder({
            city: citycode//城市，默认：“全国”
        });
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                console.log(result.regeocode.addressComponent);
                address.addressList.push({
                    formattedAddress: data.formattedAddress,
                    neighborhood: data.neighborhood,
                    lat:data.lat, lng:data.lng,
                    province: result.regeocode.addressComponent.province,
                    city: result.regeocode.addressComponent.city,
                    district: result.regeocode.addressComponent.district,
                });
            }
        });
    });
}
function getAddress(lnglatXY) {
    AMap.plugin('AMap.Geocoder',function(){
        var geocoder = new AMap.Geocoder({
            city: citycode//城市，默认：“全国”
        });
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                //获得了有效的地址信息:
                //即，result.regeocode.formattedAddress
//                console.log(result);
                var add = result.regeocode.addressComponent;
                var neighborhood = add.neighborhood ? add.neighborhood : add.street + ' ' + add.streetNumber;
                address.addressList.shift();
                address.addressList.unshift({
                    formattedAddress: result.regeocode.formattedAddress,
                    neighborhood: neighborhood,
                    lat:lnglatXY[1], lng:lnglatXY[0],
                    province: result.regeocode.addressComponent.province,
                    city:result.regeocode.addressComponent.city,
                    district: result.regeocode.addressComponent.district
                });

                this.province = result.regeocode.addressComponent.province;
                this.city = result.regeocode.addressComponent.city;
                this.district = result.regeocode.addressComponent.district;

                mapObj.setFitView();
            } else {
                //获取地址失败
            }
        });
    });
}

//清空地图
function clearMap(){
    mapObj.clearMap();
    document.getElementById("result").innerHTML = '&nbsp;';
}
//关键词查询
function placeSearch1() {
    clearMap();
    keyword = document.getElementById("keyword").value;
    mapObj.plugin(["AMap.PlaceSearch"], function() {
        MSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize:10,
            pageIndex:1,
            city: citycode //城市
        });
        AMap.event.addListener(MSearch, "complete", Search_CallBack);//返回地点查询结果
        MSearch.search(keyword); //关键字查询
    });
}
//添加marker&infowindow
function addmarker(i, d) {
    var lngX;
    var latY;
    var iName;
    var iAddress;
    if(d.location){
        lngX = d.location.getLng();
        latY = d.location.getLat();
    }else{
        lngX = d._location.getLng();
        latY = d._location.getLat();
    }
    if(d.name){
        iName = d.name;
    }else{
        iName = d._name;
    }
    if(d.name){
        iAddress = d.address;
    }else{
        iAddress = d._address;
    }
    var markerOption = {
        map:mapObj,
//        icon:"../img/location/place_logo.png",
        iconStyle: {
            src: '../img/location/place_logo.png',
            style: {
                width: '22px',
                height: '22px'
            }
        },
//        size: {x:-12, y:-12},
        position:new AMap.LngLat(lngX, latY)
    };
    var mar = new AMap.Marker(markerOption);
    marker.push(new AMap.LngLat(lngX, latY));

    var infoWindow = new AMap.InfoWindow({
        content:"<h3><font color=\"#00a6ac\">" + (i + 1) + ". " + iName + "</font></h3>" + TipContents(d.type, iAddress, d.tel),
//        size:new AMap.Size(300, 0),
        autoMove:true,
//        offset:new AMap.Pixel(0,-30)
    });
    windowsArr.push(infoWindow);
    var aa = function (e) {infoWindow.open(mapObj, mar.getPosition());};
    AMap.event.addListener(mar, "click", aa);
}
//回调函数
function Search_CallBack(data) {
    address.is_search = true;
    mapObj.clearMap();
    var resultStr = "";
    var poiArr = data.poiList.pois;
    var resultCount = poiArr.length;
    address.addressList = [];
    for (var i = 0; i < resultCount; i++) {
        resultStr += "<div id='divid'" + (i + 1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)'><div div class='search_result_1'><div div class='search_result_2'><div class='search_result_3'><img src=\"../img/location/positioning.png\"></div>" + "<div class='search_result_info'><h3 class='search_result_title'><font color=\"#5d8ff9\">" + poiArr[i].name + "</font></h3>";
        resultStr += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</div></div></div></div>";
        addmarker(i, poiArr[i]);
        address.addressList.push({
            formattedAddress: poiArr[i].address,
            neighborhood: poiArr[i].name,
            lat: poiArr[i].location.lat, lng: poiArr[i].location.lng,
            province: province,
            city: city,
            district: district
        });
    }
    mapObj.setFitView();
//    document.getElementById("result").innerHTML = resultStr;
}
function TipContents(type, address, tel) {  //窗体内容
    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
        address = "暂无";
    }

    return address;
}
function openMarkerTipById1(pointid, thiss) {  //根据id 打开搜索结果点tip
    thiss.style.background = '#CAE1FF';
//    windowsArr[pointid].open(mapObj, marker[pointid]);


}
function onmouseout_MarkerStyle(pointid, thiss) { //鼠标移开后点样式恢复
    thiss.style.background = "";
}

//输入提示
function autoSearch() {

    var keywords = document.getElementById("keyword").value;
    var auto;
    var autoOptions = {
        pageIndex:1,
        pageSize:10,
        city: citycode //城市，默认全国
    };
    auto = new AMap.Autocomplete(autoOptions);
    //查询成功时返回查询结果
    AMap.event.addListener(auto, "complete", autocomplete_CallBack);
    auto.search(keywords);
}
//输出输入提示结果的回调函数
function autocomplete_CallBack(data) {
    var resultStr = "";
    var tipArr = [];
    tipArr = data.tips;
    if (tipArr.length>0) {
        for (var i = 0; i < tipArr.length; i++) {
            resultStr += "<div class='result1_div' id='divid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
                + ",this)' onclick='selectResult(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
                + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
        }
    }
    else  {
        resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
    }

    document.getElementById("result1").innerHTML = resultStr;
    document.getElementById("result1").style.display = "block";
}

//输入提示框鼠标滑过时的样式
function openMarkerTipById(pointid, thiss) {  //根据id打开搜索结果点tip
    thiss.style.background = '#CAE1FF';
}

//输入提示框鼠标移出时的样式
function onmouseout_MarkerStyle(pointid, thiss) {  //鼠标移开后点样式恢复
    thiss.style.background = "";
}

//从输入提示框中选择关键字并查询
function selectResult(index) {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("keyword").onpropertychange = null;
        document.getElementById("keyword").onfocus = focus_callback;
    }
    //截取输入提示的关键字部分
    var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");;
    document.getElementById("keyword").value = text;
    document.getElementById("result1").style.display = "none";
    //根据选择的输入提示关键字查询
    mapObj.plugin(["AMap.PlaceSearch"], function() {
        var msearch = new AMap.PlaceSearch();  //构造地点查询类
        AMap.event.addListener(msearch, "complete", Search_CallBack); //查询成功时的回调函数
        msearch.search(text);  //关键字查询查询
    });
}

//定位选择输入提示关键字
function focus_callback() {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("keyword").onpropertychange = autoSearch;
    }
}
//回调函数
function errorInfo(data) {
    resultStr = data.info;
    document.getElementById("result").innerHTML = resultStr;
}

//解析定位结果
function onComplete(data) {
    var str=['定位成功'];
    str.push('经度：' + data.position.getLng());
    str.push('纬度：' + data.position.getLat());
    if(data.accuracy){
        str.push('精度：' + data.accuracy + ' 米');
    }
    str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
    /* 	    console.log(str); */
    //    document.getElementById('tip').innerHTML = str.join('<br>');
}
//解析定位错误信息
function onError(data) {
    /* 	    console.log(data); */
    //    document.getElementById('tip').innerHTML = '定位失败';
}
</script>
</body>
</html>