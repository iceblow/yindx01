<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="../css/add_address.css" />
    <script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
    <title>新增地址</title>
    <style>
        .news_head_edit{font-size: 14px !important;height: 100%;line-height: 30px}
    </style>
</head>
<body>
<div id="address">
    <div class="address1">
        <div class="add_con">
            <a onclick="history.go(-1)"><img src="../img/regist/left_arr.png" class="sec1_pic"></a>
            <span class="add_text ad1">新增地址</span>
            <span class="add_text ad2" v-on:click="addAddress">保存</span>
        </div>
    </div>

    <div class="address2">
        <div class="add_con">
            <span class="persom_infor">联系人</span>
            <input type="text" class="persom_con" placeholder="姓名" v-model="rname">
            <div class="sex_select">
                <button type="button" class="sex ms" v-on:click="modifySex" data-sex="0">女士</button>
                <button type="button" class="sex man" v-on:click="modifySex" data-sex="1">先生</button>
            </div>
        </div>
    </div>
    <div class="address3">
        <div class="add_con">
            <span class="persom_infor">电话</span>
            <input type="text" class="persom_con" placeholder="收货人电话" v-model="phone">
        </div>
    </div>
    <div class="address3 per_dizhi">
        <div class="add_con">
            <span class="persom_infor">地址</span> <a href="#" v-on:click="goMap">
            <img src="../img/location/dingwei.png" class="dingwei">
            <input type="text" class="persom_con special" placeholder="小区/写字楼/小学等" v-model="addressname" >
           <img src="../img/location/rightgo.png" class="rightgo" ></a>
        </div>
    </div>
    <div class="address3">
        <div class="add_con">
            <span class="persom_infor">门牌号</span>
            <input type="text" class="persom_con" placeholder="例：2号楼408室" v-model="addressdetail">
        </div>
    </div>

    <div class="address4">
        <div class="add_con" v-on:click="setDefault">
            <img src="../img/location/circle_select.png" class="circle" v-if="isdefault" v-cloak>
            <img src="../img/location/circle_unselect.png" class="circle" v-else="isdefault" v-cloak>
            <span class="set_address">设置成为默认收货地址</span>
        </div>
    </div>
    <div class="blank"></div>
</div>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
var address = new Vue({
    el: '#address',
    data: {
        apiUrl: '/user/addAddress',
        provience: getItem('add-provience'), city: getItem('add-city'), area: getItem('add-area'),
        longitude: getItem('add-lng'), latitude: getItem('add-lat'), addressname: getItem('add-address'), addressdetail: '',
        userid: userId, phone: getItem('add_phone'), rname: getItem('add_rname'), sex: getItem('add_sex'), isdefault: getItem('add_isdefault')
    },
    mounted: function() {
        this.sex = getItem('add_sex') != '' ? getItem('add_sex') : 0;
        this.isdefault = getItem('add_isdefault') != '' ? getItem('add_isdefault') : 0;
        if(this.sex == 0) {
            $(".ms").addClass('checked');
            $(".man").removeClass('checked');
        } else {
            $(".ms").removeClass('checked');
            $(".man").addClass('checked');
        }
    },
    methods: {
        addAddress: function (e) {
            this.$http.get(
                createUrl(this.apiUrl, {
                    provience: this.provience, city: this.city, area: this.area, addressname: this.addressname, addressdetail: this.addressdetail,
                    userid: this.userid, phone: this.phone, rname: this.rname, sex: this.sex, isdefault: this.isdefault,
                    longitude: this.longitude, latitude: this.latitude
                }, true))
            .then(function(response) {
                if (response.data.c == 1) {
                    setItem('add_rname', '');
                    setItem('add_sex', 0);
                    setItem('add_isdefault', 0);
                    setItem('add_phone', '');
                    window.location.href = '../auth/go?uri=/wechat/location/add_list'
                } else {
                    console.log(response.data.m)
                }
            })
        },
        modifySex: function () {
            this.sex = Math.abs(this.sex-1);
            $(".sex").toggleClass('checked');
        },
        setDefault: function () {
            this.isdefault = Math.abs(this.isdefault-1);
        },
        goMap: function () {
            setItem('add_rname', this.rname);
            setItem('add_sex', this.sex);
            setItem('add_isdefault', this.isdefault);
            setItem('add_phone', this.phone);

            window.location.href = '../auth/go?uri=/wechat/location/map'
        }

    }
});
</script>
</body>
</html>
