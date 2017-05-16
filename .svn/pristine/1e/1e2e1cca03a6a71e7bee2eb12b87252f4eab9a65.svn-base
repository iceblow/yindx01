<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-validate="http://www.w3.org/1999/xhtml">
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
        .sign_btn img{height: 100%}
        .phone_input a{height: 40px;position: absolute;right: 15%;top: 30px;border-radius: 0;line-height: 40px;border: none;color: #333}
        .phone_input input{width: 70%;border: 1px solid #d5d7e3;height: 40px;margin-left: 15%;}
        input{padding-left: 10px!important;font-size: 16px}
        .vip_img img{width: 100%}
    </style>
</head>
<body>

<div class="page-group" >
    <div class="page page-current" id="user_info">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back" href="../auth/go?uri=/wechat/user/home" external>
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">个人资料</h1>
        </header>
        <div class="content" style="background-color: white">
            <!-- 这里是页面内容区 -->
            <div class="list-block" style="margin-top: 0" id="container">
                <ul>
                    <li>
                        <a href="#" class="item-link item-content" id="pickfiles" data-key="">
                            <div class="item-inner" style="height: 4rem" >
                                <div class="item-title">头像</div>
                                <div class="item-after" style="max-height: 4rem">
                                    <img style="height: 3rem;border-radius: 50%" id="avatar" src="../img/user/default_head_pic.png" :src="userInfo.avatarurl" v-cloak/>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">积分等级</div>
                                <div class="item-after">
                                    <img style="height: 1rem;" src="../img/user/vip_zero.png" :src="vipHeader[userInfo.level]"/>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="list-block">
                <ul>
                    <li>
                        <a href="#name_edit" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">姓名</div>
                                <div class="item-after" v-cloak>{{userInfo.real_name}}</div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#birthday_edit" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">生日</div>
                                <div class="item-after" v-cloak>{{userInfo.birthday}}</div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">手机号码</div>
                                <div class="item-after" v-cloak>{{userInfo.phone | hidePhone}}</div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#sing_edit" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title">个性签名</div>
                                <div class="item-after" v-cloak>{{userInfo.signature}}</div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="page" id="name_edit">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <a class="button button-link button-nav pull-right" v-on:click="changeName">确定</a>
            <h1 class="title">修改</h1>
        </header>
        <div class="content" style="background-color: white">
            <!-- 这里是页面内容区 -->
            <div class="list-block" style="margin-top: 0">
                <ul>
                    <!-- Text inputs -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">姓名</div>
                                <div class="item-input">
                                    <input type="text" placeholder="输入你的新昵称" v-model="value" lazy>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="page" id="birthday_edit">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <a class="button button-link button-nav pull-right" v-on:click="changeBirthday">确定</a>
            <h1 class="title">修改</h1>
        </header>
        <div class="content" style="background-color: white">
            <div class="list-block" style="margin-top: 0">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">生日</div>
                                <div class="item-input">
                                    <input type="text" id="birthday" placeholder="选择出生年月" v-model="value"/>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="page" id="sing_edit">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <a class="button button-link button-nav pull-right" v-on:click="changeSing">确定 </a>
            <h1 class="title">修改</h1>
        </header>
        <div class="content" style="background-color: white">
            <!-- 这里是页面内容区 -->
            <div class="list-block" style="margin-top: 0">
                <ul>
                    <li class="align-top">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">签名</div>
                                <div class="item-input">
                                    <textarea placeholder="输入你的签名" v-model="value"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
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
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>

<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/moxie.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/plupload.full.min.js"></script>
<!--<script type="text/javascript" src="../js/plupload/plupload.full.min.js"></script> -->
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/i18n/zh_CN.js"></script>
<script type="text/javascript" src="../js/qiniujs/qiniu.js"></script>
<script type="text/javascript" src="../js/plugins/qiniu.js?version=3"></script>
<script>
    $.init();

    Vue.filter('hidePhone', function (value) {
        if (typeof(value) != "undefined" && !value && value != "") {
            return value.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
        }
    });
    var userInfo = {
        userId:0, avatarurl: '', balance: 0,  couponcount: 0, level: 0, point: 0, real_name: '', sign_week: 0, today_sign: 0,
        phone: '18969030101', signature:'', birthday:''
    };
    var info = new Vue({
        el: '#user_info',
        data: {
            apiAutoLoginUrl: '/user/autoLogin',
            apiGetUploadTokenUrl: '/file/getUploadToken',
            userInfo: userInfo,
            upToken:'',
            key: ''
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers();
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiAutoLoginUrl, {}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.userInfo = jsonData.userinfo;
                            console.log(jsonData);
                            nameEdit.userInfo = jsonData;
                        } else {
                            $.toast(response.data.m);
                        }
                    })

            },
        }
    });

    var nameEdit = new Vue({
        el: '#name_edit',
        data: {
            apiUrl: '/user/changeUserInfo',
            userInfo: userInfo,
            value: ''
        },
        methods: {
            changeName: function () {
                this.$http.get(createUrl(this.apiUrl, {userid: userId, key:2, value: this.value}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            info.userInfo.real_name = jsonData.name;
                            $.router.back()
                        } else {
                            console.log(response.data.m)
                        }
                    })
            },
        }
    });

    var birthdayEdit = new Vue({
        el: '#birthday_edit',
        data: {
            apiUrl: '/user/changeUserInfo',
            userInfo: userInfo,
            value: ''
        },
        methods: {
            changeBirthday: function () {
                $("#birthday").focus();
                this.$http.get(createUrl(this.apiUrl, {userid: userId, key:3, value: this.value}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            info.userInfo.birthday = jsonData.birthday;
                            $.router.back()
                        } else {
                            console.log(response.data.m)
                        }
                    })
            },
        }
    });

    var singEdit = new Vue({
        el: '#sing_edit',
        data: {
            apiUrl: '/user/changeUserInfo',
            userInfo: userInfo,
            value: ''
        },
        methods: {
            changeSing: function () {
                this.$http.get(createUrl(this.apiUrl, {userid: userId, key:4, value: this.value}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            info.userInfo.signature = jsonData.signature;
                            $.router.back()
                        } else {
                            console.log(response.data.m)
                        }
                    })
            },
        }
    });

    $("#birthday").calendar({
        value: ['2000-01-01'],
        onChange: function(p, values, displayValues){
            birthdayEdit.value = displayValues[0]
        }
    });

</script>
</body>
</html>
