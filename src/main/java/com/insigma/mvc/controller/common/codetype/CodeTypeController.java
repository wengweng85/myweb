package com.insigma.mvc.controller.common.codetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.init.InitService;
import com.insigma.resolver.AppException;

/**
 * Created by wengsh on 2015-01-14.
 */
@Controller
@RequestMapping("/codetype")
public class CodeTypeController extends MvcHelper {

	@Resource
	private InitService initservice;
	
	/**
	 * 跳转到代码搜索页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/toCodeValuesuggest")
	public ModelAndView toCodeValuesuggest(HttpServletRequest request, HttpServletResponse response,CodeValue codevalue) throws AppException {
		String callback_fun_name=request.getParameter("callback_fun_name");
		String codetype=request.getParameter("codetype");
		ModelAndView modelAndView=new ModelAndView("common/codevalue/codeValueSelect");
        modelAndView.addObject("callback_fun_name", callback_fun_name);
        modelAndView.addObject("codetype", codetype);
        return modelAndView;
	}
	
	/**
	 * 代码树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/treedata/{code_type}")
	@ResponseBody
	public List<CodeValue> treedata(HttpServletRequest request, HttpServletResponse response,@PathVariable String code_type) throws AppException {
		String id=request.getParameter("id");
		if(StringUtils.isEmpty(id)){
			id="000000";
		}
		CodeValue codevalue=new CodeValue();
		codevalue.setPar_code_value(id);
		codevalue.setCode_type(code_type.toUpperCase());
		return initservice.getCodeValueTree(codevalue);
	}
	
	/**
	 * 根据代码类型获取代码值
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public HashMap query(HttpServletRequest request, HttpServletResponse response,CodeValue codevalue) throws AppException {
		return initservice.queryCodeValueByParam(codevalue);
	}
	
	
}