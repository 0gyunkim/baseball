package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import dao.PlayerDAO;

public class Chk extends Gbinsert implements ActionListener, MouseListener {
	PlayerImg pi = new PlayerImg();
	PlayerDAO pp = PlayerDAO.getinstance();
	JComboBox<String> teamC = null;
	JComboBox<String> nameC = new JComboBox<>();

	JButton ok = new JButton("정보 보기");
	JButton cen = new JButton("취소");

	public void playerChk() {
		Team t = new Team();
		teamC = new JComboBox<>(t.jclist());
		nameC.setPreferredSize(new Dimension(200, 30));
		teamC.setPreferredSize(new Dimension(200, 30));

		JLabel title = new JLabel("선수정보를 확인합니다.");
		JLabel team = new JLabel("소속팀을 선택해주세요 ");
		JLabel name = new JLabel("선수 이름을 선택해주세요. ");

		this.setBounds(200, 200, 0, 0);
		setLayout(gb);
		// 1행
		gbinsert(title, 0, 0, 1, 1);
		// 2행
		gbinsert(team, 0, 1, 1, 1);
		gbinsert(teamC, 1, 1, 5, 1);
		// 3행
		gbinsert(name, 0, 2, 1, 1);
		gbinsert(nameC, 1, 2, 5, 1);
		// 4행
		gbinsert(cen, 4, 3, 1, 1);
		gbinsert(ok, 5, 3, 1, 1);

		teamC.addActionListener(this);
		ok.addActionListener(this);
		cen.addActionListener(this);
		ok.addMouseListener(this);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == teamC) {
			nameUpdate();
		}
		if (e.getSource() == cen) {
			this.setVisible(false);
		}
		if (e.getSource() == ok) {
			pi = new PlayerImg();
			String pname = (String) nameC.getSelectedItem();
			pi.imgpop(pname);

		}
	}

	public void nameUpdate() {
		String team = (String) teamC.getSelectedItem();
		nameC.removeAllItems(); // 기존 name 콤보 삭제
		String[] name = pp.playernameSel(team);
		for (String n : name) {
			nameC.addItem(n);

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == ok) {
			pi.setVisible(false);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
