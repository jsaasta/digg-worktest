package com.jsaasta.user;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import com.jsaasta.user.PaginatedUserResult;
import com.jsaasta.user.User;
import com.jsaasta.user.UserService;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserServiceTest {

    @Inject
    private UserService userService;

    /**
     * "Philosophical" question if it's better to keep things DRY and
     * use a setup(), for example setting up a Set of users,
     * or to really keep each test separated with no commonality to prevent
     * clashes or "bleeding" between tests, and performance if it creates a lot in a global context.
     * (Case in point: check UserResourceTest and the problem I have with global context there).
     * I prefer to avoid a @Before in unit tests just to make sure that another test
     * doesn't influence other tests. Smaller tests / test suites like this would be fine to use
     * BeforeEach. Everything have its pros and cons.
     *
     * @BeforeEach
     * void setup() {}
     */

    @Test
    void testList() {
        Set<User> users = Collections.synchronizedSet(new LinkedHashSet<>());
        users.add(new User("Name", "Address", "email", "0"));
        users.add(new User("Name1", "Address1", "email1", "1"));
        users.add(new User("Name2", "Address2", "email2", "2"));
        users.add(new User("Name3", "Address3", "email3", "3"));
        users.add(new User("Name4", "Address4", "email4", "4"));
        userService = new UserService(users);

        assertEquals(users, userService.list(0, 0).getEntity());

    }

    @Test
    void testListWithLimit_1() {
        Set<User> users = Collections.synchronizedSet(new LinkedHashSet<>());
        User expectedUser = new User("Name", "Address", "email", "0");
        User expectedUser1 = new User("Name1", "Address1", "email1", "1");
        User expectedUser2 = new User("Name2", "Address2", "email2", "2");
        User expectedUser3 = new User("Name3", "Address3", "email3", "3");
        User expectedUser4 = new User("Name4", "Address4", "email4", "4");
        users.add(expectedUser);
        users.add(expectedUser1);
        users.add(expectedUser2);
        users.add(expectedUser3);
        users.add(expectedUser4);

        Set<User> expectedUserResultSet = Collections.synchronizedSet(new LinkedHashSet<>());
        expectedUserResultSet.add(expectedUser);

        userService = new UserService(users);

        PaginatedUserResult actualResult = (PaginatedUserResult) userService.list(1, 0).getEntity();
        assertEquals(expectedUserResultSet, actualResult.getUsers());
        assertEquals(1, actualResult.getCurrentPage());
        assertEquals(5, actualResult.getPages());
    }


}