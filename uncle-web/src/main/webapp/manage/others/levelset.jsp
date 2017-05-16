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
		<div class="row">
			
			<form id="manageForm" class="form-horizontal" role="form">
				<select  form="manageForm" name="client" id="qiandao_type" onchange="changeClient()">
				 <option value="user" selected>用户端</option>
  				 <option value="aunt">阿姨端</option>
			</select>
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
	</div>
	<!-- /.row -->
	<script type="text/javascript">
	
	function changeClient(){
		var obj = document.getElementById("qiandao_type");
        var grade = obj.options[obj.selectedIndex].value;
        var dayCount = $('#daycount').val();
        var point = $('#point').val();
        $("#grid-table").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:{'client':grade}, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}
	
	$(document).ready(function () {
		var rowDataNames = [];
        $("#grid-table").jqGrid({
            url: './others/getLevelSet',
            datatype: "json",
            page:'1',
            colModel: [
				{ label: 'id', name: 'id', width: 50 },
                { label: '等级', name: 'level', width: 50},
                { label: '所需积分', name: 'point', width: 50},
                { label: '等级称号', name: 'title', width: 50},
                { label: '操作', name: '', width: 50 ,formatter:changestatus},
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
//							styleCheckbox(table);
//							updateActionIcons(table);
						updatePagerIcons(table);
//							enableTooltips(table);
					}, 0);
				},
        });
        
        $('[data-rel=tooltip]').tooltip({container:'body'});
        
        initButton([
// 		{
// 				name: "查询", 
// 				click: function(){
// 					searchFrom('./others/getSignSet?');
// 				}
// 			},
// 			{
// 		        name: "新增", 
// 		        click: function(){
// 		        	var obj = document.getElementById("qiandao_type");
// 		            var client = obj.options[obj.selectedIndex].value; 
// 		      		if(client=="user"){
// 		      			loadHtml("./others/addSignSet");
// 		      		}else{
// 		      			loadHtml("./others/addAuntSignSet");
// 		      		}
		        	
// 		        }
// 			} 
 			])
});
 function replaceSpace(obj){
        obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
 }
 
 function changestatus(cellvalue, options, rowObject){	
 		var html = '';
 		html += '<button style="margin-left:10px;" class="btn btn-xs btn-primary" onclick="edit(\''+rowObject.id +'\')">编辑</button>';
			return html;
 }
 function edit(id){
	 var obj = document.getElementById("qiandao_type");
     var client = obj.options[obj.selectedIndex].value; 
		if(client=="user"){
			 loadHtml("./others/editlevel?client=user&id="+id);
		}else{
			 loadHtml("./others/editlevel?client=aunt&id="+id);
		}
	  
 }
 function del(id){
	 if(!confirm("确定删除吗")){
		 return;
	 }
	 var obj = document.getElementById("qiandao_type");
     var client = obj.options[obj.selectedIndex].value; 
	 $.ajax({
		 url:'./others/delSignSet',
		 data:{'id':id,'client':client},
		 type:'post',
		 success:function(msg){
			 if(msg != null){
					if(msg.code == 1){
						alert(msg.m);
						return;
					}
					alert(msg.m);
					loadHtml("./others/qiandao");
				}
		 }
	 });
 }
 
	</script>

</body>
</html>