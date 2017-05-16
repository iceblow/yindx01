<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../manage/js/qiniu.min.js"></script>
<script type="text/javascript" src="../manage/js/plupload.full.min.js"></script>
</head>
<body>
<form class="form-horizontal" role="form"  id="versionForm">
<div class="row">
        <input type="hidden" id="adminid" value="${admin.adminid }"/>
        <div class="col-xs-12" >
            <div class="form-group">
                <label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-3">登录账号</label>
                <div class="col-sm-6">                 
                        <input class="col-sm-3 searchKey" id="account" name="account" value="${admin.account }" class="searchKey"  data-placement="bottom"/>          
                </div>
            </div>
        </div>
        <div class="col-xs-12" >
            <div class="form-group">
                <label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-3">真实姓名</label>
                <div class="col-sm-6">                 
                        <input class="col-sm-3 searchKey" id="realname" name="realname"  value="${admin.realname }"   class="searchKey"  data-placement="bottom"/>          
                </div>
            </div>
        </div>
          <div class="col-xs-12" >
            <div class="form-group">
                <label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-3">昵称</label>
                <div class="col-sm-6">                 
                        <input class="col-sm-3 searchKey" id="nickname" name="nickname" value="${admin.nickname }" class="searchKey"  data-placement="bottom"/>          
                </div>
            </div>
        </div>
        </div>
        <div class="col-xs-12" >
            <div class="form-group">
                <label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-3">角色名称</label>
                <div class="col-sm-2">
                    <select data-rel="tooltip" type="text" class="col-sm-10 searchKey" id="type" name="type"  data-placement="bottom" >
                        <option value="">请选择</option>
                        <option value="0" <c:if test="${admin.roleid == 1 }">selected</c:if> >管理员</option>
                    </select>
                </div>
            </div>
        </div>
          <div class="col-xs-12" >
            <div class="form-group">
                <label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-3">联系电话</label>
                <div class="col-sm-6">                 
                        <input class="col-sm-3 searchKey" id="phone" name="phone" value="${admin.phone }" class="searchKey"  data-placement="bottom"/>          
                </div>
            </div>
        </div>
        <div class="col-xs-12" >
            <div class="form-group">
                <label Style="text-align: center;" class="col-sm-1 control-label no-padding-right" for="form-field-3">添加时间</label>
                <div class="col-sm-6">                 
                        <input class="col-sm-4 searchKey" id="addtime" name="addtime"  value= "<fmt:formatDate value='${admin.addtime }' pattern='yyyy-MM-dd HH:mm:ss'/>"  class="searchKey"  data-placement="bottom" readonly="readonly"/>          
                </div>
            </div>
       </div> 
        
        <div class="col-xs-3" style="clear:both;float: initial;margin-top: 70px;">
            <div class="form-group">
                <button class="btn btn-info" type="button" style="margin-left: 30%;" onclick="submitaction()">
                    <i class="icon-ok bigger-110"></i>
                    修改
                </button>
                <button class="btn" type="button" style="margin-left: 20%;" onclick="goback()">
                    <i class="icon-undo bigger-110"></i>
                    取消
                </button>
            </div>
        </div>
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
                <table id="grid-table"></table>
                <div id="grid-pager"></div>
        </div><!-- /.col -->
</div><!-- /.row -->
</form>
<script type="text/javascript">

function submitaction(){
    var conform = confirm("确认修改?")
    if(conform == false){
        return;
    }
    
    var params = {};

    var adminid = $('#adminid').val();
    var account = $('#account').val();
    var realname = $('#realname').val();
    var nickname = $('#nickname').val();
    var phone = $('#phone').val();
    var addtime = $('#addtime').val();
   // alert(phone);
    debugger;
    params.adminid = adminid;
    params.account = account;
    params.realname = realname;
    params.nickname = nickname;
    params.phone = phone;
 
   $.ajax({
        url: './admin/doUpdateAdmin',
        data: params,
        type: 'POST',
        success: function(result){
        	
        	var data = eval("("+result+")");
        	if(data.code == 1) {
        		  loadHtml("./admin/adminList");
                  alert(data.message); 
        	}else {
        		 alert(data.message); 
        	}
        },
	    error:function (){
	        alert("错误");
	    }
    })
    
}

function goback(){
	loadHtml("./admin/adminList");

}

</script>
</body>
</html>