package se.drwp.APIdemo.restless.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.drwp.APIdemo.restless.model.User;
import se.drwp.APIdemo.restless.service.UserService;

@Component
@Path("/addUser")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class UserResource {
	private static final String TAG = "UserResource";
	
	@Autowired
	private UserService userService;
	
	@POST
	public Response createUser(User user , @Context UriInfo uriInfo) throws Exception {
		System.out.println(TAG + ": Creating response STARTS");
		User newUser = userService.createUser(user);
		String id = String.valueOf(newUser.getId());
		URI newUserURILocation = uriInfo.getAbsolutePathBuilder().path(id).build();
		System.out.println(TAG + ": Response has been created and sent with the created user: " + newUser.toString());
		return Response.created(newUserURILocation).entity(newUser).build();
	}

}
