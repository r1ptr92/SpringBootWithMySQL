package temple.serviceImpl;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import temple.Dao.UserDao;
import temple.models.User;
import temple.models.UserResponceDto;
import temple.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	 
	@Value( "${fromGmailId}" )
	String fromEmailId;
	
	@Value( "${emaiPassword}" )
	String emaiPassword;
	
	@Autowired
	private UserDao userDao;
	 
	@Override
	public UserResponceDto createUser(User user) {
		 User existingUser =userDao.getByEmail(user.getEmailId());
		   UserResponceDto userResponceDto=new UserResponceDto();
		   if(!(existingUser!=null && existingUser.getFirstName().length()>0)){
	  	   user.setLoggedIn(true);
	  	   user.setStatus("Active");
	  	   Date date = new Date();
	  	   user.setRegistrationDate(date);
	  	   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		   String accessToken = passwordEncoder.encode(user.getEmailId()+date.toString());
		   user.setAccessToken(accessToken);
		  
		   userDao.create(user);
		   user=userDao.getByEmail(user.getEmailId());
		   userResponceDto.setAccessToken(user.getAccessToken());
		   userResponceDto.setEmailId(user.getEmailId());
		   userResponceDto.setMessage("Registration Completed");
		   userResponceDto.setMobileNomber(user.getMobileNumber());
		   userResponceDto.setPaid(user.isPaid());
		   userResponceDto.setUserId(user.getUserId());
		   userResponceDto.setStatus("Success");
		   }else {
			   userResponceDto.setMessage("User already exists with this email address");
			   userResponceDto.setStatus("Fail");
		   }
		 
		return userResponceDto;
	}
	
	
	public void setMailServerProperties() {
		String emailPort = "587";//gmail's smtp port
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}
	public void createEmailMessage(String emailId,String otp) throws AddressException,MessagingException {
		String emailSubject = "OTP From XXXX";
		String emailBody = "This is an email sent by xxxxx. please use "+otp+"as OTP for login ";
		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);
		emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email
	}
	@Override
	public void sendOTP(String emailId) throws AddressException, MessagingException {
		boolean isNumber=emailId.matches("-?\\d+(\\.\\d+)?");
		User user;
		if(isNumber) {
			user=userDao.getByMobileNumber(emailId);
		}else {
			user=userDao.getByEmail(emailId);
		}
		Random rand = new Random();
		String otp = String.format("%04d", rand.nextInt(10000));
		String emailHost = "smtp.gmail.com";
		String fromUser = fromEmailId;
		String fromUserEmailPassword = emaiPassword;
		createEmailMessage(user.getEmailId(), otp);
		setMailServerProperties();
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
 	   user.setOTP(Integer.parseInt(otp));
 	   userDao.update(user);
	}


	@Override
	public UserResponceDto login(String emailId, String password) {
		User user=userDao.getByEmail(emailId);
		UserResponceDto userResponceDto=new UserResponceDto();
		boolean isNumber=password.matches("-?\\d+(\\.\\d+)?");
		boolean isMatch=false;
		if(user!=null) {
			if(isNumber) {
				if(user.getOTP() == Long.parseLong(password)){
					isMatch =true;
				}
			}else if(user.getPassword() == password) {
				isMatch =true;
			}
		}
		if(isMatch) {
			   userResponceDto.setAccessToken(user.getAccessToken());
			   userResponceDto.setEmailId(user.getEmailId());
			   userResponceDto.setMessage("Registration Completed");
			   userResponceDto.setMobileNomber(user.getMobileNumber());
			   userResponceDto.setPaid(user.isPaid());
			   userResponceDto.setUserId(user.getUserId());
			   userResponceDto.setStatus("Success");
		}else {
			 userResponceDto.setMessage("Invalid login creadentials");
			 userResponceDto.setStatus("Fail");
		}
		return userResponceDto;
	}
	
	
}
