package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * 自定义标签之文本框
 * 
 * @author wengsh
 *
 */
public class TextEditTag implements Tag {

	private PageContext pageContext;

	// property
	private String property;
	
	//label
   private String label;
   
   //类型 text或password
   private String type;
   
   private String maxlength;
   
   private String min;
   
   private String max;
			
	//占位列数,包括label的一列
   private String cols;

	//是否必输
	private String required;
   
	//数据格式要求
	private String datamask;
	
	// 值
	private String value;

	// 校验规则
	private String validate;
	
	//是否只读
	private String readonly;
	
	//提示
	private String placeholder;
	
	
	
	
	//onclick事件
	private String onclick;
	
	//onchange事件
	private String onchange;
	
	//onblur事件
	private String onblur;
	
	//onkeydown事件
	private String onkeydown;
	
	//onkeypress事件
	private String onkeypress;
	
	//onkeyup事件
	private String onkeyup;
	
	
	

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
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

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getValue() {
		return value;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getDatamask() {
		return datamask;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public void setDatamask(String datamask) {
		this.datamask = datamask;
	}

	
	
	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
	     //空值检查
		 validate=(validate==null)?"":validate;
	     value=(value==null)?"":value;
	     readonly=(readonly==null)?"":readonly;
	     required=(required==null)?"":required;
	     datamask=(datamask==null)?"":datamask;
	     label=(label==null)?"":label;
	     cols=(cols==null)?"1,2":cols;
	     type=(type==null)?"text":type;
	     maxlength=(maxlength==null)?"":maxlength;
	     max=(max==null)?"":max;
	     min=(min==null)?"":min;
	     placeholder=(placeholder==null)?(label.equals("")?"":"请输入"+label):placeholder;
	     
	     if(!(type.equals("text")||type.equals("password")||type.equals("number"))){
	    	 throw new JspException("rc:textedit标签类型只能为text或password,请确认");
	     }
	     
	     String [] col=cols.split(",");
	     int labelcol=Integer.parseInt(col[0]);
	     int inputcol=Integer.parseInt(col[1]);
	     //是否只读
	     boolean isreadonly=Boolean.parseBoolean(readonly);
	     //是否必输
	     boolean isrequired=Boolean.parseBoolean(required);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
	     if(!label.equals("")){
	    	 sb.append("<label class=\"col-sm-"+labelcol+"  col-xs-"+labelcol+"  control-label\">"+label);
		     if(isrequired){
		    	 sb.append("<span class=\"require\">*<span>");
		     }
		     sb.append("</label>");
	     }else{
	    	 if(isrequired){
	    		 sb.append("<span class=\"require\">*<span>");
		     }
	     }
	    
	     sb.append("<div class=\"col-sm-"+inputcol+" col-xs-"+inputcol+" \">");
		 sb.append("<input type=\""+type+"\" id=\""+property+"\" name=\""+property+"\"  placeholder=\"请输入"+label+"\" value=\""+value+"\"  validate=\""+validate+"\" class=\"form-control\"");
		 if(isreadonly){
			 sb.append(" readonly=\"readonly\" ");
		 }
		 if(!datamask.equals("")){
			 sb.append(" data-mask=\""+datamask+"\" ");
		 }
		 if(!maxlength.equals("")){
			 sb.append(" maxlength=\""+maxlength+"\" ");
		 }
		 if(!max.equals("")){
			 sb.append(" max=\""+max+"\" ");
		 }
		 if(!min.equals("")){
			 sb.append(" min=\""+min+"\" ");
		 }
		 //onclick事件
		 if(onclick!=null){
			  sb.append(" onclick=\""+onclick+"\" ");
		 }
		 //onblur
		 if(onblur!=null){
			  sb.append(" onblur=\""+onblur+"\" ");
		 }
		 //onkeypress
		 if(onkeypress!=null){
			  sb.append(" onkeypress=\""+onkeypress+"\" ");
		 }
		//onkeydown
		 if(onkeydown!=null){
			  sb.append(" onkeydown=\""+onkeydown+"\" ");
		 }
		//onkeyup
		 if(onkeyup!=null){
			  sb.append(" onkeyup=\""+onkeyup+"\" ");
		 }
		//onchange
		 if(onchange!=null){
			  sb.append(" onchange=\""+onchange+"\" ");
		 }
		
		 sb.append(">");
		 sb.append("</div>");
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
