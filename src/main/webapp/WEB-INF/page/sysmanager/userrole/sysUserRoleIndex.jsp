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
		<div class="col-sm-5">
			<div class="row">
			    <div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>������Ϣ</h5>
						</div>
						<div class="ibox-content" >
							<form class="form-horizontal" id="group_form">
								<!-- �������� -->
								<rc:hidden property="groupid"/>
								<div class="form-group">
									<label class="col-sm-2 control-label">��������</label>
									<div class="col-sm-3">
										<rc:textedit property="name" readonly="true" />
									</div>
									<label class="col-sm-2 control-label">��������</label>
									<div class="col-sm-5">
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
				                 <a class="btn btn-danger">δ��Ȩ</a>
			                {{/equals}} 
			                {{#equals isgrant 'true'}}
			                     <a class="btn btn-info">����Ȩ</a>
			                {{/equals}} 
			            </script>
			            
			            <script id="tpl_op" type="text/x-handlebars-template" >
				             <a class="btn btn-info" onclick="sys_user_role_grant('{{userid}}')">��ɫ��Ȩ</a>
			            </script>
						<div class="ibox-content" >
							<table id="usertable" 
							data-url="<c:url value='/sys/userrole/getUserListDataByid'/>"
							data-click-to-select="true"
                            data-single-select="true"
                            data-page-size="5"
							>
								<thead>
								    <tr>
								        <th data-checkbox="true" ></th>
								        <th data-formatter="sys_user_role_indexFormatter">���</th>
					                    <th data-field="username">�û���</th>
					                    <th data-field="cnname">����</th>
					                    <th data-formatter="sys_user_role_grantStatusFormatter">�Ƿ�����Ȩ</th>
					                    <!--  
					                    <th data-formatter="sys_user_role_opFormatter">��Ȩ</th>
					                    -->
								    </tr>
						        </thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="col-sm-4">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>�û���Ϣ</h5>
					</div>
					<div class="ibox-content" >
						<form class="form-horizontal" id="group_form">
							<!-- �������� -->
							<rc:hidden property="userid"/>
							<div class="form-group">
								<label class="col-sm-2 control-label">�û���</label>
								<div class="col-sm-4">
									<rc:textedit property="username" readonly="true" />
								</div>
								<label class="col-sm-2 control-label">����</label>
								<div class="col-sm-4">
									<rc:textedit property="cnname" readonly="true" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>��ɫ�б�</h5>
				</div>
				<div class="ibox-content" >
					<table id="roletable" data-url="<c:url value='/sys/userrole/getRoleByUserId'/>"
					data-page-size="20"
					>
						<thead>
						    <tr>
						        <th data-checkbox="true"  data-formatter="sys_user_role_checkedFormatter"></th>
						        <th data-formatter="sys_user_role_indexFormatter">���</th>
			                    <th data-field="name">��ɫ����</th>
			                    <th data-field="code">��ɫ����</th>
						    </tr>
				        </thead>
					</table>
					<div class="hr-line-dashed"></div>
					<div class="form-group" style="text-align: right;">
						<a  class="btn btn-primary " onclick="sys_user_role_batchAddUserRole()">����</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<rc:jsfooter />
<script type="text/javascript">
$(function() {
	 $.fn.zTree.init($("#tree-div"), sys_user_role_setting);
	 $('#usertable').inittable();
	 $('#roletable').inittable();
})
    
//�û������� 
$('#usertable').on('click-row.bs.table', function (e, row, $element) {
   	rc.evaluation(row);
   	sys_user_role_grant(row.userid);
});   
//������
var sys_user_role_setting = {
	  view: {
          dblClickExpand: false,
          showLine: true,
          selectedMulti: false
	  },	
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
//format
//״̬
function sys_user_role_grantStatusFormatter(value, row, index) {
	var tpl = $("#tpl").html();  
  	//Ԥ����ģ��  
  	var template = Handlebars.compile(tpl);  
  	return template(row);
}
//����
function sys_user_role_opFormatter(value, row, index) {
	var tpl = $("#tpl_op").html();  
  	//Ԥ����ģ��  
  	var template = Handlebars.compile(tpl);  
  	return template(row);
}

//�Ƿ�ѡ��
function sys_user_role_checkedFormatter(value,row,index){
   if (row.checked == 'true')
       return {
           checked : true//����ѡ��
       };
   return value;
}
function sys_user_role_indexFormatter(value, row, index) {
    return index+1;
}

//����¼�
function onClick(event, treeId, treeNode, clickFlag) {
	//������Ϣ��ѯ
	rc.ajaxQuery("<c:url value='/sys/userrole/getgroupdatabyid/'/>"+treeNode.id);
	sys_user_role_queryuser(treeNode.id);
}

//��ѯ
function sys_user_role_queryuser(groupid){
	var url="<c:url value='/sys/userrole/getUserListDataByid'/>"+'?groupid='+groupid;
	$('#usertable').refreshtable(url);
}

//��Աѡ�м��ؽ�ɫ
function sys_user_role_grant(userid){
	$('#userid').val(userid);
	var url="<c:url value='/sys/userrole/getRoleByUserId'/>"+'?userid='+userid;
	$('#roletable').refreshtable(url);
}


//���������û���ɫ
function sys_user_role_batchAddUserRole(){
	var userid=$('#userid').val();
	if(userid){
		var selections=$('#roletable').getAllTableSelections();
	    //ѡ�е�ֵ
	    var selectnodes='';
	    if(selections.length>0){
			   for(i=0;i<selections.length;i++){
	     	   var item=selections[i];
	     	   selectnodes+=item.roleid+',';
	        }
	 	   console.log(selectnodes);
		   rc.ajax("<c:url value='/sys/userrole/saveUserRole'/>", {userid:userid,selectnodes:selectnodes},function (response) {
		    	alert(response.message);
		    	$('#usertable').refreshtable();
		   }); 
	    }else{
	 	   layer.alert("������ѡ��һ����¼");                
			   return;
	    }
	}else{
		layer.alert('����ѡ��Ҫ���ӽ�ɫ���û�');
	}
}	 
</script>
</body>
</html>