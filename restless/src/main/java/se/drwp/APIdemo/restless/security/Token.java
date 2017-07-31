package se.drwp.APIdemo.restless.security;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Token {
	
	private String accessToken;
	private String expirationTimeStamp;
	
	
	public Token() {
		super();
		this.accessToken = generateToken();
		this.expirationTimeStamp = ExpirationTimeHelper.generateExTimeStamp();
	}
	
	private String generateToken() {
		return UUID.randomUUID().toString();
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpirationTimeStamp() {
		return expirationTimeStamp;
	}
	public void setExpirationTimeStamp(String expirationTimeStamp) {
		this.expirationTimeStamp = expirationTimeStamp;
	}
	
	

}
