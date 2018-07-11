package com.example.webdevsummer22018serverashu95.repositories;





import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverashu95.models.User;


public interface UserRepository extends CrudRepository<User,Integer> 

{
 
	
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User> findUserByUsername(
		@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	Optional<User> findUserByCredentials(
		@Param("username") String username, 
		@Param("password") String password);
	
	

}
