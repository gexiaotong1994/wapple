package com.wapple.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum RedisEnum {
	USER_LOGIN("redis_login_","用户 ",60*15),
	ADMMIN_LOGIN("admin_login_","管理员用户 ",60*15);
	private String key;
	private int exTime;
	private String msg;
	
	private RedisEnum() {
		// TODO Auto-generated constructor stub
	}
	
	private  RedisEnum(String key,String msg,int exTime) {
		this.key=key;
		this.msg=msg;
		this.exTime=exTime;
	}
	
}
