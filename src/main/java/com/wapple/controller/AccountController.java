package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.core.ReturnInstruction.Return;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/iaccount/")
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
	public String signin() {
		return view("order");
	}

	@RequestMapping("personal/information")
	public String personalInformation() {

		return view("order");
	}

	@RequestMapping("address")
	public String addressList() {
		return view("address");
	}

	@RequestMapping("address/new")
	public String addressNew() {
		return "account/address_new";
	}

}
