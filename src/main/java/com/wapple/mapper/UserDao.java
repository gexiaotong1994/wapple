package com.wapple.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
	/**
	 * 验证 用户名 手机号 邮箱是否重复
	 * @param type
	 * @param value
	 * @return
	 */
	int validate(@Param("type") String type, @Param("value") String value);
}
