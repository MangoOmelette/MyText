package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 清除历史记录的Servlet
 * 
 * @author 张诚 17691348838
 * @version 1.0, 2017-8-22 13:21:10
 */
@WebServlet("/ClearServlet")
public class ClearServlet extends HttpServlet {

	/** 序列化 */
	private static final long serialVersionUID = 1L;

	/** get提交的数据的方法 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 设定history的值为null
		Cookie cookie = new Cookie("history", null);

		// 设置作用路径
		cookie.setPath("/day14-demo01");

		// 时间设为0等于删除
		cookie.setMaxAge(0);

		// 写回浏览器
		response.addCookie(cookie);

		// 重定向跳转回商品列表
		response.sendRedirect("/day14-demo01/commodity/commodity.jsp");
	}

}
