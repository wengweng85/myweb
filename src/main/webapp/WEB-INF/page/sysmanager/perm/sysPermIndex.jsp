<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>权限管理</title>
<!-- css头文件  -->
<rc:csshead/>
</head>
<body class="gray-bg">
<div class="">
       <div class="col-sm-3">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>权限树区</h5>
					<div class="ibox-tools">
                        <a onclick="sys_perm_addnewperm()" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
                        <a onclick="sys_perm_deleteperm()" class="btn btn-danger btn-xs"><i class="fa fa-remove"></i>&nbsp;删除</a>
                    </div>
				</div>
				<div class="ibox-content">
					<ul id="tree-div" class="ztree" style="overflow: auto; height: 670px; "></<ul>
				</div>
			</div>
		</div>

		<div class="col-sm-9">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>权限编辑区</h5>
				</div>
				<div class="ibox-content">
				<form action="<c:url value='/sys/perm/saveorupdate'/>"  class="form-horizontal" method="post" id="myform">
                           <rc:hidden property="permissionid"/>
                           <rc:hidden property="parentid" />
                           <div class="form-group">
                                <rc:textedit property="parentname" cols="2,8" label="上级权限名称 " readonly="true"/>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <rc:textedit property="code"  required="true"  cols="2,8" label="权限编码 " validate="{required:true,messages:{required:'请输入权限编码'}}"/>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <rc:textedit property="name"  required="true"  cols="2,8" label="权限名称" validate="{required:true,messages:{required:'请输入权限名称'}}"/>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <rc:textedit property="describe" required="true"  cols="2,8" label="权限描述"  validate="{required:true,messages:{required:'请输入权限描述'}}"/>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <rc:select codetype="PERMTYPE" property="type"  label="权限类型" required="true"  cols="2,8" validate="{required:true,messages:{required:'请选择权限类型'}}"/> 
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                                <rc:textedit property="url" cols="2,8" label="权限地址url" />
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                                <rc:textedit property="sortnum" cols="2,8" datamask="9999" label="排序号"/>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <rc:date property="updatetime" label="更新日期" required="true"  cols="2,8" validate="{required:true,messages:{required:'请选择更新日期'}}" />
                           </div>
                           <div class="hr-line-dashed"></div>
	                       <div class="form-group" style="text-align: right;" >
		                       <div class="col-sm-10 col-xs-10">
		                           <a class="btn btn-primary "  onclick="sys_perm_savePermData()"><i class="fa fa-save"></i>&nbsp;保存</a>
		                       </div>
	                       </div>
                 </form>
			</div>
		</div>		
</div>
<rc:jsfooter />
<script type="text/javascript">
$(function() {
	//验证 ajax
	rc.validAndAjaxSubmit($("#myform"),sys_perm_callback);
	sys_perm_treeinit();
})

function sys_perm_treeinit(){
	$.fn.zTree.init($("#tree-div"), sys_perm_setting);
}
//回调函数
function sys_perm_callback(response){
	if(response.success){
       	alert(response.message);
       	sys_perm_treeinit();
	}
	else{
		alert(response.message);
	}
}
//树配置
var sys_perm_setting = {
	view: {
       showLine: true
	},
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
			pIdKey: "pid"
		}
	},
	callback: {
		onClick: sys_perm_onClick
	},
	async: {
		enable: true,
		url: "<c:url value='/sys/perm/treedata'/> ",
		autoParam:["id"],
	}
};


function sys_perm_onClick(event, treeId, treeNode, clickFlag) {
	rc.ajaxQuery("<c:url value='/sys/perm/getPermData/'/>"+treeNode.id);
}



//保存页面配置信息
function sys_perm_savePermData(){
   $('#myform').submit();
}
//新增权限
function sys_perm_addnewperm(){
	var permissionid=$('#permissionid').val()||'0';
	var name=$('#name').val()||'权限头结点';
	rc.clean();
	$('#parentid').val(permissionid);
	$('#parentname').val(name);
}
//删除权限
function sys_perm_deleteperm(){
	var permissionid=$('#permissionid').val();
	if(permissionid){
		layer.confirm('确定删除要此权限吗？', function(index){
			var url= "<c:url value='/sys/perm/deletePermDataById/'/>"+permissionid;
			rc.ajax(url, null,function (response) {
				if(response.success){
					sys_perm_treeinit();
					rc.clean();
				}else{
					alert(response.message);
				}
			});
		});
	}else{
		layer.alert('请先选择一个你要删除的权限节点');
	}
}
</script>
</body>
</html>