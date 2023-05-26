package LoginFrame;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Welcome {
	Welcome(){
		JWindow w=new JWindow();
		w.setSize(220,220);
		w.setLocationRelativeTo(null);
		JPanel panel=new JPanel();
		w.add(panel);
		JLabel label =new JLabel(new ImageIcon("E:\\Academics Documents\\SEM4\\Mini Project\\images.png"));
		panel.add(label);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		w.setVisible(true);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {};
		w.dispose();
	}
}
