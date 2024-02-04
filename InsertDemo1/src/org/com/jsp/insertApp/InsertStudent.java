package org.com.jsp.insertApp;
import java.sql.*;
public class InsertStudent {
	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		String qry="insert into btm.student values(101,'Ram' , 99.90)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded and register");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection established between java application and database server");
			stmt=con.createStatement();
			System.out.println("Paltform created");
			stmt.executeUpdate(qry);
			System.out.println("Data is inserted");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("close all the costly resources");
		}
	}

}
