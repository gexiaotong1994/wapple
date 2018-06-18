package com.wapple.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

	SUC(0, "状态正常"), NOT_TOKE(-1, "未认证 请尽快认证"), EXT_JIN(-2, "账户已经被禁用请联系管理员");
	private int code;
	private String value;

	private UserStatusEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getValueByCode(int code) {

		for (UserStatusEnum userStatusEnum : values()) {
               if (code==userStatusEnum.getCode()) {
				  return userStatusEnum.getValue();
			}
		}
		return "状态错误";
	}
	
	

}
