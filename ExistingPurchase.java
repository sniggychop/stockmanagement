package SellPurchase;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Checking.CheckingStringTonum;

import java.sql.Statement;

import LoginFrame.StartPage;

public class ExistingPurchase extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1,l2,l3,l4;
	JButton pur,back;
	JTextField t1,t2;
	Container c;
	ExistingPurchase(){
		this.setTitle("Existing Product Adding Window");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(null);
		l1=new JLabel("Amount : "+StartPage.amount);
		l1.setBounds(100,50,200,30);
		c.add(l1);
		l2=new JLabel("Enter Product ID : ");
		l2.setBounds(50,100,150,30);
		c.add(l2);
		t1=new JTextField();
		t1.setBounds(220,100,100,30);
		c.add(t1);
		l3=new JLabel("Enter Qunatity to buy : ");
		l3.setBounds(50,150,150,30);
		c.add(l3);
		t2=new JTextField();
		t2.setBounds(220,150,100,30);
		c.add(t2);
		l4=new JLabel("Amount to Pay : ");
		l4.setBounds(100,200,150,30);
		c.add(l4);
		pur=new JButton("Purchase");
		pur.setBounds(50,250,100,50);
		c.add(pur);
		pur.addActionListener(this);
		back=new JButton("Back");
		back.setBounds(170,250,100,50);
		c.add(back);
		back.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pur) {
			if(!CheckingStringTonum.isConvPosible(t1.getText())||!CheckingStringTonum.isConvPosible(t2.getText())) {
				JOptionPane.showMessageDialog(this,"Please write in correct format");
				t1.setText("");
				t2.setText("");
			}
			else if(t1.getText().isEmpty()||t2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please fill All entries.");
			}
			else if(Float.parseFloat(t2.getText())<=0) {
				JOptionPane.showMessageDialog(this,"Qunatity cant be negative or 0.");
				t1.setText("");
				t2.setText("");
			}
			else {
				float ppi=0;
				int prevqnt=0;
				int idToGet=Integer.parseInt(t1.getText());
				int qnt=Integer.parseInt(t2.getText());
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/karan";
					String user="karan";
					String pass="89588Karan@";
					Connection con = DriverManager.getConnection(url,user,pass);
					String q="select * from purchase where id='"+idToGet+"'";
					Statement st=con.createStatement();
					ResultSet res=st.executeQuery(q);
					while(res.next()) {
						ppi=res.getInt("ppi");
						prevqnt=res.getInt("pqnt");
					}				
					con.close();
				}catch(Exception f) {}
				if(ppi==0.0&&prevqnt==0) {
					JOptionPane.showMessageDialog(this,idToGet+" is not present in database.");
					t1.setText("");
					t2.setText("");
				}
				else {
					l4.setText("Amount to pay : "+ppi*qnt);
					if(ppi*qnt<=StartPage.amount) {
						StartPage.amount-=ppi*qnt;
						JOptionPane.showMessageDialog(this,"Purchased Sucessfully","Purchase",JOptionPane.INFORMATION_MESSAGE);
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							String url="jdbc:mysql://localhost:3306/karan";
							String user="karan";
							String pass="89588Karan@";
							Connection con = DriverManager.getConnection(url,user,pass);
							String q="update purchase set pqnt='"+(prevqnt+qnt)+"' where id='"+idToGet+"'";
							Statement st=con.createStatement();
							st.executeUpdate(q);
							con.close();
					
						}catch(Exception f) {}
						l1.setText("Amount : "+StartPage.amount);
						}
					else {
						JOptionPane.showMessageDialog(this,"Not Enougn Money to Buy.","Purchase",JOptionPane.INFORMATION_MESSAGE);
						t1.setText("");
						t2.setText("");
					}
				}
			}
		}
		if(e.getSource()==back) {
			Purchase.updateInPurchase();
			this.dispose();
		}
	}
}
