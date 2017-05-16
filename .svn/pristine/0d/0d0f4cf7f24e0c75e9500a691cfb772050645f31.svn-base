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
								url : './admin/getRoleList',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '角色名称',
									name : 'rolename',
									width : 50
								}, {
									label : '角色类型',
									name : '',
									width : 50,
									formatter : roletype
								}, {
									label : '角色权限',
									name : 'permissions',
									width : 50
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

// 					initButton([ {
// 						name : "新增",
// 						click : function() {
							
// 						}
// 					} ])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}

		function changestatus(cellvalue, options, rowObject) {
			var html = '';
// 			html += '<button class="btn btn-xs btn-primary" onclick="detail(\''
// 				+ rowObject.orderid + '\')" style="background-color:rgb(140,177,196) !important;border-color:rgb(140,177,196) !important;margin-right:10px;">查看</button>';
			return html;
		}
		
		function roletype(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.roletype == 0) {
				html = '平台运营';
			} else if (rowObject.roletype == 1) {
				html = '自营店';
			} else {
				html = '加盟店';
			}
			return html;
		}

	</script>
</body>
</html>