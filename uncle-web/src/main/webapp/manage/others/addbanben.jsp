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
<form class="form-horizontal" role="form"  id="versionForm">
<div class="row">
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">版本号</label>
				<div class="col-sm-2">
					<input data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="versionstr" name="versionstr" title="版本号" data-placement="bottom" >
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">平台类型</label>
				<div class="col-sm-2">
					<select  data-rel="tooltip" id="platformtype" type="text" class="col-sm-10 searchKey" name="platformtype" title="平台类型" data-placement="bottom" onchange="changeload()">
						<option value="">请选择</option>
						<option value="0">安卓</option>
						<option value="1">IOS</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">APP类型</label>
				<div class="col-sm-2">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="apptype" name="apptype" title="APP类型" data-placement="bottom" >
						<option value="">请选择</option>
						<option value="0">用户APP</option>
						<option value="1">阿姨APP</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">是否发布</label>
				<div class="col-sm-2">
					√<input value="1" type="radio" name="poststate" title="是否发布" data-placement="bottom" >
					×<input value="0" type="radio" name="poststate" title="是否发布" data-placement="bottom" >
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">版本类型</label>
				<div class="col-sm-2">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="versiontype" name="versiontype" title="版本类型" data-placement="bottom" >
						<option value="">请选择</option>
						<option value="0">小版本</option>
						<option value="1">大版本</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" id="android" style="display: none;">
		
			<div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">下载地址</label>
				<div class="col-sm-8">
					<div id="dropzone" class="col-sm-2 dropzone" >
					<div class="dz-default dz-message" id="clickImg1" ><span>
					<div class="dz-default dz-message" style="font-size:12px">
					<span class="bigger-150 bolder"><i class="icon-caret-right red"></i> 
					拖拽文件</span><span class="smaller-80 grey">(或者点击)上传 <br> 
					文件尺寸:100px*100px <br> 文件大小:50MB</span> <br><i class="upload-icon icon-cloud-upload blue icon-3x"></i></div></span></div>
					
					</div>
				</div>
				
				
			</div>
		</div>
		
		<div class="col-xs-12" id="IOS" style="display: none;">
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">下载地址</label>
				<div class="col-sm-2">
					<input data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="fileId" title="下载地址" id="IosFile" data-placement="bottom" >
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">更新内容描述</label>
				<div class="col-sm-2">
					<textarea id="editorid" data-rel="tooltip" class="col-sm-10 searchKey" name="newcontent" title="更新内容描述" data-placement="bottom" ></textarea>
				</div>
			</div>
		</div>
		<input name="logourl" id="logourl" class="searchKey" style="display:none"/>
		<div class="col-xs-3" style="clear:both;float: initial;margin-top: 70px;">
	        <div class="form-group">
				<button class="btn btn-info" type="button" style="margin-left: 30%;" onclick="submitaction()">
					<i class="icon-ok bigger-110"></i>
					发布
				</button>
				<button class="btn" type="button" style="margin-left: 20%;" onclick="goback()">
					<i class="icon-undo bigger-110"></i>
					取消
				</button>
			</div>
		</div>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div><!-- /.col -->
</div><!-- /.row -->
</form>
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
             	$('#clickImg1').hide();
             	$('#dropzone').html('<img data-dz-thumbnail width="184px" height="200px" src="" id="changeimg"/> ');
             	  
               $('#changeimg').attr("src",sourceLink);
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


// var myDropzone =new Dropzone("div#dropzone",{ 
// 	url: "#",
// 	paramName: "filedata", 
//     maxFilesize: 50.0, //mb
//     maxFiles: 1,//最大文件数量
// 	addRemoveLinks : true,
// 	dictDefaultMessage :'<div class="dz-default dz-message" style="font-size:12px"><span class="bigger-150 bolder"><i class="icon-caret-right red"></i> 拖拽文件</span><span class="smaller-80 grey">(或者点击)上传 <br /> 文件尺寸:100px*100px <br /> 文件大小:50MB</span> <br /><i class="upload-icon icon-cloud-upload blue icon-3x"></i></div>',
// 	previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
// 	init:function(){
//             this.on("addedfile", function(file) { 
//             });
//             this.on("success",function(file,data) {
           
