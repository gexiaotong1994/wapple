package com.wapple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wapple.common.Json;
import com.wapple.mapper.CartDao;
import com.wapple.mapper.ProductDao;
import com.wapple.pojo.Cart;
import com.wapple.pojo.Product;
import com.wapple.service.CartService;
import com.wapple.vo.CartListVo;
import com.wapple.vo.CartVo;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	ProductDao productDao;

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
	public Json<CartVo> list(int userId) {
		List<Cart> cartList = cartDao.queryCartListByUserId(userId);
		if (cartList.size() > 0) {
			CartVo cartVo = new CartVo();
			int totalPrice = 0;
			int totalNum = 0;
			List<CartListVo> cartListVos = Lists.newArrayList();
			for (Cart cart : cartList) {
				CartListVo cartListVo = new CartListVo();
				cartListVo.setId(cart.getId());
				Product product = productDao.queryProductById(cart.getProductId());
				if (product == null) {// 证明目前商品已经不存在了 直接将记录从购物车表中移除
					cartDao.delete(cart);
					continue;
				}
				int num;
				int productStock = product.getStock();
				if (productStock < cart.getNum()) {
					cart.setNum(productStock);
					cartDao.updateCartNum(cart);
					num = productStock;
				}
				num=cart.getNum();
				cartListVo.setId(cart.getId());
			    cartListVo.setPrdouctName(product.getName());
				
				
				

			}

			return null;
		}
		return Json.fail("您购物车为空");
	}

}
