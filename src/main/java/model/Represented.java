package model;

import java.io.Serializable;

public class Represented implements Serializable {
	private static final long serialVersionUID = -7506959225240797102L;

	public Represented() {
		super();
		//The class is a been so the constructor can be an empty constructor
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	private int gameId;
	private String role;
	private int id;
}
