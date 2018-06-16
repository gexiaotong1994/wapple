package com.wapple.vo;

import java.util.Date;

import lombok.Data;
@Data
public class UserVo {
	
	private int id;
    private String username;
    private String password;
    private String usernameCn;
    private String email;
    private String phone;
    private String question;
    private String answer;
    private int status;
    private int role;
    private String createTime;
    private String  updateTime;
}
