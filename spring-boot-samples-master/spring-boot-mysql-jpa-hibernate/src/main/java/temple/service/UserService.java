package temple.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import temple.models.User;
import temple.models.UserResponceDto;

@Service
public interface UserService {
	
	public void sendOTP(String emailId) throws AddressException, MessagingException;
	
	public UserResponceDto createUser(User user);
	
	public UserResponceDto login(String emailId,String password);
	
	

}
