package com.wapple.service.impl;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.wapple.common.Const;
import com.wapple.common.Json;
import com.wapple.enums.CookieEnum;
import com.wapple.enums.RedisEnum;
import com.wapple.enums.UserStatusEnum;
import com.wapple.mapper.UserDao;
import com.wapple.exception.VaildateException;
import com.wapple.pojo.User;
import com.wapple.pojo.UserIndex;
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
	UserDao userDao;

	@Override
	public Json<UserIndex> login(String username, String password) {

		if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
			return Json.fail("用户名密码不能为空");
		}

		if (!this.validate(username, Const.USERNAME)) {
			return Json.fail("用户名不存在");
		}

		UserIndex userIndex = userDao.queryUserIndexByUsernameAndPassword(username, MD5Util.MD5EncodeUtf8(password));
		if (userIndex == null) {
			return Json.fail("密码错误 请检查");
		}
		if (userIndex.getStatus() == UserStatusEnum.SUC.getCode()) {
			return Json.success(userIndex);
		}

		if (userIndex.getStatus() == UserStatusEnum.NOT_TOKE.getCode()) {
			return Json.fail(UserStatusEnum.NOT_TOKE.getValue());
		}

		if (userIndex.getStatus() == UserStatusEnum.EXT_JIN.getCode()) {
			return Json.fail(UserStatusEnum.EXT_JIN.getValue());
		}

		return Json.fail("未知错误 登录失败");
	}

	public boolean validate(String value, String type) {

		return userDao.validate(type, value) > 0;

	}

	/**
	 * caognckansdkj jblskljklllasl;sl
	 */

	@Override
	@Transactional
	public void register(UserIndex userIndex) {
		/*
		 * if (this.validate(userIndex.getUsername(), Const.USERNAME)) { throw new
		 * VaildateException("用户名重复"); } if (this.validate(userIndex.getPhone(),
		 * Const.PHONE)) { throw new VaildateException("电话号码重复 请更换"); } if
		 * (this.validate(userIndex.getEmail(), Const.EMAIL)) { throw new
		 * VaildateException("邮箱重复 请更换"); }
		 * 
		 */

		String password = userIndex.getPassword();
		String passMd5 = MD5Util.MD5EncodeUtf8(password);
		userIndex.setPassword(passMd5);
		userIndex.setStatus(-1);
		try {
			userDao.insertUser(userIndex);
			int id = userDao.queryIdByUsername(userIndex.getUsername());
			userDao.insertUserInfo(id);
			userDao.insertUserMember(id);
			userDao.insertUserRole(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
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

		return null;
	}

	@Override
	public User getUserByUserId(int userid) {

		return null;
	}

	@Override
	public Json<String> getQuestionByUsername(String username) {
		/*
		 * if (StringUtils.isBlank(username)) { return Json.fail("用户名不能为空"); }
		 * 
		 * if (!this.validate(username, Const.USERNAME)) { return
		 * Json.fail("用户名不存在 请检查"); }
		 * 
		 * String question = userMapper.queryQuestionByUsername(username); if
		 * (StringUtils.isBlank(question)) { return Json.fail("未找到对应的密保问题 请联系管理员"); }
		 */
		return Json.success("");
	}

	@Override
	public boolean checkAnswer(String username, String question, String answer) {

		return true;
	}

	@Override
	public boolean modifyUserStatus(String username, int status) {

		return userDao.updateStateByUsername(username, status) == 1;
	}

	@Override
	public User adminUserLogin(HttpServletRequest request) {
		CookieUtil.read(request, CookieEnum.LOGIN_ADMIN.getKey());
		return null;
	}

	@Override
	public Json<User> adminLogin(String username, String password) {
		/*
		 * Json<User> json = this.login(username, password); if (!json.isSuccess()) {
		 * return json; }
		 */

		return null;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.del(request, response, CookieEnum.LOGIN.getKey());

	}

}
