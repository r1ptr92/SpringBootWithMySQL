package temple.service;

import org.springframework.stereotype.Service;

import temple.models.UserReg;
import temple.models.UserResponceDto;

@Service
public interface UserService {
	
	public String sendOTP(UserReg user) ;
	
	public void sendMail(String from,String password,String to,String sub,String msg);
	
	public UserResponceDto createUser(UserReg userReg);
	
	public UserResponceDto login(UserReg user);

}
