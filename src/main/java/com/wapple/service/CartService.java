package com.wapple.service;

import com.wapple.common.Json;
import com.wapple.vo.CartVo;

public interface CartService {

	/**
	 * 添加到购物车
	 * 
	 * @param userId
	 * @param productId
	 * @param num
	 * @return
	 */
	Json<String> addCart(Integer userId, Integer productId, Integer num);

	/**
	 * 获取购物车的所有列表
	 * 
	 * @param userId
	 * @return
	 */
	CartVo list(int userId);

	/**
	 * 修改购物车中的数量
	 * 
	 * @param userId
	 * @param cartId
	 * @param num
	 * @return
	 */
	void changeCartNum(int userId, long cartId, int num);
	
	
	

}
