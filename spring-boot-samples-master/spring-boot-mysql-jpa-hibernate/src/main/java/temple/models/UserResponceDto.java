package temple.models;

public class UserResponceDto {

	
	private long userId;
	
	private String emailId;
	
	private String mobileNomber;
	
	private String accessToken;
	
	private boolean isPaid;
	
	private String message;
	
	private String status;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNomber() {
		return mobileNomber;
	}

	public void setMobileNomber(String mobileNomber) {
		this.mobileNomber = mobileNomber;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
