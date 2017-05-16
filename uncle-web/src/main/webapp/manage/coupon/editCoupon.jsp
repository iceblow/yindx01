
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<div class="row">
		<form id="riderForm" class="form-horizontal" role="form" action="">
			<input id="" name="couponid" type="hidden" value="${coupon.couponid}">
			<!-- <div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">图片</label>
					<div class="col-sm-3">
						<input type="file" value="" id="pic_file" name="picid" style="width: 100px; height: 100px; position: relative; z-index: 100; opacity: 0;">
							<img src="../img/default.png" style="width: 100px; height: 100px; margin-top: -100px;" id="imgs">
						PAGE CONTENT ENDS
					</div>
				</div>
			</div> -->
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">满足条件</label>
					<div class="col-sm-3">
						<input id="coupon_condition" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${coupon.couponCondition}"
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
							class="col-sm-10 searchKey" value="${coupon.discount}"
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
							class="col-sm-10 searchKey" value="${coupon.totalcount}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<!-- <div class="col-xs-12">
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
			</div> -->
			<%-- <div class="col-xs-12" id="stimeDiv" style="display: none">
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
			</div> --%>
			
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
	 		        name: "编辑", //这里是静态页的地址
	 		        click: function(){
	 		        	submitaction();
	 		        }
	 			}
	 			])
	 			
		});
		   
		

	function submitaction(){
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
 		if(!$('#maxreceive').val()){
     		alert('请设置一个用户可以领取的最大数量');
     		return;
     	}
		$.ajax({
			url:'./coupon/editCouponData',
			data:$('#riderForm').serialize(),// 你的formid
			success:function(){
					alert("编辑成功");
					loadHtml("./coupon/couponlist");
				}		
		})
	}
		 function replaceSpace(obj){
		        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		   }
		 
		 
		  /* function rangetypeChange(){
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
				
			} */
		</script>
		
</body>
</html>