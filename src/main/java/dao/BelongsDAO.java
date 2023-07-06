package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Belong;

public class BelongsDAO extends BaseDAO {

	public BelongsDAO(DataSource ds) {
		super(ds);
	}

	public void insert(Belong belong) throws SQLException {
		String query = "INSERT INTO Belongs (categoryName, gameId) values (?, ?)";
		
		// Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		// Retrieve connection and make prepared statement	

			//Set prepared statement values
			ps.setString(1, belong.getCategory());
			ps.setInt(2, belong.getGameId());
			//Set prepared statement values

			//Insert belong into database
			ps.execute();
			//Insert belong into database	
		} 
	}
	
	public void deleteAllBelongs(int gameId) throws SQLException {
		String query = "DELETE FROM Belongs WHERE Belongs.gameId= ?";
		
		// Retrieve connection and make prepared statement
			try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			// Retrieve connection and make prepared statement	

				//Set prepared statement values
				ps.setInt(1, gameId);
				//Set prepared statement values

				//execute
				ps.execute();
				//execute	
			}
	}
	
}
