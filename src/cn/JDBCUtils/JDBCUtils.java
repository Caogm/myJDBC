package cn.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

	private JDBCUtils() {

	}

	private static Connection con;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myfirstdb?useSSL=true&serverTimezone=GMT";
			String username = "root";
			String password = "cgm@51316";
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e + "数据库连接失败");
		}
	}

	/*
	 * 定义静态方法，返回数据库的连接对象
	 */
	public static Connection connection() {

		return con;
	}

	public static void close(Connection con, Statement stat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

	public static void close(Connection con, Statement stat) {

		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

}
