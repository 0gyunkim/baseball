package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.MemberDAO;
import dto.MemberDTO;

public class SignUp extends JFrame implements ActionListener, ItemListener {
	boolean idchk = false;
	String svalue = null;
	MemberDAO mm = MemberDAO.getinstance();
	JLabel q1 = new JLabel("회원가입을 환영합니다.");
	JLabel id = new JLabel("     ID : ");
	JLabel pass = new JLabel("     비밀번호 : ");
	JLabel name = new JLabel("     이름 : ");
	JLabel age = new JLabel("     나이 : ");
	JLabel pnum = new JLabel("     전화번호 : ");

	JTextField idf = new JTextField();
	JPasswordField passf = new JPasswordField();
	JTextField namef = new JTextField();
	JTextField agef = new JTextField();
	JTextField pnumf = new JTextField();

	JButton chk = new JButton("중복 확인");
	JButton reset = new JButton("초기화");
	JButton end = new JButton("완료");
	JRadioButton adm = new JRadioButton("관리자");
	JRadioButton basic = new JRadioButton("일반");
	ButtonGroup bg = new ButtonGroup();

	public SignUp() {
		this.setBounds(200, 200, 400, 250);

		// 아이디
		JPanel idP = new JPanel();
		idP.setLayout(new GridLayout(1, 3, 10, 10));
		idP.add(id);
		idP.add(idf);
		idP.add(chk);
		// 비밀번호
		JPanel passP = new JPanel();
		passP.setLayout(new GridLayout(1, 3, 10, 10));
		passP.add(pass);
		passP.add(passf);
		JLabel temp = new JLabel("  ");
		passP.add(temp);

		// 이름
		JPanel nameP = new JPanel();
		nameP.setLayout(new GridLayout(1, 3, 10, 10));
		nameP.add(name);
		nameP.add(namef);
		JLabel temp1 = new JLabel("  ");
		nameP.add(temp1);
		// 나이
		JPanel ageP = new JPanel();
		ageP.setLayout(new GridLayout(1, 3, 10, 10));
		ageP.add(age);
		ageP.add(agef);
		JLabel temp2 = new JLabel("  ");
		ageP.add(temp2);
		// 전화번호
		JPanel pnumP = new JPanel();
		pnumP.setLayout(new GridLayout(1, 3, 10, 10));
		pnumP.add(pnum);
		pnumP.add(pnumf);
		JLabel temp3 = new JLabel("  ");
		pnumP.add(temp3);
		// 버튼
		JPanel btnP = new JPanel();
		btnP.setLayout(new GridLayout(1, 2, 10, 10));
		btnP.add(reset);
		btnP.add(end);
		// 라디오버튼
		bg.add(adm);
		bg.add(basic);
		JPanel adminP = new JPanel();
		JLabel temp4 = new JLabel("  ");
		JLabel temp5 = new JLabel("  ");
		adminP.setLayout(new GridLayout(1, 4, 10, 10));
		adminP.add(temp4);
		adminP.add(adm);
		adminP.add(basic);
		adminP.add(temp5);

		// 메인
		JPanel mainP = new JPanel();
		mainP.setLayout(new GridLayout(7, 0, 10, 10));
		mainP.add(adminP);
		mainP.add(idP);
		mainP.add(passP);
		mainP.add(nameP);
		mainP.add(ageP);
		mainP.add(pnumP);
		mainP.add(btnP);

		this.add(mainP, "Center");

		chk.addActionListener(this);
		reset.addActionListener(this);
		end.addActionListener(this);
		adm.addItemListener(this);
		basic.addItemListener(this);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chk) {
			if (mm.chk(idf.getText()) == true) {
				JOptionPane.showMessageDialog(null, "중복된 아이디입니다. 다른 아이디를 사용해주세요.");
				idf.setText("");
				idchk = false;
			} else if (idf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 정확히 입력해주세요.");
				idf.setText("");
				idchk = false;
			} else if (idf.getText().charAt(0) == ' ') {
				JOptionPane.showMessageDialog(null, "아이디를 정확히 입력해주세요.");
				idchk = false;
			} else if (mm.chk(idf.getText()) == false && idf.getText().equals("") == false) {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
				idchk = true;
			}

		}
		if (e.getSource() == reset) {
			idf.setText("");
			passf.setText("");
			namef.setText("");
			agef.setText("");
			pnumf.setText("");

		}
		if (e.getSource() == end) {
			if (idchk == false) {
				JOptionPane.showMessageDialog(null, "회원가입이 불가능 합니다. 입력하신 내용을 확인해주세요.");

			} else {
				MemberDTO m = new MemberDTO();
				String mid = idf.getText();
				char[] passchar = passf.getPassword();
				String pass = new String(passchar);
				String name = namef.getText();
				String age = agef.getText();
				String pnum = pnumf.getText();
				String adm = svalue;
				if (mid.equals("") || pass.equals("") || name.equals("") || age.equals("") || adm.equals("")) {
					JOptionPane.showMessageDialog(null, "가입하실 계정의 정보를 정확히 입력해주세요");

				} else {

					m.setId(mid);
					m.setPass(pass);
					m.setName(name);
					m.setAge(age);
					m.setPnum(pnum);
					m.setAdm(adm);

					mm.insert(m);
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					this.setVisible(false);
				}
			}

		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			svalue = ((AbstractButton) e.getSource()).getText();
		}

	}

}
