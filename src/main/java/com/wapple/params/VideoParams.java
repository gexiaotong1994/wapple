package com.wapple.params;

import com.wapple.pojo.VideoType;

import ch.qos.logback.core.net.LoginAuthenticator;
import lombok.Data;

@Data
public class VideoParams {

	private String name;
	private VideoType type;
	private int season;
}
