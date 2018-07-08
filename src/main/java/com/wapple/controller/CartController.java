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
import com.wapple.vo.CartVo;

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
	public Json<String> add(HttpServletRequest request, Integer productId, int num) {
		User loginUser = this.userService.loginUser(request);
		if (loginUser == null) {
			return Json.fail("请登录后 再添加到购物车");
		}
		return cartService.addCart(loginUser.getId(), productId, num);
	}

	@RequestMapping("list")
	@ResponseBody
	public Json<CartVo> list(HttpServletRequest request) {
		User loginUser = this.userService.loginUser(request);
		if (loginUser == null) {
			return Json.fail("未登录 请登录后查看");
		}
		CartVo cartVo = cartService.list(loginUser.getId());
		if (cartVo == null) {
			return Json.fail("购物车为空");
		}

		return Json.success(cartVo);
	}

}
