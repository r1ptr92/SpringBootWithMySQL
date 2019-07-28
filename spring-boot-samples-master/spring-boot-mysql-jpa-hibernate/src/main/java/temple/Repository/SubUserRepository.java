package temple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temple.models.SubUser;
import temple.models.User;

@Repository
public
interface SubUserRepository extends JpaRepository<SubUser, Long> {
	

}