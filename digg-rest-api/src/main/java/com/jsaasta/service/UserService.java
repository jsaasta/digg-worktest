package com.jsaasta.service;

import com.jsaasta.user.PaginatedUserResult;
import com.jsaasta.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import com.jsaasta.repository.UserRepository;

import java.util.*;

@ApplicationScoped
public class UserService implements UserRepository {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    private Set<User> users = Collections.synchronizedSet(new LinkedHashSet<>());

    public UserService() {
        users.add(new User("Daisy Duck", "Streetnr 1", "daisy@acme.org", "070-1233123"));
        users.add(new User("Donald Duck", "Streetnr 2", "donald@acme.org", "071-2324513"));
        users.add(new User("Donna Duck", "Streetnr 3", "donna@acme.org", "070-7772724"));
        users.add(new User("Darkwing Duck", "Streetnr 4", "darkwing@acme.org", "073-6656156"));
        users.add(new User("Clara Cluck", "Streetnr 5", "clara@acme.org", "075-2355523"));
        users.add(new User("El Capitan", "Streetnr 6", "elcapitan@acme.org", "070-7757513"));
        users.add(new User("Emperor Evron", "Streetnr 7", "evron@acme.org", "078-3161636"));
        users.add(new User("Emutilda", "Streetnr 8", "emutilda@acme.org", "074-5518735"));
        users.add(new User("Quackmore Duck", "Streetnr 9", "quackmore@acme.org", "074-6618715"));
        users.add(new User("Taurus Bulba", "Streetnr 10", "taurusbulba@acme.org", "071-1351335"));
        users.add(new User("Chip", "Streetnr 11", "chip@acme.org", "070-0013415"));
        users.add(new User("Dale", "Streetnr 12", "dale@acme.org", "074-1235551"));
        users.add(new User("Fanny the Goose", "Streetnr 13", "fanny@acme.org", "074-3333321"));
        users.add(new User("General Lunaris", "Streetnr 14", "lunaris@acme.org", "074-4414414"));
        users.add(new User("Cal Crudon", "Streetnr 15", "calcrudon@acme.org", "074-4516636"));
    }

    public UserService(Set<User> users) {
        this.users = users;
    }

    @Override
    public Response list(int limit, int offset) {
        if (limit != 0 || offset != 0) {

            int amountOfPages = (int) Math.ceil((double) users.size() / (double) limit);
            int currentPage = (offset / limit) + 1;
            Set<User> paginatedUsers = new LinkedHashSet<>();

            users.stream().skip(offset).limit(limit).forEach(paginatedUsers::add);

            PaginatedUserResult result = new PaginatedUserResult(paginatedUsers, amountOfPages, currentPage);
            return Response.ok(result).build();
        }

        return Response.ok(users).build();
    }

    @Override
    public Response add(User user) {
        user.setId(UUID.randomUUID());
        users.add(user);
        LOG.info("Added person: " + user.toString());
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}

