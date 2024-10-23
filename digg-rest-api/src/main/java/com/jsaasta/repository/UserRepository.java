package com.jsaasta.repository;

import com.jsaasta.user.User;
import jakarta.ws.rs.core.Response;

public interface UserRepository {

    Response list(int limit, int offset);

    Response add(User user);

}
