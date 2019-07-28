package temple.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "mobilenum", nullable = false)
	private long mobileNumber;
	
	@Column(name = "email", nullable = false)
	private String emailId;

	@Column(name = "userOTP")	
	private String userOTP;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "logon_time", nullable = false)
	private Date logonTime;
	
	@Column(name = "last_logged")
	private Date last_logged;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(long mobileNumber, String emailId, String userOTP, String password, Date logonTime, Date last_logged,
			UserReg userReg, SubUser subUser) {
		super();
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.userOTP = userOTP;
		this.password = password;
		this.logonTime = logonTime;
		this.last_logged = last_logged;
	}

	
	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLogonTime() {
		return logonTime;
	}

	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}

	public Date getLast_logged() {
		return last_logged;
	}

	public void setLast_logged(Date last_logged) {
		this.last_logged = last_logged;
	}

	
} // class User
