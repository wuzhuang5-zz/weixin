package com.weixin.robot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

/**
 * mysql 数据库操作类
 * @author wz
 *
 */
public class MySQLUtil {
	/**
	 * 获取mysql数据库连接
	 * @return
	 */
	private Connection getConn() {
		String url = "jdbc:mysql://localhost:3306/wz";
		String username = "root";
		String password = "123456";
		Connection conn = null;
		try {
			//加载Mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取数据库连接
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 释放jdbc资源
	 */
	private void releaseResources(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(null != rs) 
				rs.close();
			if(null != ps)
				ps.close();
			if(null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
