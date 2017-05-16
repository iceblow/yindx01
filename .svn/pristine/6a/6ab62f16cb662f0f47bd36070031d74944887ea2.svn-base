
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../manage/js/qiniu.min.js"></script>
<script type="text/javascript" src="../manage/js/plupload.full.min.js"></script>
</head>
<body>
	<div class="row">
		<form id="riderForm" class="form-horizontal" role="form" action="">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">图片</label>
					<div class="col-sm-3">
						<input type="file" value="" id="pic_file" name="picid" style="width: 100px; height: 100px; position: relative; z-index: 100; opacity: 0;">
						<img src="../img/default.png" style="width: 100px; height: 100px; margin-top: -100px;" id="picsrc">
					</div>
					
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">满足条件</label>
					<div class="col-sm-3">
						<input id="coupon_condition" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value=""
							onBlur="replaceSpace(this)" name="coupon_condition"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">减免价格</label>
					<div class="col-sm-3">
						<input id="discount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value=""
							onBlur="replaceSpace(this)" name="discount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">总共张数</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value=""
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">范围类型</label>
					<div class="col-sm-3">
						<select id="rangetype" class="col-sm-10 searchKey"
							name="rangetype" data-placement="bottom"
							onchange="rangetypeChange();">
							<option value="">请选择</option>
							<option value="0">平台券</option>
							<option value="1">公司券</option>
						</select>
					</div>
				</div>
			</div>

			<div class="col-xs-12" id="coupon_rangeDiv" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">范围关联</label>
					<div class="col-sm-3">
						<select id="coupon_range1" class="col-sm-10 searchKey"
							name="coupon_range1">
						</select>
					</div>
				</div>
			</div>

			<div class="col-xs-12" id="showcompany" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">公司</label>
					<div class="col-sm-3">
						<select id="companyid" class="col-sm-10 searchKey"
							name="companyid">
							
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">二级分类</label>
					<div class="col-sm-3">
						<select id="categoryid" class="col-sm-10 searchKey"
							name="categoryid">
							
						</select>
					</div>
				</div>
			</div>

			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">发放类型</label>
					<div class="col-sm-3">
						<select id="granttype" class="col-sm-10 searchKey"
							name="granttype" data-placement="bottom"
							onchange="granttypeChange();">
							<option value="0">首次登陆发放(新用户)</option>
							<option value="1">自己领取</option>
							<option value="2">分享随机领取</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="stimeDiv" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">可领取开始时间</label>
					<div class="col-sm-3">
						<input id="stime" data-rel="tooltip" type="date"
							class="col-sm-10 searchKey" name="stime" data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="etimeDiv" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">可领取结束时间</label>
					<div class="col-sm-3">
						<input id="etime" data-rel="tooltip" type="date"
							class="col-sm-10 searchKey" name="etime" data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="use_stimeDiv" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">可使用开始时间</label>
					<div class="col-sm-3">
						<input id="use_stime" data-rel="tooltip" type="date"
							class="col-sm-10 searchKey" name="use_stime"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="use_etimeDiv" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">可使用结束时间</label>
					<div class="col-sm-3">
						<input id="use_etime" data-rel="tooltip" type="date"
							class="col-sm-10 searchKey" name="use_etime"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12" id="lastdayDIV">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">优惠券持续时间</label>
					<div class="col-sm-3">
						<input id="lastday" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${coupon.lastday}"
							onBlur="replaceSpace(this)" name="lastday"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">一个用户可以领取的最大数量</label>
					<div class="col-sm-3">
						<input id="maxreceive" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${coupon.maxreceive}"
							onBlur="replaceSpace(this)" name="maxreceive"
							data-placement="bottom">
					</div>
				</div>
			</div>

		</form>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
	
	<script type="text/javascript">
		
		$(document).ready(function () {
	    		var rowDataNames = [];
	            $('[data-rel=tooltip]').tooltip({container:'body'});
	            initButton([{
	 		        name: "返回", //这里是静态页的地址
	 		        click: function(){
	 		        	loadHtml('./coupon/couponlist');
	 		        }
	 			},
	 			{
	 		        name: "新增", //这里是静态页的地址
	 		        click: function(){
	 		        	submitaction();
	 		        }
	 			}
	 			])
	 			$.ajax({
					url:'./coupon/getcategorysecond',
					data:{},
					success:function(msg){
						var html = '<option value="0">全部</option>';
	           			var data = eval("("+msg+")");
	           			for(var i = 0 ;i < data.list.length ; i++){
	           				html += '<option value="'+data.list[i].dataid+'">'+data.list[i].name+'</option>'
	           			}
	           			$('#categoryid').append(html);
					}
				})
		});
		   
		var uploader = Qiniu.uploader({
		    runtimes: 'html5,flash,html4',      // 上传模式，依次退化
		    browse_button: 'pic_file',         // 上传选择的点选按钮，必需
		    // 在初始化时，uptoken，uptoken_url，uptoken_func三个参数中必须有一个被设置
		    // 切如果提供了多个，其优先级为uptoken > uptoken_url > uptoken_func
		    // 其中uptoken是直接提供上传凭证，uptoken_url是提供了获取上传凭证的地址，如果需要定制获取uptoken的过程则可以设置uptoken_func
		    // uptoken : '<Your upload token>', // uptoken是上传凭证，由其他程序生成
		    uptoken_url: '../api/file/getUploadTokenWeb',         // Ajax请求uptoken的Url，强烈建议设置（服务端提供）
		    // uptoken_func: function(file){    // 在需要获取uptoken时，该方法会被调用
		    //    // do something
		    //    return uptoken;
		    // },
		    get_new_uptoken: false,             // 设置上传文件的时候是否每次都重新获取新的uptoken
		    // downtoken_url: '/downtoken',
		    // Ajax请求downToken的Url，私有空间时使用，JS-SDK将向该地址POST文件的key和domain，服务端返回的JSON必须包含url字段，url值为该文件的下载地址
		    // unique_names: true,              // 默认false，key为文件名。若开启该选项，JS-SDK会为每个文件自动生成key（文件名）
		    // save_key: true,                  // 默认false。若在服务端生成uptoken的上传策略中指定了sava_key，则开启，SDK在前端将不对key进行任何处理
		    domain: 'http://qiniu-plupload.qiniudn.com/',     // bucket域名，下载资源时用到，必需
		    max_file_size: '100mb',             // 最大文件体积限制
		    flash_swf_url: 'company/js/Moxie.swf',  //引入flash，相对路径
		    max_retries: 3,                     // 上传失败最大重试次数
		    dragdrop: false,                     // 开启可拖曳上传
		    chunk_size: '4mb',                  // 分块上传时，每块的体积
		    auto_start: true,                   // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
		    //x_vars : {
		    //    查看自定义变量
		    //    'time' : function(up,file) {
		    //        var time = (new Date()).getTime();
		              // do something with 'time'
		    //        return time;
		    //    },
		    //    'size' : function(up,file) {
		    //        var size = file.size;
		              // do something with 'size'
		    //        return size;
		    //    }
		    //},
		    init: {
		        'FilesAdded': function(up, files) {
		            plupload.each(files, function(file) {
		                // 文件添加进队列后，处理相关的事情
		            });
		        },
		        'BeforeUpload': function(up, file) {
		               // 每个文件上传前，处理相关的事情
		        },
		        'UploadProgress': function(up, file) {
		               // 每个文件上传时，处理相关的事情
		        },
		        'FileUploaded': function(up, file, info) {
		               // 每个文件上传成功后，处理相关的事情
		               // 其中info是文件上传成功后，服务端返回的json，形式如：
		               // {
		               //    "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
		               //    "key": "gogopher.jpg"
		               //  }
		               // 查看简单反馈
		               var domain = up.getOption('domain');
		               console.log(domain);
		               console.log(info);
		               console.log(up);
		               var res = eval("("+info+")");
		               var sourceLink = domain +"/"+ res.key; //获取上传成功后的文件的Url
		               $("#picsrc").attr('src',sourceLink);
		               $("#pic_file").val(file.id);
		        },
		        'Error': function(up, err, errTip) {
		               //上传出错时，处理相关的事情
		        },
		        'UploadComplete': function() {
		               //队列文件处理完毕后，处理相关的事情
		        },
		        'Key': function(up, file) {
		            // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
		            // 该配置必须要在unique_names: false，save_key: false时才生效
		           
		            var date = new Date();
		            var month = date.getMonth() + 1;
		            var strDate = date.getDate();
		            if (month >= 1 && month <= 9) {
		                month = "0" + month;
		            }
		            if (strDate >= 0 && strDate <= 9) {
		                strDate = "0" + strDate;
		            }
		            var hours = date.getHours();
		            if (hours >= 0 && hours <= 9) {
		            	hours = "0" + hours;
		            }
		            var minutes = date.getMinutes();
		            if (minutes >= 0 && minutes <= 9) {
		            	minutes = "0" + minutes;
		            }
		            var random = Math.ceil(Math.random()*100);
		            var key = date.getFullYear() + month + strDate + hours + 
		            	minutes + date.getSeconds() + date.getMilliseconds() + "_" + random;
		            // do something with key here
		            return key
		        }
		    }
		});

	function submitaction(){
	    if(!$('#pic_file').val()){
     		alert('请上传图片');
     		return;
     	} 
 		if(!$('#coupon_condition').val()){
     		alert('请设置满足条件');
     		return;
     	}
 		if(!$('#discount').val()){
     		alert('请设置减免价格');
     		return;
     	}
 		if(!$('#totalcount').val()){
     		alert('请设置总共张数');
     		return;
     	}
 		if(!$('#lastday').val() && ($('#granttype').val()!=1)){
     		alert('请设置持续时间');
     		return;
     	}
 		if(!$('#maxreceive').val()){
     		alert('请设置一个用户可以领取的最大数量');
     		return;
     	}
		$.ajax({
			url:'./coupon/addCouponData',
			data:$('#riderForm').serialize(),// 你的formid
			success:function(){
					alert("添加成功");
					loadHtml("./coupon/couponlist");
				}		
		})
	}
		 function replaceSpace(obj){
		        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		   }
		 

			function rangetypeChange(){
				var rangetype = $('#rangetype').val();
				if(rangetype == 0){
					$('#showcompany').css("display","none");
				}
				if(rangetype == 1){
					$('#showcompany').css("display","block");
					$.ajax({
						url:'./coupon/getCompanyList',
						data:{},
						success:function(msg){
							var html = '';
		           			var data = eval("("+msg+")");
		           			for(var i = 0 ;i < data.list.length ; i++){
		           				html += '<option value="'+data.list[i].companyid+'">'+data.list[i].name+'</option>'
		           			}
		           			$('#companyid').append(html);
						}
					})
				}
			}
			
			
			
			function condition_typeChange(){
				var rangetype = $('#rangetype').val();
				var condition_type = $('#condition_type').val();
				$.ajax({
					url:'./coupon/condition_typeChange',
					data:{rangetype:rangetype},
					success:function(msg){
						if(msg != null){
							var data = eval("("+msg+")");
							if(condition_type == 0){
								$('#coupon_range2Div').css("display","none");
								$('#coupon_range2').val("");
							}
							if (condition_type == 1) {
								$('#coupon_range2Div').css("display","block");
								$('#coupon_range2').empty();
								var html = '';
								if(data.listGoodCategory){
									for(var i = 0;i<data.listGoodCategory.length;i++){
										html += '<option value="'+data.listGoodCategory[i].categoryid+'">'+data.listGoodCategory[i].name+'</option>';
									}
								}
								$('#coupon_range2').append(html);
							}
							if (condition_type == 2) {
								$('#coupon_range2Div').css("display","block");
								$('#coupon_range2').empty();
								var html = '';
								if(data.listGoodsSale){
									for(var i = 0;i<data.listGoodsSale.length;i++){
										html += '<option value="'+data.listGoodsSale[i].saleid+'">'+data.listGoodsSale[i].name+'</option>';
									}
								}
								$('#coupon_range2').append(html);
							}
							
						}
					}
				})
			}
			
			function granttypeChange(){
				var granttype = $('#granttype').val();
				if(granttype == 0){
					$('#etimeDiv').css("display","none");
					$('#stimeDiv').css("display","none");
					$('#use_etimeDiv').css("display","none");
					$('#use_stimeDiv').css("display","none");
					$('#lastdayDIV').css("display","block");
				}
				if (granttype == 1) {
					$('#etimeDiv').css("display","block");
					$('#stimeDiv').css("display","block");
					$('#use_etimeDiv').css("display","block");
					$('#use_stimeDiv').css("display","block");
					$('#lastdayDIV').css("display","block");
				}
				if (granttype == 2) {
					$('#etimeDiv').css("display","block");
					$('#stimeDiv').css("display","block");
					$('#use_etimeDiv').css("display","block");
					$('#use_stimeDiv').css("display","block");
					$('#lastdayDIV').css("display","block");
				}
				
			}
		</script>
		
</body>
</html>