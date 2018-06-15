package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.core.ReturnInstruction.Return;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myaccount/")
@Slf4j
public class AccountController {

	@RequestMapping("signin")
	public String signin() {
		
		return "login";
	}
	
	
	
	@RequestMapping("index")
	public String index() {
	    log.info("json");
		return "account/index";
	}
	
	
	
	                  
	

	
	
	


}
