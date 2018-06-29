package com.wapple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wapple.pojo.Product;
import com.wapple.service.ProductService;

@Controller
@RequestMapping("/product/")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping("item/{pname}/")
	public String detail(@PathVariable("pname") String pname, Model model) {
		Product product = productService.getProductByName(pname);
		model.addAttribute("product", product);
		return "product-detail";
	}

}
