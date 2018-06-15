package com.wapple.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wapple.pojo.User;

@Mapper
public interface UserMapper {

	
	
	@Results(id = "userMapperMap", value = { 
			@Result(column = "id", property = "id", id = true),
			@Result(column = "username_cn", property = "usernameCn"),
			@Result(column = "create_time", property = "createTime"),
			@Result(column = "update_time", property = "updateTime") })
	@Select("  SELECT u.* FROM t_wap_user u WHERE u.username=#{username} AND u.password=#{password}")
	User queryUserByNameAndPass(@Param("username") String username, @Param("password") String password);

	
	
	@Insert("INSERT INTO t_wap_user(`username`,`password`,`username_cn`,email,phone,question,answer) "
			+ "VALUE(#{username},#{password},#{usernameCn},#{email},#{phone},#{question},#{answer})")
	int insertUser(User user);
	
	
	
	@Update("update t_wap_user set status=#{status} where username=#{username}")
	int updateUserStatus(@Param("status") int status,@Param("username") String username);
	
	
	

}
