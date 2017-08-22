package com.insigma.mvc.controller.demo;

import java.util.HashMap;

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
import com.insigma.mvc.service.demo.DemoAc01Service;
import com.insigma.shiro.realm.SysUserUtil;


/**
 * demo���Գ���
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends MvcHelper<Ac01> {
	
	
	@Resource
	private DemoAc01Service demoAc01Service;
	
	
	/**
	 * ��ת����ѯҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toquery")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoQuery");
		//���µ�ǰ�û�Ȩ��
		SysUserUtil.updateCurrentUserPerms();
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
	 * ��ת���༭ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toedit/{id}")
	public ModelAndView toedit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoEdit");
		Ac01 ac01=demoAc01Service.getDemoById(id);
		modelAndView.addObject("ac01",ac01);  
        return modelAndView;
	}
	
	
	/**
	 * ��ת���鿴ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toview/{id}")
	public ModelAndView toview(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoView");
		Ac01 ac01=demoAc01Service.getDemoNameById(id);
		modelAndView.addObject("ac01",ac01);  
        return modelAndView;
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
	public AjaxReturnMsg<String> batDeleteDemodata(HttpServletRequest request,Model model,Ac01 ac01){
		return demoAc01Service.batDeleteDemoData(ac01);
	}
	
	
	/**
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("demo/demoAdd");
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
        modelAndView.addObject("callback_fun_name", callback_fun_name);
        return modelAndView;
	}
	
	/**
	 * ��ת���༭(����)ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDemoById/{id}")
	@ResponseBody
	public AjaxReturnMsg<Ac01> getDemoById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return this.success(demoAc01Service.getDemoById(id));
	}
	
	
	/**
	 * ���»򱣴�
	 * @param request
	 * @return
	 */
	@RequestMapping("/savedata")
	@ResponseBody
	public AjaxReturnMsg<String> savedata(HttpServletRequest request,Model model,@Valid Ac01 ac01,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		return demoAc01Service.saveDemoData(ac01);
		
	}
	
	/**
	 * ���¸���ͼƬ���
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatefile/{id}/{fileuuid}")
	@ResponseBody
	public AjaxReturnMsg<String> updatefile(HttpServletRequest request,Model model,@PathVariable String id,@PathVariable String fileuuid) throws Exception {
		Ac01 ac01=new Ac01();
		ac01.setAac001(id);
		ac01.setFileuuid(fileuuid);
		return demoAc01Service.updateAc01DemoFileUuid(ac01);
	}
	
	/**
	 * ɾ������ͼƬ
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletefile/{id}/{fileuuid}")
	@ResponseBody
	public AjaxReturnMsg<String> deletefile(HttpServletRequest request,Model model,@PathVariable String id,@PathVariable String fileuuid) throws Exception {
		Ac01 ac01=new Ac01();
		ac01.setAac001(id);
		ac01.setFileuuid(fileuuid);
		return demoAc01Service.deletefile(ac01);
	}

}
