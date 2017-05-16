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
    <link rel="stylesheet" href="../js/mui/css/mui.min.css">

    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/news_list.css" />
    <title>消息</title>
    <style>
        .mui_display {display: none}
        .news_head_edit{font-size: 14px !important;height: 100%;line-height: 30px}
        .news_check_box{float: left;width: 60px;height: 42px;position: relative}
        .news_check_box input{left: 12px!important;}
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav my_head money_head" id="header">
    <!-- <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a> -->
    <a class="mui-icon mui-pull-right news_head_edit mui_display" v-on:click="edit" style="display: block">编辑</a>
    <h1 class="mui-title">消息</h1>
</header>
<div class="mui-content mui-scroll-wrapper" id="pullrefresh">
    <div class="mui-scroll" id="new_list">
        <ul class="mui-table-view" id="OA_task_1">
            <li class="mui-table-view-cell mui-media mui-radio" :data-type="message.type"
                v-for="message in messageList" :id="'li_'+message.id" :data-id="message.id" v-cloak>
                <div class="mui-slider-right mui-disabled">
                    <a class="mui-btn mui-btn-red" :data-id="message.id">删除</a>
                </div>
                <div class="mui-slider-handle mui-checkbox mui-left">
                    <div class="test">
                        <div class="news_check_box" style="display: none;">
                            <input name="news" type="checkbox" :value="message.id">
                        </div>
                        <img class="mui-media-object mui-pull-left" src="../img/message/message_type_sys.png" v-if="message.type == 1">
                        <img class="mui-media-object mui-pull-left" src="../img/message/message_type_ad.png" v-if="message.type == 2">
                        <img class="mui-media-object mui-pull-left" src="../img/message/message_type_man.png" v-if="message.type == 3">
                        <img class="mui-media-object mui-pull-left" src="../img/message/message_type_active.png" v-if="message.type == 4">
                        <div class="mui-media-body">
                            <span v-if="message.type == 1">系统消息</span>
                            <span v-if="message.type == 2">广告</span>
                            <span v-if="message.type == 3">官方通知</span>
                            <span v-if="message.type == 4">活动通知</span>
                            <p class="mui-pull-right" style="font-size: 13px">{{message.timestr}}</p>
                            <p class='mui-ellipsis'>{{message.title}}</p>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<div class="mui-bar mui-bar-tab" style="display: none;height: 40px" id="bar_tab">
    <div class="mui-input-row mui-checkbox mui-left">
        <label>全选</label>
        <input name="news_all" id="news_all" type="checkbox"/>
    </div>
    <div class="foot_btn btn1" v-on:click="setReaded">标为已读</div>
    <div class="foot_btn btn2 del_news_all" v-on:click="delMessage">删除</div>
</div>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="../js/mui/js/mui.min.js"></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
var header = new Vue({
    el: '#header',
    methods: {
        edit: function () {
            $(".mui-bar-tab").toggle("mui_display");
            ($('.news_head_edit').html()=='编辑')?$('.news_head_edit').html('完成'):$('.news_head_edit').html('编辑');
            $(".news_check_box").toggle("mui_display");
            //全选功能
            $("#news_all").click(function () {
                if(this.checked){
                    $('input[name="news"]').prop("checked", true);
                }else{
                    $('input[name="news"]').prop("checked", false);
                }
            });
        }
    }
});
var bar = new Vue({
    el: '#bar_tab',
    methods: {
        delMessage: function () {
            var idsArr = [];
            $('input[name="news"]:checked').each(function(){
                idsArr.push($(this).val());
            });
//            list.delMessage(idsArr.join(','))
            $('.news_head_edit').html('编辑');
            $(".mui-bar-tab").toggle("mui_display");
            $(".news_check_box").toggle("mui_display");
            $.each(idsArr, function(key, value) {
                $("#li_"+value).remove()
            });
        },
        setReaded: function () {
            var idsArr = [];
            $('input[name="news"]:checked').each(function(){
                idsArr.push($(this).val());
            });
//            list.setReaded(idsArr.join(','))
            $('.news_head_edit').html('编辑');
            $(".mui-bar-tab").toggle("mui_display");
            $(".news_check_box").toggle("mui_display");
        }
    }

});
var list = new Vue({
    el: '#new_list',
    data: {
        apiUrl: '/user/messageList',
        apiDelMessageUrl: '/user/delMessage',
        apiSetReadedUrl: '/user/setReaded',
        messageList:[], page:1, havemove:0, delList:[], readList:[]
    },
    mounted: function() {
        this.getCustomers();
    },
    methods: {
        getCustomers: function () {
            this.$http.get(createUrl(this.apiUrl, {userid: userId}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        var jsonData = JSON.parse(response.data.r);
                        this.messageList = jsonData.messagelist;
                        console.log(this.messageList)
                    } else {
                        console.log(response.data.m)
                    }
                })
        },
        delMessage: function (ids) {
            this.$http.get(createUrl(this.apiDelMessageUrl, {userid: userId, mids:ids}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        console.log(response.data.m)
                    } else {
                        console.log(response.data.m)
                    }
                })
        },
        setReaded: function (ids) {
            this.$http.get(createUrl(this.apiSetReadedUrl, {userid: userId, mids:ids}, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        console.log(this.messageList)
                    } else {
                        console.log(response.data.m)
                    }
                })
        }

    }
});
(function($) {
    var btnArray = ['确认', '取消'];
    $('#OA_task_1').on('tap', '.mui-btn', function(event) {
        var elem = this;
        var li = elem.parentNode.parentNode;
        console.log(jQuery(elem).data('id'));
        mui.confirm('确认删除该条记录？', '系统提示', btnArray, function(e) {
            if (e.index == 0) {
                li.parentNode.removeChild(li);
                list.delMessage(1);
            } else {
                setTimeout(function() {
                    $.swipeoutClose(li);
                }, 0);
            }
        });
    });
})(mui);
mui.init({
//    pullRefresh: {
//        container: '#pullrefresh',
//        down: {
//            callback: pulldownRefresh
//        },
//        up: {
//            contentrefresh: '正在加载...',
//            callback: pullupRefresh
//        }
//    }
});
/**
 * 下拉刷新具体业务实现
 */
function pulldownRefresh() {

    mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
}

/**
 * 上拉加载具体业务实现
 */
function pullupRefresh() {

    mui('#pullrefresh').pullRefresh().endPullupToRefresh();
}
mui('#new_list').on('tap', 'li', function(e) {
    setItem('message_id', this.getAttribute('data-id'));
    setItem('message_type', this.getAttribute('data-type'));
    window.location.href = '../auth/go?uri=/wechat/message/detail'
});
</script>
</body>
</html>
