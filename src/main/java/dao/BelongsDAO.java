package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Belong;
import model.Category;

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
	
public List<Category> retrieveGameCategories(int gameId) throws SQLException {
		
		List<Category> categories = null;
		
		String query = "SELECT * FROM Belongs WHERE Belongs.gameId=?";
		
		//Retrieve connection
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
		//Retrieve connection
			
			//Set prepared statement values
			ps.setInt(1, gameId);
			//Set prepared statement values
			
			//Retrieve the categories from database 
			ResultSet rs = ps.executeQuery();
			//Retrieve the categories from database
					

			//Create list of retrieved Categories
			categories = new ArrayList<>();
			
			while(rs.next()) {
				String name = rs.getString("categoryName");
				Category category = new Category(name);
				categories.add(category);
			}
			//Create list of retrieved Categories
		}
		
		return categories;
	}
	
}
