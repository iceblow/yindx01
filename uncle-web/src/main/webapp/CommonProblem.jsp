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
			    /* font:14px arial; */
 			    body{font:18px "宋体";text-align:;background:#fff;line-height: 25px;min-width: 1000px;color: #8b8b8b;} 
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
           /* font: 30px arial;*/
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
                <!--<div class="list-style" onclick="goAbout('AboutUs')" onmouseover="change(this,0)" onmouseout="change(this,1)">-->
                <div class="list-style" onclick="goAbout('AboutUs')">
                    <div class="infor_title">关于我们</div>
                    <div class="circle_picbox">
                        <img src="img/circle_big.png" class="circle_big">
                        <img src="img/circle_small.png" class="circle_small">
                    </div>
                </div>
                <!--<div class="list-style on" onclick="goAbout('CommonProblem')" onmouseover="change(this,0)" onmouseout="change(this,1)">-->
                <div class="list-style on" onclick="goAbout('CommonProblem')">
                    <div class="infor_title">常见问题</div>
                    <div class="circle_picbox">
                        <img src="img/circle_big.png" class="circle_big" style="display: block;">
                        <img src="img/circle_small.png" class="circle_small" style="display: none;">
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
            </div>
        </div>
							<script type="text/javascript" src="../js/skip.js"></script>
							<script type="text/javascript" src="../js/mui.js"></script>
							<script type="text/javascript" src="../js/mui.min.js"></script>
							<script>
							 $(".list-style").mouseover(function(){
			                        $(this).addClass('on').siblings().removeClass('on');
			                        $('.circle_big').hide();
			                        $(this).children('.circle_picbox').children('.circle_big').show();
			                        $('.circle_small').show();
			                        $(this).children('.circle_picbox').children('.circle_small').hide();
			                 });
							function goAbout(hash){
								centent	= $('.tabbox>div'),
								centenNav = $('.aboutnav div');
								if(hash == 'commonproblem'){
									centent.eq(1).show().siblings().hide();
									centenNav.eq(1).addClass('on').siblings().removeClass('on');
									}
								if(hash == 'joinus'){
									centent.eq(2).show().siblings().hide();
									centenNav.eq(2).addClass('on').siblings().removeClass('on');
									}
								if(hash == 'collusiondevelopment'){
									centent.eq(4).show().siblings().hide();
									centenNav.eq(4).addClass('on').siblings().removeClass('on');
									}

								}
								if(window.screen.width >750){
									$('.hideCount').hide();
									$('.obtainbox').hide();
								}
							</script>

		 <div style="width: 70%;float: left;background: #ffffff;" class="problem">
						<div style="line-height: 38px;background-color: #dcecff"><h1 style="font-size: 25px;color: #6ea1fa;font-weight: bold;padding: 5px 55px;">擦窗</h1></div>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：表叔会帮忙擦外窗吗？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：我们的阿姨配备专业玻璃擦，只要窗户可以打开便可以在屋内完成内外清洗，但是不提供高空室外作业<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：纱窗、纱槽是否可以清理？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：都可以清理的<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：是否所有纱窗都可以拆卸？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：不是，有些不可拆卸的卷帘纱窗以及拆下可能会损坏的老旧纱窗，暂时不提供拆卸清洗<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：擦玻璃的试剂是否对身体有害？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：我们使用的是中性清洁剂，对身体是无害的，请您放心！<br></p>

						<div style="line-height: 38px;background-color: #dcecff"><h1 style="font-size: 25px;color: #6ea1fa;font-weight: bold;padding: 5px 55px;">家电清理</h1></div>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：是否提供更换油烟机零部件服务？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：暂时不提供更换零部件服务，如需更换，我们也提供专业的家电维修服务，您可以在App上下单预约<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：使用中的冰箱多久清洗一次？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：使用超过一年的冰箱必须清洗，建议一个季度彻底清洗一次<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：空调室内机在清洗时是否需要拆卸？清洗是否包括外机？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：内机需要拆卸，清洗服务暂时不包括外机<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：清洗洗衣机时是否需要拆卸？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：清洗滚筒洗衣机时不需要拆卸，清洗波轮洗衣机时需要拆卸<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：微波炉箱一般多久清理一次？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：表叔建议三个月清洗一次<br></p>

							<div style="line-height: 38px;background-color: #dcecff"><h1 style="font-size: 25px;color: #6ea1fa;font-weight: bold;padding: 5px 55px;">绿色洗护</h1></div>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：哪些衣物可手洗？哪些衣物必须干洗？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：特殊衣物手洗，包括羽绒服、纯棉衣物、人造革、弹性纤维多的衣物、有绒毛的衣服、有涂层的衣服等；<br>
							适合干洗，西服、大衣等衬料、里料和垫肩的衣服；缩水很严重的麻织类衣服；易掉色的衣服；真丝类和羊绒类等质地精细、易受损的衣服；裘皮等<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：皮具保养能否修护沙发上的裂纹？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：能细微修护沙发上的裂纹，淡化严重损坏的裂纹<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：鞋类洗护以后能解决所有问题，跟新的一样？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：不能，有些问题需要清洗修复来复原，也会受到污渍程度、材质损坏程度等因素影响，因此并不能解决所有问题<br></p>
							

							<div style="line-height: 38px;background-color: #dcecff"><h1 style="font-size: 25px;color: #6ea1fa;font-weight: bold;padding: 5px 55px;">家电维修</h1></div>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：维修工作人员是表叔云服的员工吗？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：表叔云服是一个中间平台，提供不同类别的服务，维修服务由具有资质的第三方人员提供<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：线下需要的补款可以直接在平台支付吗？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：不可以，线下费用直接交给维修师傅即可<br></p>
							<p style="font-size: 15px;font-weight: bold;padding: 5px 55px;">问：如需配件，是否一定要购买上门师傅提供的？<br></p>
							<p style="font-size: 15px;padding: 5px 55px;">
							答：客户可以自行选择，自备配件（不提供质保）或者跟师傅购买（提供质保）<br></p>
					</div>
					
	</div>
	<div style="clear: both;"></div>
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
								    function change(obj,bool) {
								    	if(bool == 0){
								    		obj.children[0].src="";
								    		if(obj.children[1].className == "")
								    			obj.children[1].className += "cursors";
								    	}
								    	else{
								    		obj.children[0].src="";
								    		if(obj.children[1].className != "")
								    			obj.children[1].className="";
								    	}
								    }
						    </script>

							<script type="text/javascript" src="js/skip.js"></script>
							<script type="text/javascript" src="js/script.js"></script>
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
