package se.drwp.APIdemo.restless.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class UserLog extends AbstractEntity {
	
	//@OneToMany()
	//@JoinColumn(name = "userId")
	@Column
	private Long userId;
	
	private String date;
	
	UserLog () {
	}
	
	public UserLog(Long id, String date) {
		this.userId = id;
		this.date = date;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "UserLog [userId=" + userId + ", date=" + date + "]";
	}


}
