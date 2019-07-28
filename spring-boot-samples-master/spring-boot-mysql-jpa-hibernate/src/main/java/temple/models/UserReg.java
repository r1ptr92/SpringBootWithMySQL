package temple.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an UserReg for this web application.
 */
@Entity
@Table(name = "user_reg")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReg {

	@Id
	@Column(name = "mobilenum", nullable = false)
	private long mobileNumber;

	@Column(name = "email", nullable = false)
	private String emailId;

	@Column(name = "first_name", nullable = false)	
	private String firstName;

	@Column(name = "last_name", nullable = false)	
	private String lastName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "pincodel", nullable = false)
	private int pincode;

	@Column(name = "trust_id", nullable = false)
	private String trustId;

	@Column(name = "temple_name", nullable = false)
	private String templeName;

	@Transient
	private String role;

	public UserReg(long mobileNumber, String emailId, String firstName, String lastName, String password,
			String address, String city, String country, int pincode, String trustId, String templeName, String role,
			User user) {
		super();
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		this.trustId = trustId;
		this.templeName = templeName;
		this.role = role;
	}

	public UserReg() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getTrustId() {
		return trustId;
	}

	public void setTrustId(String trustId) {
		this.trustId = trustId;
	}

	public String getTempleName() {
		return templeName;
	}

	public void setTempleName(String templeName) {
		this.templeName = templeName;
	}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

} // class UserReg
