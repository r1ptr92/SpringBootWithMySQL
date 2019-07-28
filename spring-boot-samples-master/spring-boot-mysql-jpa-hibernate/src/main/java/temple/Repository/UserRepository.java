package temple.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temple.models.User;
import temple.models.UserReg;

@Repository
public
interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailId(String emailId);
	
	public User findByMobileNumber(long mobileNum);


}