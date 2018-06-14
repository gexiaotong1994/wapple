package com.wapple.exception;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.Maps;

@ControllerAdvice
public class HandlerGoException {

	@ExceptionHandler(VaildateException.class)
	public ModelAndView vaild(RuntimeException e) {
		 Map<String, Object> map=Maps.newHashMap();
		 map.put("e", e.getMessage());
		return new ModelAndView(new RedirectView("/exception/"),map);
	}
	
	
	

}
