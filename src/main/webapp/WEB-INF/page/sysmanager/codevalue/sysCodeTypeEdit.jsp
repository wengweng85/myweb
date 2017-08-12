<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>系统管理-代码管理</title>
<!-- css头文件  -->
<rc:csshead />
</head>
<body class="gray-bg">
    <div class="col-sm-12">
		<div id="tree-code-type-detail-div"	 class="ztree"	style="overflow:auto; height: 700px; "></div>
	</div>
</div>
<rc:jsfooter />
<script type="text/javascript">
$(function() {
	var code_type='${codetype.code_type}';
	var code_root_value='${codetype.code_root_value}';
	var otherParam= { 'code_type':code_type,'code_root_value':code_root_value }
	sys_code_type_setting_detail.async.otherParam=otherParam;
	$.fn.zTree.init($("#tree-code-type-detail-div"), sys_code_type_setting_detail);
})


//code_type_detail树配置
var sys_code_type_setting_detail = {
	view: {
		nameIsHTML: true
	},
	edit: {
			enable: true,
			showRenameBtn :false,
			removeTitle :'删除代码'
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
		onClick: onCodeValueClick,
		beforeRemove: beforeRemove,
		onRemove: onRemove
	},
	async: {
		enable: true,
		url: "<c:url value='/codetype/codevalue_treedata'/>",
		autoParam:["id"]
	}
};
/**
 * code_type_detail  onclick 
 */
function onCodeValueClick(event, treeId, treeNode, clickFlag) {
}


//删除前确认
function beforeRemove(treeId, treeNode) {
    return confirm("请确定此代码【" + treeNode.name + "】是否已经被使用,删除后业务中对应的代码将关联失败,确认删除?");
}

//删除
function onRemove(e, treeId, treeNode) {
    radow.doEvent('delete_code_value',treeNode.code_value_seq);	  
}
</script>
</body>
</html>