package SellPurchase;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Checking.CheckingStringTonum;
import LoginFrame.StartPage;

public class NewProduct extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1,l2,l3,l4,l5;
	JButton pur,back;
	JTextField t1,t2,t3;
	Container c;
	public NewProduct() {
		this.setTitle("New Production Adding Window");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(null);
		l1=new JLabel("Amount : "+StartPage.amount);
		l1.setBounds(100,50,200,30);
		c.add(l1);
		l2=new JLabel("Enter Product Name : ");
		l2.setBounds(50,100,150,30);
		c.add(l2);
		t1=new JTextField();
		t1.setBounds(220,100,100,30);
		c.add(t1);
		l5=new JLabel("Enter Price per item : ");
		l5.setBounds(50,150,150,30);
		c.add(l5);
		t3=new JTextField();
		t3.setBounds(220,150,100,30);
		c.add(t3);
		l3=new JLabel("Enter Qunatity to buy : ");
		l3.setBounds(50,200,150,30);
		c.add(l3);
		t2=new JTextField();
		t2.setBounds(220,200,100,30);
		c.add(t2);
		l4=new JLabel("Amount to Pay : ");
		l4.setBounds(100,250,150,30);
		c.add(l4);
		pur=new JButton("Purchase");
		pur.setBounds(50,300,100,50);
		c.add(pur);
		pur.addActionListener(this);
		back=new JButton("Back");
		back.setBounds(170,300,100,50);
		c.add(back);
		back.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pur) {
			if(!CheckingStringTonum.isConvPosible(t2.getText())||!CheckingStringTonum.isConvPosible(t3.getText())) {
				JOptionPane.showMessageDialog(this,"Please write in correct format");
				t1.setText("");
				t2.setText("");
				t3.setText("");
			}
			else if(t1.getText().isEmpty()||t2.getText().isEmpty()||t3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please fill All entries.");
			}
			else if(Float.parseFloat(t3.getText())<=0)
				JOptionPane.showMessageDialog(this,"Price per item cant be 0 or less 0.");
			else if(Float.parseFloat(t2.getText())<0)
				JOptionPane.showMessageDialog(this,"Qunatity cant be negative.");
			else {
				float ppi=Float.parseFloat(t3.getText());
				float qnt=Integer.parseInt(t2.getText());
				if(ppi*qnt<=StartPage.amount) {
					StartPage.amount-=ppi*qnt;
					l1.setText("Amount : "+StartPage.amount);
					l4.setText("Amount to pay : "+ppi*qnt);
					JOptionPane.showMessageDialog(this,"Purchased Suceesfully.","Purchase",JOptionPane.INFORMATION_MESSAGE);
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						String url="jdbc:mysql://localhost:3306/karan";
						String user="karan";
						String pass="89588Karan@";
						Connection con = DriverManager.getConnection(url,user,pass);
						String q="insert into purchase(pname,ppi,pqnt) values(?,?,?)";
						PreparedStatement pst=con.prepareStatement(q);
						pst.setString(1,t1.getText());
						pst.setFloat(2, Float.parseFloat(t3.getText()));
						pst.setInt(3, Integer.parseInt(t2.getText()));
						pst.executeUpdate();
						con.close();
					}catch(Exception f) {}
				}
				else {
					JOptionPane.showMessageDialog(this,"Not Enoung Money to buy.","Purchase",JOptionPane.INFORMATION_MESSAGE);
					t1.setText("");
					t2.setText("");
					t3.setText("");
				}
			}
		}
		if(e.getSource()==back) {
			Purchase.updateInPurchase();
				this.dispose();
		}
	}
}
