package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@RequestMapping("main")
	public String index() {

		return "member/main";
	}

}
