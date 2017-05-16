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
							<label for="addGrade" class="col-xs-4 control-label">身份证</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="idcardNum" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">籍贯</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="originPlace" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">从业资历(年)</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="workYear" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">家庭住址</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="homeAddress" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">现居地址</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="nowAddress" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">民族</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="nation" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">体重(KG)</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="weight" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">身高(CM)</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="height" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">血型</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="bloodType" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">语言能力</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="language" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">性格</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="character" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">文化程度</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="culture" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">宗教信仰</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="religion" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">政治面貌
							</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="political " value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">婚姻状态</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="marriage" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">工作经历</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="workHis" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">自我评价</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="selfComment" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">爱好</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="hobby" value="" readonly="readonly">
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
							<label for="addGrade" class="col-xs-4 control-label">阿姨状态</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="state" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">星座</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="constellation" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">生肖</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="zodiac" value="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class=" col-sm-6">
						<div class=" form-xs form-group row">
							<label for="addGrade" class="col-xs-4 control-label">是否是王牌阿姨</label>
							<div class="col-xs-8">
								<input type="text " class="form-control input-xs maskId"
									id="kingState" value="" readonly="readonly">
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
		</div>

		</form>
	</div>
	<form id="manageForm" class="form-horizontal" role="form">
		<div class="col-xs-4">
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
	<script type="text/javascript">
		$(document).ready(
				function() {

					var rowDataNames = [];

					$("#grid-table").jqGrid(
							{
								url : './user/getAuntUser',
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
							searchFrom('./user/getAuntUser?');
						}
					} ])
				});

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
			var state = rowObject.state;
			var stateStr = '';
			if (state == 0) {
				stateStr = "审核通过";
			}
			if (state == 1) {
				stateStr = "冻结";
			}
			if (state == 2) {
				stateStr = "解除冻结";
			}
			var html = '';
			html += '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="detail(\''
					+ rowObject.auntid + '\')">查看详情</button>';
			html += '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="changeState(\''
					+ rowObject.auntid
					+ '\',\''
					+ state
					+ '\',\''
					+ stateStr
					+ '\')">' + stateStr + '</button>';
			if (state == 0) {
				html += '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="del(\''
						+ rowObject.auntid + '\')">删除</button>';
			}
			return html;
		}
		function changeState(id, state, stateStr) {
			if (!confirm("确定" + stateStr + "吗")) {
				return;
			}
			$.ajax({
				url : './user/updateAuntUserState?',
				data : {
					auntid : id,
					state : state
				},
				success : function() {
					alert("修改成功");
					window.location.reload();
				}
			})
		}

		function detail(id) {
			$("#replyModalTO").dialog("open");
			$.ajax({
				url : './user/getAuntUserByid',
				data : {
					id : id
				},
				success : function(msg) {
					if (msg != null) {
						console.log(msg);
						$('#realName').val(msg.realName);
						$('#phone').val(msg.phone);
						$('#sex').val(msg.sex);
						$('#birthday').val(msg.birthdays);
						$('#level').val(msg.level);
						$('#signature').val(msg.signature);
						$('#idcardNum').val(msg.idcardNum);
						$('#originPlace').val(msg.originPlace);
						$('#workYear').val(msg.workYear);
						$('#homeAddress').val(msg.homeAddress);
						$('#nowAddress').val(msg.nowAddress);
						$('#nation').val(msg.nation);
						$('#weight').val(msg.weight);
						$('#height').val(msg.height);
						$('#bloodType').val(msg.bloodType);
						$('#language').val(msg.language);
						$('#character').val(msg.character);
						$('#culture').val(msg.culture);
						$('#religion').val(msg.religion);
						$('#political ').val(msg.political);
						$('#marriage').val(msg.marriage);
						$('#workHis').val(msg.workHis);
						$('#selfComment').val(msg.selfComment);
						$('#hobby').val(msg.hobby);
						$('#state').val(msg.states);
						$('#constellation').val(msg.constellation);
						$('#zodiac').val(msg.zodiac);
						$('#kingState').val(msg.kingStates);
						$('#addtime').val(msg.addtimes);
					}
				}
			})
		}

		function del(id) {
			var bool = confirm("是否确认删除？");
			if (bool == true) {
				$.ajax({
					url : "./user/delAunt",
					type : "post",
					data : {
						id : id
					},
					success : function(code) {
						if (code == 0) {
							alert("删除失败");
							return;
						} else {
							alert("删除成功");
							loadHtml("./user/auntUser");
						}
					}
				});
			}
			return;
		}
	</script>


</body>
</html>