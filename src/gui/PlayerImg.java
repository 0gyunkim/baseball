package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerImg extends Gbinsert {

	public void imgpop(String name) {
		ImageIcon img = new ImageIcon("./player/" + name + ".png");
		JLabel pImg = new JLabel(img);
		this.setBounds(575, 0, 0, 0);
		this.setTitle(name + " ÇÁ·ÎÇÊ");

		this.add(pImg);
		this.pack();
		this.setVisible(true);

	}

}
