package com.insigma.mvc.serviceimp.init;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class InitServiceImpl implements InitService {

	@Resource
	private InitMapper initMapper;
	
	//@Resource
	//private InitDao initdao;

	@Override
	public List<CodeType> getInitcodetypeList() {
		return initMapper.getInitcodetypeList();
	}

	@Override
	public List<CodeValue> getInitCodeValueList(String code_type) {
		return initMapper.getInitCodeValueList(code_type);
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

}