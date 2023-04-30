package model;

public class Immagine {
	
	public Immagine() {
		super();
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTestoAlternativo() {
		return testoAlternativo;
	}
	
	public void setTestoAlternativo(String testoAlternativo) {
		this.testoAlternativo = testoAlternativo;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	private String id;
	private String testoAlternativo;
	private byte[] bytes;
}
