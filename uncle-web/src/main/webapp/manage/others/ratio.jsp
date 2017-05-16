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
			
		<form id="manageForm" class="form-horizontal" role="form">
		<div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">服务者类型</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="server_type" title="服务者类型" data-placement="bottom">
						<option value="">全部</option>
						<option value="1">个体阿姨</option>
						<option value="2">公司</option>						
					</select>				
				</div>
			</div>
		</div>
		
		<div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">服务内容</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="categoryid" title="服务内容" data-placement="bottom">
						<option value="">全部</option>
						<c:forEach var="c" items="${category }">
						<option value="${c.dataid }">${c.name }</option>
						</c:forEach>					
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-2">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">省份</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="provienceselect()"
							name="provience" title="省份" data-placement="bottom" id="provience">
							
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-2">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">城市</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="cityselect()"
							name="city" title="城市" data-placement="bottom" id="city">
							<option value="">全部</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-2">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">区域</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="areaselect()"
							name="area" title="区域" data-placement="bottom" id="area">
							<option value="">全部</option>
						</select>
					</div>
				</div>
			</div>
		<%-- <div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">城市</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="city" title="城市" data-placement="bottom">
						<option value="">全部</option>
						<c:forEach var="c" items="${cities }">
						<option value="${c.name }">${c.name }</option>
						</c:forEach>					
					</select>
				</div>
			</div>
		</div> --%>
		
		
	</form>
			
			<div class="col-xs-12">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div>
			</div>
			<!-- /.col -->
		</div>
	<!-- /.row -->
	<script type="text/javascript">
		$(document).ready(function () {
	    		
	    		var rowDataNames = [];
	    		
	            $("#grid-table").jqGrid({
	                url: './others/getRatio',
	                datatype: "json",
	                page:'1',
	                colModel: [
						{ label: '服务者类型', name: 'strServerType', width: 50 },
						{ label: '区', name: 'city', width: 50},
	                    { label: '服务内容', name:'strCategoryid' ,width: 50},
	                    
	                    { label: '佣金比例', name: 'ratio', width: 50 , formatter:strRatio},
	                    { label: '操作', width: 50 , formatter:changestatus}
	                ],
	                multiselect: true,
	                multiboxonly: true,
	                onSelectRow: function (rowId, status, e) {
	                	rowDataNames = [];
	                    var rowIds = jQuery("#jqGrid").jqGrid('getGridParam', 'selarrrow');
	                    $(rowIds).each(function(i,rowId){
	                        rowDataNames.push($("#jqGrid").jqGrid('getRowData',rowId))
	                    });
	                },
	                viewrecords: true, // show the current page, data rang and total records on the toolbar
					rowList:[10,20,30],
	                autowidth:true,
	                height: $(window).height()-300,
	                rowNum: 10,
	                pager: "#grid-pager",
	            	jsonReader: { root: 'rows', repeatitems: false ,page: "page",   // json中代表当前页码的数据  
	            	    records: "records", // json中代表数据行总数的数据 
	            	    total: "total"},
	            	loadComplete : function() {
							var table = this;
							setTimeout(function(){
								updatePagerIcons(table);
							}, 0);
						}
	            });
		});
		
		$('[data-rel=tooltip]').tooltip({container:'body'});
        
        initButton([{
		        name: "查询", //这里是静态页的地址
		        click: function(){
		        	searchFrom('./others/getRatio?');
		        }
        	}])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="setRatio(\''+rowObject.city +'\',\''+rowObject.categoryid +'\',\''+rowObject.serverType +'\')">设置</button>';
       		/* html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.ratioid +'\')">删除</button>'; */    			
   			return html;
     	}
        
        function setRatio(city,categoryid,serverType){
        	var c = prompt('请输入比例');
        	if(c != null){
        		$.ajax({
            		url:"./others/setRatio",
            		type:"post",
            		data:{'city':city,'categoryid':categoryid,'serverType':serverType,'ratio':c},
            		success:function(result){
            			if(result.c == 1){
            				alert("设置成功")
            				loadHtml("./others/ratio");		
            			}else{
            				alert("设置失败,已存在")
            				return;
            			}
            		}
            	});
        	}
        	return;
        }
        
        function strRatio(cellvalue, options, rowObject){
        	var html = '';
        	if(rowObject.ratio == null ){
        		html += '未设置';
        	}else{
        		html += rowObject.ratio;
        	}
        	return html;
        }
        
        function provienceselect(){
        	var val = $('#provience').val();
        	$.ajax({
        		url:"./system/selectCity",
        		type:"post",
        		data:{id:val},
        		success:function(msg){
        			$('#city').empty();
        			console.log(msg);
        			var html = '';
        			for(var i = 0; i < msg.list.length;i++){
        				html += '<option value="'+msg.list[i].id+'" onclick="cityselect('+msg.list[i].id+')">'+msg.list[i].name+'</option>';
        			}
        			$('#city').append(html);
        		}
        	})
		}
        function cityselect(){
        	var val = $('#city').val();
        	$.ajax({
        		url:"./system/selectArea",
        		type:"post",
        		data:{id:val},
        		success:function(msg){
        			$('#area').empty();
        			console.log(msg);
        			var html = '';
        			for(var i = 0; i < msg.list.length;i++){
        				html += '<option value="'+msg.list[i].name+'" onclick="areaselect('+msg.list[i].id+')">'+msg.list[i].name+'</option>';
        			}
        			$('#area').append(html);
        		}
        	})
		}
        $(function(){
        	
            	
            	$.ajax({
            		url:"./index/selectProvience",
            		type:"post",
            		data:{},
            		success:function(msg){
            			$('#provience').empty();
            			console.log(msg);
            			var html = '';
            			for(var i = 0; i < msg.list.length;i++){
            				html += '<option value="'+msg.list[i].id+'" onclick="cityselect('+msg.list[i].id+')">'+msg.list[i].name+'</option>';
            			}
            			$('#provience').append(html);
            		}
            	})
    		
        });
        //编辑
        /* function editAction(id){
        	loadHtml("./others/editratio?id="+id);
        } */
        
        
        
        //删除
        /* function deleteAction(id){
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
	        	$.ajax({
	        		url:"./others/delRatio",
	        		type:"post",
	        		data:{'id':id},
	        		success:function(result){
	        			if(result.c == 1){
	        				alert("删除成功")
	        				loadHtml("./others/ratio");		
	        			}else{
	        				alert("删除失败")
	        				return;
	        			}
	        		}
	        	});
        	}
        	return;
        } */

		</script>

</body>
</html>