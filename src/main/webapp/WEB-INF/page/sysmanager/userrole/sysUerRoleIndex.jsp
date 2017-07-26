<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�û���ɫ��Ȩ����</title>
<!-- cssͷ�ļ�  -->
<rc:csshead />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>�����û���</h5>
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
							<h5>������Ϣ</h5>
						</div>
						<div class="ibox-content" >
							<form  class="form-horizontal" >
								<div class="form-group">
									<label class="col-sm-1 control-label">��������</label>
									<div class="col-sm-3">
										<rc:textedit property="name" readonly="true" />
									</div>
									<label class="col-sm-1 control-label">��������</label>
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
							<h5>�û��б�</h5>
						</div>
						<!-- ģ�� -->
			            <script id="tpl" type="text/x-handlebars-template" >
                            {{#equals isgrant 'false' }}			                
				                 <a class="btn btn-info" onclick="grant('{{userid}}')">��ɫ��Ȩ</a>
			                {{/equals}} 
			                {{#equals isgrant 'true'}}
			                     <a class="btn btn-danger" onclick="revoke('{{userid}}')">ȡ����Ȩ</a>
			                {{/equals}} 
			            </script>
						<div class="ibox-content" >
								<table class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
										    <th>�Ƿ��Ѿ���Ȩ</th>
											<th>�û����</th>
											<th>�û���</th>
											<th>����</th>
											<th>��ɫ��Ȩ</th>
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
//������
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
//ҳ��ģ������׼��
var options={
	//��ģ��	
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
	                 //Ԥ����ģ��  
	                 var template = Handlebars.compile(tpl);  
	                 return template(data);
            } 
	    }
	],
    //���jquery selector
	datatable_selector:'.dataTables-example',
	url: "<c:url value='/sys/userrole/getUserListdatabyid'/>",
	param:{groupid:'G001'}
};
$(function() {
	$.fn.zTree.init($("#tree-div"), setting);
	datatable=rc.tableinit(options);
})


//����¼�
function onClick(event, treeId, treeNode, clickFlag) {
	//������Ϣ��ѯ
	rc.ajaxQuery("<c:url value='/sys/userrole/getgroupdatabyid/'/>"+treeNode.id);
	query(treeNode.id);
}

//��ѯ
function query(groupid){
	//��Ա��Ϣ��ѯ
	options.param={groupid:groupid};
	datatable.ajax.reload();
}
</script>
</body>
</html>