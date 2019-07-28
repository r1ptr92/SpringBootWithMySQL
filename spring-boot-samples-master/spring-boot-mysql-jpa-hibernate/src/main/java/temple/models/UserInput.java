package temple.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInput {

	
	private String emailId;
	
	private long mobileNumber;
	
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	
	public  UserInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInput(String emailId, long mobileNumber, String password) {
		super();
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	
	
	
}
