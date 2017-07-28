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
					<h5>��ɫ�б���</h5>
					<div class="ibox-tools">
						<a onclick="addnewrole()" class="btn btn-primary btn-xs">������ɫ</a>
					</div>
				</div>
				<!-- ģ�� tpl  -->
	            <script id="tpl" type="text/x-handlebars-template" >
                    <!--
                    <a class="btn btn-info" onclick="sys_role_editrole('{{roleid}}')" >�༭</a> 
                    -->
	                <a class="btn btn-danger" onclick="sys_role_deleterole('{{roleid}}')" >ɾ��</a> 
                </script>
				<div class="ibox-content">
					<table id="roletable" data-url="<c:url value='/sys/role/querylist'/>">
						<thead>
						    <tr>
						        <th data-formatter="sys_role_indexFormatter">���</th>
			                    <th data-field="name" >��ɫ����</th>
			                    <th data-field="code" >��ɫ����</th>
			                    <th data-formatter="sys_role_opFormatter">����</th>
						    </tr>
				        </thead>
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
						<div class="ibox-content" id="role_edit_div">
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
									<a class="btn btn-primary " onclick="sys_role_saveRoleData()">����</a>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>��ɫ��Ȩ</h5>
							<div class="ibox-tools">
							</div>
						</div>
						<div class="ibox-content">
							<div id="tree-div" class="ztree" style="overflow: auto; height: 300px; width: 250px;"></div>
							
							<div class="hr-line-dashed"></div>
							<div class="form-group" style="text-align: right;">
								<button id="btn_role_perm" class="btn btn-primary " onclick="sys_role_saveRolePermData()">����</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<rc:jsfooter />
<script type="text/javascript">
   //��ʼ��
   $(function(){
	    $('#roletable').inittable();
   	    //��ɫ�༭
   		rc.validAndAjaxSubmit($("#myform"),sys_role_callback);
   	    //Ȩ��������
    	sys_role_treeinit();
   });
 //�û������� 
   $('#roletable').on('click-row.bs.table', function (e, row, $element) {
      	rc.evaluation(row);
      	sys_role_editrole(row.roleid);
   }); 
   
   //��ɫ-Ȩ��������
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
   		 otherParam: {"id":"0"}
   	  }
   };
   
   //�ص�����
   function sys_role_callback(response){
	  if(response.success){
       	  alert(response.message);
	  }
	  else{
		  alert(response.message);
	  }
   }
   
   //format����
   function sys_role_opFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//Ԥ����ģ��  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
   }
 
   function sys_role_indexFormatter(value, row, index) {
       return index+1;
   }
   
    //����ҳ��������Ϣ
   function sys_role_saveRoleData(){
      $('#myform').submit();
   }

   
   //��ɫ�༭
   function sys_role_editrole(roleid){
	   //rc.ajaxQuery("<c:url value='/sys/role/getRoleData/'/>"+roleid);
	   var otherParam= { 'id':roleid }
	   sys_role_setting.async.otherParam=otherParam;
	   sys_role_treeinit();
   }
   
   //����Ȩ��
   function sys_role_addnewrole(){
   	   //�ұ߱༭�������
	   role_edit_div_clean();
   }
   
   function sys_role_role_edit_div_clean(){
	   rc.clean($('#role_edit_div'));
   }
   
   //ɾ����ɫ
   function sys_role_deleterole(roleid){
   	  if(roleid){
   		layer.confirm('ȷ��ɾ��Ҫ�˽�ɫ��', function(index){
   			var url= "<c:url value='/sys/role/deleteRoleDataById/'/>"+roleid;
   			rc.ajax(url, null,function (response) {
   				if(response.success){
   					$('#roletable').refreshtable();
   					role_edit_div_clean();
   				}else{
   					alert(response.message);
   				}
   			});
   		});
   	  }else{
   		layer.alert('����ѡ��һ����Ҫɾ����Ȩ�޽ڵ�');
   	  }
   }
   
   //�����ܻ�
   function sys_role_treeinit(){
	  $.fn.zTree.init($("#tree-div"), sys_role_setting);
	  var zTree = $.fn.zTree.getZTreeObj("tree-div");
	  zTree.expandAll(true)
   }
   
   //�����ɫ-Ȩ������
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
		   layer.alert('����ѡ��һ����ɫ');
	   }
	}
</script>
</body>
</html>