package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import model.Message;
import model.Role;
import model.User;

public class Persistent {
	
	private static final String constring = "jdbc:mysql://localhost:3306/gestionpacientes";
	private static final String dbuser = "root";
	private static final String dbpass = "secreto";
	
	private static ResultSet Query(String q) {
		ResultSet rs = null;
		CachedRowSet crs = null;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(constring, dbuser, dbpass);  
			Statement stmt=con.createStatement();  
			rs=stmt.executeQuery(q); 
			crs = new CachedRowSetImpl();
			crs.populate(rs);
			con.close();  
		} catch (ClassNotFoundException e) {
		    System.out.println("JDBC no encontrado");
		    e.printStackTrace();
		}
			catch(Exception e){ System.out.println(e);
		}  
		return crs;
	}
	
	private static void Execute(String q) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(constring, dbuser, dbpass);  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate(q); 
			con.close();  
		} catch (ClassNotFoundException e) {
		    System.out.println("JDBC no encontrado");
		    e.printStackTrace();
		}
			catch(Exception e){ System.out.println(e);
		}  
	}
	
	public static User getUserById (int i) {
		User u = new User();
		String q = "SELECT id,user,password,name, role_id, phone, notes FROM USER WHERE id = " + i +";";
		ResultSet rs = Query(q);
		if (rs!=null) {
			try {
				while(rs.next()) {
					u.setId(rs.getInt(1));
					u.setUsername(rs.getString(2));
					u.setPassword(rs.getString(3));
					u.setName(rs.getString(4));
					u.setRole(rs.getInt(5));
					u.setPhone(rs.getString(6));
					u.setNotes(rs.getString(7));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}
	
	public static User getUserByUsername (String user) {
		User u = new User();
		String q = "SELECT id,user,password,name, role_id, phone, notes FROM USER WHERE user = '" + user +"';";
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setName(rs.getString(4));
				u.setRole(rs.getInt(5));
				u.setPhone(rs.getString(6));
				u.setNotes(rs.getString(7));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public static User getUserByRoleId (int i) {
		User u = new User();
		String q = "SELECT id,user,password,name, role_id, phone, notes FROM USER WHERE role_id = " + i +";";
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setName(rs.getString(4));
				u.setRole(rs.getInt(5));
				u.setPhone(rs.getString(6));
				u.setNotes(rs.getString(7));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	
	public static ArrayList<User> getUserList() {
		ArrayList<User> ul = new ArrayList<User>();
		String q = "SELECT id,user,password,name, role_id, phone, notes FROM USER;";
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setName(rs.getString(4));
				u.setRole(rs.getInt(5));
				u.setPhone(rs.getString(6));
				u.setNotes(rs.getString(7));
				ul.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ul;
	}
	
	
	
	public static boolean validateCredentials (String u, String p) {
		ResultSet rs = null;
		boolean res = false;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(constring, dbuser, dbpass);  
			String q = "SELECT 1 FROM USER WHERE user=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(q);  
			stmt.setString(1, u);
			stmt.setString(2, p);
			rs=stmt.executeQuery(); 
			while(rs.next()) {
				res = true;
				System.out.println(rs.getInt(1));
			}
			con.close();  
		} catch (ClassNotFoundException e) {
		    System.out.println("JDBC no encontrado");
		    e.printStackTrace();
		}
			catch(Exception e){ System.out.println(e);
		}  
		
		return res;
	}
	
	public static void saveUser(User u) {
		
		String q = null;
		
		if (u.getId()==0) {
		
			q = "INSERT INTO USER (user,password,name, role_id, phone, notes) VALUES ("
					+ "'" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getName() +"', " + u.getRole() 
					+ ",'" + u.getPhone() + "', '" + u.getNotes() + "');";
		} else {
			q = "UPDATE USER SET user = '" + u.getUsername() + "'"
						+ ", password = '" + u.getPassword() + "'"
						+ ", name = '" + u.getName() + "'"
						+ ", role_id = " + u.getRole()
						+ ", phone = '" + u.getPhone() + "'"
						+ ", notes = '" + u.getNotes() + "'"
						+ " WHERE id = " + u.getId() + ";";
		}
		
		Execute(q);
	}
	
	public static void delUser (User u) {
		String q = "DELETE FROM USER WHERE id = "+ u.getId() + ";";
		Execute(q);
	}
	
	public static ArrayList<Message> getMessagesByUser(User u) {
		ArrayList<Message> msgs = new ArrayList<Message>();
		
		String q = "select u.id, u2.id, m.date, m.message from message m, user u, user u2 "
				+ "where m.from_id = u.id and m.to_id = u2.id and (m.from_id = "+ u.getId() +"  or m.to_id = "+ u.getId() + ");";
		
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				Message m = new Message();
				m.setFrom(rs.getInt(1));
				m.setTo(rs.getInt(2));
				m.setDate(rs.getDate(3));
				m.setText(rs.getString(4));
				msgs.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgs;
	}
	
	public static ArrayList<Message> getMessagesBetween(User a, User b) {
		ArrayList<Message> msgs = new ArrayList<Message>();
		
		String q = "select u.id, u2.id, m.date, m.message from message m, user u, user u2 "
				+ "where m.from_id = u.id and m.to_id = u2.id " 
				+ "and (m.from_id = "+ a.getId() +"  or m.to_id = "+ a.getId() + ") "
				+ "and (m.from_id = "+ b.getId() +"  or m.to_id = "+ b.getId() + ");";
		
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				Message m = new Message();
				m.setFrom(rs.getInt(1));
				m.setTo(rs.getInt(2));
				m.setDate(rs.getDate(3));
				m.setText(rs.getString(4));
				msgs.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgs;
	}
	
	
	public static void saveMessage(Message m) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateformat = dateFormat.format(m.getDate());
		
		String q = "INSERT INTO MESSAGE (FROM_ID, TO_ID, DATE, MESSAGE) VALUES ("
				+ m.getFrom() + ", " + m.getTo() + ", '" + dateformat +"', '" + m.getText() + "');";
		
		Execute(q);
	}
	
	public static Role getRoleById(int i) {
		Role r = new Role();
		String q  = "SELECT id,rolename FROM ROLE WHERE id = " + i +";";
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				r.setId(rs.getInt(1));
				r.setRoleName(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public static ArrayList<Role> getRoleList() {
		ArrayList<Role> rl = new ArrayList<Role>();
		String q  = "SELECT id,rolename FROM ROLE;";
		ResultSet rs = Query(q);
		try {
			while(rs.next()) {
				Role r = new Role();
				r.setId(rs.getInt(1));
				r.setRoleName(rs.getString(2));
				rl.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rl;
	}
	
}
