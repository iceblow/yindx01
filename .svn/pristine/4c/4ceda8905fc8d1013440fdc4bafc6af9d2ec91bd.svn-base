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
		<div id="replyModalTO">
			<form id="" role="form" action="" enctype="multipart/form-data"
				method="post">
				<div class="modal-body form-horizontal row">
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">真实姓名</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="realName" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">手机号码</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="phone" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">联系电话</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="tel" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">性别</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="sex" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">出生日期</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="birthday" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">VIP等级</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="level" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">个性签名</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="signature" value="" readonly="readonly">
							</div>
						</div>
					</div>
						<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">注册时间</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="addtime" value="" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<form id="manageForm" class="form-horizontal" role="form">
			<div class="col-xs-2">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">姓名</label>
					<div class="col-sm-8">
						<input data-rel="tooltip" type="text" class="col-sm-10 searchKey"
							onBlur="replaceSpace(this)" name="name" title="姓名"
							data-placement="bottom">
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
		<!-- /.col -->
	</div>
	<!-- /.row -->
		<div class="tipsOt"
		style="position: absolute; margin-top: -30%; width: 60%; margin-left: 30%; display: none">
		<div class="col-sm-5">
			<div class="widget-box">
				<div class="widget-header">
					<h4>修改积分</h4>
				</div>

				<div class="widget-body">
					<div class="widget-main no-padding">
						<form method="post" id="addcategory">
							<!-- <legend>Form</legend> -->
							<input id="orderid" name="orderid" type="hidden" />
							 <input id="logistic" name="logistic" type="hidden" />
							<fieldset>
								<label>积分</label> <input id="logisticnum" name="logisticnum"
									type="text" placeholder="积分" />
							</fieldset>
							<div class="form-actions center">
								<button type="button" class="btn btn-sm btn-success"
									style="margin-right: 100px;" onclick="deliverGoods()">
									确定 <i class="icon-arrow-right icon-on-right bigger-110"></i>
								</button>
								<button type="button" class="btn btn-sm btn-success"
									onclick="cancel()">
									取消 <i class="icon-arrow-right icon-on-right bigger-110"></i>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	$("#replyModalTO").dialog({
		autoOpen : false,
		height : 600,
		width : 1000,
		modal : true,
		title : '',
		buttons : {
			"关闭" : function() {
				$("#replyModalTO").dialog("close");
			}
		},
		show : {
			effect : "blind",
			duration : 100
		},
		hide : {
			effect : "blind",
			duration : 50
		}
	});
		$(document).ready(
				function() {

					var rowDataNames = [];

					$("#grid-table").jqGrid(
							{
								url : './user/getAppUser',
								datatype : "json",
								page : '${page}',
								colModel : [ {
									label : '姓名',
									name : 'realName',
									width : 50
								}, {
									label : '手机号',
									name : 'phone',
									width : 50
								}, {
									label : '性别',
									name : 'sex',
									width : 50
								}, {
									label : '等级',
									name : 'level',
									width : 50
								}, {
									label : '出生日期',
									name : 'birthdays',
									width : 50
								}, {
									label : '添加时间',
									name : 'addtimes',
									width : 50
								}, {
									label : '操作',
									name : '',
									width : 80,
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

					initButton([ {
						name : "查询",
						click : function() {
							searchFrom('./user/getAppUser?');
						}
					} ])
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
			html += '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="detail(\''
					+ rowObject.userid + '\')">查看详情</button>';
			html += '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="changPrice(\''
						+ rowObject.userid + '\')">积分修改</button>';
			return html;
		}

		function detail(id) {
			$("#replyModalTO").dialog("open");
			$.ajax({
				url : './user/getAppUserByid',
				data : {
					id : id
				},
				success : function(msg) {
					if (msg != null) {
						console.log(msg);
						$('#realName').val(msg.realName);
						$('#phone').val(msg.phone);
						$('#tel').val(msg.tel);
						$('#sex').val(msg.sex);
						$('#birthday').val(msg.birthdays);
						$('#level').val(msg.level);
						$('#signature').val(msg.signature);
						$('#addtime').val(msg.addtimes);
					}
				}
			})
		}
		function changPrice(id){
			$('#orderid').val(id);
			$('.tipsOt').show();	
		}
		
		function deliverGoods() {
			var orderid = $('#orderid').val();
			var logisticnum = $('#logisticnum').val().trim();
			if (logisticnum == '') {
				alert("请输入积分！");
				return;
			}
			if (isNaN(logisticnum)) {
				alert("请输入数字！");
				return;
			}
			var dot = logisticnum.indexOf(".");
			if (dot != -1) {
				var dotCnt = logisticnum.substring(dot+1,logisticnum.length);
                if(dotCnt.length > 2){
                    alert("小数位已超过2位！");
                    return;
                }
			}
			var bool = confirm("是否确认？");
			$.ajax({
				url : './user/changPrice',
				data : {
					id : orderid,
					point : logisticnum
				},
				success : function(code) {
					if (code == 0) {
						alert("修改失败");
						return;
					} else {
						alert("修改成功");
						loadHtml("./user/appUser");
					}
				}
			})
		}
		
		function cancel() {
			$('.tipsOt').hide();
		}
	</script>
</body>
</html>