package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAO {
	
	private ImageDAO() {
		
	}
	
	public static Image getImageFromID(String ID) {
		System.out.println(ID);
		String query = "SELECT raw, altText FROM image WHERE ID = ?";
		
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBConnectionPool.getConnection();
			
			PreparedStatement ps = null;
			ps = conn.prepareStatement(query);
			ps.setString(1, ID);

			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		Image image = new Image();
		try {
			rs.next();
			
			image.setId(ID);
			image.setAltText(rs.getString("altText"));
			byte[] bytes = rs.getBytes("raw");
			
			image.setBytes(bytes);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static void insertImage(Image image) {
		String query = "INSERT INTO image values (?, ?, ?)";
		
		Connection conn = null;
		try {
			conn = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ps.setString(1, image.getId());
			ps.setBytes(2, image.getBytes());
			ps.setString(3, image.getAltText());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBConnectionPool.releaseConnection(conn);
	}
}
