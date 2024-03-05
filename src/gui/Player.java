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
import dto.PlayerDTO;

public class Player extends JFrame implements ActionListener {
	Mod mo = null;
	Team te = null;
	PlayerDTO p = new PlayerDTO();
	PlayerDAO pp = PlayerDAO.getinstance();

	GridBagLayout gb = new GridBagLayout();
	JTextField pNameF = new JTextField();
	JTextField pNoF = new JTextField();

	JButton end = new JButton("���");
	JButton cen = new JButton("���");
	JButton pnoMod = new JButton("��ȣ ����");
	JButton nameMod = new JButton("�̸� ����");
	JButton posMod = new JButton("������ ����");
	JButton teamMod = new JButton("�� ����");

	JComboBox<String> pPosC = null;
	JComboBox<String> pTeamC = null;
	JComboBox<String> pNameC = new JComboBox<>();

	// Team Ŭ���� ��ü�� �����ؼ� Team Ŭ������ �ִ� jclist �޼��带 �̿��Ͽ� String[] Team ������ ��������
	Team t = new Team();
	String[] Team = t.jclist();
	String[] pos = { "SP", "RP", "CP", "IF", "OF", "DH" };
	// String name = null;

	public void playerAdd() {
		this.setBounds(200, 200, 200, 200);
		pTeamC = new JComboBox<>(Team);
		pPosC = new JComboBox<>(pos);
		pNameF.setColumns(20);
		JLabel pTitle = new JLabel(" ���� ����� �����մϴ�.");
		JLabel pName = new JLabel(" �̸� : ");
		JLabel pNo = new JLabel(" ��ȣ : ");
		JLabel pPos = new JLabel(" ������ :");
		JLabel pTeam = new JLabel(" �� : ");
		setLayout(gb);
		// 1��
		gbinsert(pTitle, 4, 0, 7, 1);
		// 2��
		gbinsert(pName, 0, 1, 3, 1);
		gbinsert(pNameF, 3, 1, 6, 1);
		// 3��
		gbinsert(pNo, 0, 2, 3, 1);
		gbinsert(pNoF, 3, 2, 6, 1);
		// 4��
		gbinsert(pPos, 0, 3, 3, 1);
		gbinsert(pPosC, 3, 3, 6, 1);
		// 5��
		gbinsert(pTeam, 0, 4, 3, 1);
		gbinsert(pTeamC, 3, 4, 6, 1);
		// 6��
		gbinsert(cen, 7, 5, 1, 1);
		gbinsert(end, 8, 5, 1, 1);
		//
		end.addActionListener(this);
		cen.addActionListener(this);

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

	public void playerMod() {
		JLabel modTitle = new JLabel("���� ���� ������ �����մϴ�.");
		JLabel teamSel = new JLabel("���� �������ּ���");
		JLabel posSel = new JLabel("�������� �������ּ���");
		JLabel playerSel = new JLabel("������ �������ּ���");

		pTeamC = new JComboBox<>(Team);
		pPosC = new JComboBox<>(pos);
		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1��
		gbinsert(modTitle, 0, 0, 6, 1);
		// 2��
		gbinsert(teamSel, 0, 1, 2, 1);
		gbinsert(pTeamC, 2, 1, 5, 1);
		// 3��
		gbinsert(posSel, 0, 2, 2, 1);
		gbinsert(pPosC, 2, 2, 5, 1);
		// 4��
		gbinsert(playerSel, 0, 3, 2, 1);
		gbinsert(pNameC, 2, 3, 5, 1);
		// 5��
		gbinsert(cen, 0, 4, 1, 1);
		gbinsert(pnoMod, 2, 4, 1, 1);
		gbinsert(nameMod, 3, 4, 1, 1);
		gbinsert(posMod, 4, 4, 1, 1);
		gbinsert(teamMod, 5, 4, 1, 1);

		cen.addActionListener(this);
		pTeamC.addActionListener(this);
		pPosC.addActionListener(this);
		cen.addActionListener(this);
		pnoMod.addActionListener(this);
		nameMod.addActionListener(this);
		posMod.addActionListener(this);
		teamMod.addActionListener(this);

		this.pack();
		this.setVisible(true);
	}

	public void nameComboUpdate() {
		String teamName = (String) pTeamC.getSelectedItem();
		String pos = (String) pPosC.getSelectedItem();
		pNameC.removeAllItems();
		String[] plist = pp.playerSel(teamName, pos);
		for (String p : plist) {
			pNameC.addItem(p);

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// �������_��ҹ�ư
		if (e.getSource() == cen) {
			this.setVisible(false);
		}
		// �������_��Ϲ�ư
		if (e.getSource() == end) {
			Object teamname = pTeamC.getSelectedItem();
			Object pos = pPosC.getSelectedItem();
			String teamnamest = (String) teamname;
			String posst = (String) pos;
			String noSt = pNoF.getText();

			if (pNameF.getText().equals("") || pNoF.getText().equals("") || teamname == null) {
				JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ Ȯ�����ּ���");
			} else if (pNameF.getText().equals("") == false && pNoF.getText().equals("") == false && teamname != null) {
				p.setName(pNameF.getText());
				try {
					int pno = Integer.parseInt(noSt);
					p.setPno(pno);
				} catch (Exception e2) {

				}
				p.setPos(posst);
				p.setTeam(teamnamest);
				pp.insert(p);
				JOptionPane.showMessageDialog(null, "��������� �Ϸ�Ǿ����ϴ�.");
				pNameF.setText("");
				pNoF.setText("");

			}
		}
		// ��, ������ �޺� ���ý�
		if (e.getSource() == pTeamC || e.getSource() == pPosC) {
			nameComboUpdate();

		}
		// ���� ��ȣ ���� ��ư
		if (e.getSource() == pnoMod) {
			String pName = (String) pNameC.getSelectedItem();
			String pTeam = (String) pTeamC.getSelectedItem();
			PlayerDTO p = pp.oneChk(pName, pTeam);
			if (p != null) {
				mo = new Mod();
				mo.pNoMod(p.getName(), p.getPno());
				
			} else {
				System.out.println("�߸���");
			}
		}
		if (e.getSource() == nameMod) {
			String pName = (String) pNameC.getSelectedItem();
			String pTeam = (String) pTeamC.getSelectedItem();
			PlayerDTO p = pp.oneChk(pName, pTeam);
			if (p != null) {
				mo = new Mod();
				mo.pNameMod(p.getName());
				
			} else {
				System.out.println("�߸���");
			}
		}
		if (e.getSource() == posMod) {
			String pName = (String) pNameC.getSelectedItem();
			String pTeam = (String) pTeamC.getSelectedItem();
			PlayerDTO p = pp.oneChk(pName, pTeam);
			if (p != null) {
				mo = new Mod();
				mo.pPosMod(p.getName(), p.getPos());
				
			} else {
				System.out.println("�߸���");
			}
		}
		if (e.getSource() == teamMod) {
			String pName = (String) pNameC.getSelectedItem();
			String pTeam = (String) pTeamC.getSelectedItem();
			PlayerDTO p = pp.oneChk(pName, pTeam);
			if (p != null) {
				mo = new Mod();
				mo.pTeamMod(p.getName(), p.getTeam());
				
			} else {
				System.out.println("�߸���");
			}
		}

	}

}
