package com.wapple.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

public class SpringUtil extends WebApplicationObjectSupport {

	private static ApplicationContext applicationContext;

	@Override
	protected void initApplicationContext(ApplicationContext context) {
		if (applicationContext == null) {
			applicationContext = context;
		}
	}

	public static <T> T getBean(Class<T> cls) {

		return applicationContext.getBean(cls);
	}


}
