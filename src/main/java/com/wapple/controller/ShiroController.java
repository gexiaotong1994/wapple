package com.wapple.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/shiro/")
public class ShiroController {

	@RequestMapping("subLogin")
	public String login(String username, String password, String t) {
		Subject subject = SecurityUtils.getSubject();
	    
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
		if (StringUtils.isNotBlank(t) && StringUtils.equals(t, "admin")) {
              return "redirect:/admin/index";
		}
		return null;
	}

}
