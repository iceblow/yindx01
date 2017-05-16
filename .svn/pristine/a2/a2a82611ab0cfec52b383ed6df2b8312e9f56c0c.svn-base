<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div class="row">
	<!-- 放选择的city -->
	<input type="hidden" id="cityHidden" value="">
	<!-- 放bannerType -->
	<input type="hidden" id="hidden" value="">
	<form id = "manageForm" class="form-horizontal" role="form" >
		<div class="col-xs-3">
	        <div class="form-group">
			<label class="col-sm-4 control-label no-padding-right" for="form-field-6" style="font-weight:bold">城市选择</label>
				<div class="col-sm-8">
					<select id="selectCity" name="city" class="col-sm-10 searchKey" >
						<c:forEach items="${list}" var="city">
							<option value="${city.id }">${city.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div> 
</form>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
		</div><!-- /.col -->
</div><!-- /.row -->
		<script type="text/javascript">
		$(document).ready(function () {
	    		var rowDataNames = [];
	            $("#grid-table").jqGrid({
	                url: './home/getBanner',
	                datatype: "json",
	                page:'${page}',
	                colModel: [
						{ label: '图片',  width: 50 ,formatter:loadpicture },
	                    { label: '跳转类型',name: 'bannerTypeName', width: 50},
	                    { label: '所属城市', name: 'city', width: 50 },
	                    { label: '操作',  width: 50 ,formatter:changestatus},
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
// 								styleCheckbox(table);
// 								updateActionIcons(table);
								updatePagerIcons(table);
// 								enableTooltips(table);
							}, 0);
						},
	            });
	            
	            $('[data-rel=tooltip]').tooltip({container:'body'});
	            
	            initButton([
	            {
	        	 	name: "查询", 
	        	 	click: function(){
	        	 		searchFrom('./home/getBanner?');
	        	 	}
	        	 },
	 			{
	 		        name: "新增", 
	 		        click: function(){
	 		        	loadHtml("./home/goaddHomeAd");
	 		        }
	 			}
	 			])
		});
		 function replaceSpace(obj){
		        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		 }
		 
		 function changestatus(cellvalue, options, rowObject){	
	     		var html = '';
	     		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="upsort(\''+rowObject.dataid +'\')">上升</button>';
			    html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="downsort(\''+rowObject.dataid +'\')">下降</button>';
	     		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="del(\''+rowObject.dataid +'\')">删除</button>';
	   			return html;
	     }
	     function edit(id){
	    	   loadHtml("./bannerManage/editBanner?id="+id+"&bannerType="+$("#hidden").val());
	     }
	     function del(id){
	    	 if(!confirm("确定删除吗")){
	    		 return;
	    	 }
	    	
	    	 $.ajax({
				 url:"./home/delBanner",
	    		 data:{dataid:id},
	    		 success:function(code){
	        			if(code == 0){
	        				alert("删除失败");
	        				return;
	        			}else{
	        				alert("删除成功");
	        				loadHtml("./home/bannerManage");
	        			}
	        		}
	    	 });
	     }
		
		  //加载图片
	        function loadpicture(cellvalue, options, rowObject){
	        	var html = '<img src=\''+rowObject.picurl +'\' style="height: 100px; width: 100px;">' ;	
	   			return html;
	        }
		
		 
		 function upsort(id){
			 $.ajax({
	        		url:"./home/upBannersort",
	        		type:"post",
	        		data:{dataid:id},
	        		success:function(code){
	        			if(code == 1){
	        				alert("上升成功");
	        				jQuery("#grid-table").trigger('reloadGrid');
	        			}else if(code == 0){
	        				alert("上升失败");
	        				return;
	        			}else if(code == 102){
	        				alert("已经是第一位了")
	        				return;
	        			}
	        			
	        		}
	        	})
		 }
		 
		
 		function downsort(id){
 			$.ajax({
        		url:"./home/downBannersort",
        		type:"post",
        		data:{dataid:id},
        		success:function(code){
        			if(code == 1){
        				alert("下降成功");
        				jQuery("#grid-table").trigger('reloadGrid');
        			}else if(code == 0){
        				alert("下降失败");
        				return;
        			}else if(code == 102){
        				alert("已经是第一位了")
        				return;
        			}
        		}
        	})
		 }
		</script>
</body>
</html>