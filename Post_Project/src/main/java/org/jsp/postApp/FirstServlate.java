package org.jsp.postApp;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FirstServlate  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String sid=req.getParameter("i");
		String sname=req.getParameter("nm");
		String dept=req.getParameter("dp");
		String sperc=req.getParameter("pr");
		
		// Presentation logic // Servlet Technology
	
		PrintWriter out=resp.getWriter();
//		out.println("<html>");
//        out.println("<head><title>Form Data</title></head>");
//        out.println("<body>");
//        out.println("<h2>Form Data Received</h2>");
//        out.println("<p>ID: " + sid + "</p>");
//        out.println("<p>Name: " + sname + "</p>");
//        out.println("<p>Department: " + dept + "</p>");
//        out.println("<p>Percentage: " + sperc + "</p>");
//        out.println("</body></html>");
        
        out.println("<html><body bgcolor='red'> "
        		+ "<h1> Student Name is" +sname+"from"+dept+ "</h1>"
        		+"</body></html> ");
        out.close();
        java.sql.Connection con=null;
        PreparedStatement pstm=null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://locahost:3306?user=root&password=admin");
			pstm=con.prepareStatement("insert into btm.student values(?,?,?,?)");
			pstm.setInt(1,Integer.parseInt(sid));
			pstm.setString(2, sname);
			pstm.setString(3, dept);
			pstm.setDouble(4, Double.parseDouble(sperc));
			// execute the sql statement
			pstm.execute();
			System.out.println("Record Inserted");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
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
		}
	}
}
