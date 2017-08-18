package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * 自定义标签之文本suggest搜索
 * 
 * @author wengsh
 *
 */
public class TextEditSuggestTag implements Tag {

	private PageContext pageContext;

	// property
	private String property;
	
	//label
	private String label;
		
	//占位列数,包括label的一列
	private String cols;
	
	private String keytype;

	// 值
	private String value;
	
	//值中文
	private String name_value;
	
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

   
	public String getKeytype() {
		return keytype;
	}

	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	private String title;
	

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

	public String getName_value() {
		return name_value;
	}

	public void setName_value(String name_value) {
		this.name_value = name_value;
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
	     name_value=(name_value==null)?"":name_value;
	     cols=(cols==null)?"1,2":cols;
	     
	     String [] col=cols.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     
	     //input
	     sb.append("<label class=\"col-sm-"+labelcol+" col-xs-"+labelcol+"  control-label\">"+label+"</label>");
	     sb.append("<div class=\"col-sm-"+inputcol+" col-xs-"+inputcol+" \">");
	     sb.append("<div class=\"input-group\">");
	     sb.append("<input type=\"hidden\" id=\""+property+"\" class=\"ignore_evaluation\" name=\""+property+"\"  value=\""+value+"\" >");
	     sb.append("<input type=\"text\" id=\""+property+"_name\" name=\""+property+"_name\"  placeholder=\"请输入中文或代码搜索\"  value=\""+name_value+"\"   class=\"form-control ignore_evaluation\"> ");
	     sb.append("<div class=\"input-group-btn\">");
	     sb.append("<a onclick=\""+property+"_clean_select()\" type=\"button\" class=\"btn btn-default\"><i class=\"fa fa-remove\"></i></a>");
	     sb.append("<button type=\"button\" class=\"btn btn-white dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button>");
	     sb.append("<ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul>");
	     sb.append("</div></div></div>");
	     
	     //js
	     sb.append("<script type=\"text/javascript\">");
	     sb.append("$(\"#"+property+"_name\").bsSuggest({");
	     sb.append("url:$('#contextPath').val()+'/suggest/searchcode?keytype="+keytype+"&keyword=',");
	     sb.append(" effectiveFields: ['showname','key','name'],");
	     sb.append("getDataMethod: \"url\",");
	     sb.append("effectiveFieldsAlias:{showname: \"全称\", key: \"代码\",name:\"名称\"},");
	     sb.append("fnPreprocessKeyword: function (keyword) {  ");
	     sb.append("return rc.encodeURITwice(keyword);  ");
	     sb.append(" }, ");
	     sb.append(" idField: \"id\",");
	     sb.append("keyField: \"showname\",");
	     sb.append("showHeader: true,");
	     sb.append("showBtn: true,  ");
	     sb.append("allowNoKeyword:false,");
	     sb.append("multiWord:false");
	     sb.append("}).on('onSetSelectValue', function (e, keyword, data) {");
	     sb.append("$('#"+property+"').val(keyword.id);");
	     sb.append(" $('#"+property+"_name').html(keyword.key);");
	     if(callback!=null){
			  sb.append(callback+"(keyword.id)");
		 }
	     sb.append("});");
	     sb.append("function "+property+"_clean_select(){$('#"+property+"').val('');$('#"+property+"_name').val('');rc.clean();}");
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
