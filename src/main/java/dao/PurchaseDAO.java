package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Purchase;

public class PurchaseDAO extends BaseDAO {

	public PurchaseDAO(DataSource ds) {
		super(ds);
	}

	public void insert(Purchase purchase) throws SQLException {
	
		String query = "INSERT INTO Purchase (gameId, username, price) values (?, ?, ?)";
		
		//Retrieve connection
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
		//Retrieve connection
			
			//Build query			
			ps.setInt(1, purchase.getGameId());
			ps.setString(2, purchase.getUsername());
			ps.setInt(3, purchase.getPrice());
			//Build query
			
			ps.execute();
		} 
	}
}
