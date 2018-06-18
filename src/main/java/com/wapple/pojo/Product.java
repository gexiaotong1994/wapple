package com.wapple.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	
   private int id;
   private String name;
   private int categoryId;
   private String title;
   private int stock;
   private int price;
   private int sales;
   private String desc;
   private Date createTime;
   private Date updateTime;
   private Category category;
   
  
}
