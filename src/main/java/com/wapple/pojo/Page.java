package com.wapple.pojo;

import lombok.Data;

@Data
public class Page {
	
	private int limit;
	private int offset;
	
	public Page() {
		
	}

	public Page(int limit, int offset) {
		this.limit = limit;
		this.offset = offset;
	}
	
	
	

}
