package SellPurchase;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LoginFrame.StartPage;
public class Purchase extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel top,center,bottom;
	static JLabel label;
	JButton back,Existing,New;
	public Purchase(){
		this.setTitle("Purchase Window");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c=this.getContentPane();
		top=new JPanel();
		label=new JLabel("Amount "+StartPage.amount);
		top.add(label);
		c.add(top,BorderLayout.PAGE_START);
		
		center = new JPanel();
		GridLayout g=new GridLayout(1,2);
		center.setLayout(g);
		Existing=new JButton("Purchase Exiting Product");
		Existing.addActionListener(this);
		New=new JButton("Purchase New Product");
		New.addActionListener(this);
		center.add(Existing);
		center.add(New);
		g.setHgap(30);
		g.setVgap(30);
		c.add(center,BorderLayout.CENTER);
		
		bottom=new JPanel();
		back=new JButton("Back");
		back.addActionListener(this);
		bottom.add(back);
		c.add(bottom,BorderLayout.PAGE_END);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==New) {
			new NewProduct();
		}
		else if(e.getSource()==Existing) {
			new ExistingPurchase();
		}
		if(e.getSource()==back) {
			StartPage.updateAmount();
			this.dispose();
		}
	}
	public static void updateInPurchase() {
			label.setText("Amount : "+StartPage.amount);
	}
}
