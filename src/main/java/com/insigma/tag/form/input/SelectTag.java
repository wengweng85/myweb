package com.insigma.tag.form.input;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import net.sf.ehcache.Element;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;

/**
 * 自定义标签之表单选择框标签
 * 
 * @author wengsh
 *
 */
public class SelectTag implements Tag {

	private PageContext pageContext;

	// 下拉代码类型
	private String codetype;

	// property
	private String property;

	// 值
	private String value;

	// 校验规则
	private String validate;

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public String getCodetype() {
		return codetype;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
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
		// 空值检查
		validate = (validate == null) ? "" : validate;
		value = (value == null) ? "" : value;
		// TODO Auto-generated method stub
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<select class=\"form-control selectpicker \" id=\"" + property+ "\" name=\"" + property + "\"  value=\"" + value+ "\"   data-live-search=\"true\"  validate=\"" + validate+ "\">");
		// 从EhCache获取下载
		Element element = EhCacheUtil.getManager().getCache("webcache")
				.get(codetype);
		if (element != null) {
			List<CodeValue> list = (List<CodeValue>) element.getValue();
			sb.append("<option value=\"\"></option> ");
			for (CodeValue codevalue : list) {
				sb.append("<option ");
				if (value != null && !value.equals("")) {
					if (value.equals(codevalue.getCode_value())) {
						sb.append(" selected ");
					}
				}
				sb.append("  value=\"" + codevalue.getCode_value() + "\">"+ codevalue.getCode_name() + "</option>");
			}
		}
		sb.append("</select>");

		// 从redis获取下载
		/*
		 * try{ RedisManager redismanager=
		 * MyApplicationContextUtil.getContext().getBean(RedisManager.class);
		 * List<CodeValue> list=(List<CodeValue>)redismanager.get(codetype);
		 * if(list!=null){ for(CodeValue codevalue:list){ sb.append("<option ");
		 * if(value!=null&&!value.equals("")){
		 * if(value.equals(codevalue.getCode_value())){ sb.append(" selected ");
		 * } } sb.append("  value=\""+codevalue.getCode_value()+"\">"+codevalue.
		 * getCode_name()+"</option>"); } } sb.append("</select>");
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
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
