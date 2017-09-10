<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>权限管理</title>
<!-- css头文件  -->
<rc:csshead/>
<style type="text/css">

div#rDetailMenu {
	position:absolute; 
	visibility:hidden; top:0;text-align: left;padding: 2px;
}

</style>
</head>
<body class="gray-bg">
<div >
       <div class="col-sm-3">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>权限树区</h5>
					<div class="ibox-tools">
					    <button  id="tree_expand"  class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>展开</button>
					    <!--  
                        <a onclick="sys_perm_addnewperm()" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>新增</a>
                        <a onclick="sys_perm_deleteperm()" class="btn btn-danger btn-xs"><i class="fa fa-remove"></i>删除</a>
                        -->
                    </div>
				</div>
				<div class="ibox-content">
					<ul id="tree-div" class="ztree" style="overflow: auto; height: 670px; "></<ul>
				</div>
			</div>
		</div>

		<div class="col-sm-9">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>权限编辑区</h5>
				</div>
				<div class="ibox-content">
				<form action="<c:url value='/sys/perm/saveorupdate'/>"  class="form-horizontal" method="post" id="myform">
                       <rc:hidden property="permissionid"/>
                       <rc:hidden property="parentid" />
                       <rc:hidden property="optype" />
                       <div class="form-group">
                            <rc:textedit property="parentname" cols="2,8" label="上级权限名称 " readonly="true"/>
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:textedit property="code"  required="true"  cols="2,8" label="权限编码 " validate="{required:true,messages:{required:'请输入权限编码'}}"/>
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:textedit property="name"  required="true"  cols="2,8" label="权限名称" validate="{required:true,messages:{required:'请输入权限名称'}}"/>
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                           <rc:select codetype="PERMTYPE" property="type"  label="权限类型" required="true"  cols="2,8" validate="{required:true,messages:{required:'请选择权限类型'}}"/> 
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:textedit property="url"  required="true"  value="#" cols="2,8" label="权限地址"  validate="{required:true,messages:{required:'权限地址不能为空'}}" />
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:textedit property="sortnum"  required="true"  cols="2,8" datamask="99" label="排序号" validate="{required:true,messages:{required:'排序号不能为空'}}"/>
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:select label="是否有效" property="enabled" required="true"  cols="2,8" codetype="AAE100" validate="{required:true,messages:{required:'是否有效不能为空'}}"/>
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:textedit property="describe" cols="2,8" label="权限描述" />
                       </div>
                       <div class="hr-line-dashed"></div>
                       <div class="form-group">
                            <rc:textedit property="iconcss" cols="2,8" label="图标样式" readonly="true" onclick="open_font_css_iframe()" />
                       </div>
                      
                       <div class="hr-line-dashed"></div>
                    <div class="form-group" style="text-align: right;" >
                     <div class="col-sm-10 col-xs-10">
                         <a class="btn btn-primary "  onclick="sys_perm_savePermData()"><i class="fa fa-save"></i>&nbsp;保存</a>
                     </div>
                    </div>
                 </form>
			</div>
		</div>		
</div>
<div id="rDetailMenu">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
	    <li id="m_detail_add" onclick="addDetailTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-plus"></i>&nbsp;新增权限</a></li>
	    <li id="m_detail_sub_add" onclick="addDetailSubTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-chevron-down"></i>  <i class="fa fa-plus"></i>&nbsp;新增下级权限</a></li>
	    <li id="m_detail_edit" onclick="editDetailTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-edit"></i>&nbsp;修改权限</a></li>
	    <li id="m_detail_del" onclick="removeDetailTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-remove"></i>&nbsp;删除权限</a></li>
	</ul>
</div>     

<rc:jsfooter />
<script type="text/javascript">
var zDetailTree, rDetailMenu;
var current_detail_selectedNodes=null;
$(function() {
	//验证 ajax
	rc.validAndAjaxSubmit($("#myform"),sys_perm_callback);
	rDetailMenu = $("#rDetailMenu");
	$("#tree-div").bind("scroll", onTreeDetailDivScroll);
	sys_perm_treeinit();
})

