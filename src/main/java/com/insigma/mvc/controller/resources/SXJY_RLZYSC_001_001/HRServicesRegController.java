package com.insigma.mvc.controller.resources.SXJY_RLZYSC_001_001;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 人力资源服务机构登记
 * @author lin
 * */

@Controller
@RequestMapping("/resources/SXJY_RLZYSC_001_001")
public class HRServicesRegController {
	
	/**
	 * 跳转至人力资源服务机构登记页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toreg")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		//跳转到人力资源服务机构登记页面的路径
		ModelAndView modelAndView=new ModelAndView("resources/SXJY_RLZYSC_001_001/HRServicesReg");
        return modelAndView;
	}

}
