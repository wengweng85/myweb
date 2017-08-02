package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee11;

public interface Ee11Mapper {

	int deleteByPrimaryKey(String eee111);

    int insert(Ee11 record);

    int insertSelective(Ee11 record);

    Ee11 selectByPrimaryKey(String eee111);

    int updateByPrimaryKeySelective(Ee11 record);

    int updateByPrimaryKey(Ee11 record);
}