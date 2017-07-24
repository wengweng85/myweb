<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.myweb.com/mywebtag" prefix="web"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ȩ���б�</title>
<tags:CommonCssHeadTag />
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
       <div class="col-sm-2">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Ȩ���б�����</h5>
				</div>
				<div class="ibox-content">
					<div id="tree-div" class="ztree"
						style="overflow: auto; height: 650px; width: 250px;"></div>
				</div>
			</div>
		</div>

		<div class="col-sm-5">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Ȩ�ޱ༭��</h5>
				</div>
				<div class="ibox-content">
				<form action="<c:url value='/drag/saveorupdate'/>"  class="form-horizontal" method="post" id="myform">
                           <div class="form-group">
                               <input type="hidden" id="permissionid" name="permissionid" class="form-control" >
                           </div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">�ϼ�Ȩ������ </label>
                               <div class="col-sm-10">
                                    <input type="text" id="parentname"  name="parentname" placeholder="�ϼ�Ȩ������"  class="form-control"  readonly="readonly"  validate="{required:true,messages:{required:'������Ȩ�ޱ���'}}">
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ�ޱ��� <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <input type="text" id="code"  name="code" placeholder="Ȩ�ޱ���" class="form-control"  readonly="readonly"  validate="{required:true,messages:{required:'������Ȩ�ޱ���'}}">
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ������  <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <input type="text" id="name"  name="name" placeholder="Ȩ������" class="form-control"  class="valid" validate="{required:true,messages:{required:'������Ȩ������'}}">
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ������ <span class="require">*<span></label>
                               <div class="col-sm-10">
                                    <input type="text" id="describe"  name="describe" placeholder="Ȩ������" class="form-control"  validate="{required:true,messages:{required:'������Ȩ������'}}">
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ������ <span class="require">*<span></label>
                               <div class="col-sm-10">
                                      <web:select codetype="PERMTYPE" id="type" name="type"  validate="{required:true,messages:{required:'��ѡ��Ȩ������'}}"/> 
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
                           <div class="form-group">
                               <label class="col-sm-2 control-label">Ȩ�޵�ַurl</label>
                               <div class="col-sm-10">
                                    <input type="text" id="url"  name="url" placeholder="Ȩ�޵�ַurl" class="form-control"  >
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           
	                       <div class="form-group" style="text-align: right;">
	                             <button class="btn btn-primary " onclick="savePage()">�޸�</button>
	                       </div>
                 </form>
                <div id="view"></div>
			    </div>
			</div>
		</div>		
</div>
<tags:CommonJsHeadTag/>
<script type="text/javascript">
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
	console.log(treeNode.id);
	rc.ajaxQuery("<c:url value='/sys/perm/getPermData/'/>"+treeNode.id);
}

//��ʼ�����ݼ���
$(document).ready(function(){
	$.fn.zTree.init($("#tree-div"), setting);
});
</script>
</body>
</html>