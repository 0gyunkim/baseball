package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.PlayerDAO;
import dao.TeamDAO;
import dto.PlayerDTO;

public class Mod extends JFrame implements ActionListener {
	PlayerDAO pp = PlayerDAO.getinstance();
	TeamDAO tt = TeamDAO.getinstance();

	String[] pos = { "SP", "RP", "CP", "IF", "OF", "DH" };
	JComboBox<String> newPPosC = new JComboBox<>(pos);
	JComboBox<String> teamlist = null;

	GridBagLayout gb = new GridBagLayout();

	JButton namemod = new JButton("수정");
	JButton cen = new JButton("취소");
	JButton addrmod = new JButton("수정");
	JButton pNoModB = new JButton("수정");
	JButton pNameModB = new JButton("수정");
	JButton pPosModB = new JButton("수정");
	JButton pTeamModB = new JButton("수정");

	JTextField nameModF = new JTextField();
	JTextField newPNoF = new JTextField();
	JTextField newPNameF = new JTextField();

	String teamname = null;
	String pName = null;
	Player p;

	public void teamNameMod(Object name) {
		this.setBounds(200, 200, 200, 300);
		teamname = (String) name;
		JLabel nameModTitle = new JLabel(" " + name + " 팀 이름 수정을 진행합니다.");
		JLabel nameMod = new JLabel(" 팀 이름 : ");
		setLayout(gb);
		gbinsert(nameModTitle, 0, 0, 10, 1);
		gbinsert(nameMod, 0, 1, 3, 1);
		gbinsert(nameModF, 3, 1, 7, 1);
		gbinsert(cen, 6, 2, 2, 1);
		gbinsert(namemod, 8, 2, 1, 1);
		cen.addActionListener(this);
		namemod.addActionListener(this);
		this.pack();
		this.setVisible(true);

	}

	public void teamAddrMod(Object name) {
		this.setBounds(200, 200, 200, 300);
		teamname = (String) name;
		JLabel addrModTitle = new JLabel(" " + name + " 팀 연고지 수정을 진행합니다.");
		JLabel addrMod = new JLabel(" 연고지 : ");
		setLayout(gb);
		gbinsert(addrModTitle, 0, 0, 10, 1);
		gbinsert(addrMod, 0, 1, 3, 1);
		gbinsert(nameModF, 3, 1, 7, 1);
		gbinsert(cen, 6, 2, 2, 1);
		gbinsert(addrmod, 8, 2, 1, 1);
		cen.addActionListener(this);
		addrmod.addActionListener(this);
		this.pack();
		this.setVisible(true);

	}

