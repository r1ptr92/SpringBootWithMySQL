package temple.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temple.models.User;
import temple.models.UserReg;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByEmailId(String emailId);
	
	public List<User> findByMobileNumber(long mobileNum);


}