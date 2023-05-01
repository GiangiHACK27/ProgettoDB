package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ImageDAO extends BaseDAO {
	
	public ImageDAO(DataSource ds) {
		super(ds);
	}
	
	public synchronized Image getImageFromID(String ID) {
		String query = "SELECT raw, alt FROM image WHERE ID = ?";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Image image = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
	
	public synchronized void insertImage(Image image) {
		String query = "INSERT INTO image values (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, image.getId());
			ps.setBytes(2, image.getBytes());
			ps.setString(3, image.getAltText());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
