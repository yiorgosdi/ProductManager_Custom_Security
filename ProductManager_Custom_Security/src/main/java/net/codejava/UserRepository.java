package net.codejava;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> { // since private Integer id;
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUserName(@Param("username") String username);  
}
 