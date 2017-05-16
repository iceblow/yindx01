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
<form class="form-horizontal" role="form">
		<div class="col-xs-3">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">省份选择</label>
				<div class="col-sm-8">
					<select name="provienceid" id="proselect" onchange="changepro()" data-rel="tooltip" type="text" class="col-sm-10 searchKey" title="省份选择" data-placement="bottom">
						<option value="">请选择省</option>
						<c:forEach items="${list }" var="p">
							<option value="${p.id }" >${p.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-xs-3">
	        <div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-6">城市选择</label>
				<div class="col-sm-8">
				    <input type="hidden" value="" id="cityName" name="cityName"> 
					<select name="cityid" id="cityselect"  data-rel="tooltip" type="text" onclick="cityselects()" class="col-sm-10 searchKey" title="城市选择" data-placement="bottom">
						<option value="">请选择市</option>
						<c:forEach items="${citys}" var="c">
							<option value="${c.id}" >${c.name}</option>
						</c:forEach>
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
							<option value="">请选择区</option>
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
	                url: './home/seachiconlist',
	                datatype: "json",
	                page:'${page}',
	                colModel: [
						{ label: 'icon图片', width: 50 ,formatter:loadpicture },
						{ label: '类型名称', name: 'typeName', width: 50},
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
	        	var pro = $("#proselect").val();
	        	var city = $("#cityselect").val();
	        	var text = $("#cityselect").children('option:selected').text();
	        	var area=$("#area").val();
	        	$("#cityName").val(text);
	        	if(city == "" || pro == "" ||area==""){
	        		alert("请选择省市区信息");
	        		return;
	        	}
	        	searchFrom('./home/selectcontentlist?');
	        }
    	},{
		        name: "新增", //这里是静态页的地址
		        click: function(){
		        	loadHtml("./home/goaddicon");
		        }
			}])
		
		//添加操作按钮
        function changestatus(cellvalue, options, rowObject){	
     		var html = '';
     		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="upsort(\''+rowObject.dataid +'\',\''+rowObject.city +'\')">上升</button>';
     		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="downsort(\''+rowObject.dataid +'\',\''+rowObject.city +'\')">下降</button>';
     		//html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="editAction(\''+rowObject.iconid +'\')">编辑</button>';
       		html += '<button Style="margin-left: 20px;" class="btn btn-xs btn-primary" onclick="deleteAction(\''+rowObject.dataid +'\',\''+rowObject.city +'\')">删除</button>';    			
   			return html;
     	}
        
     /*    //编辑
        function editAction(iconid){
        	loadHtml("./home/goaddicon?iconid="+iconid);
        } */
        
        //删除
        function deleteAction(iconid){
        	var bool = confirm("是否确认删除？");
        	if(bool == true){
	        	$.ajax({
	        		url:"./home/delicon",
	        		type:"post",
	        		data:{iconid:iconid},
	        		success:function(code){
	        			if(code == 0){
	        				alert("删除失败");
	        				return;
	        			}else{
	        				alert("删除成功");
	        				loadHtml("./home/goiconlist");
	        			}
	        		}
	        	});
        	}
        	return;
        }
        
      //加载图片
        function loadpicture(cellvalue, options, rowObject){
        	var html = '';
        	if(rowObject.picurl != null){
  				html += '<img src=\''+rowObject.picurl +'\' style="height: 100px; width: 100px;">' ;        		
        	}
   			return html;
        }
		
      //排序上升
        function upsort(iconid,city){
        	//var text = $("#cityselect").children('option:selected').text();
        	$.ajax({
        		url:"./home/upiconsort",
        		type:"post",
        		data:{iconid:iconid,"cityName":city},
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
        
        //排序下降
        function downsort(iconid,city){
        	//var text = $("#cityselect").children('option:selected').text();
        	
        	$.ajax({
        		url:"./home/downiconsort",
        		type:"post",
        		data:{iconid:iconid,"cityName":city},
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
        
        function changepro(){
			var provienceid = $("#proselect").val();
			//alert(provienceid);
			if(provienceid != ""){
				$.ajax({
					type: "post",
					url: "./home/changeprovience",
					data: {provienceid :provienceid},
					success: function(msg){
						var data = eval("("+msg+")");
						var list = data.cityes;
						$("#cityselect").empty();
						var html = '';
						html += '<option value="">请选择市</option>';
						if(list != null && list.length > 0){
							for(var i = 0; i < list.length; i++){
								html += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
							}
						}
						$("#cityselect").append(html);
					},
					error:function (){
						alert("错误");
					}
				});
			}
		}
        function cityselects(){
        	var val = $('#cityselect').val();
        	console.log(val);
        	$.ajax({
        		url:"./index/selectArea",
        		type:"post",
        		data:{id:val},
        		success:function(msg){
        			console.log(msg);
        			$('#area').empty();
        			var html = '';
        			html += '<option value="">请选择区</option>';
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
        
		</script>
</body>
</html>