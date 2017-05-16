 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<script type="text/javascript" src="../manage/js/qiniu.min.js"></script>
<script type="text/javascript" src="../manage/js/plupload.full.min.js"></script>
</head>
<body>
<div class="row">
	<form id = "riderForm" class="form-horizontal" role="form"  action="">
			<div class="col-xs-12" id="cityChange">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">城市</label>
				<div class="col-sm-3">
				<div>
			<select name="city" id="cityselect"  style="float: left; margin-left: 10px; width: 110px; height: 23px; margin-top: 8px;  border-radius: 4px;">
				<option value="">请选择市</option>
				<c:forEach items="${list }" var="c">
					<option value="${c.id }">${c.name }</option>
				</c:forEach>
			</select>
				</div>
			</div>
		</div>
		</div>
		
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">图片</label>
				<div class="col-sm-3">
				    <input type="hidden" value="" id="fileid" name="fileid">
					<div id="dropzone" class="col-sm-2 dropzone" >
					</div><!-- PAGE CONTENT ENDS -->
				</div>
			</div>
		</div>
		<div class="col-xs-12" style = "display:inline">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">跳转类型</label>
				<div class="col-sm-3">
					<select id="type" name="type" class="col-sm-10 searchKey" onchange="changeType();">
						<option value="">请选择</option>
						<option value="0">外链</option>
						<option value="1">公司</option>
						<option value="2">阿姨</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-xs-12" id="content" style="display:none">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">内容</label>
				<div class="col-sm-3">
					<select id="contentid" class="col-sm-10 searchKey" name="contentid" onchange="contentChange();">
					</select>
				</div>
			</div>
		</div> 

		<div class="col-xs-12" id="link" style="display:block">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">外链地址</label>
				<div class="col-sm-3">
					<input type="text " class="form-control input-xs maskId" id="linkurl" name="linkurl"  >
				</div>
			</div>
		</div>
		
