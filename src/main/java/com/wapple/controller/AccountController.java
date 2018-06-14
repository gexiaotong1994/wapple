package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.core.ReturnInstruction.Return;

@Controller
@RequestMapping("/myaccount/")
public class AccountController {

	@RequestMapping("signin")
	public String signin() {
		
		return "login";
	}
	
	
	
	@RequestMapping("index")
	public String index() {
		
		return "account/index";
	}
	                  
	

	
	
	


}
