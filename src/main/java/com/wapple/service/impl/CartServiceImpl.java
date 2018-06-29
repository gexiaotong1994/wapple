package com.wapple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wapple.common.Json;
import com.wapple.mapper.CartDao;
import com.wapple.pojo.Cart;
import com.wapple.service.CartService;
import com.wapple.vo.CartListVo;
import com.wapple.vo.CartVo;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Override
	public boolean addCart(Integer userId, Integer productId, Integer num) {
		Cart cart = this.cartDao.queryCartByUserIdAndProductId(userId, productId);
		if (cart != null) {
			// 直接修改数量
			cart.setNum(cart.getNum() + num);
			return cartDao.updateCartNum(cart) == 1;
		}
		cart = new Cart();
		cart.setNum(num);
		cart.setProductId(productId);
		cart.setUserId(userId);

		return cartDao.insertCart(cart) == 1;
	}

	@Override
	public CartVo list(int userId) {
		List<Cart> cartList = cartDao.queryCartListByUserId(userId);
		if (cartList.size() > 0) {
			CartVo cartVo = new CartVo();
			int totalPrice = 0;
			int totalNum = 0;
			List<CartListVo> cartListVos = Lists.newArrayList();
			for (Cart cart : cartList) {
				CartListVo cartListVo = new CartListVo();
				cartListVo.setId(cart.getId());
				cartListVo.setNum(cart.getNum());
				 
			}

			return cartVo;
		}
		return null;
	}

}
