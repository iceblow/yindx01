var appid = 'lvguoguo_web_1.0';
var appkey = '96e79218965eb72c92a549dd5a330112';
var server = ''; // 'http://114.215.46.202:8080';

$(function(){
	back();
})

function back(){
	$('#leftArr').on('click',function(){
		history.back();
	})
}

// 提取json数据的key,转成数组，并排序
function toArrayParams(json) {
	var params = [];
	for ( var key in json) {
		params.push(key);
	}
	params.sort();
	return params;
}

// 签名,json参数jsonParams
function signature(jsonParams, accesstoken) {
	var accesstime = parseInt(new Date().getTime() / 1000) + '';
	// 使用32位的MD5算法生成。生成方式:md5(accesstime + appid + appkey + 请求参数名称按照字母大小顺序顺序排序)
	// 注:请求参数无论是否必须都需要进入验证机制
	var checkString = accesstime + appid + appkey;
	var arrParamsKey = toArrayParams(jsonParams);
	for (var i = 0; i < arrParamsKey.length; i++) {
		checkString += arrParamsKey[i];
	}
	var accesskey = $.md5(checkString);
	var access = {
		appid : appid,
		accesstime : accesstime,
		accesskey : accesskey
	};
	if (accesstoken) {
		access['accesstoken'] = accesstoken;
	}
	// console.log(access);
	return access;
}

// ajax请求，在调用ajax的页面body前引入 <jsp:include page="../common/salert.jsp" />
function ajax(url, params, callback) {
	console.log(url);
	var access = signature(params);
	$.extend(params, access);
	$.post(url, params, function(res) {
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
	})
}

// ajax请求，在调用ajax的页面body前引入 <jsp:include page="../common/salert.jsp" />
function ajax2(url, params, accesstoken, callback) {
	console.log(url);
	if($.type(accesstoken) != 'string'){
		console.log('第三个参数错误。');
		return ;
	}
	var access = signature(params, accesstoken);
	$.extend(params, access);
	$.post(url, params, function(res) {
		if ($.isFunction(callback)) {
			if(res.code == '1'){
				try {
					callback($.parseJSON(res.result));
				} catch(e) {
					callback(res.result);
				}
			}else{
				showAlert("警告",res.message);
			}
		}
	})
}

// 获取Url 的参数
function getPara(name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
    var r = window.location.search.substr(1).match(reg);  
    if (r!=null) return (r[2]); return null;  
}  

// 验证手机号码，正常返回true,错误返回false
function valiPhone(phone){
	// 验证130-139,150-159,180-189号码段的手机号码
	var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!reg.test(phone)){ 
		showAlert("警告",'手机号码格式不正确。');
		return false; 
    }
    return true;
}

// 判断用户是否登录
function isLogin(callback){
	var user = $.parseJSON(sessionStorage.getItem('userinfo'));
	if(!user){
				location.href = 'go?uri=wechat/regist/accountlogin';
		return ;
	}
	if($.isFunction(callback)){
		callback();
	}
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { // author: meizz
 var o = {
     "M+": this.getMonth() + 1, // 月份
     "d+": this.getDate(), // 日
     "h+": this.getHours(), // 小时
     "m+": this.getMinutes(), // 分
     "s+": this.getSeconds(), // 秒
     "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
     "S": this.getMilliseconds() // 毫秒
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}