//回调函数
function sys_perm_callback(response){
	if(response.success){
       	//sys_perm_treeinit();
       	treenode_detail_edit_callback(response.message);
	}
	else{
		alert(response.message);
	}
}

$('#tree_expand').on('click',
    function(){
       if($(this).html()=='<i class="fa fa-plus"></i>展开'){
    	    var treeObj = $.fn.zTree.getZTreeObj("tree-div");
	    	treeObj.expandAll(true);
	    	$(this).html('<i class="fa fa-minus"></i>收缩');
       }else{
    		var treeObj = $.fn.zTree.getZTreeObj("tree-div");
	    	treeObj.expandAll(false);
	    	$(this).html('<i class="fa fa-plus"></i>展开</a>');
       }
    }
);
function sys_perm_treeinit(){
	$.fn.zTree.init($("#tree-div"), sys_perm_setting);
	zDetailTree = $.fn.zTree.getZTreeObj("tree-div");
	zDetailTree.expandAll(true);
}
function canPrev(treeId, nodes, targetNode) {
	return !targetNode.isParent;
}

function canNext(treeId, nodes, targetNode) {
	return !targetNode.isParent;
}

//树配置
var sys_perm_setting = {
	edit: {
		 drag:{
			prev: true,
			next: true,
			inner:true
		 },
		 enable: true,
		 showRemoveBtn: false,
		 showRenameBtn: false
	},	
	view: {
       showLine: true
	},
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
			pIdKey: "pid"
		}
	},
	callback: {
		onClick: onClick,
		onRightClick: onRightClick,
		beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		onDrag:zTreeOnDrag,
		onDrop:zTreeOnDrop
	},
	async: {
		enable: true,
		url: "<c:url value='/sys/perm/treedata'/> ",
		autoParam:["id"]
	}
};

function beforeDrag(treeId, treeNodes) {
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	/* if(targetNode.id!='0'){ */
		return targetNode ? targetNode.drop !== false : true;
	/* }else{
		layer.msg('不能移动到根结点之前');
		return false;
	}  */
}

function zTreeOnDrag(event, treeId, treeNodes) {
};

//移动
function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
     //for(i=0;i<treeNodes.length;i++){
    	 //当前节点+目标结点
    	 //console.log(treeNodes[0].id+"_"+targetNode.id)
    	 if(targetNode){
    		 var url= "<c:url value='/sys/perm/moveNode/'/>"+treeNodes[0].id+"_"+targetNode.id;
    		 rc.ajax(url, null,function (response) {
   				if(response.success){
   					//alert(response.message);
   				}else{
   					alert(response.message);
   				}
   			});
    	 }else{
    		 return false;
    	 }
     //}
};

/**
 * 右键编辑
 */
function onRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zDetailTree.cancelSelectedNode();
		showRDetailMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zDetailTree.selectNode(treeNode);
		showRDetailMenu("node", event.clientX, event.clientY);
	}
}

/**
 * 显示右键菜单
 */
function showRDetailMenu(type, x, y) {
	$("#rDetailMenu ul").show();
	if (type=="root") {
		$("#m_detail_sub_add").hide();
		$("#m_detail_edit").hide();
		$("#m_detail_del").hide();
	} else {
		$("#m_detail_sub_add").show();
		$("#m_detail_edit").show();
		$("#m_detail_del").show();
	}

    y += document.body.scrollTop;
    x += document.body.scrollLeft;
    rDetailMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
}
/**
 * 隐藏右键菜单
 */
function hideRDetailMenu() {
	if (rDetailMenu) rDetailMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event){
	if (!(event.target.id == "rDetailMenu" || $(event.target).parents("#rDetailMenu").length>0)) {
		rDetailMenu.css({"visibility" : "hidden"});
	}
}

function onTreeDetailDivScroll(event){
	rDetailMenu.css({"visibility" : "hidden"});
}

function onClick(event, treeId, treeNode, clickFlag) {
	current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
	$('#optype').val('2');//1 新增结点  2更新结点
	rc.ajaxQuery("<c:url value='/sys/perm/getPermData/'/>"+treeNode.id);
}


