<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
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
        .list-block .item-title{white-space: normal}
        .uncle_img{border-radius: 50%;height: 50px}
        .list-block .item-text{height: auto}
        .uncle_small{font-size: 12px;color: #ccc;margin-left: 15px;margin-right: 20px}
        .item-after{padding-right: 20px}
        .my_uncle{padding-left: 45px;height: 30px;line-height: 30px;width: 50%;float: left;position: relative;border-right: 1px solid #eee;margin-bottom: 10px;margin-top: 10px}
        .uncle_name{font-size: 15px;margin-left: 10px}
        .my_uncle_img{border-radius: 50%;height: 30px;position: absolute;left: 15px}
        .un_select_btn{border: none;background-color: #f6f9f8;color: #333}
        .uncle_del{position: absolute;height: 20px;top: 5px;right: 15px}
        .hide{display: none}
        .chose_uncle::after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .star{float: left;width: 15px;height: 15px;background-image: url(../img/serve/star.png);background-size: 100% 100%}
        .star_s{background-image: url("../img/serve/star_s.png") !important;}
        .star_half{background-image: url('../img/serve/h_star.png')}
    </style>
</head>
<body>
<div class="page-group">
    <div class="page" id="add_address">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <a class="button button-link button-nav pull-right" v-on:click="selected"> 选好了 </a>
            <h1 class="title">服务分类</h1>
        </header>
        <div class="content" style="background-color: white">
            <div class="chose_uncle" style="position: relative" >
                <div class="my_uncle" v-for="(a,i) in list">
                    <img class="my_uncle_img" src="../img/user/default_head_pic.png" :src="a.avatarurl" height="30" />
                    <span class="uncle_name">{{a.name}}</span><span class="uncle_small">{{a.age}}岁</span>
                    <img class="uncle_del" src="../img/user/red_del.png" :id="a.auntid" v-on:click="unSet(i, a.auntid,$event)"/>
                </div>
            </div>
            <div class="list-block media-list" style="margin-top: 0">
                <ul>
                    <li class="item-content" v-for="(aunt,item) in canBookAuntList">
                        <div class="item-media">
                            <img class="uncle_img" src="../img/user/default_head_pic.png" :src="aunt.avatarurl"/>
                        </div>
                        <div class="item-inner">
                            <div class="item-title-row">
                                <div class="item-title">{{aunt.name}}<span class="uncle_small"></span></div>
                            </div>
                            <div class="item-subtitle">
                                <div class="star star_s" v-for="i in Math.floor(aunt.score)"></div>
                                <div class="star star_half" v-if="aunt.score != Math.floor(aunt.score)"></div>
                                <div class="star" v-for="i in (5-Math.ceil(aunt.score))"></div>
                            </div>
                            <div class="item-text">籍贯{{aunt.origin_place}} <span class="uncle_small">{{aunt.age}}岁</span></div>
                        </div>
                        <div class="item-after">
                            <a class="button" :id="aunt.auntid" v-if="aunt.show" v-on:click="set(item, aunt.auntid,$event)">预约</a>
                            <a class="button un_select_btn" :id="aunt.auntid" v-else="aunt.show">已选</a>
                        </div>
                    </li>
                </ul>
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
    $.init();

    var server = new Vue({
        el: '#add_address',
        data: {
            apiUrl: '/order/canBookAuntList',
            list: [], canBookAuntList: [], ids:[],
            longitude: getCookie('lng'), latitude: getCookie('lat'), serverid: 1, thirdids: ''
        },
        emulateJSON: true,
        mounted: function() {
            this.getCustomers()
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {longitude: this.longitude, latitude: this.latitude, serverid: this.serverid, thirdids: this.thirdids}, false))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.canBookAuntList = addShow(jsonData.auntList);
                            console.log(this.canBookAuntList)
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            set: function (item, id, e) {
                if (!this.ids.contains(id)) {
                    this.list.push(this.canBookAuntList[item]);
                    this.ids.push(id)
                    this.canBookAuntList = setHide(this.canBookAuntList, id)
                }
                console.log(this.list)
            },
            unSet: function (item, id,e) {
                console.log(item+ " "+ id);
                this.list.splice(item, 1);
                this.ids.removeByValue(id);
                this.canBookAuntList = setShow(this.canBookAuntList, id)
            },
            selected: function () {
                console.log(this.list);
                setItem('anunt', this.list);

                history.go(-1);
            }
        }
    });

    function addShow(data) {
        for (var key in data) {
            data[key].show = true;
        }
        return data;
    }
    function setShow(data, id) {
        for (var key in data) {
            if (data[key].auntid == id) {
                data[key].show = true;
            }
        }
        return data
    }
    function setHide(data, id) {
        for (var key in data) {
            if (data[key].auntid == id) {
                data[key].show = false;
            }
        }
        return data
    }

    Array.prototype.contains = function (obj) {
        console.log(this)
        var i = this.length;
        while (i--) {
            if (this[i] === obj) {
                return true;
            }
        }
        return false;
    };
    Array.prototype.removeByValue = function(val) {
        for(var i=0; i<this.length; i++) {
            if(this[i] == val) {
                this.splice(i, 1);
                break;
            }
        }
    }
</script>
</body>
</html>

