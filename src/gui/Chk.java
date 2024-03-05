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

	JButton ok = new JButton("���� ����");
	JButton cen = new JButton("���");

	public void playerChk() {
		Team t = new Team();
		teamC = new JComboBox<>(t.jclist());
		nameC.setPreferredSize(new Dimension(200, 30));
		teamC.setPreferredSize(new Dimension(200, 30));

		JLabel title = new JLabel("���������� Ȯ���մϴ�.");
		JLabel team = new JLabel("�Ҽ����� �������ּ��� ");
		JLabel name = new JLabel("���� �̸��� �������ּ���. ");

		this.setBounds(200, 200, 0, 0);
		setLayout(gb);
		// 1��
		gbinsert(title, 0, 0, 1, 1);
		// 2��
		gbinsert(team, 0, 1, 1, 1);
		gbinsert(teamC, 1, 1, 5, 1);
		// 3��
		gbinsert(name, 0, 2, 1, 1);
		gbinsert(nameC, 1, 2, 5, 1);
		// 4��
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
		nameC.removeAllItems(); // ���� name �޺� ����
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
