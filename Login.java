package LoginFrame;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.sql.Statement;
public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JTextField t1;
	JPasswordField pass;
	JLabel l1,l2;
	JButton btn1,btn2;
	Container c;
	JFrame f=new JFrame("Login");
	String username,password;
	int chance =3;
	Login(String u,String p){
		username=u;
		password=p;
		this.setTitle("Login Window");
		this.setResizable(false);
		c=this.getContentPane();
		c.setLayout(null);
		l1=new JLabel("Enter Username : ");
		l1.setBounds(100,60,120,30);
		c.add(l1);
		t1=new JTextField(null);
		t1.setBounds(240,60,100,30);
		c.add(t1);
		l2=new JLabel("Enter Password : ");
		l2.setBounds(100,110,120,30);
		c.add(l2);
		pass=new JPasswordField(null);
		pass.setBounds(240,110,100,30);
		c.add(pass);
		btn1=new JButton("Login");
		btn1.setBounds(100,200,100,40);
		c.add(btn1);
		btn2=new JButton("Cancel");
		btn2.setBounds(240,200,100,40);
		c.add(btn2);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String u=t1.getText().toString();
		@SuppressWarnings("deprecation")
		String p=pass.getText().toString();
		if(e.getSource()==btn1) {
			if(u.isEmpty()||p.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please fill All entries.");
			}
			else if(username.equals(u)&&password.equals(p)) {
				this.dispose();
				new StartPage();
				t1.setText("");
				pass.setText("");
			}
			else {
				chance-=1;
				JOptionPane.showMessageDialog(this,"Invalid User.\nNow You have Only "+chance+" trial left.");
				if(chance==0) {
					System.exit(0);
				}
				t1.setText("");
				pass.setText("");
			}
		}
		if(e.getSource()==btn2) {
			int sel=JOptionPane.showConfirmDialog(f, "Do you Wants to exit ?","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(sel==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	public static void main(String[] k)throws Exception {
		new Welcome();
		String pattern1="javax.swing.plaf.nimbus.NimbusLookAndFeel";
		//String pattern2="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		//String pattern3="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		UIManager.setLookAndFeel(pattern1);
		String checkuser="",checkpass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/karan";
		String user="karan";
		String pass="89588Karan@";
		Connection con=DriverManager.getConnection(url,user,pass);
		String q="select * from login";
		Statement st=con.createStatement();
		ResultSet res=st.executeQuery(q);
		while(res.next()) {
			checkuser=res.getString("user");
			checkpass=res.getString("pass");
		}
		con.close();
		Login l=new Login(checkuser,checkpass);
		l.setSize(600,400);
		l.setLocationRelativeTo(null);
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l.setVisible(true);
	}
}

