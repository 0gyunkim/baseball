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

	JButton namemod = new JButton("����");
	JButton cen = new JButton("���");
	JButton addrmod = new JButton("����");
	JButton pNoModB = new JButton("����");
	JButton pNameModB = new JButton("����");
	JButton pPosModB = new JButton("����");
	JButton pTeamModB = new JButton("����");

	JTextField nameModF = new JTextField();
	JTextField newPNoF = new JTextField();
	JTextField newPNameF = new JTextField();

	String teamname = null;
	String pName = null;
	Player p;

	public void teamNameMod(Object name) {
		this.setBounds(200, 200, 200, 300);
		teamname = (String) name;
		JLabel nameModTitle = new JLabel(" " + name + " �� �̸� ������ �����մϴ�.");
		JLabel nameMod = new JLabel(" �� �̸� : ");
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
		JLabel addrModTitle = new JLabel(" " + name + " �� ������ ������ �����մϴ�.");
		JLabel addrMod = new JLabel(" ������ : ");
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
		JLabel title = new JLabel(name + " ������ ���ȣ�� �����մϴ�.");
		JLabel no = new JLabel(name + " ������ ���� ���ȣ�� " + pno + " �Դϴ�.");
		JLabel newNo = new JLabel("�����Ͻ� ���ȣ�� �Է��ϼ��� : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1��
		gbinsert(title, 0, 0, 7, 1);
		// 2��
		gbinsert(no, 0, 1, 2, 1);
		// 3��
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(newPNoF, 2, 2, 3, 1);
		// 4��
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
		JLabel title = new JLabel(name + " ������ �̸��� �����մϴ�.");
		JLabel no = new JLabel(name + " ������ ���� �̸��� " + name + " �Դϴ�.");
		JLabel newNo = new JLabel("�����Ͻ� �̸��� �Է��ϼ��� : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1��
		gbinsert(title, 0, 0, 7, 1);
		// 2��
		gbinsert(no, 0, 1, 2, 1);
		// 3��
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(newPNameF, 2, 2, 3, 1);
		// 4��
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
		JLabel title = new JLabel(name + " ������ �������� �����մϴ�.");
		JLabel no = new JLabel(name + " ������ ���� �������� " + pos + " �Դϴ�.");
		JLabel newNo = new JLabel("�����Ͻ� �������� �������ּ��� : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1��
		gbinsert(title, 0, 0, 7, 1);
		// 2��
		gbinsert(no, 0, 1, 2, 1);
		// 3��
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(newPPosC, 2, 2, 3, 1);
		// 4��
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
		JLabel title = new JLabel(name + " ������ ���� �����մϴ�.");
		JLabel no = new JLabel(name + " ������ ���� ���� " + team + " �Դϴ�.");
		JLabel newNo = new JLabel("�����Ͻ� ���� �������ּ��� : ");

		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1��
		gbinsert(title, 0, 0, 7, 1);
		// 2��
		gbinsert(no, 0, 1, 2, 1);
		// 3��
		gbinsert(newNo, 0, 2, 2, 1);
		gbinsert(teamlist, 2, 2, 3, 1);
		// 4��
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
				JOptionPane.showMessageDialog(null, "�� �̸� ������ �Ϸ�Ǿ����ϴ�.");
				this.setVisible(false);
				Team t = new Team();
				t.TeamMod();

			}

		}
		if (e.getSource() == addrmod) {
			String newName = nameModF.getText();
			if (newName.equals("") == false) {
				tt.teamAddrMod(newName, teamname);
				JOptionPane.showMessageDialog(null, "������ ������ �Ϸ�Ǿ����ϴ�.");
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
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				this.setVisible(false);
			}

		}
		if (e.getSource() == pNameModB) {
			String name = newPNameF.getText();
			if (name.equals("") == false) {
				pp.pNameUpdate(pName, name);
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				this.setVisible(false);

			}
		}
		if (e.getSource() == pPosModB) {
			String pos = (String) newPPosC.getSelectedItem();
			if (pos.equals("") == false) {
				pp.pPosUpdate(pName, pos);
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				this.setVisible(false);
			}
		}
		if (e.getSource() == pTeamModB) {
			String team = (String) teamlist.getSelectedItem();
			if (team.equals("") == false) {
				pp.pTeamUpdate(pName, team);
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				this.setVisible(false);
			}
		}

	}

}
