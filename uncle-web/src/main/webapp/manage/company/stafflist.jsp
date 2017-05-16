<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/detail.css">
</head>
<body>
	<div class="zhezhao" id="zhezhao"></div>
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
								url : './company/getStaffList?pagetype=${ sessionScope.page }',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '员工姓名',
									name : 'realName',
									width : 50
								}, {
									label : '手机号码',
									name : 'phone',
									width : 50
								}, {
									label : '籍贯',
									name : 'originPlace',
									width : 50
								}, {
									label : '性别',
									name : 'sex',
									width : 50
								}, {
									label : '出生日期',
									name : 'birthday',
									width : 50
								}, {
									label : '身份证号码',
									name : 'idcardNum',
									width : 50
								}, {
									label : 'VIP等级',
									name : 'level',
									width : 50
								},{
									label : '操作',
									name : '',
									width : 50,
									formatter : changestatus
								}, ],
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
										// 								styleCheckbox(table);
										// 								updateActionIcons(table);
										updatePagerIcons(table);
										// 								enableTooltips(table);
									}, 0);
								},
							});

					$('[data-rel=tooltip]').tooltip({
						container : 'body'
					});

					initButton([ {
						name : "新增",
						click : function() {
							$('.tips').show();
						}
					} ])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}

		function changestatus(cellvalue, options, rowObject) {
			console.log(rowObject);
			var html = '';
			html += '<button class="btn btn-xs btn-primary" style="margin-right:10px; background-color: #8DB2C5 !important; border-color: #8DB2C5 !important;" onclick="detail(\''
				+ rowObject.auntid + '\')">查看</button>';
			html += '<button class="btn btn-xs btn-primary" style="margin-right:10px; background-color: #F4564B !important; border-color: #F4564B !important;" onclick="del(\''
				+ rowObject.auntid + '\')">删除</button>';
			return html;
		}

		function cancel() {
			$('.tips').hide();
		}
		
		function addStaff(){
			var rowIds=$('#search-table').jqGrid('getGridParam','selarrrow');
			var ids = new Array();
			for (var i=0; i<rowIds.length ; i++) {
				var rowData = $('#search-table').jqGrid('getRowData',rowIds[i]);
				ids.push(rowData.auntid);
			}
			console.log(ids);
			if (ids.length == 0) {
				alert("请至少选择一名阿姨");
				return;
			}
			
			$.ajax({
				url: './company/addStaff',
				data: {ids:ids},
				type: 'POST',
				traditional: true,
				success: function(msg){
					var data = eval('('+msg+')');
					if (data.code == 1) {
						alert(data.message);
						loadHtml("./company/stafflist?page=1");
					} else {
						alert(data.message);
					}
				}
			})
		}
		
		$( ".detail_infor" ).dialog({
		    autoOpen : false,
		    height : 840,
		    width : 670,
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

		function detail(id){
			$.ajax({
				url: './company/staffDetail',
				data: {id:id},
				type: 'POST',
				success: function(msg){
					var data = eval('(' + msg + ')');
					if (data.code == 0) {
						alert("获取员工数据失败，请刷新后重试！");
						return;
					}
					$('.detail_img').attr("src",data.aunt.portrait);
					$('#realName').text(data.aunt.realName);
					$('#phone').text(data.aunt.phone);
					$('#sex').text(data.aunt.sex);
					$('#originPlace').text(data.aunt.originPlace);
					var birthday = new Date(data.aunt.birthday);
					$('#birthday').text(birthday.getFullYear() + "-" + (birthday.getMonth() + 1) + "-" + birthday.getDate());
					$('#idcardNum').text(data.aunt.idcardNum);
					$('#height').text(data.aunt.height + "cm");
					$('#weight').text(data.aunt.weight + "kg");
					$('#homeAddress').text(data.aunt.homeAddress);
					$('#nowAddress').text(data.aunt.nowAddress);
					$('#workYear').text(data.aunt.workYear + "年");
					$('#workHis').text(data.aunt.workHis);
					$('#characters').text(data.aunt.characters);
					$('#bloodType').text(data.aunt.bloodType);
					$('#culture').text(data.aunt.culture);
					$('#language').text(data.aunt.language);
					$('#religion').text(data.aunt.religion);
					$('#political').text(data.aunt.political);
					if (data.aunt.state == 0) {
						$('#state').text("待审核");
					} else if (data.aunt.state == 1) {
						$('#state').text("正常");
					} else if (data.aunt.state == 2) {
						$('#state').text("冻结");
					}
					$('#signature').text(data.aunt.signature);
					$('#constellation').text(data.aunt.constellation);
					$('#zodiac').text(data.aunt.zodiac);
					$('#companyid').text(data.aunt.companyid);
					$('#level').text(data.aunt.level + "级");
					
					
					$(".detail_infor").dialog("open");
				}
			});
		}
		
		function del(id){
			if (!confirm("确定删除么？")){
				return;
			}
			$.ajax({
				url: './company/delStaff',
				data: {id:id},
				type: 'POST',
				success: function(msg){
					var data = eval('('+msg+')');
					alert(data.message);
					loadHtml("./company/stafflist?page=1");
				}
			})
		}

	</script>

	<div class="tips"
		style="position: absolute; margin-top: -40%; width: 60%; margin-left: 20%; display: none">
		<div class="col-sm-8">
			<div class="widget-box">
				<div class="widget-header">
					<h4>新增</h4>
				</div>

				<div class="widget-body">
					<div class="widget-main no-padding">
						<div style="padding-left:5%; padding-top: 20px;">
							<input type="text" id="searchinput" placeholder="请输入" style="width: 50%;">
							<img src="../img/search.png" style="margin-top: -3px; cursor: pointer;" onclick="searchAddList()">
						</div>
						
						<div style="width: 90%; margin-left: 5%; margin-top: 20px;" id="jqgrid-container">
							<table id="search-table"></table>
							<div id="search-pager"></div>
						</div>

						<div class="form-actions" style="text-align: right;">
							<button type="button" class="btn btn-sm" onclick="cancel()"
								style="margin-right: 20px; background-color: #c1c1c1 !important; border-color: #c1c1c1 !important;">
								取消 
							</button>
							<button type="button" class="btn btn-sm" onclick="addStaff()"
								style="margin-right: 20px; background-color: #418BCA !important; border-color: #418BCA !important;">
								确定 
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="detail_infor" style="display:none;">
	    <table cellspacing="0" id="detail_list">
	    	<tr>
	            <td>用户头像</td>
	            <td colspan="3">
	                <img src="" class="detail_img">
	            </td>
	        </tr>
	        <tr>
	            <td>员工姓名</td>
	            <td id="realName" ></td>
	            <td>手机号码</td>
	            <td id="phone" ></td>
	        </tr>
	        <tr>
	            <td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>
	            <td id="sex" ></td>
	            <td>籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯</td>
	            <td id="originPlace" ></td>
	        </tr>
	        <tr>
	            <td>出生日期</td>
	            <td id="birthday" ></td>
	            <td>身份证号</td>
	            <td id="idcardNum" ></td>
	        </tr>
	        <tr>
	            <td>身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高</td>
	            <td id="height" ></td>
	            <td>体&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重</td>
	            <td id="weight" ></td>
	        </tr>
	        <tr>
	            <td>家庭住址</td>
	            <td id="homeAddress" ></td>
	            <td>现居地址</td>
	            <td id="nowAddress" ></td>
	        </tr>
	        <tr>
	            <td>从业资历</td>
	            <td id="workYear" ></td>
	            <td>工作经验</td>
	            <td id="workHis" ></td>
	        </tr>
	        <tr>
	            <td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</td>
	            <td id="characters" ></td>
	            <td>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</td>
	            <td id="bloodType" ></td>
	        </tr>
	        <tr>
	            <td>文化程度</td>
	            <td id="culture" ></td>
	            <td>语言能力</td>
	            <td id="language" ></td>
	        </tr>
	        <tr>
	            <td>宗教信仰</td>
	            <td id="religion" ></td>
	            <td>政治面貌</td>
	            <td id="political" ></td>
	        </tr>
	        <tr>
	            <td>阿姨状态</td>
	            <td id="state" >0.待审核 1.正常 2.冻结</td>
	            <td>个性签名</td>
	            <td id="signature" ></td>
	        </tr>
	        <tr>
	            <td>星&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;座</td>
	            <td id="constellation" ></td>
	            <td>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;肖</td>
	            <td id="zodiac" ></td>
	        </tr>
	        <tr>
	            <td>公司&nbsp;&nbsp;&nbsp;&nbsp;ID</td>
	            <td id="companyid" ></td>
	            <td>VIP&nbsp;&nbsp;等级</td>
	            <td id="level" ></td>
	        </tr>
	        <tr>
	            <td>登陆密码</td>
	            <td colspan="3">*******</td>
	        </tr>
	    </table>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		var rowDataNames = [];
		$("#search-table").jqGrid({
			url : './company/addStaffList',
			datatype : "json",
			 page:'${searchpage}',
			colModel : [ {
				label : '员工姓名',
				name : 'realName',
				width : 50
			}, {
				label : '手机号码',
				name : 'phone',
				width : 50
			}, {
				label : '籍贯',
				name : 'originPlace',
				width : 50
			}, {
				label : '性别',
				name : 'sex',
				width : 50
			}, {
				label : '身份证号码',
				name : 'idcardNum',
				width : 50
			}, {
				label : 'id',
				name : 'auntid',
				hidedlg : true
			}, ],
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
			rowList : [ 5, 10 ],
			width : $('.main-content').width() * 0.38,
			height : $(window).height() * 0.3,
			rowNum: 5,
			pager : "#search-pager",
			shrinkToFit : true,
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
				$('.tips').show();
			}
		} ])
	});
	
	function searchAddList() {
		var keywords = $('#searchinput').val();
		$("#search-table").jqGrid('setGridParam',{ 
            url:"./company/addStaffList", 
            postData:{'keywords':keywords}, //发送数据 
            page:1 
        }).trigger("reloadGrid");
	}
	</script>
</body>
</html>