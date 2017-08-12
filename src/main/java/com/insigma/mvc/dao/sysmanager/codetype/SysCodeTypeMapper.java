package com.insigma.mvc.dao.sysmanager.codetype;

import java.util.List;

import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;



/**
 * @author wengsh
 *
 */
public interface SysCodeTypeMapper {
	
	public List<CodeType> getInitcodetypeList();
	public List<CodeValue> getInitCodeValueList(String code_type);
	public List<CodeValue> queryCodeValueByCodeTypeAndParent(CodeValue codevalue);
	public List<CodeValue> getCodeValueTree(CodeValue codevalue);
	public List<CodeType> getCodeTypeTreeData();
	public List<CodeType> getCodeValueByType(CodeType codetype);
	public List<CodeType> getCodeValueByTypeAndRoot(CodeType codetype);
	public CodeType getCodeTypeInfo(String code_type);
}
