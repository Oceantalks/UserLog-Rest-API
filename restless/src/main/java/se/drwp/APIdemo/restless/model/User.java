package se.drwp.APIdemo.restless.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User extends AbstractEntity {
	
	@Column(unique = true)
	private Long phoneNumber;
	
	@Column(unique = true)
	private String userName;
	private String firstName;
	private String lastName;
	
	private String password;
	private String salt;
	private String stringToken;
	private String expirationTimeStamp;
	
	
	
	User() {		
	}
	
	public User(Long phoneNumber, String userName, String firstName, String lastName) {
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stringToken = "";
		this.expirationTimeStamp = "";
	}

	public User(Long phoneNumber, String userName, String firstName, String lastName, String password) {
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.salt = "";
		this.stringToken = "";
		this.expirationTimeStamp = "";
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getStringToken() {
		return stringToken;
	}

	public void setStringToken(String stringToken) {
		this.stringToken = stringToken;
	}

	public String getExpirationTimeStamp() {
		return expirationTimeStamp;
	}

	public void setExpirationTimeStamp(String expirationTimeStamp) {
		this.expirationTimeStamp = expirationTimeStamp;
	}

	@Override
	public String toString() {
		return "User [phoneNumber=" + phoneNumber + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", salt=" + salt + ", stringToken=" + stringToken
				+ ", expirationTimeStamp=" + expirationTimeStamp + "]";
	}
	
	

}