	public void pNoMod(String name, int pno) {
		pName = name;
		newPNoF.setColumns(10);
		JLabel title = new JLabel(name + " 선수의 등번호를 변경합니다.");
		JLabel no = new JLabel(name + " 선수의 기존 등번호는 " + pno + " 입니다.");
		JLabel newNo = new JLabel("변경하실 등번호를 입력하세요 : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1행
		gbinsert(title, 0, 0, 7, 1);
		// 2행
		gbinsert(no, 0, 1, 2, 1);
		// 3행
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(newPNoF, 2, 2, 3, 1);
		// 4행
		gbinsert(cen, 2, 3, 1, 1);
		gbinsert(pNoModB, 3, 3, 1, 1);

		cen.addActionListener(this);
		pNoModB.addActionListener(this);

		this.pack();
		this.setVisible(true);
	}

	public void pNameMod(String name) {
		pName = name;
		newPNameF.setColumns(10);
		JLabel title = new JLabel(name + " 선수의 이름을 변경합니다.");
		JLabel no = new JLabel(name + " 선수의 기존 이름은 " + name + " 입니다.");
		JLabel newNo = new JLabel("변경하실 이름을 입력하세요 : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1행
		gbinsert(title, 0, 0, 7, 1);
		// 2행
		gbinsert(no, 0, 1, 2, 1);
		// 3행
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(newPNameF, 2, 2, 3, 1);
		// 4행
		gbinsert(cen, 2, 3, 1, 1);
		gbinsert(pNameModB, 3, 3, 1, 1);

		cen.addActionListener(this);
		pNameModB.addActionListener(this);

		this.pack();
		this.setVisible(true);
	}

	public void pPosMod(String name, String pos) {
		pName = name;
		newPNameF.setColumns(10);
		JLabel title = new JLabel(name + " 선수의 포지션을 변경합니다.");
		JLabel no = new JLabel(name + " 선수의 기존 포지션은 " + pos + " 입니다.");
		JLabel newNo = new JLabel("변경하실 포지션을 선택해주세요 : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1행
		gbinsert(title, 0, 0, 7, 1);
		// 2행
		gbinsert(no, 0, 1, 2, 1);
		// 3행
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(newPPosC, 2, 2, 3, 1);
		// 4행
		gbinsert(cen, 2, 3, 1, 1);
		gbinsert(pPosModB, 3, 3, 1, 1);

		cen.addActionListener(this);
		pPosModB.addActionListener(this);

		this.pack();
		this.setVisible(true);
	}

	public void pTeamMod(String name, String team) {
		pName = name;
		newPNameF.setColumns(10);
		Team t = new Team();
		String[] tlist = t.jclist();
		teamlist = new JComboBox<String>(tlist);
		JLabel title = new JLabel(name + " 선수의 팀을 변경합니다.");
		JLabel no = new JLabel(name + " 선수의 기존 팀은 " + team + " 입니다.");
		JLabel newNo = new JLabel("변경하실 팀을 선택해주세요 : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1행
		gbinsert(title, 0, 0, 7, 1);
		// 2행
		gbinsert(no, 0, 1, 2, 1);
		// 3행
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(teamlist, 2, 2, 3, 1);
		// 4행
		gbinsert(cen, 2, 3, 1, 1);
		gbinsert(pTeamModB, 3, 3, 1, 1);

		cen.addActionListener(this);
		pTeamModB.addActionListener(this);

		this.pack();
		this.setVisible(true);
	}

	public void gbinsert(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
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
		if (e.getSource() == cen) {
			this.setVisible(false);
		}
		if (e.getSource() == namemod) {
			String newName = nameModF.getText();
			if (newName.equals("") == false) {
				tt.teamNameMod(newName, teamname);
				JOptionPane.showMessageDialog(null, "팀 이름 변경이 완료되었습니다.");
				this.setVisible(false);
				Team t = new Team();
				t.TeamMod();

			}

		}
		if (e.getSource() == addrmod) {
			String newName = nameModF.getText();
			if (newName.equals("") == false) {
				tt.teamAddrMod(newName, teamname);
				JOptionPane.showMessageDialog(null, "연고지 변경이 완료되었습니다.");
				this.setVisible(false);
				Team t = new Team();
				t.TeamMod();

			}
		}
		if (e.getSource() == pNoModB) {
			String no = newPNoF.getText();
			if (no.equals("") == false) {
				int newno = Integer.parseInt(no);
				pp.pnoUpdate(pName, newno);
				JOptionPane.showMessageDialog(null, "변경이 완료되었습니다.");
				this.setVisible(false);
			}

		}
		if (e.getSource() == pNameModB) {
			String name = newPNameF.getText();
			if (name.equals("") == false) {
				pp.pNameUpdate(pName, name);
				JOptionPane.showMessageDialog(null, "변경이 완료되었습니다.");
				this.setVisible(false);

			}
		}
		if (e.getSource() == pPosModB) {
			String pos = (String) newPPosC.getSelectedItem();
			if (pos.equals("") == false) {
				pp.pPosUpdate(pName, pos);
				JOptionPane.showMessageDialog(null, "변경이 완료되었습니다.");
				this.setVisible(false);
			}
		}
		if (e.getSource() == pTeamModB) {
			String team = (String) teamlist.getSelectedItem();
			if (team.equals("") == false) {
				pp.pTeamUpdate(pName, team);
				JOptionPane.showMessageDialog(null, "변경이 완료되었습니다.");
				this.setVisible(false);
			}
		}

	}

}
