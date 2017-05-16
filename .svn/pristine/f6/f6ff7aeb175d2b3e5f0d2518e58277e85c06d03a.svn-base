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
	<form id="riderForm" class="form-horizontal" role="form">
		<input id="id" name="id" type="hidden" value="${set.id }">
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">连续签到天数</label>
				<div class="col-sm-3">
					<input id="daycount" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="${set.daycount }" onblur="replaceSpace(this)" name="daycount" data-placement="bottom">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">赠送积分数量</label>
				<div class="col-sm-3">
					<input id="point" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="${set.point }" onblur="replaceSpace(this)" name="point" data-placement="bottom">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">客户端</label>
				<div class="col-sm-3">
					<input id="client"  data-rel="tooltip" type="text" class="col-sm-10 searchKey" onblur="replaceSpace(this)"  name="client"  data-id="${set.id }" data-client = "${client }" <c:if test="${client=='user' }">value="用户端"</c:if>  <c:if test="${client=='aunt' }">value="阿姨端"</c:if> readonly data-placement="bottom">
				</div>
			</div>
		</div>
	</form>
				<input type="hidden" id="picCheck" value="0">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div><!-- /.col -->
</div>
	
	<!-- /.row -->
	<script type="text/javascript">
		$(document).ready(function () {
    		var rowDataNames = [];
            $('[data-rel=tooltip]').tooltip({container:'body'});
            initButton([{
 		        name: "返回", //这里是静态页的地址
 		        click: function(){
 		        	loadHtml('./others/qiandao');
 		        }
 			},
 			{
 		        name: "确定编辑", //这里是静态页的地址
 		        click: function(){
 		        	submitaction();
 		        }
 			}])
		});
		
		function submitaction(){
			if(!$('#daycount').val()){
	     		alert('请输入连续签到天数');
	     		return;
	     	}
			if(!$('#point').val()){
	     		alert('请输入赠送积分数量');
	     		return;
	     	}
			var client = $('#client').data('client');//将中文转换成英文
			//$('#client').val(client);
			var params = {};
			params.daycount = $('#daycount').val();
			params.point = $('#point').val();
			params.client =client;
			params.id = $('#id').val();
			$.ajax({
				url:'./others/doEditSignSet',
				data:params,
				type:'post',
				success:function(msg){
					if(msg != null){
						if(msg.c == 0){
							alert(msg.m);
							return;
						}
						alert(msg.m);
						loadHtml("./others/qiandao");
					}
				}		
			})
		}
		
		
		 function replaceSpace(obj){
		        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		   }
		</script>

</body>
</html>