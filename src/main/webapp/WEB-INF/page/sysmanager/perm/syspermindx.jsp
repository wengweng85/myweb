<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>权限列表</title>
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
                        <a onclick="addnewperm()" class="btn btn-primary btn-xs">新增权限</a>
                        <a onclick="deleteperm()" class="btn btn-danger btn-xs">删除权限</a>
                    </div>
				</div>
				<div class="ibox-content">
					<div id="tree-div" class="ztree" style="overflow: auto; height: 670px; width: 250px;"></div>
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
                           <div class="form-group">
                               <input type="hidden" id="permissionid" name="permissionid" class="form-control" >
                               <input type="hidden" id="parentid" name="parentid" class="form-control" >
                           </div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">上级权限名称 </label>
                               <div class="col-sm-10">
                                    <rc:textedit name="parentname" id="parentname" readonly="true"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限编码 <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit name="code" id="code" validate="{required:true,messages:{required:'请输入权限编码'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限名称  <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit name="name" id="name" validate="{required:true,messages:{required:'请输入权限名称'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限描述 <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit name="describe" id="describe" validate="{required:true,messages:{required:'请输入权限描述'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限类型 <span class="require">*<span></label>
                               <div class="col-sm-10">
                                   <rc:select codetype="PERMTYPE" id="type" name="type"  validate="{required:true,messages:{required:'请选择权限类型'}}"/> 
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">权限地址url</label>
                               <div class="col-sm-10">
                                    <rc:textedit name="url" id="url" />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">排序号<span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit name="sortnum" id="sortnum" />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">更新日期</label>
                               <div class="col-sm-10">
                                   <rc:date name="updatetime" id="updatetime"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
	                       <div class="form-group" style="text-align: right;">
	                            <a class="btn btn-primary " onclick="savePermData()">保存</a>
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
	rc.validAndAjaxSubmit($("#myform"),callback);
})
//回调函数
function callback(response){
	if(response.success){
       	alert(response.message);
       	treeinit();
	}
	else{
		alert(response.message);
	}
}
//树配置
var setting = {
	view: {
		nameIsHTML: true
	},
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
			pIdKey: "pid",
			rootPId: '0'
		}
	},
	callback: {
		onClick: onClick
	},
	async: {
		enable: true,
		url: "<c:url value='/sys/perm/treedata'/> ",
		autoParam:["id"],
	}
};


function onClick(event, treeId, treeNode, clickFlag) {
	rc.ajaxQuery("<c:url value='/sys/perm/getPermData/'/>"+treeNode.id);
}

function treeinit(){
	$.fn.zTree.init($("#tree-div"), setting);
}
//初始化数据加载
$(document).ready(function(){
	treeinit();
});

//保存页面配置信息
function savePermData(){
   $('#myform').submit();
}
//新增权限
function addnewperm(){
	var permissionid=$('#permissionid').val()||'0';
	var name=$('#name').val()||'权限头结点';
	rc.clean();
	$('#parentid').val(permissionid);
	$('#parentname').val(name);
}
//删除权限
function deleteperm(){
	var permissionid=$('#permissionid').val();
	if(permissionid){
		layer.confirm('确定删除要此权限吗？', function(index){
			var url= "<c:url value='/sys/perm/deletePermDataById/'/>"+permissionid;
			rc.ajax(url, null,function (response) {
				if(response.success){
					treeinit();
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