/**
 * rc.tag-1.0.js
 *
 * 用于网站中的各类标签通用配置js
 * @author wengsh
 * 
 * version 1.0
 * 修改记录 wengsh
 * v1.0 提供select框架通用配置js
 *      提供日期类通用配置js
 *
 */
$(function() {
	
	var select_config = {
		".select2" : {}
	};
	
	var select_with_ajax_config = {
		".select2withajax" : {}
	};
	
	/**通用日期配置*/
	var date_config = {
		".form_date" : {}
	};
	
	var datetime_config = {
		".form_datetime" : {}
	};
	
	var time_config = {
		".form_time" : {}
	};
	
	
	/*for (var selector in select_config){
		$(selector).select2({
		   tags: false,
		   width: "100%", //设置下拉框的宽度
		   //minimumResultsForSearch: Infinity,
		   placeholder:'请选择',
		   language: "zh-CN", //设置 提示语言
		   closeOnSelect:false,
		   //minimumInputLength: 1,  //最小需要输入多少个字符才进行查询
		   allowClear: true, //是否允许清空选中
		   selectOnClose:false,
		   maximumSelectionLength:3
		})
	}*/
	
	
	/*for (var selector in select_config){
		$(selector).select2({
		   tags: false,
		   width: "100%", //设置下拉框的宽度
		   language: "zh-CN", //设置 提示语言
		   closeOnSelect:false,
		   minimumInputLength: 1,  //最小需要输入多少个字符才进行查询
		   allowClear: true, //是否允许清空选中
		   selectOnClose:false,
		   maximumSelectionLength:3,
		   formatResult: function formatRepo(repo){return repo.text;}, // 函数用来渲染结果
           formatSelection: function formatRepoSelection(repo){return repo.text;}, // 函数用于呈现当前的选择
		   ajax: {
		      url: contextPath+"/codetype/query",
		      dataType: 'json',
		      delay: 250,
		      data: function (params) {
		        return {
		          q: params.term,
		          code_type:'AAB800'
		        };
		     },
		     processResults: function (data) {
		        return {
		           results: data
		        };
		     },
		     cache: true
		     }
		})
	}*/
	
	
	for (var selector in date_config){
		$(selector).datetimepicker({
			format : "yyyy-mm-dd",
			minView: "2",//设置只显示到月份
			todayBtn:true,
			todayHighlight:true,
			autoclose:true
		})
	}
	
	for (var selector in datetime_config){
		$(selector).datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			todayHighlight:true,
			todayBtn:true
		})
	}
	
	for (var selector in time_config){
		$(selector).datetimepicker({
			format : "hh:ii",
			startView:'0',
			todayHighlight:true,
			todayBtn:true
		})
	}
});