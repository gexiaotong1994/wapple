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
     
	
	
	
	
	
	
	

}
