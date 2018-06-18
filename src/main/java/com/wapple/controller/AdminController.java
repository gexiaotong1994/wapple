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
import com.wapple.enums.UserStatusEnum;
import com.wapple.pojo.User;
import com.wapple.service.UserService;
import com.wapple.util.DateTimeUtil;
import com.wapple.util.HttpServletRequsetUtil;
import com.wapple.util.SpringUtil;
import com.wapple.vo.UserListVo;
import com.wapple.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminController extends HttpServlet {

	private static final UserService userService= SpringUtil.getBean(UserService.class);
    
	

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
		log.info("actionName:{}", actionName);
		if (StringUtils.equals(actionName, "login")) {
			doGetLogin(request, response);
		} else {
			switch (actionName) {
			case "index":
				doGetIndex(request, response);
				break;
			case "user/list":
				doGetUserList(request, response);
				break;
			case "user/detail":
				doGetUserDetail(request, response);
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
		List<User> userList = userService.userList();
		List<UserListVo> userVoList = Lists.newArrayList();
		for (User user : userList) {
			UserListVo userListVo=new UserListVo();
			BeanUtils.copyProperties(user,userListVo);
		
			userListVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));
			userListVo.setStatusMsg(UserStatusEnum.getValueByCode(user.getStatus()));
			userVoList.add(userListVo);
		}
		request.setAttribute("userList", userVoList);
		request.getRequestDispatcher(VIEW("user_list")).forward(request, response);
	}

	protected void doGetUserDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int uid = HttpServletRequsetUtil.getInt(request, "userId");
		if (uid > -1) {
             User user=userService.getUserByUserId(uid);
             request.setAttribute("user", user);
		}
		request.getRequestDispatcher(VIEW("user_detail")).forward(request, response);

	}

}
