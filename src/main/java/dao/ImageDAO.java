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
		String query = "SELECT raw, alt FROM image WHERE ID = ?";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Image image = null;

		//Get connection
		conn = ds.getConnection();
		//Get connection
		
		//Create query
		ps = conn.prepareStatement(query);
		ps.setString(1, ID);
		//Create query
		
		//Execute query
		rs = ps.executeQuery();
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
		
		conn.close();

		return image;
	}
	
	public synchronized void insertImagePezzotto(Image image) throws SQLException {
		String query = "INSERT INTO image values (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		conn = ds.getConnection();
		
		ps = conn.prepareStatement(query);
		
		ps.setString(1, image.getId());
		ps.setBytes(2, image.getBytes());
		ps.setString(3, image.getAltText());
		ps.execute();
		
		conn.close();
	}
	
	public synchronized int insertImage(byte[] bytes) throws SQLException {
		String query = "INSERT INTO image (raw) values (?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		conn = ds.getConnection();
		
		//add statement tag to return generated keys (they are needed to update represented table)
		ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		//add statement tag to return generated keys
		ps.setBytes(1, bytes);

		ps.execute();
		
		//get key from result set
		int id = 0;
		try (ResultSet keys = ps.getGeneratedKeys()) {
			keys.next();
	        id = keys.getInt(1);
	    }
		//get key from result set

		conn.close();
		return id;
	}
	
	public synchronized void connectImageGame(int imageId, int gameId, String role) throws SQLException {
		String query = "INSERT INTO represented (imageId, gameId, role) values (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		conn = ds.getConnection();
		
		ps = conn.prepareStatement(query);
		
		ps.setInt(1, imageId);
		ps.setInt(2, gameId);
		ps.setString(3, role); //roles should be enums but i break the cats

		ps.execute();

		conn.close();
	}
	
	
}
