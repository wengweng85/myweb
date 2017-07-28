<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
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
<div class="wrapper wrapper-content animated fadeInRight">
       <div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>权限列表树区</h5>
					<div class="ibox-tools">
                        <a onclick="_sys_perm_addnewperm()" class="btn btn-primary btn-xs">新增权限</a>
                        <a onclick="_sys_perm_deleteperm()" class="btn btn-danger btn-xs">删除权限</a>
                    </div>
				</div>
				<div class="ibox-content">
					<ul id="tree-div" class="ztree" style="overflow: auto; height: 670px; "></<ul>
				</div>
			</div>
		</div>

		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>权限编辑区</h5>
				</div>
				<div class="ibox-content">
				<form action="<c:url value='/sys/perm/saveorupdate'/>"  class="form-horizontal" method="post" id="myform">
                           <rc:hidden property="permissionid"/>
                           <rc:hidden property="parentid" />
                           <div class="form-group">
                               <label class="col-sm-2 control-label">上级权限名称 </label>
                               <div class="col-sm-10">
                                    <rc:textedit property="parentname" readonly="true"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限编码 <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="code"  validate="{required:true,messages:{required:'请输入权限编码'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限名称  <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="name"  validate="{required:true,messages:{required:'请输入权限名称'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限描述 <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="describe"  validate="{required:true,messages:{required:'请输入权限描述'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限类型 <span class="require">*<span></label>
                               <div class="col-sm-10">
                                   <rc:select codetype="PERMTYPE" property="type"   validate="{required:true,messages:{required:'请选择权限类型'}}"/> 
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限地址url</label>
                               <div class="col-sm-10">
                                    <rc:textedit property="url"  />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">排序号<span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="sortnum"  />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">更新日期<span class="require">*<span></label>
                               <div class="col-sm-10">
                                   <rc:date property="updatetime" validate="{required:true,messages:{required:'请选择更新日期'}}" />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
	                       <div class="form-group" style="text-align: right;">
	                            <a class="btn btn-primary " onclick="_sys_perm_savePermData()">保存</a>
	                       </div>
                 </form>
                <div id="view"></div>
			    </div>
			</div>
		</div>		
</div>
<rc:jsfooter />
<script type="text/javascript">
$(function() {
	//验证 ajax
	rc.validAndAjaxSubmit($("#myform"),_sys_perm_callback);
	_sys_perm_treeinit();
})

function _sys_perm_treeinit(){
	$.fn.zTree.init($("#tree-div"), _sys_perm_setting);
}
//回调函数
function _sys_perm_callback(response){
	if(response.success){
       	alert(response.message);
       	_sys_perm_treeinit();
	}
	else{
		alert(response.message);
	}
}
//树配置
var _sys_perm_setting = {
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
		onClick: _sys_perm_onClick
	},
	async: {
		enable: true,
		url: "<c:url value='/sys/perm/treedata'/> ",
		autoParam:["id"],
	}
};


function _sys_perm_onClick(event, treeId, treeNode, clickFlag) {
	rc.ajaxQuery("<c:url value='/sys/perm/getPermData/'/>"+treeNode.id);
}



//保存页面配置信息
function _sys_perm_savePermData(){
   $('#myform').submit();
}
//新增权限
function _sys_perm_addnewperm(){
	var permissionid=$('#permissionid').val()||'0';
	var name=$('#name').val()||'权限头结点';
	rc.clean();
	$('#parentid').val(permissionid);
	$('#parentname').val(name);
}
//删除权限
function _sys_perm_deleteperm(){
	var permissionid=$('#permissionid').val();
	if(permissionid){
		layer.confirm('确定删除要此权限吗？', function(index){
			var url= "<c:url value='/sys/perm/deletePermDataById/'/>"+permissionid;
			rc.ajax(url, null,function (response) {
				if(response.success){
					_sys_perm_treeinit();
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