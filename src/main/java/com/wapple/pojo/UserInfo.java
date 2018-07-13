package com.wapple.pojo;

import lombok.Data;

@Data
public class UserInfo {

	private int id;
	private int userId;
	private String usernameCn;
	private int gender;

	public UserInfo(int userId) {
		this.userId = userId;
	}

}
