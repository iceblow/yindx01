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
<form id="manageForm" class="form-horizontal" role="form">
			<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">省份</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="provienceselect()"
							name="provience" title="省份" data-placement="bottom" id="provience">
							<c:forEach items="${city}" var="i">
								<option value="${i.id }">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">城市</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="cityselect()"
							name="city" title="城市" data-placement="bottom" id="city">
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">区域</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="areaselect()"
							name="area" title="区域" data-placement="bottom" id="area">
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
	                url: './system/getServicePrice',
	                datatype: "json",
	                page:'${page}',
	                colModel: [
						{ label: '分类名称', name: 'name', width: 50 },
						{ label: '价格', name: 'price', width: 50 },
						{ label: '订金', name: 'deposit_price', width: 50 },
						{ label: '试工价格', name: 'price_small', width: 50 },
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
        
        initButton([])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		console.log(rowObject);
   			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" '
   			html += 'onclick="update(\''+rowObject.categoryid +'\',\''+rowObject.third_categoryid +'\',\''+rowObject.price +'\',\''+rowObject.deposit_price +'\',\''+rowObject.price_small +'\')">编辑</button>';
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
        function areaselect(){
        	var val = $('#area').val();
        	$("#grid-table").jqGrid('setGridParam',{
                datatype:'json',  
                postData:{city:val}, //发送数据  
                page:1,
                datatype: "json"
            }).trigger("reloadGrid"); //重新载入  
		}
    function update(categoryid,third_categoryid,price,deposit_price,price_small){
    	$('#price').prop("readonly",false);
		 $('#price_small').prop("readonly",false);
		 $('#deposit_price').prop("readonly",false);
    	if(categoryid != null && categoryid != '' && categoryid != undefined && categoryid != 'undefined'){
    	 $('#categoryid').val(categoryid);
    	 if(categoryid == 1 || categoryid == 2 || categoryid == 5 || categoryid == 6 || categoryid == 7){
    		 $('#price_small').prop("readonly",true);
    	 }else if(categoryid == 10 || categoryid == 11 || categoryid == 12 || categoryid == 13 || categoryid == 4){
    		 $('#price').prop("readonly",true);
    		 $('#price_small').prop("readonly",true);
    	 }else if((categoryid == 3 || categoryid == 7 || categoryid == 9) && third_categoryid != 0){
    		 $('#price_small').prop("readonly",true);
    	 }
    	}
    	if(third_categoryid != null && third_categoryid != '' && third_categoryid != undefined && third_categoryid != 'undefined'){
    	 $('#third_categoryid').val(third_categoryid);
    	}
    	if(price != null && price != '' && price != undefined && price != 'undefined'){
    	 $('#price').val(price);
    	}else{
    		 $('#price').val("");
    	}
    	if(deposit_price != null && deposit_price != '' && deposit_price != undefined && deposit_price != 'undefined'){
    	 $('#deposit_price').val(deposit_price);
    	}else{
    		$('#deposit_price').val('');
    	}
    	if(price_small != null && price_small != '' && price_small != undefined && price_small != 'undefined'){
    	 $('#price_small').val(price_small);
    	}else{
    		 $('#price_small').val('');
    	}
    	$(".add").dialog("open");
    }
		//取消添加
		$( ".add" ).dialog({
		    autoOpen : false,
		    width : 540,
		    modal : true,
		    title : '',
		    buttons : {
		         "关闭" : function() {
		        	$(".add").dialog("close");
		        },
		        "确定" :function(){
		        	var categoryid = $('#categoryid').val();
		        	var third_categoryid = $('#third_categoryid').val();
		        	var price = $('#price').val();
		        	var deposit_price = $('#deposit_price').val();
		        	var price_small = $('#price_small').val();
		        	var city = $('#city').val();
		          	$.ajax({
		        		url:"./system/updatePrice",
		        		type:"post",
		        		data:{categoryid:categoryid,third_categoryid:third_categoryid,price:price,deposit_price:deposit_price,
		        			price_small:price_small,city:city},
		        		success:function(msg){
		        			if (msg.code == 0) {
		        				alert('添加成功');
		        				cityselect();
		        				$(".add").dialog("close");
		        			}else{
		        				alert('添加失败');
		        			}
		        		}
		        	})	
		        }
		    },
		    show: {
		        effect: "blind",
		        duration: 100
		    },
		    hide: {
		        effect: "blind",
		        duration: 50
		    }
		});
		</script>
	<div class="add" style="display: none;">
		<div class="col-xs-3" style="width: 50%">
		<input type="hidden" id="categoryid">
		<input type="hidden" id="third_categoryid">
			<label>单价：</label> <input type="text" id="price"  style="margin-top: 20px;" /> 
			<label>定金：</label> <input type="text" id="deposit_price"  style="margin-top: 20px;" />
			<label>试工：</label> <input type="text" id="price_small"  style="margin-top: 20px;" />
		</div>
	</div>
</body>
</html>