// To save as "<CATALINA_HOME>\webapps\helloservlet\WEB-INF\src\mypkg\HelloServlet.java"
package com.academy.capstone;
 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
 
public class AddUser extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws IOException, ServletException {
   	User newUser = new User(request.getParameter("Username"),
				request.getParameter("Password"),
				request.getParameter("FirstName"),
				request.getParameter("LastName"),
				request.getParameter("Address1"),
				request.getParameter("Address2"),
				request.getParameter("City"),
				request.getParameter("State"),
				request.getParameter("Zip"),
				request.getParameter("HomePhone"),
				request.getParameter("CellPhone"),
				request.getParameter("OfficePhone"),
				request.getParameter("CompanyName"),
				request.getParameter("BranchLocation"),
				request.getParameter("FoodID"),
				request.getParameter("AdditionalInfo"),
				request.getParameter("Active"));
   	
   	DB_Helper db = new DB_Helper();
   	
   	if (db.addUser(newUser)) {
   		System.out.println("User " + newUser.username + " created successfully.");
   	}
   	else {
   		System.out.println("User creation for " + newUser.username + " failed.");
   	}
   	
   }
}