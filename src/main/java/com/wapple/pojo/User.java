package com.wapple.pojo;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.Date;

import lombok.Data;

@Data
public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private int status;
	private Date createTime;
	private Date updateTime;
	private UserRole userRole;
	private UserMember userMember;
	private UserInfo userInfo;
}
