package com.insigma.mvc.serviceimp.demo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.demo.DemoAc01Mapper;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.service.demo.DemoAc01Service;


/**
 * demo ac01 service impl
 * @author wengsh
 *
 */

@Service
public class DemoAc01ServiceImpl extends MvcHelper implements DemoAc01Service {

	@Resource
	private DemoAc01Mapper demoAc01Mapper;

	@Override
	public HashMap<String, Object> getAc01List(Ac01 ac01) {
		PageHelper.offsetPage(ac01.getOffset(), ac01.getLimit());
		List<Ac01> list=demoAc01Mapper.getAc01List(ac01);
		PageInfo<Ac01> pageinfo = new PageInfo<Ac01>(list);
		return this.success_hashmap_response(pageinfo);
	}
	
	
	

}
