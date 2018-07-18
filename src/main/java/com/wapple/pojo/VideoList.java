package com.wapple.pojo;

import lombok.Data;

@Data
public class VideoList {
	private long id;
	private String name;
	private String nameCn;
	private int type;// 大类
	private String mtypes;// 小雷
	private int season;
	private String image;

}
