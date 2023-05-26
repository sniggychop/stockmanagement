package LoginFrame;

import java.awt.BorderLayout;
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

import Checking.CheckingStringTonum;
import SellPurchase.Purchase;
import SellPurchase.SellProduct;
import SellPurchase.StockDisplay;

public class StartPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static float amount=0.0f;
	JButton stock,sell,purchase,about,exit,addMoney,back;
	static JLabel label;
	JLabel label1;
	StartPage(){
		this.setTitle("Start Window");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container c=this.getContentPane();
		c.setLayout(new BorderLayout());
		label=new JLabel("Amount : "+amount);
		JPanel top=new JPanel();
		c.add(top,BorderLayout.PAGE_START);
		top.add(label);
		
		JPanel panel=new JPanel();
		GridLayout g=new GridLayout(2,3);
		g.setVgap(10);
		g.setHgap(10);
		panel.setLayout(g);
		c.add(panel,BorderLayout.CENTER);
		stock=new JButton("Stocks");
		panel.add(stock);
		stock.addActionListener(this);
		sell=new JButton("Sell");
		panel.add(sell);
		sell.addActionListener(this);
		purchase=new JButton("Purchase");
		panel.add(purchase);
		purchase.addActionListener(this);
		addMoney=new JButton("Add Money");
		panel.add(addMoney);
		addMoney.addActionListener(this);
		about=new JButton("About");
		panel.add(about);
		about.addActionListener(this);
		exit=new JButton("Exit");
		panel.add(exit);
		exit.addActionListener(this);
		this.setVisible(true);
		
		JPanel bottom=new JPanel();
		c.add(bottom,BorderLayout.PAGE_END);
		
		JPanel right=new JPanel();
		c.add(right,BorderLayout.EAST);
		
		JPanel left= new JPanel();
		c.add(left,BorderLayout.WEST);
		
		float a=0.0f;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/karan";
		String user="karan";
		String pass="89588Karan@";
		Connection con = DriverManager.getConnection(url,user,pass);
		String q="select * from amount";
		Statement st=con.createStatement();
		ResultSet res=st.executeQuery(q);
		while(res.next()) {
			a=res.getFloat("amount");
		}
		con.close();
		}catch(Exception e ) {}
		amount=a;
		label.setText("Amount : "+amount);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==stock) {
			new StockDisplay();
		}
		else if(e.getSource()==purchase) {
			new Purchase();
		}
		else if(e.getSource()==sell) {
			new SellProduct();
		}
		else if(e.getSource()==about) {
			String str="This a Stock Management System Application.\n\nThis is used to Store , infomation related to stocks."
					+ "\n\nThere are Different Methods Like Sell, purchase , Stock display , to have a control over the"
					+ " software.\n\nSell is used to sell different products that are present"
					+ "\n\nPurchase is used to add products to software."
					+ "\n\nStock is there to see all stock which are present.";
			JOptionPane.showMessageDialog(this,str,"About the Application",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource()==addMoney) {
			String a=JOptionPane.showInputDialog("Enter Amount to Deposit : ");
			if(a!=null&&!a.isEmpty()) {
				if(!CheckingStringTonum.isConvPosible(a)) {
					JOptionPane.showMessageDialog(this,"Please write in correct format");
				}
				else {
					float am=Float.parseFloat(a);
					if(am<0) {
						JOptionPane.showMessageDialog(this,"Amount Cant be negative or zero.");
					}
					else
					{
						amount+=am;
					}
				}
			}
			label.setText("Amount : "+amount);
		}
		else if(e.getSource()==exit) {
			int sel=JOptionPane.showConfirmDialog(this,"Do you want to exit ?","Confirm",JOptionPane.YES_NO_OPTION);
			if(sel==JOptionPane.YES_OPTION) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/karan";
					String user="karan";
					String pass="89588Karan@";
					Connection con = DriverManager.getConnection(url,user,pass);
					String q="update amount set amount='"+amount+"'";
					Statement st=con.createStatement();
					st.executeUpdate(q);
					con.close();
					}catch(Exception f ) {}
				System.exit(0);
			}
		}
	}
	public static void updateAmount() {
		label.setText("Amount : "+amount);
	}
}
