package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SuperDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String username = "system";
	private String pass = "1111";
	public Connection conn = null;

	public SuperDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch (Exception e) {
			System.out.println("드라이버 로드 실패");
		}
	}

	public boolean getconn() {
		try {
			conn = DriverManager.getConnection(url, username, pass);
			System.out.println("컨넥션 성공");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("컨넥션 실패");
		}
		return false;
	}

	public void allclose(Connection conn, PreparedStatement psmt) {
		if (conn != null) {
			conn = null;
		}
		if (psmt != null) {
			psmt = null;

		}
	}

}
