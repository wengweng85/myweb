package com.insigma.tag.form.footer;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import net.sf.ehcache.Element;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;


/**
 * 自定义标签之表单选择框标签
 * @author wengsh
 *
 */
public class CommonJsFooterTag implements Tag  {
	
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
	  // TODO Auto-generated method stub
	  HttpServletRequest request=((HttpServletRequest) this.pageContext.getRequest());
      String contextpath = request.getContextPath();
	  StringBuffer sb=new StringBuffer();
	  
	  String csrf=(String)request.getAttribute("csrf" );
	  
	  //<!--css及javascript引入开始-->
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/jQuery/all/jquery.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/handlebars-v2.0.0-min.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/bootstrap.min.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/layer/layer.min.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/codemirror/codemirror.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/codemirror/mode/javascript/javascript.js'></script>");
	  //<!-- data table  -->
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/dataTables/jquery.dataTables.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/dataTables/dataTables.bootstrap.js'></script>");

	  //<!-- ztree  -->
	  sb.append("<script  src='"+contextpath+"/resource/hplus/js/jQuery/ztree/jquery.ztree.core.js'></script>");
	  sb.append("<script  src='"+contextpath+"/resource/hplus/js/jQuery/ztree/jquery.ztree.excheck.js'></script>");
	  sb.append("<script  src='"+contextpath+"/resource/hplus/js/jQuery/ztree/jquery.ztree.exedit.js'></script>");
	
	  //<!-- select -->
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/bootstrap-select/js/bootstrap-select.min.js'></script>");
	  // <!-- datapicker -->
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/plugins/datapicker/bootstrap-datepicker.js'></script>");
	      
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/rc.tag-1.0.js'></script>");
	  sb.append("<script src='"+contextpath+"/resource/hplus/js/rc.all-2.0.js'></script>");
	      
	  //<!-- 用于记录当前项目根目录供js调用 -->
	  sb.append("<input type='hidden' id='contextPath' name='contextPath' value='"+contextpath+"'>");
	  //<!-- 隐藏域用于重复校验验证 -->
	  sb.append("<input type='hidden' id='CSRFToken' name='CSRFToken' value='"+csrf+"'>");
	  
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
