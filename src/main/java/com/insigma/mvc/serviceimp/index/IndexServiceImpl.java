package com.insigma.mvc.serviceimp.index;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insigma.mvc.dao.index.IndexMapper;
import com.insigma.mvc.service.index.IndexService;

/**
 *
 * @author wengsh
 *
 */
@Service
@Transactional
public class IndexServiceImpl implements IndexService {

	Log log = LogFactory.getLog(IndexServiceImpl.class);
	
	@Resource
	private IndexMapper indexMapper;
	


}