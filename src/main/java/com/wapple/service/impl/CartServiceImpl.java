package com.wapple.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.mapper.CartDao;
import com.wapple.pojo.Cart;
import com.wapple.service.CartService;

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
	
	
	
	
	
	
	

}
