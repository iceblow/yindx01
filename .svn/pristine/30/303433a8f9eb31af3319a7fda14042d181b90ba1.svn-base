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
		<input id="id" name="id" type="hidden" value="${c.id }">
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">客户端</label>
				<div class="col-sm-3">
					<input id="client"  data-rel="tooltip" type="text" class="col-sm-10 searchKey" onblur="replaceSpace(this)"  name="client"  data-id="${c.id }" data-client = "${client }" <c:if test="${client=='user' }">value="用户端"</c:if>  <c:if test="${client=='aunt' }">value="阿姨端"</c:if> readonly data-placement="bottom">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">等级</label>
				<div class="col-sm-3">
					<input id="level" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="lv${c.level }" data-level="${c.level }" onblur="replaceSpace(this)" readonly name="level" data-placement="bottom">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">所需积分</label>
				<div class="col-sm-3">
					<input id="point" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="${c.point }" onblur="replaceSpace(this)" name="point" data-placement="bottom">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">等级称号</label>
				<div class="col-sm-3">
					<input id="title" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="${c.title }" onblur="replaceSpace(this)" name="title" data-placement="bottom">
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
 		        	loadHtml('./others/levelset');
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
			if(!$('#title').val()){
	     		alert('请输入等级称号');
	     		return;
	     	}
			if(!$('#point').val()){
	     		alert('请输入所需积分');
	     		return;
	     	}
			var client = $('#client').data('client');//将中文转换成英文
			//$('#client').val(client);
			var params = {};
			params.title = $('#title').val();
			params.point = $('#point').val();
			params.client =client;
			params.id = $('#id').val();
			params.level = $('#level').data('level');
			$.ajax({
				url:'./others/doEditlevel',
				data:params,
				type:'post',
				success:function(msg){
					if(msg != null){
						if(msg.c == 0){
							alert(msg.m);
							return;
						}
						alert(msg.m);
						loadHtml("./others/levelset");
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