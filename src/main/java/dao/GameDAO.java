package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource; 

import model.Category;
import model.Game;
import model.Game.Pegi;
import model.Game.State;
import model.Purchase;

public class GameDAO extends BaseDAO {

	public GameDAO(DataSource ds) {
		super(ds);
	}
	
	public int insertGame  (Game game) throws SQLException {
		int id = 0;
		
		String query = "INSERT into game (price, name, description, state,"
				+"shortDescription, releaseDate, pegi, publisher) values ( ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// Retrieve connection and make prepared statement with tag to return generated keys
		try (Connection conn = ds.getConnection();PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); ) {
		// Retrieve connection and make prepared statement with tag to return generated keys	

			//Set prepared statement values
			ps.setInt(1, game.getPrice());
			ps.setString(2, game.getName());
			ps.setString(3, game.getDescription());
			ps.setString(4, game.getState().getValue());
			ps.setString(5, game.getShortDescription());
			ps.setString(6, game.getReleaseDate());
			ps.setInt(7, game.getPegi().getValue());
			ps.setString(8, game.getPublisher());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database
			
			//get key from result set
			try (ResultSet keys = ps.getGeneratedKeys()) {
				keys.next();
		        id = keys.getInt(1);
		    }
			//get key from result set
		}
		
		return id;
	}
	
	public void updateGame  (Game game) throws SQLException {
		
		String query = "UPDATE game "
				+ "SET price= ?, name= ?, description= ?, state= ?,"
				+"shortDescription= ?, releaseDate= ?, pegi= ?, publisher= ?"
				+ "WHERE game.id = ?";
		
		// Retrieve connection and make prepared statement with tag to return generated keys
		try (Connection conn = ds.getConnection();PreparedStatement ps = conn.prepareStatement(query); ) {
		// Retrieve connection and make prepared statement with tag to return generated keys	

			//Set prepared statement values
			ps.setInt(1, game.getPrice());
			ps.setString(2, game.getName());
			ps.setString(3, game.getDescription());
			ps.setString(4, game.getState().getValue());
			ps.setString(5, game.getShortDescription());
			ps.setString(6, game.getReleaseDate());
			ps.setInt(7, game.getPegi().getValue());
			ps.setString(8, game.getPublisher());
			ps.setInt(9, game.getId());
			//Set prepared statement values

			//Insert user into database
			ps.execute();
			//Insert user into database

		}
		
	}
	
	public int countGames(List<Category> categories, int maxPrice, int pegi, String searchText, boolean unListed) throws SQLException {
		int size = 0;
		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query string
			StringBuilder builder = new StringBuilder("");
			builder.append("(");
			for(int i=0;i<categories.size();i++) {
				if(i == categories.size() - 1) 
					builder.append("?");
				else
					builder.append("?,");
			}
			builder.append(")");
						
			String categoriesToSearch = builder.toString();
			//Construct query string
			
			//Construct query
			String query = "SELECT DISTINCT Count(id) as count"
                    + " FROM Game as G, Belongs as B, Category as C "
                            + "WHERE G.id = B.gameId AND C.name = B.categoryName AND C.name in "
                            + categoriesToSearch
                            + "AND G.price <= ? AND "
                            + "G.pegi <= ? AND INSTR(G.name, ?) > 0 ";
			
			if(unListed == false)
				query = "SELECT DISTINCT Count(id) as count"
	                    + " FROM Game as G, Belongs as B, Category as C "
	                            + "WHERE G.id = B.gameId AND C.name = B.categoryName AND C.name in "
	                            + categoriesToSearch
	                            + "AND G.price <= ? AND "
	                            + "G.state != 'unlisted' AND "
	                            + "G.pegi <= ? AND INSTR(G.name, ?) > 0 ";
			
			ps = conn.prepareStatement(query);
			
			int i = 1;
			for(Category c : categories) {
				ps.setString(i, c.getName());
				
				i++;
			}
			
			ps.setInt(i, maxPrice);
			i++;
			ps.setInt(i, pegi);
			i++;
			ps.setString(i, searchText);
			//Construct query
			
			//Retrieve the categories from database
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				size = rs.getInt("count");
			}
			//Retrieve the categories from database
		}
		finally {
			if(ps != null)
				ps.close();
		}
		
		return size;
	}
	
	public List<Game> retrieveGames(List<Category> categories, int maxPrice, int pegi, String searchText, String order, int limit, int offset, boolean unListed) throws SQLException {
		List<Game> games = new ArrayList<>();
		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query string
			StringBuilder builder = new StringBuilder("");
			builder.append("(");
			for(int i=0;i<categories.size();i++) {
				if(i == categories.size() - 1) 
					builder.append("?");
				else
					builder.append("?,");
			}
			builder.append(")");
						
			String categoriesToSearch = builder.toString();
			//Construct query string
			
			//Construct query
			String query = "SELECT DISTINCT id, price, G.name, description, shortDescription, releaseDate, state, pegi, publisher"
                    + " FROM Game as G, Belongs as B, Category as C "
                            + "WHERE G.id = B.gameId AND C.name = B.categoryName AND C.name in "
                            + categoriesToSearch
                            + "AND G.price <= ? AND "
                            + "G.pegi <= ? AND INSTR(G.name, ?) > 0 "
                            + "ORDER BY " + order
                            + " LIMIT ? OFFSET ?";
			
			if(unListed == false) {
				query = "SELECT DISTINCT id, price, G.name, description, shortDescription, releaseDate, state, pegi, publisher"
	                    + " FROM Game as G, Belongs as B, Category as C "
	                            + "WHERE G.id = B.gameId AND C.name = B.categoryName AND C.name in "
	                            + categoriesToSearch
	                            + "AND G.price <= ? AND "
	                            + "G.pegi <= ? AND INSTR(G.name, ?) > 0 "
	                            + "AND G.state != 'unlisted' "
	                            + "ORDER BY " + order
	                            + " LIMIT ? OFFSET ?";
			}
			
			ps = conn.prepareStatement(query);
			
			int i = 1;
			for(Category c : categories) {
				ps.setString(i, c.getName());
				
				i++;
			}
			
			ps.setInt(i, maxPrice);
			i++;
			ps.setInt(i, pegi);
			i++;
			ps.setString(i, searchText);
			i++;
			ps.setInt(i, limit);
			i++;
			ps.setInt(i, offset);
			//Construct query
			
			//Retrieve the categories from database
			ResultSet rs = ps.executeQuery();
			//Retrieve the categories from database
			
			//Create the list of Game			
			while(rs.next()) {
				Game game = new Game();
				
				game.setId(rs.getInt("id"));
				game.setName(rs.getString("name"));
				game.setDescription(rs.getString("description"));
				game.setPrice(rs.getInt("price"));
				game.setShortDescription(rs.getString("shortDescription"));
				game.setReleaseDate(rs.getString("releaseDate"));
				game.setPublisher(rs.getString("publisher"));
				Pegi pegi1 = Pegi.valueOf("PEGI_" + rs.getString("pegi"));
				game.setPegi(pegi1);
				State state = State.valueOf(rs.getString("state").toUpperCase());
				game.setState(state);
				
				games.add(game);
			}
			//Create the list of Game
		}
		finally {
			if(ps != null)
				ps.close();
		}

		return games;
	}
	
	public Game retrieveGame(int gameId) throws SQLException {
		Game game = null;
		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query
			String query = "SELECT * FROM game WHERE id = ?";
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, gameId);
			//Construct query
			
			//Retrieve game from the database
			ResultSet rs = ps.executeQuery();
			//Retrieve game from the database
			
			//Analize result set
			if(rs.next()) {
				game = new Game();
				
				game.setName(rs.getString("name"));
				game.setPrice(rs.getInt("price"));
				game.setDescription(rs.getString("description"));
				game.setReleaseDate(rs.getString("releaseDate"));
				game.setShortDescription(rs.getString("shortDescription"));
				game.setPublisher(rs.getString("publisher"));
				game.setState(State.valueOf(rs.getString("state").toUpperCase()));
				game.setId(rs.getInt("id"));
				game.setPegi(Pegi.valueOf("PEGI_" + rs.getString("pegi")));
			}
			//Analize result set
			
		} finally {
			if(ps != null)
				ps.close();
		}
		
		return game;
	}
	
	public int retrieveMaxPriceGame(boolean unListed) throws SQLException {
		int maxPrice = 0;
		
		String query = "SELECT MAX(price) as max FROM Game WHERE state != 'unlisted'";
		if(unListed == true)
			query = "SELECT MAX(price) as max FROM Game";
		
		//Retrieve connection and make prepared statement
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
		//Retrieve connection and make prepared statement
			
			//Retrieve the categories from database
			ResultSet rs = ps.executeQuery();
			//Retrieve the categories from database
			
			//Create the list of Game
			if(rs.next())
				maxPrice = rs.getInt("max");
			//Create the list of Game
		}
				
		return maxPrice;
	}
	
	public boolean usernameAlreadyExist(String username) throws SQLException {		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query
			String query = "SELECT * FROM user WHERE username = ?";
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, username);
			//Construct query
			
			ResultSet rs = ps.executeQuery();
			
			//If we find a user with the same username, we return true
			if(rs != null && rs.next())
				return true;
			//If we find a user with the same username, we return true
			
		} finally {
			if(ps != null)
				ps.close();
		}
		
		return false;
	}
	
	public boolean emailAlreadyExist(String email) throws SQLException {		
		PreparedStatement ps = null;
		
		//Retrieve connection
		try (Connection conn = ds.getConnection()) {
		//Retrieve connection
			
			//Construct query
			String query = "SELECT * FROM user WHERE email = ?";
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, email);
			//Construct query
			
			ResultSet rs = ps.executeQuery();
			
			//If we find a user with the same username, we return true
			if(rs != null && rs.next())
				return true;
			//If we find a user with the same username, we return true
			
		} finally {
			if(ps != null)
				ps.close();
		}
		
		return false;
	}
	
	public List<AbstractMap.SimpleEntry<Game, Purchase>> retrievePurchasedGameForUsername(String username) throws SQLException {
		List<AbstractMap.SimpleEntry<Game, Purchase>> gamePurchased = new ArrayList<>();
		
		String query = "SELECT G.id,name,description,shortDescription,state,pegi,publisher,P.price,P.datePurchased FROM Game as G, Purchase as P WHERE G.id = P.gameId AND P.username = ?";
		
		//Retrieve connection
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
		//Retrieve connection
			
			//build query
			ps.setString(1, username);
			//build query
			
			//Retrieve all purchased game from db
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//Construct the game
				Game game = new Game();
				
				game.setId(rs.getInt("G.id"));
				game.setName(rs.getString("name"));
				game.setDescription(rs.getString("description"));
				game.setShortDescription(rs.getString("shortDescription"));
				game.setState(Game.State.valueOf(rs.getString("state").toUpperCase()));
				game.setPublisher(rs.getString("publisher"));
				//Construct the game
				
				//Construct the purchase
				Purchase purchase = new Purchase();
				
				purchase.setDatePurchased(rs.getString("P.datePurchased"));
				purchase.setPrice(rs.getInt("P.price"));
				//Construct the purchase
				
				//Add game and purchase to the list
				AbstractMap.SimpleEntry<Game, Purchase> entry = new AbstractMap.SimpleEntry<>(game, purchase);
				
				gamePurchased.add(entry);
				//Add game and purchase to the list
			}
			//Retrieve all purchased game from db
		}
		
		return gamePurchased;
	}
}