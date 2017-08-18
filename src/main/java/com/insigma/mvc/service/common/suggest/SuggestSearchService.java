package com.insigma.mvc.service.common.suggest;

import java.util.HashMap;
import java.util.List;

import com.insigma.mvc.model.SuggestKey;


/**

 * @author wengsh
 *
 */
public interface SuggestSearchService {
	
	HashMap<String,List<SuggestKey>> searchByKey(SuggestKey key);
	
}
