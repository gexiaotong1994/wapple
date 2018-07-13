package com.wapple.pojo;

import lombok.Data;

@Data
public class UserRole {
     private int id;
     private int userId;
     private int admin;
     private int level;
     
     
     public UserRole(int userId) {
 		this.userId = userId;
 	}
     
}
