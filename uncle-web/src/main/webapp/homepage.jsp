<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
		<head>
		    <meta http-equiv="content-type" content="text/html;charset=utf-8">
		    <title></title>
			<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
			<style id="css_index" index="index" type="text/css">
			    html,body{height:100%}
			    html{overflow-y:auto}
			    body{font:14px arial;text-align:left;background:#fff;;line-height: 1.5;min-width: 1000px;color: #817872;}
			    body,p,form,ul,li{margin:0;padding:0;list-style:none}
			    a{text-decoration: none;color: #817872;}
			    
			    a:visited{ text-decoration: none;}
		    
			    .cursors{color:#61afff;}

			    #content{min-height: 600px;}
			    .Fullpage{width: 100%;height:600px;}

			    .content-title{font-size: 35px;text-align: center;padding: 60px 0;}
			    .content-title p{font-size: 20px;}
			    #footer{width:100%;padding: 30px 0;background-color: #323337;text-align: center;font-weight: bold; color: #7e8287;vertical-align: top;}
			    .footer{font-size: 10px;line-height: 20px;}
			    .project{}
			    .on{color: #7badff;}
			   .projectmenu{
            width: 47%;
            text-align: center;
            vertical-align: top;
            margin-top: 50px;
            display: inline-block;
            overflow: hidden;
        }
        .projecttab{height: 70px;cursor: pointer;}
        .projecttab div img{margin-top: 20px;height: 30px;}
        .projecttab div span{padding-left: 10px;}
        .projecttabtitle{font-size: 25px;}
        .projectcontent{
            width: 400px;
            text-align: left;
            display: inline-block;
            overflow: hidden;
            margin-bottom: 50px;
        }

        .circle_hone{
            width: 135px;
            margin-top: -70px;
            margin-left: -60px;
            vertical-align: text-top;
        }
		.homepage{
		    color:#96b8fb;
		}
			</style>
			<script>
			$(function(){		            
		        	$('.projectcontent div').width(6*400+'px');
		            $('.projectcontent div').height(600+'px'); 
					/* $('.projectcontent div').height((600 - $('.project .content-title')[0].offsetHeight)+'px'); */
					$('.projectcontent img').width(400+'px');
					$('.projectcontent img').height(100+'%');
					
				$(".projecttab").mouseover(function(){
					$(this).addClass('on').siblings().removeClass('on');
					var index = $(this).index();
					/* var distance = -$('.projectcontent')[0].offsetWidth*index; */
					var distance = -400*index;
					var obj = $('.projectmenu img');
					for (var i=0;i<obj.length;i++){
						if(index == i)
							obj[i].src= "img/"+ obj[i].alt + "1.png";
						else
							obj[i].src= "img/"+ obj[i].alt + ".png";

					}
					$('.projectcontent div').stop().animate({
						left:distance
					});
				});
				
				
			});
			</script>
		</head>
		<body>
			<jsp:include page="top.jsp"></jsp:include>
<div id="content" style="padding-top: 80px;">
    <div class="Fullpage" style=" background-size: cover; background-image: url('img/homepage_bg.jpg');">
        <div style="padding: 150px 0 50px 0;text-align: center;">

            <p style="font-size: 35px;color: #724c2b;font-weight: bold;padding: 20px 0;">生活再累也要顾家, 琐事太多还有表叔!</p>
            <p style="font-size: 10px;">扫描二维码下载app或关注微信</p>
            <div style="padding: 50px 0;text-align: center;">
                <div style="background: #9bbef7;width: 200px;line-height: 40px;text-align: center;color: #ffffff;display: inline-block;cursor: pointer;border-radius: 4px;" >开始预约</div>
            </div>
            <div style="padding:5px 10px;text-align: center;font-size: 10px;">
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


    <div class="Fullpage">
        <div style="width: 70%;margin: 0 auto;font-size: 10px;">
            <div style="" class="content-title">
                服务项目
            </div>
            <script type="text/javascript">
                function change(obj,str,bool) {
                    if(bool == 0){
                        obj.children[0].src="img/" + str +"1.png";
                        if(obj.children[1].className == "")
                            obj.children[1].className += "cursors";
                    }
                    else{
                        obj.children[0].src="img/" + str +".png";
                        if(obj.children[1].className != "")
                            obj.children[1].className="";
                    }
                }
            </script>
            <div id="">
                <div style="width: 25%; text-align: center;float: left;" onmouseover="change(this,'housekeeping_hour',0)" onmouseout="change(this,'housekeeping_hour',1)">
                    <img src="img/housekeeping_hour.png" style="width: 120px;height: 120px;">
                    <p style="font-size: 25px;padding: 20px 0;">家政钟点</p>
                    <p>居家保洁</p>
                    <p>居家保洁 贴心陪护、绿色洗护、你忙我帮</p>
                </div>
                <div style="width: 25%; text-align: center;float: left;" onmouseover="change(this,'fix_server',0)" onmouseout="change(this,'fix_server',1)">
                    <img src="img/fix_server.png" style="width: 120px;height: 120px;">
                    <p style="font-size: 25px;padding: 20px 0;">维修服务</p>
                    <p>水电维修</p>
                    <p>家电维修、家具维修、管道疏通</p>
                </div>
                <div style="width: 25%; text-align: center;float: left;" onmouseover="change(this,'baby_sister',0)" onmouseout="change(this,'baby_sister',1)">
                    <img src="img/baby_sister.png" style="width: 120px;height: 120px;">
                    <p style="font-size: 25px;padding: 20px 0;">住家保姆</p>
                    <p>住家保姆</p>
                    <p>月嫂、育儿嫂、家教</p>
                </div>
                <div style="width: 25%; text-align: center;float: left;" onmouseover="change(this,'medical_treatment',0)" onmouseout="change(this,'medical_treatment',1)">
                    <img src="img/medical_treatment.png" style="width: 120px;height: 120px;">
                    <p style="font-size: 25px;padding: 20px 0;">医疗保健</p>
                    <p>预约挂号、就医导览、保健常识、 </p>
                    <p>病友交流、专家咨询、私家医生、住院陪护</p>
                </div>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>

    <div class="Fullpage">
        <div style="width: 100%;background: #f9f9f9;height: 100%">
            <div style="width: 100%;">
                <div style="float: left;width: 30%;" >
                    <img src="img/home_midpic1.png" style="height: 600px;"/>
                </div>
                <div style="float: left;width: 40%;">
                    <div style="text-align: center;padding-top: 200px;" class="content-title" >
                        便捷·环保·近人
                        <p>our  strengths</p>
                    </div>
                </div>
                <div style="float: right;width: 30%;" >
                    <img src="img/home_midpic2.png" style="height: 600px;"/>
                </div>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>

        <div class="project" style="width: 70%;margin: 0 auto;font-size: 10px;">
            <div style="" class="content-title">
                服务流程
            </div>
            <div class="projectcontent">
                    <div style="position: relative;left: 0px;">
                        <img src="img/home_midpic1.png">
                        <img src="img/home_midpic2.png">
                        <img src="img/banner1.png">
                        <img src="img/banner2.png">
                        <img src="img/home_midpic2.png">
                    </div>
                </div>
            <div  class="projectmenu">
                    <div class="projecttab">
                        <div style="float: left;width: 50%;text-align: right;">
                            <img src="img/appointment.png" alt="appointment" width="30px">
                        </div>
                        <div style="float: left;width: 50%;text-align: left;">
                            <span class="projecttabtitle">预约服务</span><br/>
                            <span style="padding-left: 12px">在线提交需求</span>
                        </div>
                    </div>
                    <div class="projecttab">
                        <div style="float: left;width: 50%;text-align: right;">
                            <img src="img/intelligent_match.png" alt="intelligent_match" width="30px">
                        </div>
                        <div style="float: left;width: 50%;text-align: left;">
                            <span class="projecttabtitle">智能匹配</span><br/>
                            <span style="padding-left: 12px">服务人员抢单或用户自主选择</span>
                        </div>
                    </div>
                    <div class="projecttab">
                        <div style="float: left;width: 50%;text-align: right;">
                            <img src="img/both_confirm.png" alt="both_confirm" width="30px">
                        </div>
                        <div style="float: left;width: 50%;text-align: left;">
                            <span class="projecttabtitle">双方确认</span><br/>
                            <span style="padding-left: 12px">服务人员主动联系用户</span>
                        </div>
                    </div>
                    <div class="projecttab">
                        <div style="float: left;width: 50%;text-align: right;">
                            <img src="img/visiting_serveice.png" alt="visiting_serveice" width="30px">
                        </div>
                        <div style="float: left;width: 50%;text-align: left;">
                            <span class="projecttabtitle">上门服务</span><br/>
                            <span style="padding-left: 12px">服务人员主上门服务</span>
                        </div>
                    </div>
                    <div class="projecttab">
                        <div style="float: left;width: 50%;text-align: right;">
                            <img src="img/tail_after.png" alt="tail_after" width="30px">
                        </div>
                        <div style="float: left;width: 50%;text-align: left;">
                            <span class="projecttabtitle">服务跟踪</span><br/>
                            <span style="padding-left: 12px">跟踪服务质量</span>
                        </div>
                    </div>
                </div>
            <div style="clear: both;"></div>
        </div>

    <div class="Fullpage" style="background: #f8f9fa;">
        <div style="width: 70%;margin: 0 auto;font-size: 10px;">
            <div style="width: 50%;float: left;">
                <div style="padding: 0 50px">
                    <div class="content-title">
                        <div style="margin-left: -55px;">各界声音</div>
                        <p style="color:#817872;display: inline-block;">ENALUATE</p>
                        <img src="img/circle_hone.png" class="circle_hone">
                    </div>
                    <div style="padding: 30px 0;">
                        <div style="color: #61afff;">体验后感想：</div>
                        <div style="font-weight: bold;">表叔云服做到了方便快捷的为民服务，信息和结算制度透明化，满足用户的个性需求。</div>
                        <div style="padding: 20px 0;">表叔云服的平台的设计简单精美，操作方便，适合大众使用，推广性强。表叔云服能把整合闲置时间，自主可控，自由高效，方便快捷。表叔云服对家政公司而言，就是一个免费的员工管理平台，可以有效地节省企业的管理和运营成本</div>
                    </div>
                </div>
            </div>
            <div style="width: 50%;float: right;">
                <div style="padding: 50px 50px 0 50px;">
                    <div style="padding: 30px 0;">
                        <div style="color: #61afff;">对平台方向的看好：</div>
                        <div style="font-weight: bold;">表叔云服以家为中心的理念，既给人一种亲切感，也迎合了当下大部分人的心理。表叔云服能够解决当下诸暨家政服务的难题，必然会推动当地新经济的发展。</div>
                        <div style="padding: 20px 0;">表叔云服这个平台只要能动起来，今后的发展一定不可估量。
                            表叔云服依靠信息技术和传统家庭服务深度结合，符合时代发展规律。</div>
                        <div style="font-weight: bold;">表叔云服的理念既呼吁了家庭关怀，又表现了服务大众的想法，在智能机普及的时代，不难做到人手表叔。</div>
                        <div style="padding: 20px 0;">表叔云服是个优秀的项目，拥有精英团队，精诚合作者，定能在竞争的商业潮流中扬帆远航，不落人后。<br/>
                            表叔云服就是要让家庭服务更规范更透明更方便。</div>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear: both;"></div>
    </div>



</div>
			<div id="footer">
				<div class="footer">
						<div style="text-align: left;display: inline-block;">
							<p style="color: #ffffff;">客服电话：</p>

							<p style="line-height: 80px;font-size: 20px;color: #61afff; ">4008001150</p>
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
