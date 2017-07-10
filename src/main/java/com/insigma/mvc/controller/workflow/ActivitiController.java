package com.insigma.mvc.controller.workflow;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.ProcessEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.QrtzTrigger;


/**
 * 任务controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/workflow") 
public class ActivitiController  extends BaseController {
	
	Log log=LogFactory.getLog(ActivitiController.class);
	
	@Resource
	ProcessEngine processEngine;
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")	
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("workflow/worklist");
        return modelAndView;
	}
	
	
	
}
