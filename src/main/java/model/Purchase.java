package model;

import java.io.Serializable;

public class Purchase implements Serializable {
	private static final long serialVersionUID = 7583010091245156485L;

	public Purchase() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDatePurchased() {
		return datePurchased;
	}
	
	public void setDatePurchased(String datePurchased) {
		this.datePurchased = datePurchased;
	}

	private int id;
	private int gameId;
	private int price;
	private String username;
	private String datePurchased;	
}