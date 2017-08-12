<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>角色管理</title>
<!-- css头文件  -->
<rc:csshead />
</head>
<body class="gray-bg">
	<div >
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-content" id="role_edit_div">
				<form action="<c:url value='/sys/role/saveorupdate'/>" class="form-horizontal" method="post" id="myform">
					<rc:hidden property="roleid"  value="${srole.roleid }"/>
					<div class="form-group">
						 <rc:textedit property="code"   value="${srole.code }" label="角色编码 "    required="true"  cols="2,4" validate="{required:true,messages:{required:'请输入角色编码'}}" />
						<rc:textedit property="name"   value="${srole.name }"  label="角色名称" required="true"  cols="2,4" validate="{required:true,messages:{required:'请输入角色名称'}}" />
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<rc:textedit property="describe"  value="${srole.describe }"  label="角色描述" required="true"  cols="2,10"  validate="{required:true,messages:{required:'请输入角色描述'}}" />
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group" style="text-align: right;">
						<a class="btn btn-primary " onclick="sys_role_saveRoleData()"><i class="fa fa-save"></i>&nbsp;保存</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>角色授权</h5>
				<div class="ibox-tools">
				</div>
			</div>
			<div class="ibox-content">
				<div id="tree-div" class="ztree" style="overflow: auto; height: 300px; width: 250px;"></div>
				<div class="hr-line-dashed"></div>
				<div class="form-group" style="text-align: right;">
					<button id="btn_role_perm" class="btn btn-primary " onclick="sys_role_saveRolePermData()"><i class="fa fa-save"></i>&nbsp;保存</button>
				</div>
			</div>
		</div>
	</div>
<rc:jsfooter />
<script type="text/javascript">
   //初始化
   $(function(){
   	    //角色编辑
   		rc.validAndAjaxSubmit($("#myform"),sys_role_callback);
   		sys_role_treeinit();
   });
   
   //角色-权限树配置
   var sys_role_setting = {
	  view: {
          showLine: true
	  },	   
      check: {
		enable: true
	  },
   	  data: {
   		simpleData: {
   			enable: true,
   			pIdKey: "pid",
   			rootPId: '0'
   		}
   	  },
   	  async: {
   		 enable: true,
   		 url: "<c:url value='/sys/role/treedata'/>",
   		 autoParam:["id"],
   		 otherParam: {"id":"${srole.roleid }"}
   	  }
   };
   
 //树初绍化
   function sys_role_treeinit(){
	  $.fn.zTree.init($("#tree-div"), sys_role_setting);
	  var zTree = $.fn.zTree.getZTreeObj("tree-div");
	  zTree.expandAll(true)
   }

 //回调函数
   function sys_role_callback(response){
	  if(response.success){
       	  alert(response.message);
      	  parent.$('#roletable').refreshtable();
	  }
	  else{
		  alert(response.message);
	  }
   }
   
    //保存页面配置信息
   function sys_role_saveRoleData(){
      $('#myform').submit();
   }
   
   //树初绍化
   function sys_role_treeinit(){
	  $.fn.zTree.init($("#tree-div"), sys_role_setting);
	  var zTree = $.fn.zTree.getZTreeObj("tree-div");
	  zTree.expandAll(true)
   }
   
   //保存角色-权限数据
   function sys_role_saveRolePermData() {
	   var roleid=$('#roleid').val();
	   if(roleid){
		    var zTree = $.fn.zTree.getZTreeObj("tree-div");
		    var nodes = zTree.getCheckedNodes(true);
		    var selectnodes=",";
		    for(i=0;i<nodes.length;i++){
		       selectnodes+= nodes[i].id+",";
		    }
		    rc.ajax("<c:url value='/sys/role/saveroleperm'/>", {roleid:roleid,selectnodes:selectnodes},function (response) {
		    	alert(response.message);
		    	if(response.success){
		    		sys_role_treeinit();
				}
			});  
	   }else{
		   layer.alert('请先选择一个角色');
	   }
	}
</script>
</body>
</html>