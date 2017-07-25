<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>��ɫ����</title>
<!-- cssͷ�ļ�  -->
<rc:csshead />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>��ɫ�б�����</h5>
					<div class="ibox-tools">
						<a onclick="addnewrole()" class="btn btn-primary btn-xs">������ɫ</a>
					</div>
				</div>
				<!-- ģ�� tpl  -->
	            <script id="tpl" type="text/x-handlebars-template" >
                    <a class="btn btn-info" onclick="editrole('{{roleid}}')" >�༭</a> 
	                <a class="btn btn-danger" onclick="deleterole('{{roleid}}')" >ɾ��</a> 
                </script>
				<div class="ibox-content">
					<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>���</th>
								<th>��ɫ����</th>
								<th>��ɫ����</th>
								<th>����</th>
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
							<h5>��ɫ�༭��</h5>
						</div>
						<div class="ibox-content">
							<form action="<c:url value='/sys/role/saveorupdate'/>" class="form-horizontal" method="post" id="myform">
								<rc:hidden property="roleid"/>
								<div class="form-group">
									<label class="col-sm-2 control-label">��ɫ���� <span
										class="require">*<span></label>
									<div class="col-sm-10">
										<rc:textedit property="code" validate="{required:true,messages:{required:'�������ɫ����'}}" />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">��ɫ���� <span
										class="require">*<span></label>
									<div class="col-sm-10">
										<rc:textedit property="name"  validate="{required:true,messages:{required:'�������ɫ����'}}" />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">��ɫ���� <span
										class="require">*<span></label>
									<div class="col-sm-10">
										<rc:textedit property="describe" validate="{required:true,messages:{required:'�������ɫ����'}}" />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group" style="text-align: right;">
									<a class="btn btn-primary " onclick="saveRoleData()">����</a>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>��ɫ��Ȩ</h5>
						</div>
						<div class="ibox-content">
							<div id="tree-div" class="ztree" style="overflow: auto; height: 300px; width: 250px;"></div>
							<div class="hr-line-dashed"></div>
							<div class="form-group" style="text-align: right;">
								<button id="btn_role_perm" disabled="disabled" class="btn btn-primary " onclick="saveRoleData()">����</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<rc:jsfooter />
<script type="text/javascript">
   var datatable;
   //ҳ��ģ������׼��
   var options={
   	//��ģ��	
   	columns:[
 	         { "data": "roleid" },
 	         { "data": "name" },
 	         { "data": "code" },
 	         { "data": null }
 	],
 	     //���Զ��� 
    columnDefs:[ {
       "targets": 3,
       "render": function ( data, type, full, meta ) {
          var tpl = $("#tpl").html();  
          //Ԥ����ģ��  
          var template = Handlebars.compile(tpl);  
          return template(data);
       }
    },
    {
      "targets": [0],
      "visible": false
     }
    ],
    //���jquery selector
	datatable_selector:'.dataTables-example',
	url: "<c:url value='/sys/role/querylist'/>"
   };
   //��ʼ��
   $(function(){
   	    datatable=rc.tableinit(options);
   	    //��ɫ�༭
   		rc.validAndAjaxSubmit($("#myform"),callback);
   });
   
    //����ҳ��������Ϣ
   function saveRoleData(){
      $('#myform').submit();
   }

   //�ص�����
   function callback(response){
	  if(response.success){
       	  alert(response.message);
       	  datatable.ajax.reload();
	  }
	  else{
		  alert(response.message);
	  }
   }
   
   //��ɫ�༭
   function editrole(roleid){
	   rc.ajaxQuery("<c:url value='/sys/role/getRoleData/'/>"+roleid);
   }
   
   //����Ȩ��
   function addnewrole(){
   	   rc.clean();
   }
   
   //ɾ����ɫ
   function deleterole(roleid){
   	  if(roleid){
   		layer.confirm('ȷ��ɾ��Ҫ�˽�ɫ��', function(index){
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
   		layer.alert('����ѡ��һ����Ҫɾ����Ȩ�޽ڵ�');
   	  }
   }
   
   
   //��ɫ-Ȩ��������
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