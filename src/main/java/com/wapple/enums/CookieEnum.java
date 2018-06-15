package com.wapple.enums;

import lombok.Getter;

@Getter
public enum CookieEnum {

	LOGIN("wapple_cookie_user",24*60*1000);
	
	private CookieEnum() {
		// TODO Auto-generated constructor stub
	}
	
	CookieEnum(String key,int exTime){
		this.key=key;
		this.exTime=exTime;
	}
	private String key;
	private int exTime;
	
	
}
