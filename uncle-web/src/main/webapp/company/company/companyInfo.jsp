<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="./js/qiniu.min.js"></script>
<script type="text/javascript" src="./js/plupload.full.min.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=420ea50f88365ed4c968a99629aeb08f"></script> 
</head>
<style>
.addpic{
	width: 100px; 
	height: 100px; 
	position: relative; 
	z-index: 100; 
	opacity: 0;
}
.showpic{
	width: 100px; 
	height: 100px; 
	margin-top: -100px;
}
</style>
<body>
	<div class="row">
		<form id="companyForm" class="form-horizontal col-xs-8" method="post" enctype="multipart/form-data" >
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold; margin-top:30px;">公司LOGO</label>
					<div class="col-sm-11">
						<div style="height: 100%; line-height: 75px; float:left;">
							<input type="file" value="" id="pic_file" class="addpic">
							<c:if test="${empty company.logourl }">
							<img src="./img/default.png" class="showpic" id="imgs">
							</c:if>
							<c:if test="${not empty company.logourl }">
							<img src="${company.logourl }" class="showpic" id="imgs">
							</c:if>
							<input type="hidden" id="logourl" name="logourl">
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">公司名称</label>
					<div class="col-sm-10">
						<input value="${company.name }" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="name" id="name" title="公司名称" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">公司分类</label>
					<div class="col-sm-10">
						<select data-rel="tooltip" class="col-sm-12" name="type" title="公司分类" data-placement="bottom" >
							<option value="0" <c:if test="${company.type == 0 }">selected="selected"</c:if> >维修公司</option>
							<option value="1" <c:if test="${company.type == 1 }">selected="selected"</c:if> >家政公司</option>
							<option value="2" <c:if test="${company.type == 2 }">selected="selected"</c:if> >中介公司</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold">公司简介</label>
					<div class="col-sm-11">
						<textarea name="profile" data-rel="tooltip" class="col-sm-12" onBlur="replaceSpace(this)" name="profile" id="profile" title="公司简介" data-placement="bottom" style="height: 80px;">${company.profile }</textarea>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">登陆账号</label>
					<div class="col-sm-10">
						<input value="${company.phone }" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="phone" id="phone" title="登陆账号" data-placement="bottom" readOnly="readOnly">
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">登陆密码</label>
					<div class="col-sm-10">
						<input value="${company.password }" data-rel="tooltip" type="password" class="col-sm-12"  onBlur="replaceSpace(this)" name="password" id="password" title="登陆密码" data-placement="bottom" readOnly="readOnly" >
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">成立时间</label>
					<div class="col-sm-10">
						<input value="<fmt:formatDate value="${company.yearCreate }" pattern="yyyy-MM-dd"/>" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" id="yearCreate" title="成立时间" data-placement="bottom" >
						<input type="hidden" value="${company.yearCreate }" name="yearCreate" id="hidetime">
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">人员规模</label>
					<div class="col-sm-10">
						<input value="${company.peopleCount }" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="peopleCount" id="peopleCount" title="人员规模" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">公司状态</label>
					<div class="col-sm-10">
						<select data-rel="tooltip" class="col-sm-12" name="stateDel" title="公司分类" data-placement="bottom" disabled="disabled" >
							<option value="0" <c:if test="${company.stateDel == 0 }">selected="selected"</c:if> >待审核</option>
							<option value="1" <c:if test="${company.stateDel == 1 }">selected="selected"</c:if> >正常</option>
							<option value="2" <c:if test="${company.stateDel == 2 }">selected="selected"</c:if> >冻结</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">所在城市</label>
					<div class="col-sm-10">
						<input value="${company.city }" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="city" id="city" title="所在城市" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold">详细地址</label>
					<div class="col-sm-11">
						<input value="${company.address }" data-rel="tooltip" type="text" class="col-sm-12" onfocus="showmap()" onBlur="replaceSpace(this)" name="address" id="address" title="详细地址" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="mapdiv" style="display: none;">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold"></label>
					<div class="col-sm-11">
						<div id="mapContainer" style="width:800px; height:480px;margin-left: 8.33%;"></div>
						<input type="hidden" name="latitude" id="latitude" value="${company.latitude }">
						<input type="hidden" name="longitude" id="longitude" value="${company.longitude }">
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">联系人</label>
					<div class="col-sm-10">
						<input value="${company.relationPeople }" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="relationPeople" id="relationPeople" title="联系人" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">联系电话</label>
					<div class="col-sm-10">
						<input value="${company.relationPhone }" data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="relationPhone" id="relationPhone" title="联系电话" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold">主营业务</label>
					<div class="col-sm-11">
						<input value="${company.mainBusiness }" data-rel="tooltip" type="text" class="col-sm-12" onBlur="replaceSpace(this)" name="mainBusiness" id="mainBusiness" title="主营业务" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold">公司详细描述</label>
					<div class="col-sm-11">
						<textarea name="companyDetail" data-rel="tooltip" type="text" class="col-sm-12" onBlur="replaceSpace(this)" name="companyDetail" id="companyDetail" title="公司详细描述" data-placement="bottom" style="height: 100px;">${company.companyDetail }</textarea>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold; margin-top:30px;">列表图片</label>
					<div class="col-sm-11">
						<div style="height: 100%; line-height: 75px; float:left;">
							<input type="file" value="" id="list_pic" class="addpic">
							<c:if test="${empty company.listpicurl }">
							<img src="./img/default.png" class="showpic" id="listpic">
							</c:if>
							<c:if test="${not empty company.listpicurl }">
							<img src="${company.listpicurl }" class="showpic" id="listpic">
							</c:if>
							<input type="hidden" id="listpicurl" name="listpicurl">
						</div>
					</div>
				</div>
			</div>

			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-1 control-label no-padding-right" for="form-field-6" style="font-weight:bold; margin-top:30px;">荣誉图片</label>
					<div class="col-sm-11">
						<div style="height: 100%; line-height: 75px; float:left;">
							<input type="file" value="" id="pic_list" class="addpic">
							<c:if test="${empty company.picurllist }">
							<img src="./img/default.png" class="showpic" id="picurl">
							</c:if>
							<c:if test="${not empty company.picurllist }">
							<img src="${company.picurllist }" class="showpic" id="picurl">
							</c:if>
							<input type="hidden" id="picurllist" name="picurllist">
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-xs-12" style="border-top:2px solid #ececec; margin-top:50px;">
				<div style="margin-top:80px;">
					<input type="button" value="保存" class="btn btn-primary" style="margin-left: 50px;" onclick="submitForm()">
					<input type="button" value="取消" class="btn" style="margin-left: 50px;" onclick="cancel()">
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}
		function showmap(){
			$('#mapdiv').show();
		}
		function hidemap(){
			$('#mapdiv').hide();
		}
		
		var map = new AMap.Map("mapContainer", {
	        resizeEnable: true,
	        zoom: 13,
	        center: ['${company.longitude }', '${company.latitude }']
	    });
		
		AMap.plugin('AMap.Geocoder',function(){
			geocoder = new AMap.Geocoder({
				city: "010"//城市，默认：“全国”
	        });
		})
		//为地图注册click事件获取鼠标点击出的经纬度坐标
	    var clickEventListener = map.on('click', function(e) {
	    	console.log(e);
	    	marker.setPosition(e.lnglat);
	        document.getElementById("longitude").value = e.lnglat.getLng();
	        document.getElementById("latitude").value = e.lnglat.getLat();
	        
	        geocoder.getAddress(e.lnglat,function(status,result){
	              if(status=='complete'){
	            	  $('#address').val(result.regeocode.formattedAddress);
	              }else{
	            	  $('#address').val('无法获取地址');
	              }
	        });
	    });
	    var marker = new AMap.Marker({
            map:map,
            bubble:true
        });
	    
	    var auto = new AMap.Autocomplete({
	        input: "address"
	    });
	    AMap.event.addListener(auto, "select", select);
	    function select(e) {
	        if (e.poi && e.poi.location) {
	            map.setZoom(15);
	            map.setCenter(e.poi.location);
	            marker.setPosition(e.poi.location);
	            document.getElementById("longitude").value = e.poi.location.getLng();
		        document.getElementById("latitude").value = e.poi.location.getLat();
	        }
	    }
		
		
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
		    domain: 'http://ofc6sbq4f.bkt.clouddn.com',     // bucket域名，下载资源时用到，必需
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
		               var res = eval('('+info+')');
		               var sourceLink = domain +"/"+ res.key; //获取上传成功后的文件的Url
		               console.log(sourceLink);
		               $('#imgs').attr("src",sourceLink);
		               $('#logourl').val(res.key);
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
		
		var Qiniu2 = new QiniuJsSDK();
		
		var uploader2 = Qiniu2.uploader({
		    runtimes: 'html5,flash,html4',      // 上传模式，依次退化
		    browse_button: 'list_pic',         // 上传选择的点选按钮，必需
		    uptoken_url: '../api/file/getUploadTokenWeb',         // Ajax请求uptoken的Url，强烈建议设置（服务端提供）
		    get_new_uptoken: false,             // 设置上传文件的时候是否每次都重新获取新的uptoken
		    domain: 'http://ofc6sbq4f.bkt.clouddn.com',     // bucket域名，下载资源时用到，必需
		    max_file_size: '100mb',             // 最大文件体积限制
		    flash_swf_url: 'company/js/Moxie.swf',  //引入flash，相对路径
		    max_retries: 3,                     // 上传失败最大重试次数
		    dragdrop: false,                     // 开启可拖曳上传
		    chunk_size: '4mb',                  // 分块上传时，每块的体积
		    auto_start: true,                   // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
		    init: {
		        'FilesAdded': function(up, files) {
		            plupload.each(files, function(file) {
		            });
		        },
		        'BeforeUpload': function(up, file) {
		        },
		        'UploadProgress': function(up, file) {
		        },
		        'FileUploaded': function(up, file, info) {
		               var domain = up.getOption('domain');
		               var res = eval('('+info+')');
		               var sourceLink = domain +"/"+ res.key; //获取上传成功后的文件的Url
		               console.log(sourceLink);
		               $('#listpic').attr("src",sourceLink);
		               $('#listpicurl').val(res.key);
		        },
		        'Error': function(up, err, errTip) {
		        },
		        'UploadComplete': function() {
		        },
		        'Key': function(up, file) {
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
		            return key
		        }
		    }
		});
		
		var Qiniu3 = new QiniuJsSDK();
		
		var uploader3 = Qiniu3.uploader({
		    runtimes: 'html5,flash,html4',      // 上传模式，依次退化
		    browse_button: 'pic_list',         // 上传选择的点选按钮，必需
		    uptoken_url: '../api/file/getUploadTokenWeb',         // Ajax请求uptoken的Url，强烈建议设置（服务端提供）
		    get_new_uptoken: false,             // 设置上传文件的时候是否每次都重新获取新的uptoken
		    domain: 'http://ofc6sbq4f.bkt.clouddn.com',     // bucket域名，下载资源时用到，必需
		    max_file_size: '100mb',             // 最大文件体积限制
		    flash_swf_url: 'company/js/Moxie.swf',  //引入flash，相对路径
		    max_retries: 3,                     // 上传失败最大重试次数
		    dragdrop: false,                     // 开启可拖曳上传
		    chunk_size: '4mb',                  // 分块上传时，每块的体积
		    auto_start: true,                   // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
		    init: {
		        'FilesAdded': function(up, files) {
		            plupload.each(files, function(file) {
		            });
		        },
		        'BeforeUpload': function(up, file) {
		        },
		        'UploadProgress': function(up, file) {
		        },
		        'FileUploaded': function(up, file, info) {
		               var domain = up.getOption('domain');
		               var res = eval('('+info+')');
		               var sourceLink = domain +"/"+ res.key; //获取上传成功后的文件的Url
		               console.log(sourceLink);
		               $('#picurl').attr("src",sourceLink);
		               $('#picurllist').val(res.key);
		        },
		        'Error': function(up, err, errTip) {
		        },
		        'UploadComplete': function() {
		        },
		        'Key': function(up, file) {
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
		            return key
		        }
		    }
		});
		
		function cancel(){
			loadHtml("./company/companyInfo");
		}
		
		
		function submitForm(){
			var time = $('#yearCreate').val();
			var dt = new Date(time.replace(/-/,"/"));
			$('#hidetime').val(dt);
			$.ajax({
				url: './company/saveCompany',
				data: $('#companyForm').serialize(),
				type: 'POST',
				success: function(msg){
					var date = eval('('+msg+')');
					if (date.code == 1) {
						alert(date.message);
						loadHtml("./company/companyInfo");
					} else {
						alert(date.message);
					}
				}
			})
		}
	</script>
</body>
</html>