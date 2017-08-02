package com.insigma.mvc.dao.resources.SXJY_RLZYSC_002_001;

import com.insigma.mvc.model.Ef11;

public interface HRAgencyApplyMapper {
	int deleteByPrimaryKey(String aef101);

    int insert(Ef11 record);

    int insertSelective(Ef11 record);

    Ef11 selectByPrimaryKey(String aef101);

    int updateByPrimaryKeySelective(Ef11 record);

    int updateByPrimaryKey(Ef11 record);
    
    int selectByAef104(Ef11 record);

}