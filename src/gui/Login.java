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
	JLabel ps = new JLabel("��й�ȣ : ");
	JLabel title = new JLabel("�߱� �Ŵ���");

	JButton login = new JButton("�α���");
	JButton res = new JButton("ȸ������");
	JTextField idf = new JTextField();
	JPasswordField psf = new JPasswordField();

	public Login() {

		idf.setPreferredSize(new Dimension(150, 30));
		psf.setPreferredSize(new Dimension(150, 30));

		setLayout(gb);
		// 1��

		gbinsert(title, 1, 0, 3, 1);
		// 2��
		gbinsert(id, 0, 1, 1, 1);
		gbinsert(idf, 1, 1, 2, 1);
		// 3��
		gbinsert(ps, 0, 2, 1, 1);
		gbinsert(psf, 1, 2, 2, 1);
		// 4��
		gbinsert(res, 1, 3, 1, 1);
		gbinsert(login, 2, 3, 1, 1);

		login.addActionListener(this);
		res.addActionListener(this);

		this.setBounds(200, 200, 300, 200);
		this.setTitle("�߱� �Ŵ���");
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
				JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ ������ Ȯ�����ּ���");
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
