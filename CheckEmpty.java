package Checking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckEmpty {
	public static boolean isNotPresent(int id) {
		int pqnt=0;
		float ppi=0.0f;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/karan";
		String user="karan";
		String pass="89588Karan@";
		Connection con=DriverManager.getConnection(url,user,pass);
		String q="select * from purchase where id='"+id+"'";
		Statement st=con.createStatement();
		ResultSet res=st.executeQuery(q);
		while(res.next()) {
			ppi=res.getFloat("ppi");
			pqnt=res.getInt("pqnt");
		}
		con.close();
		}
		catch(Exception e) {}
		if(ppi==0.0&&pqnt==0)
			return true;
		else return false;
	}
	
}

