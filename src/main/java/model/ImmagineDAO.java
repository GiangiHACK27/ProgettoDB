package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImmagineDAO {
	
	private ImmagineDAO() {
		
	}
	
	public static Immagine getImmagineFromID(String ID) {
		
		String query = "SELECT raw,testoAlternativo FROM Immagine WHERE ID = ?";
		
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
		
		Immagine immagine = new Immagine();
		try {
			rs.next();
			
			immagine.setId(ID);
			immagine.setTestoAlternativo(rs.getString("testoAlternativo"));
			byte[] bytes = rs.getBytes("raw");
			
			immagine.setBytes(bytes);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return immagine;
	}
	
	public static void insertImmagine(Immagine immagine) {
		String query = "INSERT INTO IMMAGINE values (?, ?, ?)";
		
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
			ps.setString(1, immagine.getId());
			ps.setBytes(2, immagine.getBytes());
			ps.setString(3, immagine.getTestoAlternativo());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBConnectionPool.releaseConnection(conn);
	}
}
