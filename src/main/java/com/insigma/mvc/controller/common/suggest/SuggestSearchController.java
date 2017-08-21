package com.insigma.mvc.controller.common.suggest;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SuggestKey;
import com.insigma.mvc.service.common.suggest.SuggestSearchService;
import com.insigma.resolver.AppException;

/**
 * 建议搜索controller
 */
@Controller
@RequestMapping(value = "/common/suggest")
public class SuggestSearchController extends MvcHelper<SuggestKey> {


    Log log = LogFactory.getLog(SuggestSearchController.class);

    @Resource
    private SuggestSearchService suggestSearchService;
    
    /* 
	 * 通过搜索关键字
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/searchcode")
	@ResponseBody
	public HashMap<String,List<SuggestKey>> searchcodebykey(HttpServletRequest request, HttpServletResponse response,SuggestKey key) throws AppException {
		return suggestSearchService.searchByKey(key);
	}

}