</form>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div><!-- /.col -->
</div><!-- /.row -->
		<script type="text/javascript">
		var uploader = Qiniu.uploader({
		    runtimes: 'html5,flash,html4',      // 上传模式，依次退化
		    browse_button: 'dropzone',         // 上传选择的点选按钮，必需
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
		    flash_swf_url: '../company/js/Moxie.swf',  //引入flash，相对路径
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
		               $('#dropzone').html('<img data-dz-thumbnail width="150px" height="200px" src="'+sourceLink+'" id="changeimg"/> ');
		               $.ajax({
		       			type: "post",
		       			url: "../api/file/uploadFilePath",
		       			data: {"key" :res.key,"fileType":"0"},
		       			success: function(msg){
		       			  
		       				if(msg != null){
		       					if(msg.c == 1){
		       						var jsonData = eval('('+msg.r+')');
		       						//alert(jsonData.fileid);
		       						$("#fileid").val(jsonData.fileid);
		       						//alert($("#fileid").val());
		       					}
		       				}
		       			},
		       			error:function (){
		       				alert("错误");
		       			}
		       		});
		               
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
		
		$(document).ready(function () {
	            $('[data-rel=tooltip]').tooltip({container:'body'});
	            initButton([{
	 		        name: "返回", //这里是静态页的地址
	 		        click: function(){
	 		        	loadHtml('./home/bannerManage');
	 		        }
	 			},
	 			{
	 		        name: "新增", //这里是静态页的地址
	 		        click: function(){
	 		        	submitaction();
	 		        }
	 			}
	 			])
		});
		
		 /* var myDropzone =new Dropzone("div#dropzone",{ 
			url: "../api/file/adminupload",
			paramName: "filedata", 
		    maxFilesize: 5.0, //mb
		    maxFiles: 1,//最大文件数量
			addRemoveLinks : true,
			dictDefaultMessage :'<div class="dz-default dz-message" style="font-size:12px"><span class="bigger-150 bolder"><i class="icon-caret-right red"></i> 拖拽文件</span><span class="smaller-80 grey">(或者点击)上传 <br /> 文件尺寸:320px*221px <br /> 文件大小:5MB</span> <br /><i class="upload-icon icon-cloud-upload blue icon-3x"></i></div>',
			previewTemplate: "<div style='width:340px' class=\"dz-preview dz-file-preview\">\n  <div style='height:200px' class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div  class=\"dz-size\" data-dz-size></div>\n    <img style='width:320px;height:221px;' data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
			init:function(){
		            this.on("addedfile", function(file) { 
		            });
		            this.on("success",function(file,data) {
		            	var result = data.result;
		            	var list = eval('('+result+')').fileidList;
		            	var fileid = list[0];
		            	$("form").append("<input class='classFile' type='hidden' name='fileId' value='"+fileid+"' id='"+file.lastModified+"' />");
		            });
		            this.on("removedfile",function(file){
		            	$("#"+file.lastModified).remove();
		            });
		        }
		}); */

	function submitaction(){
		if(!$("#cityselect").val()){
			alert("请选择城市");
			return;
		}
		if(!$("#type").val()){
			alert("请选择跳转类型");
			return;
		}
		
		var type = $("#type").val();
		if(type == 0){
			if(!$("#linkurl").val()){
				alert("请输入链接地址");
				return;
			}
		}else {
			if(!$("#contentid").val()){
				alert("请选择内容");
				return;
			}
		}
		
    	/* if(myDropzone.getUploadingFiles().length>0){
			alert("图片正在上传中，请稍等！");
			return;
		} */
    	if(!$("#fileid").val()){
    		alert("请上传图片");
    		return;
    	}
		$.ajax({
			url:'./home/addBannerData',
			data:$('#riderForm').serialize(),// 你的formid
			success:function(code){
				/* if(msg != null){
					if(msg.code == 1 || msg.code == 3){
						alert(msg.message);
						return;
					}
					alert(msg.message);
					loadHtml("./home/bannerManage");
				} */
				if(code == 1){
					alert("新增成功");
					loadHtml("./home/bannerManage");
				}else if(code == 101){
					alert("数据不能为空");
					return;
				}else if(code == 102){
					// alert("同一个城市只能有一个今日闪团");
					return;
				}
				
			}		
		})
	}
		 function replaceSpace(obj){
		        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		   }

			function changeType(){
				var type =$("#type").val();
				$("#hiddenType").val(type);
				
				var cityid = $('#cityselect').val();
				if(!cityid){
					alert("请选择城市");
					return;
				}
				var value = $("#type").val();
				if(value){
					if(value == 0){
						$("#content").css("display","none");
						$("#link").css("display","block");
					}else if(value == 1){
						$("#content").css("display","block");
						$("#link").css("display","none");
						$.ajax({
							url:'./home/changeAdType',
							data:{type:type,cityid:cityid},
							success:function(data){
								if(data != null){
									var msg = eval("("+data+")");
									$("#contentid").empty();
									html = '';
									for(var i = 0;i<msg.contentList.length;i++){
										html += '<option value="'+msg.contentList[i].id+'" >'+msg.contentList[i].name+'</option>';
									}
									$("#contentid").append(html);
								}
							}
						})
						
					}else if(value == 2){
						$("#content").css("display","block");
						$("#link").css("display","none");
						$.ajax({
							url:'./home/changeAdType',
							data:{type:type,cityid:cityid},
							success:function(data){
								if(data != null){
									var msg = eval("("+data+")");
									$("#contentid").empty();
									html = '';
								     for(var i = 0;i<msg.contentList.length;i++){
										html += '<option value="'+msg.contentList[i].id+'" >'+msg.contentList[i].name+'</option>';
								     }
									$("#contentid").append(html);
								}
							}
						})
					}
				}
			}
			function contentChange(){
			}
			
			 //上传图片插件-删除监听
			 $(".dz-remove").on("click",function(){
				$(this).parent().remove();
				if($("#dropzone:has(.dz-preview)").length==0){
					$("#dropzone").empty();
					$("#dropzone").append('<div class=\"dz-default dz-message\"><span>' + myDropzone.options.dictDefaultMessage + '</span></div>');
				}	
			})	
			
		</script>
</body>
</html>