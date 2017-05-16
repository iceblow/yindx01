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
	                url: './system/getServiceCategory',
	                datatype: "json",
	                page:'${page}',
	                colModel: [
						{ label: '分类名称', name: 'name', width: 50 },
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
		        	$(".add").dialog("open");
		        }
			}])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		console.log(rowObject);
   			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="detail(\''+rowObject.dataid +'\')">编辑</button>';
   			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.dataid +'\')">删除</button>';
   			return html;
     	}
        
    function detail(id){
    	$('#dataid').val(id);
      	$.ajax({
    		url:"./system/getDetail",
    		type:"post",
    		data:{id:id},
    		success:function(msg){
    			if (msg.code == 0) {
    				$('#name').html(msg.name);
    				console.log(msg.category);
    				if(msg.category != null){
    					html = "";
    					for(var i=0;i < msg.category.length;i++){
    						html += '<div style="font-size: 17px;margin-left: 27px;">'+msg.category[i].name+'<button Style="margin-left: 100px;" class="btn btn-xs btn-primary" cat="'+msg.category[i].name+'" id="hah_'+i+'" ico="'+msg.category[i].icoString+'"onclick="updateCat('+msg.category[i].dataid+''+","+''+msg.category[i].needpic+''+","+''+i+')">编辑</button></div>';
    					}
    					$('#catow').empty();
    					$('#catow').append(html);
    					$(".detail_infor").dialog("open");
    				}
    			}
    		}
    	})	
    }
    
    function updateCat(id,is,index){
    	html = '';
    	var ico =  $('#hah_'+index).attr("ico");
    	var name =  $('#hah_'+index).attr("cat");
    	$('#updateid').val(id);
    	html += '<input id="name" type="text" value="'+name+'"/>';
    	if(is == 0){
    		html += '<select id="istrue" style="margin-top:20px;"><option value="0">需要</option><option value="1">不需要</option></select>';
    	}else{
    		html += '<select><option value="1">不需要</option><option value="0">需要</option></select>';
    	}
    	if(is == 0){
    		html += '<img src="'+ico+'">';
    	}
    	$('#updateCat').empty();
		$('#updateCat').append(html);
		$(".update").dialog("open");	
    }
        
        //删除
        function deleteAction(categoryid){
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
        		$.ajax({
            		url:"./system/deleteCategory",
            		type:"post",
            		data:{categoryid:categoryid},
            		success:function(result){
            			if(result==0){
            				alert("无法删除此分类,请先删除该分类下的所有商品")
            				return;
            			}
            			jQuery("#grid-table").trigger('reloadGrid');
            		}
            	});
        	}
        	return;	
        }
        
        //确认添加
        function submitaction(){
        	var name = $('#name').val();
        	$.ajax({
        		url:"./raiders/addcategory",
        		type:"post",
        		data:{name:name},
        		success:function(message){
        			if (message == "新增成功") {
        				alert(message);
        				$('.tips').hide();
        			} else {
        				alert(message);
        			}
        			jQuery("#grid-table").trigger('reloadGrid');
        		}
        	})
//         	$('#addcategory').submit();
        }
        
		//取消添加
		function goback(){
			$('.tips').hide();
		}
		
		$( ".detail_infor" ).dialog({
		    autoOpen : false,
		    width : 540,
		    modal : true,
		    title : '',
		    buttons : {
		         "关闭" : function() {
		        	$(".detail_infor").dialog("close");
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
		        	var categoryadd = $('input[name="categoryadd"]').val();
		          	$.ajax({
		        		url:"./system/addCategory",
		        		type:"post",
		        		data:{name:categoryadd},
		        		success:function(msg){
		        			if (msg.code == 0) {
		        				alert('添加成功');
		        				jQuery("#grid-table").trigger('reloadGrid');
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
		
		$( ".update" ).dialog({
		    autoOpen : false,
		    width : 540,
		    modal : true,
		    title : '',
		    buttons : {
		         "关闭" : function() {
		        	$(".update").dialog("close");
		        },
		        "确定" :function(){
		        	var name = $('#name').val();
		        	var is = $('#istrue').val();
		        	var id = $('#updateid').val();
		          	$.ajax({
		        		url:"./system/updateCat",
		        		type:"post",
		        		data:{name:name,is:is,id:id},
		        		success:function(msg){
		        			if (msg.code == 0) {
		        				alert('添加成功');
		        				jQuery("#grid-table").trigger('reloadGrid');
		        				$(".update").dialog("close");
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
		
		$( ".model" ).dialog({
		    autoOpen : false,
		    width : 540,
		    modal : true,
		    title : '',
		    buttons : {
		         "关闭" : function() {
		        	$(".model").dialog("close");
		        } ,
		        "确定" :function(){
		        	var dataid = $('#dataid').val();
		        	var idtwo = $('#categorytwo').val();
		          	$.ajax({
		        		url:"./system/updateCategory",
		        		type:"post",
		        		data:{dataid:dataid,id:idtwo},
		        		success:function(msg){
		        			if (msg.code == 0) {
		        				$.ajax({
		        		    		url:"./system/getDetail",
		        		    		type:"post",
		        		    		data:{id:dataid},
		        		    		success:function(msg){
		        		    			if (msg.code == 0) {
		        		    				$('#name').html(msg.name);
		        		    				console.log(msg.category);
		        		    				if(msg.category != null){
		        		    					html = "";
		        		    					for(var i=0;i < msg.category.length;i++){
		        		    						html += "<div style='font-size: 17px;margin-left: 27px;'>"+msg.category[i].name+"</div>";
		        		    					}
		        		    					$('#catow').empty();
		        		    					$('#catow').append(html);
		        		    					$(".model").dialog("close");
		        		    				}
		        		    			}
		        		    		}
		        		    	})
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
		function opentwo(){
			$(".model").dialog("open");
		}
		</script>

<div class="detail_infor" style="display:none;">
	    <table cellspacing="0" id="detail_list">
	    <input type="hidden" id="dataid"/>
	    	<tr>
	            <td id="name" style="font-size: 25px;width: 100%;"></td>
	            <td><button Style="margin-left: 20px;text-align: right;" class="btn btn-xs btn-primary" onclick="opentwo()">添加</button></td>
	        </tr>
	       <tr id="catow">
	        </tr>
	    </table>
	</div>
	<div class="model" style="display:none;">
	   <div class="col-xs-3" style="width: 50%">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">二级分类</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey"
							name="category" title="二级分类" data-placement="bottom" id="categorytwo">
							<c:forEach items="${categorySeconds}" var="i">
								<option value="${i.dataid }">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
	</div>
	<div class="add" style="display:none;">
	   <div class="col-xs-3" style="width: 50%">
			<input type="text" name="categoryadd">
			</div>
	</div>
	
	<div class="update" style="display:none;">
	<input id="updateid" type="hidden">
	   <div class="col-xs-3" style="width: 50%">
				<div class="form-group">
<!-- 					<label class="col-sm-4 control-label no-padding-right" -->
<!-- 						for="form-field-6" style="font-weight: bold">编辑</label> -->
					<div class="col-sm-8" id="updateCat">
				</div>
			</div>
	</div>
</body>
</html>