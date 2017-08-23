package com.itheima.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.itheima.utils.CookieUtils;

/**
 * 点击商品列表的商品时请求处理
 *
 * @author 张诚 17691348838
 * @version 1.0, 2017-8-21 21:28:47
 */
@WebServlet("/infoServlet")
public class infoServlet extends HttpServlet {

	/** 序列化 */
	private static final long serialVersionUID = 1L;

	/**
	 * get提交方式的方法
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接受id
		String id = request.getParameter("id");

		// 得到cookie
		Cookie cookie = CookieUtils.callOnCookie(request.getCookies(), "history");

		// 如果cookie为空,就是第一次浏览
		if (cookie == null) {

			// new一个cookie
			Cookie cookies = new Cookie("history", id);

			// 设定作用路径
			cookies.setPath("/day14-demo01");

			// 设置保存时间
			cookies.setMaxAge(60 * 60);

			// 写回入浏览器
			response.addCookie(cookies);
		}

		// 如果cookie不为空,不是第一次浏览
		if (cookie != null) {
			// 得到cookie的值
			String value = cookie.getValue();

			// 用-切割得到一个String数组
			String[] values = value.split("-");

			// 将数组转化为集合
			LinkedList<String> list = new LinkedList<String>(Arrays.asList(values));

			// 如果集合里包含id则浏览过此商品
			if (list.contains(id)) {
				list.remove(id);
				list.addFirst(id);
			}

			// 否则没有浏览过此商品
			if (!list.contains(id)) {
				// 如果有三条以上浏览记录则删除最后的添加到最新
				if (list.size() >= 3) {
					list.removeLast();
					list.addFirst(id);
				}
				// 不到三条记录直接添加到第一个
				if (list.size() < 3) {
					list.addFirst(id);
				}
			}
			// 将list集合转化为一个用-连接的字符串
			String ids = StringUtils.join(list, "-");
			// 给cookie赋值
			Cookie c = new Cookie("history", ids);
			// 设置作用路径
			c.setPath("/day14-demo01");
			// 设置作用时间
			c.setMaxAge(60 * 60);
			// 写回浏览器
			response.addCookie(c);
		}
		// 把id转发给商品详情页面
		request.setAttribute("id", id);
		request.getRequestDispatcher("/commodity/info.jsp").forward(request, response);
	}

}