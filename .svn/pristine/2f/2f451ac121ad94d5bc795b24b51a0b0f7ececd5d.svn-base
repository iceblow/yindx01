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
		<form class="form-horizontal" role="form">
			<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">范围类型</label>
					<div class="col-sm-8">
						<select class="col-sm-10 searchKey" id="s1" name="rangetype">
							<option value="-1">请选择</option>
							<option value="0">平台券</option>
							<option value="1">公司券</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">发放类型</label>
					<div class="col-sm-8">
						<select class="col-sm-10 searchKey" id="s2" name="granttype">
							<option value="-1">请选择</option>
							<option value="0">首次登陆发放</option>
							<option value="1">自己领取类</option>
							<option value="2">分享随机领取</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">状态</label>
					<div class="col-sm-8">
						<select class="col-sm-10 searchKey" id="s3" name="delstate">
							<option value="-1">请选择</option>
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</div>
				</div>
			</div>
	</form>
	
		
	
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<!-- /.row -->
	<script type="text/javascript">
		$(document).ready(
				function() {
					var rowDataNames = [];
					$("#grid-table").jqGrid(
							{
								url : './coupon/getCouponList',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '优惠券编号',
									name : 'couponNum',
									width : 50,
								}, {
									label : '满足条件',
									name : 'couponCondition',
									width : 50
								}, {
									label : '减免价格',
									name : 'discount',
									width : 50,
								},{
									label : '范围类型',
									name : 'rangetype',
									width : 50,
									formatter : rangetype
								},{
									label : '公司名称',
									name : 'companyid',
									width : 50,
									formatter : companyid
								},{
									label : '发放类型',
									name : 'granttype',
									width : 50,
									formatter : granttype
								},{
									label : '总张数',
									name : 'totalcount',
									width : 50,
								},{
									label : '已领张数',
									name : 'getcount',
									width : 50,
								},{
									label : '可领数量',
									name : 'maxreceive',
									width : 50,
								},{
									label : '添加时间',
									name : 'addtime',
									width : 50,
									formatter : timeFmt
								},{
									label : '删除状态',
									name : 'delstate',
									width : 50,
									formatter : delstate
								}, {
									label : '操作',
									name : 'opreation',
									width : 100,
									formatter : changestatus
								} ],
								multiselect : true,
								multiboxonly : true,
								onSelectRow : function(rowId, status, e) {
									rowDataNames = [];
									var rowIds = jQuery("#jqGrid").jqGrid(
											'getGridParam', 'selarrrow');
									$(rowIds).each(
											function(i, rowId) {
												rowDataNames.push($("#jqGrid")
														.jqGrid('getRowData',
																rowId))
											});
								},
								viewrecords : true, // show the current page, data rang and total records on the toolbar
								rowList : [ 10, 20, 30 ],
								autowidth : true,
								height : $(window).height()-300,
								rowNum: 10,
								pager : "#grid-pager",
								jsonReader : {
									root : 'rows',
									repeatitems : false,
									page : "page", // json中代表当前页码的数据  
									records : "records", // json中代表数据行总数的数据 
									total : "total"
								},
								loadComplete : function() {
									var table = this;
									setTimeout(function() {
										updatePagerIcons(table);
									}, 0);
								},
							});

					$('[data-rel=tooltip]').tooltip({
						container : 'body'
					});

					initButton([{
				        name: "查询", //这里是静态页的地址
				        click: function(){
				        	
				        	searchFrom('./coupon/getCouponList?');
				        }
			    	},{
						name: "新增", //这里是静态页的地址
				        click: function(){
				        	loadHtml("./coupon/addcoupon");
				        }	
					}])
					
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}
		

		function getLocalTime(nS) {     
	       	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
	    }
		
		function rangetype(cellvalue, options, rowObject) {
			var html = '';
			if(rowObject.rangetype == 0){
				html = '平台券';
			}else{
				html = '公司券';
			}
			return html;
		}
		
		function granttype(cellvalue, options, rowObject) {
			var html = '';
			if(rowObject.granttype == 0){
				html = '首次登陆发放';
			}
			if(rowObject.granttype == 1){
				html = '自己领取类';
			}
			if(rowObject.granttype == 2){
				html = '分享随机领取';
			}
			return html;
		}
		
		function companyid(cellvalue, options, rowObject) {
			var html = '';
			if(rowObject.companyid != 0){
				$.ajax({
					url:'./coupon/getCompanyList',
					data:{},
					success:function(msg){
	           			var data = eval("("+msg+")");
	           			for(var i = 0 ;i < data.list.length ; i++){
	           				if(rowObject.companyid==data.list[i].companyid){
	           					html = data.list[i].name;
	           					return html;
	           				}
	           			}
					}
				})
			}else{
				html = "平台";
				return html;
			}
		}
		
		function timeFmt(cellvalue, options, rowObject) {
			var html = getLocalTime(rowObject.addtime / 1000);
			
			return html;
		}
		
		function delstate(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.delstate == 0) {
				html = '未删除';
			} else {
				html = '已删除';
			}
			return html;
		}
		
		

		 function changestatus(cellvalue, options, rowObject){	
	     		var html = '';
	     		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="editAction(\''+rowObject.couponid +'\')">编辑</button>';
	       		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.couponid +'\')">删除</button>';    					
	   			return html;
	     	}
		
		
        //编辑
        function editAction(id){
        	loadHtml("./coupon/editCoupon?couponid="+id);
        }
        
        
        
        //删除
        function deleteAction(couponid){
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
	        	$.ajax({
	        		url:'./coupon/delcoupon',
	        		type:"post",
	        		data:{couponid:couponid},
	        		success:function(result){
	        			if(result == 0){
	        				alert("删除失败");
	        				return;
	        			}else{
	        				alert("删除成功");
	        				loadHtml("./coupon/couponlist");
	        			}
	        		}
	        	});
        	}
        	return;
        }		
		
      
        function dialog(id,type){
        	$('#dialog').attr("dialogUrl","./home/loaddetail?id="+id+"&type="+type);
        	$( "#dialog" ).dialog( "open" );
        }
        
	</script>
	
	
	
</body>
</html>