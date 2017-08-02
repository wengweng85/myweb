package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee13;

public interface Ee13Mapper {

	int deleteByPrimaryKey(String eee131);

    int insert(Ee13 record);

    int insertSelective(Ee13 record);

    Ee13 selectByPrimaryKey(String eee131);

    int updateByPrimaryKeySelective(Ee13 record);

    int updateByPrimaryKey(Ee13 record);
}