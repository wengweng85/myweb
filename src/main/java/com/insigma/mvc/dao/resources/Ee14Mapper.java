package com.insigma.mvc.dao.resources;

import com.insigma.mvc.model.Ee14;

public interface Ee14Mapper {

	int deleteByPrimaryKey(String eee141);

    int insert(Ee14 record);

    int insertSelective(Ee14 record);

    Ee14 selectByPrimaryKey(String eee141);

    int updateByPrimaryKeySelective(Ee14 record);

    int updateByPrimaryKey(Ee14 record);
}