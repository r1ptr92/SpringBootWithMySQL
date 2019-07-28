package temple.models;

public class UserResponceDto {

	
	private String message;
	
	private String status;
	
	private UserReg user;
	
	
	public UserReg getUser() {
		return user;
	}


	public void setUser(UserReg user) {
		this.user = user;
	}


	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
