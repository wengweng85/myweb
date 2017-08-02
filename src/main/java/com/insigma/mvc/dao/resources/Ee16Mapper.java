package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee16;

public interface Ee16Mapper {

	int deleteByPrimaryKey(String eee161);

    int insert(Ee16 record);

    int insertSelective(Ee16 record);

    Ee16 selectByPrimaryKey(String eee161);

    int updateByPrimaryKeySelective(Ee16 record);

    int updateByPrimaryKey(Ee16 record);
}