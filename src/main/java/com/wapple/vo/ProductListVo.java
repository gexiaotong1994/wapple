package com.wapple.vo;

import lombok.Data;

@Data
public class ProductListVo {

    private int id;
    private String vname;
    private String vtitle;
    private int price;
    private int stock;
    private int sales;
    private int status;
    private String createTime;
    private String updateTime;
    private boolean image;
    
	
}
