package com.insigma.screen.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Screen工厂类
 * 
 * @author zbq
 * @date 2010-05-28
 */
public class ScreenFactory implements ApplicationContextAware {

	private boolean alreadyInit = false;

	private ApplicationContext appContext;

	private ScreenFactory() {

	}

	private static class SingletonHolder {
		private static ScreenFactory factory = new ScreenFactory();
	}

	/**
	 * 获得Screen工厂静态实例
	 */
	public static ScreenFactory getInstance() {
		return SingletonHolder.factory;
	}

	/**
	 * 根据名称取得Screen对象
	 * 
	 * @param beanName
	 * @return Screen对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getScreenBean(String beanName) {
		if (StringUtils.isBlank(beanName)) {
			return null;
		}
		return (T) appContext.getBean(beanName);
	}

	/**
	 * 设置ApplicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ScreenFactory screenFactory = ScreenFactory.SingletonHolder.factory;
		if (!screenFactory.alreadyInit) {
			screenFactory.appContext = applicationContext;
			screenFactory.alreadyInit = true;
		}
	}
}
