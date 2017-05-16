<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
		<head>
		    <meta http-equiv="content-type" content="text/html;charset=utf-8">
		    <title></title>
		    <style id="css_index" index="index" type="text/css">
			    html,body{height:100%}
			    html{overflow-y:auto}
			    body{font:18px "宋体";text-align:;background:#fff;line-height: 25px;min-width: 1000px;color: #8b8b8b;}
			    body,p,form,ul,li{margin:0;padding:0;list-style:none}
			    a{ color: #8b8b8b;text-decoration: none;}
			    

			    #content{min-height: 500px;background: #f7f8fa;	padding: 100px;}


			    .content-title{font-size: 35px;text-align: center;padding: 60px 0;}
			    
			    #footer{width:100%;padding: 30px 0;background-color: #323337;text-align: center;font-weight: bold; color: #7e8287;vertical-align: top;}
			    .footer{font-size: 10px;line-height: 20px;}
			    
	    .list-style{
            cursor: pointer;
            line-height: 40px;
            color: #000000;
        }
        .circle_small{
            width: 30px;
            position: absolute;
            right: 10px;
            top: -20px;
        }
        .on {
            /*font: 30px arial;*/
            font-size: 30px;
            /* font-weight: bold; */
            margin: 25px 0;
        }
        .circle_big{
            height: 60px;
            width: 60px;
            position: absolute;
            right: 0;
            top: -40px;
            display: none;
        }
			    .ContentTitle{font-size: 40px;font-weight: bold;padding: 50px 0;color: #000000}
			    
	    .circle_picbox{
            width: 60px;
            text-align: center;
            display: inline-block;
            position: relative;
        }
        .infor_title{
            width: 125px;
            text-align: center;
            display: inline-block;
        }  
        .AboutUs{
		    color:#96b8fb;
		}
			</style>
			<script type="text/javascript" src="js/jquery.min.js">
				$(document).ready(function(){
				  $(".flip").click(function(){
				    $(".div0").slideDown("slow");
				  });
				});
			</script>
		</head>
		<body>
			<jsp:include page="top.jsp"></jsp:include>
			<div id="content">
				<div style="width: 90%;">
					<div style="width: 30%;float: left;">
						<div style="width: 95%;margin: 0 auto;">
						    <div class="list-style on" onclick="goAbout('AboutUs')">
                                <div class="infor_title">关于我们</div>
                                <div class="circle_picbox">
                                    <img src="img/circle_big.png" class="circle_big" style="display: block;">
                                    <img src="img/circle_small.png" class="circle_small" style="display: none;">
                                </div>
                            </div>
						   <div class="list-style" onclick="goAbout('CommonProblem')">
                                <div class="infor_title">常见问题</div>
                                <div class="circle_picbox">
                                    <img src="img/circle_big.png" class="circle_big">
                                    <img src="img/circle_small.png" class="circle_small">
                                </div>
                          </div>
                          <div class="list-style" onclick="goAbout('joinUs')">
                               <div class="infor_title">加入我们</div>
                               <div class="circle_picbox">
                                   <img src="img/circle_big.png" class="circle_big">
                                   <img src="img/circle_small.png" class="circle_small">
                               </div>
                          </div>
                          <div class="list-style" onclick="goAbout('CollusionDevelopment')">
                               <div class="infor_title">共谋发展</div>
                               <div class="circle_picbox">
                                   <img src="img/circle_big.png" class="circle_big">
                                   <img src="img/circle_small.png" class="circle_small">
                               </div>
                         </div>						  
					
							<script src="js/swiper.min.js"></script>
							<!-- Initialize Swiper -->
						    <script  type="text/javascript">
						    		//var swiper = new Swiper('.swiper-container', {
							        //pagination: '.swiper-pagination',
							        //slidesPerView: 1,
							        //paginationClickable: true,
							        //spaceBetween: 30,
							        //mousewheelControl: true
							    //});
								    var galleryThumbs = new Swiper('.gallery-thumbs', {
								        spaceBetween: 10,
								        centeredSlides: true,
								        slidesPerView: 'auto',
								        touchRatio: 0.2,
								        slideToClickedSlide: true
								    });

								   
								    $(".list-style").mouseover(function(){
				                        $(this).addClass('on').siblings().removeClass('on');
				                        $('.circle_big').hide();
				                        $(this).children('.circle_picbox').children('.circle_big').show();
				                        $('.circle_small').show();
				                        $(this).children('.circle_picbox').children('.circle_small').hide();
				                    });
						    </script>

							<!--<script type="text/javascript" src="js/skip.js"></script>
							<script type="text/javascript" src="js/script.js"></script>-->
							<script type="text/javascript" src="js/jquery.cookie.js"></script>
							<script>
							function goAbout(hash){
								centent	= $('.tabbox>div'),
								centenNav = $('.aboutnav div');
								if(hash == 'AboutUs'){
	                                centent.eq(1).show().siblings().hide();
	                                centenNav.eq(1).addClass('on').siblings().removeClass('on');
	                                location.href = 'AboutUs.jsp';
	                            }
	                            if(hash == 'CommonProblem'){
	                                centent.eq(1).show().siblings().hide();
	                                centenNav.eq(1).addClass('on').siblings().removeClass('on');
	                                location.href = 'CommonProblem.jsp';
	                            }
	                            if(hash == '#'){
	                                centent.eq(2).show().siblings().hide();
	                                centenNav.eq(2).addClass('on').siblings().removeClass('on');
	                                location.href = '#';
	                            }
	                            if(hash == 'CollusionDevelopment'){
	                                centent.eq(3).show().siblings().hide();
	                                centenNav.eq(3).addClass('on').siblings().removeClass('on');
	                                location.href = 'CollusionDevelopment.jsp';
	                            }

								if(window.screen.width >750){
									$('.hideCount').hide();
									$('.obtainbox').hide();
								}
								if(window.screen.width >750){
									$('.hideCount').hide();
									$('.obtainbox').hide();
								}
							}	
							</script>
						</div>
					</div>

					<div style="width: 70%;float: left;background: #ffffff;">
						<div style="padding: 5px 55px;">
							<div class="ContentTitle">公司介绍</div>
							<p>浙江智咖互联网科技有限公司成立于2016年9月，注册资金1000万，公司坐落于美丽的西施故里------全国百强县前列浙江·诸暨。同期成立旗下互联网家庭服务平台“表叔云服”。<br>
							智咖科技是一家集互联网家服务、软件开发、计算机数据处理、金融产品外包、信息技术咨询等服务为一体的互联网科技公司。凭借对家政和互联网行业的深刻理解，智咖科技将线下家政服务与互联网应用相结合，为用户提供简单、方便、安全、高效的互联网服务平台，帮助用户实现低成本、低风险、快起步、高效率的信息化目标。公司拥有着亲切、开放、严谨的企业文化，科学规范的管理以及大量的优秀人才，在产品开发、推广和维护方面始终贯彻“以用户为中心”的宗旨，将“服务”贯穿于公司运作和管理的每一个细节，赢得用户的广泛信任和支持。<br>
							智咖科技以开放融和的态度不断向互联网应用领域纵深化发展。智咖成长的过程，就是服务用户的过程，是和用户一起不断成功的过程!</p>
							<div class="ContentTitle">平台介绍</div>
							<p>表叔云服是一个以家为中心，始终秉承“生活再累也要顾家，琐事再多还有表叔”的服务理念，致力于打造一个能为三四线城市居民匹配相宜的小时工、长期工、住家保姆、月嫂、育儿嫂、家务员、居家陪护员等一站式服务平台。其对家庭生活劳务需求或优化家庭赖以运转的社区环境，对整个家庭运作和家庭发展具有直接、重要的公共影响，除家政服务外还延伸至病患陪护、养老助残、医疗保健、育婴师等专业服务项目。</p>
							<div class="ContentTitle">联系方式</div>
							<p>
							浙江智咖互联网科技有限公司 互联网家服务大咖<br>
							公司地址：浙江省诸暨市艮塔西路138号新金融大厦7楼B座<br>
							客服电话：0575-87007782<br>
							网址：www.uncleserv.com<br>
							邮箱：zhejiangzhika@163.com</p>
							<div style="padding: 30px 0;">

								<img src="" style="width: 40px;height: 40px;">
								<img src="" style="width: 40px;height: 40px;">
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
