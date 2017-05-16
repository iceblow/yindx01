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
						for="form-field-6" style="font-weight: bold">分类</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey" onclick="selectone()"
							name="category" title="二级分类" data-placement="bottom" id="category">
							<c:forEach items="${categorySeconds}" var="i">
								<c:if test="${i.dataid == 3 or i.dataid == 4 or i.dataid == 7 or i.dataid == 9
								or i.dataid == 10 or i.dataid == 11 or i.dataid == 12}"><option value="${i.dataid }">${i.name}</option></c:if>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
				<div class="col-xs-3">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right"
						for="form-field-6" style="font-weight: bold">分类</label>
					<div class="col-sm-8">
						<select data-rel="tooltip" class="col-sm-10 searchKey"
							name="threeCategory" title="三级分类" data-placement="bottom" id="threeCategory" onchange="selecttwo(this.value)">
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
	                url: './system/getServiceTwoCategory',
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
		function haha(index){
			alert(index);
		}
		function inits(val){
			var category = $('#category').val();
        	$("#grid-table").jqGrid('setGridParam',{
                datatype:'json',  
                postData:{category:category,ThreeCategory:val}, //发送数据  
                page:1,
                datatype: "json"
            }).trigger("reloadGrid"); //重新载入  
		}
 		$('[data-rel=tooltip]').tooltip({container:'body'});
        
        initButton([{
	        name: "新增一级", //这里是静态页的地址
	        click: function(){
	        	$(".add").dialog("open");
	        }
		},{
		        name: "新增二级", //这里是静态页的地址
		        click: function(){
		        	$(".addTwo").dialog("open");
		        }
			}])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
   			html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.dataid +'\')">删除</button>';
   			return html;
     	}
        function selectone(){
        	var category = $('#category').val();
        	$.ajax({
        		url:"./system/getNext",
        		type:"post",
        		data:{category:category},
        		success:function(msg){
        			console.log(msg.category);
        			if (msg.code == 0) {
        				if(msg.category != null){
        					html = "";
        					for(var i=0;i < msg.category.length;i++){
        						html += "<option value='"+msg.category[i].dataid+"'>"+msg.category[i].name+"</option>";
        					}
        					console.log(html);
        					$('#threeCategory').empty();
        					$('#threeCategory').append(html);
        					inits();
        				}
        			}
        		}
        	})
        }
        function selectTwo(){
        	var category = $('#categoryTwoTwo').val();
        	$.ajax({
        		url:"./system/getNext",
        		type:"post",
        		data:{category:category},
        		success:function(msg){
        			console.log(msg.category);
        			if (msg.code == 0) {
        				if(msg.category != null){
        					html = "";
        					for(var i=0;i < msg.category.length;i++){
        						html += "<option value='"+msg.category[i].dataid+"'>"+msg.category[i].name+"</option>";
        					}
        					console.log(html);
        					$('#threeCategoryTwoTwo').empty();
        					$('#threeCategoryTwoTwo').append(html);
        				}
        			}
        		}
        	})
        }
        function selecttwo(val){
        	inits(val);
        }
        
        //删除
        function deleteAction(categoryid){
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
        		$.ajax({
            		url:"./system/deleteCategoryTwo",
            		type:"post",
            		data:{categoryid:categoryid},
            		success:function(result){
            			if(result==0){
            				alert("无法删除此分类,请先删除该分类下的所有商品")
            				return;
            			}
            			inits();
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
		$( ".add" ).dialog({
		    autoOpen : false,
		    width : 540,
		    modal : true,
		    title : '',
		    buttons : {
		         "关闭" : function() {
		        	$(".add").dialog("close");
		        	$('#threeCategoryTwo').empty();
		        },
		        "确定" :function(){
		        	var categoryadd = $('#categoryadd').val();
		        	var threeCategoryTwo = $('#categoryTwo').val();
		          	$.ajax({
		        		url:"./system/addCategoryTwo",
		        		type:"post",
		        		data:{name:categoryadd,threeCategoryTwo:threeCategoryTwo},
		        		success:function(msg){
		        			if (msg.code == 0) {
		        				alert('添加成功');
		        				$(".add").dialog("close");
		        			}else{
		        				alert('添加失败');
		        			}
		        			inits();
		        			$(".add").dialog("close");
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
		$( ".addTwo" ).dialog({
		    autoOpen : false,
		    width : 540,
		    modal : true,
		    title : '',
		    buttons : {
		         "关闭" : function() {
		        	$(".addTwo").dialog("close");
		        	$('#threeCategoryTwo').empty();
		        },
		        "确定" :function(){
		        	var categoryadd = $('#categoryaddTwo').val();
		        	var threeCategoryTwo = $('#categoryTwoTwo').val();threeCategoryTwo
		        	var categoryNone = categoryNone = $('#threeCategoryTwoTwo').val();
		          	$.ajax({
		        		url:"./system/addCategoryTwo",
		        		type:"post",
		        		data:{name:categoryadd,categoryNone:categoryNone,threeCategoryTwo:threeCategoryTwo},
		        		success:function(msg){
		        			if (msg.code == 0) {
		        				alert('添加成功');
		        				$(".addTwo").dialog("close");
		        			}else{
		        				alert('添加失败');
		        			}
		        			inits();
		        			$(".addTwo").dialog("close");
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
			<select data-rel="tooltip" class="col-sm-10 searchKey"
				 name="categoryTwo"
				title="二级分类" data-placement="bottom" id="categoryTwo">
				<c:forEach items="${categorySeconds}" var="i">
					<option value="${i.dataid }">${i.name}</option>
				</c:forEach>
			</select> <input type="text" name="categoryadd" id="categoryadd"
				style="width: 83.3333%; margin-top: 20px;">
		</div>
	</div>
	<div class="addTwo" style="display: none;">
		<div class="col-xs-3" style="width: 50%">
			<select data-rel="tooltip" class="col-sm-10 searchKey"
				onclick="selectTwo()"  name="categoryTwo"
				title="二级分类" data-placement="bottom" id="categoryTwoTwo">
				<c:forEach items="${categorySeconds}" var="i">
					<option value="${i.dataid }">${i.name}</option>
				</c:forEach>
			</select> <select data-rel="tooltip" class="col-sm-10 searchKey" style="margin-top: 20px;"
				name="threeCategoryTwoTwo" title="三级分类" data-placement="bottom"
				id="threeCategoryTwoTwo">
			</select> <input type="text" name="categoryaddTwo" id="categoryaddTwo"
				style="width: 83.3333%; margin-top: 20px;">
		</div>
	</div>
</body>
</html>