package model;

import java.io.Serializable;

public class SystemRequirement implements Serializable {
	private static final long serialVersionUID = -3664824857193609703L;

	public SystemRequirement() {
		super();
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public OperatingSystem getOs() {
		return os;
	}
	public void setOs(OperatingSystem os) {
		this.os = os;
	}

	public enum OperatingSystem {
		WINDOWS,
		LINUX,
		MAC
	}
	
	private int gameId;
	private String name;
	private String value;
	private OperatingSystem os;
}
