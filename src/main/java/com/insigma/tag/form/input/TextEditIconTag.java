package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * 自定义标签之选择框
 * 
 * @author wengsh
 *
 */
public class TextEditIconTag implements Tag {

	private PageContext pageContext;

	
	
	// property
	private String property;
	
	//label
	private String label;
		
	//占位列数,包括label的一列
	private String cols;
	
	

	// 值
	private String value;
	
	//值中文
	private String value_name;

    //页面弹出框url
	private String url;
	
	
	
	//自定义回调函数
	private String callback;
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private String title;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

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
	     value_name=(value_name==null)?"":value_name;
	     String [] col=cols.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     
	     sb.append("<label class=\"col-sm-"+labelcol+" col-xs-"+labelcol+"  control-label\">"+label+"</label>");
	     sb.append("<div class=\"col-sm-"+inputcol+" col-xs-"+inputcol+" \">");
	     
	     sb.append("<div class=\"input-group\">");
	     sb.append("<input type=\"hidden\" id=\""+property+"\" name=\""+property+"\"  value=\""+value+"\" >");
	     sb.append("<input type=\"text\" id=\""+property+"_value\" name=\""+property+"_value\"  value=\""+value_name+"\"  readonly=\"readonly\" class=\"form-control\"> ");
	     sb.append("<span class=\"input-group-btn\"> ");
	     sb.append("<button type=\"button\" onclick=\""+property+"_clean_select()\" class=\"btn btn-warning\"><i class=\"fa fa-remove\"></i></button>&nbsp;");
	     sb.append("<button type=\"button\" onclick=\""+property+"_open_select()\" class=\"btn btn-primary\"><i class=\"fa fa-search\"></i></button>");
	     sb.append("</span>");
	     sb.append("</div> </div>");
	     
	     //对应javascript
	     String url=getUrl()+"?callback_fun_name="+property+"_callback";
	     
	     sb.append("<script type=\"text/javascript\">");
	     sb.append(" function "+property+"_open_select(){");
	     sb.append("layer.open({ type: 2,title: '"+title+"',shadeClose: false, shade: 0.8, area: ['70%', '90%'],content: \""+url+"\" })}");
	     sb.append(" function "+property+"_callback(code,value){");
	     sb.append("$('#"+property+"').val(code);");
	     sb.append("$('#"+property+"_value').val(value);");
	     if(callback!=null){
			  sb.append(callback+"(code)");
		 }
	     sb.append("}");
	     sb.append("function "+property+"_clean_select(){$('#"+property+"').val('');$('#"+property+"_value').val('');rc.clean();}");
	     sb.append("</script>");
		
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
