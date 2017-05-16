<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title></title>
	<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
	<style id="css_index" index="index" type="text/css">
	    *{
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        html,body{height:100%}
        html{overflow-y:auto}
        body{font:18px "宋体";background:#fff;line-height: 25px;min-width: 1000px;/*color: #8b8b8b;*/}
        body,p,form,ul,li{margin:0;padding:0;list-style:none}
        a{ color: #8b8b8b;text-decoration: none;}

        #header .header{height:60px;width:100%;background:#fff;padding:10px;}
        .head-left{width:40%;float:left;text-align:center;height:100%;}
        .head-left img{height:90%;}
        .head-center{width:40%;height:100%;float:left;vertical-align: middle;font-size:16px;}
        .head-right{width:20%;float:left;font-size:12px;}
        .head-center a{line-height: 60px;padding:0 15px;}
        .head-right a{line-height: 60px;padding:0 10px;}


        #content{min-height: 500px;background: #f7f8fa;	padding: 100px;}


        .content-title{font-size: 35px;text-align: center;padding: 60px 0;}

        #footer{width:100%;padding: 30px 0;background-color: #323337;text-align: center;font-weight: bold; color: #7e8287;vertical-align: top;}
        .footer{font-size: 10px;line-height: 20px;}


        .housekeeping{
            font-size: 18px;
            margin-left: 45px;
        }
        .list_style{
            margin-top: 15px;
        }
        .triangle{
            display: none;
        }
        .clean_kind_box{
            margin-top: 35px;
            margin-left: 10px;
        }
        .clean_kind{
            width: 140px;
            height: 140px;
            text-align: center;
            color: #5288f8;
            display: inline-block;
            /*background: #f1f9ff;*/
            border-radius: 10px;
        }
        .clean{
            margin-top: 10px;
        }
        .clean_text{
            margin-top: 5px;
        }


        .content_box{
            width: 90%;
            margin: 0 auto;
            margin-top: 10px;
        }
        .list{
            margin-bottom: 10px;
            font-size: 22px;
        }
        .prompt{
            font-size: 16px;
            border: 1px dashed #5288f8;
            padding: 15px;
            max-width: 705px;
        }
        .table1{
            width: 100%;
            margin: 15px 0;
            border-top: 1px solid #837d7d;
            border-left: 1px solid #837d7d;
        }
        .table1 td {
            font-size: 18px;
            color: #837d7d;
            height: 30px;
            padding-left: 15px;
            border-bottom: 1px solid #837d7d;
            border-right: 1px solid #837d7d;
        }

        .pic_box{
            float: left;
            text-align: center;
            margin-right: 15px;
        }
        .pic_box>img{
            width: 170px;
            height: 170px;
        }
        
        .appointment_btn{
            width: 430px;
            height: 65px;
            text-align: center;
            line-height: 65px;
            font-size: 30px;
            color: white;
            background: #76c2ff;
            margin: 70px auto 30px;
            border-radius: 7px;        
        }
        
        .serverIntroduce{
		    color:#96b8fb;
		}
         /* 左侧边栏  */
		#jzh_list2_detail,#jzh_list3_detail{
		    display: none;		
		}
		#wx_list1_detail,#wx_list2_detail{
		    display: none;		
		}
		#chqg_list1_detail,#chqg_list2_detail{
		    display: none;		
		}
		/* 详情 */
		#science_clean_content,#jiadian_clean_content{
		    display: none;
		}
		#feast_helper_content,#banque_helper_content{
		    display: none;			
		}
		#household_washing_content,#shoes_upkeep_content{
		    display: none;	
		}
		#pipeline_fix_content,#hydroelectric_fix_content{
		    display: none;		
		}
		#childcare_providers_content,#moon_woman_content{
		    display: none;		
		}
		#patient_care_content{
		    display: none;		
		}
		
		.item{
            text-indent: 20px;
        }
	</style>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="content">
    <div style="width: 90%;">
        <div style="width: 25%;float: left;">
            <div style="width: 100%;margin: 0 auto;"class="left_list_box">
                <h2 style="font-size: 32px;">家政钟点</h2>
                <ul class="housekeeping">
                    <li class="list_style" style="font-size: 22px;color:#5288f8" id="jzh_list_1">居家保洁
                        <img src="img/triangle.png" class="triangle" style="display: inline-block;">
                    </li>
                    <li class="list_style" id="jzh_list_2">你忙我帮
                        <img src="img/triangle.png" class="triangle">
                    </li>
                    <li class="list_style" id="jzh_list_3">绿色洗护
                        <img src="img/triangle.png" class="triangle">
                    </li>
                  
                </ul>
                
                <h2 style="font-size: 32px; margin-top: 100px;">维修服务</h2>
                <ul class="housekeeping">
                    <li class="list_style" id="wx_list_1">家庭维修
                        <img src="img/triangle.png" class="triangle">
                    </li>
                    <li class="list_style" id="wx_list_2">上门开锁
                        <img src="img/triangle.png" class="triangle">
                    </li> 
                </ul>
                
                 <h2 style="font-size: 32px; margin-top: 100px;">长期工</h2>
                <ul class="housekeeping">
                    <li class="list_style" id="chqg_list_1">温馨护理
                        <img src="img/triangle.png" class="triangle">
                    </li>
                    <li class="list_style" id="chqg_list_2">贴心陪护
                        <img src="img/triangle.png" class="triangle">
                    </li>
                </ul>
                
             </div>
          </div>

         <div style="width: 75%;float: left;background: #ffffff;padding: 70px 0 70px 50px;box-sizing: border-box;">
            <h1 style="font-size: 37px;color: #8c847f;">服务介绍</h1>
            
            <!-- 家政钟点-->            
            <!-- 居家保洁 -->
            <div id="jzh_list1_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">居家保洁</h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="day_clean" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">日常清洁</div>
                    </div>
                    <div class="clean_kind" id="science_clean">
                        <img src="img/clean2.png" class="clean">
                        <div class="clean_text">科学擦窗</div>
                    </div>
                    <div class="clean_kind" id="jiadian_clean">
                        <img src="img/clean3.png" class="clean">
                        <div class="clean_text">家电清理</div>
                    </div>
                </div>
                
                <!-- 日常清洁 -->
                 <div class="content_box" id="day_clean_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="margin: 10px 0;">35元/小时</p>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.按时计费根据业务需求将实时调整价格，节假日会有所上涨，2小时起算，不足2小时按2小时计费；</p>
                            <p>2.受距离因素（城区与城郊）等原因影响，价格会略有浮动</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【参考用时】</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000">建筑面积（㎡）</td>
                                <td style="color: #000">参考耗时（按单人次计）</td>
                            </tr>
                            <tr>
                                <td>0~60</td>
                                <td>2~3小时</td>
                            </tr>
                            <tr>
                                <td>61~120</td>
                                <td>3~4小时</td>
                            </tr>
                            <tr>
                                <td>121~200</td>
                                <td>2~3小时</td>
                            </tr>
                            <tr>
                                <td>201以上</td>
                                <td>2~3小时</td>
                            </tr>
                        </table>
                        <div class="prompt">
                            <p>温馨提示：打扫所需时间还与屋内物品多少、洁净程度、特殊要求等因素有关，具体需以实际为准，请您根据自身情况，预估一下大致时间</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1、厨房：灶台清理，橱柜擦拭，水槽清理，厨具清洗，水龙头除垢</p>
                            <p>2、卫生间：马桶、洗手台清理，镜面擦拭，墙面清理，淋浴蓬头及水龙头清理</p>
                            <p>3、房间：寝具衣物整理， 房间物品整理摆放</p>
                            <p>4、客厅沙发茶几物品整理</p>
                            <p>5、餐厅清理 </p>
                            <p>6、阳台杂物整理，栏杆内表面灰尘擦拭 </p>
                            <p>7、地面清理（木质地板只打扫表面），地脚线开关擦拭</p>
                            <p>8、家具表面，门墙表面灰尘清理</p>
                            <p>9、垃圾倾倒</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean1.png" >
                                <p>客厅</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean2.png" >
                                <p>房间</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean3.png" >
                                <p>地板</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean4.png" >
                                <p>阳台</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean5.png" >
                                <p>厨房</p>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>1.实际阿姨服务项目根据客户具体要求，若超出以上范围则由双方协商收费，可线下结算；</p>
                            <p>2.不包括插电电器清理，家具保养，天花板吊灯吊扇清理，屋顶清扫以及玻璃的擦拭，古董宗教陈设等贵重物品清理；</p>
                            <p>3.超过一个月未进行日常保洁的用户，可能需要深度清理，故所需服务时间会适当延长</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【验收标准】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1、厨房卫生间无明显污渍油渍水痕</p>
                            <p>2、卫生间洗手台、地面无水渍，墙面镜面无污渍</p>
                            <p>3、房间衣物鞋帽寝具摆放整齐，无异味 </p>
                            <p>4、客厅餐厅物件摆放整齐，桌椅地板地面无灰尘无水渍</p>
                            <p>5、阳台杂物摆放整齐，栏杆表面没有灰尘</p>
                            <p>6、家具、门墙表面无灰尘</p>
                            <p>7、全部垃圾倾倒完毕</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>               
            
            <!-- 科学擦窗 -->          
                 <div class="content_box" id="science_clean_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="margin: 10px 0;">40元/小时</p>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.按时计费根据业务需求将实时调整价格，节假日会有所上涨，2小时起算，不足2小时按2小时计费；</p>
                            <p>2.若此项擦窗服务包含其他日常清洁，则其他清洁项目也按照擦窗服务项目收费，即40元/小时；建议擦窗与日常保洁分别下单，分开计算。</p>
                            <p>3.此项目仅针对家庭用户。</p>
                        </div>
                    </div>                    
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>玻璃、窗框、防盗窗、纱窗清尘除垢</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean_w1.png" >
                                <p>客厅</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/home_clean_w2.png" >
                                <p>房间</p>
                            </div>                            
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>1.专业的擦窗服务需要专业玻璃擦、玻璃刮、清洁剂等工具（不同于普通清洁）</p>
                            <p>2.本服务不提供高空室外作业，如有额外要求请提前联系客服。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【验收标准】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1.玻璃表面无灰尘、无手印、无水渍、无胶印</p>
                            <p>2.玻璃边框、窗槽、防盗窗干净无污渍</p>
                            <p>3.操作区域干净整洁</p>
                            <p>4.纱窗表面无灰尘、无油渍。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>  
                 
                  <!-- 家电清理-->          
                 <div class="content_box" id="jiadian_clean_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【总体业务介绍】</p>
                        <div style="font-size: 16px;">
                            <p>1、厨房：油烟机、冰箱、电饭锅、微波炉、电热壶（除垢）、烤箱等</p>
                            <p>2、客厅：饮水机、空调（不含外机）、影像用品（表面除尘）、吊扇电扇（表面除尘、扇叶清洗）等</p>
                            <p>3、卫生间：洗衣机、热水器（表面除尘）等</p>
                            <p>4、其他：冷暖风机、台灯等</p>
                        </div>
                    </div>
                    <!-- 油烟机 -->
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【油烟机】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1.服务价格：侧吸式175元/台、中/欧式125元/台</p>
                            <p>2.清洗道具：专业清洁剂、高温蒸汽机（消毒用）、抹布等</p>
                            <p>3.验收标准：油烟机顶部、所有表面无污渍、无水痕，油盒、滤网干净无污渍，无油渍</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_oil1.png" >
                                <p>侧吸式</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_oil2.png" >
                                <p>中/欧式</p>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>①  请参考油烟机类别下单，若上门发现预约类型与实际不符，则以实际类型为准</p>
                            <p>②　如超出下单业务，请待服务人员上门后自行线下沟通，费用可线下结算</p>
                            <p>③　其中涉及到的特殊材料、工具等由家政公司/平台提供（不同家政公司可能会有出入）</p>
                        </div>
                    </div>
                    <!-- 冰箱 -->
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【冰箱】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1.服务价格：单门80元/台，双开门100元/台，三开门120元/台，对开门150元/台</p>
                            <p>2.特色清洗：除冰除臭、杀菌消毒、整理</p>
                            <p>3.清洗道具：专业清洁剂、高温蒸汽机（消毒用）、小刷子、抹布、酒精、水+醋（1:1）等</p>
                            <p>4.验收标准：冰箱表面无污渍、无灰尘，内部无污渍无异味</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_icebox1.png" style="width: 80%;">
                                <p>单开门式</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_icebox2.png" >
                                <p>双开门式</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_icebox3.png" >
                                <p>三开门式</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_icebox4.png" >
                                <p>对开门式</p>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>①　请参考冰箱类别下单，若上门发现预约类型与实际不符，则以实际类型为准</p>
                            <p>②　如超出下单业务，请待服务人员上门后自行线下沟通，费用可线下结算</p>
                            <p>③　其中涉及到的特殊材料、工具等由家政公司/平台提供（不同家政公司可能会有出入）</p>
                        </div>
                    </div>
                    <!-- 空调 -->
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【空调】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                             <p>1.服务价格：挂式100元/台、立式150元/台、中央100元/风口</p>
                             <p>2.特色清洗：拆卸滤网、泡沫清洗、高温消毒</p>
                             <p>3.清洗道具：专业清洁剂、高温蒸汽机（消毒用）、抹布等</p>
                             <p>4.验收标准：空调表面干净无污渍，过滤网清洁干净</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box" style=" margin-top: 113px;">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_aircon1.png" style=" height: auto;">
                                <p>挂式</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_aircon2.png" >
                                <p>立式</p>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>①　中央空调3口起约，不足3口，按3口计算</p>
                            <p>②　请参考空调类别下单，若上门发现预约类型与实际不符，则以实际类型为准</p>
                            <p>③　如超出下单业务，请自行线下与服务人员沟通，费用可线下结算</p>
                            <p>④　其中涉及到的特殊材料、工具等由家政公司提供（不同家政公司可能会有出入）</p>
                        </div>
                    </div>
                     <!-- 洗衣机 -->
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【洗衣机】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                             <p>1.服务价格：滚筒225元/台、波轮150元/台</p>
                             <p>2.特色清洗：无缝洗尘、专业除垢、高温杀菌</p>
                             <p>3.清洗道具：专业清洁剂、高温蒸汽机（消毒用）、抹布等</p>
                             <p>4.验收标准：洗衣机外壳及内筒壁干净无异味无污渍，过滤网内无杂物</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_washer1.png">
                                <p>滚筒</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_washer2.png">
                                <p>波轮</p>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>①　以上洗衣机均不带烘干功能，滚筒洗衣机为非拆卸清洗</p>
                            <p>②　请参考洗衣机类别下单，若上门发现预约类型与实际不符，则以实际类型为准</p>
                            <p>③　如超出下单业务，请自行线下与服务人员沟通，费用可线下结算</p>
                            <p>④　其中涉及到的特殊材料、工具等由家政公司/平台提供（不同家政公司可能会有出入）</p>
                        </div>
                    </div>
                     <!-- 微波炉、饮水机等小家电 -->
                     <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【微波炉、饮水机等小家电】</p>
                        <p>1.服务价格：</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000">家电名称</td>
                                <td style="color: #000">定价（元/台）</td>
                            </tr>
                            <tr>
                                <td>饮水机</td>
                                <td>30</td>
                            </tr>
                            <tr>
                                <td>微波炉</td>
                                <td>30</td>
                            </tr>
                            <tr>
                                <td>电饭煲</td>
                                <td>30</td>
                            </tr>
                            <tr>
                                <td>电磁炉</td>
                                <td>30</td>
                            </tr>
                             <tr>
                                <td>......</td>
                                <td></td>
                            </tr>
                        </table>
                        <p>2.特色清洗：除垢除味、彻底清洗、杀菌消毒</p>
                        <p>3.清洗道具：专业清洁剂、高温蒸汽机（消毒用）、工具箱、抹布</p>
                        <p>4.验收标准：表面无污渍无灰尘，内部无垢无异味</p>        
                    </div>
                    <div class="list">
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_small1.png">
                                <p>微波炉</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_small2.png" >
                                <p>饮水机</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_small3.png" >
                                <p>电水壶</p>
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/appliance_small4.png" >
                                <p>电饭煲</p>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt" style="margin-top: 30px;">
                            <p>温馨提示：</p>
                            <p>①　如超出以上业务，请在下单时备注或自行与服务人员沟通，费用可线下结算</p>
                            <p>②　其中涉及到的特殊材料、工具等由家政公司/平台提供（不同家政公司可能会有出入）</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                                                                                    
                 </div>              
                 
              </div>
              
              
            <!-- 你忙我帮-->
            <div id="jzh_list2_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">你忙我帮</h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="cooking" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">买菜做饭</div>
                    </div>
                    <div class="clean_kind" id="feast_helper">
                        <img src="img/clean2.png" class="clean">
                        <div class="clean_text">酒席帮工</div>
                    </div>
                    <div class="clean_kind" id="banque_helper">
                        <img src="img/clean3.png" class="clean">
                        <div class="clean_text">宴会帮工</div>
                    </div>
                </div>
                
                <!-- 买菜做饭-->
                 <div class="content_box" id="cooking_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000">计费方式</td>
                                <td style="color: #000">服务价格</td>
                                <td style="color: #000">备注</td>
                            </tr>
                            <tr>
                                <td>按用餐人数计算</td>
                                <td>
                                    <p>起步价50元/餐</p>
                                    <p>人数上限为5人，每增加一人，加10元</p>
                                </td>
                                <td>
                                    <p>买菜另收服务费20元</p>
                                    <p>洗碗另收服务费10元</p>
                                </td>
                            </tr>
                            <tr>
                                <td>按小时计算</td>
                                <td>
                                    <p>30元/小时</p>
                                    <p>2小时起算，不足2小时按2小时计算</p>
                                </td>
                                <td>买菜另收服务费20元</td>
                            </tr>
                            <tr>
                                <td>按包月计算</td>
                                <td>3000元/2餐（每天）/月起</td>
                                <td>具体服务价格，以签约合同为准</td>
                            </tr>   
                        </table>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上定价仅针对用餐人数上限为5人，如用餐人数增加或过多请联系在线客服或自行与服务人员协商，可线下结算；</p>
                            <p>2.按小时计算方式，因考虑到客户用餐时间不等，服务人员耗时难以预估，故不提供洗碗服务，如有需要，请自行与服务人员商量，此费用可线下结算；</p>
                            <p>3.实际阿姨服务项目根据客户具体要求，若超出以上范围则由双方协商收费，可线下结算；</p>
                            <p>4.买菜产生路费另算。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p style="text-indent: 22px">为用户提供日常买菜做饭、家庭膳食、企业用餐等服务，用户可以根据自身需求，选择住家或者不住家的阿姨，按需定制，灵活贴心。</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/help1.png" style="width: 100%;height: auto;">
                            </div> 
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>               
                        
             <!-- 酒席帮工-->
                 <div class="content_box" id="feast_helper_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="font-size: 16px;">150~200/天</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000">桌数</td>
                                <td style="color: #000">建议帮工人数</td>
                            </tr>
                            <tr>
                                <td>10-20</td>
                                <td>3-7</td>
                            </tr>
                            <tr>
                                <td>20-30</td>
                                <td>7-10</td>
                            </tr>
                            <tr>
                                <td>30-40</td>
                                <td>10-14</td>
                            </tr>
                            <tr>
                                <td>40-50</td>
                                <td>14-18</td>
                            </tr>    
                        </table>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上定价根据业务需求将实时调整价格，节假日会有所上涨；</p>
                            <p>2.上表信息仅供参考，如需增减人数可根据实际情况在下单时自行选择。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>自办宴席聚餐提供帮工</p>
                            <p>男：端/上菜、抬酒水饮料箱等  </p>
                            <p>女：洗碗备菜、分配摆放餐具酒水、整理清洁等 </p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/help2.png" style="width: 100%;height: auto;">
                            </div> 
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上男女分工不绝对，如有特殊要求请在下单时选择；</p>
                            <p>2.如增加额外工作内容，收费另算，可在服务结束时线下支付给服务人员。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div> 
                 
                 <!-- 宴会帮工-->
                 <div class="content_box" id="banque_helper_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="font-size: 16px;">150~200/天</p>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上定价根据业务需求将实时调整价格，节假日会有所上涨；</p>
                            <p>2.上表信息仅供参考，如需增减人数可根据实际情况在下单时自行选择。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>饭馆、酒店、农庄承办宴席提供兼职服务人员</p>
                            <p>男：厨房帮工、端菜、搬装桌椅、接待引导宾客、抬酒水饮料箱、现场布置等</p>
                            <p>女：装盘理盘、洗碗备菜、接待引导宾客、分配摆放餐具酒水、整理清洁、剩菜打包等  </p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/help3.png" style="width: 100%;height: auto;">
                            </div>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/help4.png" style="width: 100%;height: auto;">
                            </div>  
                        </div>
                        <div style="clear: both;"></div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上男女分工不绝对，如有特殊要求请在下单时选择；</p>
                            <p>2.如增加额外工作内容，收费另算，可在服务结束时线下支付给服务人员。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>                                              
            
              </div>  
              
              
              <!-- 绿色洗护-->
            <div id="jzh_list3_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">绿色洗护</h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="leather_upkeep" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">皮具保养</div>
                    </div>
                    <div class="clean_kind" id="household_washing">
                        <img src="img/clean2.png" class="clean">
                        <div class="clean_text">家常洗衣</div>
                    </div>
                    <div class="clean_kind" id="shoes_upkeep">
                        <img src="img/clean3.png" class="clean">
                        <div class="clean_text">鞋类洗护</div>
                    </div>
                </div>
                
                <!-- 皮具保养-->
                 <div class="content_box" id="leather_upkeep_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务价格】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>皮质沙发桌椅洗护，80/座，160/两座</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>皮具保养：皮质沙发保养，沙发表面除尘除垢，无缝清洗，滋润上油</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【验收标准】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>皮具保养：沙发表面无灰尘污垢，重现光泽</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>               
                        
             <!-- 家常洗衣-->
                 <div class="content_box" id="household_washing_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="font-size: 16px;">30元/小时</p>
                        <div class="prompt">
                            <p>温馨提示：2小时起，不足2小时按2小时计算</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>家常洗衣：上门帮您清洗堆积如山的衣物（不含需送往洗衣店清洗的衣物）；</p>
                        </div>
                        <div class="prompt">
                            <p>温馨提示：暂时不提供的奢侈品类衣物的洗护</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【验收标准】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1.衣服表面没有明显污渍；</p>
                            <p>2.无肥皂水味。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div> 
                 
                 <!-- 鞋类洗护-->
                 <div class="content_box" id="shoes_upkeep_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000" colspan="2">鞋类洗护</td>
                                <td style="color: #000">服务价格</td>
                            </tr>
                            <tr>
                                <td rowspan="2">皮鞋护理</td>
                                <td>低帮</td>
                                <td>30元/双</td>
                            </tr>
                            <tr>
                                <td>中高筒</td>
                                <td>45元/双</td>
                            </tr>
                            <tr>
                                <td rowspan="5">球鞋</td>
                                <td>运动鞋</td>
                                <td rowspan="5">25元/双</td>
                            </tr>
                            <tr>
                                <td>登山鞋</td>
                            </tr>
                            <tr>
                                <td>旅游鞋</td>
                            </tr>   
                            <tr>
                                <td>帆布鞋</td>
                            </tr>   
                            <tr>
                                <td>板鞋</td>
                            </tr>
                            <tr>
                                <td rowspan="2">棉鞋</td>
                                <td>低帮</td>
                                <td>30元/双</td>
                            </tr> 
                            <tr>
                                <td>中高筒</td>
                                <td>45元/双</td>
                            </tr>                     
                        </table>
                        <div class="prompt">
                            <p>温馨提示：鞋类洗护2双起，可任意组合；</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>鞋类洗护：包括皮鞋、球鞋、棉鞋等各类鞋子的清洗护理；</p>
                        </div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.暂时不提供需要干洗的高档鞋类等的洗护；</p>
                            <p>2.皮鞋需要的鞋油等工具可选择自备或由服务人员携带；</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【验收标准】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1.鞋子表面无灰尘无污渍；</p>
                            <p>2.鞋面光亮。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>                                              
            
              </div>  
              
              
              <!-- 维修服务 -->
              
              <!-- 家庭维修 -->
            <div id="wx_list1_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">家庭维修</h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="appliance_fix" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">家电维修</div>
                    </div>
                    <div class="clean_kind" id="pipeline_fix">
                        <img src="img/clean2.png" class="clean">
                        <div class="clean_text">管道维修</div>
                    </div>
                    <div class="clean_kind" id="hydroelectric_fix">
                        <img src="img/clean3.png" class="clean">
                        <div class="clean_text">水电维修</div>
                    </div>
                </div>
                
                <!-- 家电维修-->
               <div class="content_box" id="appliance_fix_content"> 
                  <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务价格】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>上门费30元起步（5公里以内30元，超出5公里，每增加1公里加收3元服务费）</p>
                        </div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.因家电维修涉及零配件较多，故该费用为上门费，不包含维修费；</p>
                            <p>2.维修具体费用由维修人员根据所维修的家电决定，客户可与其自行沟通，线下收费；</p>
                            <p>3.受距离远近（以维修人员所在地为准）因素影响，上门服务费会有浮动；</p>
                            <p>4.配件费另算，用户可以自行选择，自备配件（不提供质保），或者师傅提供（提供质保）；</p>
                            <p>5.如上门检查后因客户原因不能维修，上门费将给到服务人员不予退还，请谨慎下单；</p>
                            <p>6.如上门检查后因维修人员原因不能维修，则全额退回相关费用；</p>
                            <p>7.如有疑问，请联系客服。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>1.大家电：油烟机、冰箱、空调、洗衣机、热水器、电视机等</p>
                            <p>2.小家电：电饭锅、微波炉、电热水壶、电磁炉、饮水机、机顶盒等</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/jiadian1.png">
                            </div> 
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/jiadian2.png">
                            </div> 
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/jiadian3.png">
                            </div> 
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/jiadian4.png">
                            </div> 
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>在线下单（在线提出需求）——确认订单（根据需求推荐服务人员）——上门维修（专业师傅上门维修，客户验收）——售后质保（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>               
                        
             <!-- 管道维修-->
              <div class="content_box" id="pipeline_fix_content"> 
                  <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务价格】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>上门费30元起步（5公里以内30元，超出5公里，每增加1公里加收3元服务费）</p>
                        </div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.因管道维修涉及零配件较多，故该费用为上门费，不包含维修费；</p>
                            <p>2.维修具体费用由维修人员根据所维修的项目决定，客户可与其自行沟通，线下收费；</p>
                            <p>3.受距离远近（以维修人员所在地为准）因素影响，上门服务费会有浮动；</p>
                            <p>4.配件费另算，用户可以自行选择，自备配件（不提供质保），或者师傅提供（提供质保）；</p>
                            <p>5.如上门检查后因客户原因不能维修，上门费将给到服务人员不予退还，请谨慎下单；</p>
                            <p>6.如上门检查后因维修人员原因不能维修，则全额退回相关费用；</p>
                            <p>7.如有疑问，请联系客服。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>蹲坑、地漏疏通；洗手盆/洗菜盆疏通；非拆卸马桶疏通；需拆卸马桶疏通；主管道维修；下水道更换维修</p>
                        </div> 
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>在线下单（在线提出需求）——确认订单（根据需求推荐服务人员）——上门维修（专业师傅上门维修，客户验收）——售后质保（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>
                                
              <!-- 水电维修-->  
              <div class="content_box" id="hydroelectric_fix_content"> 
                  <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务价格】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>上门费30元起步（5公里以内30元，超出5公里，每增加1公里加收3元服务费）</p>
                        </div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.因水电维修涉及零配件较多，故该费用为上门费，不包含维修费；</p>
                            <p>2.维修具体费用由维修人员根据所维修的家电决定，客户可与其自行沟通，线下收费；</p>
                            <p>3.受距离远近（以维修人员所在地为准）因素影响，上门服务费会有浮动；</p>
                            <p>4.配件费另算，用户可以自行选择，自备配件（不提供质保），或者师傅提供（提供质保）；</p>
                            <p>5.如上门检查后因客户原因不能维修，上门费将给到服务人员不予退还，请谨慎下单；</p>
                            <p>6.如上门检查后因维修人员原因不能维修，则全额退回相关费用；</p>
                            <p>7.如有疑问，请联系客服。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>水维修：由于设备老化、断裂等原因造成漏水、喷水等，更换龙头、花洒，修理面盆、水槽、马桶、浴缸等。</p>
                            <p>电维修：由于线路老化、受潮或超负荷使用大功率电器引起漏电、跳闸或保险丝烧断等，接开关，接插座，接/更换照明器具、换气扇、浴霸，更换配电器，线路排查及布局等。</p>
                        </div>
                        <div class="pic_container" style="margin: 30px 0;">
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/shuidian1.png">
                            </div> 
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/shuidian2.png">
                            </div> 
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/shuidian3.png">
                            </div> 
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/shuidian4.png">
                            </div> 
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>在线下单（在线提出需求）——确认订单（根据需求推荐服务人员）——上门维修（专业师傅上门维修，客户验收）——售后质保（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>                                                          
            
              </div>
              
              
           <!-- 上门开锁 -->
            <div id="wx_list2_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">上门开锁</h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="open_lock" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">上门开锁 </div>
                    </div>                    
                </div>

               <div class="content_box" id="appliance_fix_content"> 
                  <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务价格】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>上门费30元起步（5公里以内30元，超出5公里，每增加1公里加收3元服务费）</p>
                        </div>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.开锁换锁必须出示身份证件，同时再提供以下之一：房产证、物业证明、邻居证明；服务人员提供身份证或营业执照。</p>
                            <p>2.因换锁涉及零配件较多，故该费用为上门费，不包含配件费；</p>
                            <p>3.具体费用由维修人员根据锁的类型决定，客户可与其自行沟通，线下收费；</p>
                            <p>4.受距离远近（以维修人员所在地为准）因素影响，上门服务费会有浮动；</p>
                            <p>5.配件费另算，用户可以自行选择，自备配件（不提供质保），或者师傅提供（提供质保）；</p>
                            <p>6.如上门检查后因客户原因不能维修，上门费将给到服务人员不予退还，请谨慎下单；</p>
                            <p>7.如上门检查后因维修人员原因不能维修，则全额退回相关费用；</p>
                            <p>8.如有疑问，请联系客服。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>开锁；换锁；修锁；装锁（包括民用锁，汽车锁、保险箱、金库门等）</p>
                        </div> 
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>在线下单（在线提出需求）——确认订单（根据需求推荐服务人员）——上门维修（专业师傅上门维修，客户验收）——售后质保（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>                                                        
            
              </div>                                                                                    
 
 
 
             <!-- 长期工-->
             
             <!-- 温馨护理 -->
            <div id="chqg_list1_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">温馨护理</h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="baby_sitter" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">居家家护</div>
                    </div>
                    <div class="clean_kind" id="moon_woman">
                        <img src="img/clean2.png" class="clean">
                        <div class="clean_text">月嫂</div>
                    </div>
                    <div class="clean_kind" id="childcare_providers">
                        <img src="img/clean3.png" class="clean">
                        <div class="clean_text">育儿嫂</div>
                    </div>
                </div>
                
                <!-- 居家家护-->
                 <div class="content_box" id="baby_sitter_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="font-size: 16px;">3000元/月起</p>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.试用期与正式单价格不同（试用期计费以平台最低价定，试用最多3天，最少半天，不足半天以半天计；正式单按月计费，双方可自行议价）</p>
                            <p>2.住家与不住家价格不同；</p>
                            <p>3.具体服务价格，以签合同为准；</p>
                            <p>4.请您根据自身情况，酌情衡量后选择合适的阿姨；</p>
                            <p>5.若有疑问，请联系客服</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>专业阿姨，为用户提供日常保洁、洗衣做饭等全方位生活服务，用户可根据自身需求，灵活选择住家或者不住家。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>               
                        
             <!-- 月嫂-->
                 <div class="content_box" id="moon_woman_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="font-size: 16px;">8000元/月起</p>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.具体服务价格，以签合同为准；</p>
                            <p>2.若有疑问，请联系客服</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>专业月嫂，经过专业培训认证，为产妇和新生儿提供专业的生活护理，呵护母婴，体贴安心。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【服务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>产妇日常护理、起居膳食、形体恢复、心理辅导等</p>
                            <p>宝宝日常护理、健康喂养、日常清洁、潜能开发等</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div> 
                 
                 <!-- 育儿嫂-->
                 <div class="content_box" id="childcare_providers_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <p style="font-size: 16px;">5000元/月起</p>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.具体服务价格，以签合同为准；</p>
                            <p>2.若有疑问，请联系客服</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务范围】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>专业育儿嫂，经过专业培训认证，为用户提供专业的婴幼儿护理，帮助宝宝快乐成长。</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>                                              
            
              </div> 
              
              
           <!-- 贴心陪护 -->
            <div id="chqg_list2_detail">
                <h2 style="color: #8c847f;padding-left: 20px;margin-top: 25px;">贴心陪护 </h2>
                <div class="clean_kind_box">
                    <div class="clean_kind" id="aged_care" style="background: rgb(241, 249, 255);">
                        <img src="img/clean1.png" class="clean">
                        <div class="clean_text">老人陪护</div>
                    </div>
                    <div class="clean_kind" id="patient_care">
                        <img src="img/clean2.png" class="clean">
                        <div class="clean_text">病人陪护</div>
                    </div>
                </div>
                
                 <!-- 老人陪护-->
                 <div class="content_box" id="aged_care_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000">业务类型</td>
                                <td style="color: #000">价格</td>
                            </tr>
                            <tr>
                                <td>完全自理</td>
                                <td>2000起/月</td>
                            </tr>
                            <tr>
                                <td>部分自理</td>
                                <td>3000起/月</td>
                            </tr>
                            <tr>
                                <td>不能自理</td>
                                <td>3500起/月</td>
                            </tr>         
                        </table>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上定价依据业务内容定价，如有特殊要求请下单时备注或自行与服务人员商议，额外费用可线下结算；</p>
                            <p>2.同性陪护费用较低，异性陪护费用较高；</p>
                            <p>3.请您根据自身情况，酌情衡量后选择合适的阿姨；</p>
                            <p>4.若有疑问，请联系客服</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="font-size: 16px;">
                            <div>1、完全自理：</div>
                            <p class="item">①　陪伴聊天、下棋等活动消遣时光，情感陪护；</p>
                            <p class="item">②　带老人户外散步；</p>
                            <p class="item">③　照顾起居，掌握老人起居特点，洗老人衣物；</p>
                            <p class="item">④　帮助老人合理进食</p>
                            <p class="item">⑤　护理老人个人卫生，按照老人饮食的基础原则；</p>
                            <p class="item">⑥　若老人是在吃药状态则要提醒按时服药。</p>
                            <div>2、部分自理：</div>
                            <p class="item">①　陪伴聊天、下棋等活动消遣时光，情感陪护；</p>
                            <p class="item">②　带老人户外散步；</p>
                            <p class="item">③　照顾起居，擦拭身体，保持随身衣物干燥，身上无异味，掌握老人起居特点，洗老人衣物；</p>
                            <p class="item">④　做饭喂饭，帮助老人合理进食进食；</p>
                            <p class="item">⑤　护理老人个人卫生、健康，按照老人饮食的基础原则；</p>
                            <p class="item">⑥　关注老人身体心理情况做出积极应对，若老人是在吃药状态要提醒按时服药；</p>
                            <p class="item">⑦　在遇到突发情况帮忙及时就医。</p>
                            <div>3、不能自理：</div>
                            <p class="item">①　陪伴聊天情感陪护；</p>
                            <p class="item">②　照顾起居，掌握老人起居特点，擦拭身体，保持衣物干燥，身上无异味，帮助翻身；</p>
                            <p class="item">③　清洗老人衣物；</p>
                            <p class="item">④　做饭喂饭，进行科学营养合理的进食；</p>
                            <p class="item">⑤　护理老人个人卫生、健康，按照老人饮食的基础原则；</p>
                            <p class="item">⑥　关注老人身体心理情况随时做出应对，若老人是在吃药状态要提醒按时服药；</p>
                            <p class="item">⑦　在遇到突发情况帮忙及时就医。</p>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/peihu1.png" style="width: 100%;height: auto;">
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>               
                        
             <!-- 病人陪护 -->
                 <div class="content_box" id="patient_care_content">
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;">【服务价格】</p>
                        <table cellspacing="0" class="table1">
                            <tr>
                                <td style="color: #000">业务类型</td>
                                <td style="color: #000">价格</td>
                            </tr>
                            <tr>
                                <td>部分自理</td>
                                <td>3500起/月</td>
                            </tr>
                            <tr>
                                <td>不能自理</td>
                                <td>4000起/月</td>
                            </tr>         
                        </table>
                        <div class="prompt">
                            <p>温馨提示：</p>
                            <p>1.以上定价依据业务内容定价，如有特殊要求请下单时备注或自行与服务人员商议，额外费用可线下结算；</p>
                            <p>2.同性陪护费用较低，异性陪护费用较高；</p>
                            <p>3.请您根据自身情况，酌情衡量后选择合适的阿姨</p>
                            <p>4.若有疑问，请联系客服</p>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【业务介绍】</p>
                        <div style="font-size: 16px;">
                            <p class="item">①　陪伴聊天情感陪护；</p>
                            <p class="item">②　照顾起居，擦拭身体，保持随身衣物干燥，身上无异味，清洗衣物；</p>
                            <p class="item">③　做饭喂饭，进行科学营养合理的进食；</p>
                            <p class="item">④　护理个人卫生、健康，按照病人饮食的基础原则；</p>
                            <p class="item">⑤　关注病人身体心理情况随时做出应对，若是在吃药状态要提醒按时服药；</p>
                            <p class="item">⑥　在遇到突发情况帮忙及时就医</p>
                            <div class="pic_box">
                                <img src="http://ofc6sbq4f.bkt.clouddn.com/peihu2.png" style="width: 100%;height: auto;">
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="list">
                        <p style="margin-left: -10px;margin-top: 50px;margin-bottom: 10px;">【预约流程】</p>
                        <div style="color: #837d7d;font-size: 16px;">
                            <p>预约服务（在线提交需求）——智能匹配（服务人员抢单or用户自主选择）——双方确认（服务人员主动联系用户）——上门服务（服务人员上门服务）——服务跟踪（跟踪服务质量）</p>
                        </div>
                        <div class="appointment_btn">立即预约</div>
                    </div>                                 
                 </div>      
            
              </div>   
              
                                                                   
                                          
        </div>
  
    </div>
    <div style="clear: both;"></div>
