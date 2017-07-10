package com.insigma.mvc.service.drag;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.PageDesign;
import com.insigma.resolver.AppException;


/**
 * drag service
 * @author wengsh
 *
 */
public interface DragService {
	
	 public PageInfo<PageDesign> queryDesignPageList(PageDesign pagedesign);
	
	 public PageDesign  queryDesignPageById(String id);
	 
	 public List<PageDesign> getLatestDesignPage(PageDesign pagedesign);
	 
	 public void  updateserializedData(PageDesign design);
	 
     public String  savePageDesign(PageDesign design) throws  Exception;
	 
	 public void  updatePageDesign(PageDesign design);
	 
	 public void  deletePageDesignById(String id);
	
}
