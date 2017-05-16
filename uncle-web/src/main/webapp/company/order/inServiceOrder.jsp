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
								url : './order/getInServiceOrder?pagetype=${ sessionScope.page }',
								datatype : "json",
								 page:'${page}',
								colModel : [ {
									label : '订单号',
									name : 'ordernum',
									width : 50
								}, {
									label : '服务类型',
									name : 'type',
									width : 50
								}, {
									label : '上门时间',
									name : 'gotime',
									width : 50
								}, {
									label : '金额',
									name : 'price',
									width : 50
								}, {
									label : '是否有小费',
									name : 'isprice',
									width : 50
								}, {
									label : '地址',
									name : 'addressdetail',
									width : 50
								}, {
									label : '状态',
									name : 'state',
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

// 					initButton([ {
// 						name : "查询",
// 						click : function() {
// 							searchFrom('./point/getPointOrder?');
// 						}
// 					} ])
				});
		function replaceSpace(obj) {
			obj.value = obj.value.replace(/(^\s*)|(\s*$)/g, "");
		}

		//加载图片
		function loadpicture(cellvalue, options, rowObject) {
			var html = '';
			html += '<img src=\''+rowObject.listpicurl +'\' style="height: 100px; width: 100px;">';
			return html;
		}

		function changestatus(cellvalue, options, rowObject) {
			var html = '';
			html += '<button class="btn btn-xs btn-primary" onclick="detail(\''
				+ rowObject.orderid + '\')" style="background-color:rgb(140,177,196) !important;border-color:rgb(140,177,196) !important;margin-right:10px;">查看</button>';
			return html;
		}

		function deliver(id) {
			$('#orderid').val(id);
			$('.tips').show();
		}

		function cancel() {
			$('.tips').hide();
		}

		function deliverGoods() {
			var orderid = $('#orderid').val();
			var logistic = $('#logistic').val().trim();
			var logisticnum = $('#logisticnum').val().trim();
			var page = $('#grid-table').getGridParam('page');
			var rowNum = $('#grid-table').getGridParam('rowNum');
			if (logistic == '') {
				alert("请输入物流公司名称！");
				return;
			}
			if (logisticnum == '') {
				alert("请输入物流单号！");
				return;
			}
			$.ajax({
				url : './point/changeOrderState',
				data : {
					orderid : orderid,
					state : 0,
					logistic : logistic,
					logisticnum : logisticnum
				},
				success : function(msg) {
					if (msg != null) {
						if (msg.code == 1) {
							alert(msg.message);
							loadHtml("./point/pointOrder?page="+page+"&rowNum="+rowNum);
						} else {
							alert(msg.message);
						}
					}
				}
			})
		}
		
		function onOrder(id,type) {
			if(type == 0){
				if (!confirm("是否确定接单")) {
					return;
				}
			}
			if(type == 1){
				if (!confirm("是否确定抢单")) {
					return;
				}
			}
			if(type == 2){
				if (!confirm("是否确定拒单")) {
					return;
				}
			}
			if(type == 3){
				if (!confirm("是否确定派单")) {
					return;
				}
			}
			$.ajax({
				url : './order/onOrder',
				data : {
					orderid : id,
					type:type
				},
				success : function(msg) {
					if (msg != null) {
						msg = eval("("+msg+")");
						if (msg.code == 1) {
							alert(msg.message);
							loadHtml('./order/getPendingOrder?pagetype=${ sessionScope.page }');
						} else {
							alert(msg.message);
						}
					}
				}
			})
		}
		$( "#model" ).dialog({
		    autoOpen : false,
		    width : 560,
		    modal : true,
		    title : '',
		    show: {
		        effect: "blind",
		        duration: 100
		    },
		    hide: {
		        effect: "blind",
		        duration: 50
		    }
		});
		function detail(id) {
			$.ajax({
				url : './order/orderDetail1',
				data : {
					orderid : id
				},
				success : function(msg) {
					if (msg != null) {
						if (msg.code == 0) {
							$("#title").html(msg.order.type);
							$("#gotime").html(msg.order.gotime);
							$("#goprice").html(msg.order.goprice);
							$("#yutime").html(msg.order.yutime);
							$("#re").html(msg.order.re);
							$("#address").html(msg.order.address);
							var img = "";
							console.log(msg);
							console.log(msg.order.img);
							if(msg.order.contant != null){
								$("#contant").html(msg.order.contant);
							}else{
								$('#contant').parent('tr').css('display','none');
							}
							if(msg.order.img != null){
								for(var i = 0;i < msg.order.img.length;i++){
									img += '<img src='+msg.order.img[i]+' class="detail_img">';
								}
								$("#img").html(img);
							}else{
								$('#img').parent('tr').css('display','none');
							}
							$("#model").dialog("open");
						} else {
							alert(msg.message);
						}
					}
				}
			})
		}
	</script>

		<div class="detail_infor" style="display: none;" id="model">
    <div class="top_text">
        查看
<!--         <span class="del_btn">×</span> -->
    </div>
		<div class="top_title" id="title"></div>
		<table cellspacing="0" id="detail_list">
		 <tr>
            <td>清理内容</td>
            <td id="contant"></td>
        </tr>
        <tr>
            <td>上门时间</td>
            <td id="gotime"></td>
        </tr>
        <tr>
            <td>上门服务费</td>
            <td id="goprice"></td>
        </tr>
        <tr>
            <td>预计时长</td>
            <td id="yutime"></td>
        </tr>
        <tr>
            <td>备注</td>
            <td id="re"></td>
        </tr>
        <tr>
            <td>照片</td>
            <td id="img">
<!--                 <img src="../img/detail1.png" class="detail_img"> -->
<!--                 <img src="../img/detail2.png" class="detail_img"> -->
            </td>
        </tr>
        <tr>
            <td>雇主信息</td>
            <td id="address"></td>
        </tr>

    </table></div>
</body>
</html>