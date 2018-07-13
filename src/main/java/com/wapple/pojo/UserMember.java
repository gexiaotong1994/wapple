package com.wapple.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class UserMember {

	private int id;
	private int userId;
	private int level;
	private Date endTime;

	public UserMember(int userId) {
		this.userId = userId;
	}

}
