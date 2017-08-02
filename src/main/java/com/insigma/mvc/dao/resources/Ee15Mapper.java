package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee15;

public interface Ee15Mapper {

	int deleteByPrimaryKey(String eee151);

    int insert(Ee15 record);

    int insertSelective(Ee15 record);

    Ee15 selectByPrimaryKey(String eee151);

    int updateByPrimaryKeySelective(Ee15 record);

    int updateByPrimaryKey(Ee15 record);
}