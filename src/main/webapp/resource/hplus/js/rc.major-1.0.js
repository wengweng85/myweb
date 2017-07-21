/**
 * 
 * @author hezy
 * @date 2013-05-07
 * @use 专业树
 */
$(function() {
	$.fn.zTree.init($("#tree"), {
			view : {
				dblClickExpand : false,
				showLine : false,
				expandSpeed:"normal"
			},
			async : {
				enable : true,
				url : contextPath+'/common/major/treequery',
				autoParam : ["id=parentid"]
			},
			data:{
				simpleData: {
				   enable: true,
				   pIdKey: "pid"
			    }
			},
			callback : {
				beforeExpand : beforeExpand,
				onExpand : onExpand,
				onClick : onClick
			}
		});
});
var setting = {
	view : {
		dblClickExpand : false,
		showLine : false,
		expandSpeed:"normal"
	},
	async : {
		enable : true,
		url : contextPath+'/common/major/treequery',
		autoParam : ["id=parentid"]
	},
	data:{
		simpleData: {
		   enable: true,
		   pIdKey: "pid"
	    }
	},
	callback : {
		beforeExpand : beforeExpand,
		onExpand : onExpand,
		onClick : onClick
	}
};
var curExpandNode = null;
function beforeExpand(treeId, treeNode) {
	var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
	var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
	var zTree = $.fn.zTree.getZTreeObj("tree");
	for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
		if (treeNode !== treeNodeP.children[i]) {
			zTree.expandNode(treeNodeP.children[i], false);
		}
	}
	while (pNode) {
		if (pNode === treeNode) {
			break;
		}
		pNode = pNode.getParentNode();
	}
	if (!pNode) {
		singlePath(treeNode);
	}

}
function singlePath(newNode) {
	if (newNode === curExpandNode)
		return;
	if (curExpandNode && curExpandNode.open == true) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		if (newNode.parentTId === curExpandNode.parentTId) {
			zTree.expandNode(curExpandNode, false);
		} else {
			var newParents = [];
			while (newNode) {
				newNode = newNode.getParentNode();
				if (newNode === curExpandNode) {
					newParents = null;
					break;
				} else if (newNode) {
					newParents.push(newNode);
				}
			}
			if (newParents != null) {
				var oldNode = curExpandNode;
				var oldParents = [];
				while (oldNode) {
					oldNode = oldNode.getParentNode();
					if (oldNode) {
						oldParents.push(oldNode);
					}
				}
				if (newParents.length > 0) {
					for (var i = Math.min(newParents.length, oldParents.length)
							- 1; i >= 0; i--) {
						if (newParents[i] !== oldParents[i]) {
							zTree.expandNode(oldParents[i], false);
							break;
						}
					}
				} else {
					zTree.expandNode(oldParents[oldParents.length - 1], false);
				}
			}
		}
	}
	curExpandNode = newNode;
}

function onExpand(event, treeId, treeNode) {
	curExpandNode = treeNode;
}
/**
 * 专业自动加载
 * 
 * @param {}
 *            diaplay_element_selector
 * @param {}
 *            hidden_element_selector
 */
function autocomplte(diaplay_element_selector) {
    diaplay_element_selector.autocomplete(	
	    contextPath + "/common/major/q",
		{
		 minChars:1,// 在触发前用户至少需要输入的字符数
		 width:215, // 指定下拉框的宽度
		 max:15, // 下拉显示项目的个数
		 scroll:false,// 当结果集大于默认高度时是否使用卷轴显示
		 mustMatch : true,
		 scrollHeight:400,// 自动完成提示的卷轴高度用像素大小表示 Default: 180
		 multipleSeparator:',',// 如果是多选时,用来分开各个选择的字符
		 multiple:false,// 是否允许输入多个值即多次使
		 selectFirst:false,// 如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择
		 delay:500, // 击键后激活的延迟时间(单位毫秒)
		 extraParams:{}, // 提供更多的参数.
		 formatItem : function(row, i, max) {// 格式化列表中的条目
			row = eval("(" + row + ")");
			return row.name;
		 },
		 formatResult : function(row) {// 选择后文本框显示的值
			console.log(row);
			row = eval("(" + row + ")");
			return row.name;
		 }
	}).result(function(event, row, formatted) {
		row = eval("(" + row + ")");
		if (row) {
			setValue(row)
		} else {
			clearValue();
		}
	});
   }
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (treeNode.isParent) {
		zTree.expandNode(treeNode, null, null, null, true);
	} else {
		setValue(treeNode);
	}
}
function setValue(result){
	$('#result').html("<font color='#CB3500'>"+result.name+"</font>");
	$('#hidden_element_selector').val(result.id);
	$('#descripe_selector').val(result.name);
}
function clearValue(){
	$('#result').html('');
	//window.parent.$.colorbox.element().val('专业选择/专业修改');
	var hidden_element_selector=$('#hidden_element_selector').val();
	var descripe_selector=$('#descripe_selector').val();
	if(hidden_element_selector!=null&&hidden_element_selector!=''){
		//window.parent.$.colorbox.element().parents('body').find('#'+hidden_element_selector).val('');
	}
	if(descripe_selector!=null&&descripe_selector!=''){
		//window.parent.$.colorbox.element().parents('body').find('#'+descripe_selector).val('');
	}
}