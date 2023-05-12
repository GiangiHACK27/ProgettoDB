package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Game.Pegi;
import model.Game.State;

public class GameDAO extends BaseDAO {

	public GameDAO(DataSource ds) {
		super(ds);
	}
	
	public void insertGame  (int price, String name, String description,
							String state, String shortDescription,
							String releaseDate, String pegi) throws SQLException {
		// Retrieve connection
		Connection conn = ds.getConnection();
		// Retrieve connection

		String query = "INSERT into game (price, name, description, state,"
						+"shortDescription, releaseDate, pegi) values ( ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(query);
		
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

		//Close connection
		conn.close();
		//Close connection

	}
}
