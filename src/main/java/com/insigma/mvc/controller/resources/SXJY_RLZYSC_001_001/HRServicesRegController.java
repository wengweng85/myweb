package com.insigma.mvc.controller.resources.SXJY_RLZYSC_001_001;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ������Դ��������Ǽ�
 * @author lin
 * */

@Controller
@RequestMapping("/resources/SXJY_RLZYSC_001_001")
public class HRServicesRegController {
	
	/**
	 * ��ת��������Դ��������Ǽ�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toreg")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		//��ת��������Դ��������Ǽ�ҳ���·��
		ModelAndView modelAndView=new ModelAndView("resources/SXJY_RLZYSC_001_001/HRServicesReg");
        return modelAndView;
	}

}
