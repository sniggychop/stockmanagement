package SellPurchase;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Edit.Delete;
import Edit.SearchById;
import Edit.SearchByName;
import Edit.Update;
import LoginFrame.StartPage;

public class StockDisplay extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel top,center,bottom,right;
	JLabel l1;
	JTable table;
	DefaultTableModel model;
	JScrollPane sp;
	JButton back;
	JMenuBar menubar;
	JMenu menu,search;
	JMenuItem update,delete,searchById,searchByName;
	Object[][] data=new Object[100][4];
	int len=0;
	Container c;
	public StockDisplay() {
		this.setTitle("Stock Display Window");
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/karan";
			String user="karan";
			String pass="89588Karan@";
			Connection con = DriverManager.getConnection(url,user,pass);
			String q="select * from purchase";
			Statement st=con.createStatement();
			ResultSet res=st.executeQuery(q);
			int i=0;
			while(res.next()) {
				data[i][0]=res.getInt("id");
				data[i][1]=res.getString("pname");
				data[i][2]=res.getInt("ppi");
				data[i][3]=res.getInt("pqnt");
				i++;
			}
			len=i;
			con.close();
		}catch(Exception f) {}
		String[] names= {"Id","Product Name","Price per item","Qunatity"};
		model=new DefaultTableModel(data,names);
		table=new JTable(model);
		sp=new JScrollPane(table);
		c=this.getContentPane();
		
		top=new JPanel();
		c.add(top,BorderLayout.PAGE_START);
		menubar=new JMenuBar();
		menu=new JMenu("Edit");
		update=new JMenuItem("Update");
		update.addActionListener(this);
		delete=new JMenuItem("Delete");
		delete.addActionListener(this);
		menu.add(update);
		menu.add(delete);
		menubar.add(menu);
		search=new JMenu("Search");
		searchById =new JMenuItem("Search By Id");
		searchByName=new JMenuItem("Search By Name");
		search.add(searchById);
		search.add(searchByName);
		searchById.addActionListener(this);
		searchByName.addActionListener(this);
		menubar.add(search);
		this.setJMenuBar(menubar);
		
		l1=new JLabel("Amount : "+StartPage.amount);
		top.add(l1);
		
		center = new JPanel();
		c.add(center,BorderLayout.CENTER);
		center.add(sp);
		
		bottom=new JPanel();
		c.add(bottom,BorderLayout.PAGE_END);
		back=new JButton("Back");
		back.addActionListener(this);
		bottom.add(back);
		
		
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			this.dispose();
		}
		else if(e.getSource()==update) {
			new Update();
			this.dispose();
		}
		else if(e.getSource()==searchById) {
			new SearchById();
		}
		else if(e.getSource()==searchByName) {
			new SearchByName();
		}
		if(e.getSource()==delete) {
			new Delete();
			this.dispose();			
		}
	}

}
