package model;

public class Image {
	
	public Image() {
		super();
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAltText() {
		return altText;
	}
	
	public void setAltText(String altText) {
		this.altText = altText;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	private String id;
	private String altText;
	private byte[] bytes;
}
