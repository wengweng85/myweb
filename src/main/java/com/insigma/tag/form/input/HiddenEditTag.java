package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * 自定义标签之隐藏框
 * 
 * @author wengsh
 *
 */
public class HiddenEditTag implements Tag {

	private PageContext pageContext;

	// property
	private String property;

	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	// 值
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public String getValue() {
		return value;
	}


	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
	     //空值检查
	     value=(value==null)?"":value;
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
		 sb.append("<input type=\"hidden\" id=\""+property+"\" name=\""+property+"\" value=\""+value+"\" >");
		 try {  
			   out.write(sb.toString());
	     } catch (IOException e) {  
	           throw new RuntimeException(e);  
	     }     
		 return 0;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPageContext(PageContext arg0) {
		// TODO Auto-generated method stub
		this.pageContext = arg0;
	}

	@Override
	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub
	}

}
