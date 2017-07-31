package se.drwp.APIdemo.restless.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.drwp.APIdemo.restless.model.UserLog;
import se.drwp.APIdemo.restless.security.Secured;
import se.drwp.APIdemo.restless.service.UserLogService;

@Component
@Secured
@Path("/user")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class UserLogResource {
	
	
	@Autowired
	private UserLogService logService;
	
	@GET
	@Path("/userLog/{userId}")
	public List<UserLog> getAllLogsFromUser(@PathParam("userId") Long userId) throws Exception {
		return logService.findAllByUserId(userId);
	}

}
