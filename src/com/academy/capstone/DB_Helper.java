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

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class DB_Connection
 */
@WebServlet("/DB_Connection")
public class DB_Helper extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.sql.Connection connection;
	Validator vlad;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DB_Helper() {
        super();

        vlad = new Validator();
    }
    
    public void Connect() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		System.out.println("Where is your MySQL JDBC Driver?");
    		e.printStackTrace();
    		return;
    	}
     
    	System.out.println("MySQL JDBC Driver Registered!");
    	connection = null;
     
    	try {
    		connection = DriverManager.getConnection("jdbc:mysql://sql4.freemysqlhosting.net/sql480630","sql480630", "rZ4!rV5*");
     
    	} catch (SQLException e) {
    		System.out.println("Connection Failed! Check output console");
    		e.printStackTrace();
    		return;
    	}
     
    	if (connection != null) {
    		System.out.println("You made it, take control your database now!");
    		return;
    	} else {
    		System.out.println("Failed to make connection!");
    	}
    	
		return;
    }

    public void Disconect() {
    	try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Database connection failed to close.");
		}
    }

    /*
     * 
     */
    public User getUser(String userName) {
    	if(vlad.Email(userName) == false){
    		System.out.println("username ill-formed - query cancelled");
    		return null;
    	}
    	
    	Connect();
    	
    	String selectStatement = "SELECT * FROM Users WHERE Username = ?;";
    	PreparedStatement prepStmt;
		try {
			prepStmt = connection.prepareStatement(selectStatement);
			prepStmt.setString(1, userName);
			ResultSet rs = prepStmt.executeQuery();
			
			rs.first();
			
			User result = new User();
			
			result.userID = rs.getString(1);
			result.username = rs.getString(2);
			result.password = rs.getString(3);
			result.firstName = rs.getString(4);
			result.lastName = rs.getString(5);
			result.addr1 = rs.getString(6);
			result.addr2 = rs.getString(7);
			result.city = rs.getString(8);
			result.state = rs.getString(9);
			result.zip = rs.getString(10);
			result.homePhone = rs.getString(11);
			result.cellPhone = rs.getString(12);
			result.officePhone = rs.getString(13);
			result.companyName = rs.getString(14);
			result.branchLocation = rs.getString(15);
			result.foodID = rs.getString(16);
			result.addInfo = rs.getString(17);
			result.active = rs.getString(18);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("Prepared statement failed.");
		}
		finally {
			Disconect();
		}
		
		return null;
    }
    
    public Boolean addUser(User newUser) {    	
    	String query = "INSERT INTO Users(Username, FirstName, LastName, Address1, ";
		query = query + "Address2, ";
		query = query +	"City, State, Zip, ";
		query = query + "HomePhone, ";
		query = query + "CellPhone, ";
		query = query + "OfficePhone, ";
		query = query + "CompanyName, ";
		query = query + "BranchLocation, ";
		query = query + "Food_ID, ";
		query = query + "AdditionalInfo, ";
		query = query + "Active) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println(query);
    	
    	Connect();
    	
    	try {    			
    			PreparedStatement prepStmt = connection.prepareStatement(query);
    			prepStmt.setString(1, newUser.username);	//Their email is their username
    			prepStmt.setString(2, newUser.firstName);
    			prepStmt.setString(3, newUser.lastName);
    			prepStmt.setString(4, newUser.addr1);
    			if(newUser.addr2 != null){
    				prepStmt.setString(5, newUser.addr2);
    			}else{
    				prepStmt.setString(5, "");
    			}
    			prepStmt.setString(6, newUser.city);
    			prepStmt.setString(7, newUser.state);
    			prepStmt.setString(8, newUser.zip);
    			if(newUser.homePhone != null){
    				prepStmt.setString(9, newUser.homePhone);
    			}else{
    				prepStmt.setString(9, "");
    			}
    			if(newUser.cellPhone != null){
    				prepStmt.setString(10, newUser.cellPhone);
    			}else{
    				prepStmt.setString(10, "");
    			}
    			if(newUser.officePhone != null){
    				prepStmt.setString(11, newUser.officePhone);
    			}else{
    				prepStmt.setString(11, "");
    			}
    			prepStmt.setString(12, newUser.companyName);
    			if(newUser.branchLocation != null){
    				prepStmt.setString(13, newUser.branchLocation);
    			}else{
    				prepStmt.setString(13, "");
    			}
    			prepStmt.setString(14, newUser.foodID);
    			if(newUser.addInfo != null){
    				prepStmt.setString(15, newUser.addInfo);
    			}else{
    				prepStmt.setString(15, "");
    			}
    			prepStmt.setString(16, "Y");
    			
    			System.out.println("Executing statement");
    			System.out.println(prepStmt.toString());
    			
    			prepStmt.executeUpdate();
    			
    			return true;
    	}
    	catch(Exception e) {
    		System.out.println("User creation failed.");
    		System.out.println(e.toString());
    		return false;
    		}
    	finally {
    		Disconect();
    	}
    }

    public Boolean Login(String username, String password){
    	if(vlad.Email(username) == false || vlad.Password(password) == false){
    		System.out.println("Login ill-formed - login cancelled");
    		return null;
    	}
    	
    	Connect();
    	
    	String selectStatement = "SELECT Users.User_ID, Link.Link FROM Users, Link WHERE Link.User_ID = Users.User_ID AND Users.Username = ?;";
    	PreparedStatement prepStmt;
		try {
			prepStmt = connection.prepareStatement(selectStatement);
			prepStmt.setString(1, username);
			ResultSet rs = prepStmt.executeQuery();
			
			rs.first();
			
			if (rs.getString(2) == password)
			{
				System.out.println("Login successful!");
				return true;
			}
			else
			{
				System.out.println("Login fauled.");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Prepared statement failed.");
		}
		finally {
			Disconect();
		}
		
		return false;
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
//        Validator v = new Validator();
//        out.println(v.Phone("(815) 200-1042").toString() + "<br><br>");
        
//        Connect();
        
        User Paul = new User("Superman","secret","Paul","Superman","1001 awesome ln.","","Manhattan","NY","60115","(555) 555-5555","(555) 555-5555","(555) 555-5555","Sogeti","Omaha","1","Paul is a consultant!","Y");
        
        addUser(Paul);
//        out.println(getUser("nicholas.greene@us.sogeti.com").username);
        
//        Disconect();
       
        out.println("Hooray!");
//        
      }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
