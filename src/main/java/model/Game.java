package model;

import java.io.Serializable;

public class Game implements Serializable {

	public Game() {
		//The class is a been so the constructor can be an empty constructor
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Pegi getPegi() {
		return pegi;
	}

	public void setPegi(Pegi pegi) {
		this.pegi = pegi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public enum State{
		RELEASED("Released"),
		BETA("Beta"),
		ALPHA("Alpha"),
		COMING_SOON("Coming_soon"),
		UNLISTED("Unlisted");

		private String value;
		State(String value) {
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	public enum Pegi{
		PEGI_3(3),
		PEGI_7(7),
		PEGI_12(12),
		PEGI_16(16),
		PEGI_18(18);

		Integer value;
		
		Pegi(Integer value) {
			this.value = value;
		}
		
		public Integer getValue() {
			return value;
		}
	}
	
	private Integer id;
	private int price;
	private String name;
	private String description;
	private State state;
	private String shortDescription;
	private String releaseDate;
	private Pegi pegi;
	private String publisher;
	
	private static final long serialVersionUID = -5695993368802410513L;
}
