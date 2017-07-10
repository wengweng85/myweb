package com.insigma.common.filter;

import java.io.File;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 安全过滤器
 * @author wengsh
 *
 */
public class SafeFilterHttpServletRequestWrapper extends HttpServletRequestWrapper {

	Log log= LogFactory.getLog(SafeFilterHttpServletRequestWrapper.class);
	
    private HttpServletRequest orgRequest;
    
    /**antisamy 文件名 */
    private static final String ANTISAMY_FILENAME = "/antisamy.xml";
    
   
    
    /**
     * 得到antisamy文件名
     */
    private static String getAntisamyFilePath() {
        String urlPath = SafeFilterHttpServletRequestWrapper.class.getResource("/").getPath();
        return (new File(urlPath).getParent() + ANTISAMY_FILENAME);
    }

    
    public SafeFilterHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
    
    @Override
    public String[] getParameterValues(String parameter) {
    	String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = filterValue(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
    	String value = super.getParameter(parameter);
        if (value != null) {
            value = filterValue(value);
        }else{
        	value="";
        }
        return value;
    }
    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof SafeFilterHttpServletRequestWrapper) {
            return ((SafeFilterHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value != null) {
            value = filterValue(value);
        }
        return value;
    }



    /**
     * 过滤代码
     *
     *
     * @param s
     * @return
     */
    public String filterValue(String s){
        if (s == null || s.isEmpty()) {
            return s;
        }
        //去前后空格
        s=s.trim();
        //UrlDecoder
        try{
            s=URLDecoder.decode(URLDecoder.decode(s, "UTF-8"),"UTF-8");
        }catch (Exception e){
        	//e.printStackTrace();
        }

        //防止sql注入
        s=StringEscapeUtils.escapeSql(s);
        //防止xss攻击
        //s=stripXSS(s);
        return s;
    }

   
}
