package temple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temple.models.User;
import temple.models.UserReg;

@Repository
public
interface UserRegRepository extends JpaRepository<UserReg, Long> {

public List<UserReg> findByEmailId(String emailId);
	
public List<UserReg> findByMobileNumber(long mobileNum);
}