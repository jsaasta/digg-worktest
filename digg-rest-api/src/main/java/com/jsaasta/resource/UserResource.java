package com.jsaasta.resource;

import com.jsaasta.user.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import com.jsaasta.repository.UserRepository;

import java.util.*;

@Path("/digg/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOG = Logger.getLogger(UserResource.class);

    private UserRepository userRepository;

    private Set<User> users = Collections.synchronizedSet(new LinkedHashSet<>());

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    public Response list(@QueryParam("limit") int limit, @QueryParam("offset") int offset) {
        try {
            return userRepository.list(limit, offset);
        } catch (Exception err) {
            LOG.error("Failed to get users.");
            LOG.error(err);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not get users.").build();
        }
    }

    @POST
    public Response add(User user) {
        try {
            return userRepository.add(user);
        } catch (Exception err) {
            LOG.error("Failed to create user.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not create user.").build();
        }
    }

}
