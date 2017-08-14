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
	public List<CodeType> getCodeTypeTreeData(CodeType codetype);
	public List<CodeType> getCodeValueByType(CodeType codetype);
	public List<CodeType> getCodeValueByTypeAndRoot(CodeType codetype);
	public CodeType getCodeTypeInfo(String code_type);
	public int addCodeType(CodeType codetype);
	public int updateCodeType(CodeType codetype);
	public CodeValue getCodeTypeDetailInfo(String code_seq);
	public CodeValue getCodeTypeDetailByValue(CodeValue codeValue);
	public int addCodeTypeDetail(CodeValue codetype);
	public int updateCodeTypeDetail(CodeValue codetype);
	public int deleteCodeTypeByType(String code_type);
	public int deleteCodeValueByType(String code_type);
	public int deleteCodeValueBySeq(String code_seq);
	
}
