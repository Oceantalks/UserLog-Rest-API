package se.drwp.APIdemo.restless.service;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import se.drwp.APIdemo.restless.model.User;
import se.drwp.APIdemo.restless.repository.UserRepository;
import se.drwp.APIdemo.restless.security.EncrypterHelper;


@Service
public class UserService {
	private static final String TAG ="UserService.class";
	
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Transactional
	public User createUser(User user) throws Exception {
		System.out.println(TAG + ": Creating user and saving to database STARTS");
		User newUser = null;
				
		try{
			user.setSalt(EncrypterHelper.generateSalt());
			user.setPassword(EncrypterHelper.hashPassword(user.getPassword().toCharArray(), Base64.decodeBase64(user.getSalt())));
			newUser = userRepository.save(user);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (UnexpectedRollbackException e) {
			e.printStackTrace();
		}
		
		if (newUser == null) {
			throw new Exception(TAG + ": Could not create a new user");
		}
		
		System.out.println(TAG + ": Creating user and saving to database DONE");
		return newUser;
	}
	
	@Transactional
	public User updateUser(Long id, User user) throws Exception {
		System.out.println(TAG + ": Updating user and saving to database STARTS");
		User tempUser = null;
		User returnUser = null;
		
		if (findById(id) == null) {
			throw new Exception(TAG + ": User with id " + id + " does not exist");
		}
		
		if (findByUserName(user.getUserName()) == null) {
			throw new Exception(TAG + ": User with username " + user.getUserName() + " does not exist");
		}
				
		try{
			tempUser = findById(user.getId());
			tempUser.setUserName(user.getUserName());
			tempUser.setFirstName(user.getFirstName());
			tempUser.setLastName(user.getLastName());
			tempUser.setPhoneNumber(user.getPhoneNumber());
			tempUser.setSalt(user.getSalt());
			tempUser.setPassword(user.getPassword());
			tempUser.setStringToken(user.getStringToken());
			tempUser.setExpirationTimeStamp(user.getExpirationTimeStamp());
			returnUser = userRepository.save(tempUser);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (UnexpectedRollbackException e) {
			e.printStackTrace();
		}
		
		if (returnUser == null) {
			throw new Exception(TAG + ": Could not update the user");
		}
		
		System.out.println(TAG + ": Updating user and saving to database DONE");
		return returnUser;
	}

	@Transactional
	public User findById (Long id) throws Exception {
		System.out.println(TAG + ": Retrieving user by Id STARTS");
		User tempUser = null;
		
		try{
			tempUser = userRepository.findById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (UnexpectedRollbackException e) {
			e.printStackTrace();
		}
		
		if (tempUser == null) {
			throw new Exception(TAG + ": Could not retrieve user with id " + id);
		}
		
		System.out.println(TAG + ": Retrieved user with id " + id);
		return tempUser;
	}
	
	@Transactional
	public Boolean exists(Long id) throws Exception {
		Boolean exists = false;
		try {
			exists = userRepository.exists(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (exists) {
			throw new Exception(TAG + ": User with id " + id + " does not exists");
		}
		return exists;
	}
	
	@Transactional
	public User findByStringToken(String s) throws Exception {
		System.out.println(TAG + ": Retrieving user by token STARTS");
		User tempUser = null;
		
		try{
			tempUser = userRepository.findByStringToken(s);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (UnexpectedRollbackException e) {
			e.printStackTrace();
		}
		
		if (tempUser == null) {
			throw new Exception(TAG + ": Could not retrieve user with token " + s);
		}
		
		System.out.println(TAG + ": Retrieved user with token " + s);
		return tempUser;
	}
	
	@Transactional
	public User findByUserName(String s) throws Exception {
		System.out.println(TAG + ": Retrieving user by token STARTS");
		User tempUser = null;
		
		try {
			tempUser = userRepository.findByUserName(s);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (UnexpectedRollbackException e) {
			e.printStackTrace();
		}
		
		if (tempUser == null) {
			throw new Exception(TAG + ": Could not find user with username: " + s);
		}
		
		return tempUser;
	}
	
}
