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
<style type="text/css">

div#rMenu {
	position:absolute; 
	visibility:hidden; top:0;text-align: left;padding: 2px;
}

div#rDetailMenu {
	position:absolute; 
	visibility:hidden; top:0;text-align: left;padding: 2px;
}

</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>代码类型树</h5>
					<div class="ibox-tools">
					    <a onclick="code_type_query_callback()" class="btn btn-primary btn-xs"><i class="fa fa-refresh"></i>&nbsp;刷新</a>
                        <a onclick="open_code_type_query()" class="btn btn-primary btn-xs"><i class="fa fa-search"></i>&nbsp;代码类型查询条件</a>
                        <rc:hidden property="q_code_type"/>
                        <rc:hidden property="q_type_name"/>
                    </div>
				</div>
				<div class="ibox-content">
					<div id="tree-code-type-div" class="ztree" style="overflow: auto; height: 700px; "></div>
				</div>
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
			    <div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5 id="code_detail_tree_title">代码明细树</h5>
							<div class="ibox-tools">
							    <a onclick="code_value_query_detail_callback()" class="btn btn-primary btn-xs"><i class="fa fa-refresh"></i>&nbsp;刷新</a>
		                        <a onclick="open_code_value_query()" class="btn btn-primary btn-xs"><i class="fa fa-search"></i>&nbsp;代码明细类型查询条件</a>
		                        <rc:hidden property="q_code_value"/>
                                <rc:hidden property="q_code_name"/>
		                    </div>
						</div>
						<div class="ibox-content" >
						    <rc:hidden property="code_type"/>
							<div id="tree-code-type-detail-div"	 class="ztree"	style="overflow:auto; height: 700px; "></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="rMenu">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
	    <li id="m_add" onclick="addTreeNode();"><a tabindex="-1" href="#"><i class="fa fa-plus"></i>&nbsp;新增代码</a></li>
	    <li id="m_edit" onclick="editTreeNode();"><a tabindex="-1" href="#"><i class="fa fa-edit"></i>&nbsp;修改代码</a></li>
	    <li id="m_del" onclick="removeTreeNode();"><a tabindex="-1" href="#"><i class="fa fa-remove"></i>&nbsp;删除代码</a></li>
	</ul>
</div>

<div id="rDetailMenu">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
	    <li id="m_detail_add" onclick="addDetailTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-plus"></i>&nbsp;新增代码值</a></li>
	    <li id="m_detail_sub_add" onclick="addDetailSubTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-chevron-down"></i>  <i class="fa fa-plus"></i>&nbsp;新增下级代码值</a></li>
	    <li id="m_detail_edit" onclick="editDetailTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-edit"></i>&nbsp;修改代码值</a></li>
	    <li id="m_detail_del" onclick="removeDetailTreeNode();"><a  tabindex="-1" href="#"><i class="fa fa-remove"></i>&nbsp;删除代码值</a></li>
	</ul>
</div>           
            
<rc:jsfooter />
<script type="text/javascript">
var zTree, rMenu,zDetailTree,rDetailMenu;
$(function() {
	 $.fn.zTree.init($("#tree-code-type-div"), sys_code_type_setting);
	 zTree = $.fn.zTree.getZTreeObj("tree-code-type-div");
	 rMenu = $("#rMenu");
	 rDetailMenu = $("#rDetailMenu");
	 $("#tree-code-type-div").bind("scroll", onTreeDivScroll);
	 $("#tree-code-type-detail-div").bind("scroll", onTreeDetailDivScroll);
	
})

