package com.insigma.mvc.jpa.dao.log;

import org.springframework.data.repository.CrudRepository;

import com.insigma.mvc.model.SLog;



/**
 * @author wengsh
 *
 */
public interface LogDao extends CrudRepository <SLog, String> {
	
}
