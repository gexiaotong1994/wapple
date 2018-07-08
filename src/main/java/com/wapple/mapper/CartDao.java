package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wapple.controller.CartController;
import com.wapple.pojo.Cart;

public interface CartDao {
    
	/**
	 * 向购物车表中插入记录
	 * @param cart
	 * @return
	 */
	int insertCart(Cart cart);

	/**
	 * 根据用户id和商品id 查询在购物车中是否有记录
	 * @param userId
	 * @param productId
	 * @return
	 */
	Cart queryCartByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);

	
	/**
	 * 修改购物车的数量
	 * @param cart
	 * @return
	 */
	int updateCartNum(Cart cart);
	
	

	/**
	 * 根据userid查询个人购物车列表
	 * @param userId
	 * @return
	 */
	List<Cart> queryCartListByUserId(int userId);

	/**
	 * 删除购物车记录
	 * @param cart
	 * @return
	 */
	int delete(Cart cart);

}
