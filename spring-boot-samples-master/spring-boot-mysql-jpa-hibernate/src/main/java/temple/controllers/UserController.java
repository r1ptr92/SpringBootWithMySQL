package temple.controllers;

import temple.Dao.UserDao;
import temple.models.User;
import temple.models.UserResponceDto;
import temple.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
/**
 * Class UserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private UserDao userDao;

	  @Autowired
	  private UserService userService;
	  
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @PostMapping(path = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponceDto> createUser(@RequestBody User users) {
	  UserResponceDto userResponceDto;
    try {
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
  	@GetMapping(path = "/user/getotp/{emailId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getSite(@PathVariable("emailId") String emailId) {
    String otp ="";
    try {
    	  otp= userService.sendOTP(emailId);
    	   return otp;
    }
    catch (Exception ex) {
      return "Invalide User Id";
    }
  }
  /**
   * Retrieve the otp for the user with the passed email address.
   */
  @GetMapping(path = "/user/login/{emailId}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponceDto> login(@PathVariable("emailId") String emailId,@PathVariable("password") String password) {
	  UserResponceDto userResponceDto;
    try {
    	 userResponceDto = userService.login(emailId, password);
    	 return ResponseEntity.accepted().body(userResponceDto);
    }
    catch (Exception ex) {
    	userResponceDto = new UserResponceDto();
    	userResponceDto.setMessage("Error");
      return ResponseEntity.accepted().body(userResponceDto);
    }
  }
  @GetMapping(path = "/user/test", produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<UserResponceDto> test() {
 	  UserResponceDto userResponceDto;
 	 userResponceDto = new UserResponceDto();
 	 userResponceDto.setMessage("Ok");
       return ResponseEntity.accepted().body(userResponceDto);
     }
  
} // class UserController
