package com.wapple.service;

import com.wapple.common.Json;
import com.wapple.pojo.User;

public interface UserService {

	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	Json<User> login(String username,String password);
	
	
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	void register(User user);
	
	
	/**
	 * 验证各类值在数据库中是否存在
	 * @param value
	 * @param type
	 * @return
	 */
	boolean validate(String value, String type);
	
	
	
	
	
}