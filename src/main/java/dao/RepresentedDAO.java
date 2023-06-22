package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class RepresentedDAO extends BaseDAO{

	public RepresentedDAO(DataSource ds) {
		super(ds);
	}

	public synchronized Integer retrieveIdImage(int gameId, String role) throws SQLException {
		Integer id = null;
		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query
			String query = "SELECT * FROM Represented WHERE gameId = ? AND role = ?";
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, gameId);
			ps.setString(2, role);
			//Construct query
			
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				id = rs.getInt("imageId");
			}
			
		} finally {
			if(ps != null)
				ps.close();
		}
		
		return id;
	}
}
