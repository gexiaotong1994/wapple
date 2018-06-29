package com.wapple.mapper;

import org.apache.ibatis.annotations.Param;

import com.wapple.pojo.Cart;

public interface CartDao {
	
	
	int insertCart(Cart cart);
	
	
	
	Cart queryCartByUserIdAndProductId(@Param("userId")int userId,@Param("productId")int productId);
	
	
	int updateCartNum(Cart cart);
	

}
