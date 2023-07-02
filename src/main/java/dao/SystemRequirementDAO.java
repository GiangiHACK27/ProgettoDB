package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.SystemRequirement;

public class SystemRequirementDAO extends BaseDAO {

	public SystemRequirementDAO(DataSource ds) {
		super(ds);
	}
	
	public List<SystemRequirement> retrieveAllSystemRequirement(int gameId) throws SQLException {
		List<SystemRequirement> listSystemRequirements = new ArrayList<>();
		
		ResultSet rs = null;
		
		String query = "SELECT * FROM SystemRequirement WHERE gameId = ?";
		
		// Retrieve connection and make prepared statement 
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query); ) {
		// Retrieve connection and make prepared statement 	

			//Encode into prepared statement data
			ps.setInt(1, gameId);
			//Encode into prepared statement data
			
			//Execute query
			rs = ps.executeQuery();
			//Execute query
			
			if(rs != null) {
				while(rs.next()) {
					SystemRequirement sr = new SystemRequirement();
					
					//Retrieve data from result set to create a systemRequirement
					sr.setGameId(gameId);
					sr.setName(rs.getString("name"));
					sr.setOs(SystemRequirement.OperatingSystem.valueOf(rs.getString("os").toUpperCase()));
					sr.setValue(rs.getString("value"));
					//Retrieve data from result set to create a systemRequirement
					
					listSystemRequirements.add(sr);
				}
			}
		}
		
		return listSystemRequirements;
	}
	
	public void insertRequirement(SystemRequirement req) throws SQLException {
		String query = "INSERT INTO systemrequirement (name, os, gameId, value) VALUES (?, ?, ?, ?)";
		
		// Retrieve connection and make prepared statement with tag to return generated keys
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ) {
		// Retrieve connection and make prepared statement with tag to return generated keys	

			//Set prepared statement values
			ps.setString(1, req.getName());
			ps.setString(2, req.getOs().getSystem());
			ps.setInt(3, req.getGameId());
			ps.setString(4, req.getValue());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database
		}
	}
}