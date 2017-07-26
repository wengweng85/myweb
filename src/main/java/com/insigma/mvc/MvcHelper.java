package com.insigma.mvc;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;


/**
 * mvc�����࣬��Ҫ���ڰ�װcontroller�Լ�serviceimp�㷵�ص�����
 * �����װ���෵�س�json��ʽ
 * @author wengsh
 *
 */
public class MvcHelper {
	
	public AjaxReturnMsg validate(BindingResult result){
		 FieldError fielderror=result.getFieldErrors().get(result.getErrorCount()-1);
		 AjaxReturnMsg dto = new AjaxReturnMsg();
	     dto.setSuccess(false);
	     dto.setMessage(fielderror.getDefaultMessage());
	     dto.setObj(fielderror.getField());
	     return dto;
	}

    /**
     * �ɹ�����
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
     * �ɹ�����
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
     * �ɹ�����
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
     * �ɹ�����
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
     * �ɹ�����
     * @param response
     * @param o
     * @throws IOException
     */
    public String success_string_response(List list) {
        JSONArray jsonarray=JSONArray.fromObject(list);
        return jsonarray.toString();
    }
    
    
    /**
     * �ɹ�����
     * @param response
     * @param o
     * @throws IOException
     */
    public String success_string_response(Object o) {
        JSONObject jsonObject=JSONObject.fromObject(o);
        return jsonObject.toString();
    }
    
    /**
     * ���󷵻�
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
     * ���󷵻�
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
     * ���󷵻�
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
     * ���󷵻�
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
     * ��ajax����dto���س��ַ���
     * @param msg
     * @return
     */
    public String AjaxMsgtoString(AjaxReturnMsg msg){
        return JSONObject.fromObject(msg).toString();
    }

}