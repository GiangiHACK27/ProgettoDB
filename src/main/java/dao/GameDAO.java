package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import model.Game.Pegi;
import model.Game.State;

public class GameDAO extends BaseDAO {

	public GameDAO(DataSource ds) {
		super(ds);
	}
	
	public int insertGame  (int price, String name, String description,
							String state, String shortDescription,
							String releaseDate, String pegi) throws SQLException {
		// Retrieve connection
		Connection conn = ds.getConnection();
		// Retrieve connection

		String query = "INSERT into game (price, name, description, state,"
						+"shortDescription, releaseDate, pegi) values ( ?, ?, ?, ?, ?, ?, ?)";

		//Make prepared statement with tag to return generated keys
		PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		//Make prepared statement with tag to return generated keys

		//Set prepared statement values
		ps.setInt(1, price);
		ps.setString(2, name);
		ps.setString(3, description);
		ps.setString(4, State.valueOf(state.toUpperCase()).getValue());
		ps.setString(5, shortDescription);
		ps.setString(6, releaseDate);
		ps.setInt(7, Pegi.valueOf(pegi.toUpperCase()).getValue());
		//Set prepared statement values

		//Insert user into database
		ps.execute();
		//Insert user into database
		
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
}
