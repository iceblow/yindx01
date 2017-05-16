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
		<form id="advicesForm" class="form-horizontal" role="form">
		
		</form>
		<select form="advicesForm" name="client" id="adviece_type" onchange="changeClient()">
			 <option value="user" selected>用户端</option>
  			 <option value="aunt">阿姨端</option>
		</select>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
	<script type="text/javascript">
	
		function changeClient(){
			var obj = document.getElementById("adviece_type");
	        var grade = obj.options[obj.selectedIndex].value;
	        console.log(grade);
	        $("#grid-table").jqGrid('setGridParam',{  
	            datatype:'json',  
	            postData:{'client':grade}, //发送数据  
	            page:1  
	        }).trigger("reloadGrid");
		}
		$(document).ready(
				function() {
					
					var rowDataNames = [];
					$("#grid-table").jqGrid(
							{
								url : './others/getAdvice',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '姓名',
									name : 'realName',
									width : 60
								}, {
									label : '用户类别',
									name : 'userType',
									width : 60
								}, {
									label : '反馈信息',
									name : 'content',
									width : 180
								}, {
									label : '反馈时间',
									name : 'addtime',
									width : 60
								},{
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

					initButton([ 
// 					 {
// 						name : "查询",
// 						click : function() {
// 							searchFrom('./others/getAdvice?');
// 						}
// 					} 
					])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}

		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';     		
       		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.id +'\')">删除</button>';    			
   			return html;
     	}
		
        function deleteAction(id){
        	var obj = document.getElementById("adviece_type");
	        var client = obj.options[obj.selectedIndex].value
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
	        	$.ajax({
	        		url:"./others/delAdvice",
	        		type:"post",
	        		data:{'id':id,'client':client},
	        		success:function(result){
	        			if(result.c == 1){
	        				alert("删除成功")
	        				loadHtml("./others/advices");		
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