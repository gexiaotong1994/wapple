package com.wapple.params;

import javax.swing.text.AbstractDocument.LeafElement;

import lombok.Data;

@Data
public class VideoListParams {

	private Integer limit;
	private Integer offset;

	public VideoListParams(int limit, int offset) {
		this.limit = limit;
		this.offset = offset;
	}

}
