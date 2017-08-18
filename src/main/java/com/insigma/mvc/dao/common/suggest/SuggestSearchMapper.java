package com.insigma.mvc.dao.common.suggest;

import java.util.List;

import com.insigma.mvc.model.SuggestKey;

public interface SuggestSearchMapper {
	
	List<SuggestKey> searchByKey(SuggestKey key);
    
}