package com.insigma.mvc.dao.init;

import java.util.List;

import com.insigma.mvc.model.Aa01;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;



/**
 * @author wengsh
 *
 */
public interface InitMapper {
	
	public List<Aa01> getInitParamList();
}
