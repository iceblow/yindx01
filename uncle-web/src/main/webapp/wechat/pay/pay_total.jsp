<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单结算</title>
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
        .pay_item:after{
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
        .pay_detail{width: 70%;margin: 0 auto;border-top: 1px solid #e6e6e6;margin-top: 30px;padding-top: 20px;padding-bottom: 5px;}
        .pay_chose_title{font-size: 16px;}
        .pay_button_box{padding: 10px 15px 15px 15px ;}
        .pay_button{width: 100%;height: 45px;line-height: 45px;background-image: url(../img/user/back_background.png);border: none;color: white}
        .pay_chose_after{position: absolute;right: 30px;color: #959495}
        .confirm_item{width: 100%;padding: 15px}
        .confirm_title{width: 20%;float: left;font-size: 14px;color: #959495}
        .confirm_text{float: left;width: 80%;font-size: 14px}
        .pay_way_number{font-size: 16px;text-align: center;margin-top: 30px;margin-bottom: 30px}
        .way_money{color: #ff5266}
        .pay_line{height: 15px;background-color: #ededed}


    </style>
</head>
<body>
<div class="page-group">

    <div class="page  page-current">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">服务费用结算</h1>
        </header>
        <div class="content" style="background-color: white">
            <p class="pay_title">支付金额</p>
            <p class="pay_title" style="margin-top: 10px"><span class="pay_money">158.5</span>元</p>
            <div class="pay_detail">
                <div class="pay_item">
                    <div class="detail_title">实际支付时间</div>
                    <div class="detail_text">1小时58分钟</div>
                </div>
                <div class="pay_item">
                    <div class="detail_title">实际支付时间</div>
                    <div class="detail_text">1小时58分钟</div>
                </div>
                <div class="pay_item">
                    <div class="detail_title">实际支付时间</div>
                    <div class="detail_text">1小时58分钟</div>
                </div>
            </div>
            <div class="list-block pay_chose" style="margin-top: 20px">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-media">
                                <img src="../img/user/del_place.png" height="20"/>
                            </div>
                            <div class="item-inner">
                                <div class="item-title label pay_chose_title">选择支付时间</div>
                                <div class="item-input">
                                    <input type="text" placeholder="选择手机" style="text-align: right" id='picker' readonly/>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <a href="" class="item-link item-content">
                            <div class="item-media">
                                <img src="../img/user/del_place.png" height="20"/>
                            </div>
                            <div class="item-inner">
                                <div class="item-title label pay_chose_title">选择优惠券</div>
                            </div>
                            <div class="pay_chose_after">3333</div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="pay_button_box">
                <a class="button pay_button" href="#pay_confirm">确认支付</a>
            </div>
        </div>
    </div>
    <div class="page" id="pay_confirm">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">支付定金</h1>
        </header>
        <div class="content" style="background-color: white">
            <div class="pay_confirm">
                <div class="confirm_item">
                    <div class="confirm_title">实际支付时间</div>
                    <div class="confirm_text">实际支实际支付时间实际支付时间实际支付时间实际支付时间实际支付时间付时间</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">实际支付时间</div>
                    <div class="confirm_text">实际支实际支付时间实际支付时间实际支付时间实际支付时间实际支付时间付时间</div>
                </div>
                <div class="confirm_item">
                    <div class="confirm_title">实际支付时间</div>
                    <div class="confirm_text">实际支实际支付时间实际支付时间实际支付时间实际支付时间实际支付时间付时间</div>
                </div>

            </div>
            <div class="pay_button_box">
                <a class="button pay_button" href="#pay_way">确认支付</a>
            </div>
        </div>
    </div>
    <div class="page" id="pay_way">
        <header class="bar bar-nav my_head">
            <!-- <a class="button button-link button-nav pull-left back">
                <span class="icon icon-left"></span>
            </a> -->
            <h1 class="title">支付中心</h1>
        </header>
        <div class="content" style="background-color: white">
            <p class="pay_way_number">支付金额￥<span class="way_money">200</span></p>
            <div class="pay_line"></div>
            <div class="list-block pay_chose" style="margin-top: 0">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-media">
                                <img src="../img/user/del_place.png" height="20"/>
                            </div>
                            <div class="item-inner">
                                <div class="item-title label pay_chose_title">选择支付时间</div>
                                <div class="item-input">
                                    <input type="text" placeholder="选择手机" style="text-align: right" id='' readonly/>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <a href="" class="item-link item-content">
                            <div class="item-media">
                                <img src="../img/user/del_place.png" height="20"/>
                            </div>
                            <div class="item-inner">
                                <div class="item-title label pay_chose_title">选择优惠券</div>
                            </div>
                            <div class="pay_chose_after">3333</div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</div>
</div>

<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script>
    $("#picker").picker({
        toolbarTemplate: '<header class="bar bar-nav">\
  <button class="button button-link pull-left">按钮</button>\
  <button class="button button-link pull-right close-picker">确定</button>\
  <h1 class="title">标题</h1>\
  </header>',
        cols: [
            {
                textAlign: 'center',
                values: ['一个月', '一个月', '一个月', '一个月', '一个月', '一个月', '一个月', '一个月', '一个月']
            }
        ]
    });
    $.init();
</script>
</body>
</html>