//树配置
var sys_code_type_setting = {
	  edit: {
		 drag:{
			prev: true,
			next: true,
			inner:false
		 },
		 enable: true,
		 showRemoveBtn: false,
		 showRenameBtn: false
	  },
	  view: {
        dblClickExpand: false,
        showLine: true,
        selectedMulti: true
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
		onClick: onClick,
		onRightClick: onRightClick,
		onDblClick: zTreeOnDblClick,
		beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		onDrag:zTreeOnDrag,
		onDrop:zTreeOnDrop
	  },
	  async: {
		 enable: true,
		 url: "<c:url value='/codetype/codetype_treedata'/>",
		 autoParam:["id"],
		 otherParam:{"q_code_type":"", "q_type_name":"","code_type":"","code_root_value":""}
	  }
};


//code_type_detail(code_value_树配置
var sys_code_type_setting_detail = {
	view: {
		nameIsHTML: true
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
		onRightClick: onDetailRightClick,
		onDblClick: zDetailTreeOnDblClick
	},
	async: {
		enable: true,
		url: "<c:url value='/codetype/codevalue_treedata'/>",
		autoParam:["id"]
	}
};



/**
 * 点击code_type树,调用树结点查询对应的code_value值
 */
function onClick(event, treeId, treeNode, clickFlag) {
	//addtab(treeNode);
	var code_type=treeNode.id;
	var code_root_value=treeNode.code_root_value;
	var otherParam= {'code_type':code_type,'code_root_value':code_root_value };
	//设置树请求参数
	sys_code_type_setting_detail.async.otherParam=otherParam;
	//树初始化
	$.fn.zTree.init($("#tree-code-type-detail-div"), sys_code_type_setting_detail);
	zDetailTree = $.fn.zTree.getZTreeObj("tree-code-type-detail-div");
	//设置隐藏域
	$('#code_type').val(treeNode.id);
	$('#code_detail_tree_title').html('代码明细树-'+treeNode.name);
}

/**
 * 代码树点击弹出编辑页面
 */
function zTreeOnDblClick(event, treeId, treeNode){
	edit_code_type(treeNode.id);
}

function beforeDrag(treeId, treeNodes) {
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	return targetNode ? targetNode.drop !== false : true;
}

function zTreeOnDrag(event, treeId, treeNodes) {
};

function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
     //for(i=0;i<treeNodes.length;i++){
    	 //console.log(treeNodes[i].id+"_"+targetNode.id)
    	 if(targetNode){
    		 //radow.doEvent('moveNode',treeNodes[0].id+"_"+targetNode.id);
    	 }
     //}
};

/**
 * 右键编辑
 */
function onRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}


/**
 * 显示右键菜单
 */
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_edit").hide();
		$("#m_del").hide();
	} else {
		$("#m_edit").show();
		$("#m_del").show();
	}

    y += document.body.scrollTop;
    x += document.body.scrollLeft;
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
	
}
/**
 * 隐藏右键菜单
 */
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

/**
 * 在当前节点下增加子结点
 */
function addTreeNode() {
	hideRMenu();
	//zTree.addNodes(null, newNode);
	add_code_type();
}

//弹出编辑页面
function add_code_type(){
	layer.open({
	  type: 2,
	  title: '代码类型新增页面',
	  shadeClose: false,
	  maxmin:true,
	  shade: 0.8,
	  area: ['50%', '60%'],
	  content: "<c:url value='/codetype/toCodeTypeAdd'/>" //iframe的url
	});
}

function treenode_add_callback(codetype){
	codetype.id=codetype.code_type;
	codetype.name=codetype.type_name+'('+codetype.code_type+')'
	zTree.addNodes(null, codetype);
}

/**
 * 修改结点 
 */
var current_selectedNodes=null;
function editTreeNode() {
	hideRMenu();
	if (zTree.getSelectedNodes()[0]) {
		//newNode.checked = zTree.getSelectedNodes()[0].checked;
		current_selectedNodes=zTree.getSelectedNodes()[0];
		//调用编辑弹出页面
		edit_code_type(current_selectedNodes.id)
		//zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
	} 
}

