//grid	
var grid;
//初始化
$(function() {
	var options = {
		cell_height : 200,
		vertical_margin : 2
	};
    $('.grid-stack').gridstack(options);
    grid = $('.grid-stack').data('gridstack');
	load_grid();
});

var serialized_data =eval($('#saved-data').val()) ;

//load_grid
function load_grid() {
	grid.remove_all();
	var items = GridStackUI.Utils.sort(serialized_data);
	_.each(items, function(node) {
		var d = $('<div><div class="grid-stack-item-content"/></div>');
		d.find('.grid-stack-item-content').append('<iframe src="'+node.dataurl+'" scrolling="no" height="100%" width="100%" frameborder="no" border="0" ></iframe>')
		grid.add_widget(d, node.x, node.y, node.width, node.height);
	})
}

function onload(){	
	dwr.engine.setActiveReverseAjax(true);
}
//重新加载
function show(message){
	//console.log(message);
	window.location.reload(true);
}
	