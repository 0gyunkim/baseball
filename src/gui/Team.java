package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.TeamDAO;
import dto.TeamDTO;

public class Team extends JFrame implements ActionListener {
	TeamDAO tt = null;
	JLabel team = new JLabel("  팀 이름 : ");
	JLabel addr = new JLabel("  연고지 : ");

	JButton end = new JButton(" 등록");
	JButton reset = new JButton(" 초기화");
	JButton teammod = new JButton(" 팀 명 변경");
	JButton addrmod = new JButton(" 연고지 변경");

	JTextField teamF = new JTextField();
	JTextField addrF = new JTextField();

	JPanel teamadd = new JPanel();
	GridBagLayout gb = new GridBagLayout();

	JComboBox<String> jc = null;

	public Team() {

	}

	public void TeamADD() {
		teamF.setColumns(20);
		this.setBounds(200, 200, 300, 200);
		this.setTitle("팀 등록");
		setLayout(gb);
		gbinsert(team, 0, 0, 1, 1);
		gbinsert(teamF, 1, 0, 3, 1);
		gbinsert(addr, 0, 1, 1, 1);
		gbinsert(addrF, 1, 1, 3, 1);
		gbinsert(reset, 2, 3, 1, 1);
		gbinsert(end, 3, 3, 1, 1);
		// this.pack();
		reset.addActionListener(this);
		end.addActionListener(this);
		this.setVisible(true);
	}

	public void TeamMod() {
		this.setBounds(200, 200, 300, 200);
		String[] jclist = jclist();
		jc = new JComboBox<>(jclist);
		JLabel team = new JLabel("팀을 선택해주세요");
		jc.setPreferredSize(new Dimension(200, 30));

		setLayout(gb);
		gbinsert(team, 0, 0, 1, 1);
		gbinsert(jc, 1, 0, 4, 1);
		gbinsert(teammod, 3, 1, 1, 1);
		gbinsert(addrmod, 4, 1, 1, 1);
		teammod.addActionListener(this);
		addrmod.addActionListener(this);
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
		tt = TeamDAO.getinstance();
		// 팀 추가 리셋버튼
		if (e.getSource() == reset) {
			teamF.setText("");
			addrF.setText("");
		}
		// 팀 추가 완료버튼
		if (e.getSource() == end) {
			if (teamF.getText().equals("") || addrF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "팀 이름과 연고지를 정확히 입력해주세요");
				teamF.setText("");
				addrF.setText("");
			} else if (teamF.getText().equals("") == false || addrF.getText().equals("") == false) {
				TeamDTO t = new TeamDTO();
				t.setName(teamF.getText());
				t.setAddr(addrF.getText());
				tt.insert(t);
				JOptionPane.showMessageDialog(null, "팀 등록이 완료되었습니다.");
				this.setVisible(false);
			}

		}
		// 팀 명 수정 버튼
		if (e.getSource() == teammod) {
			Mod m = new Mod();
			Object teamname = jc.getSelectedItem();
			System.out.println(teamname);
			if (teamname == null) {
				JOptionPane.showMessageDialog(null, "팀 이름을 정확히 클릭해주세요");

			} else {
				m.teamNameMod(teamname);
				this.setVisible(false);
			}

		}
		// 연고지 변경 버튼
		if (e.getSource() == addrmod) {
			Mod m = new Mod();
			Object teamname = jc.getSelectedItem();
			System.out.println(teamname);
			if (teamname == null) {
				JOptionPane.showMessageDialog(null, "팀 이름을 정확히 클릭해주세요.");

			} else {
				m.teamAddrMod(teamname);
				this.setVisible(false);
			}
		}
	}

	public String[] jclist() {
		int temp = 0;
		tt = TeamDAO.getinstance();
		ArrayList<TeamDTO> tlist = tt.select();
		String[] tList = new String[tlist.size()];
		for (TeamDTO t : tlist) {
			tList[temp] = t.getName();
			temp = temp + 1;
		}

		return tList;
	}

}
