package com.insigma.mvc.controller.index;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.service.index.IndexService;



/**
 * ��������ҳcontoller
 * @author wengsh
 *
 */
@Controller
public class IndexController extends BaseController {
	
	@Resource
    private IndexService indexService;
	
	/**
	 * ��ҳ
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView gotoAdminIndex(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("index/admin_index");
        return modelAndView;
	}
	
	/**
	 * ��ҳ
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView gotoIndex(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("index/index2");
        return modelAndView;
	}
	
	/**
	 * http 404 ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/404")
	public ModelAndView error404(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/404");
        return modelAndView;
	}
	
	/**
	 * http 500 ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/500")
	public ModelAndView error500(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/500");
        return modelAndView;
	}
	
	/**
	 *  δ��Ȩ
	 * @param request
	 * @return
	 */
	@RequestMapping("/unrecognized")
	public ModelAndView unrecognized(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/unrecognized");
        return modelAndView;
	}
}
