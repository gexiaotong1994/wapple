package com.wapple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wapple.pojo.Product;
import com.wapple.service.CategoryService;
import com.wapple.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductController {
	
	
	
	@Autowired
	ProductService productService;
     
	@RequestMapping("/{cname}-{pname}-{pnu}")
	public String detail(Model model) {
		//Product product = productService.getProductByProductNameAndCategoryName(pname, categroy1);
		//model.addAttribute("product", product);
		return "product-detail";
	}
	
	
	
	
	
	

}
