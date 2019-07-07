package temple.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long userId;
	
	@NotNull
	private String emailId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;
	
	
	
	private String mobileNumber;

	private String address;
	
	private String city;

	private String country;
	
	private int pincode;
	
	private String trustName;
	
	private String trustAddress;
	
	private String userRegistration;
	
	private String loginType;
	
	private String password;

	private boolean isPaid;
	
	private boolean isLoggedIn;
	
	private long OTP;

	private String accessToken;
	
	private String status;
	
	private Date registrationDate;
	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	public User(long userId, String emailId, String firstName, String lastName, String mobileNumber, String address,
			String city, String country, int pincode, String trustName, String trustAddress, String userRegistration,
			String loginType, String password, boolean isPaid, boolean isLoggedIn, long oTP, String accessToken,
			String status, Date registrationDate) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		this.trustName = trustName;
		this.trustAddress = trustAddress;
		this.userRegistration = userRegistration;
		this.loginType = loginType;
		this.password = password;
		this.isPaid = isPaid;
		this.isLoggedIn = isLoggedIn;
		this.OTP = oTP;
		this.accessToken = accessToken;
		this.status = status;
		this.registrationDate = registrationDate;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getTrustName() {
		return trustName;
	}

	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}

	public String getTrustAddress() {
		return trustAddress;
	}

	public void setTrustAddress(String trustAddress) {
		this.trustAddress = trustAddress;
	}

	public String getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(String userRegistration) {
		this.userRegistration = userRegistration;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public long getOTP() {
		return OTP;
	}

	public void setOTP(long oTP) {
		OTP = oTP;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	
	
} // class User
