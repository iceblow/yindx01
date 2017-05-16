// 需要引入jquery.form.js, common.js, <jsp:include page="../common/salert.jsp" />

// options = 
//	target        返回的结果将放到这个target下
//	url           如果定义了，将覆盖原form的action
//	type          get和post两种方式
//	dataType      返回的数据类型，可选：json、xml、script
//	clearForm     true，表示成功提交后清除所有表单字段值
//	resetForm     true，表示成功提交后重置所有字段
//	iframe        如果设置，表示将使用iframe方式提交表单
//	beforeSerialize    数据序列化前：function($form,options){}
//	beforeSubmit  提交前：function(arr,$from,options){}
//	success       提交成功后：function(data,statusText){}
//	error         错误：function(data){alert(data.message);} 

function uploadImage(formId,uploadBtnId,params,callback) {
	// 判断是否有选择上传文件
    var imgPath = $("#"+uploadBtnId).val();
    if (imgPath == "") {
			showAlert("警告",'请选择上传图片！');
        return;
    }
    // 判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    if (strExtension != 'jpg' && strExtension != 'gif'
    && strExtension != 'png' && strExtension != 'bmp') {
        showAlert("警告",'请选择图片文件！');
        return;
    }
   /* if(!type || type.trim() == ''){
    		showAlert("警告",'请选择上传文件类型。');
    		return ;
    }*/
    var access = signature(params);
	$('#appid').val(access.appid);
	$('#accesstime').val(access.accesstime);
	$('#accesskey').val(access.accesskey);
	var options = {
		url:server + '/lvguoguo/api/file/upload',
		type: 'POST',
		dataType: 'json',
		success: function(res){
			if ($.isFunction(callback)) {
				if(res.code == '1'){
					try {
						callback($.parseJSON(res.result));
					} catch(e) {
						callback(res.result);
					}
				}else{
					console.log(res.message);
					showAlert("警告",res.message);
				}
			}
		},
		error: function(){
			console.log('上传失败。');
		}
	}
    $('#'+formId).ajaxSubmit(options);
}
