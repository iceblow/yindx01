//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
	'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
	'ui-icon-seek-next' : 'icon-angle-right bigger-140',
	'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
};
$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
	var icon = $(this);
	var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
	
	if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}

function getSearchKey(){
	var parmas = "";
	$(".searchKey").each(function(){
		var name = $(this).attr("name");
		if(name){
			parmas +=name+"="+$(this).val()+"&";
		}
	})
	return parmas;
}

function searchFrom(url){
	var param = getSearchKey();//getSearchParam 在global.js里，它自动查询class="searchKey"的input
	var url = url+param;
	jQuery("#grid-table").setGridParam({url:url,page:1}).trigger("reloadGrid");
}


function initButton(jsondata){
	$("#nav-search").empty();
	var data = eval(jsondata);
	for(var i=0;i<data.length;i++){
		//解决闭包
		!function(i){
			var html = '<button class="btn btn-xs btn-primary" id="nav-search-btn'+i+'">'+data[i].name+'</button>';
			$("#nav-search").append(html);
			var callback =  data[i].click;
			$("#nav-search-btn"+i).on("click",function(){
				callback(); 	
			})
		}(i);
	}
}

function loadHtml(url){
	$.ajax( {
        url: url, //这里是静态页的地址
        type: "GET", //静态页用get方法，否则服务器会抛出405错误
        success: function(data){
            $(".page-content").html(data);
        }
	});
	$("#nav-search").empty();
}

function getHtml(obj,html){
	if(obj.children("a").children(".menu-text").length>0){
		html = "<li><i class='"+obj.children("a").children("i").attr('class')+"'></i>"+obj.children("a").children(".menu-text").text()+"</li>" + html;
	}else if(obj.children("a").length>0){
		if(html==""){
			html = "<li>"+ obj.children("a").text()+"</li>" + html;
		}else{
			html = "<li>"+ obj.children("a").text()+"</li>" + html;
		}
		
	}
	return html;
}