package com.insigma.mvc.service.init;

import java.util.HashMap;
import java.util.List;

import com.insigma.mvc.model.Aa01;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;


/**
 * Ö÷Ò³service
 * @author wengsh
 *
 */
public interface InitService {
	
	public List<CodeType> getInitcodetypeList();
	public List<CodeValue> getInitCodeValueList(String code_type);
	public HashMap queryCodeValueByParam(CodeValue codevalue);
	public List<Aa01> getInitParamList();
	public Aa01 getInitParamById(String aaa001);
	public List<CodeValue> getCodeValueTree(CodeValue codevalue);
	
}
