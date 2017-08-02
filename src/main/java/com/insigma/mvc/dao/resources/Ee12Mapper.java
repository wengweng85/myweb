package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee12;

public interface Ee12Mapper {

	int deleteByPrimaryKey(String eee121);

    int insert(Ee12 record);

    int insertSelective(Ee12 record);

    Ee12 selectByPrimaryKey(String eee121);

    int updateByPrimaryKeySelective(Ee12 record);

    int updateByPrimaryKey(Ee12 record);
}