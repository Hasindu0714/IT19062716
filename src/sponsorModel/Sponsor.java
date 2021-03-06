package sponsorModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sponsor {
	//Method to connect to the DB
		private Connection connect() 
		{ 
		Connection con = null; 
			try
			{ 
				//calling jdbc connection
				Class.forName("com.mysql.jdbc.Driver"); 
		 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafproject", "root", "himasha"); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			return con; 
		} 
		
		//insert
		//Method to add new sponsorings
		public String addSponsor(String sponsor_name, String sponsor_type, String email, String phone, String funding_amount) 
		{ 
			String output = ""; 
			try
			{ 
				//checking the connection
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for inserting.";
				} 
				// create a prepared statement
				String query = " insert into sponsors(`sponsorID`,`sponsor_name`,`sponsor_type`,`email`,`phone`,`funding_amount`)"+" values (?, ?, ?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, sponsor_name); 
				preparedStmt.setString(3, sponsor_type); 
				preparedStmt.setString(4, email); 
				preparedStmt.setString(5, phone); 
				preparedStmt.setString(6, funding_amount); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newSponsor = getSponsors(); 
				output = "{\"status\":\"success\", \"data\": \"" + newSponsor + "\"}";
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while adding sponsorships.\"}"; 
				System.err.println(e.getMessage());
			} 
		 return output;
		}
		
		//get
		//Method to retrieve sponsorings
		public String getSponsors() { 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for reading.";
				} 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Sponsor Name</th>" 
						+"<th>Sponsor Type</th><th>Email</th><th>Phone</th>"
						+ "<th>Funding Amount</th>" 
						+ "<th>Update</th><th>Remove</th></tr>";
	 
				String query = "select * from sponsors"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				
				//iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String sponsorID = Integer.toString(rs.getInt("sponsorID")); 
					String sponsor_name = rs.getString("sponsor_name"); 
					String sponsor_type = rs.getString("sponsor_type"); 
					String email = rs.getString("email"); 
					String phone = rs.getString("phone"); 
					String funding_amount = rs.getString("funding_amount");
				
					// Add into the html table
					output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + sponsorID
							 + "'>" + sponsor_name + "</td>";
					 output += "<td>" + sponsor_type + "</td>"; 
					 output += "<td>" + email + "</td>"; 
					 output += "<td>" + phone + "</td>"; 
					 output += "<td>" + funding_amount + "</td>"; 
				
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-sponsorid='"
							 + sponsorID + "'>" + "</td></tr>"; 
				} 
				con.close(); 
				//Complete the html table
					output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the sponsorship details."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		 }
			
		//Method to update sponsor details
		public String updateSponsoringDetails(String sponsorID, String sponsor_name, String sponsor_type, String email, String phone, String funding_amount){ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for updating.";
				} 
				// create a prepared statement
				String query = "UPDATE sponsors SET sponsor_name=?,sponsor_type=?,email=?,phone=?,funding_amount=? WHERE sponsorID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setString(1, sponsor_name); 
				preparedStmt.setString(2, sponsor_type); 
			 	preparedStmt.setString(3, email); 
			 	preparedStmt.setString(4, phone); 
			 	preparedStmt.setString(5, funding_amount); 
			 	preparedStmt.setInt(6, Integer.parseInt(sponsorID)); 
			 	
			 	// execute the query statement
			 	preparedStmt.execute(); 
			 	con.close(); 
			 	String newSponsor = getSponsors(); 
			 	output = "{\"status\":\"success\", \"data\": \"" + 
			 	newSponsor + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while updating sponsor details.\"}";  
				System.err.println(e.getMessage());
			} 
		 		return output; 
		 }
		
		//delete
		//Method to cancel a sponsor
		public String cancelSponsoring(String sponsorID) { 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for deleting.";
				} 
				// create a prepared statement
				String query = "delete from sponsors where sponsorID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(sponsorID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newSponsor = getSponsors(); 
			 	output = "{\"status\":\"success\", \"data\": \"" + 
			 	newSponsor + "\"}";  
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting sponsor details.\"}";  
				System.err.println(e.getMessage());
			} 
			return output; 
		}
}
