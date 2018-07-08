package com.wapple.service.impl;

import java.util.ArrayList;
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

import lombok.extern.slf4j.Slf4j;

@Service("cartService")
@Slf4j
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	ProductDao productDao;

	@Override
	public Json<String> addCart(Integer userId, Integer productId, Integer num) {
		Cart cart = this.cartDao.queryCartByUserIdAndProductId(userId, productId);
		if (cart != null) {
			int cartNum = cart.getNum() + num;
			if (cartNum > 5) {
				return Json.fail("单件商品最多添加5件到购物车!");
			}
			cart.setNum(cartNum);
			int count = cartDao.updateCartNum(cart);
			if (count > 0) {
				return Json.success("更新购物成功 先有商品共[" + cartNum + "]件");
			} else {
				return Json.fail("更新购物车失败!");
			}

		}
		cart = new Cart();
		cart.setNum(num);
		cart.setProductId(productId);
		cart.setUserId(userId);
		int rowCount = cartDao.insertCart(cart);
		if (rowCount > 0) {
			return Json.success("添加购物车成功!");
		} else {
			return Json.fail("添加购物车成功失败");
		}

	}

	@Override
	public CartVo list(int userId) {
		List<Cart> cartList = cartDao.queryCartListByUserId(userId);

		if (cartList.size() == 0) {
			return null;
		}
		List<CartListVo> cartListVos = new ArrayList<>();
		int countNum = 0;
		int countPrice = 0;
		for (Cart cart : cartList) {
			// 获取商品 现在的状态
			Product product = productDao.queryProductById(cart.getProductId());
			if (product == null) {
				int rowCount = cartDao.delete(cart);
				if (rowCount > 0) {
					log.info("购物车编号:{} 移除成功", cart.getId());
				} else {
					log.info("购物车编号:{} 移除失败", cart.getId());
				}

				continue;
			}
			CartListVo cartListVo = new CartListVo();
			int cartNum = cart.getNum();
			if (cartNum > product.getStock()) {
				// 如果购物车中的商品大一商品本身的库存
				cartNum = product.getStock();
				cart.setNum(cartNum);

				int rowCount = cartDao.updateCartNum(cart);
				if (rowCount > 0) {
					log.info("购物车编号:{} 修改成功", cart.getId());
				} else {
					log.info("购物车编号:{} 修改失败", cart.getId());
				}

			}

			int totalPrice = cartNum * product.getPrice();
			cartListVo.setId(cart.getId());
			cartListVo.setNum(cartNum);
			cartListVo.setPrdouctId(product.getId());
			cartListVo.setPrice(product.getPrice());
			cartListVo.setTotalPrice(totalPrice);
			cartListVo.setPrdouctName(product.getName());
			cartListVo.setImage("/images/img/" + product.getMainImage());
			cartListVos.add(cartListVo);
			countNum += cartNum;
			countPrice += totalPrice;

		}

		CartVo cartVo = new CartVo();
		cartVo.setCartListVos(cartListVos);
		cartVo.setCountNum(countNum);
		cartVo.setCountPrice(countPrice);

		return cartVo;
	}

}
