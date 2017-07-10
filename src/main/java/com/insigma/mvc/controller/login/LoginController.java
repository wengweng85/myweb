package com.insigma.mvc.controller.login;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.common.rsa.RSAUtils;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.SUser;
import com.insigma.shiro.token.CustomUsernamePasswordToken;


/**
 * ��¼controller
 * @author Administrator
 *
 */
@Controller
public class LoginController extends BaseController {
	
	Log log=LogFactory.getLog(LoginController.class);
	
	/**
	 * ��ת����¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotologin")
	public ModelAndView gotologin(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		//�Ƿ��Ѿ���¼-��-ֱ�Ӷ�����ҳ��
		if(subject.isAuthenticated()){
			ModelAndView modelAndView=new ModelAndView("redirect:/index");
			return modelAndView;
		}
		//��¼ҳ��
		else{
			ModelAndView modelAndView=new ModelAndView("login/login");
			HashMap<String, String> map = new HashMap<String, String>();
			String contextPath = request.getContextPath();
			map.put("contextPath", contextPath);
			//����rsakey
		    RSAUtils.getPublicKeyMap(map);  
			modelAndView.addAllObjects(map);
			return modelAndView;
		}
	
	}
	
	/**
	 * ��¼
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReturnMsg login(HttpServletRequest request,SUser suer) {
		String errorMessage = "";
		Subject subject = SecurityUtils.getSubject();
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
        String host = request.getRemoteHost();
		//�����½���ƻ�
		CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(suer.getUsername(), suer.getPassword() .toCharArray(), rememberMe,host);
		try {
			subject.login(token);
			return this.success("��¼�ɹ�");
		} catch (UnknownAccountException uae) {
			errorMessage = "�û��������벻��";
		} catch (IncorrectCredentialsException ice) {
			errorMessage = "�û��������벻��";
		} catch (LockedAccountException lae) {
			errorMessage =  "�˻�������.";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			errorMessage=e.getMessage();
			token.clear();
		}
		log.error(errorMessage);
		subject.getSession().setAttribute("errorMessage", errorMessage);
		return this.error(errorMessage);
	}
	
	/**
	 * �ǳ�
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginout")
	public String loginout(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		Subject user = SecurityUtils.getSubject();
		user.logout();
		return "redirect:/gotologin";
	}
}
