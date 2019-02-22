package com.tahir.authorization.server.authorizationserver.service;



import com.tahir.authorization.server.authorizationserver.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    User findOne(long id);
    void delete(long id);
}
