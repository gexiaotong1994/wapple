package com.wapple.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	
	
	
	/**
	 * 获取登录用户  
	 * null 表示未登录
	 * @param request
	 * @return
	 */
	User loginUser(HttpServletRequest request);
	
	
	
	/**
	 * userList 
	 * 获取全部的用户列表
	 * (无参数 默认获取数据表中存在的用户)
	 * @return
	 */
	List<User> userList();
	
	
	
	/**
	 * 根据主键获取用户实体
	 * @param userid
	 * @return
	 */
	User getUserByUserId(int userid);
	
	
    
	/**
	 * 根据用户名获取密保问题
	 * @param username
	 * @return
	 */
	Json<String> getQuestionByUsername(String username);
	
	
	
	/**
	 * 
	 * @param username
	 * @param question
	 * @param answer
	 * @return 
	 */
	boolean checkAnswer(String username,String question,String answer);

	
	
	
	
	
	
}
