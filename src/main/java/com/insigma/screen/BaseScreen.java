package com.insigma.screen;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Screen类接口<br>
 * 
 * @author zbq
 * @date 2010-05-28
 */
public interface BaseScreen {

	/**
	 * 取得渲染页面所需要使用的数据<br>
	 * <li>子类实现方法取得数据保存至model中</li>
	 * 
	 * @param request
	 * @throws Exception 
	 */
	Map<String, Object> referenceData(HttpServletRequest request);

}
