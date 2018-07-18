package com.wapple.controller;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import freemarker.core.ReturnInstruction.Return;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/account/")
@Slf4j
public class AccountController {

	private static final String view(String viewName) {
		return new StringBuffer("account/").append(viewName).toString();
	}
	
	
	@RequestMapping
	public String index() {
		
		return view("index");
	}

	@RequestMapping("order")
	public String order(long id,String pas) {
		
		return view("order");
	}

	@RequestMapping("personal")
	public String personal(String type){

		return view("order");
	}

	@RequestMapping("addressList")
	public String addressList() {
		
		return view("address");
	}

	@RequestMapping("address/new")
	public String addressNew() {
		return "account/address_new";
	}

}
