package com.insigma.mvc.serviceimp.common.suggest;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.suggest.SuggestSearchMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SuggestKey;
import com.insigma.mvc.service.common.suggest.SuggestSearchService;

/**
 * @author wengsh
 *
 */
@Service
public class SuggestSearchServiceImpl extends MvcHelper<CodeValue> implements SuggestSearchService {

	@Resource
	private SuggestSearchMapper suggestSearchMapper;

	@Override
	public HashMap<String,List<SuggestKey>> searchByKey(SuggestKey key) {
		PageHelper.offsetPage(0, 10);
		String regexStr = "[\u4E00-\u9FA5]+";
		//判断是否是中文
		if(key.getKeyword().matches(regexStr)){
			key.setName(key.getKeyword());
		}else{
			key.setKey(key.getKeyword());
		}
		List<SuggestKey> list=suggestSearchMapper.searchByKey(key);
		HashMap map=new HashMap();
		map.put("value", list);
		return map;
	}
}