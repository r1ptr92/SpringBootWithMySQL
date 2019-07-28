package temple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "sub_user")
public class SubUser {
	
	@Id
	@Column(name = "mobilenum", nullable = false)
	private long mobileNumber;
	
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private String role;
	
	public SubUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public SubUser(long mobileNumber, String password, String role, User user) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.role = role;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	
}
