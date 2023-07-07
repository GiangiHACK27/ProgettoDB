package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.sql.DataSource;

import model.User;
import model.User.Role;

public class UserDAO extends BaseDAO {

	public UserDAO(DataSource ds) {
		super(ds);
	}
	
	public User getUserFromUsername(String username) throws SQLException {
		
		User user;
		
		String query = "SELECT * FROM User WHERE username = ?";
		
		//Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		//Retrieve connection and make prepared statement
			
			//Retrieve the user from database for username
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			//Retrieve the user from database for username
			
			//Check if the user exist
			if(!rs.next()) {
				return null;
			}
			//Check if the user exist
			
			//Create the user
			user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));		
			//Create the user
		}
				
		return user;
	}
	
	public void insertUser(String username, String password, String email) throws SQLException {
		
		String query = "INSERT into user (username, password, email) values (?, ?, ?)";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement
			
			//Set prepared statement values
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database
		}
	}
	
	public void updatePasswordAndEmail(String password, String email, String username) throws SQLException {
		
		String query = "UPDATE User SET password = ?, email = ? WHERE username = ?";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement
		
			//Set prepared statement values
			ps.setString(1, password);
			ps.setString(2, email);
			ps.setString(3, username);
			//Set prepared statement values
			
			//Execute query
			ps.execute();
			//Execute query
		}
	}
	
	public boolean usernameAlreadyExist(String username) throws SQLException {		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query
			String query = "SELECT * FROM user WHERE username = ?";
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, username);
			//Construct query
			
			ResultSet rs = ps.executeQuery();
			
			//If we find a user with the same username, we return true
			if(rs != null && rs.next())
				return true;
			//If we find a user with the same username, we return true
			
		} finally {
			if(ps != null)
				ps.close();
		}
		
		return false;
	}
	
	public boolean emailAlreadyExist(String email) throws SQLException {		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query
			String query = "SELECT * FROM user WHERE email = ?";
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, email);
			//Construct query
			
			ResultSet rs = ps.executeQuery();
			
			//If we find a user with the same username, we return true
			if(rs != null && rs.next())
				return true;
			//If we find a user with the same username, we return true
			
		} finally {
			if(ps != null)
				ps.close();
		}
		
		return false;
	}
}
