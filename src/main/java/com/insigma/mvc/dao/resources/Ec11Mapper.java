package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ec11;

public interface Ec11Mapper {
    int deleteByPrimaryKey(String eec111);

	int insert(Ec11 record);

	int insertSelective(Ec11 record);

	Ec11 selectByPrimaryKey(String eec111);

	int updateByPrimaryKeySelective(Ec11 record);

	int updateByPrimaryKey(Ec11 record);

}