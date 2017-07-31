package se.drwp.APIdemo.restless.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import se.drwp.APIdemo.restless.model.UserLog;

public interface UserLogRepository extends CrudRepository<UserLog, Long> {
	
	ArrayList<UserLog> findAllByUserId (Long id);

}
