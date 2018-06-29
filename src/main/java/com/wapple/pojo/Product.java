package com.wapple.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	
   private int  id;
   private int categoryId;
   private String name;
   private String title;
   private String mainImage;
   private int stock;
   private int price;
   private int sales;
   private String desc;
   private int status;
   private Date createTime;
   private Date updateTime;
   private Category category;
   
  
}
