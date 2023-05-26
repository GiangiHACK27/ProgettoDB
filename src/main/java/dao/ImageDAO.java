package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


import model.Image;

public class ImageDAO extends BaseDAO {
	
	public ImageDAO(DataSource ds) {
		super(ds);
	}
	
	public synchronized Image getImageFromID(String ID) throws SQLException {

		Image image = null;

		String query = "SELECT raw, alt FROM image WHERE ID = ?";
		
		//Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		//Retrieve connection and make prepared statement
			
			//Create query
			ps.setString(1, ID);
			//Create query
			
			//Execute query
			ResultSet rs = ps.executeQuery();
			if(rs == null)
				return null;
			rs.next();
			//Execute query
			
			//Create the image object
			image = new Image();
			image.setId(ID);
			image.setAltText(rs.getString("alt"));
			byte[] bytes = rs.getBytes("raw");
			image.setBytes(bytes);
			//Create the image object
		}

		return image;
	}
	
	public synchronized void insertImagePezzotto(Image image) throws SQLException {
		
		String query = "INSERT INTO image values (?, ?, ?)";
		
		//Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		//Retrieve connection and make prepared statement
			
			ps.setString(1, image.getId());
			ps.setBytes(2, image.getBytes());
			ps.setString(3, image.getAltText());
			ps.execute();
	
		}
	}
	
	public synchronized int insertImage(byte[] bytes) throws SQLException {
		int id = 0;
		
		String query = "INSERT INTO image (raw) values (?)";
		
		//Retrieve connection and make prepared statement, add statement tag to return generated keys (they are needed to update represented table)
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
		//Retrieve connection and make prepared statement, add statement tag to return generated keys (they are needed to update represented table)
			
			ps.setBytes(1, bytes);

			ps.execute();
			
			//get key from result set
			try (ResultSet keys = ps.getGeneratedKeys()) {
				keys.next();
		        id = keys.getInt(1);
		    }
			//get key from result set		
		}
		
		return id;
	}
	
	public synchronized void connectImageGame(int imageId, int gameId, String role) throws SQLException {

		String query = "INSERT INTO represented (imageId, gameId, role) values (?, ?, ?)";
		
		//Retrieve connection and make prepared statement	
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		//Retrieve connection and make prepared statement
			
			ps.setInt(1, imageId);
			ps.setInt(2, gameId);
			ps.setString(3, role); //roles should be enums but i break the cats

			ps.execute();	
		}
	}	
}
