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
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">keyname</label>
				<div class="col-sm-3">
					<input id="keyname" readonly="true" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="${c.keyname }" onblur="replaceSpace(this)" name="keyname" data-placement="bottom">
				</div>
			</div>
		</div>
		<div class="col-xs-12">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">配置值</label>
				<div class="col-sm-3">
					<input id="configvalue" data-rel="tooltip" type="text" class="col-sm-10 searchKey" value="${c.configvalue }" onblur="replaceSpace(this)" name="configvalue" data-placement="bottom">
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
 		        	loadHtml('./others/baseconfig');
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
			if(!$('#configvalue').val()){
	     		alert('请输入配置值');
	     		return;
	     	}
			var params = {};
			params.id = $('#id').val();
			params.keyname = $('#keyname').val();
			params.configvalue = $('#configvalue').val();
			$.ajax({
				url:'./others/doEditConfig',
				data:params,
				type:'post',
				success:function(msg){
					if(msg != null){
						if(msg.c == 1){
							alert(msg.m);
							loadHtml("./others/baseconfig");
							
						}else{
							if(msg.m){
								alert(msg.m);
							}else{
								alert('修改失败');
							}
							return;
						}
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