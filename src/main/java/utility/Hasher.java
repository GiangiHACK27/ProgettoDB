package utility;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	private Hasher() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String toHash(String string) throws NoSuchAlgorithmException {
		String hashString = null;
		
		if(string == null)
			return null;
		
		//convert password to hashed version
		java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
		byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
		StringBuilder bld = new StringBuilder("");
		
		for (int i = 0; i < hash.length; i++) {
			bld.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3));
		}
		
		hashString = bld.toString();
		//convert password to hashed version
		
		return hashString;
	}
}
