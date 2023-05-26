package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Cart;
import model.Interested;

public class InterestedDAO extends BaseDAO {
	public InterestedDAO(DataSource ds) {
		super(ds);
	}
	
	public void insertInterest(Interested interest) throws SQLException {
		// Retrieve connection
		Connection conn = ds.getConnection();
		// Retrieve connection
		
		try {

			String query = "INSERT INTO Interested (username, gameId, category) values (?, ?, ?)";

			//Make prepared statement
			PreparedStatement ps = conn.prepareStatement(query);
			//Make prepared statement

			//Set prepared statement values
			ps.setString(1, interest.getUsername());
			ps.setInt(2, interest.getGameId());
			ps.setString(3, interest.getCategory().toString().toLowerCase());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database	
		} 
		finally {
			//Close connection
			conn.close();
			//Close connection
		}
	}
	
	public void removeInterest(Interested interest) throws SQLException {
		// Retrieve connection
		Connection conn = ds.getConnection();
		// Retrieve connection
		
		try {
			String query = "DELETE FROM Interested as I WHERE I.category = ? AND I.gameId = ? AND I.username = ?";

			//Make prepared statement
			PreparedStatement ps = conn.prepareStatement(query);
			//Make prepared statement

			//Set prepared statement values
			ps.setString(1, interest.getCategory().toString().toLowerCase());
			ps.setInt(2, interest.getGameId());
			ps.setString(3, interest.getUsername());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database
		}
		finally {
			//Close connection
			conn.close();
			//Close connection
		}
	}
	
	public Cart retrieveCart(String username, Interested.Category category) throws SQLException {
		Cart cart = null;
		
		// Retrieve connection
		Connection conn = ds.getConnection();
		// Retrieve connection
		
		try {
			//Create query for retrieve all interested of a user
			String query = "SELECT * FROM Interested as I WHERE I.username = ? AND I.category = ?";
			//Create query for retrieve all interested of a user
			
			//Make prepared statement
			PreparedStatement ps = conn.prepareStatement(query);
			//Make prepared statement

			//Set prepared statement values
			ps.setString(1, username);
			ps.setString(2, category.toString().toLowerCase());
			//Set prepared statement values
			
			//Execute query
			ResultSet rs = ps.executeQuery();
			//Execute query
			
			//Build the cart
			cart = new Cart(username);
			
			//Retrieve from result set the list of game ids			
			while(rs.next()) {
				cart.addGame(rs.getInt("gameId"));
			}
			//Retrieve from result set the list of game ids
			
			//Build the cart
		}
		finally {
			//Close connection
			conn.close();
			//Close connection	
		}
		
		return cart;
	}
}
