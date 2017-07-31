package se.drwp.APIdemo.restless.resource;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.drwp.APIdemo.restless.model.User;
import se.drwp.APIdemo.restless.security.ExpirationTimeHelper;
import se.drwp.APIdemo.restless.security.Secured;
import se.drwp.APIdemo.restless.service.UserService;

@Component
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilterResource implements ContainerRequestFilter {
	private static final String TAG = "AutenticationFilterResource.class";

	@Autowired
	private UserService userService;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(TAG + ": Filtering request STARTS");
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
		
		try {
			authorizationHeader = authorizationHeader.substring("Bearer".length()).trim();
			
			User user = userService.findByStringToken(authorizationHeader);
			String expirationTimeStamp = user.getExpirationTimeStamp();
			
			if (Long.parseLong(expirationTimeStamp) > System.currentTimeMillis()) {
				user.setExpirationTimeStamp(ExpirationTimeHelper.generateExTimeStamp());
				userService.updateUser(user.getId(), user);
			} else {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity("Your login session has expired, please login again").build());
			}
			
		} catch(Exception e) {
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
		}
	}

}
