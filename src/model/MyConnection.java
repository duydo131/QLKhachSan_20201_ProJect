package model;

import common.AppConfig;

import java.sql.*;

public class MyConnection {
	public static Connection connection = null;
	
	public MyConnection(){
		try {
			connectDB();
		} catch (ClassCastException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void driverTest() throws ClassCastException {
		try {
			Class.forName(AppConfig.DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ClassCastException("JDBC Driver not found" + e.getMessage());
		}
	}

	public Connection connectDB() throws ClassCastException, SQLException {
		//driverTest();
		// thực hiện kết nối và lấy ra đối tượng connection
		try {
			// sử dụng drivermanager,getconnection truyền vào 3 tham số để connecy
			connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
			//System.out.println("Connect DB successfully!");
		} catch (SQLException e) {
			throw new SQLException("Connect DB fail");
		}
		return connection;

	}

	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
			//System.out.println("Connection is close");
		}
	}

	public PreparedStatement prepare(String sql) {
		//System.out.println(">>" + sql);
		// dùng connection để lấy ra đối tượng PreparedSatement
		//
		try {
			// cần truyền thêm tham số thứ 2 vào hàm prepareStatement
			// ResultSet.TYPE_SCROLL_SENSITIVE cho phép con trỏ resultset
			// chạy từ bản ghi đầu tiên đến bản ghi cuối cùng
			return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PreparedStatement prepareUpdate(String sql) {
		//System.out.println(">> " + sql);
		try {
			// cần truyền thêm tham số thứ 2 là Statement.RETURN_GENERATED_KEYS
			// Statement.RETURN_GENERATED_KEYS có tác dụng (gỉa sử khii thêm 1 bản ghi
			// category thì
			// chỉ truyền tên và trường deleted và không được truyền id và id là tự động
			// tăng
			// thì tham số thứ 2 giúp chúng ta lấy đc id sau khi thêm bản ghi thành công
			return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
