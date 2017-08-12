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
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>代码类型树</h5>
				</div>
				<div class="ibox-content">
					<div id="tree-div" class="ztree" style="overflow: auto; height: 700px; "></div>
					
				</div>
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
			    <div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>代码明细树</h5>
						</div>
						<div class="ibox-content" >
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
	    <li id="m_add" onclick="addTreeNode();"><a tabindex="-1" href="#">增加节点</a></li>
	    <li id="m_add2" onclick="addTreeNode();"><a tabindex="-1" href="#">增加节点</a></li>
	    <li id="m_add3" onclick="addTreeNode();"><a tabindex="-1" href="#">增加节点</a></li>
	    <li id="m_add4" onclick="addTreeNode();"><a tabindex="-1" href="#">增加节点</a></li>
	    <li id="m_add5" onclick="addTreeNode();"><a tabindex="-1" href="#">增加节点</a></li>
	</ul>
</div>          
            
<rc:jsfooter />
<script type="text/javascript">
var zTree, rMenu;
$(function() {
	 $.fn.zTree.init($("#tree-div"), sys_code_type_setting);
	 zTree = $.fn.zTree.getZTreeObj("tree-div");
	 rMenu = $("#rMenu");
})

//树配置
var sys_code_type_setting = {
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
			onClick: onClick,
			onRightClick: OnRightClick
		},
	  async: {
		 enable: true,
		 url: "<c:url value='/codetype/codetype_treedata'/>",
		 autoParam:["id"]
	  }
};


//code_type_detail树配置
var sys_code_type_setting_detail = {
	view: {
		nameIsHTML: true
	},
	edit: {
		enable: true,
		showRenameBtn :true,
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


function addDiyDom(treeId, treeNode) {
    
}
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

/**
 * 点击code_type树,调用树结点查询对应的code_value值
 */
function onClick(event, treeId, treeNode, clickFlag) {
	//addtab(treeNode);
	var code_type=treeNode.id;
	var code_root_value=treeNode.code_root_value;
	var otherParam= { 'code_type':code_type,'code_root_value':code_root_value }
	sys_code_type_setting_detail.async.otherParam=otherParam;
	$.fn.zTree.init($("#tree-code-type-detail-div"), sys_code_type_setting_detail);
}

function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}

    y += document.body.scrollTop;
    x += document.body.scrollLeft;
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

var addCount = 1;
function addTreeNode() {
	hideRMenu();
	var newNode = { name:"增加" + (addCount++)};
	if (zTree.getSelectedNodes()[0]) {
		newNode.checked = zTree.getSelectedNodes()[0].checked;
		zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
	} else {
		zTree.addNodes(null, newNode);
	}
}
function removeTreeNode() {
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		if (nodes[0].children && nodes[0].children.length > 0) {
			var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
			if (confirm(msg)==true){
				zTree.removeNode(nodes[0]);
			}
		} else {
			zTree.removeNode(nodes[0]);
		}
	}
}
function checkTreeNode(checked) {
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		zTree.checkNode(nodes[0], checked, true);
	}
	hideRMenu();
}
function resetTree() {
	hideRMenu();
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

function addtab(treeNode){
    url=contextPath+"/codetype/toCodeValueTreePage/"+treeNode.id;
    var item = {'id':treeNode.id,'name':''+treeNode.name+'','url':url,'closable':true};
	closableTab.addTab(item);
}

</script>
</body>
</html>