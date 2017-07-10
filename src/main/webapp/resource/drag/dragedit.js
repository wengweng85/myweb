// 
/**
 * 页面拖动js
 * author wengsh
 * @type 
 */
//grid	
var grid;
//初始化
$(function() {
	 var options = {
        alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent),
        cell_height : 200,
		vertical_margin : 2,
		resizable:{
	        handles: 'e, se, s, sw, w'
	    }
    };
    $('.grid-stack').gridstack(options);
    grid = $('.grid-stack').data('gridstack');
	load_grid();
	$('window').on('resize',removediv);
});

var serialized_data =eval($('#saved-data').val());


//初始化加载
function load_grid() {
	grid.remove_all();
	var items = GridStackUI.Utils.sort(serialized_data);
	_.each(items, function(node) {
		add_widget(node);
	})
}

//保存布局表格信息
function save_grid() {
	serialized_data = _.map($('.grid-stack >.grid-stack-item:visible'),
	function(el) {
		el = $(el);
		var node = el.data('_gridstack_node');
		var dataurl=el.attr('data-dataurl');
		return {
			x : node.x,
			y : node.y,
			width : node.width,
			height : node.height,
			dataurl : dataurl
		};
	});
	$('#saved-data').val(JSON.stringify(serialized_data, null, '    '));
}

function clear_grid() {
	grid.remove_all();
};
//上一次选中的页面

var oldelement;
function showdiv(element,event) {
	//onclick toggle
	if ($('#showdiv').length > 0) {
		removediv();
	}else{
		$('.grid_stack_item').css({
		    backgroundColor:"#fff",
		    filter:"Alpha(Opacity = 20)",
		    opacity: "0.2"
		})
		
		changdivcss(element)
		
		var left = $(element).position().left;
		var top = $(element).position().top;
		var width = $(element).width();
		var height = $(element).height();
		var dataurl = $(element).attr('data-dataurl');
		var id = $(element).attr('id');
	
		floatdiv = $('<div id="showdiv" class="panel panel-default " style="z-index:9999;text-align:center;" ><div class="panel-body">' +
				'<textarea class="form-control" id="dataurl" rows="4" placeholder="页面url"  >'+dataurl+'</textarea>' +
				'<button type="button" class="btn  btn-info" onclick="realod(\''+ id+ '\')">修改地址</button> ' +
				'<button type="button" onclick="deletediv(\''+ id + '\')" class="btn  btn-danger">删除页面</button> ' +
				'<button type="button" onclick="removediv()" class="btn  btn-default">关闭</button></div></div>');
		floatdiv.appendTo($('body'));
		top = top + 35;
		left = left;
		floatdiv.css({
			position : "absolute",
			z_index : "999",
			left : left+width/2-150  + "px",
			top : top +height/2-75+ "px",
			width:'300px',
			height:'150px',
			display : "block"
		});
		//oldelement=element;
	}
	
}
//hidediv
function changdivcss(element){
	$(element).css({
	    backgroundColor:"#fff",
	    filter:"Alpha(Opacity = 70)",
	    opacity: "0.7"
	})
}

//修改地址
function realod(id) {
	var dataurl = $('#dataurl').val();
	if (dataurl) {
		$('#'+id).find('.grid-stack-item-content').load(dataurl);
		$('#'+id).attr('data-dataurl',dataurl);
	} else {
		layer.alert('请输入一个地址');
	}
	save_grid();
	
}

//removediv
function removediv(){
	if ($('#showdiv').length > 0) {
		$('#showdiv').remove();
		$('.grid_stack_item').css({
		    backgroundColor:"#fff",
		    filter:"Alpha(Opacity = 100)",
		    opacity: "100"
		})
	}
}

//deletediv 
function deletediv(id) {
	var index=layer.confirm('确定删除此页面吗?',function(){
		$('#' + id).remove();
		removediv();
		save_grid();
		layer.closeAll();
	});
	
    //layer.close(index);
}

//add_new_widget
function add_new_widget(){
	var node =   {
        x: 12 * Math.random(),
        y: 5 * Math.random(),
        width: 1 + 3 * Math.random(),
        height: 1 + 3 * Math.random(),
        dataurl:''
    };
    serialized_data.push(node);
    add_widget(node);
}

//add_widget
function add_widget(node){
	var d = $('<div class="grid_stack_item"  id="'
				+ guid()
				+ '" ondblclick="showdiv(this,event)"><div class="grid-stack-item-content"/></div>');
		
	d.on('dragstart',removediv);
	d.on('resize',removediv);
	
	if(node.dataurl){
		d.find('.grid-stack-item-content').load(node.dataurl);
	    //d.find('.grid-stack-item-content').append('<iframe src="'+node.dataurl+'" scrolling="no" height="100%" width="100%" frameborder="no" border="0" ></iframe>')
	}
	grid.add_widget(d, node.x, node.y, node.width, node.height);
	d.attr('data-dataurl', node.dataurl);
}
//S4
function S4() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}
//guid
function guid() {
	return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}