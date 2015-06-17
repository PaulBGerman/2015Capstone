package com.academy.capstone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public Registration() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @return 
		 * @throws IOException 
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	    
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	PrintWriter out = response.getWriter();
	    	out.println("User Registration Page");
	    	
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    	} catch (ClassNotFoundException e) {
	    		System.out.println("Where is your MySQL JDBC Driver?");
	    		e.printStackTrace();
	    		return;
	    	}
	     
	    	System.out.println("MySQL JDBC Driver Registered!");
	    	java.sql.Connection connection = null;
	     
	    	try {
	    		connection = DriverManager.getConnection("jdbc:mysql://sql4.freemysqlhosting.net/sql480630","sql480630", "rZ4!rV5*");
	     
	    	} catch (SQLException e) {
	    		System.out.println("Connection Failed! Check output console");
	    		e.printStackTrace();
	    		return;
	    	}
	     
	    	if (connection != null) {
	    		System.out.println("You made it, take control your database now!");
	    		
	    	} else {
	    		System.out.println("Failed to make connection!");
	    	}
	    	
	    	
	    	
	    	String selectStatement = "SELECT * FROM Users WHERE Username = ?;";
	    	PreparedStatement prepStmt;
			try {
				prepStmt = connection.prepareStatement(selectStatement);
				prepStmt.setString(1, "nicholas.greene@us.sogeti.com");
				ResultSet rs = prepStmt.executeQuery();
				
				while (rs.next()) {
			        String s = rs.getString("Username");

			        out.println("<br><br>" + s);
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	
	    	
	    	
	      }
	    

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
		}

}


