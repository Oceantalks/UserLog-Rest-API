package se.drwp.APIdemo.restless.security;

public class ExpirationTimeHelper {

	public static final String generateExTimeStamp() {
		return new Long(System.currentTimeMillis()+300*1000).toString();
	}
	
}
