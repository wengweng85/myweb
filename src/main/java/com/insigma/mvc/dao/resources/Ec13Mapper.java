package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ec13;

public interface Ec13Mapper {
    int deleteByPrimaryKey(String eec131);

	int insert(Ec13 record);

	int insertSelective(Ec13 record);

	Ec13 selectByPrimaryKey(String eec131);

	int updateByPrimaryKeySelective(Ec13 record);

	int updateByPrimaryKey(Ec13 record);

}