package com.wapple.common;

import lombok.Getter;

@Getter
public class Json<T> {

	private boolean success;
	private String msg;
	private T data;

	@SuppressWarnings("unused")
	private Json() {
	}

	public Json(boolean suc) {
		this.success = suc;
	}

	public Json(boolean suc, T data) {
		this.success = suc;
		this.data = data;
	}

	public Json(boolean suc, String msg) {
		this.success = suc;
		this.msg = msg;
	}

	public static <T> Json<T> success() {
		return new Json<>(true);
	}

	public static <T> Json<T> success(T t) {
		return new Json<>(true, t);
	}

	public static <T> Json<T> fail() {
		return new Json<>(false);
	}

	public static <T> Json<T> fail(String msg) {
		return new Json<>(false, msg);
	}

}
