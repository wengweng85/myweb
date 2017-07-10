package com.insigma.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created by wengsh on 2014-12-17
 */
public final class CSRFTokenManager {

	private static Log log=LogFactory.getLog(CSRFTokenManager.class);
	
    /**
     * token 
     * The token parameter name
     */
    static final String CSRF_PARAM_NAME = "CSRFToken";

    /**
     * The location on the session which stores the token
     */
    //public static final  String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";

    /**
     *
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        String csrf= request.getParameter(CSRF_PARAM_NAME);
        log.info("csrf getParameter:"+csrf);
        if(csrf==null||csrf.equals("")){
            csrf=request.getHeader("RequestVerificationToken");
        }
        log.info("request.getHeader:RequestVerificationToken:"+request.getHeader("RequestVerificationToken"));
        return csrf;
    }
    
    /**
     * token remove
     * @param` request
     * @return
     */
    public static void removeTokenFromRequest(HttpServletRequest request,HttpServletResponse response) {
    	request.getSession().removeAttribute("csrftoken");
    }

    private CSRFTokenManager() {
    };

}