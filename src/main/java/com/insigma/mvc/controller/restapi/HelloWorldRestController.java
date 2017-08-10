package com.insigma.mvc.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.init.InitService;


/**
 * rest http controller
 * @author wengsh
 *
 */
@RestController 
public class HelloWorldRestController {
	
	
	@Autowired  
	InitService initservice;  //Service which will do all data retrieval/manipulation work 
	 
	@RequestMapping(value = "/api/codevalue/{code_type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  
    public ResponseEntity<List<CodeValue>> getCodeValueList(@PathVariable("code_type") String code_type) {  
        System.out.println("Fetching CodeValue with codetype " + code_type);  
        List<CodeValue> list = initservice.getInitCodeValueList(code_type) ; 
        if (list == null) {  
            System.out.println("CodeValue with codetype " + code_type + " not found");  
            return new ResponseEntity<List<CodeValue>>(HttpStatus.NOT_FOUND);  
        }  
        return new ResponseEntity<List<CodeValue>>(list, HttpStatus.OK);  
    }  
}
