package com.insigma.mvc.serviceimp.drag;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.dao.drag.DragMapper;
import com.insigma.mvc.model.PageDesign;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.service.drag.DragService;
import com.insigma.mvc.service.log.LogService;

/**
 *
 * @author wengsh
 *
 */
@Service

public class DragServiceImpl implements DragService {

	
	@Resource
	private DragMapper dragMapper;
	
	@Resource
	private LogService logService;
	
	

	@Override
	public PageInfo<PageDesign> queryDesignPageList(PageDesign pagedesign) {
		PageHelper.startPage(pagedesign.getCurpage(), pagedesign.getLimit());
		List<PageDesign> list =dragMapper.queryDesignPageList(pagedesign);
		PageInfo<PageDesign> pageinfo = new PageInfo<PageDesign>(list);
		return pageinfo;
	}
	
	@Override
	public List<PageDesign> getLatestDesignPage(PageDesign pagedesign) {
		PageHelper.startPage(pagedesign.getCurpage(), pagedesign.getLimit());
		List<PageDesign> list =dragMapper.queryDesignPageList(pagedesign);
		return list;
	}

	@Override
	public PageDesign queryDesignPageById(String id) {
		return dragMapper.queryDesignPageById(id);
	}

	@Override
	public void updateserializedData(PageDesign design) {
		 dragMapper.updateserializedData(design);
	}

	@Override
	//@Transactional(rollbackFor=Exception.class)
	public String savePageDesign(PageDesign design)  throws  Exception{
		//oracle 
		dragMapper.savePageDesign(design);
		System.out.println(design.getId());
		 
		SLog slog=new SLog();
		slog.setContent("测试一下");
		logService.saveLogInfo(slog);
		
		if(1==1){
			//throw new Exception("发生错误了");
		}
		return design.getId();
	}
	
	@Override
	public void updatePageDesign(PageDesign design) {
		 dragMapper.updatePageDesign(design);
	}

	@Override
	public void deletePageDesignById(String id) {
		dragMapper.deletePageDesignById(id);
		
	}

	
}