</div>

<div id="footer">
    <div class="footer">
        <div style="text-align: left;display: inline-block;">
            <p style="color: #ffffff;">客服电话：</p>

            <p style="line-height: 80px;font-size: 20px;color: #61afff; ">0575-87007782</p>
            <p>邮箱：zhejiangzhika@163.com </p>
            <p>公司地址：浙江省诸暨市艮塔西路138号新金融大厦7楼B座</p><br/>
            <p>© 2017 www.uncleserv.com浙江智咖互联网科技有限公司版权所有</p>
        </div>

        <div style="display: inline-block;padding:5px 10px;text-align: center;vertical-align: top; ">
            <div style="display: inline-block;padding: 15px ;">
                <img src="" style="width: 100px;height: 100px;">
                <p>微信联系</p>
            </div>
            <div style="display: inline-block;padding: 15px ;">
                <img src="" style="width: 100px;height: 100px;">
                <p>用户APP下载</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>

<script  type="text/javascript">
    $(".list_style").mouseover(function(){
        $('.triangle').hide();
        $(this).children('.triangle').show();
        $(".list_style").css({ "color": "#000", "font-size": "18px" });
        $(this).css({ "color": "#5288f8", "font-size": "22px" });
    });
    /* 左侧边栏点击事件 */
    $('.left_list_box').on('click','li.list_style',function () {
        /* console.log(this);
        console.log(this.id);  */
        $('.triangle').hide();
        $(this).children('.triangle').show();
        $(".list_style").css({ "color": "#000", "font-size": "18px" });
        $(this).css({ "color": "#5288f8", "font-size": "22px" });
        
        switch(this.id) {
            case "jzh_list_1":
                $('#jzh_list1_detail').show();
                $('#jzh_list2_detail').hide();
                $('#jzh_list3_detail').hide();
                $('#wx_list1_detail').hide();
                $('#wx_list2_detail').hide();
                $('#chqg_list1_detail').hide();
                $('#chqg_list2_detail').hide();
                break;
            case "jzh_list_2":
            	$('#jzh_list1_detail').hide();
                $('#jzh_list2_detail').show();
                $('#jzh_list3_detail').hide();
                $('#wx_list1_detail').hide();
                $('#wx_list2_detail').hide();
                $('#chqg_list1_detail').hide();
                $('#chqg_list2_detail').hide();
                break;
            case "jzh_list_3":
            	$('#jzh_list1_detail').hide();
                $('#jzh_list2_detail').hide();
                $('#jzh_list3_detail').show();
                $('#wx_list1_detail').hide();
                $('#wx_list2_detail').hide();
                $('#chqg_list1_detail').hide();
                $('#chqg_list2_detail').hide();
                break;
            case "wx_list_1":
                $('#jzh_list1_detail').hide();
                $('#jzh_list2_detail').hide();
                $('#jzh_list3_detail').hide();
                $('#wx_list1_detail').show();
                $('#wx_list2_detail').hide();
                $('#chqg_list1_detail').hide();
                $('#chqg_list2_detail').hide();
                break;
            case "wx_list_2":
            	$('#jzh_list1_detail').hide();
                $('#jzh_list2_detail').hide();
                $('#jzh_list3_detail').hide();
                $('#wx_list1_detail').hide();
                $('#wx_list2_detail').show();
                $('#chqg_list1_detail').hide();
                $('#chqg_list2_detail').hide();
                break;
            case "chqg_list_1":
                $('#jzh_list1_detail').hide();
                $('#jzh_list2_detail').hide();
                $('#jzh_list3_detail').hide();
                $('#wx_list1_detail').hide();
                $('#wx_list2_detail').hide();
                $('#chqg_list1_detail').show();
                $('#chqg_list2_detail').hide();
                break;
            case "chqg_list_2":
            	$('#jzh_list1_detail').hide();
                $('#jzh_list2_detail').hide();
                $('#jzh_list3_detail').hide();
                $('#wx_list1_detail').hide();
                $('#wx_list2_detail').hide();
                $('#chqg_list1_detail').hide();
                $('#chqg_list2_detail').show();
                break;
        }
    })
 
    
  /*详情  */ 
    $('.clean_kind_box').on('click','div.clean_kind',function () {
       /*  console.log(this);
        console.log(this.id); */ 
        $(".clean_kind").css({ "background": "#fff" });
        $(this).css({ "background": "#f1f9ff" });
        
        switch(this.id) {
        
        /* 家政钟点 */
            /* 居家保洁 */
            case "day_clean":
                $('#day_clean_content').show();
                $('#science_clean_content').hide();
                $('#jiadian_clean_content').hide();
                break;
            case "science_clean":
            	$('#day_clean_content').hide();
                $('#science_clean_content').show();
                $('#jiadian_clean_content').hide();
                break;
            case "jiadian_clean":
            	$('#day_clean_content').hide();
                $('#science_clean_content').hide();
                $('#jiadian_clean_content').show();
                break;
           /* 你忙我帮 */
            case "cooking":
                $('#cooking_content').show();
                $('#feast_helper_content').hide();
                $('#banque_helper_content').hide();
                break;
            case "feast_helper":
            	$('#cooking_content').hide();
                $('#feast_helper_content').show();
                $('#banque_helper_content').hide();
                break;
            case "banque_helper":
            	$('#cooking_content').hide();
                $('#feast_helper_content').hide();
                $('#banque_helper_content').show();
                break;
            /* 绿色洗护*/
            case "leather_upkeep":
                $('#leather_upkeep_content').show();
                $('#household_washing_content').hide();
                $('#shoes_upkeep_content').hide();
                break;
            case "household_washing":
            	$('#leather_upkeep_content').hide();
                $('#household_washing_content').show();
                $('#shoes_upkeep_content').hide();
                break;
            case "shoes_upkeep":
            	$('#leather_upkeep_content').hide();
                $('#household_washing_content').hide();
                $('#shoes_upkeep_content').show();
                break;
                
        /* 维修服务*/        
            /* 家庭维修*/
            case "appliance_fix":
                $('#appliance_fix_content').show();
                $('#pipeline_fix_content').hide();
                $('#hydroelectric_fix_content').hide();
                break;
            case "pipeline_fix":
            	$('#appliance_fix_content').hide();
                $('#pipeline_fix_content').show();
                $('#hydroelectric_fix_content').hide();
                break;
            case "hydroelectric_fix":
            	$('#appliance_fix_content').hide();
                $('#pipeline_fix_content').hide();
                $('#hydroelectric_fix_content').show();
                break;
                
           /* 长期工*/        
                /* 温馨护理 */
                case "baby_sitter":
                    $('#baby_sitter_content').show();
                    $('#moon_woman_content').hide();
                    $('#childcare_providers_content').hide();
                    break;
                case "moon_woman":
                	$('#baby_sitter_content').hide();
                    $('#moon_woman_content').show();
                    $('#childcare_providers_content').hide();
                    break;
                case "childcare_providers":
                	$('#baby_sitter_content').hide();
                    $('#moon_woman_content').hide();
                    $('#childcare_providers_content').show();
                    break;   
                /* 贴心陪护  */
                case "aged_care":
                    $('#aged_care_content').show();
                    $('#patient_care_content').hide();
                    break;
                case "patient_care":
                	$('#aged_care_content').hide();
                    $('#patient_care_content').show();
                    break;
              
        }
    })
    
    
    
    
    
    
    
    
    
</script>
