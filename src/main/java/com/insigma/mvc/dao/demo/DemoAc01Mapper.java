package com.insigma.mvc.dao.demo;

import java.util.List;

import com.insigma.mvc.model.DemoAc01;

public interface DemoAc01Mapper {
	
	List<DemoAc01> getDemoAc01List(DemoAc01 ac01 );
	
    int deleteByPrimaryKey(String aac001);

    int insertSelective(DemoAc01 record);

    DemoAc01 selectByPrimaryKey(String aac001);
    
    DemoAc01 selectNameByPrimaryKey(String aac001);

    int updateByPrimaryKeySelective(DemoAc01 record);
    
    int batDeleteData(String []  ids);
    
    int selectByAac002(DemoAc01 ac01);
    
    int updateDemoAc01DemBusUuid(DemoAc01 ac01);
}