package com.insigma.mvc.service.sysmanager.codetype;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
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
	public CodeValue getCodeValueByValue(CodeValue codevalue);
	public HashMap<String,List<CodeValue>> getCodeValueFromCache(CodeValue codevalue);
	public List<CodeValue> getCodeValueTree(CodeValue codevalue);
	public List<CodeType> getCodeTypeTreeData(CodeType codetype);
	public List<CodeType> getCodeValueTreeData(CodeType codetype);
	public CodeType getCodeTypeInfo(String code_type);
	public AjaxReturnMsg<String> saveOrUpdateCodeType(CodeType codetype);
	public CodeValue getCodeTypeDetailInfo(String code_seq);
	public AjaxReturnMsg<String> saveOrUpdateCodeTypeDetail(CodeValue codevalue);
	public  void setSelectCacheData(String code_type);
	public AjaxReturnMsg<String> deleteCodeType(String code_type);
	public AjaxReturnMsg<String> deleteCodeValue(String code_seq);
}
