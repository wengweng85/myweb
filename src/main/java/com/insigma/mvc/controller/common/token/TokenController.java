package com.insigma.mvc.controller.common.token;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Element;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insigma.common.interceptor.TokenUtil;
import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.MvcHelper;
import com.insigma.resolver.AppException;

/**
 * Created by wengsh on 2015-01-14.
 */
@Controller
public class TokenController extends MvcHelper {

	/**
	 * Éú³Étoken
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/token")
	public void token(HttpServletRequest request, HttpServletResponse response) throws AppException {
		try {
			String token=TokenUtil.createToken();
			request.getSession().setAttribute("csrftoken",token);

	        /*Vector<String> csrftokenlist = (Vector<String>)request.getSession().getAttribute("csrftokenlist");
	        if(csrftokenlist == null){
	        	csrftokenlist = new Vector<String>();
	        }
	        csrftokenlist.add(token);
	        request.getSession().setAttribute("csrftokenlist",csrftokenlist);*/
			EhCacheUtil.getManager().getCache("tokencache").put(new Element(token,""));
	        System.out.println("»º´æÊý£º"+EhCacheUtil.getManager().getCache("tokencache").getSize());
			
			PrintWriter out = response.getWriter();
			out.print(token);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new AppException(e);
		}
	}
}