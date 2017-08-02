package com.insigma.mvc.serviceimp.init;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.init.InitMapper;
import com.insigma.mvc.model.Aa01;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.init.InitService;

/**
 *
 * @author wengsh
 *
 */
@Service
public class InitServiceImpl extends MvcHelper implements InitService {

	@Resource
	private InitMapper initMapper;
	

	@Override
	public List<CodeType> getInitcodetypeList() {
		return initMapper.getInitcodetypeList();
	}

	@Override
	public List<CodeValue> getInitCodeValueList(String code_type) {
		return initMapper.getInitCodeValueList(code_type);
	}
	
	public List<CodeValue> queryCodeValueByCodeTypeAndParent(CodeValue codevalue){
		return initMapper.queryCodeValueByCodeTypeAndParent(codevalue);
	}
	
	
	public HashMap getCodeValueFromCache(CodeValue codevalue){
		Element element = EhCacheUtil.getManager().getCache("webcache").get(codevalue.getCode_type().toUpperCase());
		List<CodeValue> list = (List<CodeValue>) element.getValue();
		HashMap map=new HashMap();
		map.put("value", list);
		return map;
	}
	
	@Override
	public List<Aa01> getInitParamList(){
		return initMapper.getInitParamList();
	}

	@Override
	public Aa01 getInitParamById(String aaa001) {
		//return initdao.findByAaa001(aaa001);
		return null;
	}

	@Override
	public List<CodeValue> getCodeValueTree(CodeValue codevalue) {
		 return initMapper.getCodeValueTree(codevalue);
	}

}