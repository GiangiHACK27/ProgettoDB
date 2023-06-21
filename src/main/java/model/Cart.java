package model;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.*;

public class Cart implements Cloneable, Serializable {
	public Cart(String username) {
		this.username = username;
		this.gameIds = new ArrayList<>();
	}
	
	public String getUsername() {
		return username;
	}
	
	public List<Integer> getGames() {
		return gameIds;
	}
	
	public void addGame(Integer gameId) throws InvalidParameterException {
		//Control if the game is already in the list
		if(gameIds.contains(gameId))
			throw new InvalidParameterException("Game already in the cart");
		
		gameIds.add(gameId);
	}
	
	public void removeGame(Integer gameId) throws InvalidParameterException {
		for(int i = 0;i < gameIds.size();i++) {
			if(gameIds.get(i).equals(gameId)) {
				gameIds.remove(i);
				return;
			}
		}
		
		throw new InvalidParameterException("Game not present in the cart");
	}
	
    public Cart clone()
    {
        try {
        	Cart clone = null;
			clone = (Cart)super.clone();
	        clone.gameIds = new ArrayList<>(gameIds);
	        return clone;
		} catch (CloneNotSupportedException e) {
			return null;
		}
    }
	
	private String username;
	private List<Integer> gameIds; 
	private static final long serialVersionUID = 5689597178793094307L;
}
