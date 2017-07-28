package com.insigma.tag.form.head;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;


/**
 * 自定义标签之css头加载
 * @author wengsh
 *
 */
public class CommonCssHeaderTag implements Tag  {
	
	private PageContext pageContext;  

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
	  JspWriter out = pageContext.getOut();
	  String contextpath = ((HttpServletRequest) this.pageContext.getRequest()).getContextPath();
	  StringBuffer sb=new StringBuffer();
	   //<!--css-->
	  sb.append("<link href='"+contextpath+"/resource/hplus/css/bootstrap.min.css' rel='stylesheet'>");
	  sb.append("<link href='"+contextpath+"/resource/hplus/css/font-awesome.min.css' rel='stylesheet'>");
	  sb.append("<link href='"+contextpath+"/resource/hplus/css/animate.min.css' rel='stylesheet'>");
	  sb.append("<link href='"+contextpath+"/resource/hplus/css/style.min.css' rel='stylesheet'>");
	  //<!-- bootstrap-table -->
	  sb.append("<link href='"+contextpath+"/resource/hplus/css/plugins/bootstrap-table/bootstrap-table.min.css' rel='stylesheet'>");
	  //<!-- ztree -->
	  sb.append("<link href='"+contextpath+"/resource/hplus/js/jQuery/ztree/css/metroStyle/metroStyle.css'  rel='stylesheet' >");
	  //<!-- select -->
	  sb.append("<link href='"+contextpath+"/resource/hplus/js/plugins/bootstrap-select/css/bootstrap-select.min.css' rel='stylesheet'>");
	  //<!-- datapicker -->
	  sb.append("<link href='"+contextpath+"/resource/hplus/js/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css' rel='stylesheet'>");
	  //<!-- rc.css -->
	  sb.append("<link href='"+contextpath+"/resource/hplus/css/rc.css' rel='stylesheet'>");
	  
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
