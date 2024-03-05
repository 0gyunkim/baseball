# Java Swing을 이용한 야구 매니저 프로그램
### 사용 방법 
* 회원 가입시 일반회원, 관리자 선택하여 회원가입 진행
* 관리자는 팀 등록,수정이 가능하고 선수 등록 및 수정이 가능함
* 일반 회원은 선수검색만 가능
-----
### 시연 영상
https://github.com/0gyunkim/baseball/assets/153244950/b8355afd-d80b-44b9-906c-ba212e52f632

-----
### 흐름도
![흐름도](https://github.com/0gyunkim/baseball/assets/153244950/70e9851e-f088-460a-93ea-949bd96f76dd)
-----
### DB설계(ERD)
![erd](https://github.com/0gyunkim/baseball/assets/153244950/68f32155-95ac-4ff9-b962-5e1ac957f744)
-----
### 기술 스택
* 상속 및 인터페이스 구현

JFrame을 상속 받고 ActionListener 인터페이스를 구현하여 화면을 배치하고 기능을 구현함
```
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu extends JFrame implements ActionListener
```
-----
* 배열을 이용하여 선수 정보 추출

팀과 포지션을 선택하면 해당 선수들만 나오게 구현하였으나, 중간에 포지션이나 팀을 바꿔도 선수 이름이 추출됨 하지만 선수 이름에 의미없는 빈 칸이 여러개 생겨서 비효율적임

그래서 팀 또는 포지션을 선택하게 되면 선수 이름이 나오는 곳을 삭제하고 배열을 통해 재배치 하도록 메서드를 만들고 구현함
```
		// 팀, 포지션 콤보박스 선택시
		if (e.getSource() == pTeamC || e.getSource() == pPosC) {
			nameComboUpdate();

		}
```
```
	public void nameComboUpdate() {
		String teamName = (String) pTeamC.getSelectedItem();
		String pos = (String) pPosC.getSelectedItem();
		pNameC.removeAllItems();
		String[] plist = pp.playerSel(teamName, pos);
		for (String p : plist) {
			pNameC.addItem(p);

		}

	}
```
DAO단으로 팀과 포지션을 매개변수로 넘겨 DB를 조회 후 나온 정보를 ArrayList에 저장 후 chk가 오른만큼 배열을 새로 만들어 배열에 선수의 이름을 저장하여 다시 리턴해줌
```
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
```
-----
* 싱글톤으로 객체 사용

객체를 다중으로 만들게 되면 DB를 중복으로 연결을 하거나 데이터가 변경되는 시점을 알 수 없다.

그래서 DAO단 모두 getinstance 메서드에 객체를 한번만 만들고 다른 곳에서는 메서드를 호출하여 사용할 수 있도록 구현
```
	public static MemberDAO mm = null;

	public static MemberDAO getinstance() {
		if (mm == null) {
			mm = new MemberDAO();

		}
		return mm;
	}
```




 
