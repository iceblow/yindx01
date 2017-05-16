<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
.statistic{
	height: 150px;
	border-radius: 3px;
	text-align:center;
}
.number{
	color:#fff;
	font-size:40px;
	margin-top: 30px;
}
.item{
	color:#fff;
	font-size: 15px;
	margin-top:10px;
}
</style>
<body>
	<div class="row">
		<form id="manageForm" class="form-horizontal" role="form">
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">开始时间</label>
					<div class="col-sm-8">
						<input data-rel="tooltip" type="date" class="col-sm-10 searchKey" id="startDate"
							onBlur="replaceSpace(this)" name="startDate" title="商品名称"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">结束时间</label>
					<div class="col-sm-8">
						<input data-rel="tooltip" type="date" class="col-sm-10 searchKey" id="endDate"
							onBlur="replaceSpace(this)" name="endDate" title="商品名称"
							data-placement="bottom">
					</div>
				</div>
			</div>
			<input class="btn btn-xs btn-primary" type="button" value="查询" onclick="search()">
		</form>
		
		
		<div class="col-xs-12">
			<div class="col-xs-3">
				<div class="col-xs-12 statistic" style="background-color: rgb(67,142,185);">
					<label class="number">${times }</label>
					<br>
					<span class="item">接单数</span>
				</div>
				<div class="col-xs-12 statistic" style="background-color: rgb(52,188,167); margin-top: 20px;">
					<label class="number">${hours }小时</label>
					<br>
					<span class="item">钟点工</span>
				</div>
				<div class="col-xs-12 statistic" style="background-color: rgb(160,214,190);">
					<label class="number">${days }天</label>
					<br>
					<span class="item">长期工</span>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="col-xs-12 statistic" style="background-color: rgb(160,194,214); border-bottom: 1px solid rgb(138,172,199)">
					<label class="number">${balance }</label>
					<br>
					<span class="item">当前余额（元）</span>
				</div>
				<div class="col-xs-12 statistic" style="background-color: rgb(160,194,214); border-top: 1px solid rgb(138,172,199)">
					<label class="number">${use_total }</label>
					<br>
					<span class="item">总金额（元）</span>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="col-xs-12 statistic" style="background-color: rgb(206,178,80);">
					<label class="number">${totalprice }</label>
					<br>
					<span class="item">流入余额（元）</span>
				</div>
				<div class="col-xs-12 statistic" style="background-color: rgb(185,84,67);">
					<label class="number">${totalcash }</label>
					<br>
					<span class="item">流出余额（元）</span>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="col-xs-12 statistic" style="background-color: rgb(98,184,233);">
					<label class="number">${times }</label>
					<br>
					<span class="item">服务人次（次）</span>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</body>
<script type="text/javascript">
function search(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	
	loadHtml("./statistic/statistic?startDate=" + startDate +"&endDate=" + endDate);
}
</script>
</html>