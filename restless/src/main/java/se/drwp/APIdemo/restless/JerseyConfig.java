package se.drwp.APIdemo.restless;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.drwp.APIdemo.restless.resource.AuthenticationFilterResource;
import se.drwp.APIdemo.restless.resource.AuthenticationResource;
import se.drwp.APIdemo.restless.resource.UserLogResource;
import se.drwp.APIdemo.restless.resource.UserResource;


@Component
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(UserResource.class);
		register(AuthenticationResource.class);
		register(AuthenticationFilterResource.class);
		register(UserLogResource.class);
	}

}
