package com.insigma.mvc.dao.demo;

import java.util.List;

import com.insigma.mvc.model.Ac01;

public interface DemoAc01Mapper {
	
	List<Ac01> getAc01List(Ac01 ac01 );
	
    int deleteByPrimaryKey(String aac001);

    int insertSelective(Ac01 record);

    Ac01 selectByPrimaryKey(String aac001);
    
    Ac01 selectNameByPrimaryKey(String aac001);

    int updateByPrimaryKeySelective(Ac01 record);
    
    int batDeleteData(String []  ids);
    
    int selectByAac002(Ac01 ac01);
}