<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ȩ�޹���</title>
<!-- cssͷ�ļ�  -->
<rc:csshead/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
       <div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Ȩ���б�����</h5>
					<div class="ibox-tools">
                        <a onclick="addnewperm()" class="btn btn-primary btn-xs">����Ȩ��</a>
                        <a onclick="deleteperm()" class="btn btn-danger btn-xs">ɾ��Ȩ��</a>
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
					<h5>Ȩ�ޱ༭��</h5>
				</div>
				<div class="ibox-content">
				<form action="<c:url value='/sys/perm/saveorupdate'/>"  class="form-horizontal" method="post" id="myform">
                           <rc:hidden property="permissionid"/>
                           <rc:hidden property="parentid" />
                           <div class="form-group">
                               <label class="col-sm-2 control-label">�ϼ�Ȩ������ </label>
                               <div class="col-sm-10">
                                    <rc:textedit property="parentname" readonly="true"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ�ޱ��� <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="code"  validate="{required:true,messages:{required:'������Ȩ�ޱ���'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ������  <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="name"  validate="{required:true,messages:{required:'������Ȩ������'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ������ <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="describe"  validate="{required:true,messages:{required:'������Ȩ������'}}"/>
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ������ <span class="require">*<span></label>
                               <div class="col-sm-10">
                                   <rc:select codetype="PERMTYPE" property="type"   validate="{required:true,messages:{required:'��ѡ��Ȩ������'}}"/> 
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ�޵�ַurl</label>
                               <div class="col-sm-10">
                                    <rc:textedit property="url"  />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">�����<span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <rc:textedit property="sortnum"  />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">��������<span class="require">*<span></label>
                               <div class="col-sm-10">
                                   <rc:date property="updatetime" validate="{required:true,messages:{required:'��ѡ���������'}}" />
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
	                       <div class="form-group" style="text-align: right;">
	                            <a class="btn btn-primary " onclick="savePermData()">����</a>
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
	//��֤ ajax
	rc.validAndAjaxSubmit($("#myform"),callback);
	treeinit();
})
//�ص�����
function callback(response){
	if(response.success){
       	alert(response.message);
       	treeinit();
	}
	else{
		alert(response.message);
	}
}
//������
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

//����ҳ��������Ϣ
function savePermData(){
   $('#myform').submit();
}
//����Ȩ��
function addnewperm(){
	var permissionid=$('#permissionid').val()||'0';
	var name=$('#name').val()||'Ȩ��ͷ���';
	rc.clean();
	$('#parentid').val(permissionid);
	$('#parentname').val(name);
}
//ɾ��Ȩ��
function deleteperm(){
	var permissionid=$('#permissionid').val();
	if(permissionid){
		layer.confirm('ȷ��ɾ��Ҫ��Ȩ����', function(index){
			var url= "<c:url value='/sys/perm/deletePermDataById/'/>"+permissionid;
			rc.ajax(url, null,function (response) {
				if(response.success){
					treeinit();
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
</script>
</body>
</html>