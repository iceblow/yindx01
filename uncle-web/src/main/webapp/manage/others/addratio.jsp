<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../manage/js/qiniu.min.js"></script>
<script type="text/javascript" src="../manage/js/plupload.full.min.js"></script>
</head>
<body>
<form class="form-horizontal" role="form"  id="versionForm">
<div class="row">
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">服务者类型</label>
				<div class="col-sm-2">
					<select  data-rel="tooltip"  type="text" class="col-sm-10 searchKey" id="server_type" name="server_type" title="服务者类型" data-placement="bottom" onchange="changeload()">
						<option value="">请选择</option>
						<option value="1">个体阿姨</option>
						<option value="2">公司</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">城市</label>
				<div class="col-sm-2">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="city" name="city" title="城市" data-placement="bottom" >
						<option value="">请选择</option>
						<c:forEach var="c" items="${cities }">
						<option value="${c.name }">${c.name }</option>
						</c:forEach>	
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">服务类型</label>
				<div class="col-sm-2">
				<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="categoryid" name="categoryid" title="服务类型" data-placement="bottom" >
						<option value="">请选择</option>
						<c:forEach var="c" items="${category }">
						<option value="${c.dataid }">${c.name }</option>
						</c:forEach>	
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">抽成方式</label>
				<div class="col-sm-2">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="ratio_type" name="ratio_type" title="抽成方式" data-placement="bottom" >
						<option value="">请选择</option>
						<option value="1">全额</option>
						<option value="2">仅定金</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12" >
	        <div class="form-group">
				<label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-6">佣金比例</label>
				<div class="col-sm-2">
					<input data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="ratio" name="ratio" title="佣金比例" data-placement="bottom" >
				</div>
			</div>
		</div>
		<div class="col-xs-3" style="clear:both;float: initial;margin-top: 70px;">
	        <div class="form-group">
				<button class="btn btn-info" type="button" style="margin-left: 30%;" onclick="submitaction()">
					<i class="icon-ok bigger-110"></i>
					提交
				</button>
				<button class="btn" type="button" style="margin-left: 20%;" onclick="goback()">
					<i class="icon-undo bigger-110"></i>
					返回
				</button>
			</div>
		</div>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div><!-- /.col -->
</div><!-- /.row -->
</form>
<script type="text/javascript">


function submitaction(){
	var conform = confirm("确认新增?")
	if(conform == false){
		return;
	}
	var server_type = $('#server_type').val();
	if(server_type==''||server_type==null){
		alert('请选择服务者类型');
		return;
	}
	var city = $('#city').val();
	if(city==''||city==null){
		alert('请选择城市');
		return;
	}
	var categoryid = $('#categoryid').val();
	if(categoryid==''||categoryid==null){
		alert('请选择服务范围');
		return;
	}
	var ratio_type = $('#ratio_type').val();
	if(ratio_type==''||ratio_type==null){
		alert('抽成方式');
		return;
	}
	var ratio = $('#ratio').val();
	if(ratio==''||ratio==null){
		alert('请输入佣金比例');
		return;
	}
	var params = {};
	params.server_type  = server_type;
	params.city = city;
	params.categoryid = categoryid;
	params.ratio_type = ratio_type;
	params.ratio = ratio;
	$.ajax({
		url: './others/saveRatio',
		data: params,
		type: 'POST',
		success: function(msg){
			if (msg.c == 1) {
				alert(msg.m);
				loadHtml("./others/ratio");
			} else {
				alert(msg.m);
			}
		}
	})
}

function goback(){
	loadHtml("./others/ratio");
}

</script>
</body>
</html>