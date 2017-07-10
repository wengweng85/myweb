package com.insigma.mvc.model;

public class PageInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer curpage =1;
	private Integer limit=10;
	public Integer getCurpage() {
		return curpage;
	}
	public void setCurpage(Integer curpage) {
		this.curpage = curpage;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
	
}
