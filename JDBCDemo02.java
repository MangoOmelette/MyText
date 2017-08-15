package com.itheima;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * 用工具类 和 prinf实现jdbc的增删改查
 * 
 * @author 张诚
 * @version 1.0 2017年8月14日21:09:40
 */
public class JDBCDemo02 {
	@Test
	/**
	 * 测试工具类增加
	 */
	public void insert() {
		// 提升作用域
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			// 注册驱动
			JDBCUtils.getDrive();
			// 创建数据库链接
			conn = JDBCUtils.getSql();
			// 编写sql执行语句
			String sql = "insert into student values(null,?,?,?)";
			String[] values = { "汪汪", "20", "五班" };
			// 创建sql执行对象
			preparedStatement = JDBCUtils.getConnection(conn, sql, values);
			// 执行sql语句
			int num = JDBCUtils.runsql(preparedStatement);
			// 返回执行结果并输出
			if (num > 0) {
				System.out.println("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.closeAll(conn, preparedStatement);
		}
	}

	@Test
	/**
	 * 测试工具类删除
	 */
	public void delete() {
		// 提升作用域
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			// 注册驱动
			JDBCUtils.getDrive();
			// 创建数据库链接
			conn = JDBCUtils.getSql();
			// 编写sql执行语句
			String sql = "delete from student where name=?";
			String[] values = { "汪汪" };
			// 创建sql执行对象
			preparedStatement = JDBCUtils.getConnection(conn, sql, values);
			// 执行sql语句
			int num = JDBCUtils.runsql(preparedStatement);
			// 返回执行结果并输出
			if (num > 0) {
				System.out.println("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.closeAll(conn, preparedStatement);
		}
	}

	@Test
	/**
	 * 测试工具类修改
	 */
	public void update() {
		// 提升作用域
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			// 注册驱动
			JDBCUtils.getDrive();
			// 创建数据库链接
			conn = JDBCUtils.getSql();
			// 编写sql执行语句
			String sql = "update student set name=? where name=?";
			String[] values = { "叽叽", "汪汪" };
			// 创建sql执行对象
			preparedStatement = JDBCUtils.getConnection(conn, sql, values);
			// 执行sql语句
			int num = JDBCUtils.runsql(preparedStatement);
			// 返回执行结果并输出
			if (num > 0) {
				System.out.println("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.closeAll(conn, preparedStatement);
		}
	}

	@Test
	/**
	 * 测试工具类查询
	 */
	public void select() {
		// 提升作用域
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 注册驱动
			JDBCUtils.getDrive();
			// 创建数据库链接
			conn = JDBCUtils.getSql();
			// 编写sql执行语句
			String sql = "select * from student";
			// 创建sql执行对象
			preparedStatement = JDBCUtils.getConnection(conn, sql);
			// 执行sql语句
			resultSet = JDBCUtils.runsql(preparedStatement, resultSet);
			// 返回执行结果并输出
			JDBCUtils.getAll(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.closeAll(conn, preparedStatement);
		}
	}
}
