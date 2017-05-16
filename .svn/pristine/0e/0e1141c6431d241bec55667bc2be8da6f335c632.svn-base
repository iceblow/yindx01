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
        .main_uncle_img{height: 30px;border-radius: 50%}
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
            <!-- 这里是页面内容区 -->
            <div class="page-index" style="position: relative">
                <div class="">
                    <div class="user_top" id="mapContainer"></div>
                    <div class="user_info">
                        <div class="user_head">
                            <div class="user_info_head" style="position: relative;height: auto">
                                <div class="list-block media-list inset" style="margin: 0">
                                    <ul>
                                        <li>
                                            <a href="../auth/go?uri=/wechat/location/add_list" class="item-link item-content">
                                                <div class="item-media"><img src="../img/serve/place_logo.png" width="30"></div>
                                                <div class="item-inner" v-if="address.name" v-cloak>
                                                    <div class="item-title-row">
                                                        <div class="item-title">{{address.name}}</div>
                                                    </div>
                                                    <div class="item-subtitle">{{address.detail}}</div>
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
            <div class="list-block" style="margin-top: 20px;margin-bottom: 60px">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label server_title">服务内容</div>
                                <div class="item-input">
                                    <input type="text" :value="sever_name" disabled>
                                    <a class="server_about " href="" data-popup=".popup-about">查看服务说明</a>
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
                                <div class="item-title label server_title">洗鞋内容</div>
                                <div class="item-input">
                                    <div class="server_more_box" v-for="item in list">
                                        <p>{{item.type}}-{{item.sub.name}}-{{item.num}}双</p>
                                        <img class="list_item_del" v-on:click="removeItem(item.id)" src="../img/user/red_del.png" />
                                    </div>
                                    <div class="jiadian_more text_placeholder" style="">
                                        <a class="open-popup" data-popup=".popup-about" style="color: #5e93f9">
                                            添加洗护内容<img src="../img/user/add_place.png" height="13" />
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label server_title">打赏(元)</div>
                                <div class="item-input">
                                    <input type="number" placeholder="非必填" v-model="tip_price">
                                </div>
                            </div>
                        </div>
                    </li>
