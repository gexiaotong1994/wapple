package com.wapple.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.wapple.common.Json;
import com.wapple.enums.UserStatusEnum;
import com.wapple.pojo.User;
import com.wapple.service.UserService;
import com.wapple.util.DateTimeUtil;
import com.wapple.util.HttpServletRequsetUtil;
import com.wapple.util.JsonUtil;
import com.wapple.util.SpringUtil;
import com.wapple.vo.UserListVo;
import com.wapple.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminController extends HttpServlet {

	private static final UserService userService = SpringUtil.getBean(UserService.class);
	private static final String GET = "GET";
	private static final String POST = "POST";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String actionName = uri.substring(9, uri.length());
		log.info("method:{} uri:{} action:{}", method, uri, actionName);
		if (actionName.equals("login")) {
			if (GET.equals(method)) {
				getLogin(request, response);
			} else {
				postLogin(request, response);
			}
		} else {
			// 先要判断是否登录
			switch (actionName) {
			case "index":
				getIndex(request, response);
				break;
			case "userlist":
				getUserList(request, response);
				break;
			case "changeUserStatus.ajax":
				ajaxChangeUserDetail(request, response);
				break;
			case "usernew":
				getUserNew(request, response);
				break;
			case "userdetail":
				getUserDetail(request, response);
				break;
			default:
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<h4>未找到当前的链接</h4>");
				out.println("</html>");
				out.flush();
				out.close();
				break;
			}

		}

	}

	private static String VIEW(String viewName) {

		return new StringBuffer().append("/admin/").append(viewName).append(".jsp").toString();
	}

	/**
	 * 登录视图
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("login")).forward(request, response);
	}

	/**
	 * 处理登录请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void postLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("login")).forward(request, response);
	}

	protected void getIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("index")).forward(request, response);
	}

	protected void getUserNew(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("user_new")).forward(request, response);
	}

	protected void getUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = userService.userList();
		List<UserListVo> userVoList = Lists.newArrayList();
		for (User user : userList) {
			UserListVo userListVo = new UserListVo();
			BeanUtils.copyProperties(user, userListVo);
			userListVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));
			userListVo.setStatusMsg(UserStatusEnum.getValueByCode(user.getStatus()));
			userVoList.add(userListVo);
		}
		request.setAttribute("userList", userVoList);
		request.getRequestDispatcher(VIEW("user_list")).forward(request, response);
	}

	protected void getUserDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int uid = HttpServletRequsetUtil.getInt(request, "userId");
		if (uid > -1) {
			User user = userService.getUserByUserId(uid);
			request.setAttribute("user", user);
		}
		request.getRequestDispatcher(VIEW("user_detail")).forward(request, response);

	}

	
	/**
	 * 修改用户状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void ajaxChangeUserDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		String username = HttpServletRequsetUtil.getString(request, "username");
		int status = HttpServletRequsetUtil.getInt(request, "status");
		Json<String> json = null;
		boolean flag = userService.modifyUserStatus(username, status);
		if (flag) {
			json = Json.success("用户[" + username + "]修改状态成功");
		} else {
			json = Json.fail("用户[" + username + "]修改状态失败");
		}
		String jsonStr = JsonUtil.objToString(json);
		response.getWriter().println(jsonStr);

	}
	
	
	
	
	
	
	
	
	

}
