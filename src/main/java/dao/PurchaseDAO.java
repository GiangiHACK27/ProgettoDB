package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
	
	public List<Purchase> retrievePurchaseForUsername(String username) throws SQLException {
		List<Purchase> purchases = new ArrayList<>();
		
		String query = "SELECT * FROM Purchase as P WHERE P.username = ?";
		
		//Retrieve connection
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
		//Retrieve connection
			
			//Build query			
			ps.setString(1, username);
			//Build query
			
			//Retrieve from db purchases
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//Build purchase
				Purchase purchase = new Purchase();
				
				purchase.setId(rs.getInt("id"));
				purchase.setGameId(rs.getInt("gameId"));
				purchase.setUsername(rs.getString("username"));
				purchase.setDatePurchased(rs.getString("datePurchased"));
				purchase.setPrice(rs.getInt("price"));
				//Build purchase
				
				purchases.add(purchase);
			}
			//Retrieve from db purchases
		}
		
		return purchases;
	}
}
