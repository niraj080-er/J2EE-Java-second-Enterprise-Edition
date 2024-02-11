package org.jsp.fetchidApp;
import java.sql.*;
import java.util.*;

public class FetchId {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Id ");
		int sid=sc.nextInt();
		Connection con=null; // create the connection interface for the connection between the Driver and Database server
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // first step to established the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstm=con.prepareStatement("select * from btm.student where id=?"); // DQL query
			// set the data for the placeholder
			pstm.setInt(1, sid);
			rs=pstm.executeQuery(); // execute the query
			if(rs.next()) {
				//int id=rs.getInt("id");// we will be get 101
				String name=rs.getString("name");
				Double per=rs.getDouble("per");
				System.out.println("Student id is "+sid);
				System.out.println("Student name is "+name);
				System.out.println("Student percentage is "+per);
			}
			else {
				System.err.println("Data not found for id "+sid);
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
