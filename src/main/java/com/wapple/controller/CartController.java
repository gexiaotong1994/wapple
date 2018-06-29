package com.wapple.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wapple.common.Json;
import com.wapple.pojo.User;
import com.wapple.service.CartService;
import com.wapple.service.UserService;

@Controller
@RequestMapping("/cart/")
public class CartController {
	
	@Autowired
	UserService userService;

	@Autowired
	CartService cartService;
	
	@RequestMapping
	public String index() {

		return "cart";
	}
	

	
	@RequestMapping("add")
	@ResponseBody
	public Json<String> add(HttpServletRequest request,Integer productId,int num){
		User loginUser=this.userService.loginUser(request);
		if (loginUser==null) {
			return Json.fail("请登录后 再添加到购物车");
		}
	    boolean success= cartService.addCart(loginUser.getId(), productId, num);
	    if (success) {
	    	return Json.success("添加购物车成功");
		}
		return Json.fail("添加购物车失败");
	}
	
	

}
