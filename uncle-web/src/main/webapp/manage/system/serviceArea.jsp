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
	                url: './system/getServiceArea',
	                datatype: "json",
	                page:'${page}',
	                colModel: [
						{ label: '城市', name: 'city', width: 50 },
						{ label: '区域', name: 'area', width: 50 },
						{ label: '二级分类', name: 'name', width: 50 },
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
	        name: "新增", //这里是静态页的地址
	        click: function(){
	        	$.ajax({
	        		url:"./system/getCategorySecond",
	        		type:"post",
	        		data:{},
	        		success:function(msg){
	        			$('#second').empty();
	        			console.log(msg);
	        			var html = '';
	        			for(var i = 0; i < msg.list.length;i++){
	        				html += '<option value="'+msg.list[i].dataid+'" onclick="cityselect('+msg.list[i].dataid+')">'+msg.list[i].name+'</option>';
	        			}
	        			$('#second').append(html);
	        		}
	        	})
	        	$(".add").dialog("open");
	        }
		}])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		console.log(rowObject);
   			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" '
   			html += ' onclick="del(\''+rowObject.dataid +'\')">删除</button>';
   			return html;
     	}
 		  function del(id){
 	        	$.ajax({
 	        		url:"./system/delArea",
 	        		type:"post",
 	        		data:{id:id},
 	        		success:function(msg){
 	        			if(msg.code == 0){
 	        				alert("删除成功");
 	        			}else{
 	        				alert("删除失败");
 	        			}
 	        		}
 	        	})
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
        function provienceselect1(){
        	var val = $('#provience1').val();
        	$.ajax({
        		url:"./system/selectCity",
        		type:"post",
        		data:{id:val},
        		success:function(msg){
        			$('#city1').empty();
        			console.log(msg);
        			var html = '';
        			for(var i = 0; i < msg.list.length;i++){
        				html += '<option value="'+msg.list[i].id+'" onclick="cityselect1('+msg.list[i].id+')">'+msg.list[i].name+'</option>';
        			}
        			$('#city1').append(html);
        			$('#city1').append(html);
        		}
        	})
		}
        function cityselect1(){
        	var val = $('#city1').val();
        	$.ajax({
        		url:"./system/selectArea",
        		type:"post",
        		data:{id:val},
        		success:function(msg){
        			$('#area1').empty();
        			console.log(msg);
        			var html = '';
        			for(var i = 0; i < msg.list.length;i++){
        				html += '<option value="'+msg.list[i].id+'" onclick="areaselect('+msg.list[i].id+')">'+msg.list[i].name+'</option>';
        			}
        			$('#area1').append(html);
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
		        	var city = $('#city1').find("option:selected").text();
		        	var area = $('#area1').find("option:selected").text();
		        	var provience = $('#provience1').find("option:selected").text();s
		        	var second = $('#second').val();
		          	$.ajax({
		        		url:"./system/addArea",
		        		type:"post",
		        		data:{city:city,area:area,provience:provience,second:second},
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
	<div class="add" style="display:none;">
	   <div class="col-xs-3" style="width: 50%">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">省份</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="provienceselect1()"
							name="provience" title="省份" data-placement="bottom" id="provience1">
							<c:forEach items="${city}" var="i">
								<option value="${i.id }">${i.name}</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold;margin-top: 10px;">城市</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="cityselect1()"
							name="city" title="城市" data-placement="bottom" id="city1" style="margin-top: 10px;">
						</select>
					</div>
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold;margin-top: 10px;">区域</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" "
							name="area" title="区域" data-placement="bottom" id="area1" style="margin-top: 10px;">
						</select>
					</div>
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold;margin-top: 10px;">省份</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey"
							name="second" title="省份" data-placement="bottom" id="second" style="margin-top: 10px;">
						</select>
					</div>
				</div>
			</div>
	</div>
</body>
</html>