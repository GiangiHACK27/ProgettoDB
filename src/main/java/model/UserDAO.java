package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.sql.DataSource;

import model.User.Role;

public class UserDAO extends BaseDAO {

	public UserDAO(DataSource ds) {
		super(ds);
	}
	
	public User getUserFromUsername(String username) throws SQLException {
		
		//Retrieve connection
		Connection conn = ds.getConnection();
		//Retrieve connection
		
		//Retrieve the user from database for username
		String query = "SELECT * FROM User WHERE username = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		//Retrieve the user from database for username
		
		//Check if the user exist
		if(!rs.next()) {
			conn.close();
			return null;
		}
		//Check if the user exist
		
		//Create the user
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
		
		//Create the user
		
		//Close connection
		conn.close();
		//Close connection
		
		return user;
	}
	
	public void insertUser(String username, String password, String email) throws SQLException {
		// Retrieve connection
		Connection conn = ds.getConnection();
		// Retrieve connection

		String query = "INSERT into user (username, password, email) values (?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(query);
		
		//Set prepared statement values
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, email);
		//Set prepared statement values

		//Insert user into database
		ps.execute();
		//Insert user into database

		//Close connection
		conn.close();
		//Close connection

	}
}
