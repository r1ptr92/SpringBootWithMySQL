package temple.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import temple.models.User;
import temple.models.UserResponceDto;

@Service
public interface UserService {
	
	public String sendOTP(String emailId) throws AddressException, MessagingException;
	
	public void sendMail(String from,String password,String to,String sub,String msg);
	
	public UserResponceDto createUser(User user);
	
	public UserResponceDto login(User emailId);
	
	

}
