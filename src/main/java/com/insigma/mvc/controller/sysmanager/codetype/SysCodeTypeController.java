package com.insigma.mvc.controller.sysmanager.codetype;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.sysmanager.codetype.SysCodeTypeService;
import com.insigma.resolver.AppException;

/**
 * Created by wengsh on 2015-01-14.
 */
@Controller
@RequestMapping("/codetype")
public class SysCodeTypeController extends MvcHelper<CodeValue> {

	@Resource
	private SysCodeTypeService sysCodeTypeService;
	
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
		ModelAndView modelAndView=new ModelAndView("sysmanager/codevalue/codeValueSelect");
        modelAndView.addObject("callback_fun_name", callback_fun_name);
        modelAndView.addObject("codetype", codetype);
        return modelAndView;
	}
	
	/**
	 *codevalue 代码树
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
		return sysCodeTypeService.getCodeValueTree(codevalue);
	}
	
	/**
	 * 根据代码类型及代码父类名获取代码值
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/queryByCodeTypeAndParent")
	@ResponseBody
	public List<CodeValue> queryByCodeTypeAndParent(HttpServletRequest request, HttpServletResponse response,CodeValue codevalue) throws AppException {
		return sysCodeTypeService.queryCodeValueByCodeTypeAndParent(codevalue);
	}
	
	
	/**
	 * 从缓存中获取代码值
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/getCodeValueFromCache")
	@ResponseBody
	public HashMap<String,List<CodeValue>> getCodeValueFromCache(HttpServletRequest request, HttpServletResponse response,CodeValue codevalue) throws AppException {
		return sysCodeTypeService.getCodeValueFromCache(codevalue);
	}
	
	
	
	/**
	 * 跳转至代码管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	@RequiresRoles("admin")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/codevalue/sysCodeTypeIndex");
        return modelAndView;
	}
	
	
	/**
	 *codetype 代码树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/codetype_treedata")
	@ResponseBody
	public List<CodeType> codetype_treedata(HttpServletRequest request, HttpServletResponse response) throws AppException {
		return sysCodeTypeService.getCodeTypeTreeData();
	}
	
	
	/**
	 *跳转至代码值树编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/toCodeValueTreePage/{id}")
	@ResponseBody
	public ModelAndView toCodeValueTreePage(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) throws AppException {
		CodeType codetype=sysCodeTypeService.getCodeTypeInfo(id);
		ModelAndView modelAndView=new ModelAndView("sysmanager/codevalue/sysCodeTypeEdit");
		modelAndView.addObject("codetype", codetype);
        return modelAndView;
	}
	
	/**
	 *codevalue 代码树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/codevalue_treedata")
	@ResponseBody
	public List<CodeType> codevalue_treedata(HttpServletRequest request, HttpServletResponse response) throws AppException {
		//默认父节点使用
		String id=request.getParameter("id");
		String code_type=request.getParameter("code_type");
		//根目录结点
		String code_root_value=request.getParameter("code_root_value");
		CodeType codetype=new CodeType();
		codetype.setId(id);
		codetype.setCode_type(code_type);
		codetype.setCode_root_value(code_root_value);
		return sysCodeTypeService.getCodeValueTreeData(codetype);
	}
	
}