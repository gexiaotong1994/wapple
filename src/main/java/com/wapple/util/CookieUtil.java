package com.wapple.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gxt
 */
public class CookieUtil {
	private static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

	

	
	public static String read(HttpServletRequest request,String key) {
		Cookie[] cks = request.getCookies();
		if (cks != null) {
			for (Cookie ck : cks) {
				log.info("read cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
				if (StringUtils.equals(ck.getName(), key)) {
					log.info("return cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
					return ck.getValue();
				}
			}
		}
		return null;
	}

	public static void write(HttpServletResponse response,String key, String value) {
		Cookie ck = new Cookie(key, value);
		// ck.setDomain(COOKIE_DOMAIN);
		ck.setPath("/");// 代表设置在根目录
		//TODO 现在 未知以后解决 ck.setHttpOnly(true);
		// 单位是秒。
		// 如果这个maxage不设置的话，cookie就不会写入硬盘，而是写在内存。只在当前页面有效。
		ck.setMaxAge(60 * 60 * 24);// 如果是-1，代表永久
		log.info("write cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
		response.addCookie(ck);
	}
	
	
	// 删除cooki
	public static void del(HttpServletRequest request, HttpServletResponse response,String key) {
		Cookie[] cks = request.getCookies();
		if (cks != null) {
			for (Cookie ck : cks) {
				if (StringUtils.equals(ck.getName(), key)) {
					// ck.setDomain(COOKIE_DOMAIN);
					ck.setPath("/");
					ck.setMaxAge(0);// 设置成0，代表删除此cookie。
					log.info("del cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
					response.addCookie(ck);
					return;
				}
			}
		}
	}

}
