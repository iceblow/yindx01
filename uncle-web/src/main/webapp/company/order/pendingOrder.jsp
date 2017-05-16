<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/detail.css">
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
		$(document)
				.ready(
						function() {
							$(".ull li").click(function(e) {
								$("#lione").removeAttr("id");
								$(this).attr("id", "lione");
							})
							var rowDataNames = [];
							$("#grid-table")
									.jqGrid(
											{
												url : './order/getPendingOrder?pagetype=${ sessionScope.page }',
												datatype : "json",
												page : '${page}',
												colModel : [ {
													label : '订单号',
													name : 'ordernum',
													width : 50
												}, {
													label : '服务类型',
													name : 'type',
													width : 50
												}, {
													label : '上门时间',
													name : 'gotime',
													width : 50
												}, {
													label : '金额',
													name : 'price',
													width : 50
												}, {
													label : '是否有小费',
													name : 'isprice',
													width : 50
												}, {
													label : '地址',
													name : 'addressdetail',
													width : 50
												}, {
													label : '操作',
													name : '',
													width : 50,
													formatter : changestatus
												}, ],
												multiselect : true,
												multiboxonly : true,
												onSelectRow : function(rowId,
														status, e) {
													rowDataNames = [];
													var rowIds = jQuery(
															"#jqGrid").jqGrid(
															'getGridParam',
															'selarrrow');
													$(rowIds)
															.each(
																	function(i,
																			rowId) {
																		rowDataNames
																				.push($(
																						"#jqGrid")
																						.jqGrid(
																								'getRowData',
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
													setTimeout(
															function() {
																// 								styleCheckbox(table);
																// 								updateActionIcons(table);
																updatePagerIcons(table);
																// 								enableTooltips(table);
															}, 0);
												},
											});

							$('[data-rel=tooltip]').tooltip({
								container : 'body'
							});

							// 					initButton([ {
							// 						name : "查询",
							// 						click : function() {
							// 							searchFrom('./point/getPointOrder?');
							// 						}
							// 					} ])
						});

		$("#model").dialog({
			autoOpen : false,
			width : 560,
			modal : true,
			title : '',
			show : {
				effect : "blind",
				duration : 100
			},
			hide : {
				effect : "blind",
				duration : 50
			}
		});
		$("#modelto").dialog({
			autoOpen : false,
			width : 560,
			modal : true,
			title : '',
			show : {
				effect : "blind",
				duration : 100
			},
			hide : {
				effect : "blind",
				duration : 50
			}
		});

		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}

		//加载图片
		function loadpicture(cellvalue, options, rowObject) {
			var html = '';
			html += '<img src=\''+rowObject.listpicurl +'\' style="height: 100px; width: 100px;">';
			return html;
		}

		function changestatus(cellvalue, options, rowObject) {

			var html = '';
			if (rowObject.ordertype == 0) {
				console.log(rowObject);
				html += '<button class="btn btn-xs btn-primary" onclick="onOrder(\''
						+ rowObject.orderid
						+ '\',\''
						+ rowObject.id
						+ '\',\''
						+ 0
						+ '\')" style="background-color:rgb(65,139,202) !important;border-color:rgb(65,139,202) !important;margin-right:10px;">接单</button>';
				html += '<button class="btn btn-xs btn-primary" onclick="onOrder(\''
						+ rowObject.orderid
						+ '\',\''
						+ rowObject.id
						+ '\',\''
						+ 2
						+ '\')" style="background-color:rgb(244,86,75) !important;border-color:rgb(244,86,75) !important;margin-right:10px;">拒单</button>';
			} else if (rowObject.ordertype == 1) {
				html += '<button class="btn btn-xs btn-primary" onclick="onOrder(\''
						+ rowObject.orderid
						+ rowObject.ordernum
						+ '\',\''
						+ rowObject.id
						+ '\',\''
						+ 1
						+ '\')" style="background-color:rgb(255,159,36) !important;border-color:rgb(255,159,36) !important;margin-right:10px;">抢单</button>';
			} else if (rowObject.ordertype == 3) {
				html += '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="onOrder(\''
						+ rowObject.orderid
						+ '\',3)" style="background-color:rgb(7,196,176) !important;border-color:rgb(7,196,176) !important;margin-right:10px;">派单</button>';
			}
			html += '<button class="btn btn-xs btn-primary" onclick="detail(\''
					+ rowObject.orderid
					+ '\')" style="background-color:rgb(140,177,196) !important;border-color:rgb(140,177,196) !important;margin-right:10px;">查看</button>';
			return html;
		}

		function deliver(id) {
			$('#orderid').val(id);
			$('.tips').show();
		}

		function cancel() {
			$('.tips').hide();
		}
		function onOrder(id, ordernum, type) {
			if (type == 0) {
				if (!confirm("是否确定接单")) {
					return;
				}
				$.ajax({
					url : './order/accepeOrder',
					data : {
						orderidUser : id,
						orderid : ordernum
					},
					type : 'POST',
					success : function(msg) {
						var data = eval('(' + msg + ')');
						alert(data.message);
						loadHtml("./order/pendingOrder?page=1");
					}
				})
			}
			if (type == 1) {
				if (!confirm("是否确定抢单")) {
					return;
				}
				$.ajax({
					url : './order/accepeOrder',
					data : {
						orderidUser : id,
						orderid : ordernum
					},
					type : 'POST',
					success : function(msg) {
						var data = eval('(' + msg + ')');
						alert(data.message);
						loadHtml("./order/pendingOrder?page=1");
					}
				})
			}
			//拒单弹窗
			if (type == 2) {
				$('#hiddens').val(id);
				$('#hiddenid').val(ordernum);
				$("#modelto").dialog("open");
				return;
			}
			if (type == 3) {
				if (!confirm("是否确定派单")) {
					return;
				}
			}
			$
					.ajax({
						url : './order/onOrder',
						data : {
							orderid : id,
							type : type
						},
						success : function(msg) {
							if (msg != null) {
								msg = eval("(" + msg + ")");
								if (msg.code == 1) {
									alert(msg.message);
									loadHtml('./order/getPendingOrder?pagetype=${ sessionScope.page }');
								} else {
									alert(msg.message);
								}
							}
						}
					})
		}
		function detail(id) {
			$
					.ajax({
						url : './order/orderDetail',
						data : {
							orderid : id
						},
						success : function(msg) {
							if (msg != null) {
								if (msg.code == 0) {
									$("#title").html(msg.order.type);
									$("#gotime").html(msg.order.gotime);
									$("#goprice").html(msg.order.goprice);
									$("#yutime").html(msg.order.yutime);
									$("#re").html(msg.order.re);
									$("#address").html(msg.order.address);
									var img = "";
									console.log(msg);
									console.log(msg.order.img);
									if (msg.order.contant != null) {
										$("#contant").html(msg.order.contant);
									} else {
										$('#contant').parent('tr').css(
												'display', 'none');
									}
									if (msg.order.img != null) {
										for (var i = 0; i < msg.order.img.length; i++) {
											img += '<img src='+msg.order.img[i]+' class="detail_img">';
										}
										$("#img").html(img);
									} else {
										$('#img').parent('tr').css('display',
												'none');
									}
									$("#model").dialog("open");
								} else {
									alert(msg.message);
								}
							}
						}
					})
		}
		function refuse(reason,content) {
			id=$('#hiddens').val()
			ordernum=$('#hidden').val()
			$.ajax({
				url : './order/refuseOrder',
				data : {
					orderidUser : id,
					orderid : ordernum
				},
				type : 'POST',
				success : function(msg) {
					var data = eval('(' + msg + ')');
					alert(data.message);
					$(".detail_infor").dialog("close");
					loadHtml("./order/pendingOrder?page=1");
					
				}
			})
		}
		
		function closeRefuse() {
			$(".detail_infor").dialog("close");
		}
	</script>

	<div class="detail_infor" style="display: none;" id="model">
		<div class="top_text">
			查看
			<!--         <span class="del_btn">×</span> -->
		</div>
		<div class="top_title" id="title"></div>
		<table cellspacing="0" id="detail_list">
			<tr>
				<td>清理内容</td>
				<td id="contant"></td>
			</tr>
			<tr>
				<td>上门时间</td>
				<td id="gotime"></td>
			</tr>
			<tr>
				<td>上门服务费</td>
				<td id="goprice"></td>
			</tr>
			<tr>
				<td>预计时长</td>
				<td id="yutime"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td id="re"></td>
			</tr>
			<tr>
				<td>照片</td>
				<td id="img">
					<!--                 <img src="../img/detail1.png" class="detail_img"> -->
					<!--                 <img src="../img/detail2.png" class="detail_img"> -->
				</td>
			</tr>
			<tr>
				<td>雇主信息</td>
				<td id="address"></td>
			</tr>

		</table>
	</div>
	<input type="hidden" id="hiddens" value="">
	
	<input type="hidden" id="hiddenid" value="">

	<div class="detail_infor" id="modelto" style="display: none;">
		<div class="top_text">
			请提交拒单理由
			<!--         <span class="del_btn">×</span> -->
		</div>
		<ul class="ull">
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
			<li>5</li>
		</ul>
		<textarea rows="" cols="" placeholder="请输入其他拒单原因"></textarea>
		<div class="btm">
			<button class="btn btn-xs btn-primary"
				style="background-color: rgb(140, 177, 196) !important; border-color: rgb(140, 177, 196) !important; margin-right: 10px;" onclick="closeRefuse()">取消</button>
			<button class="btn btn-xs btn-primary"
				style="background-color: rgb(65, 139, 202) !important; border-color: rgb(65, 139, 202) !important; margin-right: 10px;" onclick="refuse('dadsa','d')">确定</button>
		</div>
	</div>

</body>
</html>