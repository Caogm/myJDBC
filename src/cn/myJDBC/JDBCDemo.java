package cn.myJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC操作数据库的步骤
 * 1.注册驱动
 * 	告知JVM使用的是哪个数据库
 * 2.获得连接
 * 	使用JDBC中的类，完成对MySQL数据库的连接
 * 3.获得执行语句
 * 	通过连接对象获取对SQL语句的执行者对象
 * 4.执行SQL语句
 * 	使用执行者对象，向数据库执行SQL语句，获取到数据库的执行后的结果
 * 5.处理结果
 * 6.释放资源
 * 	调用close()
 * 
*/
public class JDBCDemo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1.注册驱动
		// DriverManager.registerDriver(new Driver());
		// 查看Driverclass源码后发现，这种方式会重复注册两次驱动,不推荐使用
		// 注册驱动最好的方式是通过 反射技术，将驱动类加入到内存
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 2.获得连接
		String url = "jdbc:mysql://localhost:3306/myfirstdb?useSSL=true&serverTimezone=GMT";
		String name = "root";
		String password = "cgm@51316";
		Connection connection = DriverManager.getConnection(url, name, password);
		// System.out.println(connection);

		// 3.获得语句执行平台，通过数据库连接对象，获取到SQL语句的执行者对象
		// connection对象调用方法 Statement createStatement()获得Statement对象，将SQL语句发送到数据库
		// 返回值statement接口的实现类对象，在MySQL驱动程序中
		Statement statement = connection.createStatement();
		// System.out.println(statement);

		// 4.执行SQL语句
		// 通过执行者对象调用方法执行SQL语句，获取结果
		// int executeUpdate(String sql) 执行给定 SQL 语句，该语句可能为 INSERT、UPDATE 或
		// DELETE语句，或者不返回任何内容的 SQL 语句（如 SQL DDL 语句）。
		/*
		 * String sql =
		 * "INSERT INTO sort(sname,sprice,sdesc) VALUE ('汽车用品',15000,'又贵了')";
		 * statement.executeUpdate(sql);
		 */
		String sql1 = "SELECT * FROM sort";
		// int row = statement.executeUpdate("DELETE FROM SORT WHERE SID='7'");
		// System.out.println(row);
		// ResultSet executeQuery(String sql) 执行给定的 SQL 语句，该语句返回单个 ResultSet 对象。
		ResultSet rs = statement.executeQuery(sql1);

		// 5.处理结果集
		// ResultSet接口方法boolean next()返回true，有结果集；返回false则没有结果集
		while (rs.next()) {
			// 获取每列的数据，使用ResultSet接口方法getXXX,最好写string列名
			System.out.println(rs.getInt("sid") + "  " + rs.getString("sname") + "  " + rs.getInt("sprice") + "  "
					+ rs.getString("sdesc"));
		}

		// 6.释放资源 关闭的顺序是先得到的后关闭，后得到的先关闭。
		rs.close();
		statement.close();
		connection.close();

	}
}
