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
    <link rel="stylesheet" href="../css/bottom.css" />
    <link rel="stylesheet" href="../css/common.css" />
    <!--sui-ui-->
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/??sm.min.css,sm-extend.min.css">
    <link rel="stylesheet" href="../css/server.css" />
    <style>
        p{
            margin: 0;
        }
        .my_head{background-image: linear-gradient(to right, #78c6ff, #5d8ff9);}
        .user_top{width: 100%; height: 260px}
        .user_head{width: 100%;top: 4rem;background-color: #ffffff ;box-shadow:2px 2px 3px #aaaaaa;border: 1px solid #E3E5E4}
        .user_info_head{height: 5rem}
        .user_info{padding: 0rem 1rem ;position: absolute;bottom: -20px;width: 100%}
        .server_about{display: block;position: absolute;font-size: 12px;top:15px;right: 15px}
        .server_title{font-size: 14px;color: #BFBFBF}
        .main_uncle_img{height: 25px;width:25px;border-radius: 50%}
        .pay_btn{padding: 0 0.75rem}
        ::-webkit-scrollbar{width: 0}
        .uncle_small{font-size: 12px;color: #ccc;margin-left: 15px;margin-right: 20px}
        .chose_uncle{padding-top: 10px;padding-bottom: 10px}
        .my_uncle{padding-left: 45px;height: 30px;line-height: 30px;width: 50%;float: left;position: relative;border-right: 1px solid #eee;margin-bottom: 15px}
        .uncle_name{font-size: 15px;margin-left: 10px}
        .my_uncle_img{border-radius: 50%;height: 30px;position: absolute;left: 15px}
        .uncle_del{position: absolute;height: 20px;top: 5px;right: 15px}
        .back_btn{background-image: url("../img/user/back_background.png");border: none;border-radius: 0;margin: 0 auto;height: 40px;line-height: 40px;color: white}
        .list-block .item-input input{font-size: 14px !important}
        .list-block .item-input{font-size: 14px !important}
        .server_uncle_box{overflow: hidden;white-space: nowrap;text-overflow: ellipsis}
        /*地图样式*/
        .map_img_box{position: relative;}
        .map_img{position: absolute;width: 30px;top: 2px;left: 3px}
        .map_img img{width: 100%;border-radius: 100%;}
        .amap-logo,.amap-copyright { display: none!important; }
        .add_cl_photo{float: left;margin-right: 10px;}
        .text_placeholder{color: #959495}
        .server_more_box{padding: 10px 0;border-bottom: 1px solid #eee;position: relative}
        .jiadian_more{padding: 10px 0;color: #5e93f9;padding-left: 20px;position: relative}
        .jiadian_more img{position: absolute;height: 15px;left: 0;top: 12px}
        .chose_detail_item-inner{padding: 20px 15px 20px 10px !important;}
        .add_num_img{position: absolute;top: -8px;left: 8px}
        .reduce_num_img{position: absolute;bottom: -8px;left: 8px}
        .serve_list_box{overflow: scroll;}
        .list_item_del{position: absolute;height: 15px;right: 15px;top: 10px}
    </style>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page page-current" id="user_home">
        <div class="content" id="service" style="background-color: #ffffff">
            <!--弹窗-->
            <div class="my_alert" style="display: none">
                <div class="alert_box">
                    <div class="get_text"></div>
                    <img class="close_alert" src="../img/user/close.png" v-on:click="close"/>
                </div>
            </div>
            <!-- 这里是页面内容区 -->
            <div class="page-index" style="position: relative">
                <div class="">
                    <a class="back">
                        <img class="serve_back back" src="../img/serve/serve_back.png" />
                    </a>
                    <div class="user_top" id="mapContainer"></div>
                    <div class="user_info">
                        <div class="user_head">
                            <div class="user_info_head" style="position: relative;height: auto">
                                <div class="list-block media-list inset" style="margin: 0">
                                    <ul>
                                        <li>
                                            <a href="../auth/go?uri=/wechat/location/add_list" class="item-link item-content">
                                                <div class="item-media"><img src="../img/serve/place_logo.png" width="30"></div>
                                                <div class="item-inner" v-if="address.name != '' " v-cloak>
                                                    <div class="item-title-row">
                                                        <div class="item-title" v-cloak>{{address.name}}</div>
                                                    </div>
                                                    <div class="item-subtitle" v-cloak>{{address.detail}}</div>
                                                </div>
                                                <div class="item-inner" v-else="address.name" v-cloak>
                                                    <div class="item-title-row">
                                                        <div class="item-title">请设置你的服务地址</div>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-block" style="margin-top: 20px;">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label server_title">服务内容</div>
                                <div class="item-input">
                                    <input type="text" :value="sever_name" disabled>
                                    <a class="server_about " v-on:click="getHtml()">查看服务说明</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label server_title">上门时间</div>
                                <div class="item-input">
                                    <input type="text" placeholder="" id='datetime-picker' />
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title col-2-title label server_title">预计时长</div>
                                <div class="item-input" style="position: relative;margin-left: 20px">
                                    <input type="text" id="picker" value="2"  readonly>
                                    <div class="input_text">小时</div>
                                </div>
                            </div>
                            <div class="item-inner" v-if="judge == 1"  style="padding-left: 20px;border-left: 1px solid #eee;">
                                <div class="item-title label server_title">打赏(元)</div>
                                <div class="item-input" style="position: relative;margin-left: 20px">
                                    <input type="number" placeholder="非必填" v-model="tip_price">
                                </div>
                            </div>
                            <div class="item-inner create-actions" v-else="judge == 1" style="padding-left: 20px;border-left: 1px solid #eee;">
                                <div class="item-title label server_title" v-if="company_list.length == 0 && uncle_list.length == 0" style="width: 100%">请选择公司/个体阿姨</div>
                                <div class="server_uncle_box" v-else="company_list.length == 0 && uncle_list.length == 0">
                                    <img class="main_uncle_img" v-if="aunt_type ==2" src="../img/user/default_head_pic.png" :src="company_list[0].logourl" />
                                    <img class="main_uncle_img" v-if="aunt_type ==1" src="../img/user/default_head_pic.png" :src="uncle_list[0].avatarurl" />
                                </div>
                            </div>
                        </div>
                    </li>
                    <li v-if="needpic == 0">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label server_title">照片上传</div>
                                <div class="item-input" style="line-height: 100%;">
                                    <div style="width: 20px" id="container">
                                        <img id="pickfiles"  class="add_cl_photo" src="../img/serve/add_photo.png"  height="20"/>
                                    </div>
                                    <div class="my_photo">
                                        <img v-for="img in imgList" :src="img.url" v-on:click="imgSee(img.url,img.id)" height="20"/>
                                    </div>
                                    <div class="uncle_more text_placeholder" v-if="imgList.length == 0" style="padding-top: 3px">点击上传清洁照片</div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label server_title">备注</div>
                                <div class="item-input">
                                    <input type="text btn_input" v-model="server_text" placeholder="如有特殊要求在此输入">
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <div class="pay_btn">
                    <a class="button back_btn pay_btn"  v-on:click="sendData" v-cloak>支付定金{{server_price}}元</a>
                </div>
            </div>
        </div>
    </div>
    <div class="popup popup-uncle">
        <header class="bar bar-nav my_head">
            <a class="button button-link button-nav pull-left close-popup">
                关闭
            </a>
            <a class="button button-link button-nav pull-right close-popup">
                选好了
            </a>
            <h1 class="title">选择技师</h1>
        </header>
        <div class="content" id="add_address" style="background-color: white">
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
    <div class="popup popup-company">
        <header class="bar bar-nav my_head">
            <a class="button button-link button-nav pull-left close-popup">
                关闭
            </a>
            <a class="button button-link button-nav pull-right close-popup">
                选好了
            </a>
            <h1 class="title">选择公司</h1>
        </header>
        <div class="content" id="company" style="background-color: white">
            <div class="chose_uncle" style="position: relative" >
                <div class="my_uncle" v-for="(a,i) in list">
                    <img class="my_uncle_img" src="../img/user/default_head_pic.png" :src="a.logourl" height="30" />
                    <span class="uncle_name">{{a.name}}</span>
                    <img class="uncle_del" src="../img/user/red_del.png" :id="a.companyid" v-on:click="unSet(i, a.companyid,$event)"/>
                </div>
            </div>
            <div class="list-block media-list" style="margin-top: 0">
                <ul>
                    <li class="item-content" v-for="(company,item) in canBookCompanyList">
                        <div class="item-media">
                            <img class="uncle_img" src="../img/user/default_head_pic.png" :src="company.logourl"/>
                        </div>
                        <div class="item-inner">
                            <div class="item-title-row">
                                <div class="item-title">{{company.name}}<span class="uncle_small"></span></div>
                            </div>
                            <div class="item-text">{{company.profile}} </div>
                        </div>
                        <div class="item-after">
                            <a class="button" :id="company.companytid" v-if="company.show" v-on:click="set(item, company.companyid,$event)">预约</a>
                            <a class="button un_select_btn" :id="company.companytid" v-else="company.show">已选</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="popup popup-pay" id="con_info">
        <header class="bar bar-nav my_head">
            <a class="button button-link button-nav pull-left close-popup">
                <span class="icon icon-left" style="margin-top: 10px"></span>
            </a>
            <h1 class="title">支付定金</h1>
        </header>
        <div class="content" style="background-color: white">
            <div class="pay_confirm">
                <div class="confirm_item">
                    <div class="confirm_title">服务类型</div>
                    <div class="confirm_text">{{list.bookOrderInfo.servername}}</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">服务地址</div>
                    <div class="confirm_text">{{list.bookOrderInfo.address}}</div>
                </div>
                <div class="confirm_item" v-if="list.bookOrderInfo.auntList != null && list.bookOrderInfo.auntList.length > 0">
                    <div class="confirm_title">阿姨数量</div>
                    <div class="confirm_text">{{list.bookOrderInfo.auntList[0].name}}位</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">上门时间</div>
                    <div class="confirm_text">{{list.bookOrderInfo.server_time}}</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">服务时长</div>
                    <div class="confirm_text">{{list.bookOrderInfo.expect_time}}小时</div>
                </div>
            </div>
            <div class="pay_button_box">
                <a class="button pay_button" v-on:click="sendOrder">确认支付定金{{list.bookOrderInfo.money}}元</a>
            </div>
        </div>
    </div>
</div>

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<!--<script src="../js/jquery.scrollTo.js" />-->
<!--sui-ui-->
<script>var Zepto = jQuery</script>
<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/plugins/md5.js' charset='utf-8'></script>
<script type='text/javascript' src='../js/van-common.js' charset='utf-8'></script>
<script src="../js/vue/vue.min.js"></script>
<script src="../js/vue/vue-resource.min.js"></script>
<script src="//webapi.amap.com/maps?v=1.3&key=420ea50f88365ed4c968a99629aeb08f"></script>
<script src="//webapi.amap.com/ui/1.0/main.js"></script>
//图片上传
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/moxie.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/plupload.full.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/i18n/zh_CN.js"></script>
<script type="text/javascript" src="../js/qiniujs/qiniu.js"></script>
<script type="text/javascript" src="../js/potoUpload.js"></script>
<script type='text/javascript' src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js' charset='utf-8'></script>
<script>
    setItem('server_url','../auth/go?uri=/wechat/serves/daily');
    //    setItem('fast_state',1);
    var add_id=getItem('server_address_id');
    //    var address_name=getItem('server_address_name');
    //    var address_address=getItem('server_address_address');
    var mapObj;
    var marker = new Array();
    var windowsArr = new Array();
    var today = new Date();
    var lng = getItem('user_lng'), lat = getItem('user_lat'), city = getItem('user_city'), citycode = getItem('user_citycode');
    $.init();
    //阿姨选择
    var uncle = new Vue({
        el: '#add_address',
        data: {
            apiUrl: '/order/canBookAuntList',
            list: [], canBookAuntList: [], ids:[],
            longitude: lng, latitude: lat, serverid: 1, thirdids: ''
        },
        emulateJSON: true,
        mounted: function() {
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {longitude: this.longitude, latitude: this.latitude, serverid: service.server_id,}, false))
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

    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == "micromessenger") {
        var backurl = window.location.href;
        var wxjssign=new Vue({
            el: '#wx_jssign',
            data: {
                apiSignUrl: '/system/getWxPreperSign',
            },
            emulateJSON: true,
            methods: {
                wxjssign: function () {
                    this.$http.get(createUrl(this.apiSignUrl, {sign_backurl:backurl}, false))
                        .then(function(response) {
                            if (response.data.c == 1) {
                                var jsonData = JSON.parse(response.data.r);
                                var nonce_str = jsonData.nonce_str;
                                var appid = jsonData.appid;
                                var sign = jsonData.sign;
                                var timestamp = jsonData.timestamp;
                                wx.config({
                                    debug: false,
                                    appId: appid,
                                    timestamp:timestamp ,
                                    nonceStr: nonce_str,
                                    signature: sign,
                                    jsApiList: ['checkJsApi', 'chooseWXPay']
                                });

                                this.canBookCompanyList = addShow(jsonData.companyList);
                                console.log(888)
                                console.log(this.canBookCompanyList)
                            }
                        })
                }
            }
        });
        wxjssign.wxjssign();
    }

    //公司选择
    var company = new Vue({
        el: '#company',
        data: {
            apiUrl: '/order/canBookCompanyList',
            list: [], canBookCompanyList: [], ids:[],
            longitude: lng, latitude: lat, serverid: 1, thirdids: ''
        },
        emulateJSON: true,
        mounted: function() {
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {longitude: this.longitude, latitude: this.latitude, serverid: service.server_id,}, false))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.canBookCompanyList = addShow(jsonData.companyList);
                            console.log(888)
                            console.log(this.canBookCompanyList)
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            set: function (item, id, e) {
                if (!this.ids.contains(id)) {
                    this.list.push(this.canBookCompanyList[item]);
                    this.ids.push(id)
                    this.canBookCompanyList = setHideCompany(this.canBookCompanyList, id)
                }
            },
            unSet: function (item, id,e) {
                console.log(item+ " "+ id);
                this.list.splice(item, 1);
                this.ids.removeByValue(id);
                this.canBookCompanyList = setShowCompany(this.canBookCompanyList, id)
            },
            selected: function () {
                console.log(this.list);
                setItem('company', this.list);

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
    function setShowCompany(data, id) {
        for (var key in data) {
            if (data[key].companyid == id) {
                data[key].show = true;
            }
        }
        return data
    }
    function setHideCompany(data, id) {
        for (var key in data) {
            if (data[key].companyid == id) {
                data[key].show = false;
            }
        }
        return data
    }
    //主vue
    var service = new Vue({
        el: '#service',
        data: {
            getWebhtmlUrl:'/system/getWebHtml',
            apiGetAddressListUrl: '/user/getAddressList',
            apiCanBookAuntListUrl: '/order/canBookAuntList',
            serveUrl:'/order/getServerCategory',
            bookUrl:'/order/booking',
            thirdUrl:'/order/getThirdCategory',
            userInfo: [], longitude: lng, latitude: lat, serverid:1, thirdids:'',
            address:{ name: getItem('server_address_name'), detail: getItem('server_address_address')},
            judge:getItem('judge'),//是否快速下单判断值
            list:[],//陪护对象集合
            e_data:[],
            fast_state:getItem('fast_state'),
            tip_price:'',
            uncle_list:uncle.list,
            company_list:company.list,
            ids:'',
            work_type:'',//正式单还是试工集合
            work_time:'',//工作天数
            aunt_type:1,
//            ids_name:get_first.ids_name,
            imgUrl:[],
            server_price:'',
            sever_name:'',
            server_text:'',
            server_id:'',
            imgId:[],
            imgList:[],//上传照片的集合
            needpic:'',//是否需要照片
            bookList:'',//服务人员合集
            orderInfo: {},
            server_time:[today.getFullYear(), formatNumber(today.getMonth()+1), formatNumber(today.getDate()), formatNumber(getTodayHours()), formatNumber(today.getMinutes())],
        },
        emulateJSON: true,
        mounted: function() {
            this.getServeInfo();
//            this.getAddressList();
        },
        methods: {
            getServeInfo:function () {
                this.$http.get(createUrl(this.serveUrl, {city:getItem('service_city')}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
//                                console.log(jsonData)
                            var server=jsonData.firstList[0].secondList[2];
                            this.sever_name= server.name;
                            this.needpic=server.needpic;
                            conInfo.list.server_name = server.name;
                            this.server_price=server.deposit_price;
                            conInfo.list.server_price = server.deposit_price;
                            this.server_id=server.id;
                            setItem('choseId',server.id)
                            uncle.getCustomers();
                            company.getCustomers();
                            this.canBookAuntList();
                            this.getThird();
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            getThird:function () {
                this.$http.get(createUrl(this.thirdUrl, {serverid: this.server_id}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            var val=jsonData.firstList;
                            console.log(jsonData);
                            this.e_data=val;
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            getAddressList: function() {
                this.$http.get(createUrl(this.apiGetAddressListUrl, {userid: userId, categoryid: 1}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            if (jsonData.addressList.length) {
                                this.address.name = jsonData.addressList[0].addressname;
                                this.address.detail = jsonData.addressList[0].addressdetail;
                            }
//                                console.log(jsonData)
                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            canBookAuntList: function () {
                this.$http.get(createUrl(this.apiCanBookAuntListUrl, {longitude: lng, latitude: lat, serverid:this.server_id}, false))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            for(var key=0; key<jsonData.auntList.length; key++) {
                                marker = new AMap.Marker({
                                    icon: new AMap.Icon({  //复杂图标
                                        size: new AMap.Size(52, 52),//图标大小
                                        imageSize: new AMap.Size(26,26),
                                        image: jsonData.auntList[key].avatarurl, //大图地址
                                        imageOffset: new AMap.Pixel(0, 13)//相对于大图的取图位置
                                    }),
                                    content: "<div class='map_img_box'><img src='../img/location/aunt_back.png'  width='36'/>" +
                                    "<div class='map_img'><img src='../img/user/default_head_pic.png' /> </div> </div>",
                                    position: new AMap.LngLat(jsonData.auntList[key].longitude, jsonData.auntList[key].latitude)
                                });
                                marker.setMap(mapObj);  //在地图上添加点
                            }
                            console.log(jsonData.auntList)

                        } else {
                            $.toast(response.data.m);
                        }
                    })
            },
            getHtml:function () {
                this.$http.get(createUrl(this.getWebhtmlUrl, {relation_id:this.server_id, type: 1}, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            $('.my_alert').show();
                            $('.get_text').append(jsonData.content);
                            console.log(this.tip_price)
                        }
                    })
            },
            add_time:function (a) {
                var val=Number($('.xiyi_time').html())+a;
                if(val<1){
                    $('.xiyi_time').html(0);
                    this.work_time=0;
                }else {
                    $('.xiyi_time').html(val)
                    this.work_time=val;
                }
            },
            getType:function (type,e) {
                this.work_type=type;
                $('.server_btn').removeClass('btn_active');
                $(e.currentTarget).addClass('btn_active');
            },
            sendData:function () {
                var auntID=[];
                var companyID=[];
                var bookID=[];
                for(var i=0;i<this.uncle_list.length;i++){
                    auntID.push(this.uncle_list[i].auntid)
                };
                for(var j=0;j<this.company_list.length;j++){
                    companyID.push(this.company_list[j].companyid)
                };
                if(this.aunt_type == 1){
                    if(auntID.length ==0){
                        bookID = '';
                    }else {
                        bookID = auntID[0];
                    }
                    this.bookList=this.uncle_list;
                }else {
                    if(companyID.length ==0){
                        bookID = '';
                    }else {
                        bookID = companyID[0];
                    }
                    this.bookList=this.company_list;
                }
                conInfo.list.server_aunt=this.bookList;
                var add_fast=getItem('add_fast');
                if(this.address.name == ''){
                    $.alert('请选择服务地址')
                    return false;
                }
                if(conInfo.list.server_aunt.length == 0 && add_fast ==1){
                    $.alert('该地区不支持快速下单')
                    return false;
                }
                $.popup('.popup-pay');
                var bookDate={
                    userid:userId,
                    serverid: this.server_id,
                    addressid: add_id,
                    server_time:this.server_time[0]+'-'+this.server_time[1]+'-'+this.server_time[2]+' '+this.server_time[3]+':'+this.server_time[4]+':'+00,
                    book_type: 1,
                    app_type: 5,
                    picids:this.imgId.join(','),
                    expect_time:this.work_time,
                    book:this.server_text,
                    aunt_type:this.aunt_type,
                    tip_price:this.tip_price,
                    auntids:bookID,
                    expected_price:this.server_price
                };
                this.$http.get(createUrl(this.bookUrl, bookDate, true))
                    .then(function(response) {
                        if (response.data.c == 1) {
                            var jsonData = JSON.parse(response.data.r);
                            this.orderInfo = jsonData.orderInfo;
                            conInfo.list.bookOrderInfo = jsonData.orderInfo;
                            console.log(jsonData)
                        } else {
                            $.toast(response.data.m);
                        }
                    })
                console.log(this.imgId)
            },
            imgSee:function (img_url,id) {
                console.log(this.imgList);
                var img_box="<div class='img_see_box'>" +
                    "<img src='"+img_url+"' class='img_see_img'/>" +
                    "<img class='close_img_box' onclick='closeImg()' src='../img/user/red_del.png' height='20px'/> " +
                    "<div class='img_del_text' onclick='delImg("+id+")'>" +
                    "删除此照片<img src='../img/serve/del_pic.png' height='15px' </div> </div>";
                $('body').append(img_box);

            },
        }
    });

    var openId = getItem('openid');
    //信息确定
    var conInfo=new Vue({
        el: '#con_info',
        data: {
            apiOrderUrl: '/order/order',
            list:{
                server_name:'',
                server_price:'',
                server_aunt:'',
                server_time: service.server_time,
                server_address:service.address.detail,
                server_workTime:service.work_time,
                bookOrderInfo:''
            },
        },
        emulateJSON: true,
        methods: {
            sendOrder: function () {
//                 this.$http.get(createUrl(this.apiOrderUrl, {userid: userId, pay_type:4, openid:openId,tempid:service.orderInfo.tempid}, true))
//                     .then(function(response) {
//                         if (response.data.c == 1) {
//                             var jsonData = JSON.parse(response.data.r);
//                             // 生成订单后回调该方法发起支付功能
//                             wx.chooseWXPay({
//                                 timestamp: jsonData.pay.timeStamp,
//                                 nonceStr: jsonData.pay.noncestr,
//                                 package: jsonData.pay.packagename,
//                                 signType: 'MD5',
//                                 paySign: jsonData.pay.sign, // 支付签名
//                                 success: function (res) {
//                                     // 支付成功后的回调函数
//                                 }
//                             });

//                             console.log(this.jsonData)
//                         }
//                     })
            	window.location.href = '../auth/go?uri=/wechat/serves/paytype&tempid=' + service.orderInfo.tempid + '&money=' + this.list.bookOrderInfo.money;
            }
        }
    });


    function initMap() {
        mapObj = new AMap.Map("mapContainer",{
            center:new AMap.LngLat(lng, lat), //地图中心点
            level:15  //地图显示的比例尺级别
        });
        // 定位自己
//        circle = new AMap.Circle({   //圆形编辑器的样式
//            map: mapObj,
//            center:new AMap.LngLat(lng,lat),
//            radius:500,
//            strokeColor: "#C0D7F5",
//            strokeOpacity: 1,
//            strokeWeight: 1,
//            fillColor: "EDF0F3",
//            fillOpacity: 0.1
//        });
//        mapObj.plugin(["AMap.CircleEditor"],function(){
//            circleEditor = new AMap.CircleEditor(mapObj, circle);   //创建圆形编辑器对象
//            circleEditor.open();    //打开圆形编辑器
//        });

        // 替换图标
        myMarker = new AMap.Marker({
            icon: new AMap.Icon({  //复杂图标
                size: new AMap.Size(52, 52),//图标大小
                imageSize: new AMap.Size(26,26),
                image: "../img/location/minemap.png", //大图地址
//                image: "http://ofc6sbq4f.bkt.clouddn.com/25D4C9789AD3EA58E91AC5EFF4E783B3",
                imageOffset: new AMap.Pixel(0, 13)//相对于大图的取图位置
            }),
            position: new AMap.LngLat(lng, lat)
        });
        myMarker.setMap(mapObj);  //在地图上添加点
    }
    initMap();
    // 微信支付

    /*  wx.config({
     debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
     appId: '', // 必填，公众号的唯一标识
     timestamp: 1, // 必填，生成签名的时间戳
     nonceStr: '', // 必填，生成签名的随机串
     signature: '',// 必填，签名，见附录1
     jsApiList: ['checkJsApi', 'chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
     }); */

    wx.ready(function(){

    });
    wx.error(function(res){

    });
    wx.checkJsApi({
        jsApiList: ['chooseWXPay'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
        success: function(res) {
            // 以键值对的形式返回，可用的api值true，不可用为false
            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
        }
    });

    //选择时间天数
    $("#picker").picker({
        toolbarTemplate: '<header class="bar bar-nav">\
  <button class="button button-link pull-left close-picker">取消</button>\
  <button class="button button-link pull-right close-picker">确定</button>\
  </header>',
        cols: [
            {
                textAlign: 'center',
                values: ['2','3','4','5','6','7','8'],
                displayValues: ['2小时','3小时','4小时','5小时','6小时','7小时','8小时']
            }
        ],
        onChange:function (values, displayValues) {
            service.work_time = displayValues[0];
            conInfo.list.server_workTime = displayValues[0];
        }
    });
    //选择阿姨还是公司
    $(document).on('click','.create-actions', function () {
        if(add_id == ''){
            $.alert('未选择服务地址');
            return false
        }
        var buttons1 = [
            {
                text: '公司',
                bold: true,
                onClick: function() {
                    $.popup('.popup-company');
                    service.aunt_type = 2;
                    console.log(service.aunt_type)
                }
            },
            {
                text: '个体阿姨',
                onClick: function() {
                    $.popup('.popup-uncle');
                    service.aunt_type = 1;
                }
            }
        ];
        var buttons2 = [
            {
                text: '取消',
            }
        ];
        var groups = [buttons1, buttons2];
        $.actions(groups);
    });
    //弹窗关闭
    $('.close_alert').click(function () {
        $('.my_alert').hide();

    })
</script>
<script src='../js/plugins/datetimePicker.js'></script>
</body>
</html>
