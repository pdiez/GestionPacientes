package util;

import java.sql.*;
import java.util.TimeZone;

public class BBDD {
	
	public static void main(String args[]){  
		try{  
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String tz = TimeZone.getDefault().getID();
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/gestionpacientes?serverTimezone="+tz+"","root","Qwerty123");  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from user");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
	}  

}
