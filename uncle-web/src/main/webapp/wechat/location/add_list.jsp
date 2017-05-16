<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="YES">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control"
          content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <link rel="stylesheet" href="../js/mui/css/mui.min.css">
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/add_list.css" />
    <title>服务地址</title>
    <style>
        .news_head_edit{font-size: 14px !important;height: 100%;line-height: 30px}
        .mui-table-view-chevron .mui-table-view-cell{padding-right: 15px!important;position: relative}
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav my_head money_head" id="header">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-pull-right news_head_edit" v-on:click="change" v-cloak>{{buttonName}}</a>
    <h1 class="mui-title">服务地址</h1>
</header>
<div class="mui-content " id="pullrefresh">
     <div class="mui-scroll">
         <ul class="mui-table-view mui-table-view-chevron" >
             <li class="mui-table-view-cell" v-for="address in addressList" :data-chose="address.choose_state" :data-addfast="address.fast_state" :data-id="address.addressid" :data-name="address.rname" :data-address="address.addressname+address.addressdetail">
                 <img class="add_del_img" src="../img/location/reddel.png" v-if="showButton" :data-id="address.addressid" >
                 <div class="add_info">
                     <div class="ad1">
                         <span class="name">{{address.rname}}</span>
                         <span style="color: #999" v-if="address.sex">男</span><span style="color: #333" v-else="address.sex">女</span><span style="color: #999"> {{address.phone}}</span>
                     </div>
                     <div class="ad2" v-if="choseId != ''">
                         <span class="dfAd" v-if="address.isdefault && address.choose_state != 0">默认</span><span class="dfAd_1" v-if="address.choose_state == 0">不可用</span>{{address.addressname}}{{address.addressdetail}}
                     </div>
                     <div class="ad2" v-else="choseId != ''">
                         <span class="dfAd" v-if="address.isdefault">默认</span>{{address.addressname}}{{address.addressdetail}}
                     </div>
                 </div>
             </li>
         </ul>
     </div>
</div>
<nav class="mui-bar mui-bar-tab">
    <div class="text_box" style="text-align: center;">
        <a href="../auth/go?uri=/wechat/location/add_address">
            <img src="../img/location/address.png" class="foot_pic">
            <span class="new_add">新增地址</span>
        </a>
    </div>
</nav>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="../js/mui/js/mui.min.js"></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
var header = new Vue({
    el: '#header',
    data: { buttonName:'编辑', changeStatus: 0},
    methods: {
        change: function () {
            if(this.changeStatus == 0) {
                $(".add_info").css("transform","translateX(60px)");

                this.changeStatus = 1;
                this.buttonName = '完成';
                list.showButton = true;
            } else {
                $(".add_info").css("transform","translateX(0px)");

                this.changeStatus = 0;
                this.buttonName = '编辑';
                list.showButton = false;
            }
            $(".reddel").toggle("fast");
        }
    }
});
var list = new Vue({
    el: '#pullrefresh',
    data: {
        apiGetAddressUrl: '/user/getAddressList',
        apiDelAddressUrl: '/user/delAddress',
        addressList: [], categoryid: 0, showButton: false,
        choseId:getItem('choseId')
    },
    mounted: function() {
        this.getCustomers();
    },
    methods: {
        getCustomers: function () {
            if(this.choseId == ''){
                this.$http.get(createUrl(this.apiGetAddressUrl, {userid: userId, categoryid: this.categoryid}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.addressList = jsonData.addressList;
                            console.log(this.choseId);
                        } else {
                            console.log(response.data.m)
                        }
                    })
            }else {
                this.$http.get(createUrl(this.apiGetAddressUrl, {userid: userId, categoryid: this.choseId}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.addressList = jsonData.addressList;
                            console.log(this.choseId);
                        } else {
                            console.log(response.data.m)
                        }
                    })
            }
        }
    }
});

mui.init({

});
///**
// * 下拉刷新具体业务实现
// */
//function pulldownRefresh() {
//    setTimeout(function() {
//        mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
//    }, 1500);
//}

mui('.mui-scroll').on('tap','img',function(){
    mui.ajax(createUrl(list.apiDelAddressUrl, {userid: userId, addressid: $(this).data('id')}, true),{
        data:{},
        dataType:'json',//服务器返回json格式数据
        type:'get',//HTTP请求类型
        success:function(data){
            console.log(data);
        }
    });
    $(this).parent('li').remove();
});

mui('.mui-scroll').on('tap','li',function(){
    if(getItem('choseId')!='' && $(this).data('chose') == 0){
        mui.alert('当前地址不可用')
    }else {
        setItem('server_address_name', $(this).data('name'));
        setItem('server_address_address', $(this).data('address'));
        setItem('server_address_id', $(this).data('id'));
        setItem('add_fast',$(this).data('addfast'))
        var url = getItem('server_url');
        if(url != '') {
            window.location.href = url;
        }
    }
});

</script>
</body>
</html>

