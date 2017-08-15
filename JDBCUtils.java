package com.itheima;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类
 * 
 * @author name
 * @version 1.0 2017年8月14日21:12:04
 */
public class JDBCUtils {
	private static final String DRIVER;
	private static final String SQLURL;
	private static final String USERNAME;
	private static final String PASSWORD;

	// 静态代码块 读取配置文件给四个常量赋值
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("text.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DRIVER = prop.getProperty("DRIVER");
		SQLURL = prop.getProperty("SQLURL");
		USERNAME = prop.getProperty("USERNAME");
		PASSWORD = prop.getProperty("PASSWORD");
	}

	/**
	 * 构造私有化
	 */
	private JDBCUtils() {
	}

	/**
	 * 注册驱动
	 */
	public static void getDrive() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建数据库链接
	 */
	public static Connection getSql() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(SQLURL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 创建执行sql对象,用于执行语句有参
	 */
	public static PreparedStatement getConnection(Connection conn, String sql, String[] values) {
		PreparedStatement preparedstatement = null;
		try {
			preparedstatement = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < values.length; i++) {
			try {
				preparedstatement.setString(i + 1, values[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return preparedstatement;
	}

	/**
	 * 创建执行sql对象重载,用于执行语句无参
	 */
	public static PreparedStatement getConnection(Connection conn, String sql) {
		PreparedStatement preparedstatement = null;
		try {
			preparedstatement = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return preparedstatement;
	}

	/**
	 * 执行sql语句，适用于查询
	 */
	public static ResultSet runsql(PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 执行sql语句重载,适用于增删改
	 */
	public static int runsql(PreparedStatement preparedStatement) {
		int num = 0;
		try {
			num = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 遍历结果集
	 */
	public static void getAll(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String classname = resultSet.getString("class");
				System.out.printf("姓名:%s 年龄:%s 班级:%s", name, age, classname);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源,适用于增删改
	 */
	public static void closeAll(Connection conn, PreparedStatement preparedStatement) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放资源重载,适用于查询
	 */
	public static void closeAll(Connection conn, PreparedStatement preparedStatement, ResultSet resultSet) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
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
