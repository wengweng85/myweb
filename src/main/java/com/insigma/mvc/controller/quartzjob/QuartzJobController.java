package com.insigma.mvc.controller.quartzjob;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.insigma.mvc.service.quartzjob.QuartzJobService;


/**
 * 任务controller
 * @author Administrator
 *
 */
@Controller
public class QuartzJobController extends BaseController {
	
	Log log=LogFactory.getLog(QuartzJobController.class);
	
	@Resource
	private QuartzJobService quartzJobService;
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/list")	
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("job/joblist");
        return modelAndView;
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/querylist")
	@ResponseBody
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,QrtzTrigger qrtztrigger) throws Exception {
		PageInfo<QrtzTrigger> pageinfo =quartzJobService.queryJobList(qrtztrigger);
		return this.success(pageinfo);
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("job/addjob");
		return modelAndView;
	}
	
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/saveorupdate")
	@ResponseBody
	public AjaxReturnMsg saveorupdate( HttpServletRequest request,Model model,@Valid QrtzTrigger qrtzTrigger,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
	    quartzJobService.addJob(qrtzTrigger);
	    return this.success("新增成功");
	}
	
	/**
	 * delete
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/delete/{id}")
	@ResponseBody
	public AjaxReturnMsg jobdelete(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		quartzJobService.deleteJob(id);
		return this.success("删除成功");
	}
	
	
	/**
	 * pause
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/pause/{id}")
	@ResponseBody
	public AjaxReturnMsg jobpause(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		quartzJobService.pauseJob(id);
		return this.success("暂停成功");
	}
	
	/**
	 * resume
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/resume/{id}")
	@ResponseBody
	public AjaxReturnMsg jobresume(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		quartzJobService.resumeJob(id);
		return this.success("恢复成功");
	}
	
}
