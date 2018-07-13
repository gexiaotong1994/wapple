package com.wapple.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.aspectj.weaver.patterns.ISignaturePattern;
import org.codehaus.jackson.map.ser.std.StdArraySerializers.IntArraySerializer;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.wapple.pojo.User;
import com.wapple.pojo.UserIndex;

public interface UserDao {
	/**
	 * 验证 用户名 手机号 邮箱是否重复
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	int validate(@Param("type") String type, @Param("value") String value);

	/**
	 * 插入用户主表记录
	 * 
	 * @param user
	 * @return
	 */
	int insertUser(@Param("user") UserIndex user);

	/**
	 * 插入用户权限表id
	 * 
	 * @param userId
	 * @return
	 */
	int insertUserRole(@Param("userId") int userId);

	/**
	 * 插入用户信息表
	 * 
	 * @param userId
	 * @return
	 */
	int insertUserMember(@Param("userId") int userId);

	/**
	 * 插入用户信息表
	 * 
	 * @param userId
	 * @return
	 */
	int insertUserInfo(@Param("userId") int userId);

	/**
	 * 根据用户名获取用户的主键
	 * 
	 * @param username
	 * @return
	 */
	int queryIdByUsername(@Param("username") String username);

	/**
	 * 更改用户状态
	 * @param username
	 * @param status
	 * @return
	 */
	int updateStateByUsername(@Param("username") String username, @Param("status") int status);
	
	
	/**
	 * 根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	UserIndex queryUserIndexByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	
	
	
	
	
	

}
