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

	<script type="text/javascript">
		$(document).ready(
				function() {
					var rowDataNames = [];
					$("#grid-table").jqGrid(
							{
								url : './puse/getPuseAuntList',
								datatype : "json",
								page : '${page}',
								colModel : [ {
									label : '消息类型',
									name : '',
									width : 50,
									formatter : postertype
								}, {
									label : '消息标题',
									name : 'title',
									width : 50,
								}, {
									label : '消息详情',
									name : 'detail',
									width : 50,
								}, {
									label : '连接标题',
									name : '',
									width : 50,
									formatter : linktitle
								}, {
									label : '连接类型',
									name : '',
									width : 50,
									formatter : linktype
								}, {
									label : '连接的附带信息',
									name : '',
									width : 50,
									formatter : linkcontent
								}, {
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
								rowNum : 10,
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
						name : "新增",
						click : function() {
							loadHtml('./puse/goaddAuntPuse');
						}
					} ])
				});
		function postertype(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.type == 1) {
				html = '系统消息';
			} else if (rowObject.type == 2) {
				html = '广告';
			} else if (rowObject.type == 3) {
				html = '官方通知';
			} else if (rowObject.type == 4) {
				html = '活动通知';
			}
			return html;
		}

		function linktype(cellvalue, options, rowObject) {
			var html = '';
			if (isnull(rowObject.link_title, rowObject.link_content,
					rowObject.link_type)) {
				if (rowObject.link_type == 0) {
					html = '个人资料完善资料';
				} else if (rowObject.link_type == 1) {
					html = '订单详情';
				} else if (rowObject.link_type == 2) {
					html = '家政公司';
				} else if (rowObject.link_type == 3) {
					html = '阿姨';
				} else if (rowObject.link_type == 4) {
					html = '网页';
				}
			}
			return html;
		}

		function linktitle(cellvalue, options, rowObject) {
			var html = '';
			if (isnull(rowObject.link_title, rowObject.link_content,
					rowObject.link_type)) {
				html = rowObject.link_title;
			}
			return html;
		}

		function linkcontent(cellvalue, options, rowObject) {
			var html = '';
			if (isnull(rowObject.link_title, rowObject.link_content,
					rowObject.link_type)) {
				if (rowObject.link_type == 0) {
					html = '个人资料完善资料';
				} else if (rowObject.link_type == 1) {
					html = rowObject.link_content;
				} else if (rowObject.link_type == 2) {
					$.ajax({
						url : './puse/getcompanyByid',
						async : false,
						data : {
							companyId : rowObject.link_content
						},
						success : function(msg) {
							if (msg != null) {
								html = msg;
							}
						}
					})
				} else if (rowObject.link_type == 3) {
					$.ajax({
						url : './puse/getAuntByid',
						async : false,
						data : {
							id : rowObject.link_content
						},
						success : function(msg) {
							if (msg != null) {
								html = msg;
							}
						}
					})

				} else if (rowObject.link_type == 4) {
					html = rowObject.link_content;
				}
			}
			return html;
		}

		function isnull(link_title, link_content, link_type) {
			if (link_title != null
					&& link_content != null
					&& (link_type == 0 || link_type == 1 || link_type == 2
							|| link_type == 3 || link_type == 4)) {
				return true;
			}
			return false;
		}

		function changestatus(cellvalue, options, rowObject) {
			var html = '';
			html += '<button class="btn btn-xs btn-primary" style="margin-left: 10px;" onclick="del(\''
					+ rowObject.id + '\')">删除</button>';
			return html;
		}

		function del(id) {
			
			if (!confirm("确定删除么？")) {
				return;
			}
			$.ajax({
				url : './puse/delAuntPuse',
				data : {
					id : id
				},
				type : 'POST',
				success : function(code) {
					if (code == 0) {
						alert("删除失败");
						return;
					} else {
						alert("删除成功");
						loadHtml("./puse/puseAuntList");
					}
				}
			})
		}
		
		
	</script>

</body>
</html>