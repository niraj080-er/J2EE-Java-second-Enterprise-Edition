package org.jsp.loginApp;

import java.sql.*;
import java.util.Scanner;

public class LoginApp {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Name: ");
		String sname=sc.nextLine();
		System.out.println("Enter the password");
		String pass=sc.nextLine();
		
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String qry="select username from btm.user where name= ? and password=?";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstm=con.prepareStatement(qry);
			// set the data for the place holder
			pstm.setString(1, sname);
			pstm.setString(2,pass);
			rs=pstm.executeQuery(); // execute the query
			if(rs.next()) {
				String user=rs.getString("username");
				System.out.println("usernmae is "+user);
			}
			else {
				System.err.println("Invalid Credential");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstm!=null) {
				try {
					pstm.close();
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

			sc.close();
		}
	}

}
