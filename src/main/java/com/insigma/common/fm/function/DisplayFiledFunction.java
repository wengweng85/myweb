package com.insigma.common.fm.function;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class DisplayFiledFunction implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		return isDisplay(arguments);
	}
	
	/**
	 * 判断字段是否显示(参数1、2必须存在)<br>
	 * <li>参数1: 页面上显示的所有字段</li><br>
	 * <li>参数2: 要显示的某字段的名称</li>
	 */
	@SuppressWarnings("rawtypes")
	private Integer isDisplay(List arguments) {
		String displayFileds = (String) arguments.get(0);
		String filed = (String) arguments.get(1);
		if(displayFileds.contains(filed)){
			return 1;
		}
		return 0;
	}

}
