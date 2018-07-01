package com.wapple.service;

import com.wapple.common.Json;
import com.wapple.vo.CartVo;

public interface CartService {

	
	/**
	 * 添加到购物车
	 * @param userId
	 * @param productId
	 * @param num
	 * @return
	 */
	boolean addCart(Integer userId,Integer productId,Integer num);
	
	
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	Json<CartVo> list(int userId);
	
	
	
	
	
	
}
