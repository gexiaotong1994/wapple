package com.wapple.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.soap.Detail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.wapple.common.Json;
import com.wapple.enums.UserStatusEnum;
import com.wapple.pojo.Product;
import com.wapple.pojo.User;
import com.wapple.service.ProductService;
import com.wapple.service.UserService;
import com.wapple.util.DateTimeUtil;
import com.wapple.vo.ProductListVo;
import com.wapple.vo.UserListVo;

@Controller
@RequestMapping("/back/")
public class AdminController {
	
	
	@Autowired
	public ProductService productService;

	@Autowired
	public UserService userService;

	public static final String view(String viewName) {
		return new StringBuffer().append("forward:").append("/admin/admin_").append(viewName).append(".jsp").toString();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return view("login");
	}
	
	@RequestMapping(value = "product/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Json<String> detail(Product product){
	    System.out.println(product);
		return Json.fail();
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String username, String password, String returnUrl) throws UnsupportedEncodingException {
		Json<User> json = userService.login(username, password);
		if (!json.isSuccess()) {
			return "redirect:login#" + URLEncoder.encode(json.getMsg(), "UTF-8").toString();

		}
		User user = json.getData();
		
		return view("login");
	}

	@RequestMapping("index")
	public String index() {
		return view("index");
	}

	@RequestMapping(value = "product", method = RequestMethod.GET)
	public String product(Model model) {
		List<Product> products = productService.products();
		List<ProductListVo> productListVos = Lists.newArrayList();
		for (Product product : products) {
			productListVos.add(this.proudctToProductListVo(product));
		}
		model.addAttribute("productListVos", productListVos);
		return view("product");
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String user(Model model) {
		List<User> userList = userService.userList();
		List<UserListVo> userVoList = Lists.newArrayList();
		for (User user : userList) {
			UserListVo userListVo = new UserListVo();
			BeanUtils.copyProperties(user, userListVo);
			userListVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));
			userListVo.setStatusMsg(UserStatusEnum.getValueByCode(user.getStatus()));
			userVoList.add(userListVo);
		}
		model.addAttribute("userList", userVoList);
		return view("user");
	}

	@RequestMapping(value = "product/{id}", method = RequestMethod.GET)
	public String productDetail(Model model, @PathVariable("id") int productId) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		return view("product_detail");
	}

	/**
	 * 获取用户详情
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public String userDetail(Model model, @PathVariable("id") int userId) { 
		//pon..dmvldkj 
		User user = userService.getUserByUserId(userId);
		model.addAttribute("user", user);
		return view("user_detail");
	}

	public ProductListVo proudctToProductListVo(Product p) {
		ProductListVo productListVo = new ProductListVo();
		BeanUtils.copyProperties(p, productListVo);
		productListVo.setVname(p.getName().substring(0, 10));
		productListVo.setCreateTime(DateTimeUtil.dateToStr(p.getCreateTime()));
		productListVo.setUpdateTime(DateTimeUtil.dateToStr(p.getUpdateTime()));
		if (StringUtils.equals(p.getMainImage(), "default.jpg")) {
			productListVo.setImage(false);
		} else {
			productListVo.setImage(true);
		}

		return productListVo;

	}

}
