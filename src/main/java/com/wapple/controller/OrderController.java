package com.wapple.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/")
public class OrderController {
	// , method = RequestMethod.POST
	@RequestMapping(value = "choosePickUpType")
	public String choosePickUpType(int totalPrice, Model model) {
		model.addAttribute("totalPrice", totalPrice);
		return "choosePickUpType";
	}

}