//             });
//             this.on("removedfile",function(file){
            	
//             });
//         }
// });



 //上传图片插件-删除监听
 $(".dz-remove").on("click",function(){
	$(this).parent().remove();
	if($("#dropzone:has(.dz-preview)").length==0){
		$("#dropzone").empty();
		$("#dropzone").append('<div class=\"dz-default dz-message\"><span>' + myDropzone.options.dictDefaultMessage + '</span></div>');
	}	
})	 
//versionstr=asdasd&platformtype=1&apptype=0&poststate=1&versiontype=&fileId=adasd&newcontent=&logourl
function submitaction(){
// 	$(".form-horizontal").submit();
	var conform = confirm("确认新增?")
	if(conform == false){
		return;
	}
	var params = {};

	var loginUrl = $('#logourl').val();
	var IosFile = $('#IosFile').val();
	if(!loginUrl&&!IosFile) {
		alert('请选择相应下载地址');
		return;
	}
	//$("input[name='versiontype']:checked").val();
	//$('#versiontype option:selected').val()
	var platformtype = $('#platformtype option:selected').val();
	if(platformtype==''){
		alert('请选择相应平台类型');//android 或 ios
		return;
	}
	var apptype = $('#apptype option:selected').val();
	if(apptype==''){
		alert('请选择相应App类型');
		return;
	}
	var poststate = $("input[name='poststate']:checked").val();
	if(poststate==''){
		alert('请选择是否发布');
		return;
	}
	var versiontype = $('#versiontype option:selected').val();
	if(versiontype==''){
		alert('请选择版本类型');
		return;
	}
	var versionstr = $('#versionstr').val();
	if(!versionstr){
		alert('请输入版本号');
		return;
	}
	 //$('#changeimg').attr("src",sourceLink);
	var sourceLink = $('#changeimg').attr("src");
	
	var html = editor.html();
	html = editor.html();
	editor.html(html);

	// 同步数据后可以直接取得textarea的value
	editor.sync();
	var newcontent = $('#editorid').val();
	params.versionstr = versionstr;
	params.loginUrl = loginUrl;
	params.IosFile = IosFile;
	params.platformtype = platformtype;
	params.apptype =apptype;
	params.poststate = poststate;
	params.versiontype =  versiontype;
	params.newcontent = newcontent;
	params.sourceLink = sourceLink;
	
	$.ajax({
		url: './others/saveVersion',
		data: params,
		type: 'POST',
		success: function(msg){

			if (msg.c == 1) {
				alert(msg.m);

				loadHtml("./others/banben");
			} else {
				alert(msg.m);
			}
		}
	})
}

function goback(){
	loadHtml("./others/banben");
}


	$(function(){
	    kedit('textarea[name="newcontent"]');
	})
	
	var editor;
	function kedit(kedit){
		//添加
		editor = KindEditor.create(kedit, {
			pasteType : 2,
			//设置可否改变编辑器大小
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : true,
			filterMode : true,
			uploadJson : '../manage/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '../manage/kindeditor/jsp/file_manager_json.jsp',
			//			width: "696px", 
			height : "270px",
			urlType : "domain",
				items : [ "fontname", "fontsize", "forecolor", "bold",
						"italic", "underline", "strikethrough" ]
		});
	}
	
	
	function changeload(){
		var type = $('#platformtype').val();
		if(type == 0){
			$('#android').show();	
			$('#IOS').hide();
			return;
		}else if(type == 1){
			$('#android').hide();	
			$('#IOS').show();
			return;
		}
	}
</script>
</body>
</html>