package com.wapple.vo;

import java.util.List;

import lombok.Data;

@Data
public class CartVo {
    private int totalNum;//总数量 
    private int totalPrice;//总价
    private List<CartListVo> CartListVos;
    
}
