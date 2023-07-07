package utility;

import javax.servlet.ServletException;

public class BackendException extends ServletException {
	private static final long serialVersionUID = 7891947248797205125L;

	public BackendException() {
		super("Backend error");
	}
}
