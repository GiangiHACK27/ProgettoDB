package utility;

import java.security.InvalidParameterException;

public class InvalidParameters extends InvalidParameterException {
	private static final long serialVersionUID = -534865816457493643L;

	public InvalidParameters() {
		super("Invalid parameters");
	}
}
