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
			
			<form class="form-horizontal" role="form">
		<div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">平台类型</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="platformtype" title="平台类型" data-placement="bottom">
						<option value="">全部</option>
						<option value="0">安卓</option>
						<option value="1">IOS</option>						
					</select>				
				</div>
			</div>
		</div>
		
		<div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">APP类型</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="apptype" title="APP类型" data-placement="bottom">
						<option value="">全部</option>
						<option value="0">用户APP</option>
						<option value="1">阿姨APP</option>						
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">是否发布</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="poststate" title="是否发布" data-placement="bottom">
						<option value="">全部</option>
						<option value="0">未发布</option>
						<option value="1">已发布</option>						
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-2">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">版本类型</label>
				<div class="col-sm-8">
					<select data-rel="tooltip" type="text" class="col-sm-10 searchKey" name="versiontype" title="版本类型" data-placement="bottom">
						<option value="">全部</option>
						<option value="0">小版本</option>
						<option value="1">大版本</option>						
					</select>
				</div>
			</div>
		</div>
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
	                url: './others/getAllVersion',
	                datatype: "json",
	                page:'1',
	                colModel: [
						{ label: '版本号', name: 'versionstr', width: 50 },
						{ label: '平台类型', name: 'platformtypename', width: 50},
	                    { label: 'APP类型', name:'apptypename' ,width: 50},
	                    { label: '是否发布', name: 'poststatename', width: 50 },
	                    { label: '版本类型', name: 'versiontypename', width: 50 },
	                    { label: '发布时间',name: 'posttime', width: 50 },
	                    { label: '添加时间' ,name: 'addtime',width: 50},
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
		        	searchFrom('./others/getAllVersion?');
		        }
        	},{
				name: "新增", //这里是静态页的地址
 		        click: function(){
 		        	loadHtml("./others/addbanben");
 		        }		
			}])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		if(rowObject.poststate == 0){
     			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="editAction(\''+rowObject.versionid +'\')">发布</button>';
       			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.versionid +'\')">删除</button>';    			
     		}
     		if(rowObject.poststate == 1){
       			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.versionid +'\')">删除</button>';    			
     		}
   			return html;
     	}
        
        //编辑
        function editAction(versionid){
        	var bool = confirm("是否确认发布？");
        	if(bool == true){
	        	$.ajax({
	        		url:"./others/editVersion",
	        		type:"post",
	        		data:{'versionid':versionid},
	        		success:function(result){
	        			if(result.c == 1){
	        				alert("发布成功")
	        				loadHtml("./others/banben");
	        				
	        			}else{
	        				alert("发布失败")
	        				return;
	        			}
	        		}
	        	});
        	}
        	return;
        }
        
        
        
        //删除
        function deleteAction(versionid){
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
	        	$.ajax({
	        		url:"./others/delVersion",
	        		type:"post",
	        		data:{versionid:versionid},
	        		success:function(result){
	        			if(result.c == 1){
	        				alert("删除成功")
	        				loadHtml("./others/banben");		
	        			}else{
	        				alert("删除失败")
	        				return;
	        			}
	        		}
	        	});
        	}
        	return;
        }

		</script>

</body>
</html>