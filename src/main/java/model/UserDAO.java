package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.sql.DataSource;

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
		user.setRole(rs.getString("role"));
		
		//Create the user
		
		//Close connection
		conn.close();
		//Close connection
		
		return user;
	}
}
