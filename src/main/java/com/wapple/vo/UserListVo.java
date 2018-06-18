package com.wapple.vo;

import lombok.Data;

@Data
public class UserListVo {
	private int id;
    private String username;
    private String usernameCn;
    private String email;
    private String phone;
    private String createTime;
    private String statusMsg;
    
}
