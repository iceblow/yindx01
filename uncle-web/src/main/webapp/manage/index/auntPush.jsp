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
								url : './index/getAuntPush',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '用户ID',
									name : 'userid',
									width : 50
								}, {
									label : '用户类别',
									name : 'userType',
									width : 50,
									formatter : typeDef
								}, {
									label : '推送用ID',
									name : 'pushKey',
									width : 50
								}, {
									label : '推送消息',
									name : 'isaccept',
									width : 50,
									formatter : isacceptFmt
								}, {
									label : '用户设备类型',
									name : 'devicetype',
									width : 50,
									formatter : devicetypeFmt
								}, {
									label : '操作',
									name : 'opreation',
									width : 50,
									formatter : operationFmt
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
								height : $(window).height() - 300,
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

					initButton([ {
						name : "新增",
						click : function() {
							$.ajax({
								url: './admin/getSelectRole',
								data: {},
								type: 'POST',
								success: function(msg){
									$('#adminForm')[0].reset();
									var data = eval('('+msg+')');
									var html = '<option value="">请选择</option>';
									for (var i=0; i<data.list.length ; i++) {
										html += '<option value="'+data.list[i].roleid+'">'+data.list[i].rolename+'</option>';
									}
									$('#roleid').html(html);
									$(".detail_infor").dialog("open");
								}
							})
						}
					} ])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function isacceptFmt(cellvalue, options, rowObject) {
			var html = '';
			switch (rowObject.isaccept) {
			case 0:
				html = '接收';
					break;
			case 1:
				html = '不接受';break;
			default:
				break;
			}
			
			return html;
		}

		function changestatus(cellvalue, options, rowObject) {
			var html = '';
// 			html += '<button class="btn btn-xs btn-primary" onclick="detail(\''
// 				+ rowObject.orderid + '\')" style="background-color:rgb(140,177,196) !important;border-color:rgb(140,177,196) !important;margin-right:10px;">查看</button>';
			return html;
		}
		
		function typeDef(cellvalue, options, rowObject) {
			var html = '';
			switch (rowObject.userType) {
			case 0:
				html = '阿姨';break;
			case 1:
				html = '公司';break;
			default:
				break;
			}
			
			return html;
		}
		
		function operationFmt(cellvalue, options, rowObject) {
			var detailBtn = '<button class="btn btn-xs btn-primary" style="margin-right:10px;" onclick="detail(\''
				+ rowObject.userid + '\')">查看详情</button>';
			
				var html = detailBtn;;

			switch (rowObject.opreation) {
			case 0:
				html = detailBtn;
					break;
			case 1:
				html = '修改';break;
			case 2:
				html = '删除';break;
			default:
				break;
			}
			
			return html;
		}
		
		function picAjax(cellvalue, options, rowObject) {
			var html = '';
			$.ajax({
				url: './index/getPath',
				data: {'picid':rowObject.picid},
				type: 'POST',
				async:false,
				success: function(msg){
					html =  "<img src='" + msg +"'/>";
					console.log(html);
					
				}
			});
			return html;
		}
		
		function devicetypeFmt(cellvalue, options, rowObject) {
			var html = '';
			switch (rowObject.devicetype) {
			case 1:
				html = '安卓';break;
			case 1:
				html = 'IOS';break;
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
		
		$( ".detail_infor" ).dialog({
		    autoOpen : false,
		    height : 400,
		    width : 600,
		    modal : true,
		    title : '',
		    buttons : {
		        "关闭" : function() {
		        	$(".detail_infor").dialog("close");
		        },
		        "保存" : function() {
		        	if ($('#account').val() == '') {
		        		alert("请输入登陆账号！");
		        		return;
		        	}
		        	if ($('#password').val() == '') {
		        		alert("请输入登陆密码！");
		        		return;
		        	}
		        	if ($('#realname').val() == '') {
		        		alert("请输入真实姓名！");
		        		return;
		        	}
		        	if ($('#nickname').val() == '') {
		        		alert("请输入昵称！");
		        		return;
		        	}
		        	if ($('#phone').val() == '') {
		        		alert("请输入联系电话！");
		        		return;
		        	}
		        	if ($('#roleid').val() == '') {
		        		alert("请选择角色！");
		        		return;
		        	}
		        	$.ajax({
		        		url: './admin/addAdmin',
		        		data: $('#adminForm').serialize(),
		        		type: 'POST',
		        		success: function(msg){
		        			var data = eval('('+msg+')');
		        			if (data.code == 1) {
		        				alert(data.message);
		        				$(".detail_infor").dialog("close");
		        				loadHtml("./admin/adminList");
		        			} else {
		        				alert(data.message);
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
	
	<div class="detail_infor" style="display:none;">
		<form id="adminForm" class="form-horizontal col-xs-12" style="margin-top: 20px;">
			<div class="col-xs-11">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">登录账号</label>
					<div class="col-sm-10">
						<input data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="account" id="account" title="登录账号" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-11">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">登录密码</label>
					<div class="col-sm-10">
						<input data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="password" id="password" title="登录密码" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-11">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">真实姓名</label>
					<div class="col-sm-10">
						<input data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="realname" id="realname" title="真实姓名" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-11">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">昵称</label>
					<div class="col-sm-10">
						<input data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="nickname" id="nickname" title="昵称" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-11">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">联系电话</label>
					<div class="col-sm-10">
						<input data-rel="tooltip" type="text" class="col-sm-12"  onBlur="replaceSpace(this)" name="phone" id="phone" title="联系电话" data-placement="bottom" >
					</div>
				</div>
			</div>
			<div class="col-xs-11">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-6" style="font-weight:bold">角色</label>
					<div class="col-sm-10">
						<select class="col-sm-12" name="roleid" id="roleid" title="角色">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>