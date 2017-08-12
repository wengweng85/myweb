package com.insigma.mvc.service.sysmanager.codetype;

import java.util.HashMap;
import java.util.List;

import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;


/**
 * Ö÷Ò³service
 * @author wengsh
 *
 */
public interface SysCodeTypeService {
	
	public List<CodeType> getInitcodetypeList();
	public List<CodeValue> getInitCodeValueList(String code_type);
	public List<CodeValue> queryCodeValueByCodeTypeAndParent(CodeValue codevalue);
	public HashMap<String,List<CodeValue>> getCodeValueFromCache(CodeValue codevalue);
	public List<CodeValue> getCodeValueTree(CodeValue codevalue);
	public List<CodeType> getCodeTypeTreeData();
	public List<CodeType> getCodeValueTreeData(CodeType codetype);
	public CodeType getCodeTypeInfo(String code_type);
	
}