<!--                     <li> -->
<!--                         <div class="item-content"> -->
<!--                             <div class="item-inner" style="padding: 20px 0"> -->
<!--                                 <div class="item-title label server_title">预计时长</div> -->
<!--                                 <div class="item-input" style="position: relative;"> -->
<!--                                     <span class="xiyi_time">1</span>小时 -->
<!--                                     <img class="add_num_img add_time" v-on:click="add_time(1)" src="../img/serve/up_logo.png" /> -->
<!--                                     <img class="reduce_num_img reduce_time" v-on:click="add_time(-1)" src="../img/serve/down_logo.png" /> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <div class="item-content"> -->
<!--                             <div class="item-inner"> -->
<!--                                 <div class="item-title label server_title">照片上传</div> -->
<!--                                 <div class="item-input" style="line-height: 100%;"> -->
<!--                                     <div class="uncle_more text_placeholder" v-if="imgList.length == 0" style="padding-top: 3px">上门需要只带擦鞋工具</div> -->
<!--                                     <img class="circle_chose" v-if="need_tools == 1" src="../img/serve/circle_select.png" v-on:click="tools(0)"  height="20"/> -->
<!--                                     <img class="circle_chose"  v-if="need_tools == 0" src="../img/serve/circle_unselect.png" v-on:click="tools(1)" height="20"/> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <div class="item-content open-popup" data-popup=".popup-uncle"> -->
<!--                             <div class="item-inner"> -->
<!--                                 <div class="item-title label server_title">选择技师</div> -->
<!--                                 <div class="item-input" style="line-height: 100%"> -->
<!--                                     <div class="server_uncle_box"> -->
<!--                                         <img class="main_uncle_img" v-for="uncle in uncle_list" src="../img/user/default_head_pic.png" :src="uncle.avatarurl" /> -->
<!--                                     </div> -->
<!--                                     <div class="uncle_more text_placeholder" v-if="uncle_list.length== 0">请选择公司/个体阿姨</div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </li> -->
                    <li>
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
                    <a class="button back_btn pay_btn open-popup" data-popup=".popup-pay" v-on:click="sendData()">支付定金{{server_price}}元</a>
                </div>
            </div>
        </div>
    </div>
    <div class="popup popup-about" id="chose_shoe">
        <header class="bar bar-nav my_head">
            <a class="button button-link button-nav pull-left close-popup">
                关闭
            </a>
            <a class="button button-link button-nav pull-right close-popup">
                选好了
            </a>
            <h1 class="title">清理内容选择</h1>
        </header>
        <div class="content sever_chose_page" id="getThird">
            <div class="serve_list_box">
                <div class="chose_title">
                    <ul class="server_scroll">
                        <li v-for="items in list" v-on:click="detailScroll($event)">{{items.name}}</li>
                    </ul>
                </div>
                <div class="chose_detail" style="overflow-y: scroll">
                    <div class="list-block contacts-block" style="margin-top: 0">
                        <div class="list-group">
                            <ul>
                                <li v-for="(items,x) in list">
                                    <p class="list-group-title">{{items.name}}</p>
                                    <div class="item-content" v-for="(item,y) in items.secondList">
                                        <div class="item-inner chose_detail_item-inner"  style="padding: 15px 5px">
                                            <div class="item-title">{{item.name}}</div>
                                            <div class="item-after shoe_chose_after"><span :id="item.id">0</span>双</div>
                                            <img class="add_num_img_after" v-on:click="add_shoe(item.id,x,y,1)" src="../img/serve/up_logo.png" />
                                            <img class="reduce_num_img_after" v-on:click="add_shoe(item.id,x,y,-1)" src="../img/serve/down_logo.png" />
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div style="clear: both"></div>
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
    <div class="popup popup-pay" id="con_info">
        <header class="bar bar-nav my_head">
            <a class="button button-link button-nav pull-left close-popup">
                <span class="icon icon-left"></span>
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
                    <div class="confirm_text">{{list.bookOrderInfo.auntList.length}}位</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">上门时间</div>
                    <div class="confirm_text">{{list.bookOrderInfo.server_time}}</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">服务时长</div>
                    <div class="confirm_text">{{list.bookOrderInfo.expect_time}}小时</div>
                </div>
                <div class="confirm_item" v-if="list.bookOrderInfo.book != null && list.bookOrderInfo.book.length > 0">
                    <div class="confirm_title">备注</div>
                    <div class="confirm_text">{{list.bookOrderInfo.book}}</div>
                </div>
                <div class="confirm_item" v-if="list.bookOrderInfo.tip_price != null && list.bookOrderInfo.tip_price.length > 0">
                    <div class="confirm_title">打赏小费</div>
                    <div class="confirm_text">{{list.bookOrderInfo.tip_price}}元</div>
                </div>
                
            </div>
            <div class="pay_button_box">
                <a class="button pay_button"  v-on:click="sendOrder">确认支付定金{{list.bookOrderInfo.money}}元</a>
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
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/moxie.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/plupload.full.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/i18n/zh_CN.js"></script>
<script type="text/javascript" src="../js/qiniujs/qiniu.js"></script>
<script type="text/javascript" src="../js/potoUpload.js"></script>
<script type='text/javascript' src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js' charset='utf-8'></script>
<script>
    var mapObj;
    setItem('server_url','../auth/go?uri=/wechat/serves/xielei');
    var add_id=getItem('server_address_id');
    var address_name=getItem('server_address_name');
    var address_address=getItem('server_address_address');
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
            longitude: getItem('user_lng'), latitude: getItem('user_lat'), serverid: 9, thirdids: ''
        },
        emulateJSON: true,
        mounted: function() {
        },
        methods: {
            getCustomers: function() {
                this.$http.get(createUrl(this.apiUrl, {longitude: this.longitude, latitude: this.latitude, serverid: service.server_id}, false))
                        .then(function(response) {
                            if (response.data.c == 1) {
                                var jsonData = JSON.parse(response.data.r);

                                this.canBookAuntList = addShow(jsonData.auntList);
                                console.log(jsonData)
                            } else {
                                $.toast(response.data.m);
                            }
                        })
            },
            set: function (item, id, e) {
                if (!this.ids.contains(id)) {
                    this.list.push(this.canBookAuntList[item]);
                    this.ids.push(id);
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
    //主vue
    var service = new Vue({
        el: '#service',
        data: {
            apiGetAddressListUrl: '/user/getAddressList',
            apiCanBookAuntListUrl: '/order/canBookAuntList',
            serveUrl:'/order/getServerCategory',
            bookUrl:'/order/booking',
            thirdUrl:'/order/getThirdCategory',
            userInfo: [], longitude: lng, latitude: lat, serverid:1, thirdids:'',
            address:{ name:address_name, detail: address_address},
            list:[],//陪护对象集合
            e_data:[],
            tip_price:'',
            uncle_list:uncle.list,
            ids:[],
            work_type:'',//正式单还是试工集合
            work_time:1,//工作天数
//            ids_name:get_first.ids_name,
            server_price:'',
            sever_name:'',
            server_text:'',//备注内容
            server_id:'',
            imgUrl:[],
            imgId:[],
            imgList:[],//上传照片的集合
            need_tools:0,
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
                this.$http.get(createUrl(this.serveUrl, {}, true))
                        .then(function(response) {
                            if (response.data.c == 1) {
                                var jsonData = JSON.parse(response.data.r);
//                                console.log(jsonData)
                                var server=jsonData.firstList[2].secondList[0];
                                this.sever_name= server.name;
                                conInfo.list.server_name = server.name;
                                this.server_price=server.deposit_price;
                                conInfo.list.server_price = server.deposit_price;
                                this.server_id=server.id;
                                uncle.getCustomers();
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
                                chose_shoe.list=val;
                                console.log(jsonData);
                            } else {
                                $.toast(response.data.m);
                            }
                        })
            },
            getAddressList: function() {
                this.$http.get(createUrl(this.apiGetAddressListUrl, {userid: userId}, true))
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
                                for(var key=0;key<jsonData.auntList.length;key++){
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
            tools:function (a) {
                this.need_tools=a;
            },
            removeItem:function (id) {
                chose_shoe.ids.removeByValue(id);
                for (var i in this.list){
                    if(this.list[i].id == id){
                        this.list.splice(i,1);
                        $('#'+id).html(0);
                    }
                }
            },
            imgSee:function (img_url,id) {
                console.log(this.imgList);
                var img_box="<div class='img_see_box'>" +
                        "<img src='"+img_url+"' class='img_see_img'/>" +
                        "<img class='close_img_box' onclick='closeImg()' src='../img/user/del_place.png' height='20px'/> " +
                        "<div class='img_del_text' onclick='delImg("+id+")'>" +
                        "删除此照片<img src='../img/serve/del_pic.png' height='15px' </div> </div>";
                $('body').append(img_box);

            },
            test:function () {
            },
            //已完成
            sendData:function () {
                //阿姨集合
                var auntID=[];
                for(var i=0;i<this.uncle_list.length;i++){
                  auntID.push(this.uncle_list[i].auntid)
                };
                //鞋子列表
                var list=[];
                for(var i=0;i<this.list.length;i++){
                    list.push({
                        id:this.list[i].id,
                        count:this.list[i].num,
                        name:this.list[i].sub.name
                    })
                }
                var bookDate={
                    userid:userId,
                    serverid: this.server_id,
                    addressid: add_id,
                    server_time:this.server_time[0]+'-'+this.server_time[1]+'-'+this.server_time[2]+' '+this.server_time[3]+':'+this.server_time[4]+':'+00,
                    book_type: 1,
                    app_type: 5,
                    picids:this.imgId.join(','),
                    expect_time:this.work_time,
                    need_tools:this.need_tools,
                    book:this.server_text,
                    tip_price:this.tip_price,
                    aunt_type:'1',
                    auntids:auntID.join(','),
                    expected_price:this.server_price,
                    third_json:JSON.stringify(list),
                };
                console.log(JSON.stringify(list));
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
            }
        }
    });
    //鞋子选择
    var chose_shoe = new Vue({
        el:'#chose_shoe',
        data:{
            list:'',
            shoeList:service.list,
            ids:[],
        },
        methods:{
            add_shoe:function (id,x,y,a) {
                var val=Number($('#'+id).html())+a;
                if(val<1){
                    $('#'+id).html(0);
                    if(this.ids.contains(id)){
                        for(var i in this.shoeList){
                            if(this.shoeList[i].id == id){
                                this.shoeList.splice(i,1);
                                this.ids.removeByValue(id);
                            }
                        }
                    }
                }else {
                    $('#'+id).html(val);
                    //增加种类
                    if(!this.ids.contains(id)){
                        this.shoeList.push({
                            type:this.list[x].name,
                            sub:this.list[x]['secondList'][y],
                            num:val,
                            id:id
                        });
                        this.ids.push(id);
                    }else {
                        //增加已存在种类的数量
                        for(var i in this.shoeList){
                            if(this.shoeList[i].id == id){
                                this.shoeList[i].num = val;
                            }
                        }
                    }

                }
            },
        }

    });
    //信息确定
    var conInfo=new Vue({
        el: '#con_info',
        data: {
            list:{
                server_name:'',
                server_price:'',
                server_aunt:uncle.list,
                server_time: service.server_time,
                server_address:service.address.detail,
                server_workTime:service.work_time,
                bookOrderInfo:''
            },
        },
        emulateJSON: true,
        methods: {
            sendOrder: function () {
//                 this.$http.get(createUrl(this.apiOrderUrl, {userid: userId, pay_type:2, tempid:service.orderInfo.tempid}, true))
//                         .then(function(response) {
//                             if (response.data.c == 1) {
//                                 var jsonData = JSON.parse(response.data.r);
//                                 // 生成订单后回调该方法发起支付功能
//                                 wx.chooseWXPay({
//                                     timestamp: jsonData.pay.timeStamp,
//                                     nonceStr: jsonData.pay.noncestr,
//                                     package: jsonData.pay.packagename,
//                                     signType: 'MD5',
//                                     paySign: jsonData.pay.sign, // 支付签名
//                                     success: function (res) {
//                                         // 支付成功后的回调函数
//                                     }
//                                 });
//                             }
//                         })
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
//            content: "<div class='map_img_box'><img src='../img/location/aunt_back.png'  width='36'/>" +
//            "<div class='map_img'><img src='../img/user/default_head_pic.png' /> </div> </div>",
            position: new AMap.LngLat(lng, lat)
        });
        myMarker.setMap(mapObj);  //在地图上添加点
    }
    initMap();
    // 微信支付
    /* wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '', // 必填，公众号的唯一标识
        timestamp: 1, // 必填，生成签名的时间戳
        nonceStr: '', // 必填，生成签名的随机串
        signature: '',// 必填，签名，见附录1
        jsApiList: ['checkJsApi', 'chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    }); */
    
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
</script>
<script src='../js/plugins/datetimePicker.js'></script>
</body>
</html>
