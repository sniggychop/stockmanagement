package Edit;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import LoginFrame.StartPage;
import Checking.CheckEmpty;
import Checking.CheckingStringTonum;

public class SearchById extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1,l2;
	JButton search,back;
	JTextField t1;
	Container c;
	JPanel top,bottom;
	Object[][] data=new Object[1][4];
	String[] names= {"Id","Product Name","Price per item","Product Qunatity"};
	public SearchById(){
		this.setTitle("Search Window");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c=this.getContentPane();
		GridLayout g=new GridLayout(2,1);
		c.setLayout(g);
		top=new JPanel();
		top.setLayout(null);
		l1=new JLabel("Amount : "+StartPage.amount);
		l1.setBounds(100,30,200,30);
		top.add(l1);
		l2=new JLabel("Enter Product ID to Search : ");
		l2.setBounds(50,70,200,30);
		top.add(l2);
		t1=new JTextField();
		t1.setBounds(270,70,100,30);
		top.add(t1);
		search=new JButton("Search");
		search.setBounds(50,110,100,50);
		top.add(search);
		search.addActionListener(this);
		back=new JButton("Back");
		back.setBounds(170,110,100,50);
		top.add(back);
		back.addActionListener(this);
		c.add(top);
		bottom=new JPanel();
		c.add(bottom);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==search) {
			if(!CheckingStringTonum.isConvPosible(t1.getText())) {
				JOptionPane.showMessageDialog(this,"Please write in correct format");
				t1.setText("");
			}
			else if(t1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this,"Please fill All entries.");
				}
			else {
					int idToGet=Integer.parseInt(t1.getText());
					if(CheckEmpty.isNotPresent(idToGet)) {
						JOptionPane.showMessageDialog(this,idToGet+" is not present in database.");
						t1.setText("");
					}
					else {
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
								data[0][0]=res.getInt("id");
								data[0][1]=res.getString("pname");
								data[0][2]=res.getFloat("ppi");
								data[0][3]=res.getInt("pqnt");
							}				
							con.close();
						}catch(Exception f) {}
						new DisplayRecord(data,names);
				}
			}
		}
			if(e.getSource()==back) {
				this.dispose();
			}
	}
}
