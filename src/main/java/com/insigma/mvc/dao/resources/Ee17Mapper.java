package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee17;

public interface Ee17Mapper {

	int deleteByPrimaryKey(String eee171);

    int insert(Ee17 record);

    int insertSelective(Ee17 record);

    Ee17 selectByPrimaryKey(String eee171);

    int updateByPrimaryKeySelective(Ee17 record);

    int updateByPrimaryKey(Ee17 record);
}