//弹出编辑页面
function edit_code_type(id){
	layer.open({
	  type: 2,
	  title: '代码类型编辑页面',
	  shadeClose: false,
	  maxmin:true,
	  shade: 0.8,
	  area: ['50%', '60%'],
	  content: "<c:url value='/codetype/toCodeTypeEdit'/>/"+id //iframe的url
	});
}

/**
 * 编辑成功后回调函数
 */
function treenode_edit_callback(codetype){
	codetype.id=codetype.code_type;
	codetype.name=codetype.type_name+'('+codetype.code_type+')'
	current_selectedNodes.id=codetype.id;
	current_selectedNodes.name=codetype.name;
	current_selectedNodes.code_root_value=codetype.code_root_value;
	zTree.updateNode(current_selectedNodes);
}

/**
 * 删除代码类型结点
 */
function removeTreeNode() {
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		if (nodes[0].children && nodes[0].children.length > 0) {
			layer.alert('当前代码存在子代码结点,不允许删除');
		} else {
			current_selectedNodes=zTree.getSelectedNodes()[0];
			//
			layer.confirm('确定删除要此代码,此功能原则上只有开发人员才可以操作,删除后系统相关业务代码需要同步更新', function(index){
	   			var url= "<c:url value='/codetype/deleteCodeType/'/>"+current_selectedNodes.id;
	   			rc.ajax(url, null,function (response) {
	   				if(response.success){
	   					zTree.removeNode(current_selectedNodes);
	   				}else{
	   					alert(response.message);
	   				}
	   			});
	   		});
			
		}
	}
}

function addtab(treeNode){
    url=contextPath+"/codetype/toCodeValueTreePage/"+treeNode.id;
    var item = {'id':treeNode.id,'name':''+treeNode.name+'','url':url,'closable':true};
	closableTab.addTab(item);
}


/**
 * 右键编辑
 */
function onDetailRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zDetailTree.cancelSelectedNode();
		showRDetailMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zDetailTree.selectNode(treeNode);
		showRDetailMenu("node", event.clientX, event.clientY);
	}
}

/**
 * 代码树点击弹出代码明细编辑页面
 */
function zDetailTreeOnDblClick(event, treeId, treeNode){
	edit_detail_code_type(treeNode.code_seq);
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
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
	if (!(event.target.id == "rDetailMenu" || $(event.target).parents("#rDetailMenu").length>0)) {
		rDetailMenu.css({"visibility" : "hidden"});
	}
}

function onTreeDivScroll(event){
	rMenu.css({"visibility" : "hidden"});
}

function onTreeDetailDivScroll(event){
	rDetailMenu.css({"visibility" : "hidden"});
}

/**
 * 在根结点下增加代码价值
 */
function addDetailTreeNode() {
	hideRDetailMenu();
	//zDetailTree.addNodes(null, newNode);
	current_detail_selectedNodes=null;
	add_detail_code_type();
}

//弹出编辑页面
function add_detail_code_type(){
	if($('#code_type').val()){
		layer.open({
			  type: 2,
			  title: '代码值明细新增页面',
			  shadeClose: false,
			  maxmin:true,
			  shade: 0.8,
			  area: ['50%', '60%'],
			  content: "<c:url value='/codetype/toCodeTypeDetailAddFromRoot'/>/"+$('#code_type').val() //iframe的url
			});
	}else{
		layer.msg('请先选择代码类型');
	}
}

/**
 * 在node结点下增加子代码值
 */
function addDetailSubTreeNode() {
	hideRDetailMenu();
	//zDetailTree.addNodes(null, newNode);
	current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
	console.log(current_detail_selectedNodes);
	add_detail_sub_code_type(current_detail_selectedNodes.code_seq);
}

//弹出增加页面
function add_detail_sub_code_type(code_seq){
	layer.open({
		  type: 2,
		  title: '代码值明细新增页面',
		  shadeClose: false,
		  maxmin:true,
		  shade: 0.8,
		  area: ['50%', '60%'],
		  content: "<c:url value='/codetype/toCodeTypeDetailAddFromNode'/>/"+code_seq //iframe的url
	});
}
/**
 * 新增明细回调函数
 */
