package utility;

import javax.servlet.ServletException;

public class MethodNotSupportedException extends ServletException {
	private static final long serialVersionUID = 1L;
	
	public MethodNotSupportedException() {
		super("Method not suppoted");
	}

}
