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
			System.out.println("����̹� �ε� ����");
		} catch (Exception e) {
			System.out.println("����̹� �ε� ����");
		}
	}

	public boolean getconn() {
		try {
			conn = DriverManager.getConnection(url, username, pass);
			System.out.println("���ؼ� ����");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���ؼ� ����");
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
