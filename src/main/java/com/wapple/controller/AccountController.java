package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.core.ReturnInstruction.Return;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {

	@RequestMapping("/signin")
	public String signin() {
		
		return "login";
	}
	
	
	
	@RequestMapping("/main/")
	public String index() {
	   
		return "account/index";
	}
	
  
	
	
	
	                  
	

	
	
	


}
