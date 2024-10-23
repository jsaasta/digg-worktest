package com.jsaasta.user;

import jakarta.ws.rs.core.Response;

public interface UserRepository {

    Response list(int limit, int offset);

    Response add(User user);

}
