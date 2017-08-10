package com.insigma.mvc;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;


/**
 * mvc帮助类，主要用于包装controller以及serviceimp层返回的数据
 * 将其包装成类返回成json格式
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
     * @param PageInfo pageinfo
     * @return
     */
    public HashMap<String,Object> success_hashmap_response(PageInfo pageinfo) {
    	HashMap<String,Object> hashmap=new HashMap<String,Object>();
    	hashmap.put("total", pageinfo.getTotal());
    	hashmap.put("rows", pageinfo.getList());
    	return hashmap;
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
     * 将ajax返回dto返回成字符串
     * @param msg
     * @return
     */
    public String AjaxMsgtoString(AjaxReturnMsg msg){
        return JSONObject.fromObject(msg).toString();
    }

}
