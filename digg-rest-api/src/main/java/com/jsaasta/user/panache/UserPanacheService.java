/**
 * This class is not fully implemented, just a proof of concept.
 */
package com.jsaasta.user.panache;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.ws.rs.core.Response;
import com.jsaasta.user.User;
import com.jsaasta.user.UserRepository;

/**
 * This is commented out because DI-handling is not configured to be property-based.
 * Gets an Ambiguous dependency exception because this and UserService implements UserRepository.
 * Need to set beans depending on what jdbc-driver is used. If for example jdbc:h2 is used, this bean should be loaded.
 * If no jdbc-driver is loaded it can use the internal solution with UserService.
 * But since this is only a work test, I won't implement it to scope creep too much.
 */
//@ApplicationScoped
public class UserPanacheService implements UserRepository, PanacheRepository<Person> {
    @Override
    public Response list(int limit, int offset) {
        return Response.ok(listAll()).build();
    }

    @Override
    public Response add(User user) {
        return null;
    }

}
