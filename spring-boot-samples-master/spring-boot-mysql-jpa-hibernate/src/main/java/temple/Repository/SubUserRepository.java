package temple.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temple.models.SubUser;

@Repository
public
interface SubUserRepository extends JpaRepository<SubUser, Long> {
	

}