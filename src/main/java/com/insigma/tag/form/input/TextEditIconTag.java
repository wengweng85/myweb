package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * 自定义标签之文本自定义选择
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
	
	private String area;
	
		
	//占位列数,包括label的一列
	private String cols;
	

	// 值
	private String value;
	
	//值中文
	private String name_value;

    //页面弹出框url
	private String url;
	
	//自定义回调函数
	private String callback;
	
	//提示
	private String placeholder;
	

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

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
	     area=(area==null)?" ['70%', '90%']":area;
	     title=(title==null)?label+"搜索框":title;
	     label=(label==null)?"":label;
	     cols=(cols==null)?"1,2":cols;
	     placeholder=(placeholder==null)?(label.equals("")?"":"请输入"+label):placeholder;
	     
	     String [] col=cols.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     
	     sb.append("<label class=\"col-sm-"+labelcol+" col-xs-"+labelcol+"  control-label\">"+label+"</label>");
	     sb.append("<div class=\"col-sm-"+inputcol+" col-xs-"+inputcol+" \">");
	     
	     sb.append("<div class=\"input-group\">");
	     sb.append("<input type=\"hidden\" id=\""+property+"\" class=\"ignore_evaluation\" name=\""+property+"\"  value=\""+value+"\" >");
	     sb.append("<input type=\"text\" id=\""+property+"_name\" name=\""+property+"_name\"  placeholder=\"请点击右侧放大镜选择\"  value=\""+name_value+"\"  readonly=\"readonly\" class=\"form-control ignore_evaluation\"> ");
	     sb.append("<span class=\"input-group-btn\"> ");
	     sb.append("<a type=\"button\" onclick=\""+property+"_clean_select()\" class=\"btn btn-default\"><i class=\"fa fa-remove\"></i></a>&nbsp;");
	     sb.append("<a type=\"button\" onclick=\""+property+"_open_select()\" class=\"btn btn-primary\"><i class=\"fa fa-search\"></i></a>");
	     sb.append("</span>");
	     sb.append("</div> </div>");
	     
	     //对应javascript
	     String url=getUrl()+"?callback_fun_name="+property+"_callback";
	     
	     sb.append("<script type=\"text/javascript\">");
	     sb.append(" function "+property+"_open_select(){");
	     sb.append("layer.open({ type: 2,title: '"+title+"',shadeClose: false, shade: 0.8, area: "+area+",content: \""+url+"\" })}");
	     sb.append(" function "+property+"_callback(code,value){");
	     sb.append("$('#"+property+"').val(code);");
	     sb.append("$('#"+property+"_name').val(value);");
	     if(callback!=null){
			  sb.append(callback+"(code)");
		 }
	     sb.append("}");
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
