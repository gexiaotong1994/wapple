package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wapple.controller.CartController;
import com.wapple.pojo.Cart;

public interface CartDao {

	int insertCart(Cart cart);

	Cart queryCartByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);

	int updateCartNum(Cart cart);

	List<Cart> queryCartListByUserId(int userId);

	//TODO
	int delete(Cart cart);

}