//保存页面配置信息
function sys_perm_savePermData(){
   $('#myform').submit();
}
//新增权限
function sys_perm_addnewperm(){
	var id=$('#permissionid').val()||'0';
	var name=$('#name').val()||'权限头结点';
	rc.clean();
	$('#parentid').val(id);
	$('#parentname').val(name);
	$('#url').val('#');
}

function sys_right_menu_perm_addnewperm(id,name){
	rc.clean();
	$('#parentid').val(id);
	$('#parentname').val(name);
	$('#url').val('#');
	$('#optype').val('1');//1 新增结点  2更新结点
}
/**
 * 在根结点下增加代码价值
 */
function addDetailTreeNode() {
	hideRDetailMenu();
	current_detail_selectedNodes=null;
	sys_right_menu_perm_addnewperm('0','头结点');
}

/**
 * 在node结点下增加子权限
 */
function addDetailSubTreeNode() {
	hideRDetailMenu();
	//zDetailTree.addNodes(null, newNode);
	current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
	sys_right_menu_perm_addnewperm(current_detail_selectedNodes.id,current_detail_selectedNodes.name);
}

function editDetailTreeNode() {
	hideRDetailMenu();
	if (zDetailTree.getSelectedNodes()[0]) {
		//newNode.checked = zDetailTree.getSelectedNodes()[0].checked;
		current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
		//调用编辑弹出页面
		rc.ajaxQuery("<c:url value='/sys/perm/getPermData/'/>"+current_detail_selectedNodes.id);
		$('#optype').val('2');//1 新增结点  2更新结点
		//zDetailTree.addNodes(zDetailTree.getSelectedNodes()[0], newNode);
	} 
}

/**
 * 编辑成功后回调函数
 */
function treenode_detail_edit_callback(permissionid){
	//新增
	if($('#optype').val()=='1'){
		var new_perm_nodes={};
		new_perm_nodes.id=permissionid;
		new_perm_nodes.name=$('#name').val();
		zDetailTree.addNodes(current_detail_selectedNodes, new_perm_nodes);
	}
	//修改
	else{
		current_detail_selectedNodes.id=permissionid;
		current_detail_selectedNodes.name=$('#name').val();
		zDetailTree.updateNode(current_detail_selectedNodes);
	}
	rc.clean();
}


/**
 * 删除代码明细结点
 */
function removeDetailTreeNode() {
	hideRDetailMenu();
	var nodes = zDetailTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		console.log(nodes);
		if (nodes[0].children && nodes[0].children.length > 0) {
			layer.alert('当前权限存在子权限,不允许删除');
		} else {
			current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
			layer.confirm('确定删除要此权限,此功能原则上只有开发人员才可以操作,删除后系统相关业务程序代码需要同步更新', function(index){
	   			var url= "<c:url value='/sys/perm/deletePermDataById/'/>"+current_detail_selectedNodes.id;
	   			rc.ajax(url, null,function (response) {
	   				if(response.success){
	   					zDetailTree.removeNode(current_detail_selectedNodes);
	   				}else{
	   					alert(response.message);
	   				}
	   			});
	   		});
		}
	}
}

//删除权限
function sys_perm_deleteperm(){
	var id=$('#id').val();
	if(id){
		layer.confirm('确定删除要此权限吗？', function(index){
			var url= "<c:url value='/sys/perm/deletePermDataById/'/>"+id;
			rc.ajax(url, null,function (response) {
				if(response.success){
					sys_perm_treeinit();
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

//查看
function open_font_css_iframe(){
	layer.open({
   		  type: 2,
   		  title: '图标选择页面',
   		  shadeClose: false,
   		  maxmin:true,
   		  shade: 0.8,
   		  area: ['60%', '90%'],
   		  content: "<c:url value='/resource/hplus/fontawesome.html'/>" //iframe的url
		});
}

function font_css_choose(font_css_class_name){
	$('#iconcss').val(font_css_class_name);
	layer.close(layer.index);
}
</script>
</body>
</html>