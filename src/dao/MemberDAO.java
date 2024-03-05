package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.MemberDTO;

public class MemberDAO extends SuperDAO {
	public static MemberDAO mm = null;

	public static MemberDAO getinstance() {
		if (mm == null) {
			mm = new MemberDAO();

		}
		return mm;
	}

	private MemberDAO() {

	}

	public void insert(MemberDTO m) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "insert into bmember values (?,?,?,?,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getName());
				psmt.setString(3, m.getPass());
				psmt.setString(4, m.getPnum());
				psmt.setString(5, m.getAge());
				psmt.setString(6, m.getAdm());
				psmt.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);

			}
		}

	}

	public boolean chk(String id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		if (getconn()) {
			try {
				String sql = "select * from bmember where id=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs = psmt.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);

			}
		}
		return false;

	}

	public MemberDTO login(String id, String pass) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		if (getconn()) {
			try {
				String sql = "select * from bmember where id=? and pass=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pass);
				rs = psmt.executeQuery();
				if (rs.next()) {
					MemberDTO m = new MemberDTO();
					m.setId(rs.getString("id"));
					m.setName(rs.getString("name"));
					m.setPnum(rs.getString("pnum"));
					m.setAge(rs.getString("age"));
					m.setAdm(rs.getString("adm"));
					return m;
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);

			}
		}
		return null;
	}

}
