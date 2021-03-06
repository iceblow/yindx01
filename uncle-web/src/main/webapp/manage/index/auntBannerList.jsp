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
		<div class="col-xs-3">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">类型</label>
				<div class="col-sm-8">
					<select name="type" id="type"  data-rel="tooltip" type="text" class="col-sm-10 searchKey" title="类型" data-placement="bottom">
						<option value="">请选择</option>
						<option value="0">外联</option>
						<option value="1">公司</option>
						<option value="2">阿姨</option>
						
					</select>
				</div>
			</div>
		</div>
<!-- 		<div class="col-xs-3"> -->
<!-- 	        <div class="form-group"> -->
<!-- 				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">类型</label> -->
<!-- 				<div class="col-sm-8"> -->
<!-- 					<select name="contentid" id="contentid"  data-rel="tooltip" type="text" class="col-sm-10 searchKey" title="类型" data-placement="bottom"> -->
<!-- 						<option value="">请选择</option> -->
<!-- 						<option value="0">外链的地址</option> -->
<!-- 						<option value="1">公司Id</option> -->
<!-- 						<option value="2">阿姨Id</option> -->
						
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</form>
		
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<!-- /.row -->
	<script type="text/javascript">
		$(document).ready(
				function() {
					var rowDataNames = [];
					$("#grid-table").jqGrid(
							{
								url : './index/getAuntBannerList',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '图片ID',
									name : 'picid',
									width : 170,
									formatter : picAjax
								}, {
									label : '类型',
									name : 'type',
									width : 60,
									formatter : typeDef
								}, {
									label : '内容',
									name : 'contentid',
									width : 60,
									formatter : contentFmt
								}, {
									label : '添加时间',
									name : 'addtime',
									width : 60,
									formatter : timeFmt
								}, {
									label : '操作',
									name : 'opreation',
									width : 100,
									formatter : changestatus
								} ],
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
								height : $(window).height() - 200,
								rowNum: 10,
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
										updatePagerIcons(table);
									}, 0);
								},
							});

					$('[data-rel=tooltip]').tooltip({
						container : 'body'
					});

					initButton([{
				        name: "查询", //这里是静态页的地址
				        click: function(){
				        	searchFrom('./index/getAuntBannerList?');
				        }
			    	},{
						name: "新增", //这里是静态页的地址
				        click: function(){
				        	loadHtml("./index/addauntbanner");
				        }	
					}])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function picAjax(cellvalue, options, rowObject) {
			var html = '';
			$.ajax({
				url: './index/getPath',
				data: {'picid':rowObject.picid},
				type: 'POST',
				async:false,
				success: function(msg){
					html =  "<img src='" + msg +"' style='height: 300px; width: 400px;'/>";
					//console.log(html);
					
				}
			});
			return html;
		}
		
		 function changestatus(cellvalue, options, rowObject){	
	     		var html = '';
	     		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="upsort(\''+rowObject.bannerid +'\')">上升</button>';
	     		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="downsort(\''+rowObject.bannerid +'\')">下降</button>';
	     		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="editAction(\''+rowObject.bannerid +'\')">编辑</button>';
	       		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.bannerid +'\')">删除</button>';    					
	   			return html;
	     	}
		
		 //编辑
	        function editAction(id,type){
	        	loadHtml("./index/editauntbanner?id="+id);
	        }
	        
	        
	        
	        //删除
	        function deleteAction(id){
	        	var bool = confirm("是否确认删除？");
	        	if(bool == true){
		        	$.ajax({
		        		url:"./index/delauntbanner",
		        		type:"post",
		        		data:{id:id},
		        		success:function(result){
		        			if(result.c == 0){
		        				alert("删除失败");
		        				return;
		        			}else{
		        				alert("删除成功");
		        				loadHtml("./index/auntBannerList");
		        			}
		        		}
		        	});
	        	}
	        	return;
	        }		
			
	      //排序上升
	        function upsort(id){
	    	 
	        	$.ajax({
	        		url:"./index/upauntbanner",
	        		type:"post",
	        		data:{id:id},
	        		success:function(result){
	        			if(result.c == 1){
	        				alert("上升成功");
	        				jQuery("#grid-table").trigger('reloadGrid');
	        			}else if(result.c == 0){
	        				alert("上升失败");
	        				return;
	        			}else if(result.c == 102){
	        				alert("已经是第一位了")
	        				return;
	        			}
	        			
	        		}
	        	})
	        }
	        
	        //排序下降
	        function downsort(id){	        
	        	$.ajax({
	        		url:"./index/downauntbanner",
	        		type:"post",
	        		data:{id:id},
	        		success:function(result){
	        			if(result.c == 1){
	        				alert("下降成功");
	        				jQuery("#grid-table").trigger('reloadGrid');
	        			}else if(result.c == 0){
	        				alert("下降失败");
	        				return;
	        			}else if(result.c == 102){
	        				alert("已经是最后一位了")
	        				return;
	        			}
	        		}
	        	})
	        }
	        
	        
		
		function getLocalTime(nS) {     
	       	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
	    }
		
		function timeFmt(cellvalue, options, rowObject) {
			var html = getLocalTime(rowObject.addtime / 1000);
			
			return html;
		}
		
		function typeDef(cellvalue, options, rowObject) {
			var html = '';
			switch (rowObject.type) {
			case 0:
				html = '外链';break;
			case 1:
				html = '公司';break;
			case 2:
				html = '阿姨';break;
			default:
				break;
			}
			
			return html;
		}
		
		function contentFmt(cellvalue, options, rowObject) {
			var html = '';
			switch (rowObject.contentid) {
			case "0":
				html = '外链的地址';break;
			case "1":
				html = '公司ID';break;
			case "2":
				html = '阿姨ID';break;
			default:
				break;
			}
			
			return html;
		}
		
		function delstate(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.delstate == 0) {
				html = '未删除';
			} else {
				html = '已删除';
			}
			return html;
		}
		
		function onlinestate(cellvalue, options, rowObject) {
			var html = '';
			if (rowObject.onlinestate == 0) {
				html = '不在线';
			} else {
				html = '在线';
			}
			return html;
		}
		
		
	</script>
	
</body>
</html>