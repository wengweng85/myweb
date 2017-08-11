package com.insigma.mvc.dao;

import com.insigma.mvc.model.Ac01;

public interface Ac01Mapper {
    int deleteByPrimaryKey(String aac001);

    int insert(Ac01 record);

    int insertSelective(Ac01 record);

    Ac01 selectByPrimaryKey(String aac001);

    int updateByPrimaryKeySelective(Ac01 record);

    int updateByPrimaryKeyWithBLOBs(Ac01 record);

    int updateByPrimaryKey(Ac01 record);
}