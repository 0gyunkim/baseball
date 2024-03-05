package gui;

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

import dto.MemberDTO;

public class Menu extends JFrame implements ActionListener {
	MemberDTO admchk = null;

	ImageIcon teamAddimg = new ImageIcon("./Button_img/teamreg.png");
	ImageIcon playerAddimg = new ImageIcon("./Button_img/playerreg.png");
	ImageIcon teamModimg = new ImageIcon("./Button_img/teammana.png");
	ImageIcon playerModimg = new ImageIcon("./Button_img/playermana.png");
	ImageIcon teamChkimg = new ImageIcon("./Button_img/playerser.png");
	ImageIcon logoutimg = new ImageIcon("./Button_img/logout.png");
	ImageIcon menuimg = new ImageIcon("./img/menutitle.png");

	JButton teamAdd = new JButton(teamAddimg);
	JButton playerAdd = new JButton(playerAddimg);
	JButton teamMod = new JButton(teamModimg);
	JButton playerMod = new JButton(playerModimg);
	JButton teamChk = new JButton(teamChkimg);
	JButton logout = new JButton(logoutimg);
	GridBagLayout gb = new GridBagLayout();

	public void MainMenu(MemberDTO m) {
		teamAdd.setPreferredSize(new Dimension(300, 300));
		// teamAdd.setBorderPainted(false);
		teamAdd.setBackground(Color.black);
		playerAdd.setPreferredSize(new Dimension(300, 300));
		// playerAdd.setBorderPainted(false);
		playerAdd.setBackground(Color.black);
		teamMod.setPreferredSize(new Dimension(300, 300));
		// teamMod.setBorderPainted(false);
		teamMod.setBackground(Color.black);
		playerMod.setPreferredSize(new Dimension(300, 300));
		// playerMod.setBorderPainted(false);
		playerMod.setBackground(Color.black);
		teamChk.setPreferredSize(new Dimension(300, 300));
		// teamChk.setBorderPainted(false);
		teamChk.setBackground(Color.black);
		logout.setPreferredSize(new Dimension(300, 300));
		// logout.setBorderPainted(false);
		logout.setBackground(Color.black);

		JLabel title = new JLabel(menuimg);

		admchk = m;
		this.setBounds(100, 100, 700, 150);
		this.setTitle(m.getAdm() + "모드");
		this.getContentPane().setBackground(Color.white);
		setLayout(gb);
		// 1행
		gbinsert(title, 0, 0, 5, 1);
		// 2행
		gbinsert(teamAdd, 0, 1, 1, 1);
		gbinsert(teamMod, 2, 1, 1, 1);
		gbinsert(teamChk, 4, 1, 1, 1);
		// 2행
		gbinsert(playerAdd, 0, 2, 1, 1);
		gbinsert(playerMod, 2, 2, 1, 1);
		gbinsert(logout, 4, 2, 1, 1);

		teamAdd.addActionListener(this);
		teamMod.addActionListener(this);
		teamChk.addActionListener(this);
		playerAdd.addActionListener(this);
		playerMod.addActionListener(this);
		logout.addActionListener(this);
		this.pack();
		this.setVisible(true);

	}

	public void gbinsert(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		this.add(c);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player p = new Player();
		Team t = new Team();
		if (e.getSource() == logout) {
			this.setVisible(false);
			new Login();
		}
		if (e.getSource() == teamAdd) {
			if (admchk.getAdm().equals("일반")) {
				JOptionPane.showMessageDialog(null, "관리자 전용입니다.");
			} else if (admchk.getAdm().equals("관리자")) {
				t.TeamADD();
				// this.setVisible(false);
			}
		}
		if (e.getSource() == teamMod) {
			if (admchk.getAdm().equals("일반")) {
				JOptionPane.showMessageDialog(null, "관리자 전용입니다.");
			} else if (admchk.getAdm().equals("관리자")) {
				t.TeamMod();
				// this.setVisible(false);
			}
		}
		if (e.getSource() == playerAdd) {
			if (admchk.getAdm().equals("일반")) {
				JOptionPane.showMessageDialog(null, "관리자 전용입니다.");
			} else if (admchk.getAdm().equals("관리자")) {
				p.playerAdd();
				// this.setVisible(false);
			}
		}
		if (e.getSource() == playerMod) {
			if (admchk.getAdm().equals("일반")) {
				JOptionPane.showMessageDialog(null, "관리자 전용입니다.");
			} else if (admchk.getAdm().equals("관리자")) {
				p.playerMod();
				// this.setVisible(false);
			}
		}
		if (e.getSource() == teamChk) {
			Chk c = new Chk();
			c.playerChk();

		}
	}

}
