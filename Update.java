package Edit;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.Statement;

import LoginFrame.StartPage;
import Checking.CheckEmpty;
import Checking.CheckingStringTonum;

public class Update extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1,l2,l3;
	JButton update,back;
	JTextField t1,t2;
	Container c;
	public Update(){
		this.setTitle("Update Window");
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
		l3=new JLabel("Enter new Product name : ");
		l3.setBounds(50,150,150,30);
		c.add(l3);
		t2=new JTextField();
		t2.setBounds(220,150,100,30);
		c.add(t2);
		update=new JButton("Update");
		update.setBounds(50,200,100,50);
		c.add(update);
		update.addActionListener(this);
		back=new JButton("Back");
		back.setBounds(170,200,100,50);
		c.add(back);
		back.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==update) {
			if(!CheckingStringTonum.isConvPosible(t1.getText())) {
				JOptionPane.showMessageDialog(this,"Please write in correct format");
				t1.setText("");
				t2.setText("");
			}
			else if(t1.getText().isEmpty()||t2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this,"Please fill All entries.");
				}
			else {
					int idToGet=Integer.parseInt(t1.getText());
					if(CheckEmpty.isNotPresent(idToGet)) {
						JOptionPane.showMessageDialog(this,idToGet+" is not present in database.");
						t1.setText("");
						t2.setText("");
					}
					else {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							String url="jdbc:mysql://localhost:3306/karan";
							String user="karan";
							String pass="89588Karan@";
							Connection con = DriverManager.getConnection(url,user,pass);
							String q="update purchase set pname='"+t2.getText()+"' where id='"+idToGet+"'";
							Statement st=con.createStatement();
							st.executeUpdate(q);
							con.close();		
						}catch(Exception f) {}
						JOptionPane.showMessageDialog(this,"Name Updated Successfully.","Udpate",JOptionPane.INFORMATION_MESSAGE);
						t1.setText("");
						t2.setText("");
						this.dispose();
					}
				}
			}
			if(e.getSource()==back) {
				this.dispose();
			}
	}


}
