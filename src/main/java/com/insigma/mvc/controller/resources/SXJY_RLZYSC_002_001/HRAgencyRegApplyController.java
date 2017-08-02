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
 * ������Դ�����������
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
	 * ��ת����ѯҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toquery")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoQuery");
        return modelAndView;
	}
	
	
	/**
	 * ��ȡ��Ա��Ϣ�б�
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAc01List")
	@ResponseBody
	public HashMap<String,Object> getUserListByGroupid(HttpServletRequest request,Model model,Ac01 ac01 ) throws Exception {
		return demoAc01Service.getAc01List(ac01);
	}
	
	/**
	 * ͨ��idɾ����Աdemo��Ϣ
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
	 * ����ɾ��
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
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toreg")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("resources/SXJY_RLZYSC_002_001/HRAgencyRegApply");
        return modelAndView;
	}
	
	/**
	 * ��Աѡ���
	 * @param request
	 * @return
	 */
	@RequestMapping("/toselect")
	public ModelAndView selectindex(HttpServletRequest request,Model model) throws Exception {
		String callback_fun_name=request.getParameter("callback_fun_name");
		ModelAndView modelAndView=new ModelAndView("demo/demoSelect");
		// ����������ݵ�Map
        Map modelMap = new HashMap();
        modelMap.put("callback_fun_name", callback_fun_name);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
	}
	
	/**
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDemoById/{id}")
	@ResponseBody
	public AjaxReturnMsg getDemoById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return demoAc01Service.getDemoById(id);
	}
	
	
	/**
	 * ���»򱣴�
	 * @param request
	 * @return
	 */
	@RequestMapping("/savedata")
	@ResponseBody
	public AjaxReturnMsg savedata(HttpServletRequest request,Model model,@Valid Ef11 ef11,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		return hrAgencyApplyService.saveEf11Data(ef11);
		
	}

}
