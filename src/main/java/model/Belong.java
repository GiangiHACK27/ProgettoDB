package model;

import java.io.Serializable;

public class Belong implements Serializable {
	private static final long serialVersionUID = -3514113031396396172L;

	public Belong() {
		super();
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	private String category;
	private int gameId;
}
