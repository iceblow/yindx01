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
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">订单号</label>
					<div class="col-sm-8">
						<input data-rel="tooltip" type="text" class="col-sm-10 searchKey"
							onBlur="replaceSpace(this)" name="ordernum" title="订单号"
							data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">订单类型</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="selectone()"
							name="categoryid" title="订单类型" data-placement="bottom" id="category">
							<option value="-1">全部</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">手机号</label>
					<div class="col-sm-8">
						<input data-rel="tooltip" type="text" class="col-sm-10 searchKey"
							onBlur="replaceSpace(this)" name="phone" title="手机号"
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
	</div>
	<!-- /.row -->
	<script type="text/javascript">
		$(document).ready(
				function() {
					var rowDataNames = [];
					$("#grid-table").jqGrid(
							{
								url : './order/todoList',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '订单号',
									name : 'ordernum',
									width : 50
								}, {
									label : '订单类型',
									name : 'categoryname',
									width : 50
								}, {
									label : '发布者类型',
									name : '',
									width : 50,
									formatter : postertype
								}, {
									label : '所属城市',
									name : 'city',
									width : 50
								}, {
									label : '需要男性数量',
									name : 'auntMCount',
									width : 50
								}, {
									label : '需要女性数量',
									name : 'auntWCount',
									width : 50
								}, {
									label : '是否需要自带工具',
									name : '',
									width : 50,
									formatter : needtools
								}, {
									label : '预计金额',
									name : 'expectedPrice',
									width : 50
								}, {
									label : '定金',
									name : 'depositPrice',
									width : 50
								}, {
									label : '状态',
									name : '',
									width : 50,
									formatter : stateformat
								},{
									label : '操作',
									name : '',
									width : 50,
									formatter : changestatus
								}, ],
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
								height : $(window).height() - 300,
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

 					initButton([ {
 						name : "查询",
						click : function() {
							searchFrom('./order/getTodoOrder?');
						}
 					} ])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}

		function changestatus(cellvalue, options, rowObject) {
			var html = '';
 			html += '<button class="btn btn-xs btn-primary" onclick="detail(\''
 				+ rowObject.orderid + '\')" style="margin-right:10px;">查看</button>';
			return html;
		}
		function detail(id){
			loadHtml("./order/editOrder?orderid="+id);
		}
		
		function postertype(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.posterType == 0) {
				html = '用户';
			} else {
				html = '公司';
			}
			return html;
		}
		
		function needtools(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.needTools == 0) {
				html = '不需要';
			} else {
				html = '需要';
			}
			return html;
		}

		function stateformat(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.state == 0) {
				html = '待付定金';
			} else if (rowObject.state == 1) {
				html = '待接单/抢单';
			} else {
				html = '未出发';
			}
			return html;
		}
		
		$(function(){
			
				var html = '';
				$.ajax({
					url:'./order/getCategory',
					data:{},
					success:function(msg){				
	           			var data = eval("("+msg+")");
	           			for(var i = 0 ;i < data.list.length ; i++){
	           				html = "<option value="+data.list[i].dataid+">"+data.list[i].name+"</option>";
	           				$("#category").append(html);
	           				
	           			}
					}
				})
			
		});
		
	</script>
</body>
</html>