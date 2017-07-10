package com.insigma.mvc.dao.drag;

import java.util.List;

import com.insigma.mvc.model.PageDesign;



/**
 * “≥√Ê…Ëº∆mapper
 * @author wengsh
 *
 */
public interface DragMapper {
	
	 public List<PageDesign> queryDesignPageList(PageDesign pagedesign);
	 
	 public PageDesign  queryDesignPageById(String id);
	 
	 public List<PageDesign> getLatestDesignPage();
	 
	 public void  updateserializedData(PageDesign design);
	 
	 public void  savePageDesign(PageDesign design);
	 
	 public void  updatePageDesign(PageDesign design);
	 
	 public void  deletePageDesignById(String id);
	 
}
