package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PlayerDTO;

public class PlayerDAO extends SuperDAO {
	PlayerDTO p = null;
	int chk = 0;

	public static PlayerDAO pp = null;

	public static PlayerDAO getinstance() {
		if (pp == null) {
			pp = new PlayerDAO();
		}
		return pp;
	}

	private PlayerDAO() {
	}

	public void insert(PlayerDTO p) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "insert into player values(?,?,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, p.getPno());
				psmt.setString(2, p.getName());
				psmt.setString(3, p.getPos());
				psmt.setString(4, p.getTeam());
				psmt.executeUpdate();
			} catch (Exception e) {

			} finally {
				allclose(conn, psmt);

			}
		}

	}

	public String[] playerSel(String teamName, String pos) {
		ArrayList<PlayerDTO> plistA = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int i = 0;
		if (getconn()) {
			try {
				String sql = "select name from player where team=? and pos=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, teamName);
				psmt.setString(2, pos);
				rs = psmt.executeQuery();
				while (rs.next()) {
					p = new PlayerDTO();
					p.setName(rs.getString("name"));
					plistA.add(p);
					chk++;
				}
				String[] plist = new String[chk];
				for (PlayerDTO n : plistA) {
					plist[i] = n.getName();
					i = i + 1;
				}
				return plist;
			} catch (Exception e) {

			} finally {
				allclose(conn, psmt);
				chk = 0;
			}
		}
		return null;

	}

	public PlayerDTO oneChk(String name, String team) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		if (getconn()) {
			try {
				String sql = "select * from player where name=? and team=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, team);
				rs = psmt.executeQuery();
				if (rs.next()) {
					PlayerDTO p = new PlayerDTO();
					p.setName(rs.getString("name"));
					p.setPno(rs.getInt("pno"));
					p.setPos(rs.getString("pos"));
					p.setTeam(rs.getString("team"));
					return p;
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);

			}
		}
		return null;

	}

	public void pnoUpdate(String name, int pno) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "update player set pno=? where name=?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, pno);
				psmt.setString(2, name);
				psmt.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);
			}
		}

	}

	public void pNameUpdate(String name, String newname) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "update player set name=? where name=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newname);
				psmt.setString(2, name);
				psmt.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);
			}
		}

	}

	public void pPosUpdate(String name, String pos) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "update player set pos=? where name=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, pos);
				psmt.setString(2, name);
				psmt.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);
			}
		}

	}

	public void pTeamUpdate(String name, String team) {
		PreparedStatement psmt = null;
		if (getconn()) {
			try {
				String sql = "update player set team=? where name=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, team);
				psmt.setString(2, name);
				psmt.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				allclose(conn, psmt);
			}
		}

	}

	public String[] playernameSel(String teamName) {
		ArrayList<PlayerDTO> plistA = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int i = 0;
		if (getconn()) {
			try {
				String sql = "select name from player where team=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, teamName);
				rs = psmt.executeQuery();
				while (rs.next()) {
					p = new PlayerDTO();
					p.setName(rs.getString("name"));
					plistA.add(p);
					chk++;
				}
				String[] plist = new String[chk];
				for (PlayerDTO n : plistA) {
					plist[i] = n.getName();
					i = i + 1;
				}
				return plist;
			} catch (Exception e) {

			} finally {
				allclose(conn, psmt);
				chk = 0;
			}
		}
		return null;

	}

}
