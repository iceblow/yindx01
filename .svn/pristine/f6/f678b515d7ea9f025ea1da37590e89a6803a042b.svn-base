<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<div class="row">
<form class="form-horizontal" role="form"></form>
	
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
	</div><!-- /.col -->
</div><!-- /.row -->

<div id="dialog" title="Dialog Title">
</div>
		<script type="text/javascript">
		$(document).ready(function () {
	    		
	    		var rowDataNames = [];
	    		
	            $("#grid-table").jqGrid({
	                url: './aunt/cashlistJson',
	                datatype: "json",
	                page:'${page}',
	                colModel: [
						{ label: '用户名称', name: '',width: 50,formatter:username},
						{ label: '用户类型', name: '', width: 50,formatter:usertype},
						{ label: '提现账号', name: 'account', width: 50},
						{ label: '提现姓名', name: 'name', width: 50},
						{ label: '提现金额', name: 'money', width: 50},
						{ label: '状态', name: '', width: 50,formatter:state},
						{ label: '申请时间',name:'addtime', width: 50 , formatter:gettime },
	                    { label: '操作', width: 50 , formatter:changestatus}
	                ],
	                multiselect: false,
	                multiboxonly: false,
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
						setTimeout(function() {
							updatePagerIcons(table);
						}, 0);
						}
	            });
	            
	            
		});
		
		$('[data-rel=tooltip]').tooltip({container:'body'});
        
		initButton([{
	        name: "查询", //这里是静态页的地址
	        click: function(){
	        	var pro = $("#proselect").val();
	        	var city = $("#cityselect").val();
	        	if(city != "" && pro == ""){
	        		alert("请选择省市信息");
	        		return;
	        	}else if(pro != "" && city == ""){
	        		alert("请选择省市信息");
	        		return;
	        	}
	        	searchFrom('./active/selectactivelist?');
	        }
    	}])
		
    	 //加载图片
        function username(cellvalue, options, rowObject){
        	var html = '';
        	if(rowObject.userType== 0){
  				html += rowObject.auntname ;        		
        	}else{
        		html += rowObject.companyname ;     
        	}
   			return html;
        }
		
		function gettime(cellvalue, options, rowObject){
			
			return FormatDate(new Date(rowObject.addtime));
		}
    	
	  function usertype(cellvalue, options, rowObject){
        	var html = '';
        	if(rowObject.userType== 0){
  				html += '阿姨';		
        	}else{
        		html += '公司';
        	}
   			return html;
        }
	  
	  	function state(cellvalue, options, rowObject){
	  		var html = '';
        	if(rowObject.state == 0){
  				html += '待审核';		
        	}else if(rowObject.state == 1){
        		html += '通过';
        	}else if(rowObject.state == 2){
        		html += '拒绝';
        	}else{
        		html += '转账中';
        	}
   			return html;
	  	}
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		if(rowObject.state == 0){
     			html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="pass(\''+rowObject.cashid +'\')">通过</button>';
           		html += '<button Style="margin-left: 10px;" class="btn btn-xs btn-primary" onclick="refuse(\''+rowObject.cashid +'\')">拒绝</button>';    			
        	}
   			return html;
     	}
        
		function pass(id){
			$.ajax({
				url:'./aunt/passCash',
				type:'post',
				data:{id:id},
				dataType:'json',
				success:function(data){
					var code = data.c;
					alert(data.m);
					if(code==1){
						$("#grid-table").trigger("reloadGrid")
					}
				}
			})
		}
		
		function refuse(id){
			$.ajax({
				url:'./aunt/refuseCash',
				type:'post',
				data:{id:id},
				dataType:'json',
				success:function(data){
					var code = data.c;
					alert(data.m);
					if(code==1){
						$("#grid-table").trigger("reloadGrid")
					}
				}
			})
		}
		
        function dialog(id,type){
        	$('#dialog').attr("dialogUrl","./home/loaddetail?id="+id+"&type="+type);
        	$( "#dialog" ).dialog( "open" );
        }
</script>
</body>
</html>