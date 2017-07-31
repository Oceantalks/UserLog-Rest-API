package se.drwp.APIdemo.restless.repository;

import org.springframework.data.repository.CrudRepository;


import se.drwp.APIdemo.restless.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	// WHEN USING CRUD REPOSITORY MAKE SURE THE METHOD SIGNATURE IS THE SAME AS THE FIELD NAME IN ENTITY
	// FOR EXAMPLE:
	// private String expirationTime;
	// THEN THE INTERFACE METHOD SHOULD BE NAMED:
	// User findByExpirationTime(String s);
	
	User findById(Long id);
	
	User findByStringToken(String s);
	
	User findByUserName(String s);
	
}
