package com.wapple.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.wapple.pojo.User;
import com.wapple.service.UserService;
import com.wapple.util.DateTimeUtil;
import com.wapple.util.SpringUtil;
import com.wapple.vo.UserVo;

public class AdminController extends HttpServlet {
	
	UserService userService;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	       userService=SpringUtil.getBean(UserService.class);
	       
	}
	private static String VIEW(String viewName) {

		return new StringBuffer().append("/admin/").append(viewName).append(".jsp").toString();
	}

	private static String URI(String uri) {
		uri = uri.substring(9, uri.indexOf(".action"));
		return uri;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		String actionName = URI(request.getRequestURI());
		if (StringUtils.equals(actionName, "login")) {
			doGetLogin(request, response);
		} else {
			switch (actionName) {
			case "index":
				doGetIndex(request, response);
				break;
			case "user_list":
				doGetUserList(request, response);
				break;
			default:
				break;
			}

		}
	}

	protected void doGetLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("login")).forward(request, response);
	}

	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("index")).forward(request, response);
	}

	protected void doGetUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 List<User> userList= userService.userList();
		 List<UserVo> userVoList=Lists.newArrayList();
		 for (User user : userList) {
		     UserVo userVo=new UserVo();
		     BeanUtils.copyProperties(user, userVo);
		     userVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));
		     userVo.setUpdateTime(DateTimeUtil.dateToStr(user.getUpdateTime()));
		     userVoList.add(userVo);
		}
		request.setAttribute("userList", userVoList);
		request.getRequestDispatcher(VIEW("user_list")).forward(request, response);
	}
	
	
	
	
	
	

}
