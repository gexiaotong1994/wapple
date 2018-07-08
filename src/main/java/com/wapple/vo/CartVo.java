package com.wapple.vo;

import java.util.List;

import lombok.Data;

@Data
public class CartVo {
	private int countNum;// 总数量
	private int countPrice;// 总价
	private int yunfei;
	private List<CartListVo> cartListVos;

}






