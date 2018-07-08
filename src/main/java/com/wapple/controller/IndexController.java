package com.wapple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wapple.pojo.Page;
import com.wapple.pojo.Product;
import com.wapple.service.ProductService;

@Controller
public class IndexController {
	
	@Autowired
	ProductService productService;

	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Product> list=productService.productList(new Page(0, 3));
		
		model.addAttribute("pList", list);
		return "index";
		
	}
	
	
	@RequestMapping("/lin/{par:\\-{3,5}}/")
	public String index1(Model model) {
		
		return "index";
		
	}
	
	
	@RequestMapping("/exception")
	public String error() {
		
		return "exception";
	}
}
