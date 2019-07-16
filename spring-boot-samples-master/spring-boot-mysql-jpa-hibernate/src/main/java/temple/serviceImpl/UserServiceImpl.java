package temple.serviceImpl;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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

	 
	@Value( "${fromGmailId}" )
	String fromEmailId;
	
	@Value( "${emailPassword}" )
	String emailPassword;
	
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
	
	
	
	@Override
	public String sendOTP(String emailId) throws AddressException, MessagingException {
		String otp="";
		try {
		boolean isNumber=emailId.matches("-?\\d+(\\.\\d+)?");
		User user;
		if(isNumber) {
			user=userDao.getByMobileNumber(emailId);
		}else {
			user=userDao.getByEmail(emailId);
		}
		Random rand = new Random();
		 otp = String.format("%04d", rand.nextInt(10000));
		 String sub = "OTP From XXXX";
		 String mailBody= "This is an email sent by xxxxx. please use "+otp+"as OTP for login ";
		sendMail(fromEmailId, emailPassword, emailId,sub,mailBody);
 	   user.setOTP(Integer.parseInt(otp));
 	   userDao.update(user);
 	   }catch(Exception ex) {
 		   System.out.println(ex);
 	   }
 	   return otp;
	}


	@Override
	public UserResponceDto login(User users) {
		System.out.println("login "+users.getEmailId() ); 
		User user=userDao.getByEmail(users.getEmailId());
		UserResponceDto userResponceDto=new UserResponceDto();
		boolean isNumber=users.getPassword().matches("-?\\d+(\\.\\d+)?");
		boolean isMatch=false;
		if(user!=null) {
			if(isNumber) {
				if(user.getOTP() == Long.parseLong(users.getPassword())){
					isMatch =true;
				}
			}else if(user.getPassword().equals(users.getPassword())) {
				isMatch =true;
			}
		}
		if(isMatch) {
			   userResponceDto.setAccessToken(user.getAccessToken());
			   userResponceDto.setEmailId(user.getEmailId());
			   userResponceDto.setMessage("login Completed");
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


	@Override
	public void sendMail(final String from, final String password, String to, String sub, String msg) {
		//Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
		
	}
	
	
}
