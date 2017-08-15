package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 使用printf练习实现JDBC增删改查
 * 
 * @author name
 * @version 1.0 2017年8月14日20:07:55
 */

public class JDBCDemo01 {
	@Test
	/**
	 * 实现数据库添加操作
	 */
	public void insert() {
		// 提升作用域
		Connection conn = null;
		Statement statement = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 创建链接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day09?useSSL=false", "root", "pan1994zer");
			// 创建执行sql对象
			statement = conn.createStatement();
			// 编写执行语句
			String sql = "Insert into student values(null,'喵喵','17','三班')";
			// 执行语句
			int num = statement.executeUpdate(sql);
			// 判断执行结果
			if (num > 0) {
				System.out.println("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	/**
	 * 实现数据库删除操作
	 */
	public void delete() {

		// 提升作用域
		Connection conn = null;
		Statement statement = null;
		try {
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day09?useSSL=false", "root", "pan1994zer");
			// 创建执行sql对象
			statement = conn.createStatement();
			// 编写sql语句
			String sql = "delete from student where name='喵喵'";
			// 执行sql语句
			int num = statement.executeUpdate(sql);
			// 返回判断结果并输出
			if (num > 0) {
				System.out.println("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	/**
	 * 实现数据库修改操作
	 */
	public void update() {
		// 提升作用域
		Connection conn = null;
		Statement statement = null;
		try {
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day09?useSSL=false", "root", "pan1994zer");
			// 创建sql执行对象
			statement = conn.createStatement();
			// 编写sql执行语句
			String sql = "update student set name='汪汪' where name='喵喵'";
			// 执行语句
			int num = statement.executeUpdate(sql);
			// 返回执行结果并输出
			if (num > 0) {
				System.out.println("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	/**
	 * 实现数据库查询操作
	 */
	public void selectdemo() {
		// 提升作用域
		Connection coon = null;
		Statement staement = null;
		ResultSet resultSet = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获得链接
			coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/day09?useSSL=false", "root", "pan1994zer");
			// 建立执行sql语句对象
			staement = coon.createStatement();
			// 编写执行语句
			String sql = "select * from student";
			// 执行sql语句
			resultSet = staement.executeQuery(sql);
			// 遍历结果集
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String classname = resultSet.getString("class");
				System.out.printf("姓名:%s 年龄:%s 班级:%s", name, age, classname);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (coon != null) {
				try {
					coon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (staement != null) {
				try {
					staement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
