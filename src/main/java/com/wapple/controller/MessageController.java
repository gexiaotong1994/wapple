package com.wapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/message/")
@Controller
public class MessageController {

	@RequestMapping("{msg}")
	public String message(@PathVariable("msg") String msg, Model model) {
		model.addAttribute("message", msg);
		return "msg";
	}
}
