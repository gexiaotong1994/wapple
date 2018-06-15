package com.wapple.controller;

import java.awt.List;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.compiler.ast.StringL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.jta.UserTransactionAdapter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.Maps;
import com.wapple.common.Const;
import com.wapple.common.Json;
import com.wapple.enums.CookieEnum;
import com.wapple.enums.RedisEnum;
import com.wapple.mapper.UserDao;
import com.wapple.pojo.User;
import com.wapple.service.UserService;
import com.wapple.util.CookieUtil;
import com.wapple.util.JsonUtil;
import com.wapple.util.RedisUtil;

import lombok.val;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	private static final String[] VALID_STR = { Const.EMAIL, Const.PHONE, Const.USERNAME };

	@Autowired
	UserService userService;

	@RequestMapping("login")
	@ResponseBody
	public Json<User> login(String username, String password, HttpServletResponse response) {
		Json<User> json = userService.login(username, password);
		if (json.isSuccess()) {
			User user = json.getData();
			String userStr = JsonUtil.objToString(user);
			RedisEnum redisLogin = RedisEnum.USER_LOGIN;
			String redisKey = redisLogin.getKey() + username + System.currentTimeMillis();
			RedisUtil.setEx(redisKey, userStr, redisLogin.getExTime());
			// 将rediskey写入cookie中
			CookieUtil.write(response, CookieEnum.LOGIN.getKey(), redisKey, CookieEnum.LOGIN.getExTime());
		}
		return json;
	}

	@RequestMapping("register")
	public ModelAndView register(User user) {

		userService.register(user);
		Map<String, Object> map = Maps.newHashMap();

		map.put("username", user.getUsername());
		map.put("type", Const.PHONE);
		map.put("value", user.getPhone());
		String redirectUrl = "/wappleid/activation/to/{username}/{type}/{value}/";
		return new ModelAndView(new RedirectView(redirectUrl), map);
	}

	@RequestMapping("validate")
	@ResponseBody
	public Json<String> validate(String value, String type) {
		boolean suc = Arrays.asList(VALID_STR).contains(type);
		if (!suc) {
			return Json.fail("该类型不在数据字典中 输入有误！");
		}
		switch (type) {
		case Const.EMAIL:
			if (!userService.validate(value, Const.EMAIL)) {
				return Json.success("该邮箱可以使用！");
			} else {
				return Json.fail("该邮箱已经被注册  请更换");
			}
		case Const.PHONE:
			if (!userService.validate(value, Const.PHONE)) {
				return Json.success("该电话号码可以使用！");
			} else {
				return Json.fail("该电话号码被注册 请更换");
			}
		case Const.USERNAME:
			if (!userService.validate(value, Const.USERNAME)) {
				return Json.success("该用户名可以使用！");
			} else {
				return Json.fail("该用户名被注册 请更换");
			}
		default:
			return Json.fail("未知错误！");
		}

	}

}
