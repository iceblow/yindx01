<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="row">
		<div class="row">
			
			<form id="manageForm" class="form-horizontal" role="form">
		
			</form>
			
			
			<div class="col-xs-12">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div>
			</div>
			<!-- /.col -->
		</div>
	</div>
	<!-- /.row -->
	<script type="text/javascript">
		$(document).ready(function () {
	    		var rowDataNames = [];
	            $("#grid-table").jqGrid({
	                url: './others/getConfig',
	                datatype: "json",
	                page:'1',
	                colModel: [
	                    { label: 'keyname', name: 'keyname', width: 40},
	                    { label: '描述', name: '', width: 70,formatter:describFunction},
	                    { label: '配置值', name: 'configvalue', width: 40},
	                    { label: '操作', name: '', width: 50 ,formatter:changestatus},
	                ],
	                multiselect: true,
	                multiboxonly: true,
	                onSelectRow: function (rowId, status, e) {
	                	rowDataNames = [];
	                    var rowIds = jQuery("#jqGrid").jqGrid('getGridParam', 'selarrrow');
	                    $(rowIds).each(function(i,rowId){
	                        rowDataNames.push($("#jqGrid").jqGrid('getRowData',rowId))
	                    });
	                },
	                viewrecords: true, // show the current page, data rang and total records on the toolbar
					rowList:[20],
	                autowidth:true,
	                height: $(window).height()-300,
	                rowNum: 20,
	                pager: "#grid-pager",
	            	jsonReader: { root: 'rows', repeatitems: false ,page: "page",   // json中代表当前页码的数据  
	            	    records: "records", // json中代表数据行总数的数据 
	            	    total: "total"},
	            		loadComplete : function() {
							var table = this;
							setTimeout(function(){
// 								styleCheckbox(table);
// 								updateActionIcons(table);
								updatePagerIcons(table);
// 								enableTooltips(table);
							}, 0);
						},
	            });
	            
	            $('[data-rel=tooltip]').tooltip({container:'body'});
	            
	            initButton([
				/*  {
 					name: "查询", 
 					click: function(){
 						searchFrom('./signSet/getSignSet?');
 					}
 				} ,
	 			{
	 		        name: "新增", 
	 		        click: function(){
	 		        	loadHtml("./signSet/addSignSet");
	 		        }
	 			}  */
	 			])
		});
		 function replaceSpace(obj){
		        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		 }
		 
		 function changestatus(cellvalue, options, rowObject){	
	     		var html = '';
	     		html += '<button class="btn btn-xs btn-primary" onclick="edit(\''+rowObject.id+'\')">编辑</button>';
	   			return html;
	     }
	     function edit(id){
	    	   loadHtml("./others/editconfig?id="+id);
	     }
	   	 function describFunction(cellvalue, options, rowObject){
	   		var html = '';
     		if(rowObject){
     			//console.log(rowObject);
     			if(rowObject.keyname == 'order_point' ){
     				html += '表示订单可使用积分抵扣金额与订单金额的比例0到1之间';
     			}
     			if(rowObject.keyname == 'orderpaytime' ){
     				html += '表示下单之后支付的过期时间,单位分钟';
     			}
     			if(rowObject.keyname == 'register_point_user' ){
     				html += '表示用户端注册成功之后赠送的积分,正整数(第三方登录的用户需要在绑定手机号之后赠送)';
     			}
     			if(rowObject.keyname == 'register_point_aunt' ){
     				html += '表示阿姨端注册成功之后赠送的积分,正整数';
     			}
     			if(rowObject.keyname == 'user_info_point_user' ){
     				html += '表示用户端用户信息完善到100%之后赠送的积分,正整数';
     			}
     			if(rowObject.keyname == 'user_info_point_aunt' ){
     				html += '表示阿姨端用户信息完善到100%之后赠送的积分,正整数';
     			}
     			if(rowObject.keyname == 'video_point_aunt' ){
     				html += '表示阿姨端用户观看指定的视频教程达到五分钟之后增加的积分数量,正整数(每日限一次)';
     			}
     			if(rowObject.keyname == 'share_app_point_user' ){
     				html += '表示用户端分享一次App增加的积分数量,正整数(每日限5次)';
     			}
     			if(rowObject.keyname == 'comment_point_user' ){
     				html += '用户好评给与的积分，正整数';
     			}
     			if(rowObject.keyname == 'birthday_point_user' ){
     				html += '用户生日当天赠送积分,正整数';
     			}
     			if(rowObject.keyname == 'orderpay_point_user' ){
     				html += '用户支付订单成功之后赠送的积分所占订单价格的比例，0-1(且第一次为双倍)';
     			}
     			if(rowObject.keyname == 'first_orderpay_point_user' ){
     				html += '用户第一次支付订单额外赠送的积分数量';
     			}
     			if(rowObject.keyname == 'point_money' ){
     				html += '表示积分与钱的换算关系,正整数,表示多少积分抵扣一块钱';
     			}
     			if(rowObject.keyname == 'daymaxsms' ){
     				html += '表示一天之中一个手机号可以获取验证码的最大次数';
     			}
     		}
   			return html;
	   	 }
		 
	   	 
		</script>

</body>
</html>