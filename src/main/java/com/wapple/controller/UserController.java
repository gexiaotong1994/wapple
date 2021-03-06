package com.wapple.controller;

import java.awt.List;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.compiler.ast.StringL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.jta.UserTransactionAdapter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.Maps;
import com.wapple.common.Const;
import com.wapple.common.Json;
import com.wapple.enums.CookieEnum;
import com.wapple.enums.RedisEnum;
import com.wapple.enums.UserStatusEnum;
import com.wapple.mapper.UserDao;
import com.wapple.pojo.User;
import com.wapple.pojo.UserIndex;
import com.wapple.service.UserService;
import com.wapple.util.CookieUtil;
import com.wapple.util.JsonUtil;
import com.wapple.util.RedisUtil;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/user/")
@Slf4j
public class UserController {

	private static final String[] VALID_STR = { Const.EMAIL, Const.PHONE, Const.USERNAME };

	@Autowired
	UserService userService;

	@RequestMapping("login")
	public Json<UserIndex> login(String username, String password, HttpServletResponse response) {
		Json<UserIndex> json = userService.login(username, password);
		if (json.isSuccess()) {
			UserIndex user = json.getData();
			String userStr = JsonUtil.objToString(user);
			RedisEnum redisLogin = RedisEnum.USER_LOGIN;
			String redisKey = redisLogin.getKey() + username + System.currentTimeMillis();
			RedisUtil.setEx(redisKey, userStr, redisLogin.getExTime());
			//将rediskey写入cookie中
			CookieUtil.write(response, CookieEnum.LOGIN.getKey(), redisKey, CookieEnum.LOGIN.getExTime());
		}
		return json;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public Json<String> register(UserIndex userIndex) {
		userService.register(userIndex);
		String name = userIndex.getUsername();
		this.writeCodeToRedis(name);
		return Json.success(name);
	}

	@RequestMapping("validate")
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

	@RequestMapping("activation")
	public Json<String> activation(String username, String code) {
		String val = this.readCodeToRedis(username);
		if (StringUtils.isBlank(val)) {
			return Json.fail("验证码已失效 ");
		}
		if (!StringUtils.equals(val, code)) {
			return Json.fail("验证码错误 ");
		}
		boolean suc = userService.modifyUserStatus(username, UserStatusEnum.SUC.getCode());
		if (suc) {
			return Json.success("认证成功请登录");
		}
		return Json.fail("验证失败 ");
	}

	@RequestMapping("get_question_by_username")
	public Json<String> getQuestionByUsername(String username) {

		return userService.getQuestionByUsername(username);
	}

	@RequestMapping("check_login")
	public Json<String> checkLogin(HttpServletRequest request) {
		User user = userService.loginUser(request);
		if (user == null) {
			return Json.fail();
		}
		return Json.success(user.getUsername());
	}

	@RequestMapping("check_answer")
	public Json<String> checkAnswer(String username, String question, String answer) {
		boolean success = userService.checkAnswer(username, question, answer);
		if (success) {
			return Json.success();
		}
		return Json.fail("密保问题错误!");
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return "redirect:/";
	}

	private void writeCodeToRedis(String username) {
		int ma = new Random().nextInt(8999) + 1000;
		log.info("验证码:{}", ma);
		RedisUtil.setEx("activation_" + username, ma + "", 5 * 60);
	}

	private String readCodeToRedis(String username) {
		return RedisUtil.get("activation_" + username);

	}

}
