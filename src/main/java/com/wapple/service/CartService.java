package com.wapple.service;

public interface CartService {

	
	/**
	 * 添加到购物车
	 * @param userId
	 * @param productId
	 * @param num
	 * @return
	 */
	boolean addCart(Integer userId,Integer productId,Integer num);
	
	
	
	
	
}
