package temple.serviceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import org.springframework.stereotype.Service;

import temple.Repository.SubUserRepository;
import temple.Repository.UserRegRepository;
import temple.Repository.UserRepository;
import temple.models.SubUser;
import temple.models.User;
import temple.models.UserReg;
import temple.models.UserResponceDto;
import temple.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Value( "${fromGmailId}" )
	String fromEmailId;

	@Value( "${emailPassword}" )
	String emailPassword;

	@Autowired
	UserRegRepository userRegRepo;
	
	@Autowired
	UserRepository userRepo;

	
	@Autowired
	SubUserRepository subUserRepo;
	
	
	@Override
	public UserResponceDto createUser(UserReg userReg) {
		List<UserReg> existingUserList =userRegRepo.findByEmailId(userReg.getEmailId());
		UserReg existingUser = null;
		UserResponceDto userResponceDto=new UserResponceDto();
		if(existingUserList ==null || existingUserList.size()==0){
			existingUserList =userRegRepo.findByMobileNumber(userReg.getMobileNumber());
			
			if(existingUserList ==null || existingUserList.size()==0){
				User user =new User(); 
				user.setEmailId(userReg.getEmailId());
				user.setMobileNumber(userReg.getMobileNumber());
				user.setPassword(userReg.getPassword());
				user.setLogonTime(new Date());
				SubUser subUser= new SubUser();
				subUser.setMobileNumber(userReg.getMobileNumber());
				subUser.setPassword(userReg.getPassword());
				subUser.setRole("User");
				
				subUserRepo.save(subUser);
				userRepo.save(user);
				userRegRepo.save(userReg);

				userResponceDto.setUser(userReg);
				userResponceDto.setMessage("Registration Completed");
				userResponceDto.setStatus("Success");
			}else {
				userResponceDto.setMessage("User already exists with this mobile number");
				userResponceDto.setStatus("Fail");
			}

		}else {
			userResponceDto.setMessage("User already exists with this email address");
			userResponceDto.setStatus("Fail");
		}

		return userResponceDto;
	}


	@Override
	public String sendOTP(UserReg userInput) {
		String otp="";
		try {
			List<User> userList =null; 
			if(userInput.getEmailId()!=null && userInput.getEmailId().length()>0) {
				userList=userRepo.findByEmailId(userInput.getEmailId());
			}else if(userInput.getMobileNumber() > 0) {
				userList=userRepo.findByMobileNumber(userInput.getMobileNumber());
			}
			if(userList!=null && userList.size()>0) {
				User user = userList.get(0);
				Random rand = new Random();
				otp = String.format("%04d", rand.nextInt(10000));
				String sub = "OTP From XXXX";
				String mailBody= "Please use "+otp+" as OTP ";
				String mobilemessage= "Please use "+otp+" as OTP ";
				sendMail(fromEmailId, emailPassword, user.getEmailId(),sub,mailBody);
				String url="http://203.212.70.200/smpp/sendsms";
				String parms = "username=UNIQUEDEMOHTTP&password=UNIQUEDEMO2019&to="+user.getMobileNumber()+"&from=UNIQUE&udh=&text="+mobilemessage+"&dlr-mask=19&dlr-url";
				sendSMS(url,parms);
				user.setUserOTP(otp);
				userRepo.save(user);
			}
		}catch(Exception ex) {
			return "EmailId or Mobile no is invalid";
		}
		return otp;
	}


	@Override
	public UserResponceDto login(UserReg userInput) {
		List<User> userList =null; 
		User user =null;
		UserReg userReg = null;
		if(userInput.getEmailId()!=null && userInput.getEmailId().length()>0) {
			userList=userRepo.findByEmailId(userInput.getEmailId());
		}else if(userInput.getMobileNumber() > 0) {
			userList=userRepo.findByMobileNumber(userInput.getMobileNumber());
			
		}
		boolean isMatch=false;
		UserResponceDto userResponceDto=new UserResponceDto();
		if(userList!=null && userList.size()>0) {
			user = userList.get(0);
			boolean isNumber=userInput.getPassword().matches("-?\\d+(\\.\\d+)?");
			if(isNumber) {
				if(user.getUserOTP().equals(userInput.getPassword()))
					isMatch =true;
			}
			else if(user.getPassword().equals(userInput.getPassword()))
				isMatch =true;

		}
		if(isMatch) {
			userReg=userRegRepo.findByMobileNumber(user.getMobileNumber()).get(0);
			user.setLast_logged(new Date());
			user.setLogonTime(new Date());
			userRepo.save(user);
			if(userReg!=null) {
				userResponceDto.setUser(userReg);
			}
			userResponceDto.setMessage("login Completed");
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

	// HTTP POST request
	private void sendSMS(String url,String urlParameters) throws Exception {


		String USER_AGENT = "Mozilla/5.0";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
}
