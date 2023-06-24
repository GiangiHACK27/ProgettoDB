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
		String query = "INSERT INTO Interested (username, gameId, category) values (?, ?, ?)";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement	

			//Set prepared statement values
			ps.setString(1, interest.getUsername());
			ps.setInt(2, interest.getGameId());
			ps.setString(3, interest.getCategory().toString().toLowerCase());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database	
		} 
	}
	
	public void removeInterest(Interested interest) throws SQLException {

		String query = "DELETE FROM Interested as I WHERE I.category = ? AND I.gameId = ? AND I.username = ?";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement

			//Set prepared statement values
			ps.setString(1, interest.getCategory().toString().toLowerCase());
			ps.setInt(2, interest.getGameId());
			ps.setString(3, interest.getUsername());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database
		}
	}
	
	public Cart retrieveCart(String username, Interested.Category category) throws SQLException {
		Cart cart = null;

		String query = "SELECT * FROM Interested as I WHERE I.username = ? AND I.category = ?";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement

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
		
		return cart;
	}
	
	public void removeCart(String username, Interested.Category category) throws SQLException {
		String query = "DELETE FROM Interested as I WHERE I.category = ? AND I.username = ?";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement

			//Set prepared statement values
			ps.setString(1, category.toString().toLowerCase());
			ps.setString(2, username);
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database
		}
	}
}
