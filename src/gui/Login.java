package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.MemberDAO;
import dto.MemberDTO;

public class Login extends Gbinsert implements ActionListener {

	ImageIcon menuimg = new ImageIcon("./img/loginback.png");

	JLabel id = new JLabel("ID : ");
	JLabel ps = new JLabel("비밀번호 : ");
	JLabel title = new JLabel("야구 매니져");

	JButton login = new JButton("로그인");
	JButton res = new JButton("회원가입");
	JTextField idf = new JTextField();
	JPasswordField psf = new JPasswordField();

	public Login() {

		idf.setPreferredSize(new Dimension(150, 30));
		psf.setPreferredSize(new Dimension(150, 30));

		setLayout(gb);
		// 1행

		gbinsert(title, 1, 0, 3, 1);
		// 2행
		gbinsert(id, 0, 1, 1, 1);
		gbinsert(idf, 1, 1, 2, 1);
		// 3행
		gbinsert(ps, 0, 2, 1, 1);
		gbinsert(psf, 1, 2, 2, 1);
		// 4행
		gbinsert(res, 1, 3, 1, 1);
		gbinsert(login, 2, 3, 1, 1);

		login.addActionListener(this);
		res.addActionListener(this);

		this.setBounds(200, 200, 300, 200);
		this.setTitle("야구 매니저");
		this.getContentPane().setBackground(Color.white);

		this.setVisible(true);
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Menu mu = new Menu();
		if (e.getSource() == login) {
			String idchk = idf.getText();
			char[] passchar = psf.getPassword();
			String pass = new String(passchar);
			MemberDAO mm = MemberDAO.getinstance();
			MemberDTO m = mm.login(idchk, pass);
			if (m == null) {
				JOptionPane.showMessageDialog(null, "입력하신 계정의 정보를 확인해주세요");
				idf.setText("");
				psf.setText("");
			} else {
				// id.logintrue();
				mu.MainMenu(m);
				this.setVisible(false);
			}

		} else if (e.getSource() == res) {
			new SignUp();
			// this.setVisible(false);

		}

	}

}
