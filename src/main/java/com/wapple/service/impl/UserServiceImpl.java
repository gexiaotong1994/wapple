package com.wapple.service.impl;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.wapple.common.Const;
import com.wapple.common.Json;
import com.wapple.enums.CookieEnum;
import com.wapple.enums.RedisEnum;
import com.wapple.enums.UserStatusEnum;
import com.wapple.mapper.UserDao;
import com.wapple.exception.VaildateException;
import com.wapple.mapper.UserMapper;
import com.wapple.pojo.User;
import com.wapple.service.UserService;
import com.wapple.util.CookieUtil;
import com.wapple.util.JsonUtil;
import com.wapple.util.MD5Util;
import com.wapple.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserDao userDao;

	@Override
	public Json<User> login(String username, String password) {
		if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
			return Json.fail("用户名密码不能为空");
		}

		if (!this.validate(username, Const.USERNAME)) {
			return Json.fail("用户名不存在");
		}

		User user = userMapper.queryUserByNameAndPass(username, MD5Util.MD5EncodeUtf8(password));
		if (user == null) {
			return Json.fail("密码错误 请检查");
		}

		if (user.getStatus() == UserStatusEnum.SUC.getCode()) {
			return Json.success(user);
		}
		
		if (user.getStatus()==UserStatusEnum.NOT_TOKE.getCode()) {
			return Json.fail(UserStatusEnum.NOT_TOKE.getValue());
		}
		
		if (user.getStatus()==UserStatusEnum.EXT_JIN.getCode()) {
			return Json.fail(UserStatusEnum.EXT_JIN.getValue());
		}
		

		return Json.fail("未知错误 登录失败");
	}

	public boolean validate(String value, String type) {

		return userDao.validate(type, value) > 0;

	}

	@Override
	public void register(User user) {
		if (this.validate(user.getUsername(), Const.USERNAME)) {
			throw new VaildateException("用户名重复");
		}
		if (this.validate(user.getPhone(), Const.PHONE)) {
			throw new VaildateException("电话号码重复 请更换");
		}
		if (this.validate(user.getEmail(), Const.EMAIL)) {
			throw new VaildateException("邮箱重复 请更换");
		}

		String password = user.getPassword();
		String passMd5 = MD5Util.MD5EncodeUtf8(password);
		user.setPassword(passMd5);
		user.setRole(0);
		user.setStatus(-1);
		int rowCount = userMapper.insertUser(user);
		if (rowCount < 1) {
			throw new VaildateException("邮箱重复 请更换");
		}

	}

	@Override
	public User loginUser(HttpServletRequest request) {
		String redisKey = CookieUtil.read(request, CookieEnum.LOGIN.getKey());
		if (StringUtils.isBlank(redisKey)) {
			return null;
		}
		String redisVal = RedisUtil.get(redisKey);
		if (StringUtils.isBlank(redisVal)) {
			return null;
		}
		RedisUtil.expire(redisKey, RedisEnum.USER_LOGIN.getExTime());

		return JsonUtil.stringToObj(redisVal, User.class);
	}

	@Override
	public List<User> userList() {
		
		return userMapper.queryUserList();
	}

}
