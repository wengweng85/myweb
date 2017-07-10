package com.insigma.mvc.jpa.dao.init;

import org.springframework.data.repository.CrudRepository;

import com.insigma.mvc.model.Aa01;



/**
 * @author wengsh
 *
 */
public interface InitDao extends CrudRepository <Aa01, String> {
	
	public Aa01 findByAaa001(String aaa001);
	
}
