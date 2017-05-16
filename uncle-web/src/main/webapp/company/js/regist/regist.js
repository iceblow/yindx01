function getContextPath() {

    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
var ctxpath = getContextPath();
var vCode;
var sendPhone;
var t=60;
var a;
$(function(){
	//console.log(ctxpath);
	$('#sendVCode').click(function(){
		var phone = $('#phoneVal').val();
		if(checkPhone(phone)){
			getVCode(phone);
		}
	});
	
	$('#phoneVal').change(function(){
		$('#sendVCode').attr('style','');
		$('#checkVCode').attr('style','display:none;');
	});
	
	$('.login').click(function(){
		window.location.href="./login";
	});
});

$(document).on('click','.com_sort',function(){
	$('.com_sort').find('img').attr('src','./img/reg5.png');
	$(this).find('img').attr('src','./img/reg4.png');
});

function checkPhone(phone){
	if(phone.length!=11){
		alert('请输入一个正确的电话号码');
		return false;
	}
	return true;
}

function getVCode(phone){
	var params = {};
	params.phone = phone;
	params.type="0";
	$.ajax({
		url:ctxpath+'/company/getRegistVCode',
		type:'POST',
		data:params,
		success:function(result){
			//console.log(result);
//			console.log(result.c);
			if(result.c=='1'){
				console.log(1);
				a=setInterval(daojishi,1000);//1000毫秒
//				var code = eval("("+result+")");
//				vCode = code.vcode;
				sendPhone = phone;
				alert("发送验证码成功");
				$('#sendVCode').attr('style','display:none;');
				$('#checkVCode').attr('style','background:gray;');
			}else if(result.c=='3'){
				alert(result.m);
			}else{
				if(result.m){
					alert(result.m);
				}else{
					alert('获取验证失败');
				}
			}
		}
	});

}

function doRegist(){
	var phone = $('#phoneVal').val();
	var sendVCode = $('#vcode_input').val();
	if(!checkPhone(phone)){
		alert("请确认你的手机且已获取验证码");
		return;
	}
	if(sendVCode.length!=6){
		alert('请输入你收到的验证码');
		return;
	}
	var pwd = $('#password').val();
	if(pwd==''){
		alert('请输入密码!');
		return;
	}
	var company_type = null;
	$('.com_sort').each(function(){
		if($(this).find('img').attr('src')=='./img/reg4.png'){
			company_type = $(this).data('type');
		}
	});
	var company_name = $("#com_name").val();
	var company_ads = $("#com_ads").val();
	if(company_name==''){
		alert('请输入公司名称!');
		return;
	}
	if(company_ads==''){
		alert('请输入公司地址!');
		return;
	}
	
	var params = {};
	params.phone = phone;
	params.sendVCode=sendVCode;
	params.pwd = pwd;
	params.company_type = company_type;
	params.company_name = company_name;
	params.company_ads = company_ads;
	$.ajax({
		url:ctxpath+'/company/doRegist',
		type:'POST',
		data:params,
		success:function(result){
			if(result.c=='1'){
				window.location.href="./login"
			}else{
				if(result.m){
					alert(result.m);
				}else{
					alert('获取验证失败');
				}
			}
			
		}
	});
}
function daojishi(){
    t--;
    $('#jishi').text("("+t+")");
//刷新时间显示
    if(t==0){
        clearInterval(a);
        $('#sendVCode').attr('style','display:inline-block;');
        $('#checkVCode').attr('style','display:none;');
        t = 60;
        $('#jishi').text("(60)");
            //倒计时结束
    }
}