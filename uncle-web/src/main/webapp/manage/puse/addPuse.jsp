<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
	<form id="riderForm" class="form-horizontal" role="form" action="">
		<div class="col-xs-12">
			<div class="form-group">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">消息类型</label>
				<div class="col-sm-2">
					<select id="typeSel" data-rel="tooltip" type="text"
						class="col-sm-10 searchKey" name="categoryid" title="服务分类"
						data-placement="bottom">
						<option value="">请选择分类</option>
						<option value="1">系统消息</option>
						<option value="2">广告</option>
						<option value="3">官方通知</option>
						<option value="4">活动通知</option>
					</select>
				</div>
			</div>
		</div>
		<div class=" col-xs-12">
			<div class="form-group ">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">消息标题</label>
				<div class="col-sm-2">
					<input type="text " class="form-control input-xs maskId"
						id="puseTitle">
				</div>
			</div>
		</div>
		<div class=" col-xs-12">
			<div class=" form-group">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">消息详情</label>
				<div class="col-sm-2">
					<input type="text " class="form-control input-xs maskId"
						id="puseDetail">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
			<div class="form-group">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">城市</label>
				<div class="col-sm-2">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey"
						id="city" name="city" title="城市" data-placement="bottom">
						<option value="">请选择</option>
						<c:forEach var="c" items="${cities }">
							<option value="${c.name }">${c.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="col-xs-12">
			<div class="form-group">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">连接类型</label>
				<div class="col-sm-2">
					<select id="linkType" data-rel="tooltip" type="text"
						class="col-sm-10 searchKey" name="categoryid" title="连接分类"
						data-placement="bottom">
						<option value="">请选择分类</option>
						<option value="0">个人资料完善资料</option>
						<option value="2">家政公司</option>
						<option value="3">阿姨</option>
						<option value="4">网页</option>
					</select>
				</div>
			</div>
		</div>

		<div class=" col-xs-12">
			<div class=" form-group">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">连接标题</label>
				<div class="col-sm-2">
					<input type="text " class="form-control input-xs maskId"
						id="puseLinkTitle">
				</div>
			</div>
		</div>
		<div class=" col-xs-12">
			<div class=" form-group">
				<label Style="text-align: center;"
					class="col-sm-1 control-label no-padding-right" for="form-field-6">连接内容</label>
				<div class="col-sm-2">
					<input type="text " class="form-control input-xs maskId"
						id="linkContent">
				</div>
			</div>
		</div>
		<div class="col-xs-3"
			style="clear: both; float: initial; margin-top: 70px;">
			<div class="form-group">
				<button class="btn btn-info" type="button" style="margin-left: 30%;"
					onclick="submitaction()">
					<i class="icon-ok bigger-110"></i> 确定
				</button>
				<button class="btn" type="button" style="margin-left: 20%;"
					onclick="goback()">
					<i class="icon-undo bigger-110"></i> 取消
				</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		function goback() {
			loadHtml("./puse/puseList");
		}

		function submitaction() {
			var conform = confirm("确认?")
			if (conform == false) {
				return;
			}

			var typeSel = $("#typeSel").val();
			if (typeSel == null || typeSel == "") {
				alert('请先选择消息类型');
				return;
			}

			var puseTitle = $("#puseTitle").val();
			if (puseTitle == null || puseTitle == "") {
				alert('请填写消息标题');
				return;
			}
			var puseDetail = $("#puseDetail").val();
			if (puseDetail == null || puseDetail == "") {
				alert('请填写消息详情');
				return;
			}
			$.ajax({
				url : './puse/insertPuse',
				data : {
					type : $("#typeSel").val(),
					title : $("#puseTitle").val(),
					detail : $("#puseDetail").val(),
					city : $("#city").val(),
					linkType : $("#linkType").val(),
					puseLinkTitle : $("#puseLinkTitle").val(),
					linkContent : $("#linkContent").val()
				},
				success : function(code) {
					if (code == 1) {
						alert("新增成功");
						loadHtml("./puse/puseList");
					} else {
						alert("新增失败");
					}
				}
			})
		}
	</script>
</body>
</html>