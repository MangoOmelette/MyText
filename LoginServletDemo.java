package com.itheima.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.User;
import com.itheima.service.ServiceDemo;

/**
 * 完成验证码的代码变现
 * 
 * @author 张诚 17691348838
 * @version 1.0, 2017-8-22 19:06:19
 */
@WebServlet("/LoginServletDemo")
public class LoginServletDemo extends HttpServlet {

	/** 序列化 */
	private static final long serialVersionUID = 1L;

	/** post提交方式的方法 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			// 设置tomcat和网页的字符集
			request.setCharacterEncoding("UTF-8");

			// 设置向网页输出时的字符集
			response.setContentType("text/html;charset=UTF-8");

			// 获得提交的验证码
			String code1 = request.getParameter("code");
			// 获得存于session的生成的验证码
			String code2 = (String) request.getSession().getAttribute("code");
			request.getSession().removeAttribute("code");
			if (!code1.equalsIgnoreCase(code2)) {
				request.setAttribute("fail", "登录失败!验证码错误");
				request.getRequestDispatcher("/login/login.jsp").forward(request, response);
				return;
			}

			// 获取提交账号密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// 创建用户类
			User user = new User();
			// 向用户类添加数据进行封装
			user.setUsername(username);
			user.setPassword(password);

			// 创建ServiceDemo的对象,对数据进行操作不在Servlet里进行,向下传递
			ServiceDemo serviceDemo = new ServiceDemo();

			// 调用ServiceDemo的方法并返回查询数据的结果
			User userDemo = serviceDemo.login(user);

			// 如果登陆成功则转发到登陆成功的页面,失败则转发到失败页面
			if (userDemo != null) {
				// 将账号信息存到Session里以便进行登录验证
				request.getSession().setAttribute("user", userDemo);
				// 将账户名转发给主页,显示在主页
				request.setAttribute("username", username);
				request.getRequestDispatcher("/login/index.jsp").forward(request, response);
			} else {
				// 登录失败,带着错误信息转发回登录页面
				request.setAttribute("fail", "登录失败!用户名或者密码错误");
				request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
