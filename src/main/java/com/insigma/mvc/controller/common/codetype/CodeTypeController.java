package com.insigma.mvc.controller.common.codetype;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 根据代码类型获取代码值
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public List<CodeValue> query(HttpServletRequest request, HttpServletResponse response,CodeValue codevalue) throws AppException {
		return initservice.queryCodeValueByParam(codevalue);
	}
}