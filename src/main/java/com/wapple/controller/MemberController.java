package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {

	private String mem_view(String viewName) {

		return new StringBuffer().append("forward:/viewpage/member/").append(viewName).append(".html").toString();
	}
	
    

	@RequestMapping("main")
	public String index() {

		return "member/main";
	}

}
