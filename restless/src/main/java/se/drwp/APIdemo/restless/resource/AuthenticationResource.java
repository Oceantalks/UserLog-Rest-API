package se.drwp.APIdemo.restless.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.drwp.APIdemo.restless.model.User;
import se.drwp.APIdemo.restless.security.Credentials;
import se.drwp.APIdemo.restless.security.EncrypterHelper;
import se.drwp.APIdemo.restless.security.Token;
import se.drwp.APIdemo.restless.service.UserLogService;
import se.drwp.APIdemo.restless.service.UserService;

@Component
@Path("/authentication")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class AuthenticationResource {
	private static final String TAG = "AuthenticationResource.class";
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserLogService logService;
	
	@POST
	public Response authenticateUser(Credentials credentials) throws Exception {
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		return authenticate(username, password);
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password)
			throws Exception {
		return authenticate(username, password);
	}

	private Response authenticate(String username, String password) throws Exception {
		System.out.println(TAG + ": Authentication of user STARTS");
		User tempUser = userService.findByUserName(username);
		
		String passwordInDb = tempUser.getPassword();
		String saltInDb = tempUser.getSalt();
		String hashedPassword = EncrypterHelper.hashPassword(password.toCharArray(), Base64.decodeBase64(saltInDb));
		
		if (hashedPassword.equals(passwordInDb)) {
			Token token = new Token();
			tempUser.setStringToken(token.getAccessToken());
			tempUser.setExpirationTimeStamp(token.getExpirationTimeStamp());
			logService.createUserLog(tempUser.getId());
			userService.updateUser(tempUser.getId(), tempUser);
			System.out.println(TAG + ": Authentication of user DONE");
			return Response.ok(token).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity("Your password or username is not valid").build();
		}
		
	}

}
