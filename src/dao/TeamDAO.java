package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.TeamDTO;

public class TeamDAO extends SuperDAO {

	public static TeamDAO tt = null;

	public static TeamDAO getinstance() {
		if (tt == null) {
			tt = new TeamDAO();

		}
		return tt;
	}

	private TeamDAO() {

	}

	public void insert(TeamDTO t) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "insert into team values(?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, t.getName());
				psmt.setString(2, t.getAddr());
				psmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);

			}
		}

	}

	public ArrayList<TeamDTO> select() {
		ArrayList<TeamDTO> tlist = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		if (getconn()) {
			try {
				String sql = "select * from team";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					TeamDTO t = new TeamDTO();
					t.setName(rs.getString("name"));
					t.setAddr(rs.getString("addr"));
					tlist.add(t);
				}
				return tlist;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				allclose(conn, psmt);

			}

		}
		return null;
	}

	public void teamNameMod(String newname, String teamname) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "update team set name=? where name=? ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newname);
				psmt.setString(2, teamname);
				psmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				allclose(conn, psmt);
				
			}
		}

	}
	public void teamAddrMod(String newname, String teamname) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "update team set addr=? where name=? ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newname);
				psmt.setString(2, teamname);
				psmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				allclose(conn, psmt);
				
			}
		}

	}

}
