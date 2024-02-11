package org.jsp.fetchbyNameApp;
import java.sql.*;
import java.util.*;

public class FetchByName {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Name ");
		String snm=sc.next();
		Connection con=null; 
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstm=con.prepareStatement("select * from btm.student where name=?"); // DQL query
			// set the data for the placeholder
			pstm.setString(1,snm);
			rs=pstm.executeQuery(); // execute the query
			if(rs.next()) {
				int id=rs.getInt("id");// we will be get 101
				Double per=rs.getDouble("per");
				System.out.println("Student id is "+id);
				System.out.println("Student percentage is "+per);
			}
			else {
				System.err.println("Data not found for name "+snm);
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
