package com.wapple.controller;

import java.util.Random;

import javax.xml.bind.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wapple.common.Json;
import com.wapple.exception.VaildateException;
import com.wapple.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/wappleid/")
@Slf4j
public class WappleidController {

	private static final Random RANDOM = new Random();

	@RequestMapping("create/customer/new")
	public String createNew() {
		/**
		 * 注册新用户
		 */
		return "register";
	}

	@RequestMapping("forgot/password")
	public String forgot() {

		return "forgot";
	}
	
	
	@RequestMapping("/register-success/{username}/")
	public String regSuccess(@PathVariable("username") String username,Model model) {
	    model.addAttribute("username", username);
		return "message";
	}

	

	@RequestMapping("password/verify/security-question")
	public String securityQuestion(String wappleid) {
		if (StringUtils.isBlank(wappleid)) {
			return "msg";
		}
		return null;
	}

	@RequestMapping(value = "activation/to/{username}/{type}/{value}/", method = RequestMethod.GET)
	public String activation(@PathVariable("type") String type, @PathVariable("value") String value,
			@PathVariable("username") String username) {
		log.info("参数是:{}-{}-{}", type, value, username);
		int ma = RANDOM.nextInt(8999) + 1000;
		log.info("验证码:{}", ma);
		RedisUtil.setEx("activation_" + username, ma + "", 5 * 60);
		return "activation";
	}

	@RequestMapping(value = "activation/to/{username}/{type}/{value}/", method = RequestMethod.POST)
	@ResponseBody
	public Json<String> activation(@PathVariable("type") String type, @PathVariable("value") String value,
			@PathVariable("username") String username, String yamVal) {
		log.info("参数是:{}-{}-{}", type, value, username);
		int ma = RANDOM.nextInt(8999) + 1000;
		log.info("验证码:{}", ma);
		String redisVal = RedisUtil.get("activation_" + username);
		if (StringUtils.isBlank(redisVal)) {
			throw new VaildateException("认证已经不存在或者已过期 请重新请求");
		}
		if (!StringUtils.equals(yamVal, redisVal)) {
			return Json.fail("验证码错误");
		}
		return Json.success(username);
	}
	
	

}
