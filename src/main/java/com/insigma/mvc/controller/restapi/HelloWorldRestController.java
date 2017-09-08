package com.insigma.mvc.controller.restapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.sysmanager.codetype.SysCodeTypeService;


/**
 * rest http controller
 * @author wengsh
 *
 */
@RestController 
public class HelloWorldRestController {
	
	
	@Autowired  
	private SysCodeTypeService sysCodeTypeService;  //Service which will do all data retrieval/manipulation work 
	 
	@RequestMapping(value = "/api/codevalue/{code_type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  
    public ResponseEntity<List<CodeValue>> getCodeValueList(@PathVariable("code_type") String code_type) {  
		CodeType codetype=new CodeType();
		codetype.setCode_type(code_type);
        System.out.println("Fetching CodeValue with codetype " + code_type);  
        List<CodeValue> list = sysCodeTypeService.getInitCodeValueList(codetype) ; 
        if (list == null) {  
            System.out.println("CodeValue with codetype " + code_type + " not found");  
            return new ResponseEntity<List<CodeValue>>(HttpStatus.NOT_FOUND);  
        }  
        return new ResponseEntity<List<CodeValue>>(list, HttpStatus.OK);  
    }  
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/api/common", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)  
    public ResponseEntity<String> apiCommon(HttpServletRequest request) {  
		String json=request.getParameter("json");
		try{
			 JSONObject jsonobject =  JSONObject.fromObject(json);
			 //检验格式
			 //得到service通过spring方式得到类
			 //调用具体实现类
		}catch(JSONException e){
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.OK);  
		}
		return new ResponseEntity<String>(json, HttpStatus.OK);  
    }  
}
