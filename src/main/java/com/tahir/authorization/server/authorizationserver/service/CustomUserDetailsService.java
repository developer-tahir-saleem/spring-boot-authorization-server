package com.tahir.authorization.server.authorizationserver.service;


import com.tahir.authorization.server.authorizationserver.exception.ResourceConflictException;
import com.tahir.authorization.server.authorizationserver.model.User;
import com.tahir.authorization.server.authorizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author developer
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return null;
//        return repo
//                .findByUsername(username)
//                .map(u -> new org.springframework.security.core.userdetails.User(
//                        u.getUsername(),
//                        u.getPassword(),
//                        u.isActive(),
//                        u.isActive(),
//                        u.isFlag(u.getFlag()),
//                        u.isStatus(),
//                        AuthorityUtils.createAuthorityList(
//                                u.getRoles()
//                                        .stream()
//                                        .map(r -> "ROLE_" + r.getName().toUpperCase())
//                                        .collect(Collectors.toList())
//                                        .toArray(new String[]{}))))
//                .orElseThrow(() -> new UsernameNotFoundException("No user with "
//                        + "the name " + username + "was found in the database"));
////        Optional<com.appiskey.authservice.model.User> users = repo.findByUsername(username);
////        if(!users.isPresent()) {
////            throw new UsernameNotFoundException("Could not find the user "+username);
////        }
////
////        User user = users.get();
//////        List<SecurityGroup> securityGroups = securityGroupService.listUserGroups(user.getCompanyId(), user.getId());
////
////        return new CustomUserDetail(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getUserDetails(s);
    }

    private UserDetails getUserDetails(String s) {
        Optional<User> user = repo.findByUsername(s);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    //
    public User findAccountByUsername(String username) throws UsernameNotFoundException {
        return ((User) getUserDetails(username));
    }

    public User register(User account, String type) {
        if (repo.countByUsername(account.getUsername()) == 0) {
            account.setEnabled(true);
            account.setAccountNonLocked(true);
            account.setAccountNonExpired(true);
            account.setCredentialsNonExpired(true);
            account.setType(type);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            return repo.save(account);
        } else {
            throw new ResourceConflictException("Username is already in use", "already in use", account);
        }
    }

    public User forget(String email) throws UsernameNotFoundException {
        return findAccountByUsername(email);
    }

    public User changePassword(String email, String password) throws UsernameNotFoundException {
        User account = findAccountByUsername(email);
        account.setPassword(passwordEncoder.encode(password));
        return repo.save(account);
    }



}
