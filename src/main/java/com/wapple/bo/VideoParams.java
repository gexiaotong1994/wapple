package com.wapple.bo;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

import com.wapple.pojo.VideoType;

import lombok.Data;

@Data
public class VideoParams {
	
	private String name;
	private int season;
	private VideoType videoType;
	
	

	public VideoParams(String videoTypeName) {
		 this.videoType=new VideoType();
		 this.videoType.setName(videoTypeName);
	}
	
	public VideoParams(int videoTypeId) {
		 this.videoType=new VideoType();
		 this.videoType.setId(videoTypeId);
	}
	
	
	
	
	
	

}
