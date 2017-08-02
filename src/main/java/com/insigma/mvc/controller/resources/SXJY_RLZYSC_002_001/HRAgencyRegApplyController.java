package com.insigma.mvc.controller.resources.SXJY_RLZYSC_002_001;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.model.Ef11;
import com.insigma.mvc.service.demo.DemoAc01Service;
import com.insigma.mvc.service.resources.SXJY_RLZYSC_002_001.HRAgencyApplyService;


/**
 * 人力资源服务机构申请
 * @author pangyy
 *
 */
@Controller
@RequestMapping("/hragencyapply")
public class HRAgencyRegApplyController extends MvcHelper {
	
	
	@Resource
	private DemoAc01Service demoAc01Service;
	
	@Resource
	private HRAgencyApplyService hrAgencyApplyService;
	
	/**
	 * 跳转至查询页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toquery")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoQuery");
        return modelAndView;
	}
	
	
	/**
	 * 获取人员信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAc01List")
	@ResponseBody
	public HashMap<String,Object> getUserListByGroupid(HttpServletRequest request,Model model,Ac01 ac01 ) throws Exception {
		return demoAc01Service.getAc01List(ac01);
	}
	
	/**
	 * 通过id删除人员demo信息
	 * @param request
	 * @param model
	 * @param aac001
	 * @return
	 */
	@RequestMapping("/deletebyid/{id}")
	@ResponseBody
	public AjaxReturnMsg deleteDemoDataById(HttpServletRequest request,Model model,@PathVariable String id){
		return demoAc01Service.deleteDemoById(id);
	}
	
	
	/**
	 * 批量删除
	 * @param request
	 * @param model
	 * @param aac001
	 * @return
	 */
	@RequestMapping("/batdelete")
	@ResponseBody
	public AjaxReturnMsg batDeleteDemodata(HttpServletRequest request,Model model,Ac01 ac01){
		return demoAc01Service.batDeleteDemoData(ac01);
	}
	
	
	
	/**
	 * 跳转至编辑(新增)页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toreg")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("resources/SXJY_RLZYSC_002_001/HRAgencyRegApply");
        return modelAndView;
	}
	
	/**
	 * 人员选择框
	 * @param request
	 * @return
	 */
	@RequestMapping("/toselect")
	public ModelAndView selectindex(HttpServletRequest request,Model model) throws Exception {
		String callback_fun_name=request.getParameter("callback_fun_name");
		ModelAndView modelAndView=new ModelAndView("demo/demoSelect");
		// 构造填充数据的Map
        Map modelMap = new HashMap();
        modelMap.put("callback_fun_name", callback_fun_name);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
	}
	
	/**
	 * 跳转至编辑(新增)页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDemoById/{id}")
	@ResponseBody
	public AjaxReturnMsg getDemoById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return demoAc01Service.getDemoById(id);
	}
	
	
	/**
	 * 更新或保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/savedata")
	@ResponseBody
	public AjaxReturnMsg savedata(HttpServletRequest request,Model model,@Valid Ef11 ef11,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
		return hrAgencyApplyService.saveEf11Data(ef11);
		
	}

}
