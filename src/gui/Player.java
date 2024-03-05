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

	JButton end = new JButton("등록");
	JButton cen = new JButton("취소");
	JButton pnoMod = new JButton("번호 변경");
	JButton nameMod = new JButton("이름 변경");
	JButton posMod = new JButton("포지션 변경");
	JButton teamMod = new JButton("팀 변경");

	JComboBox<String> pPosC = null;
	JComboBox<String> pTeamC = null;
	JComboBox<String> pNameC = new JComboBox<>();

	// Team 클라스의 객체를 생성해서 Team 클래스에 있는 jclist 메서드를 이용하여 String[] Team 변수에 대입해줌
	Team t = new Team();
	String[] Team = t.jclist();
	String[] pos = { "SP", "RP", "CP", "IF", "OF", "DH" };
	// String name = null;

	public void playerAdd() {
		this.setBounds(200, 200, 200, 200);
		pTeamC = new JComboBox<>(Team);
		pPosC = new JComboBox<>(pos);
		pNameF.setColumns(20);
		JLabel pTitle = new JLabel(" 선수 등록을 진행합니다.");
		JLabel pName = new JLabel(" 이름 : ");
		JLabel pNo = new JLabel(" 번호 : ");
		JLabel pPos = new JLabel(" 포지션 :");
		JLabel pTeam = new JLabel(" 팀 : ");
		setLayout(gb);
		// 1행
		gbinsert(pTitle, 4, 0, 7, 1);
		// 2행
		gbinsert(pName, 0, 1, 3, 1);
		gbinsert(pNameF, 3, 1, 6, 1);
		// 3행
		gbinsert(pNo, 0, 2, 3, 1);
		gbinsert(pNoF, 3, 2, 6, 1);
		// 4행
		gbinsert(pPos, 0, 3, 3, 1);
		gbinsert(pPosC, 3, 3, 6, 1);
		// 5행
		gbinsert(pTeam, 0, 4, 3, 1);
		gbinsert(pTeamC, 3, 4, 6, 1);
		// 6행
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
		JLabel modTitle = new JLabel("선수 정보 수정을 진행합니다.");
		JLabel teamSel = new JLabel("팀을 선택해주세요");
		JLabel posSel = new JLabel("포지션을 선택해주세요");
		JLabel playerSel = new JLabel("선수를 선택해주세요");

		pTeamC = new JComboBox<>(Team);
		pPosC = new JComboBox<>(pos);
		this.setBounds(200, 200, 200, 200);
		setLayout(gb);
		// 1행
		gbinsert(modTitle, 0, 0, 6, 1);
		// 2행
		gbinsert(teamSel, 0, 1, 2, 1);
		gbinsert(pTeamC, 2, 1, 5, 1);
		// 3행
		gbinsert(posSel, 0, 2, 2, 1);
		gbinsert(pPosC, 2, 2, 5, 1);
		// 4행
		gbinsert(playerSel, 0, 3, 2, 1);
		gbinsert(pNameC, 2, 3, 5, 1);
		// 5행
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

		// 선수등록_취소버튼
		if (e.getSource() == cen) {
			this.setVisible(false);
		}
		// 선수등록_등록버튼
		if (e.getSource() == end) {
			Object teamname = pTeamC.getSelectedItem();
			Object pos = pPosC.getSelectedItem();
			String teamnamest = (String) teamname;
			String posst = (String) pos;
			String noSt = pNoF.getText();

			if (pNameF.getText().equals("") || pNoF.getText().equals("") || teamname == null) {
				JOptionPane.showMessageDialog(null, "입력하신 내용을 확인해주세요");
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
				JOptionPane.showMessageDialog(null, "선수등록이 완료되었습니다.");
				pNameF.setText("");
				pNoF.setText("");

			}
		}
		// 팀, 포지션 콤보 선택시
		if (e.getSource() == pTeamC || e.getSource() == pPosC) {
			nameComboUpdate();

		}
		// 선수 번호 수정 버튼
		if (e.getSource() == pnoMod) {
			String pName = (String) pNameC.getSelectedItem();
			String pTeam = (String) pTeamC.getSelectedItem();
			PlayerDTO p = pp.oneChk(pName, pTeam);
			if (p != null) {
				mo = new Mod();
				mo.pNoMod(p.getName(), p.getPno());
				
			} else {
				System.out.println("잘못됨");
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
				System.out.println("잘못됨");
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
				System.out.println("잘못됨");
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
				System.out.println("잘못됨");
			}
		}

	}

}