function treenode_detail_add_callback(codevalue){
	codevalue.id=codevalue.code_type;
	codevalue.name=codevalue.code_name+'('+codevalue.code_value+')';
	zDetailTree.addNodes(current_detail_selectedNodes, codevalue);
}

/**
 * 修改结点 
 */
var current_detail_selectedNodes=null;
function editDetailTreeNode() {
	hideRDetailMenu();
	if (zDetailTree.getSelectedNodes()[0]) {
		//newNode.checked = zDetailTree.getSelectedNodes()[0].checked;
		current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
		//调用编辑弹出页面
		edit_detail_code_type(current_detail_selectedNodes.code_seq)
		//zDetailTree.addNodes(zDetailTree.getSelectedNodes()[0], newNode);
	} 
}

//弹出编辑页面
function edit_detail_code_type(id){
	layer.open({
	  type: 2,
	  title: '代码值明细编辑页面',
	  shadeClose: false,
	  maxmin:true,
	  shade: 0.8,
	  area: ['50%', '60%'],
	  content: "<c:url value='/codetype/toCodeTypeDetailEdit'/>/"+id //iframe的url
	});
}

/**
 * 编辑成功后回调函数
 */
function treenode_detail_edit_callback(codevalue){
	codevalue.name=codevalue.code_name+'('+codevalue.code_value+')';
	current_detail_selectedNodes.code_seq=codevalue.code_seq;
	current_detail_selectedNodes.id=codevalue.code_value;
	current_detail_selectedNodes.name=codevalue.name;
	zDetailTree.updateNode(current_detail_selectedNodes);
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
			layer.alert('当前代码存在子结点,不允许删除');
		} else {
			current_detail_selectedNodes=zDetailTree.getSelectedNodes()[0];
			//
			layer.confirm('确定删除要此代码值,此功能原则上只有开发人员才可以操作,删除后系统相关业务程序代码需要同步更新', function(index){
	   			var url= "<c:url value='/codetype/deleteCodeValue/'/>"+current_detail_selectedNodes.code_seq;
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

/**
 * 代码类型查询条件弹出框
 */
function open_code_type_query(){
	layer.open({
	  type: 2,
	  title: '代码树查询条件',
	  shadeClose: false,
	  maxmin:false,
	  shade: 0.8,
	  offset: ['100px', '50px'],
	  area: ['45%', '40%'],
	  content:"<c:url value='/codetype/toCodeTypeQuery'/>"     //iframe的url
	});
}

/**
 * 查询回调函数
 */
function code_type_query_callback(){
	sys_code_type_setting.async.otherParam.q_code_type=$('#q_code_type').val();
	sys_code_type_setting.async.otherParam.q_type_name=$('#q_type_name').val();
	$.fn.zTree.init($("#tree-code-type-div"), sys_code_type_setting);
}
/**
 * 代码值查询条件弹出框
 */
function open_code_value_query(){
	if($('#code_type').val()){
		layer.open({
			  type: 2,
			  title: '代码值树查询条件',
			  shadeClose: false,
			  maxmin:false,
			  shade: 0.8,
			  area: ['45%', '40%'],
			  content:"<c:url value='/codetype/toCodeValueQuery'/>"     //iframe的url
			});
	}else{
		layer.msg('请先选择代码类型');
	}
}

/**
 * 查询回调函数
 */
function code_value_query_detail_callback(){
	sys_code_type_setting_detail.async.otherParam.q_code_value=$('#q_code_value').val();
	sys_code_type_setting_detail.async.otherParam.q_code_name=$('#q_code_name').val();
	$.fn.zTree.init($("#tree-code-type-detail-div"), sys_code_type_setting_detail);
}
</script>
</body>
</html>