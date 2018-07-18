package com.wapple.vo;

import lombok.Data;

@Data
public class VideoListVo {
	
	private long id;
	private String name;
	private String nameCn;
	private int type;// 大类
	private String mtypeStr;// 小雷
	private int season;
	private String image;
	private String url;
	
	

}
