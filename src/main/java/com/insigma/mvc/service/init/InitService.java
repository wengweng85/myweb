package com.insigma.mvc.service.init;

import java.util.List;

import com.insigma.mvc.model.Aa01;


/**
 * ��ҳservice
 * @author wengsh
 *
 */
public interface InitService {
	
	public List<Aa01> getInitParamList();
	public Aa01 getInitParamById(String aaa001);
	
}
