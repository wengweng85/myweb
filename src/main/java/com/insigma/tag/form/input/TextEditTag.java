package com.insigma.tag.form.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * �Զ����ǩ֮�ı���
 * 
 * @author wengsh
 *
 */
public class TextEditTag implements Tag {

	private PageContext pageContext;

	// property
	private String property;

	// ֵ
	private String value;

	// У�����
	private String validate;
	
	//�Ƿ�ֻ��
	private String readonly;

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

	

	public String getValue() {
		return value;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
	     //��ֵ���
		 validate=(validate==null)?"":validate;
	     value=(value==null)?"":value;
	     boolean isreadonly=Boolean.parseBoolean(readonly=(readonly==null)?"false":readonly);
	     JspWriter out = pageContext.getOut();
	     StringBuffer sb=new StringBuffer();
		 sb.append("<input type=\"text\" id=\""+property+"\" name=\""+property+"\"  validate=\""+validate+"\" class=\"form-control\"");
		 if(isreadonly){
			 sb.append(" readonly=\"readonly\" ");
		 }
		 sb.append(">");
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
