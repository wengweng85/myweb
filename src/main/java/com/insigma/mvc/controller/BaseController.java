package com.insigma.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;

/**
 * Created by Administrator on 2014-12-17.
 */


@Controller

public class BaseController {
	
	/**
	 * 检验
	 * @param result
	 * @return
	 */
	public AjaxReturnMsg validate(BindingResult result){
		FieldError fielderror=result.getFieldErrors().get(result.getErrorCount()-1);
		return this.error(fielderror.getDefaultMessage(),fielderror.getField());
	}

    /**
     * 成功返回
     * @param message
     * @return
     */
    public AjaxReturnMsg success(String message) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        return dto;
    }


    /**
     * 成功返回
     * @param message
     * @param o
     * @return
     */
    public AjaxReturnMsg success(String message,Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(o);
        return dto;
    }

    /**
     * 成功返回
     * @param o
     * @return
     */
    public AjaxReturnMsg success(Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(o);
        return dto;
    }
    /**
     * 成功返回
     * @param PageInfo pageinfo
     * @return
     */
    public AjaxReturnMsg success(PageInfo pageinfo) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(pageinfo.getList());
        dto.setTotal(pageinfo.getTotal());
        return dto;
    }

    /**
     * 成功返回
     * @param response
     * @param message
     * @throws IOException
     */
    public void success(HttpServletResponse response,String message) throws IOException {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }


    /**
     * 成功返回
     * @param response
     * @param message
     * @param o
     * @throws IOException
     */
    public void success(HttpServletResponse response,String message,Object o) throws IOException{
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(o);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 成功返回
     * @param response
     * @param o
     * @throws IOException
     */
    public void success(HttpServletResponse response,Object o) throws IOException {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(o);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
    
    
    /**
     * 成功返回
     * @param response
     * @param o
     * @throws IOException
     */
    public void success_native_response(HttpServletResponse response,List list) throws IOException {
        JSONArray jsonarray=JSONArray.fromObject(list);
        PrintWriter out = response.getWriter();
        out.write(jsonarray.toString());
        out.flush();
        out.close();
    }
    
    
    /**
     * 成功返回
     * @param response
     * @param o
     * @throws IOException
     */
    public void success_native_response(HttpServletResponse response,Object o) throws IOException {
        JSONObject jsonObject=JSONObject.fromObject(o);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
    
    
    
    /**
     * 错误返回
     * @param message
     * @return
     */
    public AjaxReturnMsg error(String message) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        return dto;
    }

    /**
     * 错误返回
     * @param message
     * @param obj
     * @return
     */
    public AjaxReturnMsg error(String message,Object obj) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        return dto;
    }

    /**
     * 错误返回
     * @param o
     * @return
     */
    public AjaxReturnMsg error(Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setObj(o);
        return dto;
    }

    /**
     * 错误返回
     * @param e
     * @return
     */
    public AjaxReturnMsg error(Exception e) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(e.getLocalizedMessage());
        return dto;
    }


    /**
     * 错误返回
     * @param message
     * @return
     */
    public void error(HttpServletResponse response,String message) throws IOException{
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 错误返回
     * @param response
     * @param message
     * @param obj
     * @throws IOException
     */
    public void error(HttpServletResponse response,String message,Object obj) throws IOException{
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 错误返回
     * @param response
     * @param o
     * @throws IOException
     */
    public void error(HttpServletResponse response,Object o) throws IOException{
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setObj(o);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 错误返回
     * @param response
     * @param e
     * @throws IOException
     */
    public void error(HttpServletResponse response,Exception e) throws IOException {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(e.getLocalizedMessage());
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
    /**
     * 将ajax返回dto返回成字符串
     * @param msg
     * @return
     */
    public String AjaxMsgtoString(AjaxReturnMsg msg){
        return JSONObject.fromObject(msg).toString();
    }

}
