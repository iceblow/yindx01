<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>评价订单</title>
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
        .pay_title{color: #959495;font-size: 14px;margin-top: 30px;text-align: center}
        .pay_money{font-size: 1.5rem;color: #ff5266}
        .detail_title{float: left;font-size: 14px;color: #959495}
        .detail_text{float: right;font-size: 14px;}
        .pay_item{margin-bottom: 15px}
        .img_box:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .confirm_item:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;
        }
        .item_list{float: left;width: 50%;margin: 0.8rem 0;height:40px ;border-right: 1px solid #eee}
        .item_list a{display: block}
        .item_text_box{width: 110px;position: relative;margin: 0 auto}
        .item_text_box img {height: 100%;}
        .serve_title{margin-bottom: 0;margin-top: 0;font-size: 12px;color: #333}
        .serve_text{font-size: 12px;color: #959495}
        .item_text{height: 100%;float: right;top: 0;padding-top: 5px}
        .line_title{line-height: 20px;width: 126px;margin: 0 auto;font-size: 12px;text-align: center;position: relative;margin-top: 20px}
        .line_title  img{width: 100%;position: absolute;left: 0;height: 4px;top: 50%;margin-top: -2px}
        .line_title_name{font-size: 14px;margin: 0}
        .item_box{border-bottom: 12px solid #ededed}
        .item_box:after {
            content: ".";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }
        .pay_detail{width: 70%;margin: 0 auto;border-top: 1px solid #e6e6e6;margin-top: 30px;padding-top: 20px;padding-bottom: 5px;}
        .pay_chose_title{font-size: 16px;}
        .pay_button_box{padding: 10px 15px 15px 15px ;}
        .pay_button{width: 100%;height: 45px;line-height: 45px;background-image: url(../img/user/back_background.png);border: none;color: white}
        .pay_chose_after{position: absolute;right: 30px;color: #959495}
        .confirm_item{width: 100%;padding: 15px}
        .confirm_title{width: 20%;float: left;font-size: 14px;color: #959495}
        .confirm_text{float: left;width: 80%;font-size: 14px;padding-left: 20px}
        .pay_way_number{font-size: 16px;text-align: center;margin-top: 30px;margin-bottom: 30px}
        .way_money{color: #ff5266}
        .pay_line{height: 15px;background-color: #ededed}
        .img_box{padding: 15px 30px;border-bottom: 1px solid #eaf1f9;padding-bottom: 0}
        .uncle_img{width: 33%;text-align: center;float: left;margin-bottom: 15px;}
        .uncle_img img{height: 35px;border-radius: 50%}
        .uncle_img p{font-size: 14px;color: #959495;}
        .uncle_img a{height: 50px;line-height: 50px;width: 95%;margin-left: 10%}
        .uncle_img .my_handle{height: 30px;line-height: 30px}
        .handle_text{width: 100%;height: 65px;border: 1px solid #eaf1f9;margin-bottom: 30px;padding: 10px;font-size: 14px}
        .star{float: left;width: 15px;height: 15px;background-image: url(../img/serve/star.png);background-size: 100% 100%;margin-right: 15px}
        .judge_box{border: 1px solid #959495;border-radius: 5px;padding: 5px 0;margin-right: 3%;width: 30% !important;}
        .judge_text{font-size: 12px !important}
        .star_s{background-image: url("../img/serve/star_s.png") !important;}
        .judge_box_active{border-color: #5e93f9 !important;}
        .judge_box_active p{color: #5e93f9 !important}


    </style>
</head>
<body>
<div class="page-group">

    <div class="page  page-current">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left" style="margin-top: 10px"></span>
            </a> -->
            <h1 class="title">评价</h1>
        </header>
        <div class="content" style="background-color: white" id="handle">
            <!-- <div class="img_box">
                <div class="uncle_img" v-for="aunt in auntList" v-cloak>
                    <img src="../img/user/default_head_pic.png" :src="aunt.picurl"/>
                    <p v-cloak>{{aunt.name}}</p>
                </div>
            </div> -->
            <div class="img_box" style="border-bottom: none" id="star_rate">
                <div class="uncle_img judge_box judge_box_active" v-if="totalStart >= 12">
                    <img src="../img/serve/s_nice.png" style="height: 20px"/>
                    <p class="judge_text">好评1</p>
                </div>
                <div class="uncle_img judge_box" v-else="totalStart >= 12">
                    <img src="../img/serve/nice.png" style="height: 20px"/>
                    <p class="judge_text">好评2</p>
                </div>
                <div class="uncle_img judge_box judge_box_active" v-if="totalStart >= 7 && totalStart <= 11">
                    <img src="../img/serve/s_so.png" style="height: 20px"/>
                    <p class="judge_text">中评1</p>
                </div>
                <div class="uncle_img judge_box" v-else="totalStart >= 7 && totalStart <= 11">
                    <img src="../img/serve/so.png" style="height: 20px"/>
                    <p class="judge_text">中评2</p>
                </div>
                <div class="uncle_img judge_box judge_box_active" v-if="totalStart <= 6">
                    <img src="../img/serve/s_bad.png" style="height: 20px"/>
                    <p class="judge_text">差评1</p>
                </div>
                <div class="uncle_img judge_box" v-else="totalStart <= 6">
                    <img src="../img/serve/bad.png" style="height: 20px"/>
                    <p class="judge_text">差评2</p>
                </div>
                <div class="pay_confirm" style="padding-top: 60px">
                    <div class="confirm_item">
                        <div class="confirm_title">服务水平</div>
                        <div class="confirm_text">
                            <div class="star star_s" v-for="i in levelStart" v-on:click="setLevel(i)"></div>
                            <div class="star" v-for="i in 5-levelStart" v-on:click="setLevel(levelStart+i)"></div>
                        </div>
                    </div>
                    <div class="confirm_item">
                        <div class="confirm_title">服务态度</div>
                        <div class="confirm_text">
                            <div class="star star_s" v-for="i in attitudeStart" v-on:click="setAttitude(i)"></div>
                            <div class="star" v-for="i in 5-attitudeStart" v-on:click="setAttitude(attitudeStart+i)"></div>
                        </div>
                    </div>
                    <div class="confirm_item">
                        <div class="confirm_title">准时到达</div>
                        <div class="confirm_text">
                            <div class="star star_s" v-for="i in timeStart" v-on:click="setTime(i)"></div>
                            <div class="star" v-for="i in 5-timeStart" v-on:click="setTime(timeStart+i)"></div>
                        </div>
                    </div>
                    <!-- 评论可选标签 -->
                    
                     <div class="uncle_img" v-for="name in listReason">
                       <a class="button my_handle serve_btn" v-on:click="change('name.name',$event)">{{name.name}}</a>
                     </div>
                    
                    <textarea class="handle_text" placeholder="上门很准时" v-model="content"></textarea>
                </div>
            </div>
            <div class="pay_button_box">
                <a class="button pay_button" href="#" v-on:click="submit">提交</a>
            </div>
        </div>
    </div>
</div>
</div>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script>
var server = new Vue({
    el: '#handle',
    data: {
        apiReasonUser: '/system/getReasonUser',
        apiCommentOrderUrl: '/order/commentOrder',
        orderid: getItem('order_comment_id'), listReason:[], levelStart:0, attitudeStart:0, timeStart:0, totalStart:0, reason:'', reasonList:[], content:''
    },
    mounted: function() {
    	
        this.$http.get(createUrl(this.apiReasonUser, {type: 1}, false))
            .then(function(response) {
                if (response.data.c == 1) {
                    var jsonData = JSON.parse(response.data.r);
                    this.listReason = jsonData.listReason;
                } else {
                    $.toast(response.data.m);
                }
            })
    },
    methods: {
        submit: function () {
            if (this.reason == "") {
                $.toast("请选择评论标签");
                return false;
            }
            this.$http.get(createUrl(this.apiCommentOrderUrl, {
                    userid: userId, orderid:this.orderid, reason:this.reason, content:this.content, score1:this.levelStart,score2:this.attitudeStart,score3:this.timeStart
                }, true))
                .then(function(response) {
                    if (response.data.c == 1) {
                        $.toast(response.data.m);
                        setTimeout(function () {
                        	window.history.back();
                        }, 1000);
                    }else{
                    	$.toast(response.data.m);
                    }
                })
        },
        setLevel: function (start) {
            this.levelStart = start;
            this.totalStart = this.levelStart + this.attitudeStart + this.timeStart;
            console.log(start)
        },
        setAttitude: function (start) {
            this.attitudeStart = start;
            this.totalStart = this.levelStart + this.attitudeStart + this.timeStart;
            console.log(start)
        },
        setTime: function (start) {
            this.timeStart = start;
            this.totalStart = this.levelStart + this.attitudeStart + this.timeStart;
            console.log(start)
        },
        change: function (a,e) {
            //改变按钮样式
            if($(e.currentTarget).hasClass('btn_active')){
                $(e.currentTarget).removeClass('btn_active')
            }else {
                $(e.currentTarget).addClass('btn_active')
            }
            if (!this.reasonList.contains(a)) {
                this.reasonList.push(a)
            } else {
                this.reasonList.removeByValue(a)
            }
            this.reason = this.reasonList.join(",");
        }

    }
});
$.init();
</script>
</body>
</html>

