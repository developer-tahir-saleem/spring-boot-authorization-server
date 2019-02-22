package com.tahir.authorization.server.authorizationserver.repository;


import com.tahir.authorization.server.authorizationserver.model.User;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;


public interface UserRepository extends Repository<User, Long> {
//    Optional<User> findByUsername(String username);

    Collection<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Integer countByUsername(String username);

    User save(User account);

    void deleteById(Long id);
}
