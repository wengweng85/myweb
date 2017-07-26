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
	
	/**通用下拉框架配置*/
	var select_config = {
		".selectpicker" : {}
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
	
	for (var selector in select_config){
		$(selector).selectpicker()
	}
	
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