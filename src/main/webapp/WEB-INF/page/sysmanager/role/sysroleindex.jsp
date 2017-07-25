<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
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
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>角色列表树区</h5>
					<div class="ibox-tools">
						<a onclick="addnewrole()" class="btn btn-primary btn-xs">新增角色</a>
					</div>
				</div>
				<!-- 模型 tpl  -->
	            <script id="tpl" type="text/x-handlebars-template" >
                    <a class="btn btn-info" onclick="editrole('{{roleid}}')" >编辑</a> 
	                <a class="btn btn-danger" onclick="deleterole('{{roleid}}')" >删除</a> 
                </script>
				<div class="ibox-content">
					<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>角色名称</th>
								<th>角色编码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="col-sm-8">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>角色编辑区</h5>
						</div>
						<div class="ibox-content">
							<form action="<c:url value='/sys/role/saveorupdate'/>" class="form-horizontal" method="post" id="myform">
								<rc:hidden property="roleid"/>
								<div class="form-group">
									<label class="col-sm-2 control-label">角色编码 <span
										class="require">*<span></label>
									<div class="col-sm-10">
										<rc:textedit property="code" validate="{required:true,messages:{required:'请输入角色编码'}}" />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">角色名称 <span
										class="require">*<span></label>
									<div class="col-sm-10">
										<rc:textedit property="name"  validate="{required:true,messages:{required:'请输入角色名称'}}" />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">角色描述 <span
										class="require">*<span></label>
									<div class="col-sm-10">
										<rc:textedit property="describe" validate="{required:true,messages:{required:'请输入角色描述'}}" />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group" style="text-align: right;">
									<a class="btn btn-primary " onclick="saveRoleData()">保存</a>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>角色授权</h5>
						</div>
						<div class="ibox-content">
							<div id="tree-div" class="ztree" style="overflow: auto; height: 300px; width: 250px;"></div>
							<div class="hr-line-dashed"></div>
							<div class="form-group" style="text-align: right;">
								<button id="btn_role_perm" disabled="disabled" class="btn btn-primary " onclick="saveRoleData()">保存</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<rc:jsfooter />
<script type="text/javascript">
   var datatable;
   //页面模型数据准备
   var options={
   	//列模型	
   	columns:[
 	         { "data": "roleid" },
 	         { "data": "name" },
 	         { "data": "code" },
 	         { "data": null }
 	],
 	     //列自定义 
    columnDefs:[ {
       "targets": 3,
       "render": function ( data, type, full, meta ) {
          var tpl = $("#tpl").html();  
          //预编译模板  
          var template = Handlebars.compile(tpl);  
          return template(data);
       }
    },
    {
      "targets": [0],
      "visible": false
     }
    ],
    //表格jquery selector
	datatable_selector:'.dataTables-example',
	url: "<c:url value='/sys/role/querylist'/>"
   };
   //初始化
   $(function(){
   	    datatable=rc.tableinit(options);
   	    //角色编辑
   		rc.validAndAjaxSubmit($("#myform"),callback);
   });
   
    //保存页面配置信息
   function saveRoleData(){
      $('#myform').submit();
   }

   //回调函数
   function callback(response){
	  if(response.success){
       	  alert(response.message);
       	  datatable.ajax.reload();
	  }
	  else{
		  alert(response.message);
	  }
   }
   
   //角色编辑
   function editrole(roleid){
	   rc.ajaxQuery("<c:url value='/sys/role/getRoleData/'/>"+roleid);
   }
   
   //新增权限
   function addnewrole(){
   	   rc.clean();
   }
   
   //删除角色
   function deleterole(roleid){
   	  if(roleid){
   		layer.confirm('确定删除要此角色吗？', function(index){
   			var url= "<c:url value='/sys/role/deleteRoleDataById/'/>"+roleid;
   			rc.ajax(url, null,function (response) {
   				if(response.success){
   					datatable.ajax.reload();
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
   
   
   //角色-权限树配置
   var setting = {
      check: {
		enable: true
	  },
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
   	  async: {
   		 enable: true,
   		 url: "<c:url value='/sys/perm/treedata'/> ",
   		 autoParam:["id"],
   	  }
   };
</script>
</body>
</html>