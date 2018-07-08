package com.wapple.controller;

import java.util.Random;

import javax.xml.bind.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wapple.common.Json;
import com.wapple.enums.UserStatusEnum;
import com.wapple.exception.VaildateException;
import com.wapple.pojo.User;
import com.wapple.service.UserService;
import com.wapple.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/wappleid/")
@Slf4j
public class WappleidController {

	private static final Random RANDOM = new Random();
	
	
	@Autowired
	UserService userService;

	@RequestMapping("login")
	public String signin() {
		return "login";
	}

	/**
	 * 注册新用户
	 * 
	 * @return
	 */
	@RequestMapping("register")
	public String register() {
		/**
		 * 注册新用户
		 */
		return "register";
	}

	@RequestMapping("forgot-pwd")
	public String forgot() {

		return "forgot-pwd";
	}

	@RequestMapping("register-success")
	public String regSuccess(String username, Model model) {
		model.addAttribute("username", username);
		return "message";
	}

	

	@RequestMapping(value = "activation/{username}/", method = RequestMethod.GET)
	public String activation(String phone,@PathVariable("username") String username) {
		int ma = RANDOM.nextInt(8999) + 1000;
		log.info("验证码:{}", ma);
		RedisUtil.setEx("activation_" + username, ma + "", 5 * 60);
		return "activation";
	}

	
	@RequestMapping(value = "activation/{username}", method = RequestMethod.POST)
	@ResponseBody
	public Json<String> activation2(@PathVariable("username") String username,String yamVal) {
		int ma = RANDOM.nextInt(8999) + 1000;
		log.info("验证码:{}", ma);
		String redisVal = RedisUtil.get("activation_" + username);
		if (StringUtils.isBlank(redisVal)) {
			throw new VaildateException("认证已经不存在或者已过期 请重新请求");
		}
		if (!StringUtils.equals(yamVal, redisVal)) {
			return Json.fail("验证码错误");
		}
		//开始执行修改逻辑 
		boolean success=userService.modifyUserStatus(username, UserStatusEnum.SUC.getCode());
		if (success) {
			return Json.success(username);
		}
		
		return Json.fail("用户名激活失败 请重新尝试");
		
	}
	
	
	@RequestMapping("retrieve")
	public String retrieve() {
		
		
		return "retrieve";
	}

}
