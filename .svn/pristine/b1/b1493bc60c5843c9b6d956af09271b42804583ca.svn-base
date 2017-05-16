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
	<form class="form-horizontal" role="form" id="versionForm">
		<div class="row">

			<div class="col-xs-12">
				<div class="form-group">
					<label Style="text-align: center;"
						class="col-sm-1 control-label no-padding-right" for="form-field-6">省份</label>
					<div class="col-sm-2">
						<select data-rel="tooltip" id="proselect" onchange="changepro()"
							type="text" class="col-sm-10 searchKey" name="provienceid"
							title="平台类型" data-placement="bottom" onchange="changeload()">
							<option value="">请选择</option>
							<c:forEach var="p" items="${pro }">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</div>
					<label Style="text-align: center;"
						class="col-sm-1 control-label no-padding-right" for="form-field-6">城市</label>
					<div class="col-sm-2">
						<select data-rel="tooltip" id="cityselect" type="text"
							class="col-sm-10 searchKey" name="city" title="平台类型"
							data-placement="bottom" onclick="cityselects()">
							<option value="">请选择</option>
						</select>
					</div>
					<label class="col-sm-1 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">区域</label>
					<div class="col-sm-2">
						<select data-rel="tooltip" class="col-sm-10 searchKey"
							onclick="areaselect()" name="area" title="区域"
							data-placement="bottom" id="area">
						</select>
					</div>
				</div>
			</div>

			<div class="col-xs-12">
				<div class="form-group">
					<label Style="text-align: center;"
						class="col-sm-1 control-label no-padding-right" for="form-field-6">轮播图跳转类型</label>
					<div class="col-sm-2">
						<select data-rel="tooltip" type="text" class="col-sm-10 searchKey"
							id="type" name="type" title="轮播图跳转类型" data-placement="bottom">
							<option value="">请选择</option>
							<option value="0">外联</option>
							<option value="1">公司</option>
							<option value="2">阿姨</option>
						</select>
					</div>
				</div>
			</div>



			<div class="col-xs-12">
				<div class="form-group">
					<label Style="text-align: center;"
						class="col-sm-1 control-label no-padding-right" for="form-field-6">内容</label>
					<div class="col-sm-2" id="content">
						<!-- 					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="contentid" name="contentid" title="内容" data-placement="bottom" > -->
						<!-- 						<option value="">请选择</option> -->
						<%-- 						<option value="0" <c:if test="${banner.contentid==0 }">selected</c:if> >外联的地址</option> --%>
						<%-- 						<option value="1" <c:if test="${banner.contentid==1 }">selected</c:if> >公司Id</option> --%>
						<%-- 						<option value="2" <c:if test="${banner.contentid==2 }">selected</c:if> >阿姨Id</option> --%>
						<!-- 					</select> -->
					</div>
				</div>
			</div>

			<div class="col-xs-12" id="pic">

				<div class="form-group">
					<label Style="text-align: center;"
						class="col-sm-1 control-label no-padding-right" for="form-field-6">图片地址</label>
					<div class="col-sm-8">
						<div id="dropzone" class="col-sm-2 dropzone">
							<div class="dz-default dz-message" id="clickImg1">
								<span>
									<div class="dz-default dz-message" style="font-size: 12px">
										<span class="bigger-150 bolder"><i
											class="icon-caret-right red"></i> 拖拽文件</span><span
											class="smaller-80 grey">(或者点击)上传 <br>
											文件尺寸:100px*100px <br> 文件大小:50MB
										</span> <br>
										<i class="upload-icon icon-cloud-upload blue icon-3x"></i>
									</div>
								</span>
							</div>

						</div>
					</div>


				</div>
			</div>

			<input name="key" id="key" class="searchKey" style="display: none" />
			<div class="col-xs-3"
				style="clear: both; float: initial; margin-top: 70px;">
				<div class="form-group">
					<button class="btn btn-info" type="button"
						style="margin-left: 30%;" onclick="submitaction()">
						<i class="icon-ok bigger-110"></i> 提交
					</button>
					<button class="btn" type="button" style="margin-left: 20%;"
						onclick="goback()">
						<i class="icon-undo bigger-110"></i> 取消
					</button>
				</div>
			</div>
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</form>
	<script type="text/javascript">
		var uploader = Qiniu
				.uploader({
					runtimes : 'html5,flash,html4', // 上传模式，依次退化
					browse_button : 'dropzone', // 上传选择的点选按钮，必需
					// 在初始化时，uptoken，uptoken_url，uptoken_func三个参数中必须有一个被设置
					// 切如果提供了多个，其优先级为uptoken > uptoken_url > uptoken_func
					// 其中uptoken是直接提供上传凭证，uptoken_url是提供了获取上传凭证的地址，如果需要定制获取uptoken的过程则可以设置uptoken_func
					// uptoken : '<Your upload token>', // uptoken是上传凭证，由其他程序生成
					uptoken_url : '../api/file/getUploadTokenWeb', // Ajax请求uptoken的Url，强烈建议设置（服务端提供）
					// uptoken_func: function(file){    // 在需要获取uptoken时，该方法会被调用
					//    // do something
					//    return uptoken;
					// },
					get_new_uptoken : false, // 设置上传文件的时候是否每次都重新获取新的uptoken
					// downtoken_url: '/downtoken',
					// Ajax请求downToken的Url，私有空间时使用，JS-SDK将向该地址POST文件的key和domain，服务端返回的JSON必须包含url字段，url值为该文件的下载地址
					// unique_names: true,              // 默认false，key为文件名。若开启该选项，JS-SDK会为每个文件自动生成key（文件名）
					// save_key: true,                  // 默认false。若在服务端生成uptoken的上传策略中指定了sava_key，则开启，SDK在前端将不对key进行任何处理
					domain : 'http://ofc6sbq4f.bkt.clouddn.com', // bucket域名，下载资源时用到，必需
					max_file_size : '100mb', // 最大文件体积限制
					flash_swf_url : '../company/js/Moxie.swf', //引入flash，相对路径
					max_retries : 3, // 上传失败最大重试次数
					dragdrop : false, // 开启可拖曳上传
					chunk_size : '4mb', // 分块上传时，每块的体积
					auto_start : true, // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
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
					init : {
						'FilesAdded' : function(up, files) {
							plupload.each(files, function(file) {
								// 文件添加进队列后，处理相关的事情
							});
						},
						'BeforeUpload' : function(up, file) {
							// 每个文件上传前，处理相关的事情
						},
						'UploadProgress' : function(up, file) {
							// 每个文件上传时，处理相关的事情
						},
						'FileUploaded' : function(up, file, info) {
							// 每个文件上传成功后，处理相关的事情
							// 其中info是文件上传成功后，服务端返回的json，形式如：
							// {
							//    "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
							//    "key": "gogopher.jpg"
							//  }
							// 查看简单反馈
							var domain = up.getOption('domain');
							var res = eval('(' + info + ')');
							var sourceLink = domain + "/" + res.key; //获取上传成功后的文件的Url
							console.log(sourceLink);
							$('#clickImg1').hide();
							$('#dropzone')
									.html(
											'<img data-dz-thumbnail width="184px" height="200px" src="" id="changeimg"/> ');

							$('#changeimg').attr("src", sourceLink);
							$('#key').val(res.key);
						},
						'Error' : function(up, err, errTip) {
							//上传出错时，处理相关的事情
						},
						'UploadComplete' : function() {
							//队列文件处理完毕后，处理相关的事情
						},
						'Key' : function(up, file) {
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
							var random = Math.ceil(Math.random() * 100);
							var key = date.getFullYear() + month + strDate
									+ hours + minutes + date.getSeconds()
									+ date.getMilliseconds() + "_" + random;
							// do something with key here
							return key
						}
					}
				});

		function changepro() {
			var provienceid = $("#proselect").val();
			//alert(provienceid);
			if (provienceid != "") {
				$.ajax({
					type : "post",
					url : "./index/changeprovience",
					data : {
						'id' : provienceid
					},
					success : function(msg) {
						console.log(1);
						var data = eval("(" + msg + ")");
						console.log(data);
						//var list = data.cityes;
						var list = data;
						$("#cityselect").empty();
						var html = '';
						html += '<option value="">请选择市</option>';
						if (list != null && list.length > 0) {
							for (var i = 0; i < list.length; i++) {
								html += '<option value="'+list[i].id+'">'
										+ list[i].name + '</option>';
							}
						}
						$("#cityselect").append(html);
					},
					error : function() {
						alert("错误");
					}
				});
			}
		}
		function submitaction() {
			// 	$(".form-horizontal").submit();
			var conform = confirm("确认新增?")
			if (conform == false) {
				return;
			}

			var params = {};
			//$("#ddlregtype").find("option:selected").text()
			var city = $('#area').find("option:selected").text();
			console.log(city);
			if (city == '' || !city || city=='请选择区') {
				alert('区不能为空');
				return;
			}
			var type = $('#type').val();
			if (type == '') {
				alert('轮播跳转类型');
				return;
			}
			var contentid = $('#contentid').val();
			if (contentid == '' || !contentid) {
				alert('内容');
				return;
			}
			var key = $('#key').val();
			if (key == '') {
				alert('图片不能为空');
				return;
			}
			params.city = city;
			params.type = type;
			params.contentid = contentid;
			params.key = key;
			$.ajax({
				url : './index/doAddAppBanner',
				data : params,
				type : 'POST',
				success : function(msg) {
					if (msg.c == 1) {
						alert(msg.m);
						loadHtml("./index/appBannerList");
					} else {
						alert(msg.m);
					}
				}
			})
		}

		function goback() {
			loadHtml("./index/appBannerList");
		}

		function changeType() {
			var type = $('#type').val();
			if (type == 0) {
				changeWaiLian();
			}
			if (type == 1) {
				changeCompany();
			}
			if (type == 2) {
				changeAunt();
			}
			if (type == '') {
				$('#content').empty();
			}
		}

		function changeWaiLian() {
			$('#content')
					.html(
							'<input class="col-sm-10 searchKey" id="contentid" name="contentid" class="searchKey" title="内容" data-placement="bottom"/>');

		}

		function changeCompany() {
			$
					.ajax({
						url : './index/getAllCompany',
						data : {
							page : 1
						},
						type : 'POST',
						success : function(result) {
							var data = eval("(" + result + ")");
							var select = $('<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="contentid" name="contentid" title="内容" data-placement="bottom" ></select>');
							select.append('<option value="">请选择</option>');
							for (var i = 0; i < data.length; i++) {
								var option = $('<option value="'+data[i].companyid+'">'
										+ data[i].name + '</option>');
								select.append(option);
							}
							$('#content').empty().append(select);

						}
					})
		}
		function changeAunt() {
			$
					.ajax({
						url : './index/getAllAunt',
						data : {
							page : 1
						},
						type : 'POST',
						success : function(result) {

							var data = eval("(" + result + ")");
							var select = $('<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="contentid" name="contentid" title="内容" data-placement="bottom" ></select>');
							select.append('<option value="">请选择</option>');
							for (var i = 0; i < data.length; i++) {
								var option = $('<option value="'+data[i].auntid+'">'
										+ data[i].realName + '</option>');
								select.append(option);
							}
							$('#content').empty().append(select);

						}
					})
		}
		 function cityselects(){
	        	var val = $('#cityselect').val();
	        	console.log(val);
	        	$.ajax({
	        		url:"./index/selectArea",
	        		type:"post",
	        		data:{id:val},
	        		success:function(msg){
	        			console.log(msg);
	        			$('#area').empty();
	        			var html = '';
	        			html += '<option value="">请选择区</option>';
	        			for(var i = 0; i < msg.list.length;i++){
	        				html += '<option value="'+msg.list[i].name+'" onclick="areaselect('+msg.list[i].id+')">'+msg.list[i].name+'</option>';
	        			}
	        			$('#area').append(html);
	        		}
	        	})
			}
		 
		 function areaselect(){
	        	var val = $('#area').val();
	        	$("#grid-table").jqGrid('setGridParam',{
	                datatype:'json',  
	                postData:{city:val}, //发送数据  
	                page:1,
	                datatype: "json"
	            }).trigger("reloadGrid"); //重新载入  
			}
	</script>
</body>
</html>