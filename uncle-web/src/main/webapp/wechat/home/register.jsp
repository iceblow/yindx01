<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-validate="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>绑定手机号</title>
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
        .phone_input{padding-top: 30px;position: relative}
        .phone_input a{height: 40px;position: absolute;right: 15%;top: 30px;border-radius: 0;line-height: 40px;border: none;color: #333}
        .btn_disable{background-color: #d5d7e3}
        .btn_send{background-color: #69a8fb;color: white !important}
        .phone_input input{width: 70%;border: 1px solid #d5d7e3;height: 40px;margin-left: 15%;}
        .phone_btn{width: 70%;border:none;border-radius: 0;height: 35px;
            background-image:url(../img/user/next_shape_select.png);background-size: 100% 100%;
            margin-left: 15%;margin-top: 30px;line-height: 35px;color: white;
        }
        input{padding-left: 10px!important;font-size: 16px}
        .vip_img img{width: 100%}
    </style>
</head>
<body>

<div class="page-group">
    <div class="page" id="phone_register">
        <header class="bar bar-nav my_head">
            <h1 class="title">绑定手机号</h1>
        </header>
        <div class="content" style="background-color: white">
            <!-- 这里是页面内容区 -->
            <div class="phone_input">
                <input placeholder="输入新手机号码" v-model="newPhone"/>
            </div>
            <div class="phone_input">
                <input placeholder="输入验证码" v-model="vcode"/>
                <a class="button btn_disable" v-if="isSet"><span class="send_time">{{time}}</span>"重新获取</a>
                <a class="button btn_send" v-else="isSet" v-on:click="sendCode">点击获取</a>
            </div>
            <a class="button phone_btn" v-on:click="changePhone">完成</a>
        </div>
    </div>
</div>
<!--<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>-->
<!--<script>var Zepto = jQuery</script>-->
<!--sui-ui-->
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="//cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.2.1/vue-resource.min.js"></script>
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
    
    var wait = 60;
    var phoneNew = new Vue({
        el: '#phone_register',
        data: {
            apiGetCodeUrl: '/user/getVCode',
            apiBindPhoneUrl: '/user/bindPhone',
            newPhone: '',
            isSet: false,
            vcode: '',
            time: 60,
            userId: getItem('user_id')
        },
        methods: {
            sendCode: function () {
            	
                if(this.newPhone == '') {
                    $.toast("请输入手机号码");
                    return;
                }
                this.$http.get(createUrl(this.apiGetCodeUrl, {phone: this.newPhone, type:4}, false))
                        .then(function(response) {
                            if (response.data.c == 1) {
                                var jsonData = JSON.parse(response.data.r);
                                this.isSet = true;
                                this.time = 60;
                                time();
                                //console.log(jsonData)
                                $.toast(response.data.m);
                            } else {
                                //console.log(response.data.m)
                            	$.toast(response.data.m);
                            }
                        });
            },
            changePhone: function () {
                if (this.newPhone == "") {
                    $.toast("请输入新手机号码");
                    return false;
                }
                if (this.vcode == "") {
                    $.toast("请输入验证码");
                    return false;
                }
                this.$http.get(createUrl(this.apiBindPhoneUrl, {userid: this.userId, vcode: this.vcode, phone:this.newPhone}, true))
                        .then(function(response) {
                            if (response.data.c == 1) {
                                var jsonData = JSON.parse(response.data.r);
                               /*  home.userInfo.phone = jsonData.phone;
                                info.userInfo.phone = jsonData.phone;
                                setting.userInfo.phone = jsonData.phone;
                                $.router.load("#user_info"); */
                                //用户信息保存
                                setItem('user_id', jsonData.userinfo.userid);
                                setItem('user_auth', jsonData.userinfo.accesstoken);
                                refreshLocal();
                                window.location.replace("../auth/go?uri=/wechat/home/home");
                            } else {
                                $.toast(response.data.m);
                            }
                        })
            }
        }
    });
    function time() {
        if (wait == 0) {
            wait = 60;
            phoneNew.time = wait;
        } else {
            wait--;
            phoneNew.time = wait;
            setTimeout(function() { time() }, 1000)
        }
    }

    $.init();
</script>
</body>
</html>