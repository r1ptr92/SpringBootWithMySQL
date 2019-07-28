package temple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import temple.models.UserReg;
import temple.models.UserResponceDto;
import temple.service.UserService;
/**
 * Class UserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------


	  @Autowired
	  private UserService userService;
	  
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @PostMapping(path = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponceDto> createUser(@RequestBody UserReg users) {
	  UserResponceDto userResponceDto;
    try {
    	System.out.println("controller----------"+users.getEmailId());
    	 userResponceDto = userService.createUser(users);
    	 return ResponseEntity.accepted().body(userResponceDto);
    	}
    catch (Exception ex) {
    	userResponceDto = new UserResponceDto();
    	userResponceDto.setMessage("Error");
    	System.out.println("ssssssssssss"+ex.toString());
    	return ResponseEntity.accepted().body(userResponceDto);
    }
    
  }
  
    /**
   * Retrieve the otp for the user with the passed email address.
   */
  
  	@PostMapping(path = "/user/getotp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOtp(@RequestBody UserReg users) {
    String otp ="";
    try {
    	  otp= userService.sendOTP(users);
    	  System.out.println("otp-----------"+otp);
    	   return ResponseEntity.accepted().body(otp);
    }
    catch (Exception ex) {
    	System.out.println(ex);
      return   ResponseEntity.accepted().body("Invalide User Id");
    }
  }
  
  /**
   * Retrieve the otp for the user with the passed email address.
   */
  	@PostMapping(path = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponceDto> login(@RequestBody UserReg users) {
  	  UserResponceDto userResponceDto;
      try {
      	 userResponceDto = userService.login(users);
      	 return ResponseEntity.accepted().body(userResponceDto);
      	}
      catch (Exception ex) {
      	userResponceDto = new UserResponceDto();
      	userResponceDto.setMessage("Error");
      	System.out.println("ssssssssssss"+ex.toString());
      	return ResponseEntity.accepted().body(userResponceDto);
      }
    }
  
 
  
} // class UserController
