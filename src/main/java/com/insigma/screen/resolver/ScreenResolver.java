package com.insigma.screen.resolver;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.insigma.screen.BaseScreen;
import com.insigma.screen.factory.ScreenFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 自定义导入模板解析类<br>
 * <li>导入模板时渲染模板用</li>
 * 
 * @author zbq
 * @date 2010-05-28
 */
public class ScreenResolver {

	private static final String EMPTY_VIEW = "";
	private static final String SCREEN_SUFFIX = "Screen";
	private static final Pattern FTL_NAME_PTN = Pattern.compile(".*/([\\w]*)$");

	private final Map<String, Object> staticAttributes = new HashMap<String, Object>();

	private String suffix;

	private FreeMarkerConfigurer freemarkerConfigurer;

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public FreeMarkerConfigurer getFreemarkerConfigurer() {
		return freemarkerConfigurer;
	}

	public void setFreemarkerConfigurer( FreeMarkerConfigurer freemarkerConfigurer) {
		this.freemarkerConfigurer = freemarkerConfigurer;
	}

	@SuppressWarnings("rawtypes")
	public void setAttributes(Map attributes) {
		setAttributesMap(attributes);
	}

	@SuppressWarnings("rawtypes")
	public void setAttributesMap(Map attributes) {
		if (attributes == null) {
			return;
		}
		Iterator it = attributes.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			if (key instanceof String) {
				addStaticAttribute((String) key, entry.getValue());
			}
		}
	}

	
	public void addStaticAttribute(String name, Object value) {
		this.staticAttributes.put(name, value);
	}

	/**
	 * 取得渲染后(数据模板合并)的视图
	 * 
	 * @param template
	 * @return 渲染后视图
	 */
	public String renderView(String template) {
		if (StringUtils.isBlank(template)) {
			return EMPTY_VIEW;
		}
		return renderViewByTemplate(template);
	}

	/**
	 * 渲染视图(取得数据, 合并数据模板)
	 * 
	 * @param template
	 * @return 渲染后视图
	 */
	private String renderViewByTemplate(String template) {

		String result = EMPTY_VIEW;
		// 取得对应的Screen类
		// 取得数据后渲染视图, 完成后返回, 如果失败返回"".
		try {

			BaseScreen screen = getScreenBean(template);
			if (screen == null) {
				return result;
			}

			HttpServletRequest request = getContextRequest();

			Map<String, Object> model = screen.referenceData(request);
			result = mergeModelToTemlate(template, getMergeModel(model));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 取得Screen渲染页面所需数据(配置的方法及变量)
	 * 
	 * @param model
	 * @return
	 */
	private Map<String, Object> getMergeModel(Map<String, Object> model) {
		// 设置渲染Screen页面所需数据、Freemarker配置文件中配置
		Map<String, Object> mergeModel = new HashMap<String, Object>();
		mergeModel.putAll(this.staticAttributes);
		mergeModel.putAll(model);
		
		return mergeModel;
	}

	private HttpServletRequest getContextRequest() {
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) requestAttr).getRequest();
	}

	/**
	 * 渲染视图, 将数据合并至模板中.
	 * 
	 * @param ftlName
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	private String mergeModelToTemlate(String ftlName, Map<String, Object> model) throws Exception {

		Template template = null;
		StringWriter swriter = new StringWriter();

		Configuration configuration = freemarkerConfigurer.getConfiguration();

		template = configuration.getTemplate(ftlName + getSuffix());
		template.process(model, swriter);

		return swriter.toString();
	}

	/**
	 * 根据名称取得Screen实例<br>
	 * <li>例: /demo.ftl => demoScreen.java</li>
	 * 
	 * @param template
	 * @return BaseScreen
	 */
	private BaseScreen getScreenBean(String name) {
		ScreenFactory factory = ScreenFactory.getInstance();
		return (BaseScreen) factory.getScreenBean(getScreenName(name));
	}

	/**
	 * 根据模板名称取得对应的Screen类名称
	 * 
	 * @param template
	 * @return screenName
	 */
	private String getScreenName(String template) {
		Matcher matcher = FTL_NAME_PTN.matcher(template);
		if (matcher.find()) {
			template = matcher.group(1);
		}

		return template + SCREEN_SUFFIX;
	}

}
