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
		".date" : {}
	};
	
	for (var selector in select_config){
		$(selector).selectpicker()
	}
	
	for (var selector in date_config){
		$(selector).datepicker({
			startView : 0,
			todayBtn : "linked",
			keyboardNavigation : !1,
			forceParse : !1,
			autoclose : !0,
			format : "yyyy-mm-dd"
		})
	}
	
});