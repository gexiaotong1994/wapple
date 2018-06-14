package com.wapple.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
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
    private Date createTime;
    private Date updateTime;
    
    
}
