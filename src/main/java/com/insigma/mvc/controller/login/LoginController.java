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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.common.rsa.RSAUtils;
import com.insigma.common.util.MD5Util;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.login.LoginService;
import com.insigma.shiro.token.CustomUsernamePasswordToken;


/**
 * 登录controller
 * @author Administrator
 *
 */
@Controller
public class LoginController extends BaseController {
	
	Log log=LogFactory.getLog(LoginController.class);
	
	@Autowired
	private LoginService loginservice;
	
	
	/**
	 * 跳转至登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotologin")
	public ModelAndView gotologin(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		//是否已经登录-是-直接定向到主页面
		if(subject.isAuthenticated()){
			ModelAndView modelAndView=new ModelAndView("redirect:/index");
			return modelAndView;
		}
		//登录页面
		else{
			ModelAndView modelAndView=new ModelAndView("login/login");
			HashMap<String, String> map = new HashMap<String, String>();
			String contextPath = request.getContextPath();
			map.put("contextPath", contextPath);
			//生成rsakey
		    RSAUtils.getPublicKeyMap(map);  
			modelAndView.addAllObjects(map);
			return modelAndView;
		}
	}
	
	/**
	 * 登录
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
        //使用rsa privatekey解密
        String password = RSAUtils.decryptStringByJs(suer.getPassword());  
		//构造登陆令牌环
		CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(suer.getUsername(),  password.toCharArray(), rememberMe,host);
		try {
			subject.login(token);
			LoginInf inf=new LoginInf();
			inf.setIp(request.getRemoteHost());
			inf.setUsergent(request.getHeader("User-Agent"));
			inf.setSessionid(request.getSession().getId());
			inf.setLoginhash(MD5Util.MD5Encode(inf.getIp()+inf.getUsergent()+inf.getSessionid()));
			loginservice.saveLoginHashInfo(inf);
			return this.success("登录成功");
		} catch (UnknownAccountException uae) {
			errorMessage = "用户名或密码不对";
		} catch (IncorrectCredentialsException ice) {
			errorMessage = "用户名或密码不对";
		} catch (LockedAccountException lae) {
			errorMessage =  "账户被锁定.";
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
	 * 登出
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
	
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clientlogout")
	public String loginout(HttpServletRequest request,Model model) {
		request.getSession().setAttribute("user", null);
		Subject user = SecurityUtils.getSubject();
		user.logout();
		return "redirect:/logout";
	}
}
