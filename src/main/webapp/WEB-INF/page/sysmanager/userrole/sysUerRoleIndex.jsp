<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户角色授权管理</title>
<!-- css头文件  -->
<rc:csshead />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>机构用户树</h5>
				</div>
				<div class="ibox-content">
					<div id="tree-div" class="ztree" style="overflow: auto; height: 750px; "></div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="row">
			    <div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>机构信息</h5>
						</div>
						<div class="ibox-content" >
							<form  class="form-horizontal" >
								<div class="form-group">
									<label class="col-sm-1 control-label">机构名称</label>
									<div class="col-sm-3">
										<rc:textedit property="name" readonly="true" />
									</div>
									<label class="col-sm-1 control-label">机构描述</label>
									<div class="col-sm-7">
										<rc:textedit property="description" readonly="true" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>用户列表</h5>
						</div>
						<!-- 模型 -->
			            <script id="tpl" type="text/x-handlebars-template" >
                            {{#equals isgrant 'false' }}			                
				                 <a class="btn btn-info" onclick="grant('{{userid}}')">角色授权</a>
			                {{/equals}} 
			                {{#equals isgrant 'true'}}
			                     <a class="btn btn-danger" onclick="revoke('{{userid}}')">取消授权</a>
			                {{/equals}} 
			            </script>
						<div class="ibox-content" >
								<table class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
										    <th>是否已经授权</th>
											<th>用户编号</th>
											<th>用户名</th>
											<th>姓名</th>
											<th>角色授权</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
						</div>
					</div>
				</div>
			</div>
		</div>
<rc:jsfooter />
<script type="text/javascript">
//树配置
var setting = {
	  check: {
		enable: false
	  },
	  data: {
		simpleData: {
			enable: true
		}
	  },
	  callback: {
			onClick: onClick
		},
	  async: {
		 enable: true,
		 url: "<c:url value='/sys/userrole/treedata'/>",
		 autoParam:["id"]
	  }
};

var datatable;
//页面模型数据准备
var options={
	//列模型	
	columns:[
			 { 
				"data": "isgrant",
				"visible": false 
			 },
	         { 
	        	"data": "userid",
	        	"visible": false 
	         },
	         { 
	        	 "data": "username" 
	         },
	         { 
	        	 "data": "cnname"
	         },
	         {   
	        	 "data": null,
	        	 "render": function ( data, type, full, meta ) {
	                 var tpl = $("#tpl").html();  
	                 //预编译模板  
	                 var template = Handlebars.compile(tpl);  
	                 return template(data);
            } 
	    }
	],
    //表格jquery selector
	datatable_selector:'.dataTables-example',
	url: "<c:url value='/sys/userrole/getUserListdatabyid'/>",
	param:{groupid:'G001'}
};
$(function() {
	$.fn.zTree.init($("#tree-div"), setting);
	datatable=rc.tableinit(options);
})


//点击事件
function onClick(event, treeId, treeNode, clickFlag) {
	//机构信息查询
	rc.ajaxQuery("<c:url value='/sys/userrole/getgroupdatabyid/'/>"+treeNode.id);
	query(treeNode.id);
}

//查询
function query(groupid){
	//人员信息查询
	options.param={groupid:groupid};
	datatable.ajax.reload();
}
</script>
</body>
</html>