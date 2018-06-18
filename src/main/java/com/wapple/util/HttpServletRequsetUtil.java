package com.wapple.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 获取requset中的各种数据
 * 
 * @author g1994
 *
 */
public class HttpServletRequsetUtil {

	public static int getInt(HttpServletRequest request, String value) {
		String val = request.getParameter(value);
		if (StringUtils.isBlank(val)) {
			return -1;
		}
		return Integer.parseInt(val);
	}

	public static String getString(HttpServletRequest request, String value) {
		return request.getParameter(value);
	}

}
