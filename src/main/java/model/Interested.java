package model;

import java.io.Serializable;

public class Interested implements Serializable {
	private static final long serialVersionUID = 4331446474668232895L;
	
	public enum Category {
		CART,
		WISHLIST
	}
	
	public Interested() {
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	private String username;
	private int gameId;
	private Category category;
}