
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
						for="form-field-6">订单号</label>
					<div class="col-sm-3">
						<input id="coupon_condition" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.ordernum}"
							onBlur="replaceSpace(this)" name="coupon_condition"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">发布者类型</label>
					<div class="col-sm-3">
						<input id="discount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strPosterType}"
							onBlur="replaceSpace(this)" name="discount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">用户id</label>
					<div class="col-sm-3">
						<input id="discount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.userid}"
							onBlur="replaceSpace(this)" name="discount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">二级分类</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.categoryname}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			

			<div class="col-xs-12" id="showcompany" style="display: none">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">所属城市</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.city}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">服务地址联系电话</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.phone}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">服务地址联系人姓名</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.rname}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">服务地址联系人性别</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.sex}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">服务地址地址名称</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.addressname}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">服务地址地址详细</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.addressdetail}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">上门时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.serverTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">用户上传图片的id集合</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.picIds}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">预计时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.expectTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">需要男性数量</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.auntMCount}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">需要女性数量</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.auntWCount}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">每天的服务时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.dayTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">相关数量</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.thingCount}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">是否需要自带工具</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.needTools}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">公司id</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.companyid}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单的单价</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.expectedPrice}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单定金</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.depositPrice}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单的小费</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.tipPrice}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单实际价格</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.lastPrice}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单增值服务费用</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.otherPirce}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单被优惠券减免的金额</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.couponPirce}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单类型</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strOrderType}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">关联订单id</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.relationOrderid}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单状态</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strState}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">投诉状态</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strComplaintState}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单评价状态</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strCommentState}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单接单方式</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strAcceptType}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单来源</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.strOrderSource}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">备注信息</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.book}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">总的服务开始时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.serverStartTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">总的服务结束时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.serverEndTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">订单被接单的时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.acceptTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">定金支付时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.payTime2}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">剩余金额支付时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.payTime1}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">长期订单支付到的时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.payedTime}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">做饭订单的选择</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.foodselect}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">其他服务内容描述</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.reasonMark}"
							onBlur="replaceSpace(this)" name="totalcount"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-12" id="showcategoryid">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6">下单时间</label>
					<div class="col-sm-3">
						<input id="totalcount" data-rel="tooltip" type="text"
							class="col-sm-10 searchKey" value="${order.addtime}"
							onBlur="replaceSpace(this)" name="totalcount"
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
	 		        	window.history.back();
	 		        	location.reload();
	 		        }
	 			},
	 			
	 			])
	 			
		});
		   
		

	
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