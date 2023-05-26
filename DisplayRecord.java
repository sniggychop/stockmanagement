package Edit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import LoginFrame.StartPage;

public class DisplayRecord extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel top,center,bottom;
	JButton back;
	Container c;
	JLabel label;
	DisplayRecord(Object[][] data,String[] names){
		this.setTitle("Found Records");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c=this.getContentPane();
		DefaultTableModel model=new DefaultTableModel(data,names);
		JTable table=new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		
		top=new JPanel();
		label=new JLabel("Amount : "+StartPage.amount);
		top.add(label);
		c.add(top,BorderLayout.PAGE_START);
		
		center = new JPanel();
		center.add(sp);
		c.add(center,BorderLayout.CENTER);
		
		bottom=new JPanel();
		back=new JButton("Back");
		bottom.add(back);
		back.addActionListener(this);
		c.add(bottom,BorderLayout.PAGE_END);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			this.dispose();
		}
	}
}
