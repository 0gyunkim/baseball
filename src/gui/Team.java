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
	JLabel team = new JLabel("  �� �̸� : ");
	JLabel addr = new JLabel("  ������ : ");

	JButton end = new JButton(" ���");
	JButton reset = new JButton(" �ʱ�ȭ");
	JButton teammod = new JButton(" �� �� ����");
	JButton addrmod = new JButton(" ������ ����");

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
		this.setTitle("�� ���");
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
		JLabel team = new JLabel("���� �������ּ���");
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
		// �� �߰� ���¹�ư
		if (e.getSource() == reset) {
			teamF.setText("");
			addrF.setText("");
		}
		// �� �߰� �Ϸ��ư
		if (e.getSource() == end) {
			if (teamF.getText().equals("") || addrF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�� �̸��� �������� ��Ȯ�� �Է����ּ���");
				teamF.setText("");
				addrF.setText("");
			} else if (teamF.getText().equals("") == false || addrF.getText().equals("") == false) {
				TeamDTO t = new TeamDTO();
				t.setName(teamF.getText());
				t.setAddr(addrF.getText());
				tt.insert(t);
				JOptionPane.showMessageDialog(null, "�� ����� �Ϸ�Ǿ����ϴ�.");
				this.setVisible(false);
			}

		}
		// �� �� ���� ��ư
		if (e.getSource() == teammod) {
			Mod m = new Mod();
			Object teamname = jc.getSelectedItem();
			System.out.println(teamname);
			if (teamname == null) {
				JOptionPane.showMessageDialog(null, "�� �̸��� ��Ȯ�� Ŭ�����ּ���");

			} else {
				m.teamNameMod(teamname);
				this.setVisible(false);
			}

		}
		// ������ ���� ��ư
		if (e.getSource() == addrmod) {
			Mod m = new Mod();
			Object teamname = jc.getSelectedItem();
			System.out.println(teamname);
			if (teamname == null) {
				JOptionPane.showMessageDialog(null, "�� �̸��� ��Ȯ�� Ŭ�����ּ���.");

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
