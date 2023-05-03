package model;

import java.io.Serializable;

public class Image implements Serializable {
	private static final long serialVersionUID = 6147511002417713785L;
	
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
