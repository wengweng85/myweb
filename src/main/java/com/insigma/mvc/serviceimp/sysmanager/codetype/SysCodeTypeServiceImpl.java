package com.insigma.mvc.serviceimp.sysmanager.codetype;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.codetype.SysCodeTypeMapper;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.sysmanager.codetype.SysCodeTypeService;

/**
 * 系统管理之代码管理
 * @author wengsh
 *
 */
@Service
public class SysCodeTypeServiceImpl extends MvcHelper<CodeValue> implements SysCodeTypeService {

	@Resource
	private SysCodeTypeMapper sysCodeTypeMapper;
	
	
	public List<CodeValue> queryCodeValueByCodeTypeAndParent(CodeValue codevalue){
		return sysCodeTypeMapper.queryCodeValueByCodeTypeAndParent(codevalue);
	}
	
	public HashMap<String,List<CodeValue>> getCodeValueFromCache(CodeValue codevalue){
		Element element = EhCacheUtil.getManager().getCache("webcache").get(codevalue.getCode_type().toUpperCase());
		List<CodeValue> list = (List<CodeValue>) element.getValue();
		HashMap map=new HashMap();
		map.put("value", list);
		return map;
	}


	
	@Override
	public List<CodeValue> getCodeValueTree(CodeValue codevalue) {
		 return sysCodeTypeMapper.getCodeValueTree(codevalue);
	}

	@Override
	public List<CodeType> getInitcodetypeList() {
		// TODO Auto-generated method stub
	   return sysCodeTypeMapper.getInitcodetypeList();
	}

	@Override
	public List<CodeValue> getInitCodeValueList(String code_type) {
		// TODO Auto-generated method stub
		return sysCodeTypeMapper.getInitCodeValueList(code_type);
	}

	@Override
	public List<CodeType> getCodeTypeTreeData() {
		return sysCodeTypeMapper.getCodeTypeTreeData();
	}

	@Override
	public List<CodeType> getCodeValueTreeData(CodeType codetype) {
		//初次加载
		if(codetype.getId().equals("")&& codetype.getCode_root_value().equals("")){
			return sysCodeTypeMapper.getCodeValueByType(codetype);
		}else{
			if(!codetype.getId().equals("")){
				codetype.setCode_root_value(codetype.getId());
			}
			return sysCodeTypeMapper.getCodeValueByTypeAndRoot(codetype);
		}
	}

	@Override
	public CodeType getCodeTypeInfo(String code_type) {
		return sysCodeTypeMapper.getCodeTypeInfo(code_type);
	}